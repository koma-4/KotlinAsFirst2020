@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow


// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.map { it * it }.sum())

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.isNotEmpty()) list.sum() / list.size
    else 0.0
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val am = list.sum() / list.size
        for (i in 0 until list.size) {
            val element = list[i]
            list[i] = element - am
        }
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    val result = mutableListOf<Int>()
    for (element1 in a) {
        for (element2 in b) {
            if (a.indexOf(element1) == b.indexOf(element2)) result.add(element1 * element2)
        }
    }
    return result.sum()
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    val result = mutableListOf<Int>()
    if (p.isEmpty()) return 0
    else {
        for (element in p) {
            result.add(element * (x.toDouble()).pow(p.indexOf(element)).toInt())
        }
    }
    return result.sum()
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isNotEmpty()) {
        var s = 0
        for (i in 0 until list.size) {
            val element = list[i]
            list[i] = element + s
            s = list[i]
        }
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var x = 2
    var number = n
    if (number == 1) result.add(number)
    else while (number > 1) {
        if (number % x == 0) {
            result.add(x)
            number /= x
        } else x += 1
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val result = mutableListOf<Int>()
    while (number >= base) {
        result.add(number % base)
        number /= base
    }
    if (number < base) result.add(number)
    if (result.size == 1) return result
    else for (i in 0 until result.size / 2) {
        val element = result[i]
        result[i] = result[result.size - 1 - i]
        result[result.size - 1 - i] = element
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val result = convert(n, base)
    val result1 = mutableListOf<String>()
    for (element in result) {
        when (element) {
            10 -> result1.add("a")
            11 -> result1.add("b")
            12 -> result1.add("c")
            13 -> result1.add("d")
            14 -> result1.add("e")
            15 -> result1.add("f")
            16 -> result1.add("g")
            17 -> result1.add("h")
            18 -> result1.add("i")
            19 -> result1.add("j")
            20 -> result1.add("k")
            21 -> result1.add("l")
            22 -> result1.add("m")
            23 -> result1.add("n")
            24 -> result1.add("o")
            25 -> result1.add("p")
            26 -> result1.add("q")
            27 -> result1.add("r")
            28 -> result1.add("s")
            29 -> result1.add("t")
            30 -> result1.add("u")
            31 -> result1.add("v")
            32 -> result1.add("w")
            33 -> result1.add("x")
            34 -> result1.add("y")
            35 -> result1.add("z")
            else -> result1.add("$element")
        }
    }
    return result1.joinToString("")
}


/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val result = mutableListOf<Int>()
    if (digits.isNotEmpty()) {
        for (i in 0 until digits.size)
            result.add(digits[i] * base.toDouble().pow(digits.size - 1 - i).toInt())
    }
    return result.sum()
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val result = mutableListOf<Int>()
    for (char in str) {
        when (char) {
            'a' -> result.add(10)
            'b' -> result.add(11)
            'c' -> result.add(12)
            'd' -> result.add(13)
            'e' -> result.add(14)
            'f' -> result.add(15)
            'g' -> result.add(16)
            'h' -> result.add(17)
            'i' -> result.add(18)
            'j' -> result.add(19)
            'k' -> result.add(20)
            'l' -> result.add(21)
            'm' -> result.add(22)
            'n' -> result.add(23)
            'o' -> result.add(24)
            'p' -> result.add(25)
            'q' -> result.add(26)
            'r' -> result.add(27)
            's' -> result.add(28)
            't' -> result.add(29)
            'u' -> result.add(30)
            'v' -> result.add(31)
            'w' -> result.add(32)
            'x' -> result.add(33)
            'y' -> result.add(34)
            'z' -> result.add(35)
            else -> result.add(Character.getNumericValue(char))
        }
    }
    val result1 = result.toList()
    return decimal(result1, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val result = mutableListOf<String>()
    var number = n
    while (number > 999) {
        result.add("M")
        number -= 1000
    }
    while (number > 899) {
        result.add("CM")
        number -= 900
    }
    while (number > 499) {
        result.add("D")
        number -= 500
    }
    while (number > 399) {
        result.add("CD")
        number -= 400
    }
    while (number > 99) {
        result.add("C")
        number -= 100
    }
    while (number > 89) {
        result.add("XC")
        number -= 90
    }
    while (number > 49) {
        result.add("L")
        number -= 50
    }
    while (number > 39) {
        result.add("XL")
        number -= 40
    }
    while (number > 9) {
        result.add("X")
        number -= 10
    }
    while (number > 8) {
        result.add("IX")
        number -= 9
    }
    while (number > 4) {
        result.add("V")
        number -= 5
    }
    while (number > 3) {
        result.add("IV")
        number -= 4
    }
    while (number > 0) {
        result.add("I")
        number -= 1
    }
    return result.joinToString("")
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val result = mutableListOf<String>()
    val number = mutableListOf<Int>()
    var number1 = n
    while (number1 > 0) {
        number.add(number1 % 10)
        number1 /= 10
    }
    while (number.isNotEmpty()) {
        when (number.size) {
            1 -> {
                when (number[number.size - 1]) {
                    1 -> result.add("один")
                    2 -> result.add("два")
                    3 -> result.add("три")
                    4 -> result.add("четыре")
                    5 -> result.add("пять")
                    6 -> result.add("шесть")
                    7 -> result.add("семь")
                    8 -> result.add("восемь")
                    9 -> result.add("девять")
                }
                number.removeAt(number.size - 1)
            }
            2, 5 -> {
                if (number[number.size - 1] == 1) {
                    when (number[number.size - 2]) {
                        0 -> result.add("десять")
                        1 -> result.add("одиннадцать")
                        2 -> result.add("двенадцать")
                        3 -> result.add("тринадцать")
                        4 -> result.add("четырнадцать")
                        5 -> result.add("пятнадцать")
                        6 -> result.add("шестнадцать")
                        7 -> result.add("семнадцать")
                        8 -> result.add("восемнадцать")
                        9 -> result.add("девятнадцать")
                    }
                    number.removeAt(number.size - 1)
                    number.removeAt(number.size - 1)
                    if (number.size == 3) result.add("тысяч")
                } else {
                    when (number[number.size - 1]) {
                        2 -> result.add("двадцать")
                        3 -> result.add("тридцать")
                        4 -> result.add("сорок")
                        5 -> result.add("пятьдесят")
                        6 -> result.add("шестьдесят")
                        7 -> result.add("семьдесят")
                        8 -> result.add("восемьдесят")
                        9 -> result.add("девяносто")
                    }
                    number.removeAt(number.size - 1)
                }
            }
            3, 6 -> {
                when (number[number.size - 1]) {
                    1 -> result.add("сто")
                    2 -> result.add("двести")
                    3 -> result.add("триста")
                    4 -> result.add("четыреста")
                    5 -> result.add("пятьсот")
                    6 -> result.add("шестьсот")
                    7 -> result.add("семьсот")
                    8 -> result.add("восемьсот")
                    9 -> result.add("девятьсот")
                }
                number.removeAt(number.size - 1)
            }
            4 -> {
                when (number[number.size - 1]) {
                    0 -> result.add("тысяч")
                    1 -> result.add("одна тысяча")
                    2 -> result.add("две тысячи")
                    3 -> result.add("три тысячи")
                    4 -> result.add("четыре тысячи")
                    5 -> result.add("пять тысяч")
                    6 -> result.add("шесть тысяч")
                    7 -> result.add("семь тысяч")
                    8 -> result.add("восемь тысяч")
                    9 -> result.add("девять тысяч")
                }
                number.removeAt(number.size - 1)
            }
        }
    }
    return result.joinToString(" ")
}
