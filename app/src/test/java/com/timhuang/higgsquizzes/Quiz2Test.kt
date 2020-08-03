package com.timhuang.higgsquizzes

import org.hamcrest.CoreMatchers.`is`
import org.junit.Test

import org.junit.Assert.*

class Quiz2Test {

    //in roman 4 more like 5-1 (1-5) , 9 more like 10-1 (1-10)
    //so before 3000 should have cases like
    //4 IV, 9 IX, 90 XC, 99 XCIX, 1000

    //4 IV, and 1,2,3
    @Test
    fun roman2Chinese_lessThanFive() {

        val t1 = roman2Chinese("IV")
        val t2 = roman2Chinese("I")
        val t3 = roman2Chinese("II")
        val t4 = roman2Chinese("III")
        assertThat(t1,`is`("四"))
        assertThat(t2,`is`("一"))
        assertThat(t3,`is`("二"))
        assertThat(t4,`is`("三"))
    }

    @Test
    fun roman2Chinese_lessThanFifteen() {

        val t1 = roman2Chinese("VIII")
        val t2 = roman2Chinese("IX")
        val t3 = roman2Chinese("X")
        val t4 = roman2Chinese("XIV")
        assertThat(t1,`is`("八"))
        assertThat(t2,`is`("九"))
        assertThat(t3,`is`("十"))
        assertThat(t4,`is`("十四"))
    }

    @Test
    fun roman2Chinese_lessThanHundred() {

        val t1 = roman2Chinese("LIX")
        val t2 = roman2Chinese("LXXIV")
        val t3 = roman2Chinese("LXXXIX")
        val t4 = roman2Chinese("XCIX")
        assertThat(t1,`is`("五十九"))
        assertThat(t2,`is`("七十四"))
        assertThat(t3,`is`("八十九"))
        assertThat(t4,`is`("九十九"))
    }

    @Test
    fun roman2Chinese_lessThanThousand() {

        val t1 = roman2Chinese("CMXCIX")
        val t2 = roman2Chinese("CDXCIX")
        val t3 = roman2Chinese("DXXI")
        val t4 = roman2Chinese("CCCXCIV")
        val t5 = roman2Chinese("CLIX")
        assertThat(t1,`is`("九百九十九"))
        assertThat(t2,`is`("四百九十九"))
        assertThat(t3,`is`("五百二十一"))
        assertThat(t4,`is`("三百九十四"))
        assertThat(t5,`is`("一百五十九"))
    }

    @Test
    fun roman2Chinese_lessThanThreeThousand() {

        val t1 = roman2Chinese("MCM")
        val t2 = roman2Chinese("MDXXI")
        val t3 = roman2Chinese("MMCMXCIX")
        val t4 = roman2Chinese("MMM")
        assertThat(t1,`is`("一千九百"))
        assertThat(t2,`is`("一千五百二十一"))
        assertThat(t3,`is`("二千九百九十九"))
        assertThat(t4,`is`("三千"))
    }


}