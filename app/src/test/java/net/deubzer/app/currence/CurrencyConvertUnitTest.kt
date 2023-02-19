package net.deubzer.app.currence

import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.util.CurrencyEnum
import net.deubzer.app.currence.util.calculateCurrencies
import org.junit.Assert
import org.junit.Test

class CurrencyConvertUnitTest {
  @Test
  fun testCurrencyConvert() {
    val raterepo = CurrencyRateRepository()

    Assert.assertEquals(
        calculateCurrencies(raterepo, 2f, Pair(CurrencyEnum.EUR, CurrencyEnum.LEW)).first,
        2f,
    )
    Assert.assertEquals(
        calculateCurrencies(raterepo, 2f, Pair(CurrencyEnum.EUR, CurrencyEnum.LEW)).second,
        3.92f,
    )
    Assert.assertEquals(
        calculateCurrencies(raterepo, -2f, Pair(CurrencyEnum.EUR, CurrencyEnum.LEW)).first,
        -2f,
    )
    Assert.assertEquals(
        calculateCurrencies(raterepo, 4F, Pair(CurrencyEnum.EUR, CurrencyEnum.EUR)).second,
        4f,
    )
  }

  @Test
  fun testRateRepository() {
    val raterepo = CurrencyRateRepository()

    Assert.assertEquals(
        raterepo.getExchangeRate(Pair(CurrencyEnum.LEW, CurrencyEnum.EUR)),
        0.51f,
    )

    raterepo.addExchangeRate(Pair(CurrencyEnum.NIS, CurrencyEnum.LEW), 5f)
    Assert.assertEquals(
        raterepo.getExchangeRate(Pair(CurrencyEnum.NIS, CurrencyEnum.LEW)),
        5f,
    )

    raterepo.addExchangeRate(Pair(CurrencyEnum.NIS, CurrencyEnum.LEW), 6f)
    Assert.assertEquals(
        raterepo.getExchangeRate(Pair(CurrencyEnum.NIS, CurrencyEnum.LEW)),
        6f,
    )

    raterepo.updateExchangeRate(Pair(CurrencyEnum.NIS, CurrencyEnum.LEW), 5f)
    Assert.assertEquals(
        raterepo.getExchangeRate(Pair(CurrencyEnum.NIS, CurrencyEnum.LEW)),
        5f,
    )
  }
}
