package com.example.terminus

import java.io.File
import java.lang.Exception

class CreateWordJSON {
    fun CreateWord() {
        File("newWords").createNewFile()
        val newwords = File("newWords")
        try {
            val fileName = "words.json"
            var line: List<String> = File(fileName).readLines()
            line.forEach {
                if (line[0].length >= 5) {
                        newwords.writeText(line[0])
                }
            }
        }catch (e:Exception ){
            e.printStackTrace()
        }
    }
}