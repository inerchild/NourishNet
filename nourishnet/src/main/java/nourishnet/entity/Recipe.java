package nourishnet.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RECIPE")
public class Recipe extends Content {
    
    @Column(columnDefinition = "TEXT")
    private String instructions;
    
    private Integer servings;
    
    @Column(name = "prep_time")
    private Integer prepTime; // in minutes
    
    @Column(name = "cook_time")
    private Integer cookTime; // in minutes
    
    private String difficulty; // e.g., "easy", "medium", "hard"
    
    @Column(name = "calories_per_serving")
    private Integer caloriesPerServing;
    
    // Constructors
    public Recipe() {
        super();
    }
    
    public Recipe(Long dietId, Long cuisineId, String title) {
        super(dietId, cuisineId, title);
    }
    
    @Override
    public String getContentType() {
        return "RECIPE";
    }
    
    // Getters and Setters
    public String getInstructions() {
        return instructions;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    public Integer getServings() {
        return servings;
    }
    
    public void setServings(Integer servings) {
        this.servings = servings;
    }
    
    public Integer getPrepTime() {
        return prepTime;
    }
    
    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }
    
    public Integer getCookTime() {
        return cookTime;
    }
    
    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public Integer getCaloriesPerServing() {
        return caloriesPerServing;
    }
    
    public void setCaloriesPerServing(Integer caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }
}
