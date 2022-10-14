package com.github.tacomonkey11.mcsi.commands.paper

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.check
import com.github.ajalt.clikt.parameters.arguments.defaultLazy
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import com.github.tacomonkey11.mcsi.installers.getLatestPaperBuild
import com.github.tacomonkey11.mcsi.installers.installPaper
import com.github.tacomonkey11.mcsi.installers.paperSupportedMCVersions
import java.nio.file.Paths

class PaperInstallCommand : CliktCommand(name = "install") {

    private val mcVersion by argument(help = "The Minecraft version to install").check { paperSupportedMCVersions.contains(it) }
    private val build by argument(help = "The build number to install (default: latest)").defaultLazy{ getLatestPaperBuild(mcVersion) }
    private val installDir by option(help = "The directory to install the server jar to (default: current)").path(mustExist = true, canBeFile = false, canBeDir = true).default(Paths.get("."))

    override fun run() = installPaper(mcVersion, build, installDir)
}