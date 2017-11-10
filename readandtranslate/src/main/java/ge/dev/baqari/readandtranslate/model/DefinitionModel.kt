package ge.dev.baqari.readandtranslate.model


import com.google.gson.annotations.SerializedName

class DefinitionModel {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("value")
    var value: DefinitionValue? = null
}
