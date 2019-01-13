package com.centyun.core.util.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.centyun.core.util.IOUtils;

/**
 * 图形压缩工具类
 * @author yinww
 *
 */

public class ImageCompressUtils {

    private static final Logger log = LoggerFactory.getLogger(ImageCompressUtils.class);

    private Image img;
    private int width;
    private int height;
    private String filename;

    public ImageCompressUtils(File file, String fileName) throws IOException {
        filename = fileName;
        img = ImageIO.read(file); // 构造Image对象
        width = img.getWidth(null); // 得到源图宽
        height = img.getHeight(null); // 得到源图长
    }

    /**
     * 按照宽度还是高度进行压缩
     * 
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public void resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w);
        } else {
            resizeByHeight(h);
        }
    }

    /**
     * 以宽度为基准，等比例放缩图片
     * 
     * @param w int 新宽度
     */
    public void resizeByWidth(int w) throws IOException {
        int h = (int) (height * w / width);
        resize(w, h);
    }

    /**
     * 以高度为基准，等比例缩放图片
     * 
     * @param h int 新高度
     */
    public void resizeByHeight(int h) throws IOException {
        int w = (int) (width * h / height);
        resize(w, h);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     * 
     * @param w int 新宽度
     * @param h int 新高度
     */
    public void resize(int w, int h) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        File file = new File(filename);
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING), 0, 0, null); // 绘制缩小后的图
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(file); // 输出到文件流
            ImageIO.write(image, filename.substring(filename.lastIndexOf('.') + 1), output);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.close(output);
        }
    }

    /**
     * 缩放图片方法
     * 
     * @param srcImageFile 要缩放的图片路径
     * @param result       缩放后的图片路径
     * @param height       目标高度像素
     * @param width        目标宽度像素
     * @param bb           是否补白
     */
    public final static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);// bi.SCALE_SMOOTH
            // 选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                double ratioHeight = (new Integer(height)).doubleValue() / bi.getHeight();
                double ratioWhidth = (new Integer(width)).doubleValue() / bi.getWidth();
                if (ratioHeight > ratioWhidth) {
                    ratio = ratioHeight;
                } else {
                    ratio = ratioWhidth;
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform// 仿射转换
                        .getScaleInstance(ratio, ratio), null);// 返回表示剪切变换的变换
                itemp = op.filter(bi, null);// 转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
            }
            if (bb) {// 补白
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// 构造一个类型为预定义图像类型之一的BufferedImage。
                Graphics2D g = image.createGraphics();// 创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
                g.setColor(Color.white);// 控制颜色
                g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
                            itemp.getHeight(null), Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
                            itemp.getHeight(null), Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "PNG", new File(result)); // 输出压缩图片
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
