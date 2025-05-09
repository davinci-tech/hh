package com.huawei.openalliance.ad.views.feedback;

import android.content.Context;
import android.view.View;
import com.huawei.hms.ads.AdFeedbackListener;

/* loaded from: classes5.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private View f8070a;
    private AdFeedbackListener b;
    private AdFeedbackListener c;
    private Context d = null;

    public Context d() {
        return this.d;
    }

    public AdFeedbackListener c() {
        return this.c;
    }

    public void b(AdFeedbackListener adFeedbackListener) {
        this.c = adFeedbackListener;
    }

    public AdFeedbackListener b() {
        return this.b;
    }

    public void a(AdFeedbackListener adFeedbackListener) {
        this.b = adFeedbackListener;
    }

    public void a(View view) {
        this.f8070a = view;
    }

    public void a(Context context) {
        this.d = context;
    }

    public View a() {
        return this.f8070a;
    }
}
