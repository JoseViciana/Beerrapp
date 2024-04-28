package com.jviciana.beerrapp.ui.view.adapters

import android.app.Dialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.jviciana.beerrapp.R
import com.jviciana.beerrapp.core.extensions.loadImage
import com.jviciana.beerrapp.data.model.BeerModel
import java.lang.String
import kotlin.Int

class BeerAdapter(private var items: List<BeerModel>) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beers = items[position]
        holder.textView.text = beers.name
        holder.imageView.loadImage(beers.image)

        holder.layout.setOnClickListener {
            val customDialog = Dialog(
                holder.view.context
            )
            with(customDialog){
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(true)
                setContentView(R.layout.beer_details)
                findViewById<ImageView>(R.id.beerImageView).loadImage(beers.image)
                findViewById<TextView>(R.id.beerTextView).text = beers.name
                findViewById<TextView>(R.id.beerDetailsTextView).text = beers.description
                findViewById<TextView>(R.id.beerAbvTitleTextView).text = "${beers.abv}%"
                findViewById<MaterialButton>(R.id.closeButton).setOnClickListener { cancel() }
                show()
            }

        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<BeerModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var layout: ConstraintLayout = view.findViewById(R.id.itemLayout)
        var imageView: ImageView = view.findViewById(R.id.itemImageView)
        var textView: TextView = view.findViewById(R.id.itemTextView)
        var view:View = view
    }
}