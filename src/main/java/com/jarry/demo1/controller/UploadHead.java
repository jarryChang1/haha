package com.jarry.demo1.controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UploadHead {

    /**
     * Thumbnailator 是一个优秀的图片处理的Google开源Java类库。
     * <dependency>
     *     <groupId>net.coobird</groupId>
     *     <artifactId>thumbnailator</artifactId>
     *     <version>0.4.8</version>
     * </dependency>
     */

    /**
     * @param file   文件流
     * @param path   文件存放路径
     * @param userId 上传用户的Id
     * @return 新文件名
     * @throws Exception
     */
    public static String uploadHead(File file, String path, int userId) throws Exception {

        String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//当前时间
        Random r = new Random();
        int x = r.nextInt(999999); //生成一个随机数
        String newFileName = userId + "-" + x + "-" + nowTime + ".jpg";//得到文件的新名字

        FileInputStream is = new FileInputStream(file);
        BufferedImage sourceImg = javax.imageio.ImageIO.read(is);

        int width = sourceImg.getWidth();                       //原文件宽度
        int height = sourceImg.getHeight();                     //原文件高度

        if (width > 200 || height > 200) {                                //等比缩放为200*200的图片，如果宽和高有一个大于200的就进行截取
            DecimalFormat df = new DecimalFormat("0.000");

            double bili = 0.000;                                //计算等比

            if (width > height) {                                  //根据像素大的一方进行等比缩放
                bili = Double.parseDouble(df.format(width / 200.0));
            } else {
                bili = Double.parseDouble(df.format(height / 200.0));
            }
            width = (int) (width / bili);
            height = (int) (height / bili);
        }

        BufferedImage src = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);           //根据计算好的宽高新建画布

        src.getGraphics().drawImage(sourceImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        ImageIO.write(src, "JPEG", new File(path, newFileName));                                     //输出图像
        is.close();
        return newFileName;
    }

    public static void main(String[] args) {
        final Integer integer = new Integer(11);

    }

}