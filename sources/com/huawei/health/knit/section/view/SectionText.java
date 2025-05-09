package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dqj;
import defpackage.nru;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionText extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2746a;
    private View e;

    public SectionText(Context context) {
        super(context);
    }

    public SectionText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        if (this.e == null) {
            LogUtil.h("SectionText", "initView mainView is null, start to inflate");
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_text_layout, (ViewGroup) this, false);
            this.e = inflate;
            this.f2746a = (HealthTextView) inflate.findViewById(R.id.member_open_button);
        }
        View view = this.e;
        ViewTreeVisibilityListener.Zy_(view, new ViewTreeVisibilityListener(view, this));
        return this.e;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (nru.d(hashMap, "ITEM_TITLE", (Object) null) instanceof String) {
            this.f2746a.setText((String) nru.d(hashMap, "ITEM_TITLE", (Object) null));
        }
        if (nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null) instanceof View.OnClickListener) {
            this.f2746a.setOnClickListener((View.OnClickListener) nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null));
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void biEvent() {
        HealthTextView healthTextView = this.f2746a;
        if (healthTextView == null || !TextUtils.equals(healthTextView.getText(), getResources().getString(R$string.IDS_vip_renewal))) {
            return;
        }
        dqj.p();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionText";
    }
}
