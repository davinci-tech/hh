package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.HwLog;
import java.lang.reflect.ParameterizedType;

/* loaded from: classes7.dex */
public abstract class cc<T> extends BaseHttpRequest<T> {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10944a = "cc";

    protected abstract String b(String str);

    protected abstract String buildRequestParams();

    protected void a(final ResponseListener<T> responseListener, final String str) {
        if (responseListener == null) {
            return;
        }
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.cc$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                cc.this.a(str, responseListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, ResponseListener responseListener) {
        String b = b(str);
        if (b == null || TextUtils.isEmpty(b)) {
            HwLog.e(f10944a, "response is null or empty");
            responseListener.onError();
            return;
        }
        T c = c(b);
        if (c != null) {
            responseListener.onResponse(c);
        } else {
            responseListener.onError();
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public T c(String str) {
        return a(str, (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public T a(String str, Class<T> cls) {
        try {
            return (T) GsonHelper.fromJson(str, (Class) cls);
        } catch (Exception e) {
            HwLog.e(f10944a, "handleJsonData exception: " + HwLog.printException(e));
            return null;
        }
    }
}
