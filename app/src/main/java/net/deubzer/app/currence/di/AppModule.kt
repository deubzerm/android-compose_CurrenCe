package net.deubzer.app.currence.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.deubzer.app.currence.data.CurrencyRateRepository
import net.deubzer.app.currence.data.ICurrencyRateRepository
import javax.inject.Singleton



//@Module
//@InstallIn(ViewModelComponent::class)
//object CurrenCeRepositoryModule {
//    @Singleton
//    @Provides
//    fun provideICurrencyRateRepository(
//        @ApplicationContext context: Context
//    ): ICurrencyRateRepository {
//        return CurrencyRateRepository()
//    }
//
//}