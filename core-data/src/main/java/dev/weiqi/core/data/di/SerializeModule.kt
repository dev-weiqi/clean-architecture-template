package dev.weiqi.core.data.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.weiqi.core.data.seriallize.InstantTypeAdapter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SerializeModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(InstantTypeAdapter())
            .build()
    }
}
