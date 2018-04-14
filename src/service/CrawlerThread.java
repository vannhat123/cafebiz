/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Article;
import java.util.ArrayList;
import model.CafeBizModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author nhat
 */
public class CrawlerThread extends Thread {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CrawlerThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        Article article = new Article();
        try {
            Document doc = Jsoup.connect(this.url).get();
            Element element = doc.select(".w640right").get(0);
            article.setTitle(doc.select("h1.title").text());
            article.setContent(element.text());
            article.setStatus(1);
//              System.out.println("-------------------------------------------------------------------------------------------------");
//            System.out.println(""+ article.getTitle());
//            System.out.println(""+ article.getContent());
//            System.out.println("-------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
       //     e.printStackTrace();
        }
    }

}
