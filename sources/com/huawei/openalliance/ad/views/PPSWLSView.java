package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.ChoicesView;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.LabelPosition;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes9.dex */
public class PPSWLSView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private PPSSplashLabelView f7983a;
    private TextView b;
    private ChoicesView c;
    private jb d;
    private WeakReference<PPSLinkedView> e;
    private Integer f;
    private Integer g;
    private View.OnClickListener h;

    public void setPpsLinkedView(PPSLinkedView pPSLinkedView) {
        this.e = new WeakReference<>(pPSLinkedView);
    }

    public void setChoiceViewOnClickListener(View.OnClickListener onClickListener) {
        this.h = onClickListener;
    }

    public void setAdMediator(jb jbVar) {
        this.d = jbVar;
    }

    public int[] getChoiceViewSize() {
        return dd.c(this.c);
    }

    public int[] getChoiceViewLoc() {
        return dd.b(this.c);
    }

    public void a(Integer num, Integer num2) {
        this.f = num;
        this.g = num2;
    }

    public void a(ContentRecord contentRecord, boolean z, int i, int i2, boolean z2) {
        ViewGroup.LayoutParams layoutParams;
        ho.b("PPSWLSView", "positionAndSet. ");
        String P = contentRecord.P() == null ? LabelPosition.LOWER_LEFT : contentRecord.P();
        this.c.setVisibility(0);
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131363388_res_0x7f0a063c);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen._2131363389_res_0x7f0a063d);
        if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
            if ("tr".equals(P)) {
                Cdo.a(getContext(), i, i2, z2, dimensionPixelSize, dimensionPixelSize2, layoutParams3);
                layoutParams = layoutParams3;
            } else {
                layoutParams = layoutParams3;
                a(z, i, i2, z2, dimensionPixelSize, dimensionPixelSize2, layoutParams3);
            }
            setLayoutParams(layoutParams);
        }
        b(contentRecord, P);
        a(contentRecord, P);
        a(contentRecord);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PPSLinkedView getPpsLinkedView() {
        WeakReference<PPSLinkedView> weakReference = this.e;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void c(String str) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
        layoutParams.addRule(6, R.id.hiad_ad_label_wls);
        layoutParams.addRule(8, R.id.hiad_ad_label_wls);
        if (!this.f7983a.a()) {
            layoutParams.addRule(15);
        }
        layoutParams.addRule("tr".equals(str) ? 16 : 17, R.id.hiad_ad_label_wls);
        this.b.setLayoutParams(layoutParams);
    }

    private void b(String str) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f7983a.getLayoutParams();
        layoutParams.addRule(15);
        layoutParams.addRule("tr".equals(str) ? 16 : 17, R.id.splash_why_this_ad);
        this.f7983a.setLayoutParams(layoutParams);
    }

    private void b(final ContentRecord contentRecord, String str) {
        a(str);
        String c = cz.c(contentRecord.ak());
        String c2 = cz.c(contentRecord.al());
        if (!TextUtils.isEmpty(c)) {
            if (TextUtils.isEmpty(c2)) {
                this.c.b();
            } else {
                this.c.setAdChoiceIcon(c2);
            }
        }
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSWLSView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ho.a()) {
                    ho.a("PPSWLSView", "choiceView onclick");
                }
                if (PPSWLSView.this.h != null) {
                    PPSWLSView.this.h.onClick(view);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (ao.a(PPSWLSView.this.getContext(), contentRecord)) {
                    if (PPSWLSView.this.d != null) {
                        PPSWLSView.this.d.e();
                    }
                    if (PPSWLSView.this.getPpsLinkedView() != null) {
                        PPSWLSView.this.getPpsLinkedView().a((Integer) 10, true);
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a(boolean z, int i, int i2, boolean z2, int i3, int i4, RelativeLayout.LayoutParams layoutParams) {
        layoutParams.addRule(12);
        layoutParams.addRule(20);
        layoutParams.leftMargin = i3;
        layoutParams.setMarginStart(i3);
        layoutParams.bottomMargin = i4;
        if (i2 == 0) {
            if (bz.b(getContext()) && z2) {
                layoutParams.setMarginStart(layoutParams.leftMargin + i);
                layoutParams.leftMargin += i;
            } else if (!bz.b(getContext()) || (bz.b(getContext()) && TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1)) {
                layoutParams.setMarginStart(dd.f(getContext()));
                layoutParams.leftMargin = dd.f(getContext());
            }
            if (z) {
                return;
            }
            if (!com.huawei.openalliance.ad.utils.x.n(getContext()) && !com.huawei.openalliance.ad.utils.x.q(getContext())) {
                return;
            }
        } else if (z) {
            return;
        }
        layoutParams.bottomMargin += dd.f(getContext());
    }

    private void a(String str) {
        Resources resources = getResources();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
        if ("tr".equals(str)) {
            layoutParams.addRule(10);
            layoutParams.addRule(21);
            layoutParams.setMarginStart(resources.getDimensionPixelSize(R.dimen._2131363334_res_0x7f0a0606));
        } else {
            layoutParams.addRule(12);
            layoutParams.addRule(20);
            layoutParams.setMarginEnd(resources.getDimensionPixelSize(R.dimen._2131363334_res_0x7f0a0606));
        }
        this.c.setLayoutParams(layoutParams);
    }

    private void a(ContentRecord contentRecord, String str) {
        b(str);
        String N = contentRecord.N();
        this.f7983a.setDataAndRefreshUi(contentRecord);
        if (!TextUtils.isEmpty(N) && this.f7983a.a()) {
            this.f7983a.a(null, N, this.f, false);
            this.f7983a.setVisibility(0);
        } else {
            ViewGroup.LayoutParams layoutParams = this.f7983a.getLayoutParams();
            layoutParams.width = 0;
            this.f7983a.setLayoutParams(layoutParams);
            this.f7983a.setVisibility(4);
        }
    }

    private void a(ContentRecord contentRecord) {
        MetaData h = contentRecord.h();
        if (h != null) {
            String c = cz.c(h.k());
            if (TextUtils.isEmpty(c)) {
                this.b.setVisibility(8);
                return;
            }
            this.b.setText(c);
            this.b.setVisibility(0);
            c(contentRecord.P());
        }
    }

    private void a(Context context) {
        inflate(context, R.layout.hiad_wls_view, this);
        ChoicesView choicesView = (ChoicesView) findViewById(R.id.splash_why_this_ad);
        this.c = choicesView;
        choicesView.setVisibility(8);
        PPSSplashLabelView pPSSplashLabelView = (PPSSplashLabelView) findViewById(R.id.hiad_ad_label_wls);
        this.f7983a = pPSSplashLabelView;
        pPSSplashLabelView.setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.hiad_ad_source_wls);
        this.b = textView;
        textView.setVisibility(8);
    }

    public PPSWLSView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        a(context);
    }

    public PPSWLSView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public PPSWLSView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PPSWLSView(Context context) {
        super(context, null);
        a(context);
    }
}
