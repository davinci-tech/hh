package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionBannerCard;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionBannerCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2671a;
    private HealthTextView b;
    private HealthTextView c;

    public SectionBannerCard(Context context) {
        this(context, null);
    }

    public SectionBannerCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionBannerCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_banner_card_layout, (ViewGroup) this, false);
        this.c = (HealthTextView) inflate.findViewById(R.id.content);
        this.b = (HealthTextView) inflate.findViewById(R.id.cancel);
        this.f2671a = (HealthTextView) inflate.findViewById(R.id.submit);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.c, nru.b(hashMap, "BANNER_CARD_CONTENT", ""));
        nsy.cMr_(this.b, nru.b(hashMap, "BANNER_CARD_CANCEL", ""));
        nsy.cMr_(this.f2671a, nru.b(hashMap, "BANNER_CARD_SUBMIT", ""));
        setClickListenerEvent(hashMap.get("CLICK_EVENT_LISTENER"));
    }

    private void setClickListenerEvent(Object obj) {
        if (obj instanceof OnClickSectionListener) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) obj;
            HealthTextView healthTextView = this.b;
            if (healthTextView != null) {
                healthTextView.setOnClickListener(new View.OnClickListener() { // from class: egb
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SectionBannerCard.aiI_(OnClickSectionListener.this, view);
                    }
                });
            }
            HealthTextView healthTextView2 = this.f2671a;
            if (healthTextView2 != null) {
                healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: egg
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SectionBannerCard.aiJ_(OnClickSectionListener.this, view);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void aiI_(OnClickSectionListener onClickSectionListener, View view) {
        onClickSectionListener.onClick("BANNER_CARD_CANCEL_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aiJ_(OnClickSectionListener onClickSectionListener, View view) {
        onClickSectionListener.onClick("BANNER_CARD_SUBMIT_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionUpdate";
    }
}
