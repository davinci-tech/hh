package com.huawei.codevalueplatform.coderule.bean.basebean;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes8.dex */
public class DeepLinkInfo {

    @SerializedName("port")
    private String port;

    @SerializedName("schema")
    private String schema = "";

    @SerializedName("host")
    private String host = "";

    @SerializedName(BleConstants.KEY_PATH)
    private String path = "";

    public String getPort() {
        return this.port;
    }

    public void setPort(String str) {
        this.port = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String str) {
        this.schema = str;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }
}
