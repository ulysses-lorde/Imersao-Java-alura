package br.com.example.games.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "melhoresGames")
public class Game {

    @Id
    private String id;
    private String title;
    private String image;
    private String protagonist;

    public Game() {
        
    }
    
    public Game(String title, String image, String protagonist) {
        this.title = title;
        this.image = image;
        this.protagonist = protagonist;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public String getProtagonist() {
        return protagonist;
    }
    

    
}
