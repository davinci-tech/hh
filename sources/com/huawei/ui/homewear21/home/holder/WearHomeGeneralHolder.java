package com.huawei.ui.homewear21.home.holder;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class WearHomeGeneralHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9674a;
    private HealthRecycleView c;

    public WearHomeGeneralHolder(View view) {
        super(view);
        this.f9674a = (LinearLayout) nsy.cMd_(view, R.id.home_general_layout);
        this.c = (HealthRecycleView) nsy.cMd_(view, R.id.general_list);
    }

    public HealthRecycleView d() {
        return this.c;
    }

    public LinearLayout dmw_() {
        return this.f9674a;
    }
}
