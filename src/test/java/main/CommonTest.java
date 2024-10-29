package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommonsTest {

    @Test
    @DisplayName("Debería tener el ancho del tablero correcto")
    void shouldHaveCorrectBoardWidth() {
        assertEquals(358, Commons.BOARD_WIDTH);
    }

    @Test
    @DisplayName("Debería tener la altura del tablero correcta")
    void shouldHaveCorrectBoardHeight() {
        assertEquals(350, Commons.BOARD_HEIGHT);
    }

    @Test
    @DisplayName("Debería tener el borde derecho correcto")
    void shouldHaveCorrectBorderRight() {
        assertEquals(30, Commons.BORDER_RIGHT);
    }

    @Test
    @DisplayName("Debería tener el borde izquierdo correcto")
    void shouldHaveCorrectBorderLeft() {
        assertEquals(5, Commons.BORDER_LEFT);
    }

    @Test
    @DisplayName("Debería tener la posición del suelo correcta")
    void shouldHaveCorrectGroundPosition() {
        assertEquals(290, Commons.GROUND);
    }

    @Test
    @DisplayName("Debería tener la altura de la bomba correcta")
    void shouldHaveCorrectBombHeight() {
        assertEquals(5, Commons.BOMB_HEIGHT);
    }

    @Test
    @DisplayName("Debería tener la altura del alien correcto")
    void shouldHaveCorrectAlienHeight() {
        assertEquals(12, Commons.ALIEN_HEIGHT);
    }

    @Test
    @DisplayName("Debería tener el ancho del alien correcto")
    void shouldHaveCorrectAlienWidth() {
        assertEquals(12, Commons.ALIEN_WIDTH);
    }

    @Test
    @DisplayName("Debería tener la posición inicial X del alien correcta")
    void shouldHaveCorrectAlienInitX() {
        assertEquals(150, Commons.ALIEN_INIT_X);
    }

    @Test
    @DisplayName("Debería tener la posición inicial Y del alien correcta")
    void shouldHaveCorrectAlienInitY() {
        assertEquals(5, Commons.ALIEN_INIT_Y);
    }

    @Test
    @DisplayName("Debería tener el valor de bajar correcto")
    void shouldHaveCorrectGoDownValue() {
        assertEquals(15, Commons.GO_DOWN);
    }

    @Test
    @DisplayName("Debería tener el número de aliens a destruir correcto")
    void shouldHaveCorrectNumberOfAliensToDestroy() {
        assertEquals(24, Commons.NUMBER_OF_ALIENS_TO_DESTROY);
    }

    @Test
    @DisplayName("Debería tener el valor de chance correcto")
    void shouldHaveCorrectChanceValue() {
        assertEquals(5, Commons.CHANCE);
    }

    @Test
    @DisplayName("Debería tener el valor de delay correcto")
    void shouldHaveCorrectDelayValue() {
        assertEquals(17, Commons.DELAY);
    }

    @Test
    @DisplayName("Debería tener el ancho del jugador correcto")
    void shouldHaveCorrectPlayerWidth() {
        assertEquals(15, Commons.PLAYER_WIDTH);
    }

    @Test
    @DisplayName("Debería tener la altura del jugador correcta")
    void shouldHaveCorrectPlayerHeight() {
        assertEquals(10, Commons.PLAYER_HEIGHT);
    }
}