package tacos.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.controllers.api.ApiDesignTacoController;
import tacos.entities.Taco;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }

    @Bean
    public RepresentationModelProcessor<RepositoryLinksResource> tacoProcessor(EntityLinks links) {
        return model -> {
            model.add(
                    links.linkFor(Taco.class)
                            .slash("recent")
                            .withRel("recents"));
            return model;
        };
    }




}
