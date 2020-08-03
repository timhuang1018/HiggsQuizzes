package com.timhuang.higgsquizzes

/**
 * Created by timhuang on 2020/8/3.
 * Exchange roman number to chinese number
 * for example:
 * f("IX") = "九“
 * f("CCCXCIV") = "三百九十四“
 * f("MCM") = "一千九百“
 * This should work for all numbers up to 3000.
 **/

fun roman2Chinese(romanString:String):String{
    val number = roman2Number(romanString)
    return number2Chinese(number)
}


fun roman2Number(romanString:String):Int{
    val romanMap = linkedMapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    var number = 0
    var count =0

    while (count<romanString.length){
        val current = romanString[count]
        val curNumber = romanMap[current]!!
        //need to compare to next char if not the last char
        if (count+1<romanString.length){
            val next = romanString[count+1]
            val nextNumber = romanMap[next]!!
            //bigger is normal exchange
            //if not bigger , means one curNumber less than nextNumber
            if (curNumber>=nextNumber){
                number += curNumber
            }else{
                number += (nextNumber - curNumber)
                count++
            }
        }else{
            number += curNumber
        }
        count++
    }
    return number
}


fun number2Chinese(number:Int):String{
    //store number to input make it can be reduce
    //exchange each from thousand , hundreds, tens , digit
    var input = number
    val chineseMap = hashMapOf<Int,String>(
        1 to "一",
        2 to "二",
        3 to "三",
        4 to "四",
        5 to "五",
        6 to "六",
        7 to "七",
        8 to "八",
        9 to "九",
        0 to "零"
    )

    var chinese = ""

    val thousands = input / 1000
    if (thousands>0){
        chinese += "${chineseMap[thousands]}千"
    }
    input -= thousands * 1000

    val hundreds = input / 100
    if (hundreds>0){
        chinese += "${chineseMap[hundreds]}百"
    }
    input -= hundreds * 100

    val tens = input / 10
    if (tens>1){
        chinese += "${chineseMap[tens]}"
    }
    if (tens>0){
        chinese += "十"
    }
    val digits = input % 10
    if (digits!=0){
        chinese += chineseMap[digits]
    }

    return chinese
}
