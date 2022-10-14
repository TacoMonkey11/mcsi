package com.github.tacomonkey11.mcsi.commands.burritoSpigot

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import okhttp3.OkHttpClient
import java.io.FileOutputStream
import java.nio.file.Path

class BurritoSpigotInstallCommand : CliktCommand(name = "install", help = "Install a BurritoSpigot server, only able to grab latest due to me not wanting to due more work") {
    private val client = OkHttpClient()

    private val installDir by option(help = "The directory to install the server to").path(mustExist = true, canBeFile = false, canBeDir = true).default(Path.of("."))

    override fun run() {
        client.newCall(okhttp3.Request.Builder().url("https://github.com/CobbleSword/BurritoSpigot/blob/downloads/jars/BurritoSpigot.jar?raw=true").build()).execute().body!!.byteStream().use { input ->
            input.copyTo(FileOutputStream(installDir.resolve("BurritoSpigot.jar").toFile()))
            }
    }
}