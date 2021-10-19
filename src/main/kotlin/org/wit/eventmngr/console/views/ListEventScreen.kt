package org.wit.eventmngr.console.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.eventmngr.console.controllers.EventUIController
import org.wit.eventmngr.console.models.EventModel
import tornadofx.*

class ListEventScreen : View("List Placemarks") {

    val eventUIController: EventUIController by inject()
    val tableContent = eventUIController.events.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", EventModel::id)
            readonlyColumn("TITLE", EventModel::title)
            readonlyColumn("DESCRIPTION", EventModel::description)
            readonlyColumn("LOCATION", EventModel::location)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    eventUIController.closeList()
                }
            }
        }
    }

}