package com.huawei.ui.homehealth.util;

import android.content.Context;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bal;
import defpackage.jei;
import defpackage.koq;
import defpackage.pfe;
import defpackage.qrp;
import health.compact.a.GRSManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FunctionSetCardMarketingUtil {
    private Context b;
    private WeakReference<FunctionSetCardMarketingUtil> e;

    public FunctionSetCardMarketingUtil(Context context) {
        this.b = context;
        if (this.e == null) {
            this.e = new WeakReference<>(this);
        }
    }

    public void a(final HttpResCallback httpResCallback) {
        final HashMap<String, String> e = pfe.e();
        final HashMap<String, String> a2 = pfe.a();
        GRSManager.a(BaseApplication.getContext()).e("healthRecommendUrl", new GrsQueryCallback() { // from class: com.huawei.ui.homehealth.util.FunctionSetCardMarketingUtil.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("FunctionSetCardMarketingUtil", "requestFavoritesCloudData onCallBackSuccess");
                String str2 = str + "/dataRecommend/privacy/userPrivacySort" + CommonUtil.getUrlSuffix();
                LogUtil.a("FunctionSetCardMarketingUtil", "url: ", str2);
                LogUtil.a("FunctionSetCardMarketingUtil", "params: ", e.toString());
                LogUtil.a("FunctionSetCardMarketingUtil", "headers: ", a2.toString());
                jei.d(str2, e, a2, httpResCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("FunctionSetCardMarketingUtil", "requestFavoritesCloudData grs get url errorCode ", Integer.valueOf(i));
            }
        });
    }

    public JSONObject e(Object obj) {
        if (!(obj instanceof List)) {
            LogUtil.a("FunctionSetCardMarketingUtil", "not list");
            return null;
        }
        List list = (List) obj;
        if (list == null || list.size() == 0) {
            LogUtil.a("FunctionSetCardMarketingUtil", "list is null");
            return null;
        }
        Object obj2 = list.get(0);
        if (!(obj2 instanceof HiSampleConfig)) {
            LogUtil.a("FunctionSetCardMarketingUtil", "HiSampleConfig is null");
            return null;
        }
        String configData = ((HiSampleConfig) obj2).getConfigData();
        LogUtil.a("FunctionSetCardMarketingUtil", "data: ", configData);
        try {
            return new JSONObject(configData);
        } catch (JSONException unused) {
            LogUtil.a("FunctionSetCardMarketingUtil", "JSONException");
            return null;
        }
    }

    public void e(LinkedList<CardConfig> linkedList, ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("FunctionSetCardMarketingUtil", "objectResponseCallback is null");
            return;
        }
        if (koq.b(linkedList)) {
            responseCallback.onResponse(-1, linkedList);
            return;
        }
        LinkedList linkedList2 = new LinkedList(linkedList);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < linkedList2.size(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("cardId", ((CardConfig) linkedList2.get(i)).getCardId());
                jSONObject2.put("showFlag", ((CardConfig) linkedList2.get(i)).isShow() ? "1" : "0");
                jSONObject2.put("isEdit", ((CardConfig) linkedList2.get(i)).getEditFlag());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("userCardOrders", jSONArray);
        } catch (JSONException unused) {
            LogUtil.a("FunctionSetCardMarketingUtil", "JSONException");
        }
        LogUtil.a("FunctionSetCardMarketingUtil", "uploadConfig userOrders:", jSONObject.toString());
        String jSONObject3 = jSONObject.toString();
        Objects.requireNonNull(responseCallback);
        qrp.d("900100010", jSONObject3, new bal(responseCallback));
    }

    public void b(boolean z, ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("FunctionSetCardMarketingUtil", "uploadRecommendSwitch objectResponseCallback is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("recommendOrderFlag", z ? 1 : 0);
        } catch (JSONException unused) {
            LogUtil.a("FunctionSetCardMarketingUtil", "JSONException");
        }
        String jSONObject2 = jSONObject.toString();
        Objects.requireNonNull(responseCallback);
        qrp.d("900100011", jSONObject2, new bal(responseCallback));
    }

    public void d(HiDataReadResultListener hiDataReadResultListener) {
        if (hiDataReadResultListener == null) {
            LogUtil.h("FunctionSetCardMarketingUtil", "getUserRecommendSwitch callback is null");
        } else {
            qrp.c("900100011", hiDataReadResultListener);
        }
    }

    public void e(HiDataReadResultListener hiDataReadResultListener) {
        if (hiDataReadResultListener == null) {
            LogUtil.h("FunctionSetCardMarketingUtil", "getUserConfig callback is null");
        } else {
            qrp.c("900100010", hiDataReadResultListener);
        }
    }
}
