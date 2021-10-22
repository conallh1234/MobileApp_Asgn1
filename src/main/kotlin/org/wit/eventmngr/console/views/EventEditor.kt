package org.wit.eventmngr.console.views


import org.wit.eventmngr.console.controllers.EventUIController
import javafx.scene.Parent
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import org.wit.eventmngr.console.models.Event
import org.wit.eventmngr.console.models.EventModel
import org.wit.eventmngr.console.models.EventModel2
import tornadofx.*

class EventEditor : View("Event Editor") {
    override val root = BorderPane()
    val eventUIController: EventUIController by inject()
    val model = EventModel2(Event())

    var nameField : TextField by singleAssign()
    var descriptionField : TextField by singleAssign()
    var locationField : TextField by singleAssign()
    var eventTable : TableView<EventModel> by singleAssign()

    val events = eventUIController.events.events.asObservable()

    var prevSelection: Event? = null

    init {
        with(root) {

            center{
                tableview(events){
                    eventTable = this
                    column("ID", Event::idProperty)
                    column("TITLE", Event::nameProperty)
                    column("DESCRIPTION", Event::descriptionProperty)
                    column("LOCATION", Event::locationProperty)

                    model.rebindOnChange(this){ selectedEvent ->
                        if (selectedEvent != null) {
                            item = eventUIController.events.events2.getOrNull(selectedEvent.id.toInt()) ?: Event()
                        }

                    }
                }
            }

            right{
                form{
                    fieldset ("Edit Event") {
                        field("Name"){
                            textfield(){
                                nameField = this
                            }
                        }
                        field("Description"){
                            textfield(){
                                descriptionField = this
                            }
                        }
                        field("Location"){
                            textfield(){
                                locationField = this
                            }
                        }
                        button("Save Changes") {
                            enableWhen(model.dirty)
                            action {
                                save()
                            }
                        }
                        button("Reset"){
                            model.rollback()
                        }

                    }
                }
            }
        }
    }

    private fun save(){
        model.commit()

        val event = model.item

        eventUIController.update(event.id.toLong(), event.name, event.description, event.location)
        println("Saving changes to event ${event.name}")
    }

}