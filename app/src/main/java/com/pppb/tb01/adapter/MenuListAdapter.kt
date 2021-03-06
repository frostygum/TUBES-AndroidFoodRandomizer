package com.pppb.tb01.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.pppb.tb01.databinding.ComponentMenuItemBinding
import com.pppb.tb01.model.Menu
import com.pppb.tb01.utils.Utils
import com.pppb.tb01.viewmodel.FoodListViewModel
import com.pppb.tb01.viewmodel.PageViewModel

class MenuListAdapter(context: Context, data: List<Menu>, private val pageViewModel: PageViewModel) : ArrayAdapter<Menu>(context, 0, data) {
    private val menuList: List<Menu> = data
    private val view: Context = context

    override fun getCount(): Int {
        return menuList.size
    }

    override fun getItem(position: Int): Menu {
        return menuList[position]
    }

    //Recycle List Pattern
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {val viewHolder: ViewHolder
        val itemView: View

        if (convertView == null) {
            itemView = ComponentMenuItemBinding.inflate(LayoutInflater.from(this.view)).root
            viewHolder = ViewHolder(itemView, this.pageViewModel)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.updateView(this.getItem(position))

        return itemView
    }

    private class ViewHolder(private val view: View, private val pageViewModel: PageViewModel) {
        private val binding: ComponentMenuItemBinding = ComponentMenuItemBinding.bind(view)

        fun updateView(menu: Menu) {
            //Instantiate View Binding for Menu Item
            this.binding.tvMenuText.text = menu.getName()
            //Listener when Clicked Menu
            this.binding.menuList.setOnClickListener{
                when(menu.getId()) {
                    1 -> this.pageViewModel.changePage("HOME")
                    2 -> this.pageViewModel.startRandomizer()
                    3 -> this.pageViewModel.changePage("LIST_FOOD")
                    4 -> this.pageViewModel.changePage("SETTING")
                    5 -> this.pageViewModel.exitApplication()
                    else -> {
                        Log.d("DEBUG", "BTN CLICK")
                    }
                }
                //Close Drawer
                pageViewModel.closeLeftDrawer()
            }
        }
    }
}