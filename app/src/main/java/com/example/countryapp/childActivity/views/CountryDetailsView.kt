package com.example.countryapp.childActivity.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.countryapp.R
import com.example.countryapp.constants.Const
import kotlinx.android.synthetic.main.country_details_view.view.*


class CountryDetailsView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.country_details_view, this)
    }

    fun setNewTextView(text: String, backgroundColor: Int) {
         val textView = TextView(context).apply {
            setTextColor(resources.getColor(R.color.mine_shaft))
            setBackgroundResource(backgroundColor)
            typeface = Typeface.create(Const.MAIN_FONT, Typeface.BOLD)
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.details_textsize)
            )
            with(resources) {
                setPadding(
                    getDimensionPixelSize(R.dimen.country_details_item_padding),
                    getDimensionPixelSize(R.dimen.zero_padding),
                    getDimensionPixelSize(R.dimen.country_details_item_padding),
                    getDimensionPixelSize(R.dimen.zero_padding)
                )
            }
            setText(text)
            RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                with(resources) {
                    setMargins(
                        getDimensionPixelSize(R.dimen.zero_padding),
                        getDimensionPixelSize(R.dimen.zero_padding),
                        getDimensionPixelSize(R.dimen.country_details_item_margin),
                        getDimensionPixelSize(R.dimen.zero_padding)
                    )
                }
                layoutParams = this
            }

        }
        ll_country_details.addView(textView)
    }
}