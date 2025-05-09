package com.huawei.health.h5pro.jsbridge.system.uikit;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class UiKitEntry extends JsBaseModule {

    /* renamed from: a, reason: collision with root package name */
    public UiKit f2440a;

    @JavascriptInterface
    public void pickTime(final long j) {
        this.f2440a.pickTime(new TimeCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.uikit.UiKitEntry.2
            @Override // com.huawei.health.h5pro.jsbridge.system.uikit.TimeCallback
            public void onUnselected() {
                UiKitEntry.this.onFailureCallback(j, "unselected");
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.uikit.TimeCallback
            public void onSelected(int i, int i2) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("hour", i);
                    jSONObject.put("minute", i2);
                    UiKitEntry.this.onSuccessCallback(j, jSONObject.toString());
                } catch (JSONException e) {
                    UiKitEntry.this.onFailureCallback(j, e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public void pickDate(final long j) {
        this.f2440a.pickDate(new DateCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.uikit.UiKitEntry.1
            @Override // com.huawei.health.h5pro.jsbridge.system.uikit.DateCallback
            public void onUnselected() {
                UiKitEntry.this.onFailureCallback(j, "unselected");
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.uikit.DateCallback
            public void onSelected(int i, int i2, int i3) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("year", i);
                    jSONObject.put("month", i2 + 1);
                    jSONObject.put("day", i3);
                    UiKitEntry.this.onSuccessCallback(j, jSONObject.toString());
                } catch (JSONException e) {
                    UiKitEntry.this.onFailureCallback(j, e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public void pickData(final long j, String str) {
        JSONArray optJSONArray;
        String[] strArr = null;
        try {
            optJSONArray = new JSONObject(str).optJSONArray("dataList");
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
        if (optJSONArray != null && optJSONArray.length() > 0) {
            strArr = new String[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                strArr[i] = optJSONArray.getString(i);
            }
            this.f2440a.pickData(strArr, new DataCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.uikit.UiKitEntry.3
                @Override // com.huawei.health.h5pro.jsbridge.system.uikit.DataCallback
                public void onUnselected() {
                    UiKitEntry.this.onFailureCallback(j, "unselected");
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.uikit.DataCallback
                public void onSelected(int i2) {
                    UiKitEntry.this.onSuccessCallback(j, Integer.valueOf(i2));
                }
            });
            return;
        }
        onFailureCallback(j, "param invalid");
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.f2440a = new AndroidUiKit(this.mContext);
    }
}
