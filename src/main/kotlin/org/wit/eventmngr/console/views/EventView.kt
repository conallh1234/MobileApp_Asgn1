package org.wit.eventmngr.console.views

class EventView {


    fun menu() : Int {
        var option : Int
        var input : String?

        println("EVENT MANAGER SYSTEM - MAIN MENU")
        println(" 1. Add An Event")
        println(" 2. Update an Event's Details")
        println(" 3. List All Stored Events")
        println(" 4. Search Stored Events")
        println(" 5. Delete An Event")
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

}