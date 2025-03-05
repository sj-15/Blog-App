package com.test.Blog.util;

import java.util.Base64;

public class Constants {
    public static String JWT_SECRET="MY_BLOG_SECRET";
    String base64EncodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
}
