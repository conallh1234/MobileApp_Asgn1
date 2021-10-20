package org.wit.eventmngr.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.eventmngr.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "events.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<EventModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class EventJSONStore : EventStore {

    var events = mutableListOf<EventModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<EventModel> {
        return events
    }

    override fun findOne(id: Long): EventModel? {
        return events.find { p -> p.id == id }
    }

    override fun create(event: EventModel) {
        event.id = generateRandomId()
        events.add(event)
        serialize()
    }

    override fun update(event: EventModel) {
        var foundEvent = findOne(event.id!!)
        if (foundEvent != null) {
            foundEvent.title = event.title
            foundEvent.description = event.description
        }
        serialize()
    }

    override fun delete(event: EventModel) {
        events.remove(event)
        serialize()
    }

    internal fun logAll() {
        events.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(events, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        events = Gson().fromJson(jsonString, listType)
    }
}

