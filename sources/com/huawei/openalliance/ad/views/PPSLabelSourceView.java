package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes5.dex */
public class PPSLabelSourceView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private PPSLabelView f7887a;
    private TextView b;
    private TextView c;
    private boolean d;
    private boolean e;

    private int b(boolean z) {
        return z ? R.layout.hiad_ad_label_source_with_click : R.layout.hiad_ad_label_source;
    }

    public TextView getAdSource() {
        return this.b;
    }

    public PPSLabelView getAdLabel() {
        return this.f7887a;
    }

    public TextView getAdJumpText() {
        return this.c;
    }

    public void b(Context context, boolean z) {
        if (!this.d) {
            inflate(context, b(z), this);
        }
        this.d = true;
        this.f7887a = (PPSLabelView) findViewById(R.id.hiad_ad_label);
        this.b = (TextView) findViewById(R.id.hiad_ad_source);
        this.c = (TextView) findViewById(R.id.hiad_ad_jump_text);
    }

    public void a(jk jkVar, ContentRecord contentRecord, boolean z) {
        PPSLabelView pPSLabelView = this.f7887a;
        if (pPSLabelView != null) {
            pPSLabelView.a(jkVar, contentRecord, z);
        }
    }

    public void a(Context context, boolean z) {
        if (!this.d) {
            this.e = z;
        }
        b(context, z);
        if (this.e != z) {
            this.e = z;
            a(z);
        }
    }

    private void a(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.f7887a.getParent();
        this.c.setBackgroundColor(getResources().getColor(R.color._2131298002_res_0x7f0906d2));
        Resources resources = getResources();
        if (z) {
            viewGroup.setBackgroundColor(resources.getColor(R.color._2131298002_res_0x7f0906d2));
            this.f7887a.setBackground(getResources().getDrawable(R.drawable._2131428502_res_0x7f0b0496));
            this.b.setBackground(getResources().getDrawable(R.drawable._2131428502_res_0x7f0b0496));
        } else {
            viewGroup.setBackground(resources.getDrawable(R.drawable._2131428502_res_0x7f0b0496));
            this.f7887a.setBackgroundColor(getResources().getColor(R.color._2131298002_res_0x7f0906d2));
            this.b.setBackgroundColor(getResources().getColor(R.color._2131298002_res_0x7f0906d2));
        }
        int a2 = ao.a(getContext(), getResources().getDimension(R.dimen._2131363274_res_0x7f0a05ca));
        viewGroup.setPadding(0, 0, 0, 0);
        this.f7887a.setPadding(a2, 0, z ? a2 : 0, 0);
        this.c.setPadding(0, 0, a2, 0);
        this.b.setPadding(z ? a2 : 0, 0, a2, 0);
        this.c.setTextAlignment(5);
    }

    public PPSLabelSourceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.d = false;
        this.e = false;
    }

    public PPSLabelSourceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.e = false;
    }

    public PPSLabelSourceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.e = false;
    }

    public PPSLabelSourceView(Context context) {
        super(context, null);
        this.d = false;
        this.e = false;
    }
}
