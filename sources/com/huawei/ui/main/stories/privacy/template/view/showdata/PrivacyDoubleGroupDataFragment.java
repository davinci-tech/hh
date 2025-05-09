package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputBloodPressureActivity;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodPressureDoubleGroupAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.model.config.PrivacyDataFragmentConfig;
import com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyDoubleGroupDataView;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDoubleGroupDataFragment;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.koq;
import defpackage.nqt;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.rkb;
import defpackage.rsb;
import defpackage.rsj;
import defpackage.rsr;
import defpackage.rtv;
import defpackage.smy;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class PrivacyDoubleGroupDataFragment extends PrivacyDataDetailMvpFragment<rsj> {
    private static final String c = "PrivacyDoubleGroupDataFragment";

    /* renamed from: a, reason: collision with root package name */
    private Integer[] f10437a;
    private List<rsb> b;
    private int d;
    private Map<Integer, CategoryDoubleGroupLayout> e;
    private PrivacyDoubleGroupDataView f;
    private DoubleGroupDataAdapter g;
    private LinearLayout h;
    private rsj i;
    private RelativeLayout j;
    private HealthViewPager k;
    private List<Integer> m;
    private HealthSubTabWidget n;
    private View o;

    public static PrivacyDoubleGroupDataFragment dQP_(PrivacyDataFragmentConfig privacyDataFragmentConfig, PageModelArgs pageModelArgs, Parcelable parcelable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_config", privacyDataFragmentConfig);
        bundle.putParcelable("extra_page_model_args", pageModelArgs);
        bundle.putParcelable("extra_source_info", parcelable);
        PrivacyDoubleGroupDataFragment privacyDoubleGroupDataFragment = new PrivacyDoubleGroupDataFragment();
        privacyDoubleGroupDataFragment.setArguments(bundle);
        return privacyDoubleGroupDataFragment;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean onBackPressed() {
        View view = this.o;
        if (view != null && view.getVisibility() == 0) {
            return this.e.get(this.f10437a[this.k.getCurrentItem()]).onBackPressed();
        }
        return super.onBackPressed();
    }

    public void d(List<rsb> list) {
        this.b = list;
        this.j.setVisibility(8);
        if (koq.b(this.b)) {
            this.h.setVisibility(0);
            showRtlLanguage();
        } else {
            this.g.d(this.b);
        }
        if (rsr.a(this.mPageModelArgs)) {
            setUiState(1);
        }
    }

    public void a(Map<Integer, List<rsb>> map) {
        CategoryDoubleGroupLayout categoryDoubleGroupLayout;
        Map<Integer, CategoryDoubleGroupLayout> map2 = this.e;
        if (map2 == null || map2.size() == 0) {
            return;
        }
        this.j.setVisibility(8);
        this.o.setVisibility(0);
        for (Map.Entry<Integer, CategoryDoubleGroupLayout> entry : this.e.entrySet()) {
            entry.getValue().d(map != null ? map.get(entry.getKey()) : null);
        }
        if (!rsr.a(this.mPageModelArgs) || (categoryDoubleGroupLayout = this.e.get(Integer.valueOf(this.d))) == null) {
            return;
        }
        categoryDoubleGroupLayout.setUiState(1);
    }

    public void c() {
        rsj rsjVar = this.i;
        if (rsjVar != null) {
            rsjVar.d(this.mPageModelArgs);
        }
    }

    public void a() {
        if (this.i != null) {
            this.mPageModelArgs.putLong("startTime", Long.MIN_VALUE);
            this.mPageModelArgs.putLong("endTime", Long.MIN_VALUE);
            this.mCustomTitleBar.setAppBarVisible(false);
            this.mSpinnerCustomTitleBar.setAppBarVisible(true);
            this.i.d(this.mPageModelArgs);
        }
    }

    public void c(boolean z) {
        HealthViewPager healthViewPager = this.k;
        if (healthViewPager == null || this.n == null) {
            return;
        }
        healthViewPager.setIsScroll(z);
        this.n.setClickable(z);
    }

    public int e() {
        HealthSubTabWidget healthSubTabWidget = this.n;
        if (healthSubTabWidget == null) {
            return 0;
        }
        return healthSubTabWidget.getHeight();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean isShowDeleteBtn() {
        return rsr.a(this.mPageModelArgs) && hasData();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean isSupportLongClick() {
        return rsr.a(this.mPageModelArgs) && this.mToolBarManager.e() == 1;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean hasData() {
        return !koq.b(this.b);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void onDeleteBtnClicked() {
        View view = this.o;
        if (view != null && view.getVisibility() == 0) {
            CategoryDoubleGroupLayout categoryDoubleGroupLayout = this.e.get(Integer.valueOf(this.d));
            if (categoryDoubleGroupLayout != null) {
                categoryDoubleGroupLayout.setUiState(2);
                return;
            }
            return;
        }
        setUiState(2);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public int getCheckedCount() {
        return this.g.b();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public int getTotalCount() {
        return this.g.j();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void onToolBarStatusChanged(int i) {
        if (i == 1) {
            this.g.c(1);
            return;
        }
        if (i == 2) {
            this.g.c(2);
        } else if (i == 3) {
            setAllChecked(false);
        } else {
            if (i != 4) {
                return;
            }
            setAllChecked(true);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void setAllChecked(boolean z) {
        this.g.c(z);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void deleteDate() {
        if (this.mDataManager == null) {
            return;
        }
        int i = this.mClassType;
        String str = this.mDeviceUuid;
        String str2 = this.mPackageName;
        SourceInfo sourceInfo = this.mPageModelArgs.getSourceInfo();
        if (sourceInfo != null) {
            if (sourceInfo.getSourceType() == 1) {
                str2 = sourceInfo.getPackageName();
                i = 1;
            } else {
                str = sourceInfo.getDeviceUniqueCode();
                i = 2;
            }
        }
        rkb rkbVar = new rkb(i, str, str2);
        if (this.mPageType == 107) {
            rtv.d();
        }
        this.mDataManager.b(this.g.e(), rkbVar, new DataSourceCallback() { // from class: rtq
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                PrivacyDoubleGroupDataFragment.this.e(i2, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Boolean bool) {
        LogUtil.a(c, "deleteData callBack code : ", Integer.valueOf(i));
        if (getActivity() == null) {
            return;
        }
        nrh.b(BaseApplication.getContext(), i == 0 ? R.string.IDS_device_wifi_clear_user_success : R.string.IDS_device_wifi_delete_fail);
        c();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void clickSpinner(int i) {
        if (this.mPageType == 107) {
            d(i);
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.privacy_fragment_data, viewGroup, false);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment, com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initViews(View view) {
        super.initViews(view);
        this.d = this.mPageModelArgs.getInt("categoryType");
        this.i = (rsj) getPresenter();
        this.j = (RelativeLayout) view.findViewById(R.id.privacy_data_loading);
        this.h = (LinearLayout) view.findViewById(R.id.no_data_source);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.privacy_data_fragment_container);
        dQO_(linearLayout);
        initComponent(linearLayout);
        d();
        b();
        if (this.mPageModelArgs.getLong("startTime") == Long.MIN_VALUE || this.mPageType != 107) {
            return;
        }
        this.mPrivacyDataModelActivity.setTitle(getString(R$string.IDS_privacy_bp_abnormal_data));
        this.mCustomTitleBar.setAppBarVisible(true);
        this.mSpinnerCustomTitleBar.setAppBarVisible(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a(c, "onActivityResult requestCode ", Integer.valueOf(i), " resultCode ", Integer.valueOf(i2));
        if (i2 == -1 && i == 1) {
            c();
        } else if (i2 == 0 && i == 2 && intent != null && intent.getBooleanExtra("edit", false)) {
            c();
        }
    }

    private void dQO_(ViewGroup viewGroup) {
        if (rsr.j(this.mPageModelArgs)) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.privacy_tab_layout_double, (ViewGroup) null);
            this.o = inflate;
            viewGroup.addView(inflate);
            HealthSubTabWidget healthSubTabWidget = (HealthSubTabWidget) this.o.findViewById(R.id.privacy_tab);
            this.n = healthSubTabWidget;
            healthSubTabWidget.setBackgroundColor(getContext().getColor(R.color._2131297799_res_0x7f090607));
            this.k = (HealthViewPager) this.o.findViewById(R.id.privacy_view_pager);
            this.e = new LinkedHashMap(10);
            String[] j = rsr.j(this.mPageType);
            Integer[] b = rsr.b(this.mPageType);
            this.f10437a = b;
            if (this.d == Integer.MIN_VALUE) {
                this.d = b[0].intValue();
            }
            nqt nqtVar = new nqt(this.k, this.n);
            for (int i = 0; i < j.length; i++) {
                smy c2 = this.n.c(j[i]);
                CategoryDoubleGroupLayout categoryDoubleGroupLayout = new CategoryDoubleGroupLayout(getContext(), getActivity(), this, this.mPageModelArgs);
                this.e.put(this.f10437a[i], categoryDoubleGroupLayout);
                if (this.d == this.f10437a[i].intValue()) {
                    nqtVar.cGt_(c2, categoryDoubleGroupLayout, true);
                    categoryDoubleGroupLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDoubleGroupDataFragment.3
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            PrivacyDoubleGroupDataFragment.this.n.b(PrivacyDoubleGroupDataFragment.this.n.getSelectedSubTabPostion());
                            PrivacyDoubleGroupDataFragment.this.n.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                } else {
                    nqtVar.cGt_(c2, categoryDoubleGroupLayout, false);
                }
            }
            this.k.addOnPageChangeListener(new HwViewPager.d() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDoubleGroupDataFragment.1
                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.d, com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    rtv.e(2, i2);
                    PrivacyDoubleGroupDataFragment privacyDoubleGroupDataFragment = PrivacyDoubleGroupDataFragment.this;
                    privacyDoubleGroupDataFragment.d = privacyDoubleGroupDataFragment.f10437a[i2].intValue();
                    CategoryDoubleGroupLayout categoryDoubleGroupLayout2 = (CategoryDoubleGroupLayout) PrivacyDoubleGroupDataFragment.this.e.get(Integer.valueOf(PrivacyDoubleGroupDataFragment.this.d));
                    if (categoryDoubleGroupLayout2 != null) {
                        categoryDoubleGroupLayout2.setUiState(1);
                    }
                }
            });
        }
    }

    private void d() {
        if (this.mComponents instanceof PrivacyDoubleGroupDataView) {
            this.f = (PrivacyDoubleGroupDataView) this.mComponents;
        }
        if (this.f == null) {
            return;
        }
        if (this.mPageType == 107 && this.mDataSource == 3) {
            this.g = new BloodPressureDoubleGroupAdapter();
        } else {
            this.g = new DoubleGroupDataAdapter();
        }
        this.g.e(!this.mIsOtherManufacturer);
        this.g.a(new DoubleGroupDataAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDoubleGroupDataFragment.2
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter.OnItemClickListener
            public void onItemClick(DoubleGroupDataAdapter.h hVar) {
                PrivacyDoubleGroupDataFragment.this.d(hVar);
            }
        });
        this.g.b(new DoubleGroupDataAdapter.OnItemLongClickListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDoubleGroupDataFragment.5
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter.OnItemLongClickListener
            public void onItemLongClick(DoubleGroupDataAdapter.h hVar) {
                if (PrivacyDoubleGroupDataFragment.this.isSupportLongClick()) {
                    PrivacyDoubleGroupDataFragment.this.showDelConfirmDialog(true);
                }
            }
        });
        this.f.setAdapter(this.g);
        this.f.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DoubleGroupDataAdapter.h hVar) {
        if (this.mIsOtherManufacturer) {
            return;
        }
        if (this.mToolBarManager.e() != 1) {
            setTitleBarState(2);
            if (getCheckedCount() == getTotalCount()) {
                this.mToolBarManager.b(4);
                return;
            } else {
                this.mToolBarManager.b(2);
                return;
            }
        }
        if (nsn.a(500)) {
            return;
        }
        a(this.g.d(hVar));
    }

    private void a(PrivacyDataModel privacyDataModel) {
        if (this.mPageType != 107) {
            return;
        }
        c(privacyDataModel);
    }

    private void c(PrivacyDataModel privacyDataModel) {
        if (privacyDataModel == null) {
            LogUtil.h(c, "startBloodPressurePage dataModel is null");
            return;
        }
        if (this.mDataSource == 3) {
            Intent intent = new Intent(getViewContext(), (Class<?>) InputBloodPressureActivity.class);
            intent.putExtra(MediaManager.ROPE_MEDIA_HIGH, privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC));
            intent.putExtra("low", privacyDataModel.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC));
            if (privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SPHYGMUS) != Integer.MIN_VALUE) {
                intent.putExtra("bmp", privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SPHYGMUS));
            }
            intent.putExtra("measureAbnormal", privacyDataModel.getInt("measureAbnormal"));
            intent.putExtra("deletetime", privacyDataModel.getStartTime());
            intent.putExtra("clientId", privacyDataModel.getClientId());
            intent.putExtra("isInputData", privacyDataModel.getInt("source_type") == 3);
            startActivityForResult(intent, 2);
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("extra_source_info", this.mSourceInfoBean);
        intent2.putExtra("extra_page_model_args", this.mPageModelArgs);
        intent2.putExtra("extra_privacy_data_model", privacyDataModel);
        intent2.setClass(getViewContext(), PrivacyDetailActivity.class);
        getViewContext().startActivity(intent2);
    }

    private void b() {
        if (this.mDataSource != 3) {
            return;
        }
        List<Integer> f = rsr.f(this.mPageType);
        this.m = f;
        if (f == null || f.size() == 0) {
            return;
        }
        initSpinner(rsr.c(this.mPageType), this.m.contains(Integer.valueOf(this.mSubPageType)) ? this.m.indexOf(Integer.valueOf(this.mSubPageType)) : 0);
    }

    private void d(int i) {
        if (koq.b(this.m, i)) {
            LogUtil.h(c, "selectBpSubpage mSubpageTypeList isOutOfBounds");
            return;
        }
        rtv.e(i, 0);
        j();
        this.mPageModelArgs.putInt("Subpage", this.m.get(i).intValue());
        c();
    }

    private void j() {
        View view = this.o;
        if (view != null) {
            view.setVisibility(8);
        }
        this.h.setVisibility(8);
        this.j.setVisibility(0);
    }
}
