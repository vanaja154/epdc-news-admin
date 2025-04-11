package com.epdc.epdcnewsadmin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SecondSection")
public class SecondSection {
     @Id
    private String id;
    
    private String leftText1;
    private String leftText2;
    private String leftText3;

    private String videoText2;
    private String videoText3;

    private String leftImage1;
    private String leftImage2;
    private String leftImage3;

    private String video1;
    private String video2;
    private String video3;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLeftText1() {
        return leftText1;
    }
    public void setLeftText1(String leftText1) {
        this.leftText1 = leftText1;
    }
    public String getLeftText2() {
        return leftText2;
    }
    public void setLeftText2(String leftText2) {
        this.leftText2 = leftText2;
    }
    public String getLeftText3() {
        return leftText3;
    }
    public void setLeftText3(String leftText3) {
        this.leftText3 = leftText3;
    }
    public String getVideoText2() {
        return videoText2;
    }
    public void setVideoText2(String videoText2) {
        this.videoText2 = videoText2;
    }
    public String getVideoText3() {
        return videoText3;
    }
    public void setVideoText3(String videoText3) {
        this.videoText3 = videoText3;
    }
    public String getLeftImage1() {
        return leftImage1;
    }
    public void setLeftImage1(String leftImage1) {
        this.leftImage1 = leftImage1;
    }
    public String getLeftImage2() {
        return leftImage2;
    }
    public void setLeftImage2(String leftImage2) {
        this.leftImage2 = leftImage2;
    }
    public String getLeftImage3() {
        return leftImage3;
    }
    public void setLeftImage3(String leftImage3) {
        this.leftImage3 = leftImage3;
    }
    public String getVideo1() {
        return video1;
    }
    public void setVideo1(String video1) {
        this.video1 = video1;
    }
    public String getVideo2() {
        return video2;
    }
    public void setVideo2(String video2) {
        this.video2 = video2;
    }
    public String getVideo3() {
        return video3;
    }
    public void setVideo3(String video3) {
        this.video3 = video3;
    }

    

}
