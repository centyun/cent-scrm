package com.centyun.core.util.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.centyun.core.util.CommonUtils;
import com.centyun.core.util.IOUtils;

/**
 * 圆形图工具类
 * @author yinww
 *
 */

public class CircleImageUtils {

    private static final Logger log = LoggerFactory.getLogger(CircleImageUtils.class);

    /**
     * 判断path是否是web请求
     * 
     * @param path
     * @return
     */
    private static boolean isHttpFile(String path) {
        if (!CommonUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
            return true;
        }
        return false;
    }

    public static boolean getCircleImage(String sourcePath, String destPath) {
        CircleImageParam circleParam = new CircleImageParam();
        circleParam.setSourcePath(sourcePath);// 可以是网络图片,可是是本地图片
        circleParam.setDestinyPath(destPath);// 必须是本地图片,且图片格式必须是png
        return getCircleImage(circleParam);
    }

    /**
     * 画圆形图片,sourcePath是原图片路径,radius是圆形图片的半径
     * 
     * @param sourcePath  原图片地址,不能为空
     * @param destinyPath 目的图片地址,不能为空
     * @param             radius, 不能为负数,为0或者null表示用图片的宽、高最小值做直径
     * @return
     */
    public static boolean getCircleImage(CircleImageParam param) {
        if (CommonUtils.isEmpty(param.getSourcePath()) || CommonUtils.isEmpty(param.getDestinyPath())) {
            return false;
        }

        long start = System.currentTimeMillis();
        // 获取原图片
        BufferedImage waterImg = null;
        try {

            if (isHttpFile(param.getSourcePath())) {
                InputStream sis = null;
                try {
                    URL sourceUrl = new URL(param.getSourcePath());
                    sis = sourceUrl.openConnection().getInputStream();
                    waterImg = ImageIO.read(sis);
                } catch (Exception e) {
                    log.error("读取原图片异常,{}", e);
                } finally {
                    if (sis != null) {
                        sis.close();
                    }
                }
            } else {
                File file = null;
                try {
                    file = new File(param.getSourcePath());
                    waterImg = ImageIO.read(file);
                } catch (Exception e) {
                    log.error("读取原图片异常,{}", e);
                }
            }
        } catch (Exception e) {
            log.error("图片转换成圆形失败", e);
            return false;
        }

        // 半径有设置时,以设置的半径为主
        int width = waterImg.getWidth();
        int height = waterImg.getHeight();
        int diameter = (width < height) ? width : height;
        if (param.getDiameter() != null && param.getDiameter() > 0) {
            diameter = param.getDiameter();
        }

        if (diameter > 64) { // 限制圆形图片的大小
            diameter = 64;
        }

        // 按照要求缩放图片
        BufferedImage tag = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(waterImg, 0, 0, diameter, diameter, null);
        waterImg = tag;

        // 生成最终的图片
        boolean rs = false;
        Graphics2D g2 = null;
        try {
            tag = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            g2 = tag.createGraphics();
            g2.setComposite(AlphaComposite.Src);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, diameter, diameter, diameter, diameter));
            g2.setComposite(AlphaComposite.SrcAtop);
            g2.drawImage(waterImg, 0, 0, null);
            int temp = param.getDestinyPath().lastIndexOf(".") + 1;
            rs = ImageIO.write(tag, param.getDestinyPath().substring(temp), new File(param.getDestinyPath()));
        } catch (IOException e) {
            log.error("合成图片错误", e);
            return false;
        } finally {
            if (g2 != null) {
                g2.dispose();
            }
        }

        long end = System.currentTimeMillis();
        log.info("生成圆角图片耗时:{},图片地址:{}", (end - start), param.getDestinyPath());
        return rs;
    }

    public static void download(String imageUrl, String destPath) {
        DataInputStream dis = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(imageUrl);
            // 打开网络输入流
            dis = new DataInputStream(url.openStream());
            // 建立一个新的文件
            fos = new FileOutputStream(new File(destPath));
            byte[] buffer = new byte[1024];
            int length;
            // 开始填充数据
            while ((length = dis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(dis);
            IOUtils.close(fos);
        }
    }

}
