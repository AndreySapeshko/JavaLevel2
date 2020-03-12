import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src\\main\\resources\\Text.txt");
        byte[] buffer = new byte[fis.available()];
        int count = 0;
        String inputText = "";
        if (fis.available() > 0) {
            count = fis.read(buffer);
            inputText = new String(buffer);
        }
        //Массив с словами.
        String[] wordsOfText = inputText.split("[\t\n\r\f\u0085\u2028\u2029!-/]?\\s[!-/]?");
        //Список слов без повторов.
        ArrayList<String> setWords = setWordsFromArray(wordsOfText);

        for (int i = 0; i < setWords.size(); i++) {
            System.out.print(setWords.get(i) + "-" + i + " ");
            if ((i % 10 == 0 && i != 0) || i == setWords.size() - 1) {
                System.out.println();
            }
        }
        //Список слов с колличеством повторов.
        HashMap<String, Integer> replaysWords = countReplaysOfWords(wordsOfText);
        int i = 0;
        for (Map.Entry<String, Integer> pair : replaysWords.entrySet()) {
            System.out.println(i++ + " " + pair.getKey() + " - " + pair.getValue());
        }

        //PhoneBook
        PhoneBook myPhoneBook = new PhoneBook("myBook", "Owner");
        myPhoneBook.setContact("Ivanov", "Ivan", 543345, "543@email.com");
        myPhoneBook.setContact("Petrov", 55555);
        myPhoneBook.setContact("Sidorov", "555@email.com");
        myPhoneBook.setContact("Petrov", 55555);
        myPhoneBook.setContact("Ivanov", "546789", "567@email.com");
        myPhoneBook.getContact("Ivanov");
        myPhoneBook.getContact("Petrov");
        System.out.println(myPhoneBook.toString());
    }

    public static ArrayList<String> setWordsFromArray(String[] arr) {
        ArrayList<String> setWords = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!setWords.contains(arr[i])) {
                setWords.add(arr[i]);
            }
        }
        return setWords;
    }

    public static HashMap<String, Integer> countReplaysOfWords(String[] arr) {
        HashMap<String, Integer> replaysWords = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (replaysWords.containsKey(arr[i])) {
                replaysWords.put(arr[i], replaysWords.get(arr[i]) + 1);
            } else {
                replaysWords.put(arr[i], 1);
            }
        }
        return replaysWords;
    }
}
