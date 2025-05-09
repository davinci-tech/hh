package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionRangeView extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2718a;
    private HealthTextView b;
    Context c;
    private HealthTextView d;
    private View e;
    private HealthTextView g;

    public SectionRangeView(Context context) {
        super(context);
    }

    public SectionRangeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionRangeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionRangeView", "onCreateView");
        this.c = context;
        b();
        return this.e;
    }

    private void b() {
        if (nsn.p()) {
            this.e = LayoutInflater.from(this.c).inflate(R.layout.section_blood_oxygen_altitude_range_large, (ViewGroup) this, false);
        } else {
            this.e = LayoutInflater.from(this.c).inflate(R.layout.section_blood_oxygen_altitude_range, (ViewGroup) this, false);
        }
        this.f2718a = (HealthTextView) this.e.findViewById(R.id.blood_oxygen_latest_string);
        this.b = (HealthTextView) this.e.findViewById(R.id.blood_oxygen_latest_data);
        this.g = (HealthTextView) this.e.findViewById(R.id.bloodgen_trend_range_string);
        this.d = (HealthTextView) this.e.findViewById(R.id.bloodgen_trend_range_data);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionRangeView", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.b("SectionRangeView", "no need to bind");
            return;
        }
        nsy.cMr_(this.f2718a, nru.b(hashMap, "LEFT_TOP_VALUE", ""));
        nsy.cMr_(this.b, (CharSequence) nru.c(hashMap, "LEFT_TEXT", SpannableString.class, null));
        nsy.cMr_(this.g, nru.b(hashMap, "RIGHT_TEXT", ""));
        nsy.cMr_(this.d, (CharSequence) nru.c(hashMap, "RIGHT_BOTTOM_TEXT", SpannableString.class, null));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionRangeView";
    }
}
