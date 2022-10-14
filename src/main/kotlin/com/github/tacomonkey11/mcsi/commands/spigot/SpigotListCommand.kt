package com.github.tacomonkey11.mcsi.commands.spigot

import com.github.ajalt.clikt.core.CliktCommand
import okhttp3.OkHttpClient
import okhttp3.Request

class SpigotListCommand : CliktCommand(name = "list") {
    private val client = OkHttpClient()
    override fun run() {

        val reg = "<a href=\"([0-9]\\.[0-9]\\.?[0-9]?\\.json)".toRegex()

        val response = client.newCall(Request.Builder().url("https://hub.spigotmc.org/versions/").build()).execute()
        val versions = reg.findAll(response.body!!.string()).map { it.groupValues[1] }
        versions.toSet().forEach {
            println(it.replace(".json", ""))
        }
    }
}