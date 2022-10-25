package tacos.dto;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import tacos.assemblers.IngredientModelAssembler;
import tacos.entities.Taco;

import java.util.Date;

@Relation(value="taco", collectionRelation="tacos")
public class TacoModel extends RepresentationModel<TacoModel> {

    private static final IngredientModelAssembler ingredientAssembler = new IngredientModelAssembler();
    @Getter
    private final String name;
    @Getter
    private final Date createdAt;

    @Getter
    private final Date updatedAt;
    @Getter
    private final CollectionModel<IngredientModel> ingredients;

    public TacoModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.updatedAt = taco.getUpdatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}
