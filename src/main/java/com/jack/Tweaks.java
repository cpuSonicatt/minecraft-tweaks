package com.jack;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Tweaks extends JavaPlugin {

    public void onEnable() {

        getServer().getLogger().info("--- TWEAKS LOADING ---");
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            List<CustomRecipe> recipes = mapper.readValue(this.getResource("recipes.json"), new TypeReference<List<CustomRecipe>>() {});
            for (CustomRecipe recipe : recipes) {
                getServer().addRecipe(newRecipe(recipe));
                getServer().getLogger().info(String.format("Loaded %s", recipe.getKey()));
            }
        } catch (Exception ex) {
            getServer().getLogger().info(String.format("Cannot load recipes.json: %s", ex.getMessage()));
        }

        getServer().getLogger().info("--- TWEAKS LOADED ---");

    }


    public void onDisable() {

    }

    private Recipe newRecipe(CustomRecipe customRecipe) {
        NamespacedKey ntKey = new NamespacedKey(this, customRecipe.getKey());
        ItemStack nameTag = new ItemStack(customRecipe.getProduces().getMaterial(), customRecipe.getProduces().getAmount());

        ShapedRecipe recipe = new ShapedRecipe(ntKey, nameTag);
        recipe.shape(customRecipe.getInstruction());
        for (CustomIngredient ingredient : customRecipe.getIngredients()) {
            recipe.setIngredient(ingredient.getAssociation().charAt(0), ingredient.getMaterial());
        }

        return recipe;
    }
}
