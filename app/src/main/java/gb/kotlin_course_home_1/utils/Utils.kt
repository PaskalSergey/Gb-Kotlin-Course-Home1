package gb.kotlin_course_home_1.utils

import java.io.BufferedReader
import java.util.stream.Collectors

class Utils {
}

fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}