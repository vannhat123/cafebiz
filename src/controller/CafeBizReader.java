/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Article;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import model.CafeBizModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.CrawlerThread;

/**
 *
 * @author nhat
 */
public class CafeBizReader {

    private static ArrayList<Article> listArticle = new ArrayList<>();
    private static ArrayList<Article> listArticlefull = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CafeBizModel cafeBizModel = new CafeBizModel();
        CafeBizReader cafeBizReader = new CafeBizReader();
        cafeBizReader.getIndexArticles();
        //    cafeBizReader.printListArticle();
        cafeBizReader.getArticleDetail();
   //     cafeBizReader.printListArticleFull();

        ArrayList<CrawlerThread> listThread = new ArrayList<>();
//        HashSet<CrawlerThread> listThread = new HashSet<>();
        for (int i = 0; i < listArticlefull.size(); i++) {
            CrawlerThread crawler1 = new CrawlerThread(listArticlefull.get(i).getUrl());
            listThread.add(crawler1);
            crawler1.start();
        }
        cafeBizModel.insert(listArticlefull);
        for (CrawlerThread crawlerThread : listThread) {
            crawlerThread.join();
        }
        long endTime = System.currentTimeMillis();
        System.err.println("Thời gian chạy: " + (endTime - startTime) + " mls.");
        for (int i = 0; i < listThread.size(); i++) {
            CrawlerThread get = listThread.get(i);

        }

    }

    public void printListArticle() {
        for (int i = 0; i < listArticle.size(); i++) {
            Article art = listArticle.get(i);
            System.out.println(i + 1 + ". " + art.getUrl());
        }
    }

    public void printListArticleFull() {
        for (int i = 0; i < listArticlefull.size(); i++) {
            Article art = listArticlefull.get(i);
            System.out.println(i + 1 + ". " + art.getUrl()+ art.getTitle());
        }
    }

    public void getIndexArticles() {
        try {
       //     if (listArticle.size() == 0) {
                Document doc = Jsoup.connect("http://cafebiz.vn/chuyen-cua-3-con-ca-doc-va-ngam-nhieu-nguoi-trong-chung-ta-se-thay-ngam-ngui-20180412162344398.chn").get();
                Elements els = doc.select(".topmenuul .normal a");
                for (int i = 0; i < els.size(); i++) {
                    Element el = els.get(i);
                    // Biến các phần tử html thành đối tượng article.
                    Article article = new Article();
                    article.setUrl("http://cafebiz.vn" + el.attr("href"));
                    article.setTitle(el.text());
                    article.setStatus(0);
                    listArticle.add(article);

                }
          //  }
        } catch (IOException ex) {
            System.err.println("Khong the lay thong tin");
        }
    }

    public void getArticleDetail() {
        try {
            //     if (listArticlefull.size() == 0) {
            for (int i = 0; i < listArticle.size() && i != 5; i++) {
                Article get = listArticle.get(i);
                Document doc = Jsoup.connect(get.getUrl()).get();
                Elements els = doc.select("a");
                for (int j = 0; j < els.size(); j++) {
                    Element el = els.get(j);
                    // Biến các phần tử html thành đối tượng article.
                    Article article = new Article();
                    article.setUrl("http://cafebiz.vn" + el.attr("href"));
                    article.setTitle(el.text());
                    article.setStatus(1);
                    listArticlefull.add(article);
                }
            }
            //  }
        } catch (IOException ex) {
            System.err.println("Khong the lay thong tin");
        }
    }

}
