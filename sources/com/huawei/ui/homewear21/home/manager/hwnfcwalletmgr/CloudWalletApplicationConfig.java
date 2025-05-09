package com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr;

import com.google.gson.annotations.SerializedName;
import defpackage.nrv;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class CloudWalletApplicationConfig {

    @SerializedName("walletConfig")
    private List<WalletApplicationCountryConfig> walletConfig = new ArrayList();

    public static class WalletApplicationConfig {

        @SerializedName("deeplink")
        private String deeplink;

        @SerializedName("deviceModels")
        private List<String> deviceModels;

        @SerializedName("icon")
        private String icon;

        @SerializedName("name")
        private String name;

        @SerializedName("platforms")
        private List<String> platforms;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public String getDeeplink() {
            return this.deeplink;
        }

        public void setDeeplink(String str) {
            this.deeplink = str;
        }

        public List<String> getPlatforms() {
            return this.platforms;
        }

        public void setPlatforms(List<String> list) {
            this.platforms = list;
        }

        public List<String> getDeviceModels() {
            return this.deviceModels;
        }

        public void setDeviceModels(List<String> list) {
            this.deviceModels = list;
        }

        public String toString() {
            return "WalletApplicationConfig{name='" + this.name + "', icon='" + this.icon + "', deeplink='" + this.deeplink + "', platforms=" + this.platforms + ", deviceModels=" + this.deviceModels + '}';
        }
    }

    public static class WalletApplicationCountryConfig {

        @SerializedName("applications")
        private List<WalletApplicationConfig> applications;

        @SerializedName("countryCode")
        private String countryCode;

        public String getCountryCode() {
            return this.countryCode;
        }

        public void setCountryCode(String str) {
            this.countryCode = str;
        }

        public List<WalletApplicationConfig> getApplications() {
            return this.applications;
        }

        public void setApplications(List<WalletApplicationConfig> list) {
            this.applications = list;
        }

        public String toString() {
            return "WalletApplicationCountryConfig{countryCode='" + this.countryCode + "', applications=" + this.applications + '}';
        }
    }

    public List<WalletApplicationCountryConfig> getWalletConfig() {
        return this.walletConfig;
    }

    public void setWalletConfig(List<WalletApplicationCountryConfig> list) {
        this.walletConfig = list;
    }

    public String toString() {
        return "CloudWalletApplicationConfig{walletConfig=" + this.walletConfig + '}';
    }

    public static CloudWalletApplicationConfig loads(String str) {
        return (CloudWalletApplicationConfig) nrv.b(str, CloudWalletApplicationConfig.class);
    }
}
