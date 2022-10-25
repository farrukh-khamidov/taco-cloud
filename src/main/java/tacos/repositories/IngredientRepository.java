package tacos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
