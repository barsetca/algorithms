package lesson5;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Anagram {

  private final String word;
  private final char[] chars;
  private final Collection<String> anagrams = new LinkedHashSet<>();

  public Anagram(String word) {
    this.word = word;
    this.chars = word.toCharArray();

  }

  private void doAnagram(int length) {
    if (length <= 1) {
      return;
    }
    for (int i = 0; i < length; i++) {
      doAnagram(length - 1);
      anagrams.add(String.valueOf(chars));
      rotate(length);
    }
  }

  private void rotate(int length) {
    int index = chars.length - length;
    char temp = chars[index];
    for (int i = index + 1; i < chars.length; i++) {
      chars[i - 1] = chars[i];
    }
    chars[chars.length - 1] = temp;
  }

  public Collection<String> getAnagrams() {
    anagrams.clear();
    doAnagram(word.length());
    return anagrams;
  }

  public static void main(String[] args) {
    Anagram anagram = new Anagram("dog");
    anagram.getAnagrams().forEach(System.out::println);

  }
}
