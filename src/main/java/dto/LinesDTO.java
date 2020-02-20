package dto;

import entities.Lines;

/**
 *
 * @author Malthe
 */
public class LinesDTO {
    private String line;
    
    
    public LinesDTO(Lines lines) {
        this.line = lines.getLine();
    }

    @Override
    public String toString() {
        return "LinesDTO{" + "line=" + line + '}';
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    
}
