package com.github.tacomonkey11.mcsi.commands.spigot

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class SpigotCommand : CliktCommand(name = "spigot") {
    init {
        subcommands(
            SpigotInstallCommand(),
            SpigotListCommand()
        )
    }
    override fun run() {}
}