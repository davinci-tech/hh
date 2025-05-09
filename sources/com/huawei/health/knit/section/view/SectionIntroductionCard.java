package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionIntroductionCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2692a;
    private HealthTextView d;
    private HealthTextView e;

    public SectionIntroductionCard(Context context) {
        super(context);
    }

    public SectionIntroductionCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionIntroductionCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_introduction_card, (ViewGroup) this, false);
        this.f2692a = inflate;
        this.e = (HealthTextView) inflate.findViewById(R.id.hsh_introduction_title);
        this.d = (HealthTextView) this.f2692a.findViewById(R.id.htv_introduction_content);
        return this.f2692a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.h("SectionIntroductionCard", "no need to bind");
            return;
        }
        LogUtil.a("SectionIntroductionCard", "bindParamsToView");
        nsy.cMr_(this.e, nru.b(hashMap, "STAND_WHAT", ""));
        nsy.cMs_(this.d, nru.b(hashMap, "STAND_EXPLAIN", ""), true);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionIntroductionCard";
    }
}
