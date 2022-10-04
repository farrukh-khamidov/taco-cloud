package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.constants.IngredientType;
import tacos.models.Ingredient;
import tacos.models.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@Slf4j
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model) {
        System.out.println("design ga keldi");
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
                new Ingredient("ASD", "SDFA ascfsafcas", IngredientType.WRAP),
                new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
                new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
                new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
                new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
                new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE)
        );
        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }


        model.addAttribute("taco", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType() == type).collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, Model model) {

        if (errors.hasErrors()) {
            List<Ingredient> ingredients = Arrays.asList(
                    new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
                    new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
                    new Ingredient("ASD", "SDFA ascfsafcas", IngredientType.WRAP),
                    new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
                    new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
                    new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
                    new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
                    new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
                    new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
                    new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
                    new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE)
            );
            IngredientType[] types = IngredientType.values();
            for (IngredientType type : types) {
                model.addAttribute(type.toString().toLowerCase(),
                        filterByType(ingredients, type));
            }
            return "design";
        }
        // Save the taco design...
        // We'll do this in chapter 3
        log.info("taco: " + taco);
        return "redirect:/orders/current";
    }
}
