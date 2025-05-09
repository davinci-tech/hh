package com.huawei.agconnect.apms.collect.model.event.webview;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.m0;

/* loaded from: classes2.dex */
public class WebViewErrorEvent extends Event {
    private int errorCode;
    private String errorDesc;
    private boolean forMainFrame;
    private String method;
    private String mimeType;
    private int primaryError;
    private int statusCode;
    private String url;

    public WebViewErrorEvent(long j, String str, String str2, String str3, boolean z, int i, int i2, int i3, String str4) {
        this.timestamp = j;
        this.url = str;
        this.method = str2;
        this.mimeType = str3;
        this.forMainFrame = z;
        this.statusCode = i;
        this.errorCode = i2;
        this.primaryError = i3;
        this.errorDesc = str4;
        this.runtimeEnvInformation = Agent.getRuntimeEnvInformation();
    }

    @Override // com.huawei.agconnect.apms.collect.model.event.Event, com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(this.runtimeEnvInformation.asJsonArray());
        jsonArray.add(Long.valueOf(this.timestamp));
        jsonArray.add(m0.abc(this.url));
        jsonArray.add(m0.abc(this.method));
        jsonArray.add(m0.abc(this.mimeType));
        jsonArray.add(m0.abc(Boolean.valueOf(this.forMainFrame)));
        jsonArray.add(m0.abc(Integer.valueOf(this.statusCode)));
        jsonArray.add(m0.abc(Integer.valueOf(this.errorCode)));
        jsonArray.add(m0.abc(Integer.valueOf(this.primaryError)));
        jsonArray.add(m0.abc(this.errorDesc));
        return jsonArray;
    }
}
