package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class Section21_9Target_03 extends BaseSection {
    private Section21_9Target_03Impl c;

    public Section21_9Target_03(Context context) {
        this(context, null);
        d(context);
    }

    private void d(Context context) {
        this.c = !Utils.o() ? new Section21_9Target_03ChinaImpl(context) : new Section21_9Target_03OverseaImpl(context);
    }

    public Section21_9Target_03(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        d(context);
    }

    public Section21_9Target_03(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d(context);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        return this.c.onCreateView(context, this);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        this.c.bindParamsToView(hashMap);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.section_sub_header) {
            onClick("MORE_CLICK_EVENT");
        } else {
            LogUtil.b("view id is invalid", new Object[0]);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section21_9Target_03";
    }
}
