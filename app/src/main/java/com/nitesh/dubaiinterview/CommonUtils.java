package com.nitesh.dubaiinterview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.nitesh.dubaiinterview.CustomPackage.TypefacedEditText;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedTextView;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    private Context context;

    public CommonUtils(Context context){
        this.context = context;
    }


    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public void logV(String class_tag, String msg) {
        Log.v(class_tag, class_tag + ": " + msg);
    }

    private static int screenWidth = 0;
    private static int screenHeight = 0;
    public static String getPaddedNumber(int number) {
        return String.format("%02d", number);
    }

    public static SpannableString setRechargeAmount(Context context, String amount) {
        if (context != null && amount == null || (amount != null && amount.trim().isEmpty())) {
            return new SpannableString("");
        }
        amount = commaFormatedAmount(amount).contains(context.getString(R.string.INR))
                ? commaFormatedAmount(amount)
                : context.getString(R.string.INR) + " " + commaFormatedAmount(amount);
        if (!amount.contains(".")) {
//            amount = commaFormatedAmount(amount);
            amount += ".00"; // \ u00a0 unicode for blank space character
        }

        int start = 0;
        int length = amount.length();
        int end = amount.indexOf(".");

        SpannableString ss1 = new SpannableString(amount);
        ss1.setSpan(new RelativeSizeSpan(1f), start, end, 0); // set size


        // Decimal part ----------------------------------------------------------------------------
        ss1.setSpan(new RelativeSizeSpan(0.7f), end + 1, length, 0);// set color

        return ss1;
    }
    public static String commaFormatedAmount(@NonNull String amount) {

        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        String result = format.format(Float.parseFloat(getRawAmount(amount))).replaceAll("Rs.", "").replaceAll("\\.00", "");
        return result.trim();
    }
    public static String getRawAmount(@NonNull String amount) {
        // u00A0 is a empty unicode character which is NOT blank space, so trim doesn't handle it
        amount = TextUtils.isEmpty(amount) ? "0" : amount.trim()
                .replaceAll("\u20B9", "")
                .replaceAll(",", "")
                .replaceAll(" ", "")
                .replaceAll("\u00A0", "");

        return TextUtils.isEmpty(amount) ? "0" : amount;
    }

    public static String generate6DigitRandomNo() {
        SecureRandom test = new SecureRandom();
        int result = test.nextInt(10000);
        String resultStr = result + "";
        if (resultStr.length() != 4)
            for (int x = resultStr.length(); x < 4; x++) resultStr = "0" + resultStr;
        return resultStr;
    }

    public static String generate7DigitRandomNo() {
        SecureRandom test = new SecureRandom();
        int result = test.nextInt(1000000000);
        String resultStr = result + "";
        if (resultStr.length() != 9)
            for (int x = resultStr.length(); x < 9; x++) resultStr = "0" + resultStr;
        return resultStr;
    }
    public static int dp2px(int dp, Context context) {
        if (dp < 0) dp = -dp;
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }
    public static String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
    public static void setEtNonFocusable(TypefacedEditText... et) {
        for (TypefacedEditText e : et) {
            e.setFocusable(false);
            e.setFocusableInTouchMode(false);
            e.setLongClickable(false);
        }
    }
    public static boolean Password_pattern (String password){
        Pattern Password_pattern = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$");
        if (!Password_pattern.matcher(password).matches()){
        return false;
        }
        return true;
    }
    public static int getDateDiffInDays(String selectedDate) {

        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        Date date1 = null, date2 = null;
        try {
            String currentDate = mDay + "/" + (mMonth + 1) + "/" + mYear;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date1 = sdf.parse(currentDate);

            date2 = sdf.parse(selectedDate);
            long diff = date1.getTime() - date2.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            return (int) days;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String currentDateTime(){
        String date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd ',' HH:mm:ss a");
        date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    public static String[] suffixes =
            //    0     1     2     3     4     5     6     7     8     9
            { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                    //    10    11    12    13    14    15    16    17    18    19
                    "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
                    //    20    21    22    23    24    25    26    27    28    29
                    "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                    //    30    31
                    "th", "st" };

    public static String[] Monthsuffixes =
            //    01    02     03     04     05     06     07    08     09     10
            { "","Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sep", "Oct",
                    //    11    12
                    "Nov", "Dec"};


    public static String SuffixWithChangeFormat(String apidate){
        String mdate = null;
        String [] Split = apidate.split(",");
        String mdateSplit = Split[0];
        String [] datesplit = mdateSplit.split("-");
        String date = datesplit[2].trim();
        String month = datesplit[1];
        String year = datesplit[0];
        mdate = date + suffixes[Integer.parseInt(date)] + " " + Monthsuffixes[Integer.parseInt(month)] + " " + year + " at " + changeTime(Split[1]);
        return mdate;
    }
    private static String changeTime(String time) {
        String outTime = "00";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Date dt = sdf.parse(time);

            SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
            outTime = sdfs.format(dt);
        }catch (Exception e){

        }
        return outTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDateTime() {
        Date date=null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date());               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1);      // adds one hour
        cal.getTime();
        try {
            date = formatter.parse(String.valueOf(cal.getTime()));
            Log.e("formated date ", date + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    public void encryptData(String data,String key) {
        Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);
        SharedPreferences preferences =    PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, encryption.encryptOrNull(data));
        editor.apply();
    }
    public String decryptData(String key){
        String data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);
        data  = encryption.decryptOrNull(preferences.getString(key,""));
        return data;
    }

    public void clearData(){
        try{
            final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

        }catch (Exception e){

        }
    }
    public static String AddedZeroSuffix(int code){
        if (code == 1 || code == 2 || code == 3 ||  code == 4 ||  code == 5 ||  code == 6 ||  code == 7 ||
                code == 8 ||  code == 9){
            return "0"+code;
        }else{
            return String.valueOf(code);
        }
    }
    public static boolean validCellPhone(String number)
    {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }

    public static void hideKeyBoard(Activity activity) {
        try {
            if (activity != null) {
                View view = activity.getCurrentFocus();
                final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    public static void CreateTextColorCustome(String text, Context context, int startpoint, int endpoint, TypefacedTextView textView) {

        SpannableString spanString = new SpannableString(text);

        spanString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.yellow)), startpoint, endpoint, 0); //to highlight word havgin '@'

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);

            }
        };
        spanString.setSpan(clickableSpan, startpoint, endpoint, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setText(spanString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static String DayAndNight(){
        String returnText = null;
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            returnText = "Good Morning";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            returnText = "Good Afternoon";
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            returnText = "Good Evening";
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            returnText = "Good Night";
        }

        return returnText;
    }
    public static String getCurrencyInfoForLocale() {
        Locale defaultLocale = Locale.getDefault();
        Currency currency = Currency.getInstance(defaultLocale);
        System.out.println("Currency Code: " + currency.getCurrencyCode());
        System.out.println("Symbol: " + currency.getSymbol());
        System.out.println("Default Fraction Digits: " + currency.getDefaultFractionDigits());
        System.out.println();
        return currency.getCurrencyCode();
    }

    public static boolean emailCommaRegexValidation(String emailIDs){
        return emailIDs.matches("^(([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)(\\s*(;|,)\\s*|\\s*$))*$");
    }
    public static boolean emailIDValidation(String emialid_string){
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return  emialid_string.matches(EMAIL_REGEX);
    }

    public static int compareVersions(String version1, String version2){

        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++){
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0){
                return compare;
            }
        }

        return 0;
    }

    public static String dbkey(){
        return "?[,5QBz+s~1w&f'26KH6`d&%&(N*R-";
    }
}
