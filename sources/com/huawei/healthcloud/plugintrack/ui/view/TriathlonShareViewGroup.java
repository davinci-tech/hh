package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes4.dex */
public class TriathlonShareViewGroup extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private DetailItemContainer f3812a;
    private Context b;
    private LinearLayout c;
    private HealthTextView d;
    private ImageView e;

    public TriathlonShareViewGroup(Context context, int i) {
        super(context);
        b(context, i);
    }

    public TriathlonShareViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        b(context, i);
    }

    private void b(Context context, int i) {
        if (context == null) {
            LogUtil.h("Track_TriathlonShareViewGroup", "initViewGroup context is null.");
            return;
        }
        this.b = context;
        View.inflate(context, R.layout.triatjlon_share_viewgroup_layout, this);
        this.d = (HealthTextView) findViewById(R.id.sport_type_triathlon_name);
        this.f3812a = (DetailItemContainer) findViewById(R.id.triathlon_detail_container);
        this.c = (LinearLayout) findViewById(R.id.chart_view);
        this.e = (ImageView) findViewById(R.id.divide_line);
        if (i == 101) {
            this.d.setTextColor(getResources().getColor(R.color._2131296871_res_0x7f090267));
            this.e.setBackgroundColor(getResources().getColor(R.color._2131296871_res_0x7f090267));
        } else if (i == 100) {
            this.d.setTextColor(getResources().getColor(R.color._2131298052_res_0x7f090704));
            this.e.setBackgroundColor(getResources().getColor(R.color._2131298052_res_0x7f090704));
        } else {
            LogUtil.h("Track_TriathlonShareViewGroup", "no match style execute.");
        }
    }

    public DetailItemContainer c() {
        return this.f3812a;
    }

    public HealthTextView d() {
        return this.d;
    }

    public LinearLayout bjN_() {
        return this.c;
    }
}
