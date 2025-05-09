package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;

/* loaded from: classes5.dex */
public class WifiDeviceGetDeviceRegistration implements IRequest {
    private static final String URL_GET_DEVICE_REGISTRATION = "/deviceAgent/getDeviceRegistration";
    private int productId;
    private String uniqueId;

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String str) {
        this.uniqueId = str;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int i) {
        this.productId = i;
    }

    public String toString() {
        return "WifiDeviceGetDeviceRegistration{uniqueId='" + this.uniqueId + "', productId='" + this.productId + "'}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_GET_DEVICE_REGISTRATION;
    }
}
