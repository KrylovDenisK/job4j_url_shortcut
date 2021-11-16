package ru.job4j.shortcut.service.generator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class GeneratingValueImplTest {
    private GeneratingValueImpl generatingValue = new GeneratingValueImpl();
    @Test
    public void checkStringSizeAfterGenereted() {
        String string = generatingValue.generate(10);
        Assert.assertEquals(string.length(), 10);
    }
}