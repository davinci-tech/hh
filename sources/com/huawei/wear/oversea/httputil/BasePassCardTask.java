package com.huawei.wear.oversea.httputil;

import android.content.Context;
import com.amap.api.maps.model.MyLocationStyle;
import defpackage.stq;
import defpackage.sua;
import defpackage.sug;
import defpackage.swd;
import defpackage.swe;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public abstract class BasePassCardTask<Result, RequestParams> extends BaseWalletTask<Result, RequestParams> {
    private static final String TAG = "BasePassCardTask";

    public BasePassCardTask(Context context, String str) {
        super(context, str);
    }

    public BasePassCardTask(Context context, String str, int i, int i2) {
        super(context, str, i, i2);
    }

    public BasePassCardTask(Context context, String str, int i, Map<String, String> map) {
        super(context, str, i, map, null);
    }

    @Override // com.huawei.wear.oversea.httputil.BaseWalletTask
    protected void resolveResponse(sua suaVar, String str) {
        String tag = getTag();
        if (swe.b((CharSequence) str, true)) {
            stq.e(tag, "resolveResponse responseStr is null", false);
            suaVar.g = -99;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String c = swd.c(jSONObject, "errorCode");
            String c2 = swd.c(jSONObject, "errorMsg");
            if (c != null) {
                String str2 = TAG;
                stq.e(str2, ", " + (" code:" + c + " msg:" + c2), false);
                suaVar.g = Integer.parseInt(c);
                return;
            }
            String c3 = swd.c(jSONObject, "returnCode");
            String c4 = swd.c(jSONObject, "returnDesc");
            if (jSONObject.has(MyLocationStyle.ERROR_INFO)) {
                suaVar.d(sug.a(jSONObject.getJSONObject(MyLocationStyle.ERROR_INFO)));
            }
            if (c3 == null) {
                stq.e(TAG, ", returnCode is invalid.", false);
                suaVar.g = -99;
                return;
            }
            suaVar.g = Integer.parseInt(c3);
            if (suaVar.g != 0) {
                String str3 = " code:" + suaVar.g + " desc:" + c4;
                stq.e(TAG, ", returnDesc : err " + str3, false);
                return;
            }
            makeResponseData(suaVar, jSONObject);
        } catch (NoSuchMethodError unused) {
            stq.d(TAG, "resolveResponse, NoSuchMethodError", false);
            suaVar.g = -99;
        } catch (NumberFormatException unused2) {
            stq.d(TAG, "resolveResponse, NumberFormatException", false);
            suaVar.g = -99;
        } catch (JSONException unused3) {
            stq.d(TAG, "resolveResponse, JSONException", false);
            suaVar.g = -99;
        }
    }
}
