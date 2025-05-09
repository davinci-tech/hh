package com.huawei.pluginmessagecenter.service;

import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.mqw;
import defpackage.mrc;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class MessageJsApi extends JsBaseModule {
    private static final int DEFAULT_ERROR = -1;
    private static final String MODULE = "module";
    private static final String MSG_ID = "messageId";
    private static final int MSG_STATUS_UNREAD = 0;
    private static final String TYPE = "type";

    @JavascriptInterface
    public void getMessages(long j, String str) {
        LogUtil.a(this.TAG, "enter getMessages");
        ArrayList arrayList = new ArrayList();
        if (StringUtils.g(str)) {
            LogUtil.h(this.TAG, "messageCenter.getMessages: parameter cannot be null or empty.");
            onFailureCallback(j, "parameter is not or emyty", -1);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            List<MessageObject> a2 = mqw.b(this.mContext).a(jSONObject.getString("module"), jSONObject.getString("type"));
            if (koq.b(a2)) {
                LogUtil.h(this.TAG, "list is Empty");
                onSuccessCallback(j, "");
                return;
            }
            for (MessageObject messageObject : a2) {
                if (messageObject.getReadFlag() == 0) {
                    arrayList.add(messageObject);
                }
            }
            String json = new Gson().toJson(arrayList);
            LogUtil.a(this.TAG, "getMessages success");
            onSuccessCallback(j, json);
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "getMessage JSONException");
            onFailureCallback(j, "JSONException", -1);
        }
    }

    @JavascriptInterface
    public void onRead(String str) {
        LogUtil.a(this.TAG, "enter onRead");
        if (StringUtils.g(str)) {
            LogUtil.h(this.TAG, "parameter be null or empty.");
            return;
        }
        try {
            mrc.e(this.mContext).b(new JSONObject(str).getString(MSG_ID));
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "onRead JSONException");
        }
    }
}
