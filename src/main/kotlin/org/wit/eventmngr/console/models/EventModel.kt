package org.wit.eventmngr.console.models

import tornadofx.getProperty
import java.util.ArrayList

data class EventModel(var id : Long? = 0,
                      var title : String = "",
                      var description : String = "",
                      var location : String = "" ){
    //fun idProperty() = getProperty(EventModel::id)
}