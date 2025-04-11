package com.epdc.epdcnewsadmin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ThirdSection")
public class ThirdSection {

    @Id
    private String id;

    private String title1;
    private String text1;
    private String image1;

    private String title2;
    private String text2;
    private String image2;

    private String title3;
    private String text3;
    private String image3;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle1() {
        return title1;
    }
    public void setTitle1(String title1) {
        this.title1 = title1;
    }
    public String getText1() {
        return text1;
    }
    public void setText1(String text1) {
        this.text1 = text1;
    }
    public String getImage1() {
        return image1;
    }
    public void setImage1(String image1) {
        this.image1 = image1;
    }
    public String getTitle2() {
        return title2;
    }
    public void setTitle2(String title2) {
        this.title2 = title2;
    }
    public String getText2() {
        return text2;
    }
    public void setText2(String text2) {
        this.text2 = text2;
    }
    public String getImage2() {
        return image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }
    public String getTitle3() {
        return title3;
    }
    public void setTitle3(String title3) {
        this.title3 = title3;
    }
    public String getText3() {
        return text3;
    }
    public void setText3(String text3) {
        this.text3 = text3;
    }
    public String getImage3() {
        return image3;
    }
    public void setImage3(String image3) {
        this.image3 = image3;
    }
    public String getTitle4() {
        return title4;
    }
    public void setTitle4(String title4) {
        this.title4 = title4;
    }
    public String getText4() {
        return text4;
    }
    public void setText4(String text4) {
        this.text4 = text4;
    }
    public String getImage4() {
        return image4;
    }
    public void setImage4(String image4) {
        this.image4 = image4;
    }
    public String getTitle5() {
        return title5;
    }
    public void setTitle5(String title5) {
        this.title5 = title5;
    }
    public String getText5() {
        return text5;
    }
    public void setText5(String text5) {
        this.text5 = text5;
    }
    public String getImage5() {
        return image5;
    }
    public void setImage5(String image5) {
        this.image5 = image5;
    }
    public String getTitle6() {
        return title6;
    }
    public void setTitle6(String title6) {
        this.title6 = title6;
    }
    public String getText6() {
        return text6;
    }
    public void setText6(String text6) {
        this.text6 = text6;
    }
    public String getImage6() {
        return image6;
    }
    public void setImage6(String image6) {
        this.image6 = image6;
    }
    private String title4;
    private String text4;
    private String image4;

    private String title5;
    private String text5;
    private String image5;

    private String title6;
    private String text6;
    private String image6;


}
