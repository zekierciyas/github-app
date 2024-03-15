package com.zekierciyas.github_app.core.presentation.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.zekierciyas.github_app.R

class CustomSearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var searchEditText: TextInputEditText
    private var searchBarBackground: Drawable? = null
    private var searchIcon: Drawable? = null
    private var searchBarHint: String? = null
    private var searchBarTextColor: Int = Color.BLACK
    private var searchBarHintColor: Int = Color.GRAY
    private var searchBarIconTint: Int = Color.GRAY
    private var cornerRadius: Float = 20f // Default value

    init {
        // Load attributes
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.CustomSearchBar, defStyleAttr, 0
            )
            searchBarBackground = typedArray.getDrawable(R.styleable.CustomSearchBar_searchBarBackground)
            searchIcon = typedArray.getDrawable(R.styleable.CustomSearchBar_searchIcon)
            searchBarHint = typedArray.getString(R.styleable.CustomSearchBar_searchBarHint)
            searchBarTextColor = typedArray.getColor(R.styleable.CustomSearchBar_searchBarTextColor, Color.BLACK)
            searchBarHintColor = typedArray.getColor(R.styleable.CustomSearchBar_searchBarHintColor, Color.GRAY)
            searchBarIconTint = typedArray.getColor(R.styleable.CustomSearchBar_searchBarIconTint, Color.GRAY)
            cornerRadius = typedArray.getFloat(R.styleable.CustomSearchBar_searchBarCornerRadius, 20f)

            typedArray.recycle()
        }

        initView(context)
    }

    private fun initView(context: Context) {
        orientation = HORIZONTAL

        val cardView = CardView(context)
        val cardParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        cardParams.setMargins(8, 8, 8, 8)
        cardView.layoutParams = cardParams
        cardView.radius = cornerRadius

        searchEditText = TextInputEditText(context)
        searchEditText.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        searchEditText.background = null
        searchEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT
        searchEditText.maxLines = 1
        searchEditText.hint = searchBarHint
        searchEditText.setTextColor(searchBarTextColor)

        val searchIconView = ImageView(context)
        val params =  LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        searchIconView.layoutParams = params

        searchIconView.setImageDrawable(searchIcon?.apply { setTint(searchBarIconTint) })

        cardView.addView(searchEditText)
        cardView.addView(searchIconView)
        addView(cardView)
    }

    fun addTextChangedListener(watcher: TextWatcher) {
        searchEditText.addTextChangedListener(watcher)
    }
}
