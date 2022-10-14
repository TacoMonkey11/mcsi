package com.github.tacomonkey11.mcsi.installers

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.OkHttpClient
import okhttp3.Request
import java.nio.file.Files
import java.nio.file.Path

private val client = OkHttpClient()
private val json = Json { ignoreUnknownKeys = true }

val vanillaReleases by lazy {
    val versions = mutableListOf<String>()
    json.parseToJsonElement(
        client.newCall(Request.Builder().url("https://launchermeta.mojang.com/mc/game/version_manifest_v2.json").build()).execute().body?.string()!!
    ).jsonObject["versions"]!!.jsonArray.map {
        if (it.jsonObject["type"]!!.jsonPrimitive.content == "release" || it.jsonObject["type"]!!.jsonPrimitive.content == "snapshot") { versions.add(it.jsonObject["id"]!!.jsonPrimitive.content) }
    }
    versions.removeAll(listOf("1.0", "1.1", "1.2.1", "1.2.2", "1.2.3", "1.2.4"))
    versions
}
fun installVanilla(mcVersion: String, installDir: Path) {
    val response = client.newCall(Request.Builder().url("https://launchermeta.mojang.com/mc/game/version_manifest_v2.json").build()).execute()
    val url = json.parseToJsonElement(response.body?.string()!!)
        .jsonObject["versions"]!!
        .jsonArray.first { it.jsonObject["id"]!!.jsonPrimitive.content == mcVersion }
        .jsonObject["url"]!!
        .jsonPrimitive.content
    val downloadUrl = json.parseToJsonElement(client.newCall(Request.Builder().url(url).build()).execute().body?.string()!!)
        .jsonObject["downloads"]!!
        .jsonObject["server"]!!
        .jsonObject["url"]!!
        .jsonPrimitive.content
    val serverJar = client.newCall(Request.Builder().url(downloadUrl).build()).execute().body?.byteStream()
    Files.copy(serverJar, installDir.resolve("server.jar"))
}