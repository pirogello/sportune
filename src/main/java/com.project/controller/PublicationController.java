package com.project.controller;

import com.project.model.Publication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    @GetMapping("{publication}")
    public Publication getPublication(@PathVariable Publication publication){
        return publication;
    }
}
