package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.main.stories.health.activity.healthdata.InputBloodPressureActivity;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodPressureDoubleGroupAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.component.PrivacyDoubleGroupDataView;
import com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryDoubleGroupLayout;
import defpackage.koq;
import defpackage.mlg;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.rkb;
import defpackage.rsb;
import defpackage.rsg;
import defpackage.rtv;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class CategoryDoubleGroupLayout extends CategoryCommonLayout {
    private static final String e = "CategoryDoubleGroupLayout";

    /* renamed from: a, reason: collision with root package name */
    private PrivacyDoubleGroupDataFragment f10430a;
    private PrivacyDoubleGroupDataView b;
    private List<rsb> c;
    private DoubleGroupDataAdapter d;
    private HealthButton f;
    private LinearLayoutManager h;
    private LinearLayout i;

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected int getLayoutId() {
        return R.layout.privacy_fragment_data;
    }

    public CategoryDoubleGroupLayout(Context context, Activity activity, Fragment fragment, PageModelArgs pageModelArgs) {
        super(context, activity, pageModelArgs);
        if (fragment instanceof PrivacyDoubleGroupDataFragment) {
            this.f10430a = (PrivacyDoubleGroupDataFragment) fragment;
        }
        initViews();
    }

    public void d(List<rsb> list) {
        this.c = list;
        e();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected void initViews() {
        super.initViews();
        ((RelativeLayout) findViewById(R.id.privacy_data_loading)).setVisibility(8);
        this.i = (LinearLayout) findViewById(R.id.no_data_source);
        HealthButton healthButton = (HealthButton) findViewById(R.id.load_all);
        this.f = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: rtd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CategoryDoubleGroupLayout.dQI_(CategoryDoubleGroupLayout.this, view);
            }
        });
        b();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected boolean hasData() {
        return !koq.b(this.c);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected int getCheckedCount() {
        return this.d.b();
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected int getTotalCount() {
        return this.d.j();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    public void onToolBarStatusChanged(int i) {
        if (i == 1) {
            if (this.mPageModelArgs.getLong("startTime") != Long.MIN_VALUE) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            this.d.c(1);
            this.f10430a.c(true);
            return;
        }
        if (i == 2) {
            this.f.setVisibility(8);
            this.d.c(2);
            this.f10430a.c(false);
        } else if (i == 3) {
            setAllChecked(false);
        } else {
            if (i != 4) {
                return;
            }
            setAllChecked(true);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected void setAllChecked(boolean z) {
        this.d.c(z);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.view.showdata.CategoryCommonLayout
    protected void deleteDate() {
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
        this.mDataManager.b(this.d.e(), rkbVar, new DataSourceCallback() { // from class: rtc
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                CategoryDoubleGroupLayout.this.b(i2, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Boolean bool) {
        LogUtil.a(e, "deleteData callBack code : " + i);
        nrh.b(getContext(), i == 0 ? R.string.IDS_device_wifi_clear_user_success : R.string.IDS_device_wifi_delete_fail);
        this.f10430a.c();
    }

    private void e() {
        if (this.b == null) {
            return;
        }
        if (koq.b(this.c)) {
            this.i.setVisibility(0);
            showRtlLanguage();
        } else {
            this.i.setVisibility(8);
            this.d.d(this.c);
            a();
        }
    }

    private void b() {
        PrivacyDoubleGroupDataView privacyDoubleGroupDataView = new PrivacyDoubleGroupDataView(getContext());
        this.b = privacyDoubleGroupDataView;
        privacyDoubleGroupDataView.setOverScrollMode(2);
        ((LinearLayout) findViewById(R.id.privacy_data_fragment_container)).addView(this.b);
        if (this.mPageType == 107) {
            this.d = new BloodPressureDoubleGroupAdapter();
        } else {
            this.d = new DoubleGroupDataAdapter();
        }
        this.d.e(!this.mIsOtherManufacturer);
        this.d.a(new DoubleGroupDataAdapter.OnItemClickListener() { // from class: rtf
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter.OnItemClickListener
            public final void onItemClick(DoubleGroupDataAdapter.h hVar) {
                CategoryDoubleGroupLayout.this.b(hVar);
            }
        });
        this.d.b(new DoubleGroupDataAdapter.OnItemLongClickListener() { // from class: rte
            @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter.OnItemLongClickListener
            public final void onItemLongClick(DoubleGroupDataAdapter.h hVar) {
                CategoryDoubleGroupLayout.this.e(hVar);
            }
        });
        this.b.setAdapter(this.d);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.h = linearLayoutManager;
        this.b.setLayoutManager(linearLayoutManager);
    }

    public /* synthetic */ void e(DoubleGroupDataAdapter.h hVar) {
        if (isSupportLongClick()) {
            showDelConfirmDialog(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DoubleGroupDataAdapter.h hVar) {
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
        a(this.d.d(hVar));
    }

    private void a(PrivacyDataModel privacyDataModel) {
        if (this.mPageType != 107) {
            return;
        }
        e(privacyDataModel);
    }

    private void e(PrivacyDataModel privacyDataModel) {
        if (privacyDataModel == null) {
            LogUtil.h(e, "startBpPage dataModel is null");
            return;
        }
        Intent intent = new Intent(getContext(), (Class<?>) InputBloodPressureActivity.class);
        intent.putExtra(MediaManager.ROPE_MEDIA_HIGH, privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC));
        intent.putExtra("low", privacyDataModel.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC));
        if (privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SPHYGMUS) != Integer.MIN_VALUE) {
            intent.putExtra("bmp", privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SPHYGMUS));
        }
        intent.putExtra("measureAbnormal", privacyDataModel.getInt("measureAbnormal"));
        intent.putExtra("deletetime", privacyDataModel.getStartTime());
        intent.putExtra("clientId", privacyDataModel.getClientId());
        intent.putExtra("isInputData", privacyDataModel.getInt("source_type") == 3);
        this.f10430a.startActivityForResult(intent, 2);
    }

    private void a() {
        long j = this.mPageModelArgs.getLong("Time");
        LogUtil.a(e, "scrollToPosition time = " + j);
        if (j == Long.MIN_VALUE) {
            return;
        }
        int i = 0;
        String a2 = mlg.a(j, 0);
        String a3 = mlg.a(j, Integer.MAX_VALUE);
        Iterator<rsb> it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            rsb next = it.next();
            int i2 = i + 1;
            if (!a2.equals(next.a())) {
                i = i2 + a(next);
            } else {
                i = i2 + b(next.e(), a3, j);
                break;
            }
        }
        if (this.b != null) {
            int j2 = (int) (((nsn.j() * 0.5f) - this.mSpinnerCustomTitleBar.getHeight()) - this.f10430a.e());
            LogUtil.a(e, "scrollToPosition offset = " + j2);
            this.h.scrollToPositionWithOffset(i, j2);
        }
        this.mPageModelArgs.putLong("Time", Long.MIN_VALUE);
    }

    private int a(rsb rsbVar) {
        if (rsbVar == null) {
            return 0;
        }
        List<rsg> e2 = rsbVar.e();
        if (koq.b(e2)) {
            return 0;
        }
        Iterator<rsg> it = e2.iterator();
        int i = 0;
        while (it.hasNext()) {
            List<PrivacyDataModel> d = it.next().d();
            i = i + 1 + (d == null ? 0 : d.size());
        }
        return i;
    }

    private int b(List<rsg> list, String str, long j) {
        if (koq.b(list)) {
            return 0;
        }
        int i = 0;
        for (rsg rsgVar : list) {
            i++;
            List<PrivacyDataModel> d = rsgVar.d();
            if (!str.equals(rsgVar.e())) {
                List<PrivacyDataModel> d2 = rsgVar.d();
                i += d2 == null ? 0 : d2.size();
            } else {
                Iterator<PrivacyDataModel> it = d.iterator();
                while (it.hasNext()) {
                    if (it.next().getStartTime() == j) {
                        return i;
                    }
                    i++;
                }
            }
        }
        return i;
    }

    private void dQJ_(View view) {
        this.f10430a.a();
    }

    public static void dQI_(CategoryDoubleGroupLayout categoryDoubleGroupLayout, View view) {
        categoryDoubleGroupLayout.dQJ_(view);
        ViewClickInstrumentation.clickOnView(view);
    }
}
