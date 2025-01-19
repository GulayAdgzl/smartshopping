@Singleton
class NotificationManager @Inject constructor(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    init {
        createNotificationChannel()
    }

    fun showPriceAlert(product: Product, oldPrice: Double, newPrice: Double) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Fiyat Değişikliği")
            .setContentText("${product.name} ürününün fiyatı ${oldPrice}TL'den ${newPrice}TL'ye ${if (newPrice < oldPrice) "düştü" else "yükseldi"}!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(product.id.hashCode(), notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Fiyat Takibi",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Fiyat değişikliği bildirimleri"
        }
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "price_alerts"
    }
}