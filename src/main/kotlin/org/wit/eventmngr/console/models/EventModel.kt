package org.wit.eventmngr.console.models

import java.util.ArrayList

data class EventModel(var id : Long? = 0,
                      var title : String = "",
                      var description : String = "",
                      var attendees : ArrayList<AttendeeModel>  )