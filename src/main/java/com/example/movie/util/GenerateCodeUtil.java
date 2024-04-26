package com.example.movie.util;

import java.util.Random;

public class GenerateCodeUtil {

    public static String generateCode() {
        Random random = new Random();
        //Tạo ra mã gồm 6 chữ số
        long codeRandom = random.nextInt(900000) + 100000;
        return String.valueOf(codeRandom);
    }
}
