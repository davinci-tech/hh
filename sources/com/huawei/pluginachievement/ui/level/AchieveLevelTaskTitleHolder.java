package com.huawei.pluginachievement.ui.level;

import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.mfm;
import defpackage.mkg;

/* loaded from: classes9.dex */
public class AchieveLevelTaskTitleHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f8447a;

    AchieveLevelTaskTitleHolder(View view) {
        super(view);
        HealthSubHeader healthSubHeader = (HealthSubHeader) mfm.cgM_(view, R.id.achieve_task_title);
        this.f8447a = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(0);
    }

    public void b(mkg mkgVar) {
        if (mkgVar != null) {
            this.f8447a.setVisibility(0);
            if (TextUtils.isEmpty(mkgVar.e())) {
                this.f8447a.setVisibility(8);
            } else {
                this.f8447a.setVisibility(0);
                this.f8447a.setHeadTitleText(mkgVar.e());
            }
        }
    }
}
