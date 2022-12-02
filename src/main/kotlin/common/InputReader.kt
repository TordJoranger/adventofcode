package main.common

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.engine.cio.*
import java.io.File
import java.time.LocalDateTime

suspend fun getInput(sessionCookie: String, day : Int): File {
    val year: Int = LocalDateTime.now().year
    val file = File("src/main/kotlin/common/inputs/$year-$day.txt")
    if(!file.exists())
        file.writeText(downloadFile(day, year, sessionCookie))
    return file
}

private suspend fun downloadFile(day: Int, year: Int, sessionCookie: String) : String {
    HttpClient(CIO).use { client ->
        val response = client.get(getUrl(day, year)) {
            headers {
                append(HttpHeaders.Accept, "text/html")
                append(HttpHeaders.Cookie, "session=$sessionCookie")
            }
        }
        return response.body()
    }
}

private fun getUrl(day: Int, year: Int): String {
    return "https://adventofcode.com/$year/day/$day/input"
}


