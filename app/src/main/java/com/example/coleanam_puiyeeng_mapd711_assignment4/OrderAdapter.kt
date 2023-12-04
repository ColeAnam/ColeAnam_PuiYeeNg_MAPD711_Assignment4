// Contributed by Cole Anam
package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order
// Order Adapter for recycler view
class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_layout, parent, false)
        return OrderViewModel(view)
    }

    override fun onBindViewHolder(holder: OrderViewModel, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount() = orders.size

    inner class OrderViewModel(orderview: View) : ViewHolder(orderview) {

        private val orderId: TextView = orderview.findViewById(R.id.orderId)
        private val customerId: TextView = orderview.findViewById(R.id.customerId)
        private val productId: TextView = orderview.findViewById(R.id.productId)
        private val orderDate: TextView = orderview.findViewById(R.id.orderDate)
        private val quantity: TextView = orderview.findViewById(R.id.quantity)
        private val status: TextView = orderview.findViewById(R.id.status)

        @SuppressLint("SetTextI18n")
        fun bind(order: Order) {
            orderId.text = orderId.text.toString() + order.orderId.toString()
            customerId.text = customerId.text.toString() + order.customerId.toString()
            productId.text = productId.text.toString() + order.productId.toString()
            orderDate.text = orderDate.text.toString() + order.orderDate.toString()
            quantity.text = quantity.text.toString() + order.quantity.toString()
            status.text = status.text.toString() + order.status
        }
    }

}