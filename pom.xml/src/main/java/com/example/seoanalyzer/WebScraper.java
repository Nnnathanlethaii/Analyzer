package com.example.seoanalyzer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebScraper {

    /**
     * Fetches the HTML from the given URL and parses it into a jsoup Document.
     *
     * @param url the full http/https URL to scrape
     * @return a parsed Document
     * @throws IOException if fetching fails
     */
    public static Document fetch(String url) throws IOException{
        return Jsoup.connect(url)
                .userAgent("SEO-Analyzer-Bot/1.0")
                .timeout(10_000)
                .get();
    }
}
