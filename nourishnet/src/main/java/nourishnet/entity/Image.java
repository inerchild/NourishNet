package nourishnet.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("IMAGE")
public class Image extends Content {
    //determines if url is required
    @Column(nullable = true)
    private String url;
    
    private String caption;
    
    @Column(name = "alt_text")
    private String altText;
    
    @Column(name = "image_type")
    private String imageType; // e.g., "plated", "cooking", "ingredients"
    
    // Constructors
    public Image() {
        super();
    }
    
    public Image(Long dietId, Long cuisineId, String title, String url) {
        super(dietId, cuisineId, title);
        this.url = url;
    }
    
    @Override
    public String getContentType() {
        return "IMAGE";
    }
    
    // Getters and Setters
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getCaption() {
        return caption;
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public String getAltText() {
        return altText;
    }
    
    public void setAltText(String altText) {
        this.altText = altText;
    }
    
    public String getImageType() {
        return imageType;
    }
    
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
