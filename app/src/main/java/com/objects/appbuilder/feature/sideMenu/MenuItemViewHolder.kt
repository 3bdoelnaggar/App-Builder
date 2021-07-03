package com.objects.appbuilder.feature.sideMenu

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.objects.appbuilder.databinding.MenuItemBinding
import com.objects.appbuilder.feature.main.UiMenuItem

class MenuItemViewHolder(private val binding: MenuItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UiMenuItem, onItemClicked: (UiMenuItem)->Unit) {
        if (item.isSelected) {
            binding.menuItemConstraintLayout.setBackgroundColor(item.selectedColor)
        }else{
            binding.menuItemConstraintLayout.setBackgroundColor(item.backgroundColor)
        }
        binding.titleTextView.setTextColor(Color.parseColor("#000000"))
        binding.titleTextView.text = item.title
        binding.root.setOnClickListener {
            onItemClicked.invoke(item)
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup): MenuItemViewHolder {
            val binding =
                MenuItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return MenuItemViewHolder(binding)
        }
    }

}
