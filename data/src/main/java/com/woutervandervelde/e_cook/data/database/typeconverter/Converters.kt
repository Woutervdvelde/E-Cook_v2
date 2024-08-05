package com.woutervandervelde.e_cook.data.database.typeconverter

import android.util.Log
import androidx.room.TypeConverter
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.Tag

class Converters {
    @TypeConverter
    fun fromTag(value: Tag): String = value.name

    @TypeConverter
    fun toTag(value: String): Tag = enumValueOf<Tag>(value)

    @TypeConverter
    fun fromTags(value: List<Tag>): String = value.joinToString(",") { it.ordinal.toString() }

    @TypeConverter
    fun toTags(value: String): List<Tag> {
        val result: MutableList<Tag> = mutableListOf()
        val tags = enumValues<Tag>()
        value.split(",").forEach {
            if (it.isBlank()) return@forEach
            result.add(tags[it.toInt()])
        }
        return result
    }

    @TypeConverter
    fun fromMeasurementUnit(value: MeasurementUnit): String = value.name

    @TypeConverter
    fun toMeasurementUnit(value: String): MeasurementUnit = MeasurementUnit.valueOf(value)

    @TypeConverter
    fun fromSteps(value: List<String>): String {
        return if (value.isEmpty()) ""
        else value.joinToString("|||")
    }

    @TypeConverter
    fun toSteps(value: String): List<String> {
        return if (value.isBlank()) listOf()
        else value.split("|||")
    }
}