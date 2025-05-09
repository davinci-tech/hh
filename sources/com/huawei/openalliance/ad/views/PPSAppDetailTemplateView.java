package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dk;

/* loaded from: classes5.dex */
public class PPSAppDetailTemplateView extends PPSAppDetailView {
    private TextView e;
    private TextView f;
    private int g;

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100651_res_0x7f0603eb})) == null) {
            return;
        }
        try {
            int integer = obtainStyledAttributes.getInteger(0, 2);
            this.g = integer;
            ho.a("PPSAppDetailTemplateView", "insreTemplate %s", Integer.valueOf(integer));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected void a() {
        super.a();
        this.e = (TextView) findViewById(R.id.app_name);
        this.f = (TextView) findViewById(R.id.app_develop_name);
        AppInfo appInfo = getAppInfo();
        if (appInfo != null) {
            this.e.setText(appInfo.getAppName());
            this.f.setText(appInfo.getDeveloperName());
        }
        c(getContext());
    }

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected int a(Context context) {
        return this.g == 4 ? R.layout.hiad_landing_app_detail_insretemplate4 : R.layout.hiad_landing_app_detail_template_custom;
    }

    private void c(Context context) {
        if (!ao.n(context)) {
            ho.b("PPSAppDetailTemplateView", "do not deal elderly font.");
            return;
        }
        TextView textView = this.e;
        if (textView == null || this.f == null) {
            ho.c("PPSAppDetailTemplateView", "appName or appDeveloper tv is null.");
            return;
        }
        a(textView, 28);
        a(this.f, 21);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, (int) dk.a(context, 4), 0, 0);
        if (this.g != 4) {
            this.e.setLayoutParams(layoutParams);
        }
        this.f.setLayoutParams(layoutParams);
    }

    private void a(TextView textView, int i) {
        if (textView != null) {
            textView.setTextSize(1, i);
        }
    }

    public PPSAppDetailTemplateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PPSAppDetailTemplateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PPSAppDetailTemplateView(Context context) {
        super(context);
    }
}
