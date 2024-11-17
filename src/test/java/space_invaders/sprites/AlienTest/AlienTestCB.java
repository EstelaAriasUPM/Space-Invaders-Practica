package space_invaders.sprites.AlienTest;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Commons;
import space_invaders.sprites.Alien;

public class AlienTestCB {
    @Test
    @DisplayName("initAlien CB")
    public void testAlienInitialization() {
        new Alien(50, 100);
        new Alien(-10, -20);
        new Alien(-10, 20);
        new Alien(10, -20);
        new Alien(Commons.BOARD_WIDTH + 10, Commons.BOARD_HEIGHT + 20);
        new Alien(10, Commons.BOARD_HEIGHT + 20);
        new Alien(Commons.BOARD_WIDTH + 10, 20);
        
    }
    
    @Test
    @DisplayName("Act CB")
    public void testAlienAct() {
        Alien alien = new Alien(50, 100);
        alien.act(10);
    }
    
    @Test
    @DisplayName("init bomb CB")
    public void testBombInitializationLower() {
        Alien alien = new Alien(-10, -20);
        alien.getBomb().isDestroyed();
    }
}
