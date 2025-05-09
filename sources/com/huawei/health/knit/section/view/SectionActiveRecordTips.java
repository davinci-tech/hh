package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.view.SectionActiveRecordTips;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.nru;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionActiveRecordTips extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2664a;
    private HealthImageView b;
    private HealthButton c;
    private View d;
    private HealthTextView e;
    private HealthTextView h;

    public SectionActiveRecordTips(Context context) {
        super(context);
    }

    public SectionActiveRecordTips(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionActiveRecordTips(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionActiveRecordTips", "onCreateView: ");
        if (this.d == null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_active_record_tips_item, (ViewGroup) this, false);
            this.d = inflate;
            this.h = (HealthTextView) inflate.findViewById(R.id.tips_item_tv);
            this.e = (HealthTextView) this.d.findViewById(R.id.tips_item_button_tv);
            this.f2664a = (ImageView) this.d.findViewById(R.id.tips_item_img);
            this.c = (HealthButton) this.d.findViewById(R.id.go_sport_button);
            this.b = (HealthImageView) this.d.findViewById(R.id.tips_description);
        }
        return this.d;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionActiveRecordTips", "bindParamsToView enter ");
        setViewData(hashMap);
    }

    private void setViewData(HashMap<String, Object> hashMap) {
        String b = nru.b(hashMap, "ITEM_TITLE", "");
        String b2 = nru.b(hashMap, "ITEM_SUBTITLE", "");
        int d = nru.d((Map) hashMap, "BACKGROUND_IMAGE", 0);
        String b3 = nru.b(hashMap, "RIGHT_BUTTON_TEXT", "");
        final BaseKnitDataProvider.d dVar = (BaseKnitDataProvider.d) nru.c(hashMap, "CLICK_EVENT_LISTENER", BaseKnitDataProvider.d.class, null);
        this.h.setText(b);
        this.e.setText(b2);
        d();
        this.f2664a.setBackground(nsf.cKq_(d));
        if (LanguageUtil.bc(getContext())) {
            this.f2664a.setRotationY(180.0f);
        }
        this.c.setText(b3);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionActiveRecordTips.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseKnitDataProvider.d dVar2 = dVar;
                if (dVar2 != null) {
                    dVar2.onClick(0);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        final BaseKnitDataProvider.d dVar2 = (BaseKnitDataProvider.d) nru.c(hashMap, "LEFT_BUTTON_VISIBILITY", BaseKnitDataProvider.d.class, null);
        if (dVar2 != null) {
            this.b.setVisibility(0);
            this.b.setOnClickListener(new View.OnClickListener() { // from class: egf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SectionActiveRecordTips.aiB_(BaseKnitDataProvider.d.this, view);
                }
            });
        } else {
            this.b.setVisibility(8);
        }
    }

    public static /* synthetic */ void aiB_(BaseKnitDataProvider.d dVar, View view) {
        dVar.onClick(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        if (this.h.getTextLine() > 3 || this.e.getTextLine() > 3) {
            LogUtil.a("SectionActiveRecordTips", "title text lines more than 3");
            this.h.setTextSize(getContext().getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6));
            this.e.setTextSize(getContext().getResources().getDimensionPixelSize(R.dimen._2131365063_res_0x7f0a0cc7));
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionActiveRecordTips";
    }
}
