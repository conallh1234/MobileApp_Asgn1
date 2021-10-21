package org.wit.eventmngr.console.views

import org.wit.eventmngr.console.models.EventModel
import tornadofx.*

class EventSearch : View(){
    //val model : EventModel by inject()

    override val root = form {
        fieldset {
            field("ID"){
                textfield()
            }
        }
        button("Search"){
            setOnAction {
               // model.
            }
        }
    }
}