package com.github.tacomonkey11.mcsi.installers

import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess

private val client = OkHttpClient()
private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}
val paperSupportedMCVersions: List<String> = json.parseToJsonElement(
    client.newCall(Request.Builder().url("https://api.papermc.io/v2/projects/paper/").build()).execute().body?.string()!!)
    .jsonObject["versions"]!!
    .jsonArray.map { it.jsonPrimitive.content }

fun getLatestPaperBuild(mcVersion: String): String {
    //check if mcVersion is valid
    if (!paperSupportedMCVersions.contains(mcVersion)) {
        println("Invalid Minecraft version: $mcVersion")
        exitProcess(1)
    }
    return json.parseToJsonElement(
        client.newCall(Request.Builder().url("https://papermc.io/api/v2/projects/paper/versions/$mcVersion").build()).execute().body?.string()!!)
        .jsonObject["builds"]!!
        .jsonArray.last().jsonPrimitive.content
}

fun verifyPaperBuild(mcVersion: String, build: String): Boolean {
    return json.parseToJsonElement(
        client.newCall(Request.Builder().url("https://papermc.io/api/v2/projects/paper/versions/$mcVersion").build()).execute().body?.string()!!)
        .jsonObject["builds"]!!
        .jsonArray.map { it.jsonPrimitive.content }.contains(build)
}

fun installPaper(mcVersion: String, build: String, installDir: Path) {
    if (verifyPaperBuild(mcVersion, build)) {
        val paperJarRequest = Request.Builder()
            .url("https://papermc.io/api/v2/projects/paper/versions/$mcVersion/builds/$build/downloads/paper-$mcVersion-$build.jar")
            .build()
        val paperJarResponse = client.newCall(paperJarRequest).execute()
        val paperJar = paperJarResponse.body?.byteStream()
        Files.copy(paperJar, installDir.resolve("paper-$mcVersion-$build.jar"))
    } else {
        println("Invalid build number")
        exitProcess(1)
    }
}