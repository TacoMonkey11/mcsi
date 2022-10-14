package com.github.tacomonkey11.mcsi.commands.vanilla

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.check
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import com.github.tacomonkey11.mcsi.installers.installVanilla
import com.github.tacomonkey11.mcsi.installers.vanillaReleases
import java.nio.file.Path

class VanillaInstallCommand : CliktCommand(name = "install") {
    private val mcVersion by argument(help = "The version of Minecraft to install").check { it in vanillaReleases }
    private val installDir by option(help = "The directory to install the server to").path(mustExist = true, canBeFile = false, canBeDir = true).default(
        Path.of("."))

    override fun run() {
        installVanilla(mcVersion, installDir)
    }
}