package com.masliaiev.filmspace.presentation.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.masliaiev.filmspace.presentation.fragments.MainFragment

class RecyclerViewPagerAdapter(
    private val rvList: List<RecyclerView>,
    private val context: Context
): PagerAdapter() {

    override fun getCount(): Int {
        return rvList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        container.addView(rvList[position])

        return rvList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "first"
            1 -> "second"
            else -> null
        }
    }
}