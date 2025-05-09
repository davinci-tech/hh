package com.huawei.watchface.mvp.model.webview;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes7.dex */
public class JsNetwork {
    private boolean connected;
    private String type;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface NetworkType {
        public static final String MOBILE = "mobile";
        public static final String NONE = "none";
        public static final String UNKNOWN = "unknown";
        public static final String WIFI = "wifi";
    }

    public JsNetwork(String str, boolean z) {
        this.type = str;
        this.connected = z;
    }

    public String getType() {
        return this.type;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public String toString() {
        return "JsNetwork{type='" + this.type + "', connected=" + this.connected + '}';
    }
}
