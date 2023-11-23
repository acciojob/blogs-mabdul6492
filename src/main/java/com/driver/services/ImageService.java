package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    BlogRepository blogRepository;
    ImageRepository imageRepository;

    public ImageService(BlogRepository blogRepository, ImageRepository imageRepository) {
        this.blogRepository = blogRepository;
        this.imageRepository = imageRepository;
    }

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository.findById(blogId).orElse(null);
        if(blog == null) return image;

        blog.getImageList().add(image);
        blogRepository.save(blog);
        image.setBlog(blog);
        imageRepository.save(image);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository.findById(id).orElse(null);
        if(image == null) return 0;

        String[] screenXY = screenDimensions.split("X");
        String imageDimensions = image.getDimensions();
        String[] imageXY = imageDimensions.split("X");

        int x = Integer.parseInt(screenXY[0])/ Integer.parseInt(imageXY[0]);
        int y = Integer.parseInt(screenXY[1])/ Integer.parseInt(imageXY[1]);

        return x*y;
    }
}
