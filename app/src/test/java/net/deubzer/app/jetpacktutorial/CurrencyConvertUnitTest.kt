package net.deubzer.app.jetpacktutorial

import net.deubzer.app.jetpacktutorial.util.calcEur
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException

class CurrencyConvertUnitTest {

    @Test
    fun testCurrencyConvert(){
        Assert.assertEquals(calcEur("2"), "1.02")
        Assert.assertEquals(calcEur("-2"), "-1.02")
        try {
            calcEur("")
        } catch (i : IllegalArgumentException) {
            Assert.assertEquals(i.message, "komisch")
        }
    }
}