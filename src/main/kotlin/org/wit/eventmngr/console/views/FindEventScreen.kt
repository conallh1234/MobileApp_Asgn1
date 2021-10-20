package org.wit.eventmngr.console.views

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.eventmngr.console.controllers.EventUIController
import org.wit.eventmngr.console.models.EventModel
import tornadofx.*

class FindEventScreen : View("Find Event") {

    val model = ViewModel()
    val eventUIController: EventUIController by inject()
    val _id = model.bind { SimpleStringProperty() }
    var tableContent = eventUIController.events.findOne(4406933375874914725)
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
                        tableContent = eventUIController.events.findOne(_id.toString().toLong())
                    }
                }
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