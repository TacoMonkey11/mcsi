package com.github.tacomonkey11.mcsi.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.tacomonkey11.mcsi.commands.server.PaperCommand
import com.github.tacomonkey11.mcsi.commands.server.SpigotCommand

class InstallCommand : CliktCommand(name = "install") {
    init {
        subcommands(
            SpigotCommand(),
            PaperCommand()
        )
    }

    override fun run() {

    }
}