package com.example.seoanalyzer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SEOAnalyzer {
    public static ScoreReport analyze(Document doc) {
        ScoreReport report = new ScoreReport();

        // 1. <title>
        Element title = doc.selectFirst("title");
        if (title != null && !title.text().isBlank()){
            report.addPoints(10, "<title> tag present");
        } else {
            report.subtractPoints(10, "Missing <title> tag");
        }

        // 2. <meta name="description">
        Element desc = doc.selectFirst("meta[name=description]");
        if (desc != null && !desc.attr("content").isBlank()) {
            report.addPoints(10, "Meta description present");
        } else {
            report.subtractPoints(10, "Missing meta description");
        }

        // 3. <meta name="keywords">
        Element keywords = doc.selectFirst("meta[name=keywords]");
        if (keywords != null && !keywords.attr("content").isBlank()) {
            report.addPoints(5, "Meta keywords present");
        } else {
            report.subtractPoints(5, "Missing meta keywords");
        }

        // 4. <h1> tags
        Elements h1s = doc.select("h1");
        if (!h1s.isEmpty()) {
            report.addPoints(5, "Found " + h1s.size() + " <h1> tag(s)");
        } else {
            report.subtractPoints(5, "No <h1> tags found");
        }

        // 5.<img> without alt
        Elements imgs = doc.select("img");
        int missingAlts = 0;
        for (Element img : imgs){
            if (img.attr("alt").isBlank()){
                missingAlts++;
            }
        }
        if (imgs.isEmpty()) {
            report.addPoints(2, "No images to check");
        } else {
            int pts = missingAlts * 2;
            report.subtractPoints(pts, missingAlts + " image(s) without alt attribute");
        }
        return report;
    }
}
