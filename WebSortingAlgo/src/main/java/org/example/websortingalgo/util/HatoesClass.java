package org.example.websortingalgo.util;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.example.websortingalgo.controller.Controller;
import org.example.websortingalgo.dto.SortingRequest;

import java.util.Map;

public class HatoesClass {

    // Reusable helper method to add links to any sorting response
    public static EntityModel<Map<String, Object>> addSortingLinks(
            Map<String, Object> response, SortingRequest sortingRequest, String algorithmType) {

        // Create the response model with the current response data
        EntityModel<Map<String, Object>> resource = EntityModel.of(response);

        // Add the self-link for the current sorting algorithm
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(Controller.class)
                        .sortData(sortingRequest))
                .withSelfRel().withTitle("Sort data with " + algorithmType);



        // Add the links to the response
        resource.add(selfLink);

        return resource;
    }
}

