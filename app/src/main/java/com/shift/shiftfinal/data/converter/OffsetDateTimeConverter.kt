package com.shift.shiftfinal.data.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.OffsetDateTime

object OffsetDateTimeConverter : JsonDeserializer<OffsetDateTime?> {


    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): OffsetDateTime? {
        return json?.let {
            OffsetDateTime.parse(it.asString)
        }
    }


}