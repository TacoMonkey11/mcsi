package com.github.tacomonkey11.mcsi.commands.server

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.check
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import com.github.tacomonkey11.mcsi.installers.installSpigot
import com.github.tacomonkey11.mcsi.installers.validateSpiotMCVersion
import java.nio.file.Paths

class SpigotCommand : CliktCommand(name = "spigot") {

    private val mcVersion by argument(help = "The Minecraft version to install").check { validateSpiotMCVersion(it) }
    private val installDir by option(help = "The directory to install the server jar to (default: current)").path(mustExist = true, canBeFile = false, canBeDir = true).default(Paths.get("."))
    private val craftBukkit by option(help = "Build CraftBukkit instead of Spigot").flag()

    override fun run() = installSpigot(mcVersion, installDir, craftBukkit)
}