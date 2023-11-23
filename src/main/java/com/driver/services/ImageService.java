package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Optional<Blog> blogOptional = blogRepository2.findById(blogId);
        if(blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            image.setBlog(blog);
            blog.getImageList().add(image);
            blogRepository2.save(blog);
            return image;
        }
        return null;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count = -1;
        Optional<Image> imageOptional = imageRepository2.findById(id);
        if(imageOptional.isPresent()) {
            String imageDimension = imageOptional.get().getDimensions();
            String[] dimensionParts = imageDimension.split("X");
            int inLen = Integer.parseInt(dimensionParts[0]);
            int inWid = Integer.parseInt(dimensionParts[1]);

            dimensionParts = screenDimensions.split("X");
            int outLen = Integer.parseInt(dimensionParts[0]);
            int outWid = Integer.parseInt(dimensionParts[1]);

            int c = (outLen/inLen) * (outWid/inWid);
            count = c;
        }
        return count;
    }
}
