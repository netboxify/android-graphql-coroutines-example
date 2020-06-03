package i.xxxx.graphql_coroutines_example

import BooksQuery
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import i.xxxx.graphql_coroutines_example.AppoloClient.getApolloClient
import i.xxxx.graphql_coroutines_example.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var apolloClient: ApolloClient = getApolloClient()
    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    fun getBooks(view: View) {
        coroutineScope.launch {
            makeSearchRequest(binding.editText.text.toString())
        }
    }

    private suspend fun makeSearchRequest(value: String) {
        val res = apolloClient.query(
            BooksQuery.builder()
                .title(value).build()
        ).toDeferred().await()

        binding.recyclerview.apply {
            adapter = res.data?.findBooks()?.let { RecyclerViewAdapter(it) }
        }
    }

}
