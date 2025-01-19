@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    
    @Provides
    @Singleton
    fun providePriceAnalyzer(): PriceAnalyzer {
        return PriceAnalyzerImpl()
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository = productRepositoryImpl
}