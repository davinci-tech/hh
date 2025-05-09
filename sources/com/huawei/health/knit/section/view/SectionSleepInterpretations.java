package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ixx;
import defpackage.nsy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionSleepInterpretations extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private SleepInterpretationsTextview f2736a;
    private boolean b;
    private SleepInterpretationsTextview c;
    private SleepInterpretationsTextview d;
    private View e;
    private HealthTextView f;
    private d j;

    public SectionSleepInterpretations(Context context) {
        super(context);
        this.b = false;
    }

    public SectionSleepInterpretations(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
    }

    public SectionSleepInterpretations(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        try {
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_sleep_interpretation, (ViewGroup) this, false);
            this.f = (HealthTextView) inflate.findViewById(R.id.section_title);
            this.d = (SleepInterpretationsTextview) inflate.findViewById(R.id.daily_quality_desc);
            this.f2736a = (SleepInterpretationsTextview) inflate.findViewById(R.id.daily_problem_impact);
            this.c = (SleepInterpretationsTextview) inflate.findViewById(R.id.daily_advice);
            this.e = inflate.findViewById(R.id.daily_advice_card_view);
            b();
            return inflate;
        } catch (NullPointerException e) {
            ReleaseLogUtil.c("SectionSleepInterpretations", "Exception msg = ", ExceptionUtils.d(e));
            return new View(context);
        }
    }

    private void b() {
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.huawei.health.knit.section.view.SectionSleepInterpretations.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                LogUtil.a("SectionSleepInterpretations", "addOnScrollChangedListener onAttachedToWindow");
                if (SectionSleepInterpretations.this.j == null) {
                    SectionSleepInterpretations sectionSleepInterpretations = SectionSleepInterpretations.this;
                    sectionSleepInterpretations.j = new d(sectionSleepInterpretations);
                }
                SectionSleepInterpretations sectionSleepInterpretations2 = SectionSleepInterpretations.this;
                nsy.cMb_(sectionSleepInterpretations2, sectionSleepInterpretations2.j);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                LogUtil.a("SectionSleepInterpretations", "addOnScrollChangedListener detached from window");
                SectionSleepInterpretations.this.removeOnAttachStateChangeListener(this);
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void configLayoutParameters(LinearLayout.LayoutParams layoutParams) {
        if (isSupportSafeRegion()) {
            int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            layoutParams.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
            layoutParams.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        nsy.cMg_(this, this.j);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return getClass().getSimpleName();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        HealthTextView healthTextView;
        Object obj = hashMap.get("DAILY_SLEEP_INTERPRETATIONS_TITLE");
        if ((obj instanceof String) && (healthTextView = this.f) != null) {
            healthTextView.setText((String) obj);
        }
        Object obj2 = hashMap.get("DAILY_SLEEP_INTERPRETATIONS");
        Object obj3 = hashMap.get("DAILY_SLEEP_PROBLEM_FACTOR");
        if (this.d == null) {
            ReleaseLogUtil.d("SectionSleepInterpretations", "mDailyQualityDesc is null");
            return;
        }
        if (!(obj3 instanceof String) || TextUtils.isEmpty((String) obj3)) {
            this.d.setupDrawable(null);
        } else {
            this.d.d();
        }
        e(obj2, this.d);
        e(obj3, this.f2736a);
        Object obj4 = hashMap.get("DAILY_SLEEP_ADVICE");
        if (obj4 instanceof String) {
            String str = (String) obj4;
            if (!TextUtils.isEmpty(str)) {
                Drawable drawable = ContextCompat.getDrawable(getContextRef(), R.drawable._2131430447_res_0x7f0b0c2f);
                if (drawable != null) {
                    int dimension = (int) getContextRef().getResources().getDimension(R.dimen._2131362922_res_0x7f0a046a);
                    drawable.setBounds(0, 0, dimension, dimension);
                }
                this.c.setupDrawable(drawable);
                this.c.setupTextView(str);
                this.e.setVisibility(0);
                return;
            }
        }
        this.e.setVisibility(8);
    }

    private void e(Object obj, SleepInterpretationsTextview sleepInterpretationsTextview) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                sleepInterpretationsTextview.setupTextView(str);
                sleepInterpretationsTextview.setVisibility(0);
                return;
            }
        }
        sleepInterpretationsTextview.setVisibility(8);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void checkVisibilityAndSetBiEvent() {
        if (!ViewTreeVisibilityListener.Zx_(this) || this.b) {
            return;
        }
        biEvent();
        this.b = true;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void biEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(getContextRef(), AnalyticsValue.SLEEP_INTERPRETATIONS_2030112.value(), hashMap, 0);
    }

    static class d implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SectionSleepInterpretations> f2737a;

        public d(SectionSleepInterpretations sectionSleepInterpretations) {
            this.f2737a = new WeakReference<>(sectionSleepInterpretations);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            SectionSleepInterpretations sectionSleepInterpretations = this.f2737a.get();
            if (sectionSleepInterpretations == null) {
                return;
            }
            if (sectionSleepInterpretations.b) {
                nsy.cMf_(sectionSleepInterpretations, this);
            } else {
                sectionSleepInterpretations.checkVisibilityAndSetBiEvent();
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            SectionSleepInterpretations sectionSleepInterpretations = this.f2737a.get();
            if (sectionSleepInterpretations == null) {
                return;
            }
            if (sectionSleepInterpretations.b) {
                nsy.cMg_(sectionSleepInterpretations, this);
            } else {
                sectionSleepInterpretations.checkVisibilityAndSetBiEvent();
            }
        }
    }
}
