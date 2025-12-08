package xyz.foulds.aoc.year25.day08;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
class MainTest {

    @Test
    public void inputTest() throws IOException
    {
        Main.main(new String[]{"src/test/resources/inputTest.txt"});
    }

    @Test
    public void input() throws IOException
    {
        Main.main(new String[]{"src/test/resources/input.txt"});
    }
}