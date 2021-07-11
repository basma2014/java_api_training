package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class StartHandlerTest {
    @Test
    public void streamWorking() {
        try {
            String testString = "Testing here";
            InputStream stream = new ByteArrayInputStream(testString.getBytes());
            Assertions.assertEquals(testString, StartHandler.streamToString(stream));
        } catch (IOException e) {
            System.out.print("[*] " + e);
        }
    }

}
