package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputBloodPressureActivity;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodOxygenGroupAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodPressureGroupAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.model.config.PrivacyDataFragmentConfig;
import com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyGroupDataView;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyGroupDataFragment;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.koq;
import defpackage.mlg;
import defpackage.nqt;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.rkb;
import defpackage.rru;
import defpackage.rsg;
import defpackage.rsi;
import defpackage.rsr;
import defpackage.rtv;
import defpackage.smy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class PrivacyGroupDataFragment extends PrivacyDataDetailMvpFragment<rsi> {

    /* renamed from: a, reason: collision with root package name */
    private CustomAlertDialog f10440a;
    private Integer[] b;
    private int c;
    private List<rsg> d = new ArrayList(16);
    private Map<Integer, CategoryGroupLayout> e;
    private long f;
    private rsi g;
    private HealthTextView h;
    private PrivacyGroupDataView i;
    private GroupDataAdapter j;
    private PageModelArgs k;
    private List<String> l;
    private LinearLayout m;
    private List<SourceInfo> n;
    private RelativeLayout o;
    private HealthSubTabWidget p;
    private int q;
    private List<Integer> r;
    private HealthViewPager s;
    private View t;

    public static PrivacyGroupDataFragment dQR_(PrivacyDataFragmentConfig privacyDataFragmentConfig, PageModelArgs pageModelArgs, Parcelable parcelable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_config", privacyDataFragmentConfig);
        bundle.putParcelable("extra_page_model_args", pageModelArgs);
        bundle.putParcelable("extra_source_info", parcelable);
        PrivacyGroupDataFragment privacyGroupDataFragment = new PrivacyGroupDataFragment();
        privacyGroupDataFragment.setArguments(bundle);
        return privacyGroupDataFragment;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public boolean onBackPressed() {
        View view = this.t;
        if (view != null && view.getVisibility() == 0) {
            return this.e.get(this.b[this.s.getCurrentItem()]).onBackPressed();
        }
        return super.onBackPressed();
    }

    public void c(boolean z) {
        HealthViewPager healthViewPager = this.s;
        if (healthViewPager == null || this.p == null) {
            return;
        }
        healthViewPager.setIsScroll(z);
        this.p.setClickable(z);
    }

    public int c() {
        HealthSubTabWidget healthSubTabWidget = this.p;
        if (healthSubTabWidget == null) {
            return 0;
        }
        return healthSubTabWidget.getHeight();
    }

    public void c(List<rsg> list) {
        this.o.setVisibility(8);
        if (koq.b(list)) {
            this.j.a(this.d);
            if (this.j.isEmpty()) {
                this.m.setVisibility(0);
                showRtlLanguage();
                return;
            }
            return;
        }
        this.m.setVisibility(8);
        long j = this.f;
        if (j == 0) {
            j = b();
        }
        i(list);
        this.j.a(this.d);
        if (this.i != null) {
            int groupCount = this.j.getGroupCount();
            for (int groupCount2 = this.j.getGroupCount(); groupCount2 < groupCount; groupCount2++) {
                this.i.expandGroup(groupCount2);
            }
            if (rsr.a(j, b())) {
                d();
            } else {
                this.i.expandGroup(groupCount - 1);
            }
        }
    }

    private void f() {
        long j = this.mPageModelArgs.getLong("Time");
        LogUtil.a("PrivacyGroupDataFragment", "scrollToPosition time = " + j);
        if (j == Long.MIN_VALUE) {
            return;
        }
        int i = 0;
        String a2 = mlg.a(j, 0);
        Iterator<rsg> it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            rsg next = it.next();
            i++;
            if (!a2.equals(next.e())) {
                i += next.d().size();
            } else {
                Iterator<PrivacyDataModel> it2 = next.d().iterator();
                while (it2.hasNext() && j != it2.next().getStartTime()) {
                    i++;
                }
            }
        }
        if (this.i != null) {
            int j2 = (int) ((nsn.j() * 0.5f) - this.mSpinnerCustomTitleBar.getHeight());
            LogUtil.a("PrivacyGroupDataFragment", "scrollToPosition offset = " + j2);
            this.i.setSelectionFromTop(i, j2);
        }
        this.mPageModelArgs.putLong("Time", Long.MIN_VALUE);
    }

    private long b() {
        if (this.d.isEmpty()) {
            return 0L;
        }
        return this.j.getGroup(r0.getGroupCount() - 1).d().get(r0.size() - 1).getStartTime();
    }

    public void b(List<rsg> list) {
        this.f = b();
        if (this.mPageModelArgs.getDataSource() == 2) {
            this.d.clear();
        }
        c(list);
    }

    public void e(List<rsg> list) {
        this.d = list;
        this.o.setVisibility(8);
        if (koq.b(list)) {
            this.m.setVisibility(0);
            showRtlLanguage();
        } else {
            this.j.a(this.d);
            if (this.i != null) {
                int groupCount = this.j.getGroupCount();
                for (int i = 0; i < groupCount; i++) {
                    this.i.expandGroup(i);
                }
            }
            f();
        }
        if (rsr.a(this.mPageModelArgs)) {
            setUiState(1);
        }
    }

    public void e(Map<Integer, List<rsg>> map) {
        CategoryGroupLayout categoryGroupLayout;
        Map<Integer, CategoryGroupLayout> map2 = this.e;
        if (map2 == null || map2.size() == 0) {
            return;
        }
        this.o.setVisibility(8);
        this.t.setVisibility(0);
        for (Map.Entry<Integer, CategoryGroupLayout> entry : this.e.entrySet()) {
            entry.getValue().c(map != null ? map.get(entry.getKey()) : null);
        }
        if (!rsr.a(this.mPageModelArgs) || (categoryGroupLayout = this.e.get(Integer.valueOf(this.c))) == null) {
            return;
        }
        categoryGroupLayout.setUiState(1);
    }

    public void d(List<SourceInfo> list) {
        a(list);
    }

    private void a(List<SourceInfo> list) {
        this.n = new ArrayList(10);
        if (list != null && list.size() > 0) {
            this.n.addAll(list);
        }
        this.n.add(0, null);
        ArrayList arrayList = new ArrayList(10);
        HashMap hashMap = new HashMap(16);
        hashMap.put("SpinnerItemIcon", String.valueOf(R.mipmap._2131820864_res_0x7f110140));
        hashMap.put("SpinnerItemText", getString(R$string.IDS_privacy_all_data));
        arrayList.add(hashMap);
        this.q = 0;
        if (list != null && list.size() > 0) {
            for (SourceInfo sourceInfo : list) {
                HashMap hashMap2 = new HashMap(16);
                hashMap2.put("SpinnerItemIcon", String.valueOf(sourceInfo.getIconResourceId()));
                hashMap2.put("SpinnerItemText", sourceInfo.getDeviceName());
                arrayList.add(hashMap2);
            }
        }
        initSpinnerWithIcon(arrayList, this.q);
    }

    private void e() {
        if (this.mDataSource != 3) {
            return;
        }
        this.l = rsr.c(this.mPageType);
        List<Integer> f = rsr.f(this.mPageType);
        this.r = f;
        if (f == null || f.size() == 0) {
            return;
        }
        if (this.r.contains(Integer.valueOf(this.mSubPageType))) {
            this.q = this.r.indexOf(Integer.valueOf(this.mSubPageType));
        } else {
            this.q = 0;
        }
        initSpinner(this.l, this.q);
    }

    private void i(List<rsg> list) {
        rsg rsgVar;
        List<PrivacyDataModel> d;
        if (list == null) {
            return;
        }
        int size = this.d.size();
        if (size == 0) {
            this.d.addAll(list);
            return;
        }
        boolean z = false;
        for (rsg rsgVar2 : list) {
            if (z) {
                this.d.add(rsgVar2);
            } else {
                String e = rsgVar2.e();
                if (e != null && (d = (rsgVar = this.d.get(size - 1)).d()) != null) {
                    if (e.equals(rsgVar.e())) {
                        d.addAll(rsgVar2.d());
                        z = true;
                    } else {
                        this.d.add(rsgVar2);
                    }
                }
            }
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment, com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initViews(View view) {
        super.initViews(view);
        this.g = (rsi) getPresenter();
        this.c = this.mPageModelArgs.getInt("categoryType");
        if (this.mPageType == 107 && this.mDataSource == 3) {
            this.j = new BloodPressureGroupAdapter();
        } else if (this.mPageType == 105 && this.mDataSource == 3) {
            this.j = new BloodOxygenGroupAdapter();
        } else {
            this.j = new GroupDataAdapter();
        }
        this.o = (RelativeLayout) view.findViewById(R.id.privacy_data_loading);
        this.m = (LinearLayout) view.findViewById(R.id.no_data_source);
        this.h = (HealthTextView) view.findViewById(R.id.hint);
        j();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.privacy_data_fragment_container);
        dQQ_(linearLayout);
        initComponent(linearLayout);
        if (this.mComponents instanceof PrivacyGroupDataView) {
            this.i = (PrivacyGroupDataView) this.mComponents;
        }
        i();
        e();
    }

    private void dQQ_(ViewGroup viewGroup) {
        if (rsr.i(this.mPageModelArgs)) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.privacy_tab_layout, (ViewGroup) null);
            this.t = inflate;
            viewGroup.addView(inflate);
            HealthSubTabWidget healthSubTabWidget = (HealthSubTabWidget) this.t.findViewById(R.id.privacy_tab);
            this.p = healthSubTabWidget;
            healthSubTabWidget.setBackgroundColor(getContext().getColor(R.color._2131297799_res_0x7f090607));
            this.s = (HealthViewPager) this.t.findViewById(R.id.privacy_view_pager);
            this.e = new HashMap(10);
            String[] j = rsr.j(this.mPageType);
            Integer[] b = rsr.b(this.mPageType);
            this.b = b;
            if (this.c == Integer.MIN_VALUE) {
                this.c = b[0].intValue();
                this.mPageModelArgs.putInt("categoryType", this.c);
            }
            nqt nqtVar = new nqt(this.s, this.p);
            for (int i = 0; i < j.length; i++) {
                smy c = this.p.c(j[i]);
                CategoryGroupLayout categoryGroupLayout = new CategoryGroupLayout(getContext(), getActivity(), this, this.mPageModelArgs);
                this.e.put(this.b[i], categoryGroupLayout);
                if (this.c == this.b[i].intValue()) {
                    nqtVar.cGt_(c, categoryGroupLayout, true);
                } else {
                    nqtVar.cGt_(c, categoryGroupLayout, false);
                }
            }
            this.s.addOnPageChangeListener(new HwViewPager.d() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyGroupDataFragment.5
                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.d, com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    PrivacyGroupDataFragment privacyGroupDataFragment = PrivacyGroupDataFragment.this;
                    privacyGroupDataFragment.c = privacyGroupDataFragment.b[i2].intValue();
                    PrivacyGroupDataFragment.this.mPageModelArgs.putInt("categoryType", PrivacyGroupDataFragment.this.c);
                    CategoryGroupLayout categoryGroupLayout2 = (CategoryGroupLayout) PrivacyGroupDataFragment.this.e.get(PrivacyGroupDataFragment.this.b[i2]);
                    if (categoryGroupLayout2 != null) {
                        categoryGroupLayout2.setTitleBarState(1);
                    }
                    if (PrivacyGroupDataFragment.this.mPageType == 106) {
                        rtv.f();
                    }
                }
            });
        }
    }

    private void i() {
        PrivacyGroupDataView privacyGroupDataView = this.i;
        if (privacyGroupDataView == null) {
            LogUtil.h("PrivacyGroupDataFragment", "setAllDataViewInfo mGroupDataView is null");
            return;
        }
        privacyGroupDataView.setAdapter(this.j);
        this.j.c(!this.mIsOtherManufacturer);
        this.i.setDividerHeight(0);
        this.i.setLoadMoreListener(new PrivacyGroupDataView.LoadMoreListener() { // from class: rtw
            @Override // com.huawei.ui.main.stories.privacy.template.view.component.PrivacyGroupDataView.LoadMoreListener
            public final void loadMore() {
                PrivacyGroupDataFragment.this.d();
            }
        });
        this.i.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyGroupDataFragment$$ExternalSyntheticLambda2
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public final boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return PrivacyGroupDataFragment.this.dQS_(expandableListView, view, i, j);
            }
        });
        this.j.b(new GroupDataAdapter.OnItemClickListener() { // from class: rtu
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter.OnItemClickListener
            public final void onItemClickListener(int i, int i2) {
                PrivacyGroupDataFragment.this.e(i, i2);
            }
        });
        this.j.c(new GroupDataAdapter.OnLongClickListener() { // from class: rtt
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter.OnLongClickListener
            public final void onLongClickListener(int i, int i2) {
                PrivacyGroupDataFragment.this.c(i, i2);
            }
        });
    }

    /* synthetic */ boolean dQS_(ExpandableListView expandableListView, View view, int i, long j) {
        LogUtil.c("PrivacyGroupDataFragment", expandableListView.isGroupExpanded(i) + ";" + i);
        if (i == this.j.getGroupCount() - 1 && expandableListView.isGroupExpanded(i)) {
            d();
        }
        ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i);
        return false;
    }

    public /* synthetic */ void c(int i, int i2) {
        if (isSupportLongClick()) {
            this.j.a(i, i2);
            if (this.mPageType == 103) {
                rtv.e(1, getTotalCount() == getCheckedCount());
            }
            showDelConfirmDialog(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        if (this.mIsOtherManufacturer) {
            return;
        }
        if (this.mToolBarManager.e() != 1) {
            this.j.a(i, i2);
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
        a(this.j.d(i, i2));
    }

    private void a(PrivacyDataModel privacyDataModel) {
        int i = this.mPageType;
        if (i == 103) {
            PageModelArgs b = rsr.b(this.mPageModelArgs);
            this.k = b;
            b.setTimestamp(privacyDataModel.getEndTime());
            this.k.setModuleType(2);
            PrivacyDataModelActivity.dQK_(getViewContext(), this.k, this.mSourceInfoBean);
            return;
        }
        if (i != 105) {
            if (i != 112) {
                if (i == 107) {
                    b(privacyDataModel);
                    return;
                }
                if (i != 108) {
                    if (this.mPageType == 106 || this.mPageType == 113) {
                        rtv.a();
                    }
                    PageModelArgs b2 = rsr.b(this.mPageModelArgs);
                    this.k = b2;
                    b2.setTimestamp(privacyDataModel.getStartTime());
                    this.k.setModuleType(2);
                    Intent intent = new Intent(getViewContext(), (Class<?>) PrivacyDataModelActivity.class);
                    intent.putExtra("extra_page_model_args", this.k);
                    intent.putExtra("extra_source_info", this.mSourceInfoBean);
                    startActivityForResult(intent, 1);
                    return;
                }
            }
            Intent intent2 = new Intent();
            intent2.putExtra("extra_source_info", this.mSourceInfoBean);
            intent2.putExtra("extra_page_model_args", this.mPageModelArgs);
            intent2.putExtra("extra_privacy_data_model", privacyDataModel);
            intent2.setClass(getViewContext(), PrivacyDetailActivity.class);
            getViewContext().startActivity(intent2);
            return;
        }
        c(privacyDataModel);
    }

    private void b(PrivacyDataModel privacyDataModel) {
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

    private void c(PrivacyDataModel privacyDataModel) {
        Intent intent = new Intent();
        if (this.mDataSource == 3) {
            int i = privacyDataModel.getInt("bloodOxygenCardKey");
            if (i == 1001 || i == 1002) {
                if (i == 1001) {
                    int b = rtv.b(i, privacyDataModel.getInt("lakeLouiseScoreKey"));
                    if (b != 0) {
                        rtv.c(2, rtv.a(i), b);
                    }
                } else {
                    rtv.c(2, rtv.a(i), rtv.b(i, privacyDataModel.getInt("bloodOxygenKey")));
                }
                intent.putExtra("extra_source_info", this.mSourceInfoBean);
                intent.putExtra("extra_page_model_args", this.mPageModelArgs);
                intent.putExtra("extra_privacy_data_model", privacyDataModel);
                intent.setClass(getViewContext(), PrivacyDetailActivity.class);
            } else {
                this.mPageModelArgs.setTimestamp(privacyDataModel.getStartTime());
                this.mPageModelArgs.setModuleType(2);
                intent.putExtra("extra_page_model_args", this.mPageModelArgs);
                intent.putExtra("extra_source_info", this.mSourceInfoBean);
                intent.setClass(getViewContext(), PrivacyDataModelActivity.class);
            }
        } else {
            this.mPageModelArgs.setTimestamp(privacyDataModel.getStartTime());
            this.mPageModelArgs.setModuleType(2);
            intent.putExtra("extra_page_model_args", this.mPageModelArgs);
            intent.putExtra("extra_source_info", this.mSourceInfoBean);
            intent.setClass(getViewContext(), PrivacyDataModelActivity.class);
        }
        getViewContext().startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("PrivacyGroupDataFragment", "onActivityResult requestCode ", Integer.valueOf(i), " resultCode ", Integer.valueOf(i2));
        if (i2 == -1 && i == 1) {
            a();
        } else if (i2 == 0 && i == 2 && intent != null && intent.getBooleanExtra("edit", false)) {
            a();
        }
    }

    public void a() {
        this.d.clear();
        rsi rsiVar = this.g;
        if (rsiVar != null) {
            rsiVar.d(this.mPageModelArgs);
        }
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
        List<rsg> list = this.d;
        return (list == null || list.isEmpty()) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void onDeleteBtnClicked() {
        if (this.mPageType == 103) {
            rtv.e(0, false);
        }
        View view = this.t;
        if (view != null && view.getVisibility() == 0) {
            CategoryGroupLayout categoryGroupLayout = this.e.get(Integer.valueOf(this.c));
            if (categoryGroupLayout != null) {
                categoryGroupLayout.setUiState(2);
                return;
            }
            return;
        }
        setUiState(2);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public int getCheckedCount() {
        return this.j.e();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public int getTotalCount() {
        return this.j.b();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void onToolBarStatusChanged(int i) {
        if (i == 1) {
            this.j.d(1);
            return;
        }
        if (i == 2) {
            this.j.d(2);
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
        this.j.d(z);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void deleteDate() {
        if (this.mDataManager == null) {
            return;
        }
        if (rsr.e(this.mPageModelArgs)) {
            CustomAlertDialog c = new CustomAlertDialog.Builder(getContext()).cyp_(LayoutInflater.from(getContext()).inflate(R.layout.dialog_deleting, (ViewGroup) null)).c();
            this.f10440a = c;
            c.setCancelable(false);
            this.f10440a.show();
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
        this.mDataManager.b(this.j.c(), new rkb(i, str, str2), new DataSourceCallback() { // from class: rtx
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                PrivacyGroupDataFragment.this.c(i2, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Boolean bool) {
        LogUtil.a("PrivacyGroupDataFragment", "deleteData callBack code : " + i);
        CustomAlertDialog customAlertDialog = this.f10440a;
        if (customAlertDialog != null) {
            customAlertDialog.cancel();
            this.f10440a = null;
        }
        if (getActivity() == null) {
            return;
        }
        nrh.b(BaseApplication.getContext(), i == 0 ? R.string.IDS_device_wifi_clear_user_success : R.string.IDS_device_wifi_delete_fail);
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        rsi rsiVar = this.g;
        if (rsiVar == null || !rsiVar.b()) {
            return;
        }
        this.g.d(this.mPageModelArgs);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.privacy_fragment_data, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        rru.d().b();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpFragment
    public void clickSpinner(int i) {
        if (this.mPageType == 107) {
            c(i);
        } else if (this.mPageType == 105) {
            e(i);
        } else {
            d(i);
        }
    }

    private void c(int i) {
        if (koq.b(this.r, i)) {
            LogUtil.h("PrivacyGroupDataFragment", "selectBpSubpage mSubpageTypeList isOutOfBounds");
            return;
        }
        h();
        this.mPageModelArgs.putInt("Subpage", this.r.get(i).intValue());
        a();
    }

    private void e(int i) {
        if (koq.b(this.r, i)) {
            LogUtil.h("PrivacyGroupDataFragment", "selectSpo2Subpage mSubpageTypeList isOutOfBounds");
            return;
        }
        rtv.e(this.r.get(i).intValue());
        h();
        this.j.a(new ArrayList());
        this.mPageModelArgs.putInt("Subpage", this.r.get(i).intValue());
        a();
    }

    private void d(int i) {
        if (koq.b(this.n, i)) {
            LogUtil.h("PrivacyGroupDataFragment", "selectSource mSourceList isOutOfBounds");
            return;
        }
        SourceInfo sourceInfo = this.n.get(i);
        if (this.mPageType == 103) {
            rtv.c(rtv.b(sourceInfo));
        }
        this.mPageModelArgs.setSourceInfo(sourceInfo);
        j();
        h();
        a();
    }

    private void h() {
        View view = this.t;
        if (view != null) {
            view.setVisibility(8);
        }
        this.m.setVisibility(8);
        this.o.setVisibility(0);
    }

    private void j() {
        if (this.mPageType == 103 && this.mDataSource == 3) {
            if (this.mPageModelArgs.getSourceInfo() == null) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
        }
    }
}
