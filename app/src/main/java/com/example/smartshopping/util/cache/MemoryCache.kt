class MemoryCache<K, V>(
    private val maxSize: Int = 100
) {
    private val cache = LinkedHashMap<K, V>(0, 0.75f, true)
    
    @Synchronized
    fun put(key: K, value: V) {
        if (cache.size >= maxSize) {
            val firstKey = cache.keys.first()
            cache.remove(firstKey)
        }
        cache[key] = value
    }
    
    @Synchronized
    fun get(key: K): V? = cache[key]
}