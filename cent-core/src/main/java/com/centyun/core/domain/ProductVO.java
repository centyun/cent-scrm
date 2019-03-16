package com.centyun.core.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductVO {

    private String id;
    private String name;
    private String englishName;
    private String code;
    private String icon;
    private String releaseUrl;

    private boolean active;
    private List<ModuleVO> modules; // 产品下的模块

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getReleaseUrl() {
        return releaseUrl;
    }

    public void setReleaseUrl(String releaseUrl) {
        this.releaseUrl = releaseUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ModuleVO> getModules() {
        return modules;
    }

    public void setModules(List<ModuleVO> modules) {
        this.modules = modules;
    }

    public void addModule(ModuleVO module) {
        if(modules == null) {
            modules = new ArrayList<>();
        }
        modules.add(module);
    }

}
