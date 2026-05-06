package com.TjnStory.demo.shared.util;

import java.text.Normalizer;

public class NameNormalizer {

    private NameNormalizer() {}

    public static String normalize(String input) {

        if (input == null) {
            return null;
        }

        String result = input.trim();

        result = result.replaceAll("\\s+", " ");

        result = Normalizer.normalize(result, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        result = result.toLowerCase();

        return result;

    }
}
