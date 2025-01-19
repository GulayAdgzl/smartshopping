object PriceFormatter {
    fun formatPrice(price: Double): String {
        return String.format("%.2f ₺", price)
    }

    fun formatPriceChange(oldPrice: Double, newPrice: Double): String {
        val difference = newPrice - oldPrice
        val percentage = (difference / oldPrice) * 100
        return String.format("%.1f%% ${if (difference >= 0) "↑" else "↓"}", kotlin.math.abs(percentage))
    }
}