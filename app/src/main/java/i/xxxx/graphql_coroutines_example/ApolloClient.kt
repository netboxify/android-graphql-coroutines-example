package i.xxxx.graphql_coroutines_example

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

object AppoloClient {
    const val BASE_URL = "http://192.168.100.4:4000/graphql"
    private var apClient: ApolloClient? = null
    //Singleton pattern
    @JvmStatic
    fun getApolloClient(): ApolloClient {
        apClient?.let {
            return it
        } ?: kotlin.run {
            apClient = ApolloClient.builder()
                .okHttpClient(
                    OkHttpClient.Builder()
                        .build()
                )
                .serverUrl(BASE_URL)
                .build()
        }
        return apClient!!
    }
}
