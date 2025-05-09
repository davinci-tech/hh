package com.huawei.ui.homehealth.smartcard;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;

/* loaded from: classes6.dex */
public class SmartCardViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    public HealthTextView f9619a;
    public ImageView b;
    public RelativeLayout c;
    public ImageView d;
    public View e;
    public HealthTextView h;
    public HealthTextView i;
    public HealthTextView j;

    public SmartCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        if (context != null) {
            View inflate = View.inflate(context, R.layout.pop_custom_view_dialog, null);
            this.e = inflate;
            if (inflate != null) {
                this.h = (HealthTextView) inflate.findViewById(R.id.hw_health_smart_card_pop_text1);
            }
        }
        this.c = (RelativeLayout) view.findViewById(R.id.hw_health_smart_card_layout);
        this.d = (ImageView) view.findViewById(R.id.hw_health_smart_card_more);
        this.b = (ImageView) view.findViewById(R.id.hw_health_smart_card_background);
        this.f9619a = (HealthTextView) view.findViewById(R.id.hw_health_onboarding_view_text1);
        this.i = (HealthTextView) view.findViewById(R.id.hw_health_onboarding_view_text2);
        this.j = (HealthTextView) view.findViewById(R.id.hw_health_onboarding_view_text3);
        BaseActivity.setViewSafeRegion(true, view.findViewById(R.id.hw_health_smart_card_content));
    }
}
