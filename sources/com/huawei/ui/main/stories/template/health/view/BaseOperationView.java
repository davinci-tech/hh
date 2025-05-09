package com.huawei.ui.main.stories.template.health.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.sport.configuredpage.ConfiguredPageItemDecoration;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.configuredpage.ConfigureServerDetailActivity;
import com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredLayoutAdapter;
import com.huawei.ui.main.stories.template.health.view.BaseOperationView;
import defpackage.cdu;
import defpackage.cdy;
import defpackage.gnm;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.pfh;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public abstract class BaseOperationView extends BaseNoDataView implements View.OnClickListener {
    protected static final int DEFAULT_STYLE_INT = 0;
    private static final String SERVER_NAME = "server_name";
    private static final String TAG = "Main_BaseOperationView";
    protected LinearLayout mConfiguredItemRootLayout;
    private Context mContext;
    private int mGutter;
    protected HealthSubHeader mHealthSubHeader;
    private int mLayout;
    private int mOrientation;
    private cdy mPageModule;
    protected HealthRecycleView mRecyclerView;
    private List<cdu> mShowCardItemList;
    private List<List<cdu>> mShowVerticalLayoutDataList;

    public abstract void findViews(View view);

    public abstract int getLayoutId();

    public BaseOperationView(Context context) {
        this(context, null);
    }

    public BaseOperationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseOperationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowVerticalLayoutDataList = new ArrayList(10);
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) this, false);
        addView(inflate);
        findViews(inflate);
        initView();
    }

    private void initView() {
        this.mConfiguredItemRootLayout.setVisibility(0);
        this.mHealthSubHeader.setVisibility(8);
        this.mHealthSubHeader.setMoreTextVisibility(4);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.setHasFixedSize(true);
        this.mGutter = nrr.b(this.mContext);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int b = this.mPageModule.b();
        boolean z = b == 4 && this.mPageModule.c() == 11;
        if (b == 1 || z) {
            LoginInit.getInstance(this.mContext).browsingToLogin(new IBaseResponseCallback() { // from class: rza
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    BaseOperationView.this.m862x9688cf74(i, obj);
                }
            }, null);
        } else {
            jumpConfigureServerDetail();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$onClick$0$com-huawei-ui-main-stories-template-health-view-BaseOperationView, reason: not valid java name */
    public /* synthetic */ void m862x9688cf74(int i, Object obj) {
        if (i == 0) {
            jumpConfigureServerDetail();
        } else {
            LogUtil.h(TAG, "browsingToLogin errorCode is not success", Integer.valueOf(i));
        }
    }

    private void jumpConfigureServerDetail() {
        String a2 = this.mPageModule.a();
        if (this.mLayout == 12) {
            Intent intent = new Intent(this.mContext, (Class<?>) ConfigureServerDetailActivity.class);
            intent.putExtra(SERVER_NAME, this.mPageModule);
            intent.setFlags(268435456);
            gnm.aPB_(this.mContext, intent);
            return;
        }
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        Intent intent2 = new Intent(this.mContext, (Class<?>) WebViewActivity.class);
        intent2.setFlags(268435456);
        intent2.putExtra("url", a2);
        gnm.aPB_(this.mContext, intent2);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(TAG, "onConfigurationChanged");
    }

    public void setUiData(cdy cdyVar) {
        List<cdu> list;
        this.mPageModule = cdyVar;
        if (cdyVar == null) {
            LogUtil.h(TAG, "setUiData() pageModuleObject is null.");
            return;
        }
        this.mLayout = cdyVar.c();
        setRecyclerViewMargin();
        int b = this.mPageModule.b();
        this.mOrientation = this.mPageModule.i();
        if (!pfh.e(b, this.mLayout)) {
            LogUtil.h(TAG, "updateConfiguredPageUi() moduleType:", Integer.valueOf(b), " is not support layout: ", Integer.valueOf(this.mLayout));
            return;
        }
        List<cdu> e = this.mPageModule.e();
        if (koq.b(e)) {
            LogUtil.h(TAG, "updateConfiguredPageUi() allCardItemObjectList is empty.");
            return;
        }
        List<cdu> c = pfh.c(this.mLayout, b, e);
        this.mShowCardItemList = c;
        if (koq.b(c)) {
            this.mConfiguredItemRootLayout.setVisibility(8);
            LogUtil.h(TAG, "updateConfiguredPageUi() showCardItemObjectList is empty.");
            return;
        }
        if (this.mLayout == 7 && (list = this.mShowCardItemList) != null && list.size() > 2) {
            this.mShowCardItemList = this.mShowCardItemList.subList(0, 2);
        }
        if (this.mLayout == 12) {
            boolean ag = nsn.ag(BaseApplication.getContext());
            if (!ag && this.mShowCardItemList.size() > 4) {
                this.mShowCardItemList = this.mShowCardItemList.subList(0, 4);
            } else if (ag && this.mShowCardItemList.size() > 8) {
                this.mShowCardItemList = this.mShowCardItemList.subList(0, 8);
            } else {
                LogUtil.h(TAG, "setUiData: do nothing");
            }
        }
        if (this.mLayout == 9 && !nsn.ag(BaseApplication.getContext())) {
            this.mShowVerticalLayoutDataList.clear();
            this.mShowVerticalLayoutDataList.add(this.mShowCardItemList);
        }
        initSubHeaderLayout();
        initConfiguredItemLayout();
    }

    private void setRecyclerViewMargin() {
        if (this.mLayout == 7) {
            this.mRecyclerView.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.configured_item_image_text_background));
            ViewGroup.LayoutParams layoutParams = this.mRecyclerView.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
                int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
                Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
                layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
                layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
                this.mRecyclerView.setLayoutParams(layoutParams2);
                return;
            }
            return;
        }
        this.mRecyclerView.setBackground(null);
    }

    private void initSubHeaderLayout() {
        LinearLayout.LayoutParams subHeaderParams = getSubHeaderParams();
        if (this.mLayout == 10) {
            this.mHealthSubHeader.setVisibility(4);
            if (subHeaderParams != null) {
                subHeaderParams.height = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362565_res_0x7f0a0305);
                return;
            }
            return;
        }
        String g = this.mPageModule.g();
        if (this.mPageModule.d() != 1 || TextUtils.isEmpty(g)) {
            this.mHealthSubHeader.setVisibility(4);
            if (subHeaderParams != null) {
                subHeaderParams.height = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362565_res_0x7f0a0305);
                return;
            }
            return;
        }
        this.mHealthSubHeader.setVisibility(0);
        this.mHealthSubHeader.setHeadTitleText(g);
        if (this.mLayout != 12) {
            if (!TextUtils.isEmpty(this.mPageModule.a())) {
                this.mHealthSubHeader.setMoreLayoutVisibility(0);
                this.mHealthSubHeader.setMoreViewClickListener(this);
                return;
            } else {
                this.mHealthSubHeader.setMoreLayoutVisibility(8);
                return;
            }
        }
        boolean ag = nsn.ag(BaseApplication.getContext());
        if ((ag && this.mShowCardItemList.size() > 8) || (!ag && this.mShowCardItemList.size() > 4)) {
            this.mHealthSubHeader.setMoreLayoutVisibility(0);
            this.mHealthSubHeader.setMoreViewClickListener(this);
        } else {
            this.mHealthSubHeader.setMoreLayoutVisibility(8);
        }
    }

    private LinearLayout.LayoutParams getSubHeaderParams() {
        ViewGroup.LayoutParams layoutParams = this.mHealthSubHeader.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            int i = layoutParams2.leftMargin;
            int i2 = layoutParams2.rightMargin;
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i2;
        }
        return layoutParams2;
    }

    private void initConfiguredItemLayout() {
        boolean ag = nsn.ag(BaseApplication.getContext());
        setRecyclerViewLayout(pfh.b(this.mLayout, ag, this.mShowCardItemList.size()), pfh.d(this.mLayout, ag, this.mShowCardItemList.size(), this.mGutter), pfh.e(this.mLayout), pfh.b(this.mPageModule.h(), this.mLayout));
    }

    private void setRecyclerViewLayout(int i, int i2, int i3, int[] iArr) {
        ConfiguredLayoutAdapter configuredLayoutAdapter;
        GridLayoutManager gridLayoutManager;
        if (this.mContext == null) {
            LogUtil.h(TAG, "setRecyclerViewLayout() mContext is null.");
            return;
        }
        if (koq.b(this.mShowCardItemList) && koq.b(this.mShowVerticalLayoutDataList)) {
            LogUtil.h(TAG, "setRecyclerViewLayout() mShowList or mGridVerticalList is empty.");
            return;
        }
        if (i < 1) {
            LogUtil.h(TAG, "setRecyclerViewLayout() gridNumber should be at least 1.");
            return;
        }
        if (!nsn.ag(BaseApplication.getContext()) && this.mLayout == 9) {
            configuredLayoutAdapter = new ConfiguredLayoutAdapter(true, this.mContext, this.mShowVerticalLayoutDataList, this.mPageModule, true);
        } else {
            configuredLayoutAdapter = new ConfiguredLayoutAdapter(true, this.mContext, this.mShowCardItemList, this.mPageModule);
        }
        if (this.mOrientation == 2) {
            gridLayoutManager = new GridLayoutManager(this.mContext, 1);
            gridLayoutManager.setOrientation(0);
        } else {
            gridLayoutManager = new GridLayoutManager(this.mContext, i);
            gridLayoutManager.setOrientation(1);
        }
        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        this.mRecyclerView.addItemDecoration(new ConfiguredPageItemDecoration(i2, i3, i, iArr));
        this.mRecyclerView.setAdapter(configuredLayoutAdapter);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.setVisibility(0);
    }
}
