package ge.dev.baqari.readandtranslate.ui

import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ge.dev.baqari.readandtranslate.R


@Suppress("SENSELESS_COMPARISON")
class TranslationAdapter(private var sentences: List<String?>?) : BaseAdapter() {

    override fun getCount(): Int {
        return sentences?.size!!
    }

    override fun getItem(i: Int): Any? {
        return sentences!![i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View? {
        var convertView = view
        if (convertView == null)
            convertView = LayoutInflater.from(viewGroup.context).inflate(R.layout.translate_text_item, viewGroup, false)

        val holder = TranslationHolder(convertView!!)
        holder.translatedText.text = sentences!![i]

        return convertView
    }

    inner class TranslationHolder internal constructor(view: View) {
        var translatedText: AppCompatTextView = view.findViewById(R.id.text_translated)
    }
}
