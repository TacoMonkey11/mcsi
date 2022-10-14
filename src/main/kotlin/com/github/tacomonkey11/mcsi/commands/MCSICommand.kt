package com.github.tacomonkey11.mcsi.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.tacomonkey11.mcsi.commands.paper.PaperCommand
import com.github.tacomonkey11.mcsi.commands.spigot.SpigotCommand

class MCSICommand  : CliktCommand(
    name = "mcsi",
    help = "A command line interface for installing Minecraft server jars"
) {
    init {
        subcommands(
            SpigotCommand(),
            PaperCommand()
        )
        versionOption("1.0.0")
    }
    override fun run() {}
}