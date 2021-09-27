package com.example.countryapp.childActivity

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.countryapp.R
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.constants.CountryConst
import com.example.countryapp.mainActivity.MainActivity

class ChildPresenter(private val context: Context) : ChildContract.Presenter {

    override fun setNewTextView(llcurrencies: LinearLayout, text: String, backgroundColor: Int) {
        TextView(context).apply {
            setTextColor(resources.getColor(R.color.mine_shaft))
            setBackgroundResource(backgroundColor)
            typeface = Typeface.create(CountryConst.MAIN_FONT, Typeface.BOLD)
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
            llcurrencies.addView(this)
        }

    }


}