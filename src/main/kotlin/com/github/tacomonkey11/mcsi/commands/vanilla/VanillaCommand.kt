package com.github.tacomonkey11.mcsi.commands.vanilla

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class VanillaCommand : CliktCommand(name = "vanilla") {

    init {
        subcommands(
            VanillaListCommand(),
            VanillaInstallCommand()
        )
    }

    override fun run() {

    }
}