package com.killkinto.blockchain

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.killkinto.blockchain.data.Quote
import com.killkinto.blockchain.overview.QuoteAdapter
import com.killkinto.blockchain.overview.Status
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("listData")
fun RecyclerView.setListData(list: List<Quote>?) =
    list?.let {
        (this.adapter as QuoteAdapter).submitList(list)
    }

@BindingAdapter("datetime")
fun TextView.formatDateTime(timestamp: Long?) {
    timestamp?.let {
        if (timestamp != 0L) {
            val format =
                SimpleDateFormat(context.getString(R.string.datetime_mask), Locale.getDefault())
            val datetime = Date(timestamp * 1000)
            text = format.format(datetime)
        }
    }
}

@BindingAdapter("statusImage")
fun ImageView.setStatusImage(status: Status?) {
    status?.let {
        when (status) {
            Status.LOADING -> {
                this.visibility = View.VISIBLE
                this.setImageResource(R.drawable.loading_animation)
            }
            Status.ERROR -> {
                this.visibility = View.VISIBLE
                this.setImageResource(R.drawable.ic_baseline_cloud_off_48)
            }
            Status.EMPTY -> {
                this.visibility = View.VISIBLE
                this.setImageResource(R.drawable.ic_baseline_block)
            }
            Status.ERROR_CONNECT -> {
                this.visibility = View.VISIBLE
                this.setImageResource(R.drawable.ic_baseline_cloud_off_48)
            }
            Status.DONE -> this.visibility = View.GONE
        }
    }
}