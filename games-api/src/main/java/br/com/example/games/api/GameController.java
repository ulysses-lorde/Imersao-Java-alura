package br.com.example.games.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameRepository repositorio;

    @GetMapping("/games")
    public List<Game> obterGames() {
        List<Game> games = repositorio.findAll();
        return games;
    }

    @PostMapping("/games")
    public Game adicionarGame(@RequestBody Game game) {
        Game gameSalvo = repositorio.save(game);
        return gameSalvo;
        
    }
    
}
