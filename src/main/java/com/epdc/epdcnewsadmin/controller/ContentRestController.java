package com.epdc.epdcnewsadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.epdc.epdcnewsadmin.entity.News;
import com.epdc.epdcnewsadmin.service.NewsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.lang.reflect.Method;

@RestController
public class ContentRestController {

    @Autowired
    private NewsService newsService;

    private static final String UPLOAD_DIR = "uploads/";

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

    @PostMapping(value = "/update-content", consumes = { "multipart/form-data" })
    public String updateContent(
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subcategory,
            @RequestParam("Title") String title,
            @RequestParam("Text") String text,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        // Fetch the latest news content by category
        News news = newsService.getLatestContentByCategory(category);

        if (news == null) {
            news = new News();
            news.setCategory(category);
            // return "No news available";
        }

        try {
            // Dynamically set the title, text, and image fields using reflection
            String titleSetter = "set" + subcategory.substring(0, 1).toUpperCase() + subcategory.substring(1) + "Title";
            String textSetter = "set" + subcategory.substring(0, 1).toUpperCase() + subcategory.substring(1) + "Text";
            String imageSetter = "set" + subcategory.substring(0, 1).toUpperCase() + subcategory.substring(1) + "Image";

            Method setTitleMethod = News.class.getMethod(titleSetter, String.class);
            Method setTextMethod = News.class.getMethod(textSetter, String.class);
            Method setImageMethod = News.class.getMethod(imageSetter, String.class);

            setTitleMethod.invoke(news, title);
            setTextMethod.invoke(news, text);

            if (image != null && !image.isEmpty()) {
                String fileName = saveFile(image);
                setImageMethod.invoke(news, fileName);
            }

            // Save the updated news object
            newsService.saveContent(news);

            return "Content updated successfully for " + subcategory;
        } catch (Exception e) {
            return "Error updating content: " + e.getMessage();
        }
    }

    @GetMapping("/get-content")
    public Map<String, Object> getContent(
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subcategory) {

        // Fetch the latest news content by category
        News news = newsService.getLatestContentByCategory(category);

        System.out.println(category + " " + subcategory);

        if (news == null) {
            return Map.of("error", "No news available");
        }

        try {
            // Dynamically get the title, text, and image fields using reflection
            String titleGetter = "get" + subcategory.substring(0, 1).toUpperCase() + subcategory.substring(1) + "Title";
            String textGetter = "get" + subcategory.substring(0, 1).toUpperCase() + subcategory.substring(1) + "Text";
            String imageGetter = "get" + subcategory.substring(0, 1).toUpperCase() + subcategory.substring(1) + "Image";

            Method getTitleMethod = News.class.getMethod(titleGetter);
            Method getTextMethod = News.class.getMethod(textGetter);
            Method getImageMethod = News.class.getMethod(imageGetter);

            String title = (String) getTitleMethod.invoke(news);
            String text = (String) getTextMethod.invoke(news);
            String image = (String) getImageMethod.invoke(news);

            // Return the retrieved data as a map
            return Map.of(
                    "title", title,
                    "text", text,
                    "image", image);
        } catch (Exception e) {
            return Map.of("error", "Error retrieving content: " + e.getMessage());
            // return "asdjhkasjd";
        }
    }

    @GetMapping("/get-news-content")
    public Map<String, Object> getMethodName(@RequestParam("category") String category,
            @RequestParam("subcategory") String subcategory) {
        String titleGetter = "getTitle" + subcategory;
        String textGetter = "getText" + subcategory;
        String imageGetter = "getImage" + subcategory;
        News news = newsService.getLatestContentByCategory(category);

        if (news == null) {
            return Map.of("error", "No news available");
        }

        try {
            Method getTitleMethod = News.class.getMethod(titleGetter);
            Method getTextMethod = News.class.getMethod(textGetter);
            Method getImageMethod = News.class.getMethod(imageGetter);

            String title = (String) getTitleMethod.invoke(news);
            String text = (String) getTextMethod.invoke(news);
            String image = (String) getImageMethod.invoke(news);
            return Map.of(
                    "title", title,
                    "text", text,
                    "image", image);

        } catch (Exception e) {
            // TODO: handle exception
            return Map.of("error", "Error retrieving content: " + e.getMessage());

        }

    }



    @PostMapping(value = "/update-news-content", consumes = { "multipart/form-data" })
    public Map<String, Object> postContent(
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subcategory,
            @RequestParam("title") String title,
            @RequestParam("text") String text,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        // Retrieve the latest news content for the given category
        News news = newsService.getLatestContentByCategory(category);
    
        // If no existing content is found, create a new News object
        if (news == null) {
            news = new News();
        }
    
        try {
            // Dynamically construct method names based on the subcategory
            String titleSetter = "setTitle" + subcategory;
            String textSetter = "setText" + subcategory;
            String imageSetter = "setImage" + subcategory;
    
            // Retrieve setter methods with correct parameter types
            Method setTitleMethod = News.class.getMethod(titleSetter, String.class);
            Method setTextMethod = News.class.getMethod(textSetter, String.class);
            Method setImageMethod = News.class.getMethod(imageSetter, String.class);
    
            // Set the new title and text values using the setter methods
            setTitleMethod.invoke(news, title);
            setTextMethod.invoke(news, text);
    
            // Handle image upload if an image is provided
            if (image != null && !image.isEmpty()) {
                String fileName = saveFile(image); // Save the uploaded file and get its name
                setImageMethod.invoke(news, fileName); // Set the image file name
            }
    
            // Save the updated news content
            newsService.saveContent(news);
    
            // Return the updated data as a map
            return Map.of(
                    "title", title,
                    "text", text,
                    "image", image != null ? image.getOriginalFilename() : null
            );
        } catch (NoSuchMethodException e) {
            // Handle cases where the subcategory is invalid or does not exist
            return Map.of("error", "Invalid subcategory: " + subcategory);
        } catch (Exception e) {
            // Handle other exceptions (e.g., file upload errors, database errors)
            return Map.of("error", "Error updating content: " + e.getMessage());
        }
    }



    @GetMapping("/get-right-images")
    public Map<String, Object> getRightImages(@RequestParam("category") String category) {
        // Retrieve the latest news content for the given category
        News news = newsService.getLatestContentByCategory(category);
    
        if (news == null) {
            return Map.of("error", "No news available for the selected category");
        }
    
        // Return the right images
        return Map.of(
                "rightImage1", news.getRightImage1() != null ? news.getRightImage1() : "",
                "rightImage2", news.getRightImage2() != null ? news.getRightImage2() : ""
        );
    }
    @PostMapping(value = "/update-right-images", consumes = { "multipart/form-data" })
    public Map<String, Object> updateRightImages(
            @RequestParam("category") String category,
            @RequestParam("rightImage1") MultipartFile rightImage1,
            @RequestParam("rightImage2") MultipartFile rightImage2,
            @RequestParam(value = "rightImage1Link", required = false) String rightImage1Link,
            @RequestParam(value = "rightImage2Link", required = false) String rightImage2Link
    ) {
        // Retrieve the latest news content for the given category
        News news = newsService.getLatestContentByCategory(category);
        // If no existing content is found, create a new News object
        if (news == null) {
            news = new News();
            news.setCategory(category); // Set the category for the new News object
        }
        try {
            // Save the uploaded files and update the News object
            if (rightImage1 != null && !rightImage1.isEmpty()) {
                String fileName1 = saveFile(rightImage1); // Save the uploaded file and get its name
                news.setRightImage1(fileName1); // Set the image file name
            }
            if (rightImage2 != null && !rightImage2.isEmpty()) {
                String fileName2 = saveFile(rightImage2); // Save the uploaded file and get its name
                news.setRightImage2(fileName2); // Set the image file name
            }
            // Update the links
            if (rightImage1Link != null && !rightImage1Link.isEmpty()) {
                news.setRightImage1Link(rightImage1Link);
            }
            if (rightImage2Link != null && !rightImage2Link.isEmpty()) {
                news.setRightImage2Link(rightImage2Link);
            }
            // Save the updated news content
            newsService.saveContent(news);
            // Return the updated data as a map
            return Map.of(
                    "rightImage1", rightImage1 != null ? rightImage1.getOriginalFilename() : null,
                    "rightImage2", rightImage2 != null ? rightImage2.getOriginalFilename() : null,
                    "rightImage1Link", rightImage1Link,
                    "rightImage2Link", rightImage2Link
            );
        } catch (Exception e) {
            return Map.of("error", "Error updating content: " + e.getMessage());
        }
    }
}