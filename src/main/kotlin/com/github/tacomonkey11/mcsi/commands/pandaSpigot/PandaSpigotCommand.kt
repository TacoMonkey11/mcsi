package com.github.tacomonkey11.mcsi.commands.pandaSpigot

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class PandaSpigotCommand : CliktCommand(name = "panda-spigot") {

    init {
        subcommands(
            PandaSpigotInstallCommand()
        )
    }

    override fun run() {

    }
}