package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.jsb.inner.data.a;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.inter.AdContentRequestFactory;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class q extends j {
    private Context d;

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        this.d = context.getApplicationContext();
        ho.b("JsbGetApiReqBody", "get js api req body.");
        if (ho.a()) {
            ho.a("JsbGetApiReqBody", str);
        }
        JSONObject jSONObject = new JSONObject(str);
        String[] a2 = com.huawei.openalliance.ad.utils.cz.a(jSONObject.optJSONArray(JsbMapKeyNames.H5_SLOT_IDS));
        int optInt = jSONObject.optInt("adType", -1);
        int optInt2 = jSONObject.optInt("deviceType", 4);
        RequestOptions c = c(context, str);
        a.C0078a c0078a = new a.C0078a();
        a(jSONObject, str, c0078a);
        String a3 = a(a2, optInt, optInt2, c, c0078a.a());
        if (TextUtils.isEmpty(a3)) {
            a(remoteCallResultCallback, this.f7108a, 4001, (Object) null, true);
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("data", a3);
        jSONObject2.put("complete", true);
        jSONObject2.put("callBackName", (Object) null);
        ho.b("JsbGetApiReqBody", "notifyResultCallback by callBackJson.");
        a(remoteCallResultCallback, this.f7108a, 1000, jSONObject2);
    }

    private void a(JSONObject jSONObject, String str, a.C0078a c0078a) {
        Integer num;
        String optString = jSONObject.optString(JsbMapKeyNames.CREATIVE_TYPES);
        int optInt = jSONObject.optInt(JsbMapKeyNames.MEDIA_DIRECTION, -111111);
        int optInt2 = jSONObject.optInt(JsbMapKeyNames.MEDIA_ASPECT, -111111);
        int optInt3 = jSONObject.optInt(JsbMapKeyNames.AD_WIDTH, -111111);
        int optInt4 = jSONObject.optInt(JsbMapKeyNames.AD_HEIGHT, -111111);
        String optString2 = jSONObject.optString(JsbMapKeyNames.H5_MEDIA_CONTENT);
        if (!TextUtils.isEmpty(optString)) {
            List<Integer> b = com.huawei.openalliance.ad.utils.cz.b(optString, ";");
            if (!com.huawei.openalliance.ad.utils.bg.a(b)) {
                c0078a.a(b);
            }
        }
        if (optInt != -111111) {
            num = Integer.valueOf((optInt != 0 ? optInt != 1 ? optInt != 2 ? bm.UNKNOWN : bm.LAND : bm.PORTRAIT : bm.ANY).a());
        } else {
            num = null;
        }
        if (optInt2 != -111111 && bm.ANY.a(optInt2)) {
            num = Integer.valueOf(optInt2);
        }
        if (num != null) {
            c0078a.a(num.intValue() == bm.CUSTOM.a() ? 0 : Integer.valueOf(num.intValue() + 2));
        }
        if (optInt4 != -111111 && optInt3 != -111111) {
            c0078a.b(Integer.valueOf(optInt3));
            c0078a.c(Integer.valueOf(optInt4));
        }
        try {
            c0078a.a(g(str));
        } catch (Throwable unused) {
            ho.c("JsbGetApiReqBody", "get loc error.");
        }
        if (TextUtils.isEmpty(optString2)) {
            return;
        }
        c0078a.a(optString2);
    }

    private String a(String[] strArr, int i, int i2, RequestOptions requestOptions, com.huawei.hms.ads.jsb.inner.data.a aVar) {
        String a2;
        if (com.huawei.openalliance.ad.utils.bg.a(strArr) || -1 == i) {
            ho.b("JsbGetApiReqBody", "get body without slotId and type.");
            a2 = AdContentRequestFactory.a(this.d, i2, requestOptions, aVar);
        } else {
            ho.b("JsbGetApiReqBody", "get req body.");
            a2 = AdContentRequestFactory.a(this.d, strArr, i, i2, requestOptions, aVar, null);
        }
        if (ho.a()) {
            ho.a("JsbGetApiReqBody", "reqBody: %s", a2);
        }
        return a2;
    }

    public q() {
        super("pps.api.req.getbody");
    }
}
