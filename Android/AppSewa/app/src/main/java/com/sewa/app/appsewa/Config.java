package com.sewa.app.appsewa;

/**
 * Created by Sushant on 4/3/2016.
 */
public class Config {
    //For AVD the ip is 10.0.2.2, for genymotion 10.0.3.2
    private static final String SERVER_IP="http://192.168.204.50/sewa/";
    private static final String APP_EMAIL="";
    private static final String APP_PASSWORD="";
    public static String getAppEmail() {
        return APP_EMAIL;
    }

    public static String getAppPassword() {
        return APP_PASSWORD;
    }

    public static String getServerIp() {
        return SERVER_IP;
    }
}
