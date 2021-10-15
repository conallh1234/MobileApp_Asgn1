package org.wit.eventmngr.console.models

interface EventStore {
    fun findAll(): List<EventModel>
    fun findOne(id: Long): EventModel?
    fun create(event: EventModel)
    fun update(event: EventModel)
    fun delete(event: EventModel)
}