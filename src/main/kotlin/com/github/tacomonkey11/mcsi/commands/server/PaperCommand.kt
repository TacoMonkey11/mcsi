package com.github.tacomonkey11.mcsi.commands.server

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.*
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.path
import com.github.tacomonkey11.mcsi.installers.*
import java.nio.file.Paths

class PaperCommand : CliktCommand(name = "paper") {

    private val mcVersion by argument(help = "The Minecraft version to install").check { paperSupportedMCVersions.contains(it) }
    //check if build is valid and if not, use default
    private val build by argument(help = "The build number to install (default: latest)").defaultLazy{getLatestPaperBuild(mcVersion)}
    private val installDir by option(help = "The directory to install the server jar to (default: current)").path(mustExist = true, canBeFile = false, canBeDir = true).default(Paths.get("."))

    override fun run() = installPaper(mcVersion, build, installDir)
}
