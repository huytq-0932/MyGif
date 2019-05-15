package com.sun.mygif.ui.main

import android.support.v4.app.Fragment
import com.sun.mygif.R
import com.sun.mygif.ui.BaseActivity
import com.sun.mygif.ui.explore.ExploreContentFragment
import com.sun.mygif.ui.favorite.FavoriteContentFragment
import com.sun.mygif.ui.home.HomeContentFragment
import kotlinx.android.synthetic.main.activity_main.bottomNavigation

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

    private fun openFragment(id: Int, fragment: Fragment, isAddToBackStack: Boolean) =
        supportFragmentManager.beginTransaction().replace(id, fragment).apply {
            if (isAddToBackStack) addToBackStack(null)
        }.commit()
}
