/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CrawlerThread;

/**
 *
 * @author nhat
 */
public class CafeBizModel {

    public boolean insert(ArrayList<Article> listArticlefull) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafebiz?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            for (int i = 0; i < listArticlefull.size(); i++) {
                Article get = listArticlefull.get(i);
                 stt.execute("INSERT INTO readcafe (url, title, content, status) VALUES ('"
                        + get.getUrl() + "', '"
                        + get.getTitle() + "', '"
                        + get.getContent() + "', '"
                        + get.getStatus() + "')");
                 
            }
               System.out.println(listArticlefull.size());
         
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
