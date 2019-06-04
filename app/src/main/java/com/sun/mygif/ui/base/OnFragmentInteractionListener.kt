package com.sun.mygif.ui.base

import android.support.v4.app.Fragment

interface OnFragmentInteractionListener{
    fun onFragmentChange(fragment: Fragment)
    fun onSaveState(gifId: String)
}
