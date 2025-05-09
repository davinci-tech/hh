package com.huawei.wear.oversea.httputil;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.stf;
import defpackage.sth;
import defpackage.stq;
import defpackage.sua;
import defpackage.suk;
import defpackage.svk;
import defpackage.swd;
import defpackage.swe;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes9.dex */
public abstract class BaseWalletTask<Result, RequestParams> extends HttpConnTask<Result, RequestParams> {
    private static final String TAG = "BaseWalletTask";
    private String mUrl;

    protected abstract int getErrorLogConstant();

    protected abstract String getTag();

    protected abstract void makeResponseData(sua suaVar, JSONObject jSONObject) throws JSONException;

    public BaseWalletTask(Context context, String str) {
        super(context, str);
        this.mUrl = str;
    }

    public BaseWalletTask(Context context, String str, int i, int i2) {
        super(context, str, i, i2);
    }

    public BaseWalletTask(Context context, String str, int i, Map<String, String> map, Map<String, String> map2) {
        super(context, str, i, map, map2);
    }

    protected void resolveResponse(sua suaVar, String str) {
        String tag = getTag();
        if (swe.b((CharSequence) str, true)) {
            stq.e(tag, "responseStr is null", false);
            suaVar.g = -99;
            return;
        }
        try {
            boolean e = stf.e(this.mUrl);
            stq.b(TAG, "resolveResponse restUrlCheck isRestFul : " + e);
            if (!e) {
                JSONObject jSONObject = new JSONObject(str);
                String c = swd.c(jSONObject, "merchantID");
                int d = swd.d(jSONObject, "keyIndex");
                String c2 = swd.c(jSONObject, TrackConstants$Opers.RESPONSE);
                String c3 = swd.c(jSONObject, "errorCode");
                String c4 = swd.c(jSONObject, "errorMsg");
                if (c3 != null) {
                    stq.e(TAG, ", " + (" code:" + c3 + " msg:" + c4), false);
                    suaVar.g = Integer.parseInt(c3);
                    return;
                }
                if (svk.b().equals(c) && !swe.b((CharSequence) c2, true) && d == -1) {
                    str = c2;
                }
                stq.d(TAG, ", unexpected error from server.", false);
                suaVar.g = -99;
                return;
            }
            stq.d(TAG, ", decryptedResponse : " + str, true);
            JSONObject jSONObject2 = new JSONObject(str);
            String c5 = swd.c(jSONObject2, "returnCode");
            String c6 = swd.c(jSONObject2, "returnDesc");
            String c7 = swd.c(jSONObject2, "header");
            if (!TextUtils.isEmpty(c7)) {
                suaVar.h = (sth) new Gson().fromJson(c7, sth.class);
            }
            if (c5 == null) {
                stq.d(TAG, ", returnCode is invalid.", false);
                suaVar.g = -99;
                return;
            }
            suaVar.g = Integer.parseInt(c5);
            if (!TextUtils.isEmpty(c6)) {
                suaVar.j = c6;
            }
            if (suaVar.g != 0) {
                int i = suaVar.g;
                stq.e(TAG, ", returnDesc : ", false);
            } else {
                makeResponseData(suaVar, jSONObject2);
            }
        } catch (JsonSyntaxException unused) {
            stq.e(TAG, ", JsonSyntaxException : ", true);
            suaVar.g = -99;
        } catch (NoSuchMethodError unused2) {
            stq.e(TAG, ", NoSuchMethodError : ", true);
            suaVar.g = -99;
        } catch (NumberFormatException unused3) {
            stq.e(TAG, ", NumberFormatException : ", true);
            suaVar.g = -99;
        } catch (JSONException unused4) {
            stq.e(TAG, ", JSONException : ", true);
            suaVar.g = -99;
        }
    }

    @Override // com.huawei.wear.oversea.httputil.HttpConnTask
    protected void onReportEvent(String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str3)) {
            str3 = getTag();
        }
        String str7 = "commander:" + str3 + "; srcTranID:" + str5 + ";  ; result:" + str + "; returnCode:" + str2 + "; ";
        stq.c(getTag(), getSubProcessPrefix() + "HttpConnTask onReportEvent, action:" + str4 + ", Report data: " + str7, false);
        StringBuilder sb = new StringBuilder();
        sb.append(str7);
        sb.append(" exception:");
        sb.append(str6);
        suk.e(str4, sb.toString());
    }
}
