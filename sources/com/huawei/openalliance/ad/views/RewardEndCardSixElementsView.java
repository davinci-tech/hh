package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;

/* loaded from: classes9.dex */
public class RewardEndCardSixElementsView extends SixElementsView {
    private TextView q;

    @Override // com.huawei.openalliance.ad.views.SixElementsView
    public void a(AdLandingPageData adLandingPageData) {
        super.a(adLandingPageData);
        TextView textView = this.q;
        if (textView != null) {
            textView.setText(com.huawei.openalliance.ad.utils.c.a(this.o, this.b, true));
        }
    }

    @Override // com.huawei.openalliance.ad.views.SixElementsView
    protected void a(Context context, AttributeSet attributeSet) {
        b(context, attributeSet);
        this.b = context;
        if (b()) {
            this.c = inflate(context, R.layout.reward_endcard_six_elements_elderly_layout, this);
            this.m = (TextView) this.c.findViewById(R.id.endcard_six_elements_splicing);
        } else {
            this.c = inflate(context, this.f8017a == 1 ? R.layout.reward_endcard_six_elements_center_layout : R.layout.reward_endcard_six_elements_layout, this);
            this.f = (TextView) this.c.findViewById(R.id.endcard_six_elements_version);
            this.g = (TextView) this.c.findViewById(R.id.endcard_six_elements_desc);
            this.g.setOnClickListener(this);
            this.h = (TextView) this.c.findViewById(R.id.endcard_six_elements_privacy_policy);
            this.h.setOnClickListener(this);
            this.i = (TextView) this.c.findViewById(R.id.endcard_six_elements_permission);
            this.i.setOnClickListener(this);
            this.j = (TextView) this.c.findViewById(R.id.endcard_version_line);
            this.k = (TextView) this.c.findViewById(R.id.endcard_privacy_line);
            this.l = (TextView) this.c.findViewById(R.id.endcard_permission_line);
            a(this.c, true);
        }
        this.q = (TextView) this.c.findViewById(R.id.reward_endcard_jump_text);
        this.d = (TextView) this.c.findViewById(R.id.endcard_six_elements_name);
        this.e = (TextView) this.c.findViewById(R.id.endcard_six_elements_develop_name);
        a();
    }

    public RewardEndCardSixElementsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RewardEndCardSixElementsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RewardEndCardSixElementsView(Context context) {
        super(context);
    }
}
