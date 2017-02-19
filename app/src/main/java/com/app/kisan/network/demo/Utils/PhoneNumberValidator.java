package com.app.kisan.network.demo.Utils;

import java.util.regex.Pattern;

/**
 * Created by adyro on 18-02-2017.
 */

public class PhoneNumberValidator {

    public static boolean isValidPhoneNumber(String mobNo){

        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");

        if(pattern.matcher(mobNo).matches()){
            return true;
        }else {
            return false;
        }
    }

}
