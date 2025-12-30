package nourishnet.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VIDEO")
public class Video extends Content {
    //determines if url is required
    @Column(nullable = true)
    private String url;
    
    private String platform; // e.g., "youtube", "vimeo", "local"
    
    private Integer duration; // duration in seconds
    
    private String thumbnail;
    
    @Column(name = "video_id")
    private String videoId; // Platform-specific video ID
    
    // Constructors
    public Video() {
        super();
    }
    
    public Video(Long dietId, Long cuisineId, String title, String url) {
        super(dietId, cuisineId, title);
        this.url = url;
    }
    
    @Override
    public String getContentType() {
        return "VIDEO";
    }
    
    // Getters and Setters
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public String getThumbnail() {
        return thumbnail;
    }
    
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public String getVideoId() {
        return videoId;
    }
    
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
