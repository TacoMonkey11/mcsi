package com.github.tacomonkey11.mcsi.commands.vanilla

import com.github.ajalt.clikt.core.CliktCommand
import com.github.tacomonkey11.mcsi.installers.vanillaReleases

class VanillaListCommand : CliktCommand(name = "list") {
    override fun run() {
        vanillaReleases.forEach { println(it) }
        println("**Some versions may not provide a server jar**")
    }
}