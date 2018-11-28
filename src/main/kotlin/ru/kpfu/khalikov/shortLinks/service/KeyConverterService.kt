package ru.kpfu.khalikov.shortLinks.service

interface KeyConverterService {
    fun idToKey(id : Long):String
    fun keyToId(key:String):Long
}
