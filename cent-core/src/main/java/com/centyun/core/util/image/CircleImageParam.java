package com.centyun.core.util.image;

/**
 * 圆形图截取的参数
 * @author yinww
 *
 */

public class CircleImageParam {

    private String sourcePath;
    private String destinyPath;
    private Integer diameter;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getDestinyPath() {
        return destinyPath;
    }

    public void setDestinyPath(String destinyPath) {
        this.destinyPath = destinyPath;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

}
