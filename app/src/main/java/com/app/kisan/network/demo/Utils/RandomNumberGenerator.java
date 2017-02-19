package com.app.kisan.network.demo.Utils;

import java.util.Random;

/**
 * Created by adyro on 18-02-2017.
 */

public class RandomNumberGenerator {

    public static int randomNumber(){
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return n;
    }
}
