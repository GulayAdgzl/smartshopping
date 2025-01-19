
@HiltAndroidApplication
class SmartShoppingApp : Application() {
    
    override fun onCreate() {
        super.onCreate()
        setupTimber()
        initializeCrashlytics()
        setupWorkManager()
    }
    
    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    
    private fun initializeCrashlytics() {
        FirebaseCrashlytics.getInstance().apply {
            setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        }
    }
    
    private fun setupWorkManager() {
        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(HiltWorkerFactory())
                .build()
        )
    }
}