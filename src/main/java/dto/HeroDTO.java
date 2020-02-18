
package dto;
import entities.Hero;

public class HeroDTO {
    private int id;
    private String heroName;
    private int intelligence;
    private int strength;
    private int speed;
    private int durability;
    private int power;
    private int combat;
    private String publisher;
    
    
    public HeroDTO(Hero hero) {
        this.id = hero.getId();
        this.heroName = hero.getHeroName();
        this.intelligence = hero.getIntelligence();
        this.strength = hero.getStrength();
        this.speed = hero.getSpeed();
        this.durability = hero.getDurability();
        this.power = hero.getPower();
        this.combat = hero.getCombat();
        this.publisher = hero.getPublisher();
    }

    public int getId() {
        return id;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDurability() {
        return durability;
    }

    public int getPower() {
        return power;
    }

    public int getCombat() {
        return combat;
    }

    public String getPublisher() {
        return publisher;
    }
}
