package org.wit.eventmngr.console.views

import com.sun.org.apache.xpath.internal.operations.Bool
import org.wit.eventmngr.console.models.AttendeeModel
import org.wit.eventmngr.console.models.EventJSONStore
//import org.wit.eventmngr.console.models.EventMemStore
import org.wit.eventmngr.console.models.EventModel

class EventView {

    fun menu() : Int {
        var option : Int
        var input : String?

        println("EVENT MANAGER SYSTEM - MAIN MENU")
        println(" 1. Add An Event")
        println(" 2. Update an Event's Title & Description")
        println(" 3. Update an Events's Attendees")
        println(" 4. List All Stored Events")
        println(" 5. Search Stored Events")
        println(" 6. Delete An Event")
        println(" -1. Exit App")
        println()
        print("Enter Option: ")
        input = readLine()!!
        option = if(input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listEvents(events : EventJSONStore) {
        println("List All Events")
        println()
        events.logAll()
        println()
    }

    fun showEvent(event : EventModel) {
        if(event != null)
            println("Event Details [ $event ]")
        else
            println("Event Not Found...")
    }

    fun addEventData(event: EventModel) : Boolean {
        var count : Int
        var i = 0

        println()
        print("Enter a Title : ")
        event.title = readLine()!!
        print("Enter a Description : ")
        event.description = readLine()!!
        print("Number of Attendees: ")
        count = readLine()!!.toIntOrNull()!!
        if(count!=null){
            while (i < count){
                var name : String
                var paid : Boolean
                var paidCapture : String = ""

                print("Enter a Name for attendee #$count : ")
                name = readLine()!!
                print("Has this Attendee Paid: (Y/N)")
                paidCapture = readLine()!!
                if (paidCapture == "Y" ||paidCapture == "y"){
                    paid = true
                    event.attendees
                }
                else if (paidCapture == "N" || paidCapture == "n"){
                    paid = false
                }
                i++
            }
        }

        return event.title.isNotEmpty() && event.description.isNotEmpty() && event.attendees.isNotEmpty()
    }

    fun updateEventDetails(event: EventModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (event != null) {
            print("Enter a new Title for [ " + event.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + event.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                event.title = tempTitle
                event.description = tempDescription
                return true
            }
        }
        return false
    }

    fun updateEventAttendees(event: EventModel) : Boolean {
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}