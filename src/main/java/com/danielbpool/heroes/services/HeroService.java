package com.danielbpool.heroes.services;

import com.danielbpool.heroes.Repositories.HeroRepository;
import com.danielbpool.heroes.model.Hero;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    private final HeroRepository heroRepository;
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> findHeroes() {
        return this.heroRepository.fetchHeroes();
    }

    public Hero findHero(int id) {
        return this.heroRepository.fetchHero(id);
    }
}
