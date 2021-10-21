package org.wit.eventmngr.console.views

import org.wit.eventmngr.console.controllers.EventController
import org.wit.eventmngr.console.controllers.EventUIController
import org.wit.eventmngr.console.models.EventModel
import tornadofx.*

class EventList : View() {
    private val ctrl : EventUIController by inject()

    override val root = tableview(ctrl.events.events.asObservable()) {
        column("ID", EventModel::id)
        column("TITLE", EventModel::title)
        column("DESCRIPTION", EventModel::description)
        column("LOCATION", EventModel::location)
    }
}