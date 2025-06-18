package com.epdc.epdcnewsadmin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "content")
public class HomePageContent 
{

    @Id
    private String id;

    private String mainImage;  
    private String mainText;

    private String addText;
    private String adLink; 
    private String adImage; 
    
    public String getAdLink() {
        return adLink;
    }
    public void setAdLink(String adLink) {
        this.adLink = adLink;
    }
    public String getAdImage() {
        return adImage;
    }
    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }
    

    private String sidebar1Image;
    private String sidebar1Title;
    private String sidebar1Text;

    private String sidebar2Image;
    private String sidebar2Title;
    private String sidebar2Text;

    private String sidebar3Image;
    private String sidebar3Title;
    private String sidebar3Text;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMainImage() {
        return mainImage;
    }
    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }
    public String getMainText() {
        return mainText;
    }
    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
    public String getAddText() {
        return addText;
    }
    public void setAddText(String addText) {
        this.addText = addText;
    }
    public String getSidebar1Image() {
        return sidebar1Image;
    }
    public void setSidebar1Image(String sidebar1Image) {
        this.sidebar1Image = sidebar1Image;
    }
    public String getSidebar1Title() {
        return sidebar1Title;
    }
    public void setSidebar1Title(String sidebar1Title) {
        this.sidebar1Title = sidebar1Title;
    }
    public String getSidebar1Text() {
        return sidebar1Text;
    }
    public void setSidebar1Text(String sidebar1Text) {
        this.sidebar1Text = sidebar1Text;
    }
    public String getSidebar2Image() {
        return sidebar2Image;
    }
    public void setSidebar2Image(String sidebar2Image) {
        this.sidebar2Image = sidebar2Image;
    }
    public String getSidebar2Title() {
        return sidebar2Title;
    }
    public void setSidebar2Title(String sidebar2Title) {
        this.sidebar2Title = sidebar2Title;
    }
    public String getSidebar2Text() {
        return sidebar2Text;
    }
    public void setSidebar2Text(String sidebar2Text) {
        this.sidebar2Text = sidebar2Text;
    }
    public String getSidebar3Image() {
        return sidebar3Image;
    }
    public void setSidebar3Image(String sidebar3Image) {
        this.sidebar3Image = sidebar3Image;
    }
    public String getSidebar3Title() {
        return sidebar3Title;
    }
    public void setSidebar3Title(String sidebar3Title) {
        this.sidebar3Title = sidebar3Title;
    }
    public String getSidebar3Text() {
        return sidebar3Text;
    }
    public void setSidebar3Text(String sidebar3Text) {
        this.sidebar3Text = sidebar3Text;
    }

    
}
