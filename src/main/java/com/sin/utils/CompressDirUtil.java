package com.sin.utils;


import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhoujie
 * @version V1.0
 * @ClassName: CompressDirUtil
 * @Description: 压缩文件工具类
 * @date 2019年5月31日 下午15:24:44
 */
public class CompressDirUtil {

    static String compresspath = "F:\\图片\\转换图片"; //需要压缩的文件夹的目录

    public static void main(String[] args) {
        boolean bl = compressFileToZip(compresspath); //压缩文件
        if (bl) {
            System.out.println("压缩成功");
        }
    }

    /**
     * @param @param compresspath 需要压缩的文件夹的目录
     * @return void    返回类型
     * @throws
     * @Title: compressAllFileZip
     * @Description: 传递文件路径压缩文件，传递文件夹路径压缩文件夹，注：空的文件夹不会出现在压缩包内
     */
    public static boolean compressFileToZip(String compresspath) {
        boolean bool = false;
        try {
            ZipOutputStream zipOutput = null;
            File file = new File(compresspath);
            if (file.isDirectory()) {
                zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compresspath + ".zip")));
                compressZip(zipOutput, file, ""); //递归压缩文件夹，最后一个参数传""压缩包就不会有当前文件夹；传file.getName(),则有当前文件夹;
            } else {
                zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compresspath.substring(0, compresspath.lastIndexOf(".")) + ".zip")));
                zipOFile(zipOutput, file); //压缩单个文件
            }
            zipOutput.closeEntry();
            zipOutput.close();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * @param @param  zipOutput
     * @param @param  file
     * @param @param  suffixpath
     * @param @throws IOException
     * @return void    返回类型
     * @throws
     * @Title: compressZip
     * @Description: 子文件夹中可能还有文件夹，进行递归
     */
    private static void compressZip(ZipOutputStream zipOutput, File file, String suffixpath) {
        File[] listFiles = file.listFiles();// 列出所有的文件
        for (File fi : listFiles) {
            if (fi.isDirectory()) {
                if (suffixpath.equals("")) {
                    compressZip(zipOutput, fi, fi.getName());
                } else {
                    compressZip(zipOutput, fi, suffixpath + File.separator + fi.getName());
                }
            } else {
                zip(zipOutput, fi, suffixpath);
            }
        }
    }

    /**
     * @param @param zipOutput
     * @param @param file  文件
     * @param @param suffixpath  文件夹拼接路径
     * @return void    返回类型
     * @throws
     * @Title: zip
     * @Description: 压缩的具体操作
     */
    public static void zip(ZipOutputStream zipOutput, File file, String suffixpath) {
        try {
            ZipEntry zEntry = null;
            if (suffixpath.equals("")) {
                zEntry = new ZipEntry(file.getName());
            } else {
                zEntry = new ZipEntry(suffixpath + File.separator + file.getName());
            }
            zipOutput.putNextEntry(zEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = bis.read(buffer)) != -1) {
                zipOutput.write(buffer, 0, read);
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param @param zipOutput
     * @param @param file  文件
     * @return void    返回类型
     * @throws
     * @Title: zip
     * @Description: 压缩单个文件
     */
    public static void zipOFile(ZipOutputStream zipOutput, File file) {
        try {
            ZipEntry zEntry = new ZipEntry(file.getName());
            zipOutput.putNextEntry(zEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = bis.read(buffer)) != -1) {
                zipOutput.write(buffer, 0, read);
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
