package com.nitesh.dubaiinterview.CustomPackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.nitesh.dubaiinterview.R;


public class TypefacedTextView extends AppCompatTextView {
    private Context context;

    public TypefacedTextView(Context context) {
        super(context);
        this.context = context;
//        init();
    }

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        init(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    public TypefacedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        CustomFontHelper.setCustomFont(this, context, attrs);
//        init(context, attrs);
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface typeface = TypefaceLoader.get(context, "fonts/Lato-Regular.ttf");
            setTypeface(typeface);
        }
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
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
            int index = styledAttrs.getInt(R.styleable.TypefacedTextView_customTypeface, 1);
            String font = "Lato-Regular.ttf";

            switch (index) {
                case 1:
                    //font = "Roboto-Light.ttf";
                    break;
                case 2:
                    // font = "Roboto-Regular.ttf";
                    break;
                case 3:
                    // font = "Roboto-Bold.ttf";
                    break;
                case 4:
                    font = "NanumGothic.ttf";
                    break;
                case 5:
                    font = "irisupc.ttf";
                    break;

                case 6:
                    // font = "clanproBook.otf";
                    break;
                case 7:
                    //  font = "clanproNarrBook.otf";
                    break;
                case 8:
                    //  font = "clanproNarrNews.otf";
                    break;
                case 9:
                    font = "Lato-Light.ttf";
                    break;
                default:
                case 10:
                    font = "Lato-Regular.ttf";
                    break;
                case 11:
                    font = "Lato-Semibold.ttf";
                    break;
                case 12:
                    //font = "timesbd.ttf";
                    break;
                case 13:
                    font = "Lato-Black.ttf";
                    break;
                case 14:
                    font = "Farrington-7B-Qiqi.ttf";
                    break;
                case 15:
                    font = "HelveticaNeue-Light.ttf";
                    break;
                case 16:
                    font = "Cred-Card-Regular.ttf";
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
//                // @PKJun20 For letter spacing in API > 20
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//                    textview.setLetterSpacing(0.025f);
            }

        }

    }
}
