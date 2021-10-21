package org.wit.eventmngr.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.eventmngr.console.controllers.EventUIController
import tornadofx.*


class MenuScreen : View("Event Main Menu") {

    val eventUIController: EventUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Event") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        eventUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Events") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        eventUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }
}