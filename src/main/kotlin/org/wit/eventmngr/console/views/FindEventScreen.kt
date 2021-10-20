package org.wit.eventmngr.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import jdk.jfr.Event
import org.wit.eventmngr.console.controllers.EventUIController
import org.wit.eventmngr.console.models.EventModel
import tornadofx.*

class FindEventScreen : View("Find Event") {

    val model = ViewModel()
    var event = mutableListOf<EventModel>()
    val eventUIController: EventUIController by inject()
    val _id = model.bind { SimpleStringProperty() }
    var tableContent = eventUIController.events.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Event ID") {
                textfield(_id).required()
            }
            button("Search") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        var add = eventUIController.events.findOne(_id.toString().toLong())
                        if (add != null) {
                            event.add(add)
                            tableContent = event
                        }
                    }
                }
            }
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
}