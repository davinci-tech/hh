package com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr;

/* loaded from: classes.dex */
public class WalletApplication {
    private String deeplink;
    private String icon;
    private boolean isHuaweiWallet;
    private String name;

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

    public boolean isHuaweiWallet() {
        return this.isHuaweiWallet;
    }

    public void setHuaweiWallet(boolean z) {
        this.isHuaweiWallet = z;
    }

    public WalletApplication(String str, String str2, String str3, boolean z) {
        this.name = str;
        this.icon = str2;
        this.deeplink = str3;
        this.isHuaweiWallet = z;
    }

    public WalletApplication(String str, String str2, String str3) {
        this(str, str2, str3, false);
    }

    public WalletApplication(String str, String str2) {
        this(str, str2, "", true);
    }

    public WalletApplication() {
    }
}
