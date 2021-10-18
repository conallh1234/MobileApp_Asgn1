package org.wit.eventmngr.console.controllers

import mu.KotlinLogging
import org.wit.eventmngr.console.models.EventJSONStore
import org.wit.eventmngr.console.models.EventModel
import org.wit.eventmngr.console.views.EventView

class EventController {
    // val events = PlacemarkMemStore()
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
        logger.info { "Shutting Down Placemark Console App" }
    }

    fun menu() :Int { return eventView.menu() }

    fun add(){
        var aPlacemark = EventModel()

        if (eventView.addPlacemarkData(aPlacemark))
            events.create(aPlacemark)
        else
            logger.info("Placemark Not Added")
    }

    fun list() {
        eventView.listevents(events)
    }

    fun update() {

        eventView.listevents(events)
        var searchId = eventView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            if(eventView.updatePlacemarkData(aPlacemark)) {
                events.update(aPlacemark)
                eventView.showPlacemark(aPlacemark)
                logger.info("Placemark Updated : [ $aPlacemark ]")
            }
            else
                logger.info("Placemark Not Updated")
        }
        else
            println("Placemark Not Updated...")
    }

    fun delete() {
        eventView.listevents(events)
        var searchId = eventView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            events.delete(aPlacemark)
            println("Placemark Deleted...")
            eventView.listevents(events)
        }
        else
            println("Placemark Not Deleted...")
    }

    fun search() {
        val aPlacemark = search(eventView.getId())!!
        eventView.showPlacemark(aPlacemark)
    }


    fun search(id: Long) : PlacemarkModel? {
        var foundPlacemark = events.findOne(id)
        return foundPlacemark
    }

    fun dummyData() {

    }

}