package com.woutervandervelde.e_cook.data.database.typeconverter

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
    fun toTags(value: String): List<Tag> = value.split(",").map { enumValues<Tag>()[it.toInt()] }

    @TypeConverter
    fun fromMeasurementUnit(value: MeasurementUnit): String = value.name

    @TypeConverter
    fun toMeasurementUnit(value: String): MeasurementUnit = MeasurementUnit.valueOf(value)
}