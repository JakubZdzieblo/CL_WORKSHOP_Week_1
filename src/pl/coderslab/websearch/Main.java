package pl.coderslab.websearch;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> newsTitles = findTextOnSite("https://www.eurogamer.net/", "div.latest");
        ArrayList<String> wordsFromSites = addWordsToList(newsTitles);

        newsTitles = findTextOnSite("https://www.gamespot.com/news/", "article.media.media-article");
        ArrayList<String> tempList = addWordsToList(newsTitles);
        wordsFromSites.addAll(tempList);

        newsTitles = findTextOnSite("https://www.polygon.com/gaming", "div.c-entry-box--compact.c-entry-box--compact--article");
        tempList = addWordsToList(newsTitles);
        wordsFromSites.addAll(tempList);

        newsTitles = findTextOnSite("https://www.shacknews.com/", "div.article-content-wrapper");
        tempList = addWordsToList(newsTitles);
        wordsFromSites.addAll(tempList);

        ArrayList<String> filter = new ArrayList<>();
        filter.addAll(Arrays.asList("published", "comments", "new", "hours", "ago", "days", "but", "and",
                "minutes", "articles", "show", "more", "out", "this", "the", "from", "under", "next", "for"));

        try {
            PrintWriter out1 = new PrintWriter("popular_words.txt");
            for (String word : wordsFromSites) {
                out1.println(word);
            }
            out1.close();

            PrintWriter out2 = new PrintWriter("filtered_popular_words.txt");
            File file = new File("popular_words.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String word = scan.nextLine();
                if (!filter.contains(word)) {
                    out2.println(word);
                }
            }
            out2.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }

    private static ArrayList<String> findTextOnSite (String url, String cssQuery) {
        ArrayList<String> siteContent = new ArrayList<>();
        Connection connect = Jsoup.connect(url);
        try {
            Document document = connect.get();
            Elements links = document.select(cssQuery);
            for (Element elem : links) {
                siteContent.add(elem.text().toLowerCase());
                // System.out.println(elem.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return siteContent;
    }

    private static ArrayList<String> addWordsToList (ArrayList<String> list) {
        ArrayList<String> wordsList = new ArrayList<>();
        for (String element : list) {
            String[] wordArray = element.split(" |!|\\?|,|:|;|\\.|\"|'");
            for (String word : wordArray) {
                if (word.length() >= 3) {
                    wordsList.add(word);
                }
            }
        }
        return wordsList;
    }


}
