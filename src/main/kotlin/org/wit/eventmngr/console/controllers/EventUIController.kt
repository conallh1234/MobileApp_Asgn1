package org.wit.eventmngr.console.controllers

import mu.KotlinLogging
import org.wit.eventmngr.console.models.EventJSONStore
import org.wit.eventmngr.console.models.EventModel
import org.wit.eventmngr.console.views.AddEventScreen
import org.wit.eventmngr.console.views.ListEventScreen
import org.wit.eventmngr.console.views.MenuScreen

import tornadofx.Controller
import tornadofx.runLater

class EventUIController : Controller() {

    val events = EventJSONStore()
    val events2 = EventJSONStore().events2
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Event Manager TornadoFX UI App" }
    }
    fun add(_title : String, _description : String, _location : String){

        var anEvent = EventModel(title = _title, description = _description, location = _location)
        events.create(anEvent)
        logger.info("Event Added")
    }

    fun update(_id : Long,_title : String, _description : String, _location : String){
        var anEvent = events.findOne(_id)
        if(anEvent != null) {
            events.updateUI(anEvent, _title, _description, _location)
        }
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListEventScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        events.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddEventScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddEventScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListEventScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}