package model;

import java.util.Date;

public class Board {

    private int num;
    private String title;
    private String writer;
    private String content;
    private Date regdate;
    private Date last_modified;
    private int cnt;

    public Board() {
    }

    public Board(int num, String title, String writer, String content, Date regdate,
        Date last_modified, int cnt) {
        this.num = num;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.regdate = regdate;
        this.last_modified = last_modified;
        this.cnt = cnt;
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

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
