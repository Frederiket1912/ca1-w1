package dto;

import entities.Jokes;

/**
 *
 * @author Malthe
 */
public class JokesDTO {
    private String joke;
    
    
    public JokesDTO(Jokes jokes) {
        this.joke = jokes.getJoke();
    }

    @Override
    public String toString() {
        return "JokesDTO{" + "joke=" + joke + '}';
    }

    public String getLine() {
        return joke;
    }

    public void setLine(String joke) {
        this.joke = joke;
    }
    
}
