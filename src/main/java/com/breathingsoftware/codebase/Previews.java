package com.breathingsoftware.codebase;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Previews {
    public static void run(String[] args) {

        Document doc;
    	try {
            System.out.println("URL: " + Query.URL);
            System.out.println("base64login: " + Query.base64login());
     
            Response ticket_xml = Jsoup
                .connect(Query.URL + "/opentempo-java/tickets?query=resolution:open")
                .header("Authorization", "Basic " + Query.base64login())
                .header("Accept", "application/xml")
                .header("Content-type", "application/xml")
                .execute();

            String[] xml = ticket_xml.body().split("\\r?\\n");
            String id = null;
            String summary = "";
            for (String x : xml) {
                if (x.contains("<ticket-id")) {
                    id = parseValue(x);
                    summary = "";
                }

                if (x.contains("<summary>")) summary = parseValue(x);

                if (x.contains("<tags>") && ! x.contains("tedpreviewed")) {
                    System.out.printf("%s: %s\n", id, summary);
                    System.out.println(" - https://6degrees.codebasehq.com/projects/opentempo-java/tickets/" + id);
                }
            }

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
