package tacos.controllers.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.controllers.DesignTacoController;
import tacos.entities.Taco;
import tacos.repositories.TacoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/design", produces="application/json")
//@CrossOrigin(origins="*")
public class ApiDesignTacoController {

    private TacoRepository tacoRepo;

//    @Autowired
//    EntityLinks entityLinks;

    public ApiDesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }
    @GetMapping("/recent")
    public  CollectionModel<EntityModel<Taco>> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());


        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        CollectionModel<EntityModel<Taco>> recentResources = CollectionModel.wrap(tacos);
        recentResources.add(WebMvcLinkBuilder.linkTo(DesignTacoController.class)
                .slash("recent")
                .withRel("recents"));

        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }


}
