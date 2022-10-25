package tacos.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.controllers.api.ApiIngredientController;
import tacos.dto.IngredientModel;
import tacos.entities.Ingredient;

public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {
    public IngredientModelAssembler() {
        super(ApiIngredientController.class, IngredientModel.class);
    }

    @Override
    protected IngredientModel instantiateModel(Ingredient entity) {
        return new IngredientModel(entity);
    }

    @Override
    public IngredientModel toModel(Ingredient entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
