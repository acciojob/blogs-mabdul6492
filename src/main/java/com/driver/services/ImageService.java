package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if(!blogOptional.isPresent()) return null;

        Blog blog = blogOptional.get();
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Optional<Image> imageOptional = imageRepository.findById(id);
        if(!imageOptional.isPresent()) return 0;

        Image image = imageOptional.get();
        String[] screenXY = screenDimensions.split("X");
        String imageDimensions = image.getDimensions();
        String[] imageXY = imageDimensions.split("X");

        int x = Integer.parseInt(screenXY[0])/ Integer.parseInt(imageXY[0]);
        int y = Integer.parseInt(screenXY[1])/ Integer.parseInt(imageXY[1]);

        return x*y;
    }
}
