class PriceCheckWorkerFactory @Inject constructor(
    private val repository: ProductRepository,
    private val notificationManager: NotificationManager
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            PriceCheckWorker::class.java.name ->
                PriceCheckWorker(appContext, workerParameters, repository, notificationManager)
            else -> null
        }
    }
}