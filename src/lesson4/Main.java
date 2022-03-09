package lesson4;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        printWordsStats(new String[]{"Собака", "Кошка", "Мышь", "Попугай", "Слон", "Жираф", "Кошка",
                "Шиншилла", "Мышь", "Крокодил", "Попугай", "Панда", "Кошка", "Шиншилла", "Страус", "Медведь"});

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Лапина", "99-66-33");
        phoneBook.add("Петров", "12-34-56");
        phoneBook.add("Михайлова", "23-45-56");
        phoneBook.add("Лебедев", "87-65-45");
        phoneBook.add("Соболев", "12-34-56");
        phoneBook.add("Константинова", "66-55-77");
        phoneBook.add("Лапина", "99-66-33");
        phoneBook.add("Лебедев", "98-76-54");

        printPhones(phoneBook, "Лапина");
        printPhones(phoneBook, "Лебедев");
        printPhones(phoneBook, "Петров");
        printPhones(phoneBook, "Константинова");
        printPhones(phoneBook, "Иванов");
    }

    private static void printPhones(PhoneBook phoneBook, String name) {
        Set<String> phones = phoneBook.getPhones(name);
        if (phones.isEmpty()) {
            System.out.println("Контакта '" + name + "' нет в телефонной книге");
        } else {
            System.out.println("Телефоны контакта '" + name + "': " + phones);
        }
    }

    private static void printWordsStats(String[] words) {
        if (words.length == 0) {
            System.out.println("Массив пустой");
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            Integer prevCount = wordCount.get(word);
            if (prevCount == null) {
                wordCount.put(word, 1);
            } else {
                wordCount.put(word, prevCount + 1);
            }
        }

        List<String> uniqueWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            if (count == 1) {
                uniqueWords.add(word);
            }
        }

        if (uniqueWords.isEmpty()) {
            System.out.println("Нет уникальных слов");
        } else {
            System.out.println("Уникальные слова: " + uniqueWords);
        }

        System.out.println("\nКоличество повторений:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            System.out.println(word + ": " + count);
        }
    }

}
