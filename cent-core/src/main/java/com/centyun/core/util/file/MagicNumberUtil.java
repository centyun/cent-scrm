package com.centyun.core.util.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 魔数, 可判断文件的类型, 用于上传文件的校验
 * 
 * @author yinww
 *
 */

public class MagicNumberUtil {

    /**
     * 判断文件的类型
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public static FileType getType(String filePath) throws IOException {
        // 获取文件头
        String fileHead = getFileHeader(filePath);

        if (fileHead != null && fileHead.length() > 0) {
            fileHead = fileHead.toUpperCase();
            FileType[] fileTypes = FileType.values();

            for (FileType type : fileTypes) {
                if (fileHead.startsWith(type.getValue())) {
                    return type;
                }
            }
        }

        return null;
    }

    /**
     * 读取文件头
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    private static String getFileHeader(String filePath) throws IOException {
        byte[] b = new byte[28];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            inputStream.read(b, 0, 28);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return bytesToHex(b);
    }

    /**
     * 将字节数组转换成16进制字符串
     * 
     * @param src
     * @return
     */
    private static String bytesToHex(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }
}
