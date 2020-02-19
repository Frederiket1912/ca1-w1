package dto;

import entities.Lines;

/**
 *
 * @author Malthe
 */
public class LinesDTO {
    private int id;
    private String line;
    
    
    public LinesDTO(Lines lines) {
        this.id = lines.getId();
        this.line = lines.getLine();
    }

    @Override
    public String toString() {
        return "LinesDTO{" + "id=" + id + ", line=" + line + '}';
    }
    
}
