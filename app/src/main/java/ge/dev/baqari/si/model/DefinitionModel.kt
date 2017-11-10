package ge.dev.baqari.si.model


import com.google.gson.annotations.SerializedName

class DefinitionModel {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("value")
    lateinit var value: DefinitionValue
}
