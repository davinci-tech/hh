package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.SportIntensityExplain;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public abstract class ScrollChartObserverView extends LinearLayout {
    private static final int DATA_CONTENT_MAX_WIDTH = 110;
    private static final String TAG = "ScrollChartObserverView";
    private HealthTextView mContent;
    private Context mContext;
    private LinearLayout mDataLayout;
    protected HealthImageView mExplain;
    public ObserveredClassifiedView mHost;
    private HealthTextView mTitle;
    private HealthTextView mUnit;

    public IFocusObserverItem acquireFocusItem() {
        return null;
    }

    public void initChartLinkage() {
    }

    public abstract void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2);

    public ScrollChartObserverView(Context context, ObserveredClassifiedView observeredClassifiedView, String str, String str2) {
        super(context);
        this.mHost = observeredClassifiedView;
        this.mContext = context;
        initView(str, str2);
    }

    protected void initView(String str, String str2) {
        inflate(getContext(), R.layout.view_scrollchart_observer, this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.view_scroll_chart_observer_root);
        this.mContent = (HealthTextView) findViewById(R.id.data_content);
        if (nsn.r()) {
            linearLayout.setGravity(GravityCompat.START);
            this.mContent.setMaxWidth(nsn.c(this.mContext, 220.0f));
        } else {
            linearLayout.setGravity(1);
            this.mContent.setMaxWidth(nsn.c(this.mContext, 110.0f));
        }
        this.mDataLayout = (LinearLayout) findViewById(R.id.scroll_data_layout);
        this.mTitle = (HealthTextView) findViewById(R.id.data_title);
        this.mUnit = (HealthTextView) findViewById(R.id.data_unit);
        this.mExplain = (HealthImageView) findViewById(R.id.data_explain_icon);
        this.mTitle.setText(str);
        this.mUnit.setText(str2);
        this.mExplain.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.a(ScrollChartObserverView.TAG, "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    ScrollChartObserverView.this.mContext.startActivity(new Intent(ScrollChartObserverView.this.mContext, (Class<?>) SportIntensityExplain.class));
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        if (LanguageUtil.ai(this.mContext)) {
            this.mDataLayout.setLayoutDirection(1);
        }
    }

    public void updateUnit(String str) {
        this.mUnit.setText(str);
    }

    public void setContentText(String str) {
        this.mContent.setText(str);
    }

    public void setContentColor(int i) {
        HealthTextView healthTextView = this.mContent;
        if (healthTextView == null || this.mUnit == null) {
            return;
        }
        healthTextView.setTextColor(this.mContext.getResources().getColor(i));
        this.mUnit.setTextColor(this.mContext.getResources().getColor(i));
    }

    public void setTitleColor(int i) {
        HealthTextView healthTextView = this.mTitle;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setTextColor(this.mContext.getResources().getColor(i));
    }

    public void setContentTypeface(Typeface typeface) {
        this.mContent.setTypeface(typeface);
    }

    public HealthTextView getContentTextView() {
        return this.mContent;
    }

    public HealthTextView getUnitTextView() {
        return this.mUnit;
    }
}
