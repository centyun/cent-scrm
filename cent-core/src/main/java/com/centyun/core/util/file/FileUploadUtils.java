package com.centyun.core.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.centyun.core.constant.AppConstant;
import com.centyun.core.util.CommonUtils;
import com.centyun.core.util.DateUtils;
import com.centyun.core.util.IOUtils;
import com.centyun.core.util.RandomGenerator;

public class FileUploadUtils {
    private static Logger log = LoggerFactory.getLogger(FileUploadUtils.class);

    public static String getExt(String fileName) {
        if (!CommonUtils.isEmpty(fileName)) {
            int index = fileName.lastIndexOf(AppConstant.DOT);
            if (index > 0) {
                return fileName.substring(index);
            }
        }
        return null;
    }

    /**
     * 保存文件
     * 
     * @param file
     * @return
     */
    public static File saveFile(MultipartFile file, String uploadBaseDir, String appName) {
        Date timestamp = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append(uploadBaseDir).append(appName).append(AppConstant.URL_SEPARATOR)
                .append(DateUtils.formatDate(timestamp, DateUtils.YEAR_MONTH)).append(AppConstant.URL_SEPARATOR)
                .append(DateUtils.formatDate(timestamp, DateUtils.DAY)).append(AppConstant.URL_SEPARATOR)
                .append(DateUtils.formatDate(timestamp, DateUtils.HOURE)).append(AppConstant.URL_SEPARATOR)
                .append(DateUtils.formatDate(timestamp, DateUtils.FULL)).append(RandomGenerator.getString(6))
                .append(getExt(file.getOriginalFilename()));

        File destFile = new File(sb.toString());
        FileOutputStream os = null;
        InputStream is = null;
        try {
            File parentFile = destFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs(); // 如果目录不存在则创建
            }

            os = new FileOutputStream(destFile);
            is = file.getInputStream();
            byte[] buffer = new byte[2048];
            int length = 0;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
                os.flush();
            }
        } catch (Exception e) {
            log.error("文件保存出错", e);
        } finally {
            IOUtils.close(os);
            IOUtils.close(is);
        }
        return destFile;
    }

}
