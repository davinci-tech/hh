package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataLayerType;
import com.huawei.ui.main.R$string;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class JumpableStyleMarkerTextView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9944a;
    private HealthTextView b;
    private boolean c;
    private RelativeLayout d;
    private HealthTextView e;
    private View.OnClickListener g;
    private int h;
    private View.OnClickListener j;

    public JumpableStyleMarkerTextView(Context context) {
        super(context);
        this.b = null;
        this.d = null;
        this.h = 0;
        this.c = false;
        b();
    }

    public JumpableStyleMarkerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = null;
        this.d = null;
        this.h = 0;
        this.c = false;
        b();
    }

    public JumpableStyleMarkerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = null;
        this.d = null;
        this.h = 0;
        this.c = false;
        b();
    }

    private void b() {
        inflate(getContext(), R.layout.view_jumpable_marker_textview, this);
        this.g = new c();
        this.f9944a = (HealthTextView) findViewById(R.id.detail_markview_data);
        this.b = (HealthTextView) findViewById(R.id.detail_markview_data_unit);
        this.e = (HealthTextView) findViewById(R.id.detail_markview_data_head);
        this.d = (RelativeLayout) findViewById(R.id.jumpable_click_area);
        if (LanguageUtil.ai(getContext()) || LanguageUtil.bp(getContext())) {
            this.f9944a = (HealthTextView) findViewById(R.id.kiswahili_detail_markview_data);
            this.b = (HealthTextView) findViewById(R.id.kiswahili_detail_markview_data_unit);
            this.e = (HealthTextView) findViewById(R.id.kiswahili_detail_markview_data_head);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.kiswahili_jumpable_click_area);
            this.d = relativeLayout;
            relativeLayout.setVisibility(0);
        }
        if (nsn.s()) {
            this.b.setTextSize(1, 28.0f);
            this.e.setTextSize(1, 28.0f);
        }
        this.d.setOnClickListener(this.g);
    }

    public void b(int i) {
        Drawable mutate;
        if (this.h == i) {
            return;
        }
        this.f9944a.setTextColor(i);
        this.b.setTextColor(i);
        this.e.setTextColor(i);
        if (!LanguageUtil.bc(getContext())) {
            mutate = getResources().getDrawable(R.mipmap._2131820814_res_0x7f11010e).mutate();
        } else {
            mutate = getResources().getDrawable(R.mipmap._2131820815_res_0x7f11010f).mutate();
        }
        mutate.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
    }

    public void setMarkerText(String str, String str2) {
        if (!TextUtils.equals(this.f9944a.getText(), str)) {
            this.f9944a.setText(str);
        }
        if (TextUtils.equals(this.b.getText(), str2)) {
            return;
        }
        this.b.setText(str2);
    }

    public void setContentOnClickListener(View.OnClickListener onClickListener) {
        this.j = onClickListener;
    }

    public void setHost(ObserveredClassifiedView observeredClassifiedView) {
        if (observeredClassifiedView.getStepDataType().isYearData() && !observeredClassifiedView.getStepDataType().isAllAportData()) {
            if (observeredClassifiedView.getStepDataType().isCaloriesData() || observeredClassifiedView.getStepDataType().isClimbData() || observeredClassifiedView.getStepDataType().isDistanceData() || observeredClassifiedView.getStepDataType().isTimeStrengthData() || observeredClassifiedView.getStepDataType().isStepData()) {
                this.e.setVisibility(0);
                this.e.setText(getContext().getString(R$string.IDS_hw_health_show_healthdata_weight_average));
                return;
            }
            return;
        }
        if (observeredClassifiedView.getStepDataType().isAllAportData() && observeredClassifiedView.getDataLayerType() == DataLayerType.CALORIES) {
            this.e.setVisibility(0);
            this.e.setText(getContext().getString(R$string.IDS_track_total_calories));
        } else {
            this.e.setVisibility(8);
        }
    }

    class c implements View.OnClickListener {
        private c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (JumpableStyleMarkerTextView.this.j != null && JumpableStyleMarkerTextView.this.c) {
                JumpableStyleMarkerTextView.this.j.onClick(view);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void setTextJumpable(boolean z) {
        this.c = z;
    }
}
