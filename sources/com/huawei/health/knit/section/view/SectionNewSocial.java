package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionNewSocial extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2706a;
    private String b;
    private Context e;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return null;
    }

    public SectionNewSocial(Context context) {
        super(context);
        this.b = "RESOURCE_BRIEF_INFO";
    }

    public SectionNewSocial(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = "RESOURCE_BRIEF_INFO";
    }

    public SectionNewSocial(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = "RESOURCE_BRIEF_INFO";
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.e = context;
        if (this.f2706a == null) {
            LogUtil.h("SectionNewSocial", "initView mainView is null, start to inflate");
            this.f2706a = LayoutInflater.from(this.e).inflate(R.layout.section_new_social_layout, (ViewGroup) this, false);
        }
        return this.f2706a;
    }
}
