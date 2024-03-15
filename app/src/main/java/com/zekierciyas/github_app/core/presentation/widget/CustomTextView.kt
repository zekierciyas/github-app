package com.zekierciyas.github_app.core.presentation.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.zekierciyas.github_app.R

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var smallFontSize: Float = 12f

    init {
        applyAttributes(context, attrs)
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.CustomTextView, 0, 0
            )

            // Custom font size attributes
            smallFontSize = typedArray.getDimension(
                R.styleable.CustomTextView_customTextFont,
                12f // Default small font size
            )

            // Custom text attributes
            val customText = typedArray.getString(R.styleable.CustomTextView_customText)
            customText?.let { text = it }

            // Custom font color
            val fontColor = typedArray.getColor(
                R.styleable.CustomTextView_customTextFont,
                currentTextColor
            )
            setTextColor(fontColor)

            // Custom font
            val fontPath = typedArray.getString(R.styleable.CustomTextView_customTextFont)
            fontPath?.let {
                val typeface = Typeface.createFromAsset(context.assets, it)
                setTypeface(typeface)
            }

            typedArray.recycle()
        }
    }
}
