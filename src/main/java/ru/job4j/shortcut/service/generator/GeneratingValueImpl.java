package ru.job4j.shortcut.service.generator;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class GeneratingValueImpl implements GenaratingValue {
    private String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String lower = upper.toLowerCase();
    private String number = "0123456789";
    private String values = upper + lower + number;
    private Random random = new Random();

    @Override
    public String generate(int size) {
        StringBuilder result = new StringBuilder();
        int valuesLength = values.length();
        for (int i = 0; i < size; i++) {
            int pos = random.nextInt(valuesLength);
            result.append(values.charAt(pos));
        }
        return result.toString();
    }
}
