package org.wit.eventmngr.console.views

import javafx.collections.FXCollections
import javafx.collections.transformation.FilteredList
import org.wit.eventmngr.console.controllers.EventController
import org.wit.eventmngr.console.controllers.EventUIController
import org.wit.eventmngr.console.models.EventModel
import org.wit.eventmngr.console.models.Event
import org.wit.eventmngr.console.models.EventModel2

import tornadofx.*

class EventList : View() {
    private val ctrl : EventUIController by inject()
    public val filteredList = FilteredList<EventModel>(FXCollections.observableList(ctrl.events.events.asObservable()))
    private val model : EventModel2 by inject()


    override val root = tableview(ctrl.events.events.asObservable()) {
        column("ID", EventModel::id)
        column("TITLE", EventModel::title)
        column("DESCRIPTION", EventModel::description)
        column("LOCATION", EventModel::location)
        //bindSelected(model)

    }


}