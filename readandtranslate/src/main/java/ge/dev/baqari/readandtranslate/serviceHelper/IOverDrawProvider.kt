package ge.dev.baqari.readandtranslate.serviceHelper

interface IOverDrawProvider {
    fun access(requestCode: Int): Boolean
    fun isOverMarshmallow(): Boolean
    fun permissionRequest()
}