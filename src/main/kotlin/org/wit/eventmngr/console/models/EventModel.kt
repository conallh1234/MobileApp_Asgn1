package org.wit.eventmngr.console.models

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.util.StringConverter
import tornadofx.*
import java.util.ArrayList

data class EventModel(var id : Long = 0,
                      var title : String = "",
                      var description : String = "",
                      var location : String = "" ){
    fun idProperty() = getProperty(EventModel::id)
    fun titleProperty() = getProperty(EventModel::id)
    fun descriptionProperty() = getProperty(EventModel::id)
    fun locationProperty() = getProperty(EventModel::id)

}

//class EventModel2(id : Long? = null, title : String? = null, description : String? = null, location : String? = null ){
//    val idProperty = SimpleLongProperty(this, "id", generateRandomId())
//    var id by idProperty
//
//    val titleProperty = SimpleStringProperty(this, "name", "")
//    var title by titleProperty
//    val descriptionProperty = SimpleStringProperty(this, "description", "")
//    var description by descriptionProperty
//    val locationProperty = SimpleStringProperty(this, "location", "")
//    var location by locationProperty
//}

class Event(id: String? = null, name : String? = null, description: String? = null, location: String? = null){
    val idProperty = SimpleStringProperty(this, "id", generateRandomId().toString())
    var id by property(id)
    fun idProperty() = getProperty(Event::id)

    val nameProperty = SimpleStringProperty(this, "name", "")
    var name by property(name)
    fun nameProperty() = getProperty(Event::name)

    val descriptionProperty = SimpleStringProperty(this, "description", "")
    var description by property(description)
    fun descriptionProperty() = getProperty(Event::description)

    val locationProperty = SimpleStringProperty(this, "location", "")
    var location by property(location)
    fun locationProperty() = getProperty(Event::location)
}



class EventModel2(event: Event) : ItemViewModel<Event>(event){
    val id = bind(EventModel::idProperty)
    val title = bind(EventModel::titleProperty)
    val description = bind(EventModel::descriptionProperty)
    val location = bind(EventModel::locationProperty)
}