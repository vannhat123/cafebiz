/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.CafeBizReader;
import entity.Article;
import java.util.ArrayList;
import model.CafeBizModel;
import service.CrawlerThread;

/**
 *
 * @author 84976
 */
public class MainThread {

    private static ArrayList<Article> listArticle = new ArrayList<>();
    private static ArrayList<Article> listArticlefull = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        MainThread mainThread = new MainThread();
        CafeBizModel cafeBizModel = new CafeBizModel();
        mainThread.generateMenu();
//        Article a = new Article("aptech.con.vn", "nhat", "herrooooooooo", 1);
//        listArticlefull.add(a);
//        cafeBizModel.insert(listArticlefull);

    }

    public void generateMenu() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CafeBizModel cafeBizModel = new CafeBizModel();
        CafeBizReader cafeBizReader = new CafeBizReader();
        cafeBizReader.getIndexArticles();
        //    cafeBizReader.printListArticle();
        cafeBizReader.getArticleDetail();
        cafeBizReader.printListArticleFull();
        
        ArrayList<CrawlerThread> listThread = new ArrayList<>();
//        HashSet<CrawlerThread> listThread = new HashSet<>();
        for (int i = 0; i < listArticlefull.size(); i++) {
            CrawlerThread crawler1 = new CrawlerThread(listArticlefull.get(i).getUrl());
            listThread.add(crawler1);
            crawler1.start();
        }
        for (CrawlerThread crawlerThread : listThread) {
            crawlerThread.join();
        }
        long endTime = System.currentTimeMillis();
        System.err.println("Thời gian chạy: " + (endTime - startTime) + " mls.");
  //      cafeBizModel.insert(listThread);
    }
}
