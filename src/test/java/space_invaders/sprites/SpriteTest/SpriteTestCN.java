package space_invaders.sprites.SpriteTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import space_invaders.sprites.Sprite;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.ImageIcon;
import java.awt.Image;

public class SpriteTestCN {

    @Test
    @DisplayName("Debería inicializar un sprite como visible")
    public void testSpriteInitialization() {
        Sprite sprite = new Sprite();
        assertTrue(sprite.isVisible());
    }

    @Test
    @DisplayName("Debería cambiar la visibilidad del sprite a falso")
    public void testSpriteDie() {
        Sprite sprite = new Sprite();
        sprite.die();
        assertFalse(sprite.isVisible());
    }

    @Test
    @DisplayName("Debería cambiar la visibilidad del sprite al valor indicado")
    public void testSetVisible() {
        Sprite sprite = new Sprite();
        sprite.setVisible(false);
        assertFalse(sprite.isVisible());
        sprite.setVisible(true);
        assertTrue(sprite.isVisible());
    }

    @Test
    @DisplayName("Debería asignar y devolver la imagen del sprite")
    public void testSetAndGetImage() {
        Sprite sprite = new Sprite();
        Image image = new ImageIcon("src/main/resources/images/player.png").getImage();
        sprite.setImage(image);
        assertEquals(image, sprite.getImage());
    }

    @Test
    @DisplayName("Debería asignar y devolver la coordenada X del sprite")
    public void testSetAndGetX() {
        Sprite sprite = new Sprite();
        sprite.setX(100);
        assertEquals(100, sprite.getX());
    }

    @Test
    @DisplayName("Debería asignar y devolver la coordenada Y del sprite")
    public void testSetAndGetY() {
        Sprite sprite = new Sprite();
        sprite.setY(200);
        assertEquals(200, sprite.getY());
    }

    @Test
    @DisplayName("Debería asignar y devolver el estado de eliminación del sprite")
    public void testSetAndGetDying() {
        Sprite sprite = new Sprite();
        sprite.setDying(true);
        assertTrue(sprite.isDying());
        sprite.setDying(false);
        assertFalse(sprite.isDying());
    }
}