package com.breathingsoftware;

import java.io.IOException;

import com.typesafe.config.ConfigFactory;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Message {
    public static void main(String[] args) {
        if (args.length < 2) return;

        String ticket = args[1];

        Document doc;
    	try {
     
            Response ticket_xml = Jsoup
                .connect(Query.URL + "/opentempo-java/tickets/" + ticket)
                .header("Authorization", "Basic " + Query.base64login)
                .header("Accept", "application/xml")
                .header("Content-type", "application/xml")
                .execute();

            String[] xml = ticket_xml.body().split("\\r?\\n");
            String id = null;
            String summary = null;
            for (String x : xml) {
                if (x.contains("<ticket-id")) id = Query.parseValue(x);
                if (x.contains("<summary>")) summary = Query.parseValue(x);
                if (id != null && summary != null) break;
            }

            System.out.printf("CB %s - \n", id);
            System.out.printf("\n");
            System.out.printf("[touch:%s]\n", id);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
