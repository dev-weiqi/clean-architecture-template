package dev.weiqi.core.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.weiqi.core.common.AppCoroutineDispatchers
import dev.weiqi.core.common.annotation.CourseDuration
import java.time.Instant
import java.time.ZoneId
import java.util.*
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Provides
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    fun provideZoneId(): ZoneId = ZoneId.systemDefault()

    @Provides
    fun provideCurrentInstant(): Instant = Instant.now()

    @CourseDuration
    @Provides
    fun provideCourseDuration(): Long = 30L
}
