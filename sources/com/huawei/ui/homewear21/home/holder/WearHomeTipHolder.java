package com.huawei.ui.homewear21.home.holder;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class WearHomeTipHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9676a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;

    public WearHomeTipHolder(View view) {
        super(view);
        this.f9676a = (RelativeLayout) nsy.cMd_(view, R.id.common_auto_test_toast_layout);
        this.e = (HealthTextView) nsy.cMd_(view, R.id.toast_cancel_tv);
        this.c = (HealthTextView) nsy.cMd_(view, R.id.toast_try_tv);
        this.d = (HealthTextView) nsy.cMd_(view, R.id.toast_no_notice_tv);
        this.b = (HealthTextView) nsy.cMd_(view, R.id.toast_title_tv);
    }

    public HealthTextView c() {
        return this.c;
    }

    public HealthTextView b() {
        return this.e;
    }

    public HealthTextView d() {
        return this.d;
    }

    public HealthTextView e() {
        return this.b;
    }

    public RelativeLayout dmG_() {
        return this.f9676a;
    }
}
