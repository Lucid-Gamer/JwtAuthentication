package com.authenticate.jwt.demo;

import io.jsonwebtoken.io.Encoders;

public class DemoApplication {

    public static void main(String[] args) {
        String str = "198dgASDhADSuwVDXCefb8y21yher8AS91hSADIFionyASDdepyHFasd923P98xczdas3QFWG9";
        byte[] bytes = str.getBytes();
        System.out.println(Encoders.BASE64.encode(bytes));;
    }

}