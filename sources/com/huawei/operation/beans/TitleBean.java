package com.huawei.operation.beans;

/* loaded from: classes5.dex */
public class TitleBean {
    public static final String LEFT_BTN_TYPE_ARROW = "arrow";
    public static final String LEFT_BTN_TYPE_X = "x";
    public static final String RIGHT_BTN_TYPE_IS_MY_ACTIVITY = "isMyActivity";
    public static final String RIGHT_BTN_TYPE_MORE = "more";
    public static final String RIGHT_BTN_TYPE_SHARE = "share";
    private String featureUrl;
    private boolean isShowAppBar = true;
    private String leftBtn;
    private String orderManagerUrl;
    private String rightBtn;
    private String shopcarUrl;
    private String url;

    public String fetchGetUrl() {
        return this.url;
    }

    public void configSetUrl(String str) {
        this.url = str;
    }

    public String fetchGetLeftBtn() {
        return this.leftBtn;
    }

    public void configSetLeftBtn(String str) {
        this.leftBtn = str;
    }

    public String fetchGetRightBtn() {
        return this.rightBtn;
    }

    public void configSetRightBtn(String str) {
        this.rightBtn = str;
    }

    public String fetchGetShoppingCartUrl() {
        return this.shopcarUrl;
    }

    public void configSetShoppingCartUrl(String str) {
        this.shopcarUrl = str;
    }

    public String fetchGetFeatureUrl() {
        return this.featureUrl;
    }

    public void configSetFeatureUrl(String str) {
        this.featureUrl = str;
    }

    public String fetchGetOrderManagerUrl() {
        return this.orderManagerUrl;
    }

    public void configSetOrderManagerUrl(String str) {
        this.orderManagerUrl = str;
    }

    public void configSetIsShowAppBar(boolean z) {
        this.isShowAppBar = z;
    }

    public boolean fetchIsShowAppBar() {
        return this.isShowAppBar;
    }

    public String toString() {
        return "TitleBean{leftBtn='" + this.leftBtn + "', rightBtn='" + this.rightBtn + "', isShowAppBar='" + this.isShowAppBar + "'}";
    }
}
