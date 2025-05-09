package com.huawei.openalliance.ad.views.compliance;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.hk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdvertiserInfo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.views.h;
import java.util.List;

/* loaded from: classes9.dex */
public class ComplianceView extends h {
    private View k;
    private TextView l;
    private RelativeLayout m;
    private TextView n;
    private ContentRecord o;
    private ImageView p;
    private hk q;

    @Override // com.huawei.openalliance.ad.views.h
    public void setViewClickListener(hk hkVar) {
        this.q = hkVar;
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void setAdContent(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        this.o = contentRecord;
        a();
        b();
        c();
        f();
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void c() {
        try {
            ho.b("ComplianceView", "adapterView mFeedbackViewPaddingLeft = %s, mFeedbackViewPaddingRight= %s", Integer.valueOf(this.g), Integer.valueOf(this.h));
            if (d()) {
                this.b.setPadding(this.g, 0, this.h, 0);
                this.b.requestLayout();
                this.b.getViewTreeObserver().addOnGlobalLayoutListener(this.j);
            }
        } catch (Throwable th) {
            ho.c("ComplianceView", "adapterView error, %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void b(Context context) {
        ImageView imageView = (ImageView) findViewById(R.id.right_arrow);
        this.p = imageView;
        if (imageView != null) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131428545_res_0x7f0b04c1);
            if (dd.c()) {
                this.p.setImageBitmap(az.b(drawable));
            }
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void a(Context context) {
        try {
            View inflate = LayoutInflater.from(context).inflate(R.layout.hiad_compliance_choice_view, this);
            this.b = inflate.findViewById(R.id.compliance_view_root);
            this.k = inflate.findViewById(R.id.why_this_ad_line);
            this.l = (TextView) inflate.findViewById(R.id.compliance_info);
            this.m = (RelativeLayout) inflate.findViewById(R.id.why_this_ad_btn);
            this.c = inflate.findViewById(R.id.compliance_scrollview);
            this.n = (TextView) inflate.findViewById(R.id.why_this_ad_tv);
        } catch (Throwable th) {
            ho.c("ComplianceView", "initView error, %s", th.getClass().getSimpleName());
        }
    }

    private void f() {
        TextView textView;
        if (!ao.n(getContext()) || (textView = this.l) == null || this.n == null) {
            return;
        }
        textView.setTextSize(1, 28.0f);
        this.n.setTextSize(1, 28.0f);
    }

    private void b() {
        String value;
        ContentRecord contentRecord = this.o;
        if (contentRecord != null) {
            List<AdvertiserInfo> aS = contentRecord.aS();
            StringBuffer stringBuffer = new StringBuffer();
            if (bg.a(aS)) {
                ho.b("ComplianceView", "complianceInfo is null");
                return;
            }
            for (int i = 0; i < aS.size(); i++) {
                if (i != aS.size() - 1) {
                    stringBuffer.append(aS.get(i).getValue());
                    value = ", ";
                } else {
                    value = aS.get(i).getValue();
                }
                stringBuffer.append(value);
            }
            TextView textView = this.l;
            if (textView != null) {
                textView.setText(stringBuffer);
            }
        }
    }

    private void a() {
        if (this.k == null || this.m == null) {
            ho.b("ComplianceView", "partingLine or whyThisAdClick view not init");
            return;
        }
        if (this.i != null && !this.i.booleanValue()) {
            ho.b("ComplianceView", "not need show why this ad");
            return;
        }
        this.k.setVisibility(0);
        this.m.setVisibility(0);
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.compliance.ComplianceView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComplianceView.this.o != null) {
                    ao.a(ComplianceView.this.getContext(), ComplianceView.this.o);
                    if (ComplianceView.this.q != null) {
                        ComplianceView.this.q.a();
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public ComplianceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ComplianceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ComplianceView(Context context) {
        super(context);
    }
}
