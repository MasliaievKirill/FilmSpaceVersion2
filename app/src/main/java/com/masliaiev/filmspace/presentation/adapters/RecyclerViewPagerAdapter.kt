package com.masliaiev.filmspace.presentation.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.masliaiev.filmspace.R

class RecyclerViewPagerAdapter(
    private val rvList: List<RecyclerView>,
    private val context: Context
) : PagerAdapter() {

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

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            FIRST_PAGE -> context.getString(R.string.title_all_movies)
            SECOND_PAGE -> context.getString(R.string.title_rating_movies)
            else -> throw RuntimeException("Unknown page in tab layout")
        }
    }

    companion object {
        private const val FIRST_PAGE = 0
        private const val SECOND_PAGE = 1
    }
}