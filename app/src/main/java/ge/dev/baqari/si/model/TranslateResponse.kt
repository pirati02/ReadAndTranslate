package ge.dev.baqari.si.model


import com.google.gson.annotations.SerializedName

class TranslateResponse {
    @SerializedName("total_rows")
    var totalRows: Int? = null
    @SerializedName("offset")
    var offset: Int? = null
    @SerializedName("rows")
    var rows: List<DefinitionModel>? = null
}
