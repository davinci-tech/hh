package com.huawei.health.device.wifi.interfaces;

import android.content.Context;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.IpushTokenCallback;
import defpackage.cpw;
import defpackage.cse;
import defpackage.jec;
import health.compact.a.CompileParameterUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class WiFiDevicePushReceiver implements IpushBase, IpushTokenCallback {
    private static final int DEVICE_UNBIND_PUSH_TYPE = 6;
    private static final String TAG = "WiFiDevicePushReceiver";

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public List<String> getPushType() {
        return Arrays.asList("6");
    }

    @Override // com.huawei.health.messagecenter.model.IpushBase
    public void processPushMsg(Context context, String str) {
        cpw.a(false, TAG, "get pushType 6 msg time:", jec.c(new Date()));
        if (str == null || "".equals(str) || str.length() < 1) {
            cpw.e(false, TAG, "processPushMsg Error, PushMsg is Empty");
            return;
        }
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            cpw.a(false, TAG, "WiFiDevicePushReceiver processPushMsg():msg =", str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("pushType") && jSONObject.getInt("pushType") == 6) {
                    cse.e().c();
                }
            } catch (JSONException e) {
                cpw.e(false, TAG, "processPushMsg JSONException:", e.getMessage());
            }
        }
    }

    @Override // com.huawei.health.messagecenter.model.IpushTokenCallback
    public void pushTokenHandle(Context context, String str) {
        cpw.a(false, TAG, "token =", str);
    }
}
