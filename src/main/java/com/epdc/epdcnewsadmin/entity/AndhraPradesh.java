package com.epdc.epdcnewsadmin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AndhraPradesh")
public class AndhraPradesh {

     @Id
    private String id;

    private String sidebar1Image;
    private String sidebar1Title;
    private String sidebar1Text;

    private String sidebar2Image;
    private String sidebar2Title;
    private String sidebar2Text;

    private String sidebar3Image;
    private String sidebar3Title;
    private String sidebar3Text;

    private String center1Image;
    private String center1Title;
    private String center1Text;

    private String center2Image;
    private String center2Title;
    private String center2Text;

    private String center3Image;
    private String center3Title;
    private String center3Text;

    private String rightImage1;
    private String rightImage2;

    private String image1;
    private String title1;
    private String text1;

    private String image2;
    private String title2;
    private String text2;

    private String image3;
    private String title3;
    private String text3;

    private String image4;
    private String title4;
    private String text4;

    private String image5;
    private String title5;
    private String text5;

    private String image6;
    private String title6;
    private String text6;


    private String detailName;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getdetailName() {
        return detailName;
    }
    public void setdetailName(String detailName) {
        this.detailName = detailName;
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
    public String getCenter1Image() {
        return center1Image;
    }
    public void setCenter1Image(String center1Image) {
        this.center1Image = center1Image;
    }
    public String getCenter1Title() {
        return center1Title;
    }
    public void setCenter1Title(String center1Title) {
        this.center1Title = center1Title;
    }
    public String getCenter1Text() {
        return center1Text;
    }
    public void setCenter1Text(String center1Text) {
        this.center1Text = center1Text;
    }
    public String getCenter2Image() {
        return center2Image;
    }
    public void setCenter2Image(String center2Image) {
        this.center2Image = center2Image;
    }
    public String getCenter2Title() {
        return center2Title;
    }
    public void setCenter2Title(String center2Title) {
        this.center2Title = center2Title;
    }
    public String getCenter2Text() {
        return center2Text;
    }
    public void setCenter2Text(String center2Text) {
        this.center2Text = center2Text;
    }
    public String getCenter3Image() {
        return center3Image;
    }
    public void setCenter3Image(String center3Image) {
        this.center3Image = center3Image;
    }
    public String getCenter3Title() {
        return center3Title;
    }
    public void setCenter3Title(String center3Title) {
        this.center3Title = center3Title;
    }
    public String getCenter3Text() {
        return center3Text;
    }
    public void setCenter3Text(String center3Text) {
        this.center3Text = center3Text;
    }
    public String getRightImage1() {
        return rightImage1;
    }
    public void setRightImage1(String rightImage1) {
        this.rightImage1 = rightImage1;
    }
    public String getRightImage2() {
        return rightImage2;
    }
    public void setRightImage2(String rightImage2) {
        this.rightImage2 = rightImage2;
    }
    public String getImage1() {
        return image1;
    }
    public void setImage1(String image1) {
        this.image1 = image1;
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
    public String getImage2() {
        return image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
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
    public String getImage3() {
        return image3;
    }
    public void setImage3(String image3) {
        this.image3 = image3;
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
    public String getImage4() {
        return image4;
    }
    public void setImage4(String image4) {
        this.image4 = image4;
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
    public String getImage5() {
        return image5;
    }
    public void setImage5(String image5) {
        this.image5 = image5;
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
    public String getImage6() {
        return image6;
    }
    public void setImage6(String image6) {
        this.image6 = image6;
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
    


    
}
