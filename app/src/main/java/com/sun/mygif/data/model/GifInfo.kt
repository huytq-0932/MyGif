package com.sun.mygif.data.model

import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

private const val FIELD_GIFID = "gifId"
private const val FIELD_CATEGORY = "category"

@Parcelize
data class GifInfo(val gifId: String, val category: String) : Parcelable {
    constructor(cursor: Cursor) : this(
        gifId = cursor.getString(cursor.getColumnIndex(FIELD_GIFID)),
        category = cursor.getString(cursor.getColumnIndex(FIELD_CATEGORY))
    )
}
