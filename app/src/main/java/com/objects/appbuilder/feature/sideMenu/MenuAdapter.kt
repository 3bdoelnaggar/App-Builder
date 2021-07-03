package com.objects.appbuilder.feature.sideMenu

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.objects.appbuilder.feature.main.UiMenuItem

class MenuAdapter(private val items: List<UiMenuItem>,private val onItemClicked:(UiMenuItem)->Unit): RecyclerView.Adapter<MenuItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
       return MenuItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(items[position],onItemClicked)
    }

    override fun getItemCount(): Int {
      return items.size
    }
}