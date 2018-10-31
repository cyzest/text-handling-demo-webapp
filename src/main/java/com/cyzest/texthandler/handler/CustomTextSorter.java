package com.cyzest.texthandler.handler;

import java.util.Comparator;

/**
 * 커스텀 정렬 규칙
 * <p>
 * step 1) 영문자와 숫자가 먼저나온다.
 * step 2) 영문자는 대문자와 소문자별로 각각 오름차순으로 정렬하며 영문순으로 표현한다.
 * step 3) 정렬된 영문자는 오름차순인 숫자와 영문자->숫자 순으로 한글자자씩 교차하여 표현한다.
 * step 4) 나머지 문자들은 오름차순하여 마지막에 표현한다.
 * <p>
 * ex) !#0abA798Zc -> A0a7b8c9Z!#
 */
public class CustomTextSorter implements TextSorter {

    private final CustomAlphabetComparator customAlphabetComparator = new CustomAlphabetComparator();

    @Override
    public String sort(String text) {

        String soredText = null;

        if (isNotEmpty(text)) {

            soredText = "";

            StringBuilder alphabetGroup = new StringBuilder();
            StringBuilder numberGroup = new StringBuilder();
            StringBuilder otherGroup = new StringBuilder();

            int textLength = text.length();

            for (int i = 0; i < textLength; i++) {

                char character = text.charAt(i);

                if (isAlphabet(character)) {
                    alphabetGroup.append(character);
                } else if (isNumber(character)) {
                    numberGroup.append(character);
                } else {
                    otherGroup.append(character);
                }
            }

            String sortedAlphabet = getStringBuilderToSortedString(alphabetGroup);
            String sortedNumber = getStringBuilderToSortedString(numberGroup);
            String sortedOther = getStringBuilderToSortedString(otherGroup);

            String mergedString = getCrossMergeString(sortedAlphabet, sortedNumber);

            if (isNotEmpty(mergedString)) {
                soredText += mergedString;
            }

            if (isNotEmpty(sortedOther)) {
                soredText += sortedOther;
            }
        }

        return soredText;
    }

    private boolean isAlphabet(char character) {
        return (character >= 65 && character <= 90) || (character >= 97 && character <= 122);
    }

    private boolean isNumber(char character) {
        return character >= 48 && character <= 57;
    }

    private String getStringBuilderToSortedString(StringBuilder stringBuilder) {
        String sortedString = null;
        if (stringBuilder.length() > 0) {
            sortedString = stringBuilder.chars()
                    .mapToObj(c -> (char) c)
                    .sorted(customAlphabetComparator)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }
        return sortedString;
    }

    private String getCrossMergeString(String first, String second) {

        String mergeString = null;

        StringBuilder stringBuilder = new StringBuilder();

        int firstLength = getLength(first);
        int secondLength = getLength(second);
        int maxLength = Math.max(firstLength, secondLength);

        for (int i = 0; i < maxLength; i++) {
            if (i < firstLength) {
                stringBuilder.append(first.charAt(i));
            }
            if (i < secondLength) {
                stringBuilder.append(second.charAt(i));
            }
        }

        if (stringBuilder.length() > 0) {
            mergeString = stringBuilder.toString();
        }

        return mergeString;
    }

    private int getLength(String str) {
        int length = 0;
        if (isNotEmpty(str)) {
            length = str.length();
        }
        return length;
    }

    private boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    private class CustomAlphabetComparator implements Comparator<Character> {
        @Override
        public int compare(Character c1, Character c2) {
            if (c1 != c2) {
                char upperChar1 = Character.toUpperCase(c1);
                char upperChar2 = Character.toUpperCase(c2);
                if (upperChar1 != upperChar2) {
                    return upperChar1 - upperChar2;
                }
            }
            return c1 - c2;
        }
    }
}
