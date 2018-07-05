package com.androidgits.quotes.model;

/**
 * Created by Lenovo on 7/5/2018.
 */

public class Quotes {

    private String author;
    private String quote;
    private String cat;

    public Quotes() {
    }

    public Quotes(String author, String quote, String cat) {
        this.author = author;
        this.quote = quote;
        this.cat = cat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
