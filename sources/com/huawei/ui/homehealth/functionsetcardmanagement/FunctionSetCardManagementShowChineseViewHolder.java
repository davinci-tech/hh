package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.homeinterface.OnStartDragListener;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.nrt;
import defpackage.ojs;
import defpackage.owo;

/* loaded from: classes6.dex */
public class FunctionSetCardManagementShowChineseViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9447a;
    private final HealthTextView b;
    private ImageView c;
    private ImageView d;
    private final OnStartDragListener e;

    FunctionSetCardManagementShowChineseViewHolder(View view, Context context, boolean z, OnStartDragListener onStartDragListener) {
        super(view, context, z);
        this.b = (HealthTextView) view.findViewById(R.id.itemText);
        this.f9447a = (RelativeLayout) view.findViewById(R.id.card_content_layout);
        this.e = onStartDragListener;
        this.d = (ImageView) view.findViewById(R.id.itemDelete);
        this.d = (ImageView) view.findViewById(R.id.icon);
        this.c = (ImageView) view.findViewById(R.id.card_small_img);
    }

    public void c(ojs ojsVar, boolean z) {
        if (z) {
            this.d.setVisibility(0);
            if (nrt.a(getContext())) {
                this.d.setBackground(getContext().getResources().getDrawable(R.drawable._2131427998_res_0x7f0b029e));
            } else {
                this.d.setBackground(getContext().getResources().getDrawable(R.drawable._2131427997_res_0x7f0b029d));
            }
        } else {
            this.d.setVisibility(8);
        }
        owo.djb_(getContext(), ojsVar, this.b, this.c);
    }

    public ImageView dbU_() {
        return this.d;
    }

    public RelativeLayout dbT_() {
        return this.f9447a;
    }
}
