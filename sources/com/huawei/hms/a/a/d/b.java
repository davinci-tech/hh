package com.huawei.hms.a.a.d;

import android.content.Context;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.utils.PickerHiAnalyticsUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.common.PickerCommonNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hwidauth.api.ResultCallBack;
import defpackage.kqp;
import defpackage.ksy;

/* loaded from: classes9.dex */
public class b implements ResultCallBack<kqp> {

    /* renamed from: a, reason: collision with root package name */
    private Context f4244a;
    private String b;
    private TaskCompletionSource<Void> c = new TaskCompletionSource<>();

    public b(Context context, String str) {
        this.f4244a = context;
        this.b = str;
    }

    public TaskCompletionSource<Void> a() {
        return this.c;
    }

    @Override // com.huawei.hwidauth.api.ResultCallBack
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(kqp kqpVar) {
        int e = kqpVar.getStatus().e();
        if (200 == e) {
            ksy.b("[ACCOUNTSDK]AccountPickerRevokeCallBack", "RevokeResult is success.", true);
            this.c.setResult(null);
            a(0);
            return;
        }
        ksy.b("[ACCOUNTSDK]AccountPickerRevokeCallBack", "RevokeResult is error: " + e, true);
        ksy.b("[ACCOUNTSDK]AccountPickerRevokeCallBack", "RevokeResult is error: " + e + " status message " + kqpVar.getStatus().a(), false);
        String a2 = kqpVar.getStatus().a();
        if (404 == e) {
            this.c.setException(new ApiException(new Status(e, "cancel authorization request error.")));
            a(2005);
        } else {
            this.c.setException(new ApiException(new Status(e, a2)));
            a(e);
        }
    }

    private void a(int i) {
        HiAnalyticsClient.reportExit(this.f4244a, PickerCommonNaming.AccountPickerRevokeAccess, this.b, PickerHiAnalyticsUtil.getHiAnalyticsStatus(i), i);
    }
}
