package com.github.tacomonkey11.mcsi.commands.burritoSpigot

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class BurritoSpigotCommand : CliktCommand(name = "burrito-spigot") {

    init {
        subcommands(
            BurritoSpigotInstallCommand()
        )
    }

    override fun run() {

    }
}