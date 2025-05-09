package com.huawei.ui.homewear21.home.holder;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class WearHomeVipHolder extends RecyclerView.ViewHolder {
    private LinearLayout e;

    public WearHomeVipHolder(View view) {
        super(view);
        if (view == null) {
            return;
        }
        this.e = (LinearLayout) nsy.cMd_(view, R.id.vip_layout);
    }

    public LinearLayout dmH_() {
        return this.e;
    }
}
