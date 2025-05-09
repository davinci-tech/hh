package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.health.knit.bff.BFFApi;
import com.huawei.health.knit.bff.impl.IResourceRequestCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eae implements BFFApi {
    private String c;
    private JSONObject e;

    @Override // com.huawei.health.knit.bff.BFFApi
    public void getPageResource(List<Integer> list, boolean z, IResourceRequestCallback iResourceRequestCallback) {
        if (koq.b(list)) {
            LogUtil.h("BFFImpl", "resPosIdList is null.");
            return;
        }
        if (iResourceRequestCallback == null) {
            LogUtil.h("BFFImpl", "param is invalid.");
            return;
        }
        JSONObject a2 = eaf.a();
        JSONObject jSONObject = this.e;
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    a2.put(next, this.e.get(next));
                }
            } catch (JSONException unused) {
                LogUtil.b("BFFImpl", "JSONException");
            }
        }
        if (this.c == null) {
            this.c = d();
        }
        LogUtil.a("BFFImpl", "mUrl: " + this.c + ", requestBody: " + a2.toString());
        c(a2, z, iResourceRequestCallback);
    }

    private String d() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("knitRequest") + "/functions/2PioDjylqtMakeB6DPy3Ij/yogarequest";
    }

    @Override // com.huawei.health.knit.bff.BFFApi
    public void setExtraRequestArgs(JSONObject jSONObject) {
        this.e = jSONObject;
    }

    private void c(JSONObject jSONObject, boolean z, final IResourceRequestCallback iResourceRequestCallback) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        Map<String, String> e = eaf.e();
        LogUtil.a("BFFImpl", "headers: ", e.toString());
        String jSONObject2 = jSONObject.toString();
        if (!z) {
            LogUtil.a("BFFImpl", "not use cache");
            lqi.d().b(this.c, e, jSONObject2, String.class, new ResultCallback<String>() { // from class: eae.4
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.a("BFFImpl", "post request onSuccess, bff post time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                    try {
                        if (str == null) {
                            iResourceRequestCallback.onFailure(5001, "data is null");
                        } else {
                            iResourceRequestCallback.onSuccess(new JSONObject(str));
                        }
                    } catch (JSONException unused) {
                        iResourceRequestCallback.onFailure(5002, "JSONException");
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.a("BFFImpl", "post request onFail, bff post time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                    iResourceRequestCallback.onFailure(FitnessStatusCodes.DATA_TYPE_NOT_FOUND, th.getMessage());
                }
            });
            return;
        }
        LogUtil.a("BFFImpl", "use cache");
        String e2 = jdq.e(com.huawei.haf.application.BaseApplication.e(), this.c + jSONObject2);
        LogUtil.a("BFFImpl", this.c, ", md5 = ", e2);
        lqi.d().d(this.c, e, jSONObject.toString(), String.class, new ResultCallback<lqq<String>>() { // from class: eae.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(lqq<String> lqqVar) {
                LogUtil.a("BFFImpl", "post request onSuccess, bff post time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                LogUtil.c("BFFImpl", "source from = ", Integer.valueOf(lqqVar.b()));
                try {
                    String c = lqqVar.c();
                    if (TextUtils.isEmpty(c)) {
                        iResourceRequestCallback.onFailure(5001, "data is null");
                    } else {
                        iResourceRequestCallback.onSuccess(new JSONObject(c));
                    }
                } catch (JSONException unused) {
                    iResourceRequestCallback.onFailure(5002, "JSONException");
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a("BFFImpl", "post request onFailure, bff post time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                iResourceRequestCallback.onFailure(FitnessStatusCodes.DATA_TYPE_NOT_FOUND, th.getMessage());
            }
        }, new dzz().d(e2));
    }
}
