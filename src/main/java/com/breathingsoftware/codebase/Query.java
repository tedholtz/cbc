package com.breathingsoftware.codebase;

import java.io.IOException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.codec.binary.Base64;

public class Query {
    public static String URL = "https://api3.codebasehq.com";
    public static String username;
    public static String key;

    public static void main(String[] args) {

    	try {
            loadConfig();

            if (args.length == 0) return;
    
            if ("previews".equals(args[0])) Previews.run(args);
            if ("message".equals(args[0])) Message.run(args);

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public static String base64login() {
        final String login = username + ":" + key;
        return new String(Base64.encodeBase64(login.getBytes()));
    }

    public static String parseValue(String x) {
        return x
            .replaceAll("^[^>]*>", "")
            .replaceAll("<.*$", "");
    }

    private static void loadConfig() {
        Config conf = ConfigFactory.load();
        username = conf.getString("codebase_api_username");
        key = conf.getString("codebase_api_key");
    }
}
