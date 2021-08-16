package com.jack;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Tweaks extends JavaPlugin {

    public void onEnable() {

        getServer().getLogger().info("--- TWEAKS LOADING ---");

        Map<Character, Material> paper = new HashMap<Character, Material>();
        paper.put('~', Material.STRING);
        paper.put('#', Material.PAPER);

        Map<Character, Material> saddle = new HashMap<Character, Material>();
        saddle.put('L', Material.LEATHER);
        saddle.put('I', Material.IRON_INGOT);

        Map<Character, Material> leather = new HashMap<Character, Material>();
        leather.put('L', Material.ROTTEN_FLESH);

        Map<Character, Material> cobweb = new HashMap<Character, Material>();
        cobweb.put('~', Material.STRING);
        cobweb.put('o', Material.HONEY_BOTTLE);

        getServer().addRecipe(newRecipe("j_name_tag", new String[] {"~#"} , paper, Material.NAME_TAG, 1));
        getServer().addRecipe(newRecipe("j_saddle", new String[] {"LLL", "LIL"}, saddle, Material.SADDLE, 1));
        getServer().addRecipe(newRecipe("j_leather", new String[] {"LLL", "LLL", "LLL"}, leather, Material.LEATHER, 1));
        getServer().addRecipe(newRecipe("j_cobweb", new String[] {"~~~", "~o~", "~~~"}, cobweb, Material.COBWEB, 4));

        getServer().getLogger().info("--- TWEAKS LOADED ---");

    }


    public void onDisable() {

    }

    private Recipe newRecipe(String key, String[] shape, Map<Character, Material> ingredients,  Material result, int amount) {
        NamespacedKey ntKey = new NamespacedKey(this, key);
        ItemStack nameTag = new ItemStack(result, amount);

        ShapedRecipe recipe = new ShapedRecipe(ntKey, nameTag);
        recipe.shape(shape);
        for (Map.Entry<Character, Material> entry : ingredients.entrySet()) {
            recipe.setIngredient(entry.getKey(), entry.getValue());
        }

        return recipe;
    }
}
