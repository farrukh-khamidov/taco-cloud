package tacos.entities;

import lombok.*;
import tacos.constants.IngredientType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Ingredient implements Serializable {
    @Id
    private String id;
    private String name;
    private IngredientType type;
}
