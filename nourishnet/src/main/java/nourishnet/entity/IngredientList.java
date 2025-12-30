package nourishnet.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("INGREDIENT_LIST")
public class IngredientList extends Content {
    
    @Column(columnDefinition = "TEXT")
    private String ingredients; // JSON string or comma-separated list
    
    @Column(name = "diet_approved")
    private Boolean dietApproved;
    
    @Column(name = "dietary_notes", length = 500)
    private String dietaryNotes;
    
    @Column(name = "shopping_tips", length = 500)
    private String shoppingTips;
    
    // Constructors
    public IngredientList() {
        super();
        this.dietApproved = true;
    }
    
    public IngredientList(Long dietId, Long cuisineId, String title) {
        super(dietId, cuisineId, title);
        this.dietApproved = true;
    }
    
    @Override
    public String getContentType() {
        return "INGREDIENT_LIST";
    }
    
    // Getters and Setters
    public String getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    
    public Boolean getDietApproved() {
        return dietApproved;
    }
    
    public void setDietApproved(Boolean dietApproved) {
        this.dietApproved = dietApproved;
    }
    
    public String getDietaryNotes() {
        return dietaryNotes;
    }
    
    public void setDietaryNotes(String dietaryNotes) {
        this.dietaryNotes = dietaryNotes;
    }
    
    public String getShoppingTips() {
        return shoppingTips;
    }
    
    public void setShoppingTips(String shoppingTips) {
        this.shoppingTips = shoppingTips;
    }
}
