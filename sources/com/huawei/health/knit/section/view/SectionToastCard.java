package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.listener.ILayoutCreatedCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionToastCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2747a;
    private RelativeLayout e;

    public SectionToastCard(Context context) {
        super(context);
    }

    public SectionToastCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionToastCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionToastCard", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.common_auto_test_toast, (ViewGroup) this, false);
        this.f2747a = inflate;
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.common_auto_test_toast_layout);
        this.e = relativeLayout;
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
            LogUtil.a("SectionToastCard", "type is error");
        } else {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = nsn.c(context, 12.0f);
            layoutParams2.topMargin = nsn.c(context, 0.0f);
            this.e.setLayoutParams(layoutParams2);
        }
        return this.f2747a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionToastCard", "bindParamsToView");
        if (hashMap == null || hashMap.isEmpty()) {
            LogUtil.a("SectionToastCard", "no need to bind");
            return;
        }
        Object obj = hashMap.get("FULL_SCREEN_CALLBACK");
        if (!(obj instanceof ILayoutCreatedCallback)) {
            LogUtil.a("SectionToastCard", "type error");
        } else {
            ((ILayoutCreatedCallback) obj).onLayoutUpdate(this.e);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionToastCard";
    }
}
