package com.shift.shiftfinal.data.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.shift.shiftfinal.domain.entity.LoanState
import java.lang.reflect.Type

object LoanStateConverter : JsonDeserializer<LoanState?> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LoanState? {
        return json?.let {
            LoanState.valueOf(it.asString)
        }
    }

}