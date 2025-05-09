package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.hwid.SignInResult;
import com.huawei.hwcloudjs.core.JsCallback;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
class i implements ResultCallback<SignInResult> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f6242a;
    final /* synthetic */ WeakReference b;
    final /* synthetic */ WeakReference c;
    final /* synthetic */ boolean d;
    final /* synthetic */ JsCallback e;
    final /* synthetic */ HmsCoreApi f;

    @Override // com.huawei.hms.support.api.client.ResultCallback
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(SignInResult signInResult) {
        this.f.a(signInResult, this.f6242a, (WeakReference<Activity>) this.b, (WeakReference<HuaweiApiClient>) this.c, this.d, this.e);
    }

    i(HmsCoreApi hmsCoreApi, boolean z, WeakReference weakReference, WeakReference weakReference2, boolean z2, JsCallback jsCallback) {
        this.f = hmsCoreApi;
        this.f6242a = z;
        this.b = weakReference;
        this.c = weakReference2;
        this.d = z2;
        this.e = jsCallback;
    }
}
