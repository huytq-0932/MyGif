package com.sun.mygif.ui.main

import com.sun.mygif.R
import com.sun.mygif.ui.base.BaseActivity
import com.sun.mygif.ui.explore.ExploreContentFragment
import com.sun.mygif.ui.favorite.FavoriteContentFragment
import com.sun.mygif.ui.home.HomeContentFragment
import com.sun.mygif.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutResource: Int = R.layout.activity_main

    override fun initComponent() {
        openFragment(R.id.frameContent, HomeContentFragment(), false)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> openFragment(R.id.frameContent, HomeContentFragment(), false)
                R.id.item_explore -> openFragment(R.id.frameContent, ExploreContentFragment(), false)
                R.id.item_favorite -> openFragment(R.id.frameContent, FavoriteContentFragment(), false)
            }
            true
        }
    }

    override fun initData() {
    }

    override fun onBackPressed() = if (isSearchingGifs()) handleSearchOff() else super.onBackPressed()

    private fun isSearchingGifs() = supportFragmentManager.findFragmentById(R.id.constraintMain) is SearchFragment

    private fun handleSearchOff() = with(supportFragmentManager) {
        for (index in 0 until backStackEntryCount) popBackStack()
    }
}
