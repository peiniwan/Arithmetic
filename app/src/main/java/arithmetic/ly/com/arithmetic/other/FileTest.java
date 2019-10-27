package arithmetic.ly.com.arithmetic.other;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyu1 on 2018/2/6.
 */

public class FileTest {
    public static void main(String[] args) {
//        File dir = new File("e:\\demodir");
//        // dir.delete();
//        removeDir(dir);
////        showDir(dir);

        File dir = new File("e:\\java0331");
        FilenameFilter filter = new FilenameFilter() {// 过滤器，匿名内部类
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        };
        List<File> list = new ArrayList<File>();
        getFiles(dir, filter, list);
        File destFile = new File(dir, "javalist.txt");// 目的文件，当前文件
        try {
            write2File(list, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * 对指定目录中的内容进行深度遍历，并按照指定过滤器，进行过滤， 将过滤后的内容存储到指定容器List中。 File
     * dir,FilenameFilter filter,List<File> list一个目录，文件名过滤，存储的容器
     */
    public static void getFiles(File dir, FilenameFilter filter, List<File> list) {
        File[] files = dir.listFiles();
        // 深度遍历
        for (File file : files) {
            if (file.isDirectory()) {
                // 递归
                getFiles(file, filter, list);
            } else {
                // 对遍历到的文件进行过滤器的过滤。将符合条件File对象，存储到List集合中。
                if (filter.accept(dir, file.getName())) {
                    list.add(file);
                }
            }
        }
    }

    // 将集合写到文件去
    public static void write2File(List<File> list, File destFile)
            throws IOException {

        BufferedWriter bufw = null;
        try {
            bufw = new BufferedWriter(new FileWriter(destFile));
            for (File file : list) {
                bufw.write(file.getAbsolutePath());
                bufw.newLine();
                bufw.flush();
            }
        } catch (IOException e) {//可以写可以不写
            throw new RuntimeException("写入失败");
        } finally {
            if (bufw != null)
                try {
                    bufw.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败");
                }
        }
    }


}
