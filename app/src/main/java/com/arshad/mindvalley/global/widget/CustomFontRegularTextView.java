package com.arshad.mindvalley.global.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.arshad.mindvalley.global.Constants;

public class CustomFontRegularTextView extends TextView {
    /*
     * Caches typefaces based on their file path and name,
     * so that they don't have to be created every time when they are referenced.
     */
    private static Typeface mTypeface;

    public CustomFontRegularTextView(final Context context) {
        this(context, null);
    }

    public CustomFontRegularTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontRegularTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), Constants.FONT_REGULAR);
        }
        setTypeface(mTypeface);
    }
}