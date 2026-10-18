package com.saravanamarriagebureau.di

import javax.inject.Qualifier

/**
 * Qualifier for the SmbApi/OkHttpClient used ONLY to refresh the access token.
 * This instance must not carry the AuthInterceptor or the TokenAuthenticator,
 * otherwise a 401 on the refresh endpoint would recurse.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RefreshApi
