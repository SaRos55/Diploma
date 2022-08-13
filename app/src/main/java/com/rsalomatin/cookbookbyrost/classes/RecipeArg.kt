package com.rsalomatin.cookbookbyrost.classes

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object RecipeArg : ReadWriteProperty<Bundle, ArrayList<String>?> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: ArrayList<String>?) {
        value?.let { thisRef.putStringArrayList(property.name, it) }
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): ArrayList<String>? {
        return thisRef.getStringArrayList(property.name)
    }
}