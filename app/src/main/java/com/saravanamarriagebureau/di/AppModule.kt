package com.saravanamarriagebureau.di

import com.saravanamarriagebureau.data.repository.ExternalAppNavigatorImpl
import com.saravanamarriagebureau.util.ExternalAppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindExternalAppNavigator(
        implementation: ExternalAppNavigatorImpl
    ): ExternalAppNavigator
}