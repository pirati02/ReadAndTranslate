package ge.dev.baqari.si.serviceHelper

interface IOverDrawProvider {
    fun access(requestCode: Int): Boolean
    fun isOverMarshmallow(): Boolean
    fun permissionRequest()
}