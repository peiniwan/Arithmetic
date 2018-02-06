package arithmetic.ly.com.arithmetic.file;

import java.io.File;

/**
 * Created by liuyu1 on 2018/2/6.
 */

public class FileTest {
    public static void main(String[] args) {
        File dir = new File("e:\\demodir");
        // dir.delete();
        removeDir(dir);
//        showDir(dir);
    }

    public static void removeDir(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                removeDir(file);
            } else {
                System.out.println(file + ":" + file.delete());
            }
        }
        System.out.println(dir + ":" + dir.delete());
    }

    public static void showDir(File dir) {
        System.out.println(dir);
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                showDir(f);
            } else {
                System.out.println(f);
            }
        }
    }


}
