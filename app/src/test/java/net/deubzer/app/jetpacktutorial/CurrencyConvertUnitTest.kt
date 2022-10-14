package net.deubzer.app.jetpacktutorial

import net.deubzer.app.jetpacktutorial.util.CurrencyEnum
import net.deubzer.app.jetpacktutorial.util.calcEur
import org.junit.Assert
import org.junit.Test

class CurrencyConvertUnitTest {

    @Test
    fun testCurrencyConvert(){
        Assert.assertEquals(calcEur("2", CurrencyEnum.EUR), "1.02")
        Assert.assertEquals(calcEur("-2", CurrencyEnum.EUR), "-1.02")
    }

    @Test
    fun testCurrencyConvertMisuse(){
        Assert.assertThrows(java.lang.NumberFormatException::class.java){
            calcEur("", CurrencyEnum.EUR)
        }
    }
}