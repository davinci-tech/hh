package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.nrt;
import defpackage.ojs;
import defpackage.owo;

/* loaded from: classes6.dex */
public class FunctionSetCardManagementDeleteChineseViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f9444a;
    private final HealthTextView b;
    private RelativeLayout c;
    private ImageView e;

    FunctionSetCardManagementDeleteChineseViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.b = (HealthTextView) view.findViewById(R.id.itemText);
        this.f9444a = (ImageView) view.findViewById(R.id.icon);
        this.e = (ImageView) view.findViewById(R.id.card_small_img);
        this.c = (RelativeLayout) view.findViewById(R.id.card_content_layout);
    }

    public void e(ojs ojsVar, boolean z) {
        if (z) {
            this.f9444a.setVisibility(0);
            if (nrt.a(getContext())) {
                this.f9444a.setBackground(getContext().getResources().getDrawable(R.drawable._2131427530_res_0x7f0b00ca));
            } else {
                this.f9444a.setBackground(getContext().getResources().getDrawable(R.drawable._2131427529_res_0x7f0b00c9));
            }
        } else {
            this.f9444a.setVisibility(8);
        }
        owo.djb_(getContext(), ojsVar, this.b, this.e);
    }

    public ImageView dbO_() {
        return this.f9444a;
    }

    public RelativeLayout dbN_() {
        return this.c;
    }
}
