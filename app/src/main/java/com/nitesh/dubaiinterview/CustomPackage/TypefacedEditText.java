package com.nitesh.dubaiinterview.CustomPackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;

import com.nitesh.dubaiinterview.R;


public class TypefacedEditText extends AppCompatEditText {

    private Context context;

    public TypefacedEditText(Context context) {
        super(context);
        this.context = context;

        initfiler();
    }

    public TypefacedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        CustomFontHelper.setCustomFont(this, context, attrs);
        initfiler();
    }

    public TypefacedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        CustomFontHelper.setCustomFont(this, context, attrs);
        initfiler();
    }


    public void initfiler() {
        InputFilter[] editFilters = getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new EmojiExcludeFilter();
        setFilters(newFilters);


        setLongClickable(false);

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
        public static void setCustomFont(AppCompatEditText textview, Context context, AttributeSet attrs) {
//            TypedArray a = mcontext.obtainStyledAttributes(attrs, R.styleable.ButtonFont);
//            String font = a.getString(R.styleable.ButtonFont_font);
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefacedEditText);
            int index = styledAttrs.getInt(R.styleable.TypefacedEditText_customTypeface, 1);
            String font = "Lato-Regular.ttf";

            switch (index) {
                case 1:
                    // font = "Roboto-Light.ttf";
                    break;
                case 2:
                    // font = "Roboto-Regular.ttf";
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
                    // font = "clanproBook.otf";
                    break;
                case 7:
                    // font = "clanproNarrBook.otf";
                    break;
                case 8:
                    //  font = "clanproNarrNews.otf";
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
                    //  font = "timesbd.ttf";
                    break;
                case 13:
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

    private class EmojiExcludeFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                int type = Character.getType(source.charAt(i));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
            }
            return null;
        }
    }

/*	ImageView fab;
    public void setFab(ImageView fab){
		this.fab = fab;
	}
	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			// Do your thing.
			fab.setVisibility(VISIBLE);
			return super.dispatchKeyEvent(event);//;  // So it is not propagated.
		}
		else{
			fab.setVisibility(GONE);
			return super.dispatchKeyEvent(event);
		}

	}*/


}
