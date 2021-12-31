package com.danielbpool.heroes.controllers;

import com.danielbpool.heroes.model.Hero;
import com.danielbpool.heroes.services.HeroService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeroController {
    private final HeroService heroService;
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/heroes")
    List<Hero> listHeroes() {
        return heroService.findHeroes();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/hero")
    Hero getHero(@RequestParam int id) {
        return heroService.findHero(id);
    }
}
