package org.wit.eventmngr.console.views

import org.wit.eventmngr.console.models.Event
import org.wit.eventmngr.console.views.EventList
import org.wit.eventmngr.console.models.EventModel
import org.wit.eventmngr.console.models.EventModel2
import tornadofx.*

class EventSearch : View(){
    val model : EventModel2 by inject()

    override val root = form {
        fieldset {
            field("ID"){
                var _id = textfield()

            }
        }
        button("Apply"){
            setOnAction {
                fire()
            }
        }
    }
}

