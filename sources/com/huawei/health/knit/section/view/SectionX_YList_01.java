package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.health.R;
import com.huawei.health.section.adapter.SectionX_YList_01Adapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.eed;
import defpackage.eeh;
import defpackage.eet;
import defpackage.nrf;
import defpackage.nru;
import defpackage.nrv;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class SectionX_YList_01 extends BaseSection {
    private static final String TAG = "SectionX_YList_01";
    public Context context;
    private boolean mIsNoMoreDataVisible;
    private eeh mLastData;
    public int mPageType;
    private SectionX_YList_01Adapter mSectionListAdapter;
    public String mSubHeaderTitle;
    private ConstraintLayout noDataBackground;
    private HealthButton noDataButton;
    private HealthTextView noDataContent;
    private HealthTextView noDataName;
    private LinearLayout noDataView;
    private RelativeLayout recycleViewRootLayout;
    private LinearLayout rootLayout;
    public HealthRecycleView sectionRecycleView;
    private HealthSubHeader subHeader;

    protected abstract SectionX_YList_01Adapter getSectionListAdapter(Context context, eeh eehVar);

    protected abstract View layoutInflater();

    protected abstract void setRecyclerViewLayout(eeh eehVar);

    public SectionX_YList_01(Context context) {
        this(context, null);
    }

    public SectionX_YList_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionX_YList_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    private boolean isLoadDefaultView(HashMap<String, Object> hashMap) {
        return hashMap.containsKey("IS_LOAD_DEFAULT_VIEW") && (hashMap.get("IS_LOAD_DEFAULT_VIEW") instanceof Boolean) && ((Boolean) hashMap.get("IS_LOAD_DEFAULT_VIEW")).booleanValue();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View layoutInflater = layoutInflater();
        this.rootLayout = (LinearLayout) layoutInflater.findViewById(R.id.section_root_view);
        this.subHeader = (HealthSubHeader) layoutInflater.findViewById(R.id.section_sub_header);
        this.recycleViewRootLayout = (RelativeLayout) layoutInflater.findViewById(R.id.section1_1list_01_recycler_layout);
        this.sectionRecycleView = (HealthRecycleView) layoutInflater.findViewById(R.id.section1_1list_01_recycler_view);
        this.noDataView = (LinearLayout) layoutInflater.findViewById(R.id.section1_1list_01_no_data_view);
        this.noDataBackground = (ConstraintLayout) layoutInflater.findViewById(R.id.no_data_root_view);
        this.noDataName = (HealthTextView) layoutInflater.findViewById(R.id.no_data_name);
        this.noDataContent = (HealthTextView) layoutInflater.findViewById(R.id.no_data_content);
        this.noDataButton = (HealthButton) layoutInflater.findViewById(R.id.no_data_button);
        return layoutInflater;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        eet.e(this.context, this.sectionRecycleView, this.mSectionListAdapter);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a(TAG, "bindViewParams");
        if (isLoadDefaultView(hashMap)) {
            loadDefaultView();
            bindDefaultView(hashMap);
        } else {
            loadView();
            bindView(hashMap);
        }
    }

    private void bindView(HashMap<String, Object> hashMap) {
        LogUtil.a(TAG, "bindView");
        this.mPageType = nru.d((Map) hashMap, "PAGE_TYPE", 0);
        setSubHeaderTitle(nru.d(hashMap, "TITLE", (Object) null));
        setSubViewStatus(nru.d(hashMap, "SUBTITLE", (Object) null));
        setNoMoreData(nru.d(hashMap, "NO_MORE_DATA", (Object) null));
        eeh eehVar = new eeh();
        setClickEvent(nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null), eehVar);
        eehVar.g(nru.d(hashMap, "NAME", String.class, new ArrayList()));
        eehVar.a(nru.d(hashMap, "IMAGE", Object.class, new ArrayList()));
        setImageSize(nru.d(hashMap, "IMAGE_SIZE", (Object) null), eehVar);
        eehVar.d(nru.d(hashMap, "INTERVALS", String.class, new ArrayList()));
        setDifficulty(nru.d(hashMap, "DIFFICULTY", (Object) null), eehVar);
        setDurationData(nru.d(hashMap, PlayInfo.KEY_DURATION, (Object) null), eehVar);
        eehVar.j(nru.d(hashMap, "ITEM_TAG", eed.class, new ArrayList()));
        if (this.mLastData == null || !TextUtils.equals(nrv.a(eehVar), nrv.a(this.mLastData))) {
            setRecyclerViewLayout(eehVar);
            setRecyclerViewData(eehVar);
            this.mLastData = eehVar;
        }
    }

    public void setSubHeaderClick(HealthSubHeader healthSubHeader, final Object obj) {
        if (this.mIsNoMoreDataVisible) {
            LogUtil.a(TAG, "No more data type without SubHeaderClick");
            healthSubHeader.setClickable(false);
        } else {
            healthSubHeader.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionX_YList_01.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ((OnClickSectionListener) obj).onClick("MORE_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void setSubTitle(Object obj) {
        if (this.subHeader != null) {
            if (eet.f(obj)) {
                LogUtil.a(TAG, "subtitle is set");
                this.subHeader.setMoreTextVisibility(0);
                this.subHeader.setMoreText((String) obj);
            } else {
                LogUtil.a(TAG, "subtitle is invisible");
                this.subHeader.setMoreTextVisibility(4);
            }
        }
    }

    private void bindDefaultView(HashMap<String, Object> hashMap) {
        LogUtil.a(TAG, "bindDefaultView");
        this.mPageType = nru.d((Map) hashMap, "PAGE_TYPE", 0);
        setSubHeaderTitle(nru.d(hashMap, "TITLE", (Object) null));
        setSubTitle(nru.d(hashMap, "SUBTITLE", (Object) null));
        setDefaultClickEvent(nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null));
        setNoDataImage(nru.d(hashMap, "NO_DATA_IMAGE", (Object) null));
        setNoDataName(nru.d(hashMap, "NO_DATA_NAME", (Object) null));
        setNoDataContent(nru.d(hashMap, "NO_DATA_CONTENT", (Object) null));
        setNoDataButton(nru.d(hashMap, "NO_DATA_BUTTON", (Object) null));
    }

    protected void loadView() {
        LogUtil.a(TAG, "loadView");
        this.rootLayout.post(new Runnable() { // from class: com.huawei.health.knit.section.view.SectionX_YList_01.4
            @Override // java.lang.Runnable
            public void run() {
                SectionX_YList_01.this.rootLayout.setMinimumWidth(nsn.h(SectionX_YList_01.this.context));
            }
        });
        RelativeLayout relativeLayout = this.recycleViewRootLayout;
        if (relativeLayout == null) {
            LogUtil.a(TAG, "loadView, recycleViewRootLayout is null");
            return;
        }
        relativeLayout.setVisibility(0);
        this.noDataView.setVisibility(8);
        this.subHeader.setMoreTextVisibility(4);
        this.subHeader.setSubHeaderBackgroundColor(this.context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        initRecycleView();
    }

    private void initRecycleView() {
        this.sectionRecycleView.setHasFixedSize(true);
        this.sectionRecycleView.setNestedScrollingEnabled(false);
        this.sectionRecycleView.a(false);
        this.sectionRecycleView.d(false);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        this.sectionRecycleView.setItemAnimator(defaultItemAnimator);
    }

    protected void loadDefaultView() {
        LogUtil.a(TAG, "loadDefaultView");
        this.rootLayout.post(new Runnable() { // from class: com.huawei.health.knit.section.view.SectionX_YList_01.1
            @Override // java.lang.Runnable
            public void run() {
                SectionX_YList_01.this.rootLayout.setMinimumWidth(nsn.h(SectionX_YList_01.this.context));
            }
        });
        this.recycleViewRootLayout.setVisibility(8);
        this.noDataView.setVisibility(0);
        this.subHeader.setMoreTextVisibility(4);
        this.subHeader.setSubHeaderBackgroundColor(this.context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.section_sub_header) {
            onClick("MORE_CLICK_EVENT");
        } else if (id == R.id.no_data_button) {
            onClick("NO_DATA_BUTTON_CLICK_EVENT");
        } else {
            LogUtil.b("view id is invalid", new Object[0]);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setRecyclerViewData(eeh eehVar) {
        SectionX_YList_01Adapter sectionX_YList_01Adapter = this.mSectionListAdapter;
        if (sectionX_YList_01Adapter == null) {
            SectionX_YList_01Adapter sectionListAdapter = getSectionListAdapter(this.context, eehVar);
            this.mSectionListAdapter = sectionListAdapter;
            this.sectionRecycleView.setAdapter(sectionListAdapter);
            return;
        }
        sectionX_YList_01Adapter.setData(eehVar);
    }

    private void setClickEvent(Object obj, eeh eehVar) {
        if (!eet.a(obj) || this.subHeader == null) {
            return;
        }
        LogUtil.a(TAG, "click event is set");
        setSubHeaderClick(this.subHeader, obj);
        eehVar.e((OnClickSectionListener) obj);
    }

    private void setSubViewStatus(Object obj) {
        HealthSubHeader healthSubHeader;
        if (obj == null || (healthSubHeader = this.subHeader) == null) {
            return;
        }
        ccq.c(obj, healthSubHeader, "Section_Section1_1List_01", 8, "gone");
    }

    private void setSubHeaderTitle(Object obj) {
        if (!eet.f(obj) || this.subHeader == null) {
            return;
        }
        LogUtil.a(TAG, "title is set");
        String str = (String) obj;
        this.mSubHeaderTitle = str;
        this.subHeader.setHeadTitleText(str);
    }

    private void setNoMoreData(Object obj) {
        LogUtil.a(TAG, "subHeader viewVisible" + obj);
        if (this.subHeader == null || !(obj instanceof Boolean)) {
            return;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        this.mIsNoMoreDataVisible = booleanValue;
        int i = booleanValue ? 8 : 0;
        LogUtil.a(TAG, "subHeader no more data is set" + i);
        this.subHeader.setRightArrayVisibility(i);
    }

    private void setImageSize(Object obj, eeh eehVar) {
        if (eet.e(obj)) {
            LogUtil.a(TAG, "image size is set");
            eehVar.b((List) obj);
        }
    }

    private void setDurationData(Object obj, eeh eehVar) {
        if (eet.h(obj)) {
            LogUtil.a(TAG, "duration list is set");
            eehVar.e((List<String>) obj);
        }
    }

    private void setDifficulty(Object obj, eeh eehVar) {
        if (eet.h(obj)) {
            LogUtil.a(TAG, "difficulty list is set");
            eehVar.c((List) obj);
        }
    }

    private void setNoDataName(Object obj) {
        if (!eet.f(obj) || this.noDataName == null) {
            return;
        }
        LogUtil.a(TAG, "no data name is set");
        this.noDataName.setText((String) obj);
    }

    private void setNoDataContent(Object obj) {
        if (!eet.f(obj) || this.noDataContent == null) {
            return;
        }
        LogUtil.a(TAG, "no data content is set");
        this.noDataContent.setText((String) obj);
    }

    private void setNoDataImage(Object obj) {
        LogUtil.a(TAG, "no data image is set");
        if (eet.f(obj)) {
            String str = (String) obj;
            if (str.length() > 0 && this.noDataBackground != null) {
                nrf.d(str, new CustomTarget<Drawable>() { // from class: com.huawei.health.knit.section.view.SectionX_YList_01.3
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable drawable) {
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    /* renamed from: ajQ_, reason: merged with bridge method [inline-methods] */
                    public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                        SectionX_YList_01.this.noDataBackground.setBackground(drawable);
                    }

                    @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                    public void onLoadFailed(Drawable drawable) {
                        LogUtil.h(SectionX_YList_01.TAG, "loadRoundRectangle onLoadFailed");
                    }
                });
            }
        }
        if (eet.c(obj)) {
            int intValue = ((Integer) obj).intValue();
            ConstraintLayout constraintLayout = this.noDataBackground;
            if (constraintLayout != null) {
                constraintLayout.setBackgroundResource(intValue);
            }
        }
    }

    private void setNoDataButton(Object obj) {
        if (!eet.f(obj) || this.noDataButton == null) {
            return;
        }
        LogUtil.a(TAG, "no data button is set");
        this.noDataButton.setText((String) obj);
        if (nsn.r()) {
            ViewGroup.LayoutParams layoutParams = this.noDataButton.getLayoutParams();
            layoutParams.height = -2;
            this.noDataButton.setLayoutParams(layoutParams);
        }
    }

    private void setDefaultClickEvent(final Object obj) {
        if (!eet.a(obj) || this.subHeader == null || this.noDataButton == null) {
            return;
        }
        LogUtil.a(TAG, "click event is set");
        setSubHeaderClick(this.subHeader, obj);
        this.noDataButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionX_YList_01.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((OnClickSectionListener) obj).onClick("NO_DATA_BUTTON_CLICK_EVENT");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return TAG;
    }
}
