package com.jarry.demo1.utils.FileUtils;

import java.io.File;
import java.io.FileFilter;

/**
 * 递归删除文件夹所有文件；
 * 可以修改为对文件夹下文件过滤处理、图片压缩等。
 *
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-30 11:48
 */
public class DeleteFile {
    public static void delFolder(String folerPath) {
        delAllFile(folerPath);
        String filePath = folerPath;
//        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        myFilePath.delete();
    }

    private static boolean delAllFile(String folerPath) {
        boolean f = false;
        File file = new File(folerPath);
        if (!file.exists()) return f;
        if (!file.isDirectory()) return f;
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (folerPath.endsWith(File.separator)) temp = new File(folerPath + tempList[i]);
            else temp = new File(folerPath + File.separator + tempList[i]);
            if (temp.isFile()) temp.delete();
            if (temp.isDirectory()) {
                //递归删除子文件夹以及文件
                //delAllFile(folerPath+File.separator+tempList[i]);
                delFolder(folerPath + File.separator + tempList[i]);
                f = true;
            }
        }
        return f;
    }

    //简单递归删除所有文件及文件夹
    public static void deleteFileAll(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteFileAll(files[i]);
                } else files[i].delete();
            }
            file.delete();
        }
    }

}
