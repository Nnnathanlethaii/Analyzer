package com.example.seoanalyzer;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("=== Simple SEO Analyzer ===");
        System.out.print("Enter URL to analyze (include http:// or https://): ");
        String url = in.nextLine().trim();

        try {
            System.out.println("\nFetching page...");
            Document doc = WebScraper.fetch(url);

            System.out.println("Analyzing SEO elements...\n");
            ScoreReport report = SEOAnalyzer.analyze(doc);

            // Print detail lines
            List<String> details = report.getDetails();
            details.forEach(System.out::println);

            // Final score (clamp to [0,100])
            int score = Math.max(0, Math.min(100, report.getScore()));
            System.out.println("\nOverall SEO Score: " + score + " / 100");

        } catch (IOException e) {
            System.err.println("Error fetching or parsing URL: " + e.getMessage());
        } finally {
            in.close();
        }
    }
}
