package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.wifi.interfaces.ScanDeviceCallback;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cpp;
import defpackage.ctn;
import defpackage.ctv;
import defpackage.cuh;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class WiFiDeviceScanFragment extends BaseFragment {
    private static final int MSG_REFRESH_DATA = 1;
    private static final int MSG_SCAN_DEVICE_FINISH = 3;
    private static final int MSG_START_SCAN_DEVICE = 2;
    private static final int SCAN_COUNT = 10;
    private static final int SCAN_DELAY_TIME = 3000;
    private static final String TAG = "WiFiDeviceScanFragment";
    private ProductListAdapter mAdapter;
    private int mConfigMode;
    private ContentValues mDeviceInfo;
    private MyHandler mHandler;
    private ListView mListView;
    private String mProductId;
    private dcz mProductInfo;
    private HealthProgressBar mProgressBar;
    private ScanCallback mScanCallback;
    private cuh mScanManager;
    private HealthTextView mScanTipView;
    private LinearLayout mSearchWattingLayout;
    private String mWiFiSsid;
    private String mWifiPwd;
    private ArrayList<ctn> mDevices = new ArrayList<>(16);
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiDeviceScanFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i >= 0 && i < WiFiDeviceScanFragment.this.mDevices.size()) {
                ctn ctnVar = (ctn) WiFiDeviceScanFragment.this.mDevices.get(i);
                ctnVar.d(ctv.b(ctnVar.b()));
                Bundle bundle = new Bundle();
                bundle.putString("productId", WiFiDeviceScanFragment.this.mProductId);
                bundle.putString("outhName", WiFiDeviceScanFragment.this.mWiFiSsid);
                bundle.putString("outhPd", WiFiDeviceScanFragment.this.mWifiPwd);
                bundle.putSerializable("add_device_info", ctnVar);
                bundle.putInt("config_mode", WiFiDeviceScanFragment.this.mConfigMode);
                bundle.putParcelable("commonDeviceInfo", WiFiDeviceScanFragment.this.mDeviceInfo);
                WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment = new WiFiDeviceBindResultFragment();
                wiFiDeviceBindResultFragment.setArguments(bundle);
                WiFiDeviceScanFragment.this.switchFragment(wiFiDeviceBindResultFragment);
            }
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    };

    static class MyHandler extends StaticHandler<WiFiDeviceScanFragment> {
        MyHandler(WiFiDeviceScanFragment wiFiDeviceScanFragment) {
            super(wiFiDeviceScanFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiDeviceScanFragment wiFiDeviceScanFragment, Message message) {
            if (wiFiDeviceScanFragment.isDestroy()) {
                return;
            }
            LogUtil.a(WiFiDeviceScanFragment.TAG, "MyHandler what:", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                if (message.obj == null || !(message.obj instanceof ArrayList)) {
                    return;
                }
                ArrayList arrayList = (ArrayList) message.obj;
                if (arrayList.size() > 0) {
                    wiFiDeviceScanFragment.updataListView(arrayList);
                    return;
                }
                return;
            }
            if (i == 2) {
                wiFiDeviceScanFragment.startScan();
                return;
            }
            if (i == 3) {
                wiFiDeviceScanFragment.initAnim(false);
                if (wiFiDeviceScanFragment.mDevices.size() <= 0) {
                    wiFiDeviceScanFragment.switchFragment(new WiFiDeviceScanFailFragment());
                    return;
                }
                return;
            }
            LogUtil.h(WiFiDeviceScanFragment.TAG, "MyHandler what is other");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updataListView(ArrayList<ctn> arrayList) {
        LogUtil.a(TAG, "updataListView ", Integer.valueOf(arrayList.size()));
        if (this.mAdapter != null) {
            this.mDevices.clear();
            this.mDevices.addAll(arrayList);
            this.mAdapter.refreshData(this.mDevices);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.b(TAG, "DeviceMainActivity is Destroyed");
            return true;
        }
        if (isAdded()) {
            return false;
        }
        LogUtil.b(TAG, "MyHandler mFragment is not add");
        return true;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.child = layoutInflater.inflate(R.layout.wifi_device_search_layout, viewGroup, false);
        initData();
        initView();
        if (onCreateView instanceof ViewGroup) {
            ((ViewGroup) onCreateView).addView(this.child);
        }
        return onCreateView;
    }

    private void initData() {
        this.mProductId = getArguments().getString("productId");
        this.mWiFiSsid = getArguments().getString("outhName");
        this.mWifiPwd = getArguments().getString("outhPd");
        this.mConfigMode = getArguments().getInt("config_mode", 1);
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null && TextUtils.isEmpty(this.mProductId)) {
            this.mProductId = this.mDeviceInfo.getAsString("productId");
        }
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.a(TAG, "initData mProductId is null");
            super.onBackPressed();
            return;
        }
        LogUtil.a(TAG, "initData  ", this.mProductId);
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        this.mScanManager = cuh.e(this.mainActivity);
        this.mScanCallback = new ScanCallback(this);
        this.mHandler = new MyHandler(this);
        setTitle(this.mainActivity.getString(R.string.IDS_device_wifi_scan_title));
    }

    private void initView() {
        this.mProgressBar = (HealthProgressBar) nsy.cMd_(this.child, R.id.wifi_device_scanning_img);
        this.mListView = (ListView) nsy.cMd_(this.child, R.id.wifi_device_content_listview);
        this.mScanTipView = (HealthTextView) nsy.cMd_(this.child, R.id.wifi_device_measure_search_prompt);
        this.mSearchWattingLayout = (LinearLayout) nsy.cMd_(this.child, R.id.wifi_device_search_layout);
        this.mScanTipView.setText(R.string.IDS_device_measureactivity_device_searching);
        this.mScanTipView.setVisibility(0);
        ProductListAdapter productListAdapter = new ProductListAdapter(this.mDevices);
        this.mAdapter = productListAdapter;
        this.mListView.setAdapter((ListAdapter) productListAdapter);
        this.mListView.setOnItemClickListener(this.mOnItemClickListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a(TAG, "onStart");
        super.onStart();
        this.mDevices.clear();
        ProductListAdapter productListAdapter = this.mAdapter;
        if (productListAdapter != null) {
            productListAdapter.notifyDataSetChanged();
        }
        initAnim(true);
        Object systemService = this.mainActivity.getSystemService("wifi");
        if (systemService instanceof WifiManager) {
            ((WifiManager) systemService).startScan();
        }
        this.mHandler.sendEmptyMessageDelayed(2, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAnim(boolean z) {
        if (!z) {
            HealthProgressBar healthProgressBar = this.mProgressBar;
            if (healthProgressBar != null) {
                healthProgressBar.setVisibility(8);
            }
            this.mSearchWattingLayout.setVisibility(8);
        } else {
            HealthProgressBar healthProgressBar2 = this.mProgressBar;
            if (healthProgressBar2 != null) {
                healthProgressBar2.setVisibility(0);
            }
            this.mSearchWattingLayout.setVisibility(0);
        }
        LogUtil.a(TAG, "initAnim isStart:", Boolean.valueOf(z));
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        stopScan();
        popupFragment(WiFiProductIntroductionFragment.class);
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        LogUtil.a(TAG, "onStop");
        super.onStop();
        stopScan();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        super.onDestroy();
    }

    private void stopScan() {
        initAnim(false);
        this.mScanManager.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScan() {
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            this.mScanManager.d(dczVar, 10, this.mScanCallback);
        } else {
            LogUtil.b(TAG, "startScan Get device infomation is fail");
        }
    }

    static class ScanCallback extends ScanDeviceCallback<WiFiDeviceScanFragment> {
        @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallback
        public /* bridge */ /* synthetic */ void onDeviceDiscovered(WiFiDeviceScanFragment wiFiDeviceScanFragment, List list) {
            onDeviceDiscovered2(wiFiDeviceScanFragment, (List<ctn>) list);
        }

        ScanCallback(WiFiDeviceScanFragment wiFiDeviceScanFragment) {
            super(wiFiDeviceScanFragment);
        }

        /* renamed from: onDeviceDiscovered, reason: avoid collision after fix types in other method */
        public void onDeviceDiscovered2(WiFiDeviceScanFragment wiFiDeviceScanFragment, List<ctn> list) {
            if (list == null) {
                LogUtil.b(WiFiDeviceScanFragment.TAG, "onDeviceDiscovered deviceInfo is null");
                return;
            }
            LogUtil.a(WiFiDeviceScanFragment.TAG, "onDeviceDiscovered deviceInfo ", Integer.valueOf(list.size()));
            ArrayList removeCoapRepeatDevice = wiFiDeviceScanFragment.removeCoapRepeatDevice(wiFiDeviceScanFragment.removeRepeatDevice(list));
            LogUtil.a(WiFiDeviceScanFragment.TAG, "onDeviceDiscovered mDevices ", Integer.valueOf(removeCoapRepeatDevice.size()));
            if (wiFiDeviceScanFragment.mHandler != null) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = removeCoapRepeatDevice;
                wiFiDeviceScanFragment.mHandler.sendMessage(obtain);
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallback
        public void onDeviceDiscoveryFinished(WiFiDeviceScanFragment wiFiDeviceScanFragment) {
            if (wiFiDeviceScanFragment.mHandler != null) {
                wiFiDeviceScanFragment.mHandler.sendEmptyMessage(3);
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.ScanDeviceCallback
        public void onFailure(WiFiDeviceScanFragment wiFiDeviceScanFragment, Object obj) {
            if (wiFiDeviceScanFragment.mHandler != null) {
                wiFiDeviceScanFragment.mHandler.sendEmptyMessage(3);
            }
        }
    }

    public ArrayList<ctn> removeRepeatDevice(List<ctn> list) {
        boolean z;
        ArrayList<ctn> arrayList = new ArrayList<>(16);
        for (ctn ctnVar : list) {
            String f = ctnVar.f();
            String h = ctnVar.h();
            String i = ctnVar.i();
            if (TextUtils.isEmpty(f) || TextUtils.isEmpty(h) || TextUtils.isEmpty(i)) {
                LogUtil.a(TAG, "removeRepeatDevice ssid or productId or type is null");
            } else {
                LogUtil.a(TAG, "removeRepeatDevice ssid: ", f, ", productId: ", h, ", type: ", i);
                if (arrayList.size() > 0) {
                    Iterator<ctn> it = arrayList.iterator();
                    while (it.hasNext()) {
                        ctn next = it.next();
                        if (next != null && f.equals(next.f()) && next.i().equals(i)) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                if (!z && !z && isNeedScanMode(ctnVar)) {
                    arrayList.add(ctnVar);
                }
            }
        }
        LogUtil.a(TAG, "removeRepeatDevice all add info is:", arrayList);
        return arrayList;
    }

    private boolean isNeedScanMode(ctn ctnVar) {
        int i = this.mConfigMode;
        if (i == 2) {
            return "softap".equals(ctnVar.i());
        }
        if (i == 1) {
            return "coap".equals(ctnVar.i()) || "wifiap".equals(ctnVar.i());
        }
        LogUtil.a(TAG, "isNeedScanMode default");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ctn> removeCoapRepeatDevice(ArrayList<ctn> arrayList) {
        ArrayList<ctn> arrayList2 = new ArrayList(16);
        ArrayList<ctn> arrayList3 = new ArrayList(16);
        Iterator<ctn> it = arrayList.iterator();
        while (it.hasNext()) {
            ctn next = it.next();
            LogUtil.a(TAG, "info.getSourceType() = ", next.i());
            if ("coap".equals(next.i())) {
                arrayList2.add(next);
            }
            if ("wifiap".equals(next.i())) {
                arrayList3.add(next);
            }
        }
        LogUtil.a(TAG, "removeRepeatDevice, coap devices size:", Integer.valueOf(arrayList2.size()));
        if (!arrayList3.isEmpty() && !arrayList2.isEmpty()) {
            for (ctn ctnVar : arrayList2) {
                if (ctnVar.g() != null) {
                    for (ctn ctnVar2 : arrayList3) {
                        if (ctnVar2.g() != null && ctnVar2.c() != null && ctnVar2.g().equals(ctnVar.g()) && ctnVar2.c().equals(ctnVar.c())) {
                            arrayList.remove(ctnVar2);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    class ProductListAdapter extends BaseAdapter {
        private ArrayList<ctn> mProductList;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        ProductListAdapter(ArrayList<ctn> arrayList) {
            this.mProductList = arrayList;
        }

        public void refreshData(ArrayList<ctn> arrayList) {
            this.mProductList = arrayList;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mProductList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            ArrayList<ctn> arrayList = this.mProductList;
            if (arrayList == null || i >= arrayList.size()) {
                return null;
            }
            return this.mProductList.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            dcz dczVar;
            ViewHolder viewHolder = null;
            Object[] objArr = 0;
            if (i < 0 || i >= this.mProductList.size()) {
                dczVar = null;
            } else {
                dczVar = ResourceManager.e().d(this.mProductList.get(i).h());
            }
            if (view == null) {
                ViewHolder viewHolder2 = new ViewHolder();
                View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.wifi_device_scan_item_layout, (ViewGroup) null);
                viewHolder2.mDeviceNameTv = (HealthTextView) nsy.cMd_(inflate, R.id.wifi_device_content);
                viewHolder2.mIconView = (ImageView) nsy.cMd_(inflate, R.id.wifi_device_icon);
                inflate.setTag(viewHolder2);
                viewHolder = viewHolder2;
                view = inflate;
            } else if (view.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (viewHolder == null) {
                LogUtil.h(WiFiDeviceScanFragment.TAG, "view is null");
                return view;
            }
            if (dczVar != null) {
                LogUtil.a(WiFiDeviceScanFragment.TAG, "ProductListAdapter productInfo is not null");
                viewHolder.mDeviceNameTv.setText(dcx.d(WiFiDeviceScanFragment.this.mProductId, WiFiDeviceScanFragment.this.mProductInfo.n().b()));
                viewHolder.mIconView.setImageBitmap(dcx.TK_(dcq.b().a(WiFiDeviceScanFragment.this.mProductId, WiFiDeviceScanFragment.this.mProductInfo.n().d())));
            } else {
                LogUtil.a(WiFiDeviceScanFragment.TAG, "ProductListAdapter productInfo is null");
            }
            return view;
        }

        class ViewHolder {
            HealthTextView mDeviceNameTv;
            ImageView mIconView;

            private ViewHolder() {
            }
        }
    }
}
