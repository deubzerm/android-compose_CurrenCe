package net.deubzer.app.currence

import net.deubzer.app.currence.util.CurrencyEnum
import net.deubzer.app.currence.util.calculateCurrencies
import org.junit.Assert
import org.junit.Test

class CurrencyConvertUnitTest {

    @Test
    fun testCurrencyConvert(){
        Assert.assertEquals(
            calculateCurrencies(2f, Pair(CurrencyEnum.EUR,CurrencyEnum.LEW)).first, 2f)
        Assert.assertEquals(
            calculateCurrencies(2f, Pair(CurrencyEnum.EUR,CurrencyEnum.LEW)).second, 3.92f)
        Assert.assertEquals(
            calculateCurrencies(-2f, Pair(CurrencyEnum.EUR,CurrencyEnum.LEW)).first, -2f)
        Assert.assertEquals(
            calculateCurrencies(4F, Pair(CurrencyEnum.EUR,CurrencyEnum.EUR)).second, 4f)

    }
}