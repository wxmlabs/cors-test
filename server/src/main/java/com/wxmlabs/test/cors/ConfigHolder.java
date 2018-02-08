package com.wxmlabs.test.cors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigHolder {
    private static Properties config = new Properties();
    private static File configFile = null;
    private static long lastModified = 0;

    public static boolean isEnableCORS() {
        checkReload();
        return Boolean.valueOf(config.getProperty("api.rest.cors.enable", "false"));
    }

    public static String getAllowOriginPattern() {
        checkReload();
        return config.getProperty("api.rest.cors.allow.origin.pattern","(.+\\.)?itrusign.(com|cn)(:\\d+)?");
    }

    public static String getAllowCredentials() {
        checkReload();
        return config.getProperty("api.rest.cors.allow.credentials");
    }

    public static String getAllowHeaders() {
        checkReload();
        return config.getProperty("api.rest.cors.allow.headers");
    }

    public static String getAllowMethods() {
        checkReload();
        return config.getProperty("api.rest.cors.allow.methods");
    }

    public static int getMaxAge() {
        checkReload();
        return Integer.valueOf(config.getProperty("api.rest.cors.max.age", "10"));
    }



    private static void checkReload() {
        if (Boolean.valueOf(config.getProperty("config.reload", "false")) && configFile != null && lastModified < configFile.lastModified()) {
            lastModified = configFile.lastModified();
            try {
                config.load(new FileReader(configFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(String.format("can not found %s failed.", configFile.getAbsolutePath()), e);
            } catch (IOException e) {
                throw new RuntimeException(String.format("reload %s failed.", configFile.getAbsolutePath()), e);
            }
        }
    }

    static {
        try {
            URL configUrl = ConfigHolder.class.getClassLoader().getResource("config.properties");
            if (configUrl == null) {
                throw new Error("can not found config.properties file.");
            }
            if (configUrl.getProtocol().equals("file")) {
                configFile = new File(configUrl.getFile());
                lastModified = configFile.lastModified();
            }
            config.load(configUrl.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
