package nourishnet.config;

import nourishnet.entity.*;
import nourishnet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    
    @Autowired
    private DietRepository dietRepository;
    
    @Autowired
    private CuisineRepository cuisineRepository;
    
    @Autowired
    private ContentRepository contentRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Seed Diets
        Diet vegetarian = new Diet("Vegetarian", "Plant-based diet excluding meat and fish");
        vegetarian.setGuidelines("Eat plenty of vegetables, fruits, legumes, and whole grains");
        vegetarian.setHealthBenefits("Lower risk of heart disease, improved digestion");
        vegetarian.setRestrictions("No meat, poultry, or fish");
        
        Diet pescatarian = new Diet("Pescatarian", "Vegetarian diet that includes fish and seafood");
        pescatarian.setGuidelines("Include fish 2-3 times per week, plenty of vegetables");
        pescatarian.setHealthBenefits("Omega-3 fatty acids, heart health, lean protein");
        pescatarian.setRestrictions("No meat or poultry");
        
        Diet keto = new Diet("Keto", "Very low-carb, high-fat diet");
        keto.setGuidelines("70% fat, 25% protein, 5% carbs");
        keto.setHealthBenefits("Weight loss, blood sugar control, mental clarity");
        keto.setRestrictions("Very limited carbs - no grains, sugar, most fruits");
        
        Diet alkaline = new Diet("Alkaline", "Diet focused on alkaline-forming foods");
        alkaline.setGuidelines("80% alkaline foods, 20% acidic foods");
        alkaline.setHealthBenefits("Reduced inflammation, improved energy, better pH balance");
        alkaline.setRestrictions("Limited meat, dairy, processed foods");
        
        dietRepository.save(vegetarian);
        dietRepository.save(pescatarian);
        dietRepository.save(keto);
        dietRepository.save(alkaline);
        
        // Seed Cuisines
        Cuisine indian = new Cuisine("Indian", "South Asia");
        indian.setDescription("Rich spices, curries, rice, and bread-based dishes");
        indian.setCharacteristics("Bold flavors, aromatic spices, vegetarian-friendly");
        
        Cuisine thai = new Cuisine("Thai", "Southeast Asia");
        thai.setDescription("Balance of sweet, sour, salty, and spicy flavors");
        thai.setCharacteristics("Fresh herbs, coconut milk, rice noodles");
        
        Cuisine japanese = new Cuisine("Japanese", "East Asia");
        japanese.setDescription("Fresh ingredients, minimal processing, umami flavors");
        japanese.setCharacteristics("Sushi, miso, soy sauce, seafood-based");
        
        Cuisine african = new Cuisine("African", "Africa");
        african.setDescription("Diverse regional dishes with bold spices and grains");
        african.setCharacteristics("Stews, grains, plantains, aromatic spices");
        
        cuisineRepository.save(indian);
        cuisineRepository.save(thai);
        cuisineRepository.save(japanese);
        cuisineRepository.save(african);
        
        // Seed Sample Content (32 items total - 2 per diet-cuisine combination)
        
        // Vegetarian + Indian
        Image vegIndianImage = new Image(vegetarian.getId(), indian.getId(), "Palak Paneer", "/images/palak-paneer.jpg");
        vegIndianImage.setDescription("Traditional Indian spinach and cottage cheese curry");
        vegIndianImage.setCaption("Creamy Palak Paneer");
        vegIndianImage.setAltText("Bowl of palak paneer garnished with cream");
        
        Video vegIndianVideo = new Video(vegetarian.getId(), indian.getId(), "How to Make Dal", "/videos/dal-tutorial.mp4");
        vegIndianVideo.setDescription("Step-by-step guide to making perfect lentil dal");
        vegIndianVideo.setPlatform("local");
        vegIndianVideo.setDuration(420);
        
        // Vegetarian + Thai
        Recipe vegThaiRecipe = new Recipe(vegetarian.getId(), thai.getId(), "Vegetable Pad Thai");
        vegThaiRecipe.setDescription("Classic Thai stir-fried rice noodles with vegetables");
        vegThaiRecipe.setInstructions("1. Soak rice noodles in warm water\n2. Stir-fry vegetables\n3. Add noodles and sauce\n4. Garnish with peanuts and lime");
        vegThaiRecipe.setServings(4);
        vegThaiRecipe.setDifficulty("medium");
        vegThaiRecipe.setPrepTime(15);
        vegThaiRecipe.setCookTime(20);
        
        IngredientList vegThaiIngredients = new IngredientList(vegetarian.getId(), thai.getId(), "Tom Yum Soup Ingredients");
        vegThaiIngredients.setDescription("Complete ingredient list for vegetarian Tom Yum soup");
        vegThaiIngredients.setIngredients("Lemongrass, galangal, kaffir lime leaves, mushrooms, tomatoes, Thai chili, vegetable broth, lime juice, coconut milk");
        vegThaiIngredients.setDietApproved(true);
        
        // Vegetarian + Japanese
        Image vegJapaneseImage = new Image(vegetarian.getId(), japanese.getId(), "Vegetable Sushi Rolls", "/images/veg-sushi.jpg");
        vegJapaneseImage.setDescription("Colorful vegetarian maki rolls");
        vegJapaneseImage.setCaption("Fresh Vegetable Sushi");
        
        Recipe vegJapaneseRecipe = new Recipe(vegetarian.getId(), japanese.getId(), "Miso Soup");
        vegJapaneseRecipe.setInstructions("1. Heat dashi broth\n2. Add miso paste\n3. Add tofu and wakame\n4. Garnish with green onions");
        vegJapaneseRecipe.setServings(2);
        vegJapaneseRecipe.setDifficulty("easy");
        
        // Vegetarian + African
        Video vegAfricanVideo = new Video(vegetarian.getId(), african.getId(), "Injera Bread Making", "/videos/injera.mp4");
        vegAfricanVideo.setDescription("Traditional Ethiopian flatbread tutorial");
        vegAfricanVideo.setDuration(600);
        
        IngredientList vegAfricanIngredients = new IngredientList(vegetarian.getId(), african.getId(), "Berbere Stew Ingredients");
        vegAfricanIngredients.setIngredients("Berbere spice mix, lentils, tomatoes, onions, garlic, vegetable broth");
        
        // Pescatarian + Indian
        Image pescIndianImage = new Image(pescatarian.getId(), indian.getId(), "Fish Curry", "/images/fish-curry.jpg");
        pescIndianImage.setDescription("Coastal Indian fish curry with coconut milk");
        
        Recipe pescIndianRecipe = new Recipe(pescatarian.getId(), indian.getId(), "Tandoori Fish");
        pescIndianRecipe.setInstructions("1. Marinate fish in yogurt and spices\n2. Grill until cooked through");
        pescIndianRecipe.setServings(4);
        
        // Pescatarian + Thai
        Video pescThaiVideo = new Video(pescatarian.getId(), thai.getId(), "Thai Fish Cakes", "/videos/fish-cakes.mp4");
        pescThaiVideo.setDuration(360);
        
        IngredientList pescThaiIngredients = new IngredientList(pescatarian.getId(), thai.getId(), "Green Curry Fish Ingredients");
        pescThaiIngredients.setIngredients("White fish, green curry paste, coconut milk, Thai basil, fish sauce");
        
        // Pescatarian + Japanese
        Image pescJapaneseImage = new Image(pescatarian.getId(), japanese.getId(), "Salmon Nigiri", "/images/salmon-nigiri.jpg");
        pescJapaneseImage.setDescription("Fresh salmon sushi");
        
        Video pescJapaneseVideo = new Video(pescatarian.getId(), japanese.getId(), "Sushi Rolling Tutorial", "/videos/sushi.mp4");
        pescJapaneseVideo.setDuration(480);
        
        // Pescatarian + African
        Recipe pescAfricanRecipe = new Recipe(pescatarian.getId(), african.getId(), "Grilled Tilapia");
        pescAfricanRecipe.setInstructions("1. Season tilapia with African spices\n2. Grill until flaky");
        
        IngredientList pescAfricanIngredients = new IngredientList(pescatarian.getId(), african.getId(), "Fish Stew Ingredients");
        pescAfricanIngredients.setIngredients("Tilapia, tomatoes, peppers, palm oil, onions, scotch bonnet");
        
        // Keto + Indian
        Image ketoIndianImage = new Image(keto.getId(), indian.getId(), "Keto Butter Chicken", "/images/keto-butter-chicken.jpg");
        ketoIndianImage.setDescription("Low-carb butter chicken without naan");
        
        IngredientList ketoIndianIngredients = new IngredientList(keto.getId(), indian.getId(), "Keto Curry Ingredients");
        ketoIndianIngredients.setIngredients("Chicken, heavy cream, ghee, curry spices, no sugar");
        
        // Keto + Thai
        Recipe ketoThaiRecipe = new Recipe(keto.getId(), thai.getId(), "Keto Tom Yum");
        ketoThaiRecipe.setInstructions("1. Make broth with lemongrass and galangal\n2. Add shrimp and vegetables\n3. Season without sugar");
        ketoThaiRecipe.setServings(2);
        
        Video ketoThaiVideo = new Video(keto.getId(), thai.getId(), "Low-Carb Thai Curry", "/videos/keto-curry.mp4");
        ketoThaiVideo.setDuration(300);
        
        // Keto + Japanese
        IngredientList ketoJapaneseIngredients = new IngredientList(keto.getId(), japanese.getId(), "Keto Sashimi Bowl Ingredients");
        ketoJapaneseIngredients.setIngredients("Salmon, tuna, avocado, seaweed, soy sauce, wasabi, no rice");
        
        Image ketoJapaneseImage = new Image(keto.getId(), japanese.getId(), "Cauliflower Sushi", "/images/cauliflower-sushi.jpg");
        ketoJapaneseImage.setDescription("Keto-friendly sushi with cauliflower rice");
        
        // Keto + African
        Video ketoAfricanVideo = new Video(keto.getId(), african.getId(), "Keto Suya Skewers", "/videos/suya.mp4");
        ketoAfricanVideo.setDuration(240);
        
        Recipe ketoAfricanRecipe = new Recipe(keto.getId(), african.getId(), "Grilled Meat with Peanut Sauce");
        ketoAfricanRecipe.setInstructions("1. Grill meat\n2. Serve with sugar-free peanut sauce");
        
        // Alkaline + Indian
        Recipe alkalineIndianRecipe = new Recipe(alkaline.getId(), indian.getId(), "Alkaline Vegetable Curry");
        alkalineIndianRecipe.setInstructions("1. Sauté alkaline vegetables\n2. Add mild spices\n3. Simmer in vegetable broth");
        
        Video alkalineIndianVideo = new Video(alkaline.getId(), indian.getId(), "Alkaline Indian Cooking", "/videos/alkaline-indian.mp4");
        alkalineIndianVideo.setDuration(420);
        
        // Alkaline + Thai
        Image alkalineThaiImage = new Image(alkaline.getId(), thai.getId(), "Alkaline Thai Salad", "/images/alkaline-salad.jpg");
        alkalineThaiImage.setDescription("Fresh alkaline vegetable salad with Thai herbs");
        
        IngredientList alkalineThaiIngredients = new IngredientList(alkaline.getId(), thai.getId(), "Alkaline Soup Ingredients");
        alkalineThaiIngredients.setIngredients("Coconut water, cucumber, mint, lime, basil, alkaline vegetables");
        
        // Alkaline + Japanese
        Video alkalineJapaneseVideo = new Video(alkaline.getId(), japanese.getId(), "Alkaline Miso Soup", "/videos/alkaline-miso.mp4");
        alkalineJapaneseVideo.setDuration(180);
        
        Recipe alkalineJapaneseRecipe = new Recipe(alkaline.getId(), japanese.getId(), "Seaweed Salad");
        alkalineJapaneseRecipe.setInstructions("1. Rehydrate wakame seaweed\n2. Mix with sesame oil and rice vinegar");
        
        // Alkaline + African
        IngredientList alkalineAfricanIngredients = new IngredientList(alkaline.getId(), african.getId(), "Alkaline Greens Ingredients");
        alkalineAfricanIngredients.setIngredients("Collard greens, kale, spinach, alkaline spices");
        
        Image alkalineAfricanImage = new Image(alkaline.getId(), african.getId(), "African Greens", "/images/african-greens.jpg");
        alkalineAfricanImage.setDescription("Traditional African leafy greens - alkaline-friendly");
        
        // Save all content
        contentRepository.save(vegIndianImage);
        contentRepository.save(vegIndianVideo);
        contentRepository.save(vegThaiRecipe);
        contentRepository.save(vegThaiIngredients);
        contentRepository.save(vegJapaneseImage);
        contentRepository.save(vegJapaneseRecipe);
        contentRepository.save(vegAfricanVideo);
        contentRepository.save(vegAfricanIngredients);
        
        contentRepository.save(pescIndianImage);
        contentRepository.save(pescIndianRecipe);
        contentRepository.save(pescThaiVideo);
        contentRepository.save(pescThaiIngredients);
        contentRepository.save(pescJapaneseImage);
        contentRepository.save(pescJapaneseVideo);
        contentRepository.save(pescAfricanRecipe);
        contentRepository.save(pescAfricanIngredients);
        
        contentRepository.save(ketoIndianImage);
        contentRepository.save(ketoIndianIngredients);
        contentRepository.save(ketoThaiRecipe);
        contentRepository.save(ketoThaiVideo);
        contentRepository.save(ketoJapaneseIngredients);
        contentRepository.save(ketoJapaneseImage);
        contentRepository.save(ketoAfricanVideo);
        contentRepository.save(ketoAfricanRecipe);
        
        contentRepository.save(alkalineIndianRecipe);
        contentRepository.save(alkalineIndianVideo);
        contentRepository.save(alkalineThaiImage);
        contentRepository.save(alkalineThaiIngredients);
        contentRepository.save(alkalineJapaneseVideo);
        contentRepository.save(alkalineJapaneseRecipe);
        contentRepository.save(alkalineAfricanIngredients);
        contentRepository.save(alkalineAfricanImage);
        
        System.out.println("✅ Database seeded with 4 diets, 4 cuisines, and 32 content items!");
    }
}
