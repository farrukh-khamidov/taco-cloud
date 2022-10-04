package tacos.models;

import lombok.Data;
import tacos.constants.IngredientType;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final IngredientType type;
}
