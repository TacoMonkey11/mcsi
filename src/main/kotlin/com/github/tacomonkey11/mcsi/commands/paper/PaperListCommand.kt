package com.github.tacomonkey11.mcsi.commands.paper

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.option
import com.github.tacomonkey11.mcsi.installers.paperSupportedMCVersions
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.OkHttpClient
import okhttp3.Request

class PaperListCommand : CliktCommand(name = "list") {
    private val client = OkHttpClient()
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }
    private val mcVersion by option("-v", "--version", help = "The Minecraft version to list builds for").check { paperSupportedMCVersions.contains(it) }

    override fun run() {
        if (mcVersion != null) {
            val versions = json.parseToJsonElement(client.newCall(Request.Builder().url("https://api.papermc.io/v2/projects/paper/versions/$mcVersion").build()).execute().body!!.string()).jsonObject["builds"]!!.jsonArray.map { it.jsonPrimitive.content }
            versions.forEach { println(it) }
        } else {
            val versions = json.parseToJsonElement(client.newCall(Request.Builder().url("https://api.papermc.io/v2/projects/paper").build()).execute().body!!.string()).jsonObject["versions"]!!.jsonArray.map { it.jsonPrimitive.content }
            versions.forEach { println(it) }
        }
    }
}