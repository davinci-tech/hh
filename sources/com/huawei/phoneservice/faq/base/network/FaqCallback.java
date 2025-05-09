package com.huawei.phoneservice.faq.base.network;

import android.app.Activity;
import com.google.gson.Gson;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.l;
import defpackage.uhy;
import java.io.IOException;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class FaqCallback<T> implements Callback {

    /* renamed from: a, reason: collision with root package name */
    private final Class<T> f8231a;
    private Gson b;
    private WeakReference<Activity> c;

    public abstract void onResult(Throwable th, T t);

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
    public void onResponse(Submit submit, Response response) throws IOException {
        uhy.e((Object) submit, "");
        uhy.e((Object) response, "");
        String byte2Str = StringUtils.byte2Str(response.getBody().bytes());
        try {
            String str = null;
            if (l.e(byte2Str)) {
                uhy.b((Object) null, "");
            } else {
                JSONObject jSONObject = new JSONObject(byte2Str);
                int optInt = jSONObject.optInt("responseCode");
                String optString = jSONObject.optString("responseDesc");
                JSONObject optJSONObject = jSONObject.optJSONObject("responseData");
                int optInt2 = jSONObject.optInt("code");
                String optString2 = jSONObject.optString("msg");
                if (optInt != 200) {
                    if (optInt2 != 0) {
                        a(response, new FaqWebServiceException(optInt2, optString2));
                        return;
                    } else {
                        a(response, new FaqWebServiceException(optInt, optString));
                        return;
                    }
                }
                if (optJSONObject != null) {
                    str = optJSONObject.toString();
                }
            }
            a(response, str);
        } catch (Exception e) {
            a(response, e);
        }
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
    public void onFailure(Submit submit, Throwable th) {
        uhy.e((Object) submit, "");
        uhy.e((Object) th, "");
        try {
            a((Response) null, th);
        } catch (Throwable th2) {
            i.c("FaqCallback", th2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void a(String str) {
        try {
            onResult(null, this.b.fromJson(str, (Class) this.f8231a));
        } catch (Throwable th) {
            onResult(th, null);
            i.c("doResult", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(FaqCallback faqCallback, Throwable th) {
        uhy.e((Object) faqCallback, "");
        faqCallback.onResult(th, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(FaqCallback faqCallback, String str) {
        uhy.e((Object) faqCallback, "");
        faqCallback.a(str);
    }

    private final void a(Response response, final Throwable th) {
        WeakReference<Activity> weakReference = this.c;
        Activity activity = weakReference != null ? weakReference.get() : null;
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.phoneservice.faq.base.network.e
                @Override // java.lang.Runnable
                public final void run() {
                    FaqCallback.a(FaqCallback.this, th);
                }
            });
        } else {
            onResult(th, null);
        }
    }

    private final void a(Response response, final String str) {
        WeakReference<Activity> weakReference = this.c;
        Activity activity = weakReference != null ? weakReference.get() : null;
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.phoneservice.faq.base.network.d
                @Override // java.lang.Runnable
                public final void run() {
                    FaqCallback.a(FaqCallback.this, str);
                }
            });
        } else {
            a(str);
        }
    }

    public FaqCallback(Class<T> cls, Activity activity) {
        uhy.e((Object) cls, "");
        this.f8231a = cls;
        this.b = new Gson();
        this.c = new WeakReference<>(activity);
    }
}
