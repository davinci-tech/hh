package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.api.DeviceMainActivityApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.constants.WeightConstants;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.adapter.ProductListAdapter;
import com.huawei.health.ecologydevice.ui.measure.adapter.WeightListAdapter;
import com.huawei.health.ecologydevice.ui.measure.fragment.ListFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.cjv;
import defpackage.cos;
import defpackage.cpp;
import defpackage.dcr;
import defpackage.dcu;
import defpackage.dcz;
import defpackage.dks;
import defpackage.ixx;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class DeviceSelectBindFragment extends ListFragment {
    private static final String AM16_PRODUCTID = "6d5416d9-2167-41df-ab10-c492e152b44f";
    private static final String AUTO_TEST_PRODUCT_ID = "aa:bb:cc:dd";
    private static final String HONOR_DEVICE = "honor";
    private static final String HUAWEI_DEVICE = "huawei";
    private static final String METIS_PRODUCTID = "9323f6b7-b459-44f4-a698-988d1769832a";
    private static final String TAG = "DeviceSelectBindFragment";
    private static final int TO_FAILED = 2;
    private static final int TO_REFRESH = 1;
    private HealthDevice.HealthDeviceKind mKind;
    private ProductListAdapter mProductListAdapter;
    private WeightListAdapter mWeightListAdapter;
    protected ArrayList<dcz> mProductInfos = new ArrayList<>(10);
    private ArrayList<dcz> mHuawei = new ArrayList<>(10);
    private ArrayList<dcz> mHonor = new ArrayList<>(10);
    private ArrayList<dcz> mOther = new ArrayList<>(10);
    private boolean mIsDownDevice = false;
    private ArrayList<String> mToDownloadList = new ArrayList<>(10);
    private boolean isBackToFinish = false;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            DeviceSelectBindFragment deviceSelectBindFragment = DeviceSelectBindFragment.this;
            deviceSelectBindFragment.handleClickEvent(i, deviceSelectBindFragment.mProductListAdapter);
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    };
    private Runnable mAdapterData = new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment.2
        @Override // java.lang.Runnable
        public void run() {
            DeviceSelectBindFragment deviceSelectBindFragment = DeviceSelectBindFragment.this;
            deviceSelectBindFragment.initAdapterData(deviceSelectBindFragment.mKind);
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h(DeviceSelectBindFragment.TAG, "DeviceSelectBindFragment mHandler handleMessage:msg == null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                DeviceSelectBindFragment.this.refreshData(message);
            } else {
                if (i != 2) {
                    return;
                }
                LogUtil.a(DeviceSelectBindFragment.TAG, "DeviceSelectBindFragment FAILED");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshData(Message message) {
        LogUtil.a(TAG, "DeviceSelectBindFragment REFRESH");
        dcz dczVar = message.obj instanceof dcz ? (dcz) message.obj : null;
        if (dczVar == null || !this.mKind.equals(dczVar.l())) {
            return;
        }
        if (METIS_PRODUCTID.equals(dczVar.t()) || AM16_PRODUCTID.equals(dczVar.t()) || !((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isShowWiFiDevice(dczVar.t())) {
            LogUtil.a(TAG, "DeviceSelectBindFragment productInfo = ", dczVar);
            return;
        }
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
            String a2 = dczVar.n().a();
            LogUtil.a(TAG, "DeviceSelectBindFragment company = ", a2);
            sort(this.mProductInfos, dczVar, a2);
            try {
                this.mWeightListAdapter.notifyDataSetChanged();
                return;
            } catch (IllegalStateException e) {
                LogUtil.h(TAG, "DeviceSelectBindFragment mHandler TO_REFRESH HDK_WEIGHT isMain = ", Boolean.valueOf(Looper.myLooper() == Looper.getMainLooper()), ", e:", e.getMessage());
                return;
            }
        }
        this.mProductListAdapter.c(dczVar);
        try {
            this.mProductListAdapter.notifyDataSetChanged();
        } catch (IllegalStateException e2) {
            LogUtil.h(TAG, "DeviceSelectBindFragment mHandler TO_REFRESH HDK_WEIGHT isMain = ", Boolean.valueOf(Looper.myLooper() == Looper.getMainLooper()), ", e:", e2.getMessage());
        }
    }

    private void sort(ArrayList<dcz> arrayList, dcz dczVar, String str) {
        if (getActivity() == null) {
            LogUtil.h(TAG, "sort getActivity() == null");
        } else if (arrayList.isEmpty()) {
            addHeaderItem(arrayList, dczVar, str);
        } else {
            productInfosSort(arrayList);
            queryWhetherRepeation(arrayList, dczVar, str);
        }
    }

    private void queryWhetherRepeation(ArrayList<dcz> arrayList, dcz dczVar, String str) {
        int query = query(arrayList, dczVar);
        if (HUAWEI_DEVICE.equalsIgnoreCase(str)) {
            if (this.mHuawei.size() <= 0) {
                dcz dczVar2 = new dcz(1);
                dczVar2.f(getActivity().getString(R.string.IDS_device_huawei_band));
                dczVar2.d(true);
                arrayList.add(0, dczVar2);
                dczVar.e(false);
            }
            if (query == -1) {
                arrayList.add(1, dczVar);
                return;
            } else {
                arrayList.set(query, dczVar);
                return;
            }
        }
        if (HONOR_DEVICE.equalsIgnoreCase(str)) {
            if (this.mHonor.size() <= 0) {
                dcz dczVar3 = new dcz(1);
                dczVar3.f(getActivity().getString(R.string.IDS_device_honor_band));
                dczVar3.d(false);
                if (this.mHuawei.size() <= 0) {
                    arrayList.add(this.mHuawei.size(), dczVar3);
                } else {
                    arrayList.add(this.mHuawei.size() + 1, dczVar3);
                }
                dczVar.e(false);
            }
            if (query == -1) {
                if (this.mHuawei.size() <= 0) {
                    arrayList.add(this.mHuawei.size() + 1, dczVar);
                    return;
                } else {
                    arrayList.add(this.mHuawei.size() + 2, dczVar);
                    return;
                }
            }
            arrayList.set(query, dczVar);
            return;
        }
        if (this.mOther.size() <= 0) {
            dcz dczVar4 = new dcz(1);
            dczVar4.f(getActivity().getString(R.string._2130841847_res_0x7f0210f7));
            dczVar4.d(false);
            arrayList.add(arrayList.size(), dczVar4);
        }
        if (query == -1) {
            arrayList.add(arrayList.size(), dczVar);
        } else {
            arrayList.set(query, dczVar);
        }
    }

    private void addHeaderItem(ArrayList<dcz> arrayList, dcz dczVar, String str) {
        dcz dczVar2 = new dcz(1);
        if (HUAWEI_DEVICE.equalsIgnoreCase(str)) {
            dczVar2.f(getActivity().getString(R.string.IDS_device_huawei_band));
            dczVar2.d(true);
            dczVar.e(false);
        } else if (HONOR_DEVICE.equalsIgnoreCase(str)) {
            dczVar2.f(getActivity().getString(R.string.IDS_device_honor_band));
            dczVar2.d(false);
            dczVar.e(false);
        } else {
            dczVar2.f(getActivity().getString(R.string._2130841847_res_0x7f0210f7));
            dczVar2.d(false);
        }
        arrayList.add(dczVar2);
        arrayList.add(dczVar);
    }

    private void productInfosSort(ArrayList<dcz> arrayList) {
        this.mHuawei.clear();
        this.mHonor.clear();
        this.mOther.clear();
        Iterator<dcz> it = arrayList.iterator();
        while (it.hasNext()) {
            dcz next = it.next();
            dcz.b n = next.n();
            if (n != null) {
                String a2 = n.a();
                if (a2 != null) {
                    if (a2.equalsIgnoreCase(HUAWEI_DEVICE)) {
                        this.mHuawei.add(next);
                    } else if (a2.equalsIgnoreCase(HONOR_DEVICE)) {
                        this.mHonor.add(next);
                    } else {
                        LogUtil.a(TAG, "DeviceSelectBindFragment productInfosSort");
                    }
                } else {
                    this.mOther.add(next);
                }
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ListFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ListFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "DeviceSelectBindFragment onCreateView");
        ResourceManager.e().e(new MyResourceFileListener(this));
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        init();
        return onCreateView;
    }

    private void init() {
        LogUtil.a(TAG, "DevcieSelectBindFragment-init");
        String string = getArguments().getString("kind");
        this.isBackToFinish = getArguments().getBoolean(DeviceCategoryFragment.WIFI_UPDATE_BACK_FINISH, false);
        this.mKind = dks.c(string);
        LogUtil.a(TAG, "DevcieSelectBindFragment-------" + string);
        showMoreButton(false, null);
        this.mProductInfos.clear();
        this.myDevicesListview = (ListView) this.child.findViewById(R.id.device_list_view);
        initView(this.mKind);
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
            this.mWeightListAdapter = new WeightListAdapter(this.mainActivity, this.mProductInfos);
            this.myDevicesListview.setAdapter((ListAdapter) this.mWeightListAdapter);
            this.myDevicesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment$$ExternalSyntheticLambda0
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    DeviceSelectBindFragment.this.m374x718c388e(adapterView, view, i, j);
                }
            });
        } else {
            this.mProductListAdapter = new ProductListAdapter(this.mProductInfos);
            this.myDevicesListview.setAdapter((ListAdapter) this.mProductListAdapter);
            this.myDevicesListview.setOnItemClickListener(this.mOnItemClickListener);
        }
        String string2 = getArguments().getString("deviceType");
        if (string2 != null) {
            super.setTitle(string2);
        }
        super.showButton(false, null);
    }

    /* renamed from: lambda$init$0$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceSelectBindFragment, reason: not valid java name */
    /* synthetic */ void m374x718c388e(AdapterView adapterView, View view, int i, long j) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_SELECT_DEVICE_2060002.value(), hashMap, 0);
        dcz item = this.mWeightListAdapter.getItem(i);
        if (item != null && item.n() != null) {
            switchProductIntroductionFragment(item);
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAdapterData(HealthDevice.HealthDeviceKind healthDeviceKind) {
        dcr c = ResourceManager.e().a().c(healthDeviceKind);
        this.mToDownloadList.clear();
        if (c != null) {
            LogUtil.a(TAG, "home size=", Integer.valueOf(c.d().size()), " more size=" + c.b().size());
            Iterator<dcu> it = c.d().iterator();
            while (it.hasNext()) {
                dcu next = it.next();
                if (ResourceManager.e().g(cos.e + next.e()) && ResourceManager.e().a(next.e())) {
                    dcz d = ResourceManager.e().d(next.e());
                    if (d != null) {
                        Message obtain = Message.obtain();
                        obtain.what = 1;
                        obtain.obj = d;
                        this.mHandler.sendMessage(obtain);
                    }
                } else {
                    this.mToDownloadList.add(next.d());
                    LogUtil.h(TAG, "DeviceSelectBindFragment file is not Exists ", next.d());
                }
            }
        } else {
            LogUtil.h(TAG, "the productGroup is null");
        }
        if (!ResourceManager.e().b() && !ResourceManager.e().d()) {
            LogUtil.a(TAG, "DeviceSelectBindFragment toDownloadList ", Integer.valueOf(this.mToDownloadList.size()));
            if (this.mToDownloadList.size() <= 0 || this.mIsDownDevice) {
                return;
            }
            ResourceManager.e().c(this.mToDownloadList);
            return;
        }
        ResourceManager.e().i();
    }

    public void initView(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a(TAG, "DeviceSelectBindFragment initView");
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
            showMoreButton(true, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HashMap hashMap = new HashMap(16);
                    hashMap.put("click", "1");
                    ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_MORE_DEVICE_SEARCH_2060017.value(), hashMap, 0);
                    if (BluetoothAdapter.getDefaultAdapter().getState() == 12) {
                        DeviceSelectBindFragment.this.switchDeviceScanningFragment();
                    } else {
                        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(DeviceSelectBindFragment.this.getActivity());
                        builder.e(R.string.IDS_device_bluetooth_open_request);
                        builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment.4.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                DeviceSelectBindFragment.this.switchDeviceScanningFragment();
                                ViewClickInstrumentation.clickOnView(view2);
                            }
                        });
                        builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSelectBindFragment.4.2
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                ViewClickInstrumentation.clickOnView(view2);
                            }
                        });
                        NoTitleCustomAlertDialog e = builder.e();
                        e.setCancelable(false);
                        e.show();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        this.mIsDownDevice = false;
        cpp.a(this.mAdapterData);
        LogUtil.a(TAG, "DeviceSelectBindFragment initview finished");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchDeviceScanningFragment() {
        setTitle(getResources().getString(R.string.IDS_device_search_title));
        Bundle bundle = new Bundle();
        bundle.putString("productId", AUTO_TEST_PRODUCT_ID);
        bundle.putString("scan_kind", HealthDevice.HealthDeviceKind.HDK_HEART_RATE.name());
        bundle.putString("title", getResources().getString(R.string.IDS_device_search_title));
        Fragment deviceScanningFragment = new DeviceScanningFragment();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", "");
        contentValues.put("productId", AUTO_TEST_PRODUCT_ID);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        deviceScanningFragment.setArguments(bundle);
        switchFragment(deviceScanningFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ResourceManager.e().f();
    }

    static class MyResourceFileListener implements ResourceFileListener {
        WeakReference<DeviceSelectBindFragment> weakReference;

        MyResourceFileListener(DeviceSelectBindFragment deviceSelectBindFragment) {
            this.weakReference = new WeakReference<>(deviceSelectBindFragment);
        }

        @Override // com.huawei.health.device.manager.ResourceFileListener
        public void onResult(int i, String str) {
            DeviceSelectBindFragment deviceSelectBindFragment = this.weakReference.get();
            if (deviceSelectBindFragment != null) {
                if (i == 200) {
                    if (MessageConstant.GROUP_MEDAL_TYPE.equals(str)) {
                        deviceSelectBindFragment.mIsDownDevice = true;
                        cpp.a(deviceSelectBindFragment.mAdapterData);
                        return;
                    } else {
                        dcz d = ResourceManager.e().d(str);
                        Message obtain = Message.obtain();
                        obtain.what = 1;
                        obtain.obj = d;
                        deviceSelectBindFragment.mHandler.sendMessage(obtain);
                    }
                }
                if (i == -1) {
                    Message obtain2 = Message.obtain();
                    obtain2.what = 2;
                    obtain2.obj = str;
                    deviceSelectBindFragment.mHandler.sendMessage(obtain2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClickEvent(int i, ProductListAdapter productListAdapter) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_SELECT_DEVICE_2060002.value(), hashMap, 0);
        cjv cjvVar = productListAdapter.getItem(i) instanceof cjv ? (cjv) productListAdapter.getItem(i) : null;
        if (cjvVar == null || cjvVar.a() != 0) {
            return;
        }
        dcz dczVar = cjvVar.i() instanceof dcz ? (dcz) cjvVar.i() : null;
        if (dczVar != null) {
            switchProductIntroductionFragment(dczVar);
        }
    }

    private void switchProductIntroductionFragment(dcz dczVar) {
        Fragment productIntroductionFragment = new ProductIntroductionFragment();
        if (((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isWiFiDevice(dczVar.t())) {
            productIntroductionFragment = ((DeviceMainActivityApi) Services.c("PluginDevice", DeviceMainActivityApi.class)).getPluginDeviceFragment(WeightConstants.FragmentType.WIFI_PRODUCT_INTRODUCTION);
        }
        Bundle bundle = new Bundle();
        bundle.putString("productId", dczVar.t());
        productIntroductionFragment.setArguments(bundle);
        switchFragment(productIntroductionFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (this.isBackToFinish) {
            this.mainActivity.finish();
            return false;
        }
        return super.onBackPressed();
    }
}
