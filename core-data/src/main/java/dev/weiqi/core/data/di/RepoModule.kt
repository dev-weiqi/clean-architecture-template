package dev.weiqi.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.weiqi.core.data.repo.ScheduleRepo
import dev.weiqi.core.data.repo.ScheduleRepoImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepoModuleModule {

    @Binds
    fun bindsScheduleRepo(impl: ScheduleRepoImpl): ScheduleRepo
}