package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes7.dex */
public class InstallWatchFaceBean {
    private String actionUrl;
    private String cnTitle;
    private String couponId;
    private String fileSize;
    private String fileUrl;
    private String hashCode;
    private int installationType;
    private boolean isOneClick;
    private boolean isThreePay;
    private boolean isUpdate;
    private boolean isZip;
    private String price;
    private String productId;
    private String resourceType;
    private String showDialog;
    private String symbolType;
    private String title;
    private String version;
    private String versionCode;
    private boolean vipFreeWatchFace;
    private String watchFaceHiTopId;
    private String watchScreen;

    public InstallWatchFaceBean(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this.watchFaceHiTopId = str;
        this.version = str2;
        this.fileUrl = str3;
        this.fileSize = str4;
        this.hashCode = str5;
        this.watchScreen = str6;
        this.isUpdate = z;
    }

    public InstallWatchFaceBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z) {
        this(str, str2, str3, str4, str5, str6, str7, str8, z, 0);
    }

    public InstallWatchFaceBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, int i) {
        this.watchFaceHiTopId = str;
        this.version = str2;
        this.productId = str3;
        this.price = str4;
        this.showDialog = str5;
        this.symbolType = str6;
        this.versionCode = str7;
        this.couponId = str8;
        this.isThreePay = z;
        this.installationType = i;
    }

    public InstallWatchFaceBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this(str, str2, str3, str4, str5, str6, str7, str8, false);
    }

    public InstallWatchFaceBean() {
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String str) {
        this.resourceType = str;
    }

    public int getInstallationType() {
        return this.installationType;
    }

    public String getActionUrl() {
        return this.actionUrl;
    }

    public void setActionUrl(String str) {
        this.actionUrl = str;
    }

    public void setInstallationType(int i) {
        this.installationType = i;
    }

    public boolean isThreePay() {
        return this.isThreePay;
    }

    public void setThreePay(boolean z) {
        this.isThreePay = z;
    }

    public String getCouponId() {
        return this.couponId;
    }

    public void setCouponId(String str) {
        this.couponId = str;
    }

    public String getWatchFaceHiTopId() {
        return this.watchFaceHiTopId;
    }

    public void setWatchFaceHiTopId(String str) {
        this.watchFaceHiTopId = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String str) {
        this.fileUrl = str;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String str) {
        this.fileSize = str;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String str) {
        this.hashCode = str;
    }

    public String getWatchScreen() {
        return this.watchScreen;
    }

    public void setWatchScreen(String str) {
        this.watchScreen = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getShowDialog() {
        return this.showDialog;
    }

    public void setShowDialog(String str) {
        this.showDialog = str;
    }

    public String getSymbolType() {
        return this.symbolType;
    }

    public void setSymbolType(String str) {
        this.symbolType = str;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(String str) {
        this.versionCode = str;
    }

    public boolean isUpdate() {
        return this.isUpdate;
    }

    public void setUpdate(boolean z) {
        this.isUpdate = z;
    }

    public boolean isOneClick() {
        return this.isOneClick;
    }

    public void setOneClick(boolean z) {
        this.isOneClick = z;
    }

    public boolean isZip() {
        return this.isZip;
    }

    public void setZip(boolean z) {
        this.isZip = z;
    }

    public boolean isVipFreeWatchFace() {
        return this.vipFreeWatchFace;
    }

    public void setVipFreeWatchFace(boolean z) {
        this.vipFreeWatchFace = z;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getCnTitle() {
        return this.cnTitle;
    }

    public void setCnTitle(String str) {
        this.cnTitle = str;
    }
}
