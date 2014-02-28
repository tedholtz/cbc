package com.breathingsoftware;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CodeBaseComment {
    public static void main(String[] args) {
        String ticket = args[0];

        Document doc;
    	try {
     
            String url = "https://api3.codebasehq.com";
            String username = "account_name/username";
            String password = "api_key";
            String login = username + ":" + password;
            String base64login = new String(Base64.encodeBase64(login.getBytes()));
            
            Response ticket_xml = Jsoup
                .connect(url + "/opentempo-java/tickets/" + ticket)
                .header("Authorization", "Basic " + base64login)
                .header("Accept", "application/xml")
                .header("Content-type", "application/xml")
                .execute();

            String[] xml = ticket_xml.body().split("\\r?\\n");
            String id = null;
            String summary = null;
            for (String x : xml) {
                if (x.contains("<ticket-id")) id = parseValue(x);
                if (x.contains("<summary>")) summary = parseValue(x);
                if (id != null && summary != null) break;
            }

            System.out.printf("CB %s - %s\n", id, summary);
            System.out.printf("\n");
            System.out.printf("[touch:%s]\n", id);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    private static String parseValue(String x) {
        return x
            .replaceAll("^[^>]*>", "")
            .replaceAll("<.*$", "");
    }
}
