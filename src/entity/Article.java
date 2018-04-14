/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author nhat
 */
public class Article {

    private String url;
    private String title;
    private String content;
    private int status;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Article() {
    }

    public boolean isValid() {
        if (url == null) {
            return false;
        }
        if (content == null) {
            return false;
        }
        if (status == 0) {
            return false;
        }
        if (title == null) {
            return false;
        }
        return true;
    }

    public Article(String url, String title, String content, int status) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public Article(String content) {
        this.content = content;
    }
 

    public Article(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Article(String url, String title, int status) {
        this.url = url;
        this.title = title;
        this.status = status;
    }

}
