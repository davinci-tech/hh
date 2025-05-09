package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.ojs;
import defpackage.owo;

/* loaded from: classes6.dex */
public class FunctionSetCardManagementDeleteViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthDivider f9445a;
    private ImageView d;
    private final HealthTextView e;

    FunctionSetCardManagementDeleteViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.e = (HealthTextView) view.findViewById(R.id.itemText);
        this.d = (ImageView) view.findViewById(R.id.itemAdd);
        this.f9445a = (HealthDivider) view.findViewById(R.id.healthDividerBar);
    }

    public void c(ojs ojsVar) {
        owo.e(ojsVar, this.e);
    }

    public ImageView dbP_() {
        return this.d;
    }

    public HealthDivider b() {
        return this.f9445a;
    }
}
