package ru.kpfu.khalikov.shortLinks.controllers

import org.mockito.Mockito

fun <T> whenever(call: T) = Mockito.`when`(call)