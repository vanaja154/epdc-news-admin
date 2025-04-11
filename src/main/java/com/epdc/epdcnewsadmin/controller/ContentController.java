package com.epdc.epdcnewsadmin.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.epdc.epdcnewsadmin.entity.Admin;
// Ensure all entity and service classes exist and are correctly imported
// import com.epdc.epdcnewsadmin.entity.AndhraPradesh;
import com.epdc.epdcnewsadmin.entity.DonationSection;
import com.epdc.epdcnewsadmin.entity.Epaper;
import com.epdc.epdcnewsadmin.entity.Feedback;
import com.epdc.epdcnewsadmin.entity.FourthSection;
import com.epdc.epdcnewsadmin.entity.HomePageContent;
import com.epdc.epdcnewsadmin.entity.News;
import com.epdc.epdcnewsadmin.entity.SecondSection;
import com.epdc.epdcnewsadmin.entity.ThirdSection;
import com.epdc.epdcnewsadmin.entity.UserFeedBack;
import com.epdc.epdcnewsadmin.repository.AdminRepository;
// import com.epdc.epdcnewsadmin.service.AndhraPradeshService;
import com.epdc.epdcnewsadmin.service.DonationSectionService;
import com.epdc.epdcnewsadmin.service.EpaperService;
import com.epdc.epdcnewsadmin.service.FeedbackService;
import com.epdc.epdcnewsadmin.service.FourthSectionService;
import com.epdc.epdcnewsadmin.service.HomePageContentService;
import com.epdc.epdcnewsadmin.service.NewsService;
import com.epdc.epdcnewsadmin.service.SecondSectionService;
import com.epdc.epdcnewsadmin.service.ThirdSectionService;
import com.epdc.epdcnewsadmin.service.UserFeedBackService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ContentController {

    @Autowired
    private HomePageContentService contentService;

    @Autowired
    private SecondSectionService contentServiceSecondSection;

    @Autowired
    private ThirdSectionService contentServiceThirdSection;

    @Autowired
    private FourthSectionService contentServiceFourthSection;

    @Autowired
    private EpaperService epaperService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private DonationSectionService donationSectionService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserFeedBackService userfeedbackService;

    @Autowired
    private AdminRepository adminRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @CrossOrigin(origins = "http://localhost:8023")

    @GetMapping("/index")
    public String showHomePage(HttpSession session,Model model) {

         // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }

        HomePageContent firstSection = contentService.getLatestContent();
        if (firstSection == null) {
            firstSection = new HomePageContent(); // Create new content if not exists
        }

        // Add attributes to the model
        model.addAttribute("contentList", contentService.getAllContent());
        model.addAttribute("firstSection", firstSection);

        return "index";
    }

    @GetMapping("/news/{category}")
    public String showNewsForm(@PathVariable String category, Model model) {
        News news = newsService.getLatestContentByCategory(category);
        if (news == null) {
            news = new News();
            news.setCategory(category);
        }
        model.addAttribute("news", news);
        return "news-form"; // Thymeleaf template for the form
    }

    @PostMapping("/updateContent")
    public String updateContent(
            @RequestParam(value = "addText", required = false) String addText,
            @RequestParam(value = "mainImage", required = false) MultipartFile mainImage,
            @RequestParam(value = "mainText", required = false) String mainText,
            @RequestParam(value = "sidebar1Image", required = false) MultipartFile sidebar1Image,
            @RequestParam(value = "sidebar1Title", required = false) String sidebar1Title,
            @RequestParam(value = "sidebar1Text", required = false) String sidebar1Text,
            @RequestParam(value = "sidebar2Image", required = false) MultipartFile sidebar2Image,
            @RequestParam(value = "sidebar2Title", required = false) String sidebar2Title,
            @RequestParam(value = "sidebar2Text", required = false) String sidebar2Text,
            @RequestParam(value = "sidebar3Image", required = false) MultipartFile sidebar3Image,
            @RequestParam(value = "sidebar3Title", required = false) String sidebar3Title,
            @RequestParam(value = "sidebar3Text", required = false) String sidebar3Text,
            Model model) {

        HomePageContent content = contentService.getLatestContent();
        if (content == null) {
            content = new HomePageContent();
        }

        // Update fields if they are provided
        if (addText != null && !addText.isEmpty()) {
            content.setAddText(addText);
        }
        if (mainImage != null && !mainImage.isEmpty()) {
            content.setMainImage(saveFile(mainImage));
        }
        if (mainText != null && !mainText.isEmpty()) {
            content.setMainText(mainText);
        }
        if (sidebar1Image != null && !sidebar1Image.isEmpty()) {
            content.setSidebar1Image(saveFile(sidebar1Image));
        }
        if (sidebar1Title != null && !sidebar1Title.isEmpty()) {
            content.setSidebar1Title(sidebar1Title);
        }
        if (sidebar1Text != null && !sidebar1Text.isEmpty()) {
            content.setSidebar1Text(sidebar1Text);
        }
        if (sidebar2Image != null && !sidebar2Image.isEmpty()) {
            content.setSidebar2Image(saveFile(sidebar2Image));
        }
        if (sidebar2Title != null && !sidebar2Title.isEmpty()) {
            content.setSidebar2Title(sidebar2Title);
        }
        if (sidebar2Text != null && !sidebar2Text.isEmpty()) {
            content.setSidebar2Text(sidebar2Text);
        }
        if (sidebar3Image != null && !sidebar3Image.isEmpty()) {
            content.setSidebar3Image(saveFile(sidebar3Image));
        }
        if (sidebar3Title != null && !sidebar3Title.isEmpty()) {
            content.setSidebar3Title(sidebar3Title);
        }
        if (sidebar3Text != null && !sidebar3Text.isEmpty()) {
            content.setSidebar3Text(sidebar3Text);
        }

        // Save the updated content
        contentService.saveContent(content);

        // Pass updated content back to the view
        model.addAttribute("firstSection", content);
        return "index";
    }

    @GetMapping("/second")
    public String showSecondSectionPage(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        model.addAttribute("secondSection", contentServiceSecondSection.getLatestContent());

        SecondSection secondSection = contentServiceSecondSection.getLatestContent();
        if (secondSection == null) {
            secondSection = new SecondSection(); // Create new content if not exists
        }

        // Add attributes to the model
        model.addAttribute("contentList", contentServiceSecondSection.getAllContent());
        model.addAttribute("secondSection", secondSection);

        return "second";
    }

    // Endpoint to update the data
    @PostMapping("/updateSecondSection")
    public String updateSecondSection(
            @RequestParam(value = "leftImage1", required = false) MultipartFile leftImage1,
            @RequestParam(value = "leftText1", required = false) String leftText1,
            @RequestParam(value = "leftImage2", required = false) MultipartFile leftImage2,
            @RequestParam(value = "leftText2", required = false) String leftText2,
            @RequestParam(value = "leftImage3", required = false) MultipartFile leftImage3,
            @RequestParam(value = "leftText3", required = false) String leftText3,
            @RequestParam(value = "video1", required = false) MultipartFile video1,
            @RequestParam(value = "video2", required = false) MultipartFile video2,
            @RequestParam(value = "videoText2", required = false) String videoText2,
            @RequestParam(value = "video3", required = false) MultipartFile video3,
            @RequestParam(value = "videoText3", required = false) String videoText3,
            Model model) {

        SecondSection content = contentServiceSecondSection.getLatestContent();
        if (content == null) {
            content = new SecondSection();
        }

        // Update fields if they are provided
        if (leftImage1 != null && !leftImage1.isEmpty()) {
            content.setLeftImage1(saveFile(leftImage1));
        }
        if (leftText1 != null && !leftText1.isEmpty()) {
            content.setLeftText1(leftText1);
        }
        if (leftImage2 != null && !leftImage2.isEmpty()) {
            content.setLeftImage2(saveFile(leftImage2));
        }
        if (leftText2 != null && !leftText2.isEmpty()) {
            content.setLeftText2(leftText2);
        }
        if (leftImage3 != null && !leftImage3.isEmpty()) {
            content.setLeftImage3(saveFile(leftImage3));
        }
        if (leftText3 != null && !leftText3.isEmpty()) {
            content.setLeftText3(leftText3);
        }
        if (video1 != null && !video1.isEmpty()) {
            content.setVideo1(saveFile(video1));
        }
        if (video2 != null && !video2.isEmpty()) {
            content.setVideo2(saveFile(video2));
        }
        if (videoText2 != null && !videoText2.isEmpty()) {
            content.setVideoText2(videoText2);
        }
        if (video3 != null && !video3.isEmpty()) {
            content.setVideo3(saveFile(video3));
        }
        if (videoText3 != null && !videoText3.isEmpty()) {
            content.setVideoText3(videoText3);
        }

        // Save the updated content
        contentServiceSecondSection.saveContent(content);

        // Pass updated content back to the view
        model.addAttribute("secondSection", content);
        return "second";
    }

    @GetMapping("/third")
    public String showThirdSectionPage(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        ThirdSection content = contentServiceThirdSection.getLatestContent();
        if (content == null) {
            content = new ThirdSection(); // Create new content if it doesn't exist
        }
        model.addAttribute("thirdSection", content);
        return "third";
    }

    @PostMapping("/updateThirdSection")
    public String updateThirdSection(
            @RequestParam(value = "image1", required = false) MultipartFile image1,
            @RequestParam(value = "title1", required = false) String title1,
            @RequestParam(value = "text1", required = false) String text1,
            @RequestParam(value = "image2", required = false) MultipartFile image2,
            @RequestParam(value = "title2", required = false) String title2,
            @RequestParam(value = "text2", required = false) String text2,
            @RequestParam(value = "image3", required = false) MultipartFile image3,
            @RequestParam(value = "title3", required = false) String title3,
            @RequestParam(value = "text3", required = false) String text3,
            @RequestParam(value = "image4", required = false) MultipartFile image4,
            @RequestParam(value = "title4", required = false) String title4,
            @RequestParam(value = "text4", required = false) String text4,
            @RequestParam(value = "image5", required = false) MultipartFile image5,
            @RequestParam(value = "title5", required = false) String title5,
            @RequestParam(value = "text5", required = false) String text5,
            @RequestParam(value = "image6", required = false) MultipartFile image6,
            @RequestParam(value = "title6", required = false) String title6,
            @RequestParam(value = "text6", required = false) String text6,
            Model model) {

        ThirdSection content = contentServiceThirdSection.getLatestContent();
        if (content == null) {
            content = new ThirdSection();
        }

        // Update fields if they are provided
        if (image1 != null && !image1.isEmpty()) {
            content.setImage1(saveFile(image1));
        }
        if (title1 != null && !title1.isEmpty()) {
            content.setTitle1(title1);
        }
        if (text1 != null && !text1.isEmpty()) {
            content.setText1(text1);
        }
        if (image2 != null && !image2.isEmpty()) {
            content.setImage2(saveFile(image2));
        }
        if (title2 != null && !title2.isEmpty()) {
            content.setTitle2(title2);
        }
        if (text2 != null && !text2.isEmpty()) {
            content.setText2(text2);
        }
        if (image3 != null && !image3.isEmpty()) {
            content.setImage3(saveFile(image3));
        }
        if (title3 != null && !title3.isEmpty()) {
            content.setTitle3(title3);
        }
        if (text3 != null && !text3.isEmpty()) {
            content.setText3(text3);
        }
        if (image4 != null && !image4.isEmpty()) {
            content.setImage4(saveFile(image4));
        }
        if (title4 != null && !title4.isEmpty()) {
            content.setTitle4(title4);
        }
        if (text4 != null && !text4.isEmpty()) {
            content.setText4(text4);
        }
        if (image5 != null && !image5.isEmpty()) {
            content.setImage5(saveFile(image5));
        }
        if (title5 != null && !title5.isEmpty()) {
            content.setTitle5(title5);
        }
        if (text5 != null && !text5.isEmpty()) {
            content.setText5(text5);
        }
        if (image6 != null && !image6.isEmpty()) {
            content.setImage6(saveFile(image6));
        }
        if (title6 != null && !title6.isEmpty()) {
            content.setTitle6(title6);
        }
        if (text6 != null && !text6.isEmpty()) {
            content.setText6(text6);
        }

        // Save the updated content
        contentServiceThirdSection.saveContent(content);

        // Pass updated content back to the view
        model.addAttribute("thirdSection", content);
        return "third";
    }

    @GetMapping("/fourth")
    public String showFourthSectionPage(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        FourthSection content = contentServiceFourthSection.getLatestContent();
        if (content == null) {
            content = new FourthSection(); // Create new content if it doesn't exist
        }
        model.addAttribute("fourthSection", content);
        return "fourth";
    }

    @PostMapping("/updateFourthSection")
    public String updateFourthSection(
            @RequestParam(value = "image1", required = false) MultipartFile image1,
            @RequestParam(value = "image2", required = false) MultipartFile image2,
            @RequestParam(value = "image3", required = false) MultipartFile image3,
            @RequestParam(value = "image4", required = false) MultipartFile image4,
            @RequestParam(value = "image5", required = false) MultipartFile image5,
            @RequestParam(value = "image6", required = false) MultipartFile image6,
            @RequestParam(value = "image7", required = false) MultipartFile image7,
            @RequestParam(value = "image8", required = false) MultipartFile image8,
            @RequestParam(value = "image9", required = false) MultipartFile image9,
            @RequestParam(value = "image10", required = false) MultipartFile image10,
            @RequestParam(value = "image11", required = false) MultipartFile image11,
            @RequestParam(value = "image12", required = false) MultipartFile image12,
            @RequestParam(value = "image13", required = false) MultipartFile image13,
            @RequestParam(value = "image14", required = false) MultipartFile image14,
            @RequestParam(value = "image15", required = false) MultipartFile image15,
            Model model) {

        FourthSection content = contentServiceFourthSection.getLatestContent();
        if (content == null) {
            content = new FourthSection();
        }

        // Update fields if they are provided
        if (image1 != null && !image1.isEmpty()) {
            content.setImage1(saveFile(image1));
        }
        if (image2 != null && !image2.isEmpty()) {
            content.setImage2(saveFile(image2));
        }
        if (image3 != null && !image3.isEmpty()) {
            content.setImage3(saveFile(image3));
        }
        if (image4 != null && !image4.isEmpty()) {
            content.setImage4(saveFile(image4));
        }
        if (image5 != null && !image5.isEmpty()) {
            content.setImage5(saveFile(image5));
        }
        if (image6 != null && !image6.isEmpty()) {
            content.setImage6(saveFile(image6));
        }
        if (image7 != null && !image7.isEmpty()) {
            content.setImage7(saveFile(image7));
        }
        if (image8 != null && !image8.isEmpty()) {
            content.setImage8(saveFile(image8));
        }
        if (image9 != null && !image9.isEmpty()) {
            content.setImage9(saveFile(image9));
        }
        if (image10 != null && !image10.isEmpty()) {
            content.setImage10(saveFile(image10));
        }
        if (image11 != null && !image11.isEmpty()) {
            content.setImage11(saveFile(image11));
        }
        if (image12 != null && !image12.isEmpty()) {
            content.setImage12(saveFile(image12));
        }
        if (image13 != null && !image13.isEmpty()) {
            content.setImage13(saveFile(image13));
        }
        if (image14 != null && !image14.isEmpty()) {
            content.setImage14(saveFile(image14));
        }
        if (image15 != null && !image15.isEmpty()) {
            content.setImage15(saveFile(image15));
        }

        // Save the updated content
        contentServiceFourthSection.saveContent(content);

        // Pass updated content back to the view
        model.addAttribute("fourthSection", content);
        return "redirect:/fourth";
    }

    @PostMapping("/updateCombinedContent")
    public String updateCombinedContent(
            @RequestParam String category,
            @RequestParam(value = "sidebar1Image", required = false) MultipartFile sidebar1Image,
            @RequestParam(value = "sidebar1Title", required = false) String sidebar1Title,
            @RequestParam(value = "sidebar1Text", required = false) String sidebar1Text,
            @RequestParam(value = "sidebar2Image", required = false) MultipartFile sidebar2Image,
            @RequestParam(value = "sidebar2Title", required = false) String sidebar2Title,
            @RequestParam(value = "sidebar2Text", required = false) String sidebar2Text,
            @RequestParam(value = "sidebar3Image", required = false) MultipartFile sidebar3Image,
            @RequestParam(value = "sidebar3Title", required = false) String sidebar3Title,
            @RequestParam(value = "sidebar3Text", required = false) String sidebar3Text,
            @RequestParam(value = "rightImage1", required = false) MultipartFile rightImage1,
            @RequestParam(value = "rightImage2", required = false) MultipartFile rightImage2,
            @RequestParam(value = "image1", required = false) MultipartFile image1,
            @RequestParam(value = "title1", required = false) String title1,
            @RequestParam(value = "text1", required = false) String text1,
            @RequestParam(value = "image2", required = false) MultipartFile image2,
            @RequestParam(value = "title2", required = false) String title2,
            @RequestParam(value = "text2", required = false) String text2,
            @RequestParam(value = "image3", required = false) MultipartFile image3,
            @RequestParam(value = "title3", required = false) String title3,
            @RequestParam(value = "text3", required = false) String text3,
            Model model) throws IOException {

        News news = newsService.getLatestContentByCategory(category);
        if (news == null) {
            news = new News();
            news.setCategory(category);
        }

        // Update Sidebar Section
        if (sidebar1Image != null && !sidebar1Image.isEmpty()) {
            news.setSidebar1Image(saveFile(sidebar1Image));
        }
        if (sidebar1Title != null) {
            news.setSidebar1Title(sidebar1Title);
        }
        if (sidebar1Text != null) {
            news.setSidebar1Text(sidebar1Text);
        }
        if (sidebar2Image != null && !sidebar2Image.isEmpty()) {
            news.setSidebar2Image(saveFile(sidebar2Image));
        }
        if (sidebar2Title != null) {
            news.setSidebar2Title(sidebar2Title);
        }
        if (sidebar2Text != null) {
            news.setSidebar2Text(sidebar2Text);
        }
        if (sidebar3Image != null && !sidebar3Image.isEmpty()) {
            news.setSidebar3Image(saveFile(sidebar3Image));
        }
        if (sidebar3Title != null) {
            news.setSidebar3Title(sidebar3Title);
        }
        if (sidebar3Text != null) {
            news.setSidebar3Text(sidebar3Text);
        }

        // Update Right Section
        if (rightImage1 != null && !rightImage1.isEmpty()) {
            news.setRightImage1(saveFile(rightImage1));
        }
        if (rightImage2 != null && !rightImage2.isEmpty()) {
            news.setRightImage2(saveFile(rightImage2));
        }

        // Update Second Section
        if (image1 != null && !image1.isEmpty()) {
            news.setImage1(saveFile(image1));
        }
        if (title1 != null) {
            news.setTitle1(title1);
        }
        if (text1 != null) {
            news.setText1(text1);
        }
        if (image2 != null && !image2.isEmpty()) {
            news.setImage2(saveFile(image2));
        }
        if (title2 != null) {
            news.setTitle2(title2);
        }
        if (text2 != null) {
            news.setText2(text2);
        }
        if (image3 != null && !image3.isEmpty()) {
            news.setImage3(saveFile(image3));
        }
        if (title3 != null) {
            news.setTitle3(title3);
        }
        if (text3 != null) {
            news.setText3(text3);
        }

        // Save the updated content
        newsService.saveContent(news);
        return "redirect:/newssub?category=" + category;
    }

    @PostMapping("/updateMainEditions")
    public String updateMainEditions(
            @RequestParam(value = "edition1Image", required = false) MultipartFile edition1Image,
            @RequestParam(value = "edition1Title", required = false) String edition1Title,
            @RequestParam(value = "edition1PdfFile", required = false) MultipartFile edition1PdfFile,
            @RequestParam(value = "advertisementImage", required = false) MultipartFile advertisementImage,
            @RequestParam(value = "advertisementLink", required = false) String advertisementLink,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, // Parse date correctly
            Model model) {
    
        // Fetch the existing edition by date or create a new one
        Epaper epaper = epaperService.getEditionsByDate(date).stream().findFirst().orElse(null);
        if (epaper == null) {
            epaper = new Epaper();
            epaper.setDate(date); // Set the date for the new edition
        }
    
        // Update Edition 1 fields
        if (edition1Image != null && !edition1Image.isEmpty()) {
            epaper.setEdition1Image(saveFile(edition1Image));
        }
        if (edition1Title != null && !edition1Title.trim().isEmpty()) {
            epaper.setEdition1Title(edition1Title.trim());
        }
        if (edition1PdfFile != null && !edition1PdfFile.isEmpty()) {
            epaper.setEdition1PdfFile(saveFile(edition1PdfFile));
        }
    
        // Update Advertisement fields
        if (advertisementImage != null && !advertisementImage.isEmpty()) {
            epaper.setAdvertisementImage(saveFile(advertisementImage));
        }
        if (advertisementLink != null && !advertisementLink.trim().isEmpty()) {
            epaper.setAdvertisementLink(advertisementLink.trim());
        }
    
        // Save the updated content
        epaperService.saveEditions(epaper);
    
        // Pass updated content back to the view
        model.addAttribute("epaper", epaper);
        return "epaper";
    }
    private String saveFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GetMapping("/news")
    public String andhraPradesh() {
        return "news";
    }

    @GetMapping("/epaper")
public String epaper(HttpSession session, Model model, 
                     @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }

    Epaper epaper;
    if (date != null) {
        epaper = epaperService.getEditionsByDate(date).stream().findFirst().orElse(new Epaper());
    } else {
        epaper = epaperService.getLatestEditions();
        if (epaper == null) {
            epaper = new Epaper(); // Create a default object if none exists
        }
    }

    model.addAttribute("epaper", epaper);
    return "epaper";
}
    @GetMapping("/newssub")
    public String showNewsFormAfterSubmiting(@RequestParam String category, Model model) {
        News news = newsService.getLatestContentByCategory(category);
        if (news == null) {
            news = new News();
            news.setCategory(category);
        }
        model.addAttribute("category", category);
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/newupdate")
    public String mynewUpdate(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        return "updatenews";
    }

    @GetMapping("/scrooling")
    public String scrooling(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        return "updatenewsscrooling";
    }

    @GetMapping("/adds")
    public String adds(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        return "adds";
    }

    @GetMapping("/donation")
    public String showDonationPage(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        DonationSection content = donationSectionService.getLatestContent();
        if (content == null) {
            content = new DonationSection(); // Create new content if it doesn't exist
        }
        model.addAttribute("donationSection", content);
        return "donation"; // Corresponds to donation.html
    }

    @PostMapping("/updateDonationSection")
    public String updateDonationSection(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "amount", required = false) Double amount,
            Model model) {

        DonationSection content = donationSectionService.getLatestContent();
        if (content == null) {
            content = new DonationSection();
        }

        // Update fields if they are provided
        if (image != null && !image.isEmpty()) {
            content.setImage(saveFile(image));
        }
        if (title != null && !title.isEmpty()) {
            content.setTitle(title);
        }
        if (text != null && !text.isEmpty()) {
            content.setText(text);
        }
        if (amount != null) {
            content.setAmount(amount);
        }

        // Save the updated content
        donationSectionService.saveContent(content);

        // Pass updated content back to the view
        model.addAttribute("donationSection", content);
        return "donation"; // Reload the donation.html page
    }

    @GetMapping("/feedback")
    public String showAdminFeedback(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        // Fetch all feedback from the database
        List<Feedback> feedbackList = feedbackService.getAllFeedback();

        // Pass the feedback list to the model
        model.addAttribute("feedbackList", feedbackList);

        return "feedback"; // Corresponds to admin-feedback.html
    }

    @PostMapping("/admin/approve-feedback/{id}")
    public String approveFeedback(@PathVariable String id) {
        feedbackService.approveFeedback(id);
        return "redirect:/feedback";
    }

    @PostMapping("/adminlogin")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session, // Add HttpSession
            Model model) {

        System.out.println("Login attempt with email: " + email);
        Admin admin = adminRepository.findByEmail(email);
        System.out.println("Admin found: " + admin);

        if (admin != null) {
            System.out.println("Stored password: " + admin.getPassword());
            System.out.println("Entered password: " + password);
            System.out.println("Password match: " + admin.getPassword().equals(password));
        }

        if (admin != null && admin.getPassword().equals(password)) {
            // Set session attribute to indicate admin is logged in
            session.setAttribute("adminLoggedIn", true);
            System.out.println("Login successful. Redirecting to /index");
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid email or password!");
            System.out.println("Login failed. Adding error message.");
            return "adminlogin";
        }
    }

    @GetMapping("/")
    public String admin() {
        return "adminlogin";
    }


    @GetMapping("/userfeedback")
    public String showUserFeedback(HttpSession session, Model model) {

        // Check if admin is logged in
    Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
    if (isLoggedIn == null || !isLoggedIn) {
        return "redirect:/"; // Redirect to login page if not logged in
    }
        // Fetch all feedback from the database
        List<UserFeedBack> feedbackList = userfeedbackService.getAllFeedback();

        // Pass the feedback list to the model
        model.addAttribute("feedbackList", feedbackList);

        return "userfeedback"; // Corresponds to admin-feedback.html
    }

    @PostMapping("/admin/approve/{id}")
    public String approveUserFeedback(@PathVariable String id) {
        userfeedbackService.approveFeedback(id);
        return "redirect:/userfeedback";
    }

}