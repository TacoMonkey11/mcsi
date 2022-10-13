package com.github.tacomonkey11.mcsi.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption

class MCSICommand  : CliktCommand(
    name = "mcsi",
    help = "A command line interface for installing Minecraft server jars"
) {

    init {
        subcommands(
            InstallCommand()
        )
        versionOption("1.0.0")
    }


    override fun run() {}
}