package ru.kpfu.khalikov.shortLinks.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.servlet.mvc.Controller
import ru.kpfu.khalikov.shortLinks.ShortLinksApplication
import ru.kpfu.khalikov.shortLinks.service.KeyMapperService


@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(ShortLinksApplication::class))
@WebAppConfiguration
class AddControllerTest{
    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMVC: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: AddController

    private val LINK: String = "link"

    private val KEY: String = "key"

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        mockMVC = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
        whenever(service.add(LINK)).thenReturn(KEY)
    }
    @Test
    fun whenUserAddLinkHeTakesAKey(){
        mockMVC.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(AddController.AddRequest(LINK))))
        .andExpect(MockMvcResultMatchers.jsonPath("$.key", Matchers.equalTo(KEY)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.link", Matchers.equalTo(LINK)))
    }


}