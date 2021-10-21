package org.wit.eventmngr.console.views

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.transformation.FilteredList
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.eventmngr.console.controllers.EventUIController
import org.wit.eventmngr.console.models.EventModel
import tornadofx.*

class ListEventScreen : View("List Placemarks") {
    val model = ViewModel()
    val _id = model.bind { SimpleStringProperty() }
    val eventUIController: EventUIController by inject()
    val tableContent = eventUIController.events.findAll()
    var filteredData = FXCollections.observableList(tableContent)
    var filteredList = FilteredList<EventModel>(FXCollections.observableList(tableContent))
    val data = tableContent.observable()
    override val root = vbox {
        setPrefSize(800.0, 300.0)
        this += EventList::class
        hbox {
            this += EventList::class
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