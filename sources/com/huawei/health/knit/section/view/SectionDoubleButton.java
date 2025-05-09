package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.nru;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionDoubleButton extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthCardView f2680a;
    private HealthCardView b;
    private ImageView c;
    private HealthTextView d;
    private ImageView e;
    private HealthTextView h;

    public SectionDoubleButton(Context context) {
        super(context);
    }

    public SectionDoubleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionDoubleButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionDoubleButton", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_double_button, (ViewGroup) this, false);
        this.b = (HealthCardView) inflate.findViewById(R.id.left_button);
        this.f2680a = (HealthCardView) inflate.findViewById(R.id.right_button);
        this.c = (ImageView) inflate.findViewById(R.id.left_button_image);
        this.d = (HealthTextView) inflate.findViewById(R.id.left_button_text);
        this.e = (ImageView) inflate.findViewById(R.id.right_button_image);
        this.h = (HealthTextView) inflate.findViewById(R.id.right_button_text);
        if (!LanguageUtil.m(context) && !LanguageUtil.p(context)) {
            this.d.setAllCaps(true);
            this.h.setAllCaps(true);
        }
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionDoubleButton", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionDoubleButton", "no need to bind");
            return;
        }
        nsy.cMA_(this.b, nru.d((Map) hashMap, "LEFT_BUTTON_VISIBILITY", 0));
        nsy.cMA_(this.f2680a, nru.d((Map) hashMap, "RIGHT_BUTTON_VISIBILITY", 0));
        nsy.cMr_(this.d, nru.b(hashMap, "LEFT_BUTTON_TEXT", ""));
        nsy.cMr_(this.h, nru.b(hashMap, "RIGHT_BUTTON_TEXT", ""));
        this.c.setBackgroundResource(nru.d((Map) hashMap, "LEFT_BUTTON_IMAGE", R.drawable._2131427992_res_0x7f0b0298));
        this.e.setBackgroundResource(nru.d((Map) hashMap, "RIGHT_BUTTON_IMAGE", R.drawable._2131427992_res_0x7f0b0298));
        final OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionDoubleButton.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a2.onClick(1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f2680a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionDoubleButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a2.onClick(2);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.c.setRotationY(180.0f);
            this.e.setRotationY(180.0f);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionDoubleButton";
    }
}
