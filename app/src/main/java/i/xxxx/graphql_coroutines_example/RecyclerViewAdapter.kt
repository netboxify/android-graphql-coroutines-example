package i.xxxx.graphql_coroutines_example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val list:  List<BooksQuery.FindBook>)
    : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: BooksQuery.FindBook = list[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = list.size
}