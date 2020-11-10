package lesson5;

import java.io.File;

public class ShowFiles {

  public static void main(String[] args) {
    showFiles("", new File("C:\\newprojects\\algorithms\\src"));
  }

  private static void showFiles(String prfx, File root) {

    if (root.isFile()) {
      System.out.println(prfx + "File: " + root.getName());
    }
    if (root.isDirectory()) {
      System.out.println(prfx + "Dir: " + root.getName());
      File[] files = root.listFiles();
      assert files != null;
      for (File file : files) {
        showFiles(prfx + "  ", file);
      }
    }
  }
}
