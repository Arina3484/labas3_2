import kotlin.math.min

fun main() {
    // Функция для генерации таблицы Виженера
    fun generateVigenereTable(): Array<CharArray> {
        val table = Array(26) { CharArray(26) }
        for (i in 0 until 26) {
            for (j in 0 until 26) {
                table[i][j] = ((i + j) % 26 + 'A'.toInt()).toChar()
            }
        }
        return table
    }

    // Функция для шифрования и расшифрования текста методом Виженера
    fun vigenereCipher(text: String, key: String, encrypt: Boolean, table: Array<CharArray>): String {
        val result = StringBuilder()
        val keyRepeated = buildString {
            while (length < text.length) {
                append(key)
            }
        }

        for (i in text.indices) {
            val row = text[i].toUpperCase() - 'A'
            val col = keyRepeated[i].toUpperCase() - 'A'

            if (text[i].isLetter()) {
                val charIndex = if (encrypt) (row + col) % 26 else (row - col + 26) % 26
                result.append(table[row][charIndex])
            } else {
                result.append(text[i])
            }
        }

        return result.toString()
    }

    // Ввод сообщения, ключа и выбор использования типовой таблицы или генерации случайной
    println("Введите исходное сообщение:")
    val message = readLine()?.toUpperCase() ?: ""

    println("Введите ключ:")
    val key = readLine()?.toUpperCase() ?: ""

    println("Выберите опцию для таблицы Виженера:")
    println("1. Использовать типовую таблицу")
    println("2. Генерировать случайную таблицу")

    val choice = readLine()

    val vigenereTable: Array<CharArray> = when (choice) {
        "2" -> generateVigenereTable()
        else -> Array(0) { CharArray(0) } // Типовая таблица может быть предоставлена заранее
    }

    // Шифрование и расшифрование
    val encryptedText = vigenereCipher(message, key, true, vigenereTable)
    val decryptedText = vigenereCipher(encryptedText, key, false, vigenereTable)

    // Вывод результатов
    println("Зашифрованный текст: $encryptedText")
    println("Расшифрованный текст: $decryptedText")
}
