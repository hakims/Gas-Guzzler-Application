
// Note: this Class was borrowed from a StackOverflow post: 
// http://stackoverflow.com/questions/5357455/limit-decimal-places-in-android-edittext


package com.example.gasguzzler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.InputFilter;
import android.text.Spanned;

public class DecimalDigitsInputFilter implements InputFilter {

Pattern mPattern;

public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
    mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
}

@Override
public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher matcher=mPattern.matcher(dest);       
        if(!matcher.matches())
            return "";
        return null;
    }

}
