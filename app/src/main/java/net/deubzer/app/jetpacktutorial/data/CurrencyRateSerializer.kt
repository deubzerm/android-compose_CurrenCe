package net.deubzer.app.jetpacktutorial.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import net.deubzer.app.jetpacktutorial.datastore.Currency
import net.deubzer.app.jetpacktutorial.datastore.CurrencyRate
import net.deubzer.app.jetpacktutorial.datastore.Exchange
import java.io.InputStream
import java.io.OutputStream

object CurrencyRateSerializer : Serializer<CurrencyRate> {

    override suspend fun readFrom(input: InputStream): CurrencyRate {
        try {
            return CurrencyRate.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read CurrencyRateProto", exception)
        }
    }

    override suspend fun writeTo(currencyRate: CurrencyRate, output: OutputStream) {
        currencyRate.writeTo(output)
    }

    override val defaultValue: CurrencyRate
        get() = CurrencyRate.getDefaultInstance()
}

object ExchangeSerializer: Serializer<Exchange> {
    override val defaultValue: Exchange
        get() = Exchange.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Exchange {
        try {
            return Exchange.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read ExchangeProto", exception)
        }
    }

    override suspend fun writeTo(t: Exchange, output: OutputStream) {
        t.writeTo(output)
    }
}