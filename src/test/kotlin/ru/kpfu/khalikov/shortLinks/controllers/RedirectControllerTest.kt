package ru.kpfu.khalikov.shortLinks.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.client.match.MockRestRequestMatchers.header
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.kpfu.khalikov.shortLinks.ShortLinksApplication
import ru.kpfu.khalikov.shortLinks.service.KeyMapperService

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(ShortLinksApplication::class))
@WebAppConfiguration
class RedirectControllerTest{

    @Autowired lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMVC: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        mockMVC = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    private val PATH = "/aAbBcCdD"
    private val REDIRECT_STATUS: Int = 302
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://vk.com"

    @Test
    fun controllerMustReddirectUsWhenRequesIsSeccuessful(){
        mockMVC.perform(get(PATH))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val BAD_PATH = "/bad_path"

    private val NOT_FOUND: Int = 404

    @Test fun controllerMustReturn404IfBadKey(){
        mockMVC.perform(get(BAD_PATH))
                .andExpect(status().`is`(NOT_FOUND))
    }

}