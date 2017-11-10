package ge.dev.baqari.si.model

import com.google.gson.annotations.SerializedName

class DefinitionValue {
    @SerializedName("_id")
    var id: String? = null
    @SerializedName("_rev")
    var rev: String? = null
    @SerializedName("wordID")
    var wordID: Int? = null
    @SerializedName("Word")
    var word: String? = null
    @SerializedName("Text")
    var text: String? = null
    @SerializedName("DictType")
    var dictType: Int? = null
    @SerializedName("DictName")
    var dictName: String? = null
    @SerializedName("soundcode")
    var soundcode: Int? = null

}