package org.wit.eventmngr.console.controllers

import mu.KotlinLogging
import org.wit.eventmngr.console.models.EventJSONStore
import org.wit.eventmngr.console.models.EventModel
import org.wit.eventmngr.console.views.EventView

class EventController {
    // val events = EventMemStore()
    val events = EventJSONStore()
    val eventView = EventView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Event Manager App" }
        println("Created by Conall Heffernan")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Event Manager App" }
    }

    fun menu() :Int { return eventView.menu() }

    fun add(){
        var anEvent = EventModel()

        if (eventView.addEventData(anEvent))
            events.create(anEvent)
        else
            logger.info("Event Not Added")
    }

    fun list() {
        eventView.listevents(events)
    }

    fun update() {

        eventView.listevents(events)
        var searchId = eventView.getId()
        val anEvent = search(searchId)

        if(anEvent != null) {
            if(eventView.updateEventData(anEvent)) {
                events.update(anEvent)
                eventView.showEvent(anEvent)
                logger.info("Event Updated : [ $anEvent ]")
            }
            else
                logger.info("Event Not Updated")
        }
        else
            println("Event Not Updated...")
    }

    fun delete() {
        eventView.listevents(events)
        var searchId = eventView.getId()
        val anEvent = search(searchId)

        if(anEvent != null) {
            events.delete(anEvent)
            println("Event Deleted...")
            eventView.listevents(events)
        }
        else
            println("Event Not Deleted...")
    }

    fun search() {
        val anEvent = search(eventView.getId())!!
        eventView.showEvent(anEvent)
    }


    fun search(id: Long) : EventModel? {
        var foundEvent = events.findOne(id)
        return foundEvent
    }

    fun dummyData() {

    }

}