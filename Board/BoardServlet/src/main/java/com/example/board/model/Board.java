package com.example.board.model;

import java.util.Date;

public class Board {

    private int num;
    private String title;
    private String writer;
    private String content;
    private int cnt;
    private Date regdate;
    private Date lastModified;

    public Board() {

    }

    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Board(int num, String title, String writer, String content) {
        this.num = num;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Board(int num, String title, String writer, String content, int cnt, Date regdate,
        Date lastModified) {
        this.num = num;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.cnt = cnt;
        this.regdate = regdate;
        this.lastModified = lastModified;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
