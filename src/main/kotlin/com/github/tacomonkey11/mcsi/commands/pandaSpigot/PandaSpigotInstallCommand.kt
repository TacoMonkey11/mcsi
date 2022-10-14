package com.github.tacomonkey11.mcsi.commands.pandaSpigot

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import okhttp3.OkHttpClient
import java.io.FileOutputStream
import java.nio.file.Path
import java.util.zip.ZipFile

class PandaSpigotInstallCommand : CliktCommand(name = "install", help = "Install a PandaSpigot server, only able to grab latest due to github api limitations") {
    private val client = OkHttpClient()

    private val installDir by option(help = "The directory to install the server to").path(mustExist = true, canBeFile = false, canBeDir = true).default(Path.of("."))

    override fun run() {
        client.newCall(okhttp3.Request.Builder().url("https://nightly.link/hpfxd/PandaSpigot/workflows/build/master/Server%20JAR.zip").build()).execute().body!!.byteStream().use { input ->
            input.copyTo(FileOutputStream(installDir.resolve("PandaSpigot.zip").toFile()))
        }
        // Unzipping the file because the jar is stored inside of an archive fr some reason
        ZipFile(installDir.resolve("PandaSpigot.zip").toFile()).use { zip ->
            zip.entries().asSequence().forEach { entry ->
                zip.getInputStream(entry).use { input ->
                    input.copyTo(FileOutputStream(installDir.resolve(entry.name).toFile()))
                }
            }
        }
        installDir.resolve("PandaSpigot.zip").toFile().delete()
    }
}