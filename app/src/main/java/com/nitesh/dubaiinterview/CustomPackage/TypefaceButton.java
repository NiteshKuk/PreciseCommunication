package com.nitesh.dubaiinterview.CustomPackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.nitesh.dubaiinterview.R;


@RemoteViews.RemoteView
public class TypefaceButton extends AppCompatButton {
    public TypefaceButton(Context context) {
        super(context);
    }

    public TypefaceButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    public TypefaceButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    static class CustomFontHelper {

        /**
         * Sets a font on a textview based on the custom com.my.package:font attribute
         * If the custom font attribute isn't found in the attributes nothing happens
         *
         * @param textview
         * @param context
         * @param attrs
         */
        @SuppressWarnings("JavaDoc")
        public static void setCustomFont(TextView textview, Context context, AttributeSet attrs) {
//            TypedArray a = mcontext.obtainStyledAttributes(attrs, R.styleable.ButtonFont);
//            String font = a.getString(R.styleable.ButtonFont_font);
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefacedButton);
            int index = styledAttrs.getInt(R.styleable.TypefacedButton_customfont, 1);
            String font = "Lato-Regular.ttf";

            switch (index) {
                case 1:
                    // font = "Roboto-Light.ttf";
                    break;
                case 2:
                    //  font = "Roboto-Regular.ttf";
                    break;
                case 3:
                    //  font = "Roboto-Bold.ttf";
                    break;
                case 4:
                    font = "NanumGothic.ttf";
                    break;
                case 5:
                    font = "irisupc.ttf";
                    break;

                case 6:
                    font = "clanproBook.otf";
                    break;
                case 7:
                    font = "clanproNarrBook.otf";
                    break;
                case 8:
                    font = "clanproNarrNews.otf";
                    break;
                case 9:
                    font = "Lato-Light.ttf";
                    break;
                case 10:
                    font = "Lato-Regular.ttf";
                    break;
                case 11:
                    font = "Lato-Semibold.ttf";
                    break;
                case 12:
                    font = "Lato-Black.ttf";
                    break;

            }
            setCustomFont(textview, font, context);
            styledAttrs.recycle();
//            a.recycle();
        }

        /**
         * Sets a font on a textview
         *
         * @param textview
         * @param font
         * @param context
         */
        @SuppressWarnings("JavaDoc")
        public static void setCustomFont(TextView textview, String font, Context context) {
            if (font == null) {
                return;
            }
            Typeface tf = FontCache.get(font, context);
            if (tf != null) {
                textview.setTypeface(tf);
            }
        }

    }

}
