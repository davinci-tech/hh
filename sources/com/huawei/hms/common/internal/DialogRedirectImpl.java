package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes9.dex */
public class DialogRedirectImpl extends DialogRedirect {

    /* renamed from: a, reason: collision with root package name */
    private final Activity f4454a;
    private final int b;
    private final Intent c;

    DialogRedirectImpl(Intent intent, Activity activity, int i) {
        this.c = intent;
        this.f4454a = activity;
        this.b = i;
    }

    @Override // com.huawei.hms.common.internal.DialogRedirect
    public final void redirect() {
        Activity activity;
        Intent intent = this.c;
        if (intent == null || (activity = this.f4454a) == null) {
            return;
        }
        activity.startActivityForResult(intent, this.b);
    }
}
