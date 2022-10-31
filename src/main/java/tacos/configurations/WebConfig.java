package tacos.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.entities.Taco;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("home");
        registry.addViewController("/signin");
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
