package com.huawei.hms.a.a.d;

import android.content.Context;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.utils.PickerHiAnalyticsUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.common.PickerCommonNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hwidauth.api.ResultCallBack;
import defpackage.kqg;
import defpackage.ksy;

/* loaded from: classes9.dex */
public class c implements ResultCallBack<kqg> {

    /* renamed from: a, reason: collision with root package name */
    private TaskCompletionSource<Void> f4245a = new TaskCompletionSource<>();
    private Context b;
    private String c;

    public TaskCompletionSource<Void> a() {
        return this.f4245a;
    }

    public c(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    @Override // com.huawei.hwidauth.api.ResultCallBack
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onResult(kqg kqgVar) {
        if (kqgVar.e()) {
            ksy.b("[ACCOUNTSDK]AccountPickerSignOutCallBack", "ClearAccountResult is success.", true);
            this.f4245a.setResult(null);
            a(0);
            return;
        }
        ksy.b("[ACCOUNTSDK]AccountPickerSignOutCallBack", "ClearAccountResult is error: " + kqgVar.getStatus().e(), true);
        ksy.b("[ACCOUNTSDK]AccountPickerSignOutCallBack", "ClearAccountResult is error: " + kqgVar.getStatus().e() + " status message " + kqgVar.getStatus().a(), false);
        this.f4245a.setException(new ApiException(new Status(2015, kqgVar.getStatus().a())));
        a(2015);
    }

    private void a(int i) {
        HiAnalyticsClient.reportExit(this.b, PickerCommonNaming.AccountPickerSignout, this.c, PickerHiAnalyticsUtil.getHiAnalyticsStatus(i), i);
    }
}
