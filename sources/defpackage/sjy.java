package defpackage;

import android.graphics.Bitmap;

/* loaded from: classes7.dex */
public class sjy {
    private int bindStatus;
    private String deviceCode;
    private String deviceIconUrl;
    private String deviceName;
    private Bitmap icon;
    private String identify;
    private boolean isConnected;
    private boolean isLocalBound;
    private int productId;
    private String uniqueId;
    private String wiseDeviceProdId;
    private String wxLinkSdkId;

    public String getDeviceCode() {
        return this.deviceCode;
    }

    public String getWxLinkSdkId() {
        return this.wxLinkSdkId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public int getBindStatus() {
        return this.bindStatus;
    }

    public String getWiseDeviceProdId() {
        return this.wiseDeviceProdId;
    }

    public Bitmap getIcon() {
        return this.icon;
    }

    public void setIcon(Bitmap bitmap) {
        this.icon = bitmap;
    }

    public String getIdentify() {
        return this.identify;
    }

    public void setIdentify(String str) {
        this.identify = str;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public boolean isLocalBound() {
        return this.isLocalBound;
    }

    public void setLocalBound(boolean z) {
        this.isLocalBound = z;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public void setConnected(boolean z) {
        this.isConnected = z;
    }

    public int getProductId() {
        return this.productId;
    }

    public String getDeviceIconUrl() {
        return this.deviceIconUrl;
    }
}
