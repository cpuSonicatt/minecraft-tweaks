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

    private Recipe nameTagRecipe() {


        NamespacedKey ntKey = new NamespacedKey(this, "jack_name_tag");
        ItemStack nameTag = new ItemStack(Material.NAME_TAG, 1);

        ShapedRecipe nameTagRecipe = new ShapedRecipe(ntKey, nameTag);
        nameTagRecipe.shape("#~");
        nameTagRecipe.setIngredient('#', Material.PAPER);
        nameTagRecipe.setIngredient('~', Material.STRING);

        return nameTagRecipe;


    }

    private Recipe saddleRecipe() {


        NamespacedKey sKey = new NamespacedKey(this, "jack_saddle");
        ItemStack saddle = new ItemStack(Material.SADDLE, 1);

        ShapedRecipe saddleRecipe = new ShapedRecipe(sKey, saddle);
        saddleRecipe.shape("LLL", "LIL");
        saddleRecipe.setIngredient('L', Material.LEATHER);
        saddleRecipe.setIngredient('I', Material.IRON_INGOT);

        return saddleRecipe;


    }

    private Recipe leatherRecipe() {


        NamespacedKey lKey = new NamespacedKey(this, "jack_leather");
        ItemStack leather = new ItemStack(Material.LEATHER, 1);

        ShapedRecipe leatherRecipe = new ShapedRecipe(lKey, leather);
        leatherRecipe.shape("RRR", "RRR", "RRR");
        leatherRecipe.setIngredient('R', Material.ROTTEN_FLESH);

        return leatherRecipe;
    }

}
