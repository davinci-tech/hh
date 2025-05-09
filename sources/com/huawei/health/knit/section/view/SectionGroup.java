package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import defpackage.eec;
import defpackage.eet;
import defpackage.egv;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
abstract class SectionGroup extends BaseSection {
    private static final String TAG = "SectionGroup";
    private HealthCardView mCardView;
    private LinearLayout mCardViewLayout;
    private KnitFragment mKnitFragment;
    private LinearLayout mRootView;
    private List<BaseSection> mSectionList;
    protected List<d> mViewList;
    private int mViewType;

    protected abstract void bindParamsToView(int i, HashMap<String, Object> hashMap);

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        return null;
    }

    protected abstract void setSectionGroupCardColor(int i);

    public SectionGroup(Context context) {
        super(context);
        this.mViewList = new ArrayList();
        this.mSectionList = new ArrayList();
    }

    public SectionGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mViewList = new ArrayList();
        this.mSectionList = new ArrayList();
    }

    public SectionGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mViewList = new ArrayList();
        this.mSectionList = new ArrayList();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void initView(KnitFragment knitFragment, int i) {
        LogUtil.a(TAG, "initView");
        this.mViewType = i;
        this.mKnitFragment = knitFragment;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.knit_section_group_layout, (ViewGroup) this, false);
        this.mRootView = (LinearLayout) inflate.findViewById(R.id.section_group_root_layout);
        this.mCardViewLayout = (LinearLayout) inflate.findViewById(R.id.section_group_card_layout);
        this.mCardView = (HealthCardView) inflate.findViewById(R.id.section_group_card_view);
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (this.mCardView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mCardView.getLayoutParams();
            layoutParams.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
            layoutParams.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
            this.mCardView.setLayoutParams(layoutParams);
        }
        addView(inflate);
        initSectionList(i);
    }

    private void initSectionList(int i) {
        List<Class<? extends BaseSection>> c = egv.c(i);
        if (koq.b(c)) {
            LogUtil.a(TAG, "sectionClzList is empty");
            return;
        }
        Iterator<Class<? extends BaseSection>> it = c.iterator();
        while (it.hasNext()) {
            BaseSection d2 = eet.d(getContext(), it.next());
            if (d2 != null) {
                this.mSectionList.add(d2);
            }
        }
        for (int i2 = 0; i2 < this.mSectionList.size(); i2++) {
            addSection(i2);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void onBind(SectionBean sectionBean) {
        LogUtil.a(TAG, "onBind");
        if (sectionBean == null) {
            return;
        }
        sectionBean.b(this);
        if (koq.b(this.mSectionList)) {
            LogUtil.a(TAG, "sectionList is empty");
            return;
        }
        initRootView();
        KnitDataProvider c = sectionBean.c();
        if (c == null || koq.b(c.getDataProviderList())) {
            return;
        }
        List dataProviderList = c.getDataProviderList();
        if (dataProviderList.size() != getSectionList().size()) {
            LogUtil.a(TAG, "size not match");
            throw new IllegalStateException("section size not match data provider size");
        }
        initViewList();
        LogUtil.a(TAG, "section size: " + this.mSectionList.size());
        for (int i = 0; i < dataProviderList.size(); i++) {
            KnitDataProvider knitDataProvider = (KnitDataProvider) dataProviderList.get(i);
            BaseSection baseSection = this.mSectionList.get(i);
            if (!knitDataProvider.isActive(getContext())) {
                LogUtil.a(TAG, "dp is not active: " + i);
                baseSection.setSupportShare(false);
            } else {
                eec.d a2 = sectionBean.a(knitDataProvider);
                if (a2 != null && a2.a() != null) {
                    LogUtil.a(TAG, "dp is valid: ", Integer.valueOf(i));
                    this.mViewList.get(i).c(0);
                    baseSection.setSupportShare(true);
                    knitDataProvider.parseParams(getContextRef(), a2.a(), a2.c());
                    overrideParamsByOnlineData(sectionBean, a2.a());
                    bindParamsToView(i, a2.a());
                } else {
                    LogUtil.a(TAG, "dp has no valid data: " + i);
                    baseSection.setSupportShare(false);
                }
            }
        }
    }

    private void initViewList() {
        Iterator<d> it = this.mViewList.iterator();
        while (it.hasNext()) {
            it.next().c(8);
        }
    }

    private void initRootView() {
        for (int i = 0; i < this.mRootView.getChildCount(); i++) {
            if (this.mRootView.getChildAt(i) != null) {
                this.mRootView.getChildAt(i).setVisibility(0);
            }
        }
    }

    private void addSection(int i) {
        if (this.mSectionList.size() <= i) {
            LogUtil.a(TAG, "index is invalid");
            return;
        }
        BaseSection baseSection = this.mSectionList.get(i);
        View onCreateView = baseSection.onCreateView(getContext());
        if (onCreateView == null) {
            LogUtil.a(TAG, "parameter view is null");
            return;
        }
        ViewParent parent = onCreateView.getParent();
        if (parent != this) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(onCreateView);
            }
            LogUtil.a(TAG, "add section: " + i);
            this.mViewList.add(new d(baseSection, 8));
            baseSection.addView(onCreateView);
            this.mRootView.addView(baseSection, new LinearLayout.LayoutParams(-1, -2));
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        super.clear();
        if (koq.b(this.mSectionList)) {
            return;
        }
        for (BaseSection baseSection : this.mSectionList) {
            if (baseSection != null) {
                baseSection.clear();
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public KnitFragment getKnitFragment() {
        return this.mKnitFragment;
    }

    public List<BaseSection> getSectionList() {
        return this.mSectionList;
    }

    @Override // android.view.View
    public LinearLayout getRootView() {
        return this.mRootView;
    }

    public HealthCardView getCardView() {
        return this.mCardView;
    }

    public class d {
        private View b;
        private int d;

        d(View view, int i) {
            this.b = view;
            this.d = i;
        }

        public void c(int i) {
            this.d = i;
        }

        public View ajj_() {
            return this.b;
        }

        public int d() {
            return this.d;
        }
    }
}
