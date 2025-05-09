package com.huawei.openalliance.ad.beans.parameter;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class App implements Serializable {
    private static final long serialVersionUID = 30421300;
    private Integer brand;
    private String hostPkgname;
    private String name;
    private String pkgname;
    private String version;

    public void setVersion(String str) {
        this.version = str;
    }

    public void setPkgname(String str) {
        this.pkgname = str;
    }

    /* loaded from: classes9.dex */
    public static final class Builder {
        private Integer brand;
        private String hostPkgname;
        private String name;
        private String pkgname;
        private String version;

        public Builder setVersion(String str) {
            this.version = str;
            return this;
        }

        public Builder setPkgname(String str) {
            this.pkgname = str;
            return this;
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setHostPkgname(String str) {
            this.hostPkgname = str;
            return this;
        }

        public Builder setBrand(Integer num) {
            this.brand = num;
            return this;
        }

        public App build() {
            return new App(this);
        }
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setHostPkgname(String str) {
        this.hostPkgname = str;
    }

    public void setBrand(Integer num) {
        this.brand = num;
    }

    public String getVersion() {
        return this.version;
    }

    public String getPkgname() {
        return this.pkgname;
    }

    public String getName() {
        return this.name;
    }

    public String getHostPkgname() {
        return this.hostPkgname;
    }

    public Integer getBrand() {
        return this.brand;
    }

    public App(String str, String str2, String str3, String str4) {
        this.name = str2;
        this.pkgname = str;
        this.version = str3;
        this.hostPkgname = str4;
    }

    public App(String str, String str2, String str3, Integer num) {
        this.version = str;
        this.name = str2;
        this.pkgname = str3;
        this.brand = num;
    }

    public App(String str, String str2, String str3) {
        this.name = str2;
        this.pkgname = str;
        this.version = str3;
    }

    public App(Builder builder) {
        this.version = builder.version;
        this.name = builder.name;
        this.pkgname = builder.pkgname;
        this.brand = builder.brand;
        this.hostPkgname = builder.hostPkgname;
    }

    public App() {
    }
}
