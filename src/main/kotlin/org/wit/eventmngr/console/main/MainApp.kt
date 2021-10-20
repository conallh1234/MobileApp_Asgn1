package org.wit.eventmngr.console.main

import org.wit.eventmngr.console.views.MenuScreen
import tornadofx.App
import tornadofx.launch

class MainApp : App(MenuScreen::class){
    fun main(args: Array<String>){
        launch<MainApp>(args)
    }
}