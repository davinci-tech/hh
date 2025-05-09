package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class AbnormalRecordListSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f2614a;
    private HealthRecycleView b;
    private View c;
    private LinearLayout d;
    private Context e;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
    }

    public AbnormalRecordListSection(Context context) {
        super(context);
    }

    public AbnormalRecordListSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AbnormalRecordListSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.e = context;
        if (this.c == null) {
            LogUtil.h("AbnormalRecordListSection", "initView mainView is null, start to inflate");
            View inflate = LayoutInflater.from(this.e).inflate(R.layout.section_abnormal_record_list_layout, (ViewGroup) this, false);
            this.c = inflate;
            this.d = (LinearLayout) inflate.findViewById(R.id.section_abnormal_record_root);
            this.f2614a = (HealthSubHeader) this.c.findViewById(R.id.section_abnormal_record_cardview);
            this.b = (HealthRecycleView) this.c.findViewById(R.id.section_abnormal_record_recyclerview);
        }
        return this.c;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "AbnormalRecordListSection";
    }
}
