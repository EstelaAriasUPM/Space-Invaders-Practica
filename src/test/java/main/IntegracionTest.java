package main;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import space_invaders.sprites.*;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;

public class IntegracionTest {

    @Mock
    private Alien alienMock;

    @Test
    public void testInitBomb(){
        when(alienMock.getX()).thenReturn(100);
        when(alienMock.getY()).thenReturn(100);
        
        Alien.Bomb bomb = alienMock.getBomb();
        
    }

}
