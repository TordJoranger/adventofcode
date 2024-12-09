package common

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.engine.cio.*
import java.io.File
import java.time.LocalDateTime

suspend fun getTestInput(day : Int): File {
    val year: Int = LocalDateTime.now().year
    val file = File("src/test/kotlin/aoc$year/inputs/test-$day.txt")
    if(!file.exists()) {

        val text = tryGetTestInput(day, year)
        if(text!= null)
            file.writeText(text)
    }
    return file
}

private suspend fun tryGetTestInput(day: Int, year: Int) : String? {
    HttpClient(CIO).use { client ->
        val response = client.get(getTestUrl(day, year)) {
            headers {
                append(HttpHeaders.Accept, "text/html")
            }
        }
        val body = response.body<String>()
        val regex = Regex("<pre><code>(.*?)</code></pre>", RegexOption.DOT_MATCHES_ALL)
        val matchResult = regex.find(body)

        // Extract the matched group (text inside the tags)
        val extractedText = matchResult?.groups?.get(1)?.value
        return extractedText
    }
}


private fun getTestUrl(day: Int, year: Int): String {
    return "https://adventofcode.com/$year/day/$day"
}