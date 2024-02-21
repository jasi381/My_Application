//package com.example.taskoneday.utils
//
//import android.os.Bundle
//import android.os.Parcelable
//import androidx.compose.runtime.saveable.Saver
//import androidx.compose.runtime.saveable.SaverScope
//import com.example.taskoneday.data.SpecificationItems
//
//object CustomSaver : Saver<SpecificationItems, Parcelable> {
//    override fun SaverScope.save(value: SpecificationItems): Parcelable {
//        val bundle = Bundle()
//        bundle.putString("_id", value._id)
//        bundle.putBoolean("is_default_selected", value.is_default_selected)
//        bundle.putStringArrayList("name", ArrayList(value.name))
//        bundle.putInt("price", value.price)
//        bundle.putInt("sequence_number", value.sequence_number)
//        bundle.putString("specification_group_id", value.specification_group_id)
//        bundle.putInt("unique_id", value.unique_id)
//        return bundle
//    }
//
//    override fun restore(value: Parcelable): SpecificationItems {
//        val bundle = value as Bundle
//        return SpecificationItems(
//            _id = bundle.getString("_id")!!,
//            is_default_selected = bundle.getBoolean("is_default_selected"),
//            name = bundle.getStringArrayList("name")!!,
//            price = bundle.getInt("price"),
//            sequence_number = bundle.getInt("sequence_number"),
//            specification_group_id = bundle.getString("specification_group_id")!!,
//            unique_id = bundle.getInt("unique_id")
//        )
//    }
//
//}
//
////object CustomMapSaver : Saver<Map<String, Pair<Boolean, Dummy>>, Parcelable> {
////    override fun SaverScope.save(value: Map<String, Pair<Boolean, Dummy>>): Parcelable {
////        val bundle = Bundle()
////        value.forEach { (key, pair) ->
////            bundle.putBoolean("${key}_checked", pair.first)
////            bundle.putParcelable(key, CustomSaver.save(pair.second))
////        }
////        return bundle
////    }
////
////    override fun restore(value: Parcelable): Map<String, Pair<Boolean, Dummy>> {
////        val bundle = value as Bundle
////        val map = mutableMapOf<String, Pair<Boolean, Dummy>>()
////        bundle.keySet().forEach { key ->
////            if (key.endsWith("_checked")) {
////                val actualKey = key.removeSuffix("_checked")
////                val isChecked = bundle.getBoolean(key)
////                val dummy = CustomSaver.restore(bundle.getParcelable(actualKey)!!)
////                map[actualKey] = Pair(isChecked, dummy)
////            }
////        }
////        return map
////    }
////}
//
//
