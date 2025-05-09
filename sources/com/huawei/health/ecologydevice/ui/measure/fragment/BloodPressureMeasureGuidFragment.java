package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.device.open.data.MeasureResult;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import defpackage.cpp;
import defpackage.czu;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dhd;
import defpackage.dij;
import defpackage.dks;
import defpackage.ixx;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class BloodPressureMeasureGuidFragment extends BluetoothMeasureFragment {
    private static final String TAG = "BloodPressureMeasureGuidFragment";
    private dcz mProductInfo;

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.b(TAG, "onCreate getArguments is empty");
            return;
        }
        this.mDeviceInfo = (ContentValues) arguments.getParcelable("commonDeviceInfo");
        if (this.mDeviceInfo != null) {
            this.mProductId = this.mDeviceInfo.getAsString("productId");
            this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
        } else {
            LogUtil.h(TAG, "initData mDeviceInfo is empty");
        }
        if (BloodPressureStartFromDeviceHelper.b(dij.e(this.mProductId)) || BloodPressureStartFromDeviceHelper.c()) {
            jumpToMeasureActivity(this.mProductId, this.mUniqueId);
        }
    }

    private void jumpToMeasureActivity(String str, String str2) {
        Bundle bundle = new Bundle();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("productId", str);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putBoolean("isBindSuccess", true);
        Fragment productIntroductionFragment = new ProductIntroductionFragment();
        productIntroductionFragment.setArguments(bundle);
        switchFragment((Fragment) null, productIntroductionFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public dhd getMode() {
        LogUtil.a(TAG, "BloodPressureMeasureGuidFragment getmode()");
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        dhd dhdVar = new dhd();
        dhdVar.e(getMeasure());
        dhdVar.b(getMeasureListForString());
        dcz dczVar = this.mProductInfo;
        ArrayList<dcz.d> q = dczVar != null ? dczVar.q() : null;
        if (q != null) {
            dhdVar.c(dcx.d(this.mProductId, q.get(0).c()));
        }
        dhdVar.b(GravityCompat.START);
        dhdVar.e(true);
        dhdVar.a(true);
        dcz dczVar2 = this.mProductInfo;
        super.setTitle(dcx.d(this.mProductId, dczVar2 != null ? dczVar2.n().b() : null));
        return dhdVar;
    }

    private ArrayList<Object> getMeasure() {
        LogUtil.a(TAG, "BloodPressureMeasureGuidFragment getMeasure()");
        ArrayList<Object> arrayList = new ArrayList<>(10);
        dcz dczVar = this.mProductInfo;
        ArrayList<dcz.d> q = dczVar != null ? dczVar.q() : null;
        if (q == null) {
            return arrayList;
        }
        Iterator<dcz.d> it = q.iterator();
        while (it.hasNext()) {
            arrayList.add(dcq.b().a(this.mProductId, it.next().e()));
        }
        LogUtil.a(TAG, "------" + arrayList.size());
        return arrayList;
    }

    private ArrayList<String> getMeasureListForString() {
        LogUtil.a(TAG, "BloodPressureMeasureGuidFragment getMeasureListForString()");
        ArrayList<String> arrayList = new ArrayList<>(10);
        dcz dczVar = this.mProductInfo;
        ArrayList<dcz.d> q = dczVar != null ? dczVar.q() : null;
        if (q == null) {
            return arrayList;
        }
        Iterator<dcz.d> it = q.iterator();
        while (it.hasNext()) {
            arrayList.add(dcx.d(this.mProductId, it.next().c()));
        }
        LogUtil.a(TAG, "imageListForString.size() = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public void clickMeasure() {
        LogUtil.a(TAG, "BloodPressureMeasureGuidFragment clickMeasure(), mProductId = ", this.mProductId, ", mKind = ", this.mKind);
        HashMap hashMap = new HashMap(16);
        dcz d = ResourceManager.e().d(this.mProductId);
        czu.e().a(dij.e(this.mProductId));
        if (d != null) {
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(this.mProductId, d.n().b()));
        }
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BEGIN_MEASURE_2060009.value(), hashMap, 0);
        Bundle bundle = new Bundle();
        bundle.putString("kind", this.mKind);
        bundle.putString("productId", this.mProductId);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putString("title", dcx.d(this.mProductId, this.mProductInfo.n().b()));
        Fragment bloodPressureMeasuringProgressFragment = new BloodPressureMeasuringProgressFragment();
        bloodPressureMeasuringProgressFragment.setArguments(bundle);
        BloodPressureStartFromDeviceHelper.e(d);
        switchFragment(bloodPressureMeasuringProgressFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureMeasureGuidFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasureGuidFragment.this.m284x83902909(view);
            }
        };
    }

    /* renamed from: lambda$getOnClickListener$0$com-huawei-health-ecologydevice-ui-measure-fragment-BloodPressureMeasureGuidFragment, reason: not valid java name */
    /* synthetic */ void m284x83902909(View view) {
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else if (view == null) {
            LogUtil.a(TAG, "onClick: view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (R.id.bt_device_measure_guide_next == view.getId()) {
                clickMeasure();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        nsn.a();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleDataChangedInUiThread(HealthDevice healthDevice, HealthData healthData, boolean z) {
        LogUtil.a(TAG, "handleDataChangedInUiThread: data = " + healthData);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleDataChangedInUiThreadUniversal(MeasureResult measureResult, boolean z) {
        LogUtil.a(TAG, "handleDataChangedInUiThreadUniversal: data = " + measureResult);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleStatusChangedInUiThread(HealthDevice healthDevice, int i) {
        LogUtil.a(TAG, "handleStatusChangedInUiThread: status = " + i);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleStatusChangedInUiThreadUniversal(int i) {
        LogUtil.a(TAG, "handleStatusChangedInUiThreadUniversal: status = " + i);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleFailedEventInUiThread(int i) {
        LogUtil.a(TAG, "handleFailedEventInUiThread: code = " + i);
    }
}
