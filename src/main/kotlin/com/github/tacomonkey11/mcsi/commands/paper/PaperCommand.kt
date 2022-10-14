package com.github.tacomonkey11.mcsi.commands.paper

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class PaperCommand : CliktCommand(name = "paper") {
    init {
        subcommands(
            PaperInstallCommand(),
            PaperListCommand()
        )
    }

    override fun run() {}
}
