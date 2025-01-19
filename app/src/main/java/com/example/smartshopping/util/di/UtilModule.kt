@Module
@InstallIn(SingletonComponent::class)
object UtilModule {
    
    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManager {
        return NotificationManager(context)
    }

    @Provides
    @Singleton
    fun provideAnalyticsManager(
        firebaseAnalytics: FirebaseAnalytics
    ): AnalyticsManager {
        return AnalyticsManager(firebaseAnalytics)
    }

    @Provides
    @Singleton
    fun provideCryptoManager(
        @ApplicationContext context: Context
    ): CryptoManager {
        return CryptoManager(context)
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor {
        return NetworkMonitor(context)
    }
}