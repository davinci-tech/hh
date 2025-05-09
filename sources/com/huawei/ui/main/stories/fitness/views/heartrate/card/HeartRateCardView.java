package com.huawei.ui.main.stories.fitness.views.heartrate.card;

import android.content.Context;
import android.os.Build;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public abstract class HeartRateCardView extends ScrollChartObserverView {
    private static final String TAG = "HeartRateCardView";
    private static final int TEXT_LENGTH = 6;
    private static final int TEXT_SIZE_CONTENT_DP = 40;
    private static final int TEXT_SIZE_EIGHTEEN = 18;
    private static final int TEXT_SIZE_TITLE_DP = 30;
    private static final int TEXT_SIZE_TWENTY = 20;
    private static final int TEXT_SIZE_UNIT_DP = 36;
    private HealthTextView mContent;
    private HealthTextView mTitle;
    private HealthTextView mUnit;
    private String mUnitText;

    public HeartRateCardView(Context context, ObserveredClassifiedView observeredClassifiedView, String str, String str2) {
        super(context, observeredClassifiedView, str, str2);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void initView(String str, String str2) {
        if (nsn.t()) {
            inflate(getContext(), R.layout.view_heart_rate_scrollchart_observer_trable, this);
        } else {
            inflate(getContext(), R.layout.view_heart_rate_scrollchart_observer, this);
        }
        this.mTitle = (HealthTextView) findViewById(R.id.data_title);
        this.mContent = (HealthTextView) findViewById(R.id.data_content);
        this.mUnit = (HealthTextView) findViewById(R.id.data_unit);
        this.mTitle.setText(str);
        this.mUnit.setText(str2);
        this.mUnitText = str2;
        if (!nsn.r() || Build.BRAND.toLowerCase(Locale.ENGLISH).contains("samsung")) {
            return;
        }
        this.mTitle.setTextSize(1, 30.0f);
        this.mContent.setTextSize(1, 40.0f);
        this.mUnit.setTextSize(1, 36.0f);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void setContentText(String str) {
        if (str == null) {
            LogUtil.h(TAG, "setContentText text is null");
            return;
        }
        int i = str.length() > 6 ? 18 : 20;
        boolean bp = LanguageUtil.bp(BaseApplication.e());
        this.mContent.setText(bp ? this.mUnitText : str);
        HealthTextView healthTextView = this.mUnit;
        if (!bp) {
            str = this.mUnitText;
        }
        healthTextView.setText(str);
        if (bp) {
            this.mUnit.setTextSize(i);
        }
        this.mContent.setTextSize(bp ? 12.0f : i);
        if (LanguageUtil.bi(BaseApplication.e())) {
            this.mUnit.setLineSpacing(nsn.c(BaseApplication.e(), -14.0f), 1.0f);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void setContentColor(int i) {
        HealthTextView healthTextView = this.mContent;
        if (healthTextView == null || this.mUnit == null) {
            return;
        }
        healthTextView.setTextColor(BaseApplication.e().getResources().getColor(i));
        this.mUnit.setTextColor(BaseApplication.e().getResources().getColor(i));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void setTitleColor(int i) {
        HealthTextView healthTextView = this.mTitle;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setTextColor(BaseApplication.e().getResources().getColor(i));
    }
}
