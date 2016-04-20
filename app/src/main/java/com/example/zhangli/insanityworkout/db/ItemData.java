package com.example.zhangli.insanityworkout.db;

/**
 * Created by zhangli on 16/4/20.
 */
public class ItemData {
    private int id;
    private String day;
    private String content;
    private String iscomplete;
    private String time;


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDay(){
        return this.day;
    }

    public void setDay(String day){
        this.day = day;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getIscomplete(){
        return this.iscomplete;
    }

    public void setIscomplete(String iscomplete){
        this.iscomplete = iscomplete;
    }

    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time = time;
    }


}
