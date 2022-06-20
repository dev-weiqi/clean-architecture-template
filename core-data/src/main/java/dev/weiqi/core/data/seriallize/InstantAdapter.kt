package dev.weiqi.core.data.seriallize

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import java.time.Instant

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class InstantType

class InstantTypeAdapter {
    @FromJson
    @InstantType
    fun fromJson(value: String): Instant {
        return Instant.parse(value)
    }

    @ToJson
    fun toJson(@InstantType value: Instant): String {
        return value.toString()
    }
}
