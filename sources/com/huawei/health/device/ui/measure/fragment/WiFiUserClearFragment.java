package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.kit.hwwsp.hagrid.bean.MiniUserDataInfo;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.adapter.UserClearAdapter;
import com.huawei.health.device.wifi.interfaces.SelectClearUserInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import defpackage.cfi;
import defpackage.cgz;
import defpackage.cjx;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.crz;
import defpackage.csf;
import defpackage.ctk;
import defpackage.cts;
import defpackage.ctv;
import defpackage.jbs;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsf;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class WiFiUserClearFragment extends BaseFragment implements SelectClearUserInterface, HealthToolBar.OnSingleTapListener {
    private static final int CLEAR_USER_DATA_CHANGE_UI_SETVIEW = 1;
    private static final int CLEAR_USER_DATA_FAIL = 3;
    private static final int CLEAR_USER_DATA_SUCCESS = 0;
    private static final int CLEAR_USER_DATA_SUCCESS_FROM_WIFI = 2;
    private static final String DEVICE_MAIN_ID = "0";
    private static final int INSTEAD_FALSE = -1;
    private static final int MAX_USER_DATA = 10;
    private static final String TAG = "WiFiUserClearFragment";
    private static final int UID_MAX_LEN = 32;
    private static List<crz> mUserList;
    private static UserClearAdapter sAdapter;
    private int mChooseCount;
    private Context mContext;
    private String mDeviceId;
    private HealthToolBar mHealthToolBar;
    private MyHandler mMyHandler;
    private String mProductId;
    private HealthRecycleView mRecyclerView;
    private int mNoSelect = -1;
    private ArrayList<MiniUserDataInfo> mUserDataInfoList = new ArrayList<>(10);
    private EventBus.ICallback mEventCallback = new EventBus.ICallback() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiUserClearFragment.1
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            String e = bVar.e();
            Bundle Kl_ = bVar.Kl_();
            if (Kl_ == null || !"delete_user_data_result".equals(e)) {
                return;
            }
            if (Kl_.getInt("ret") == 0) {
                WiFiUserClearFragment.this.mMyHandler.sendEmptyMessage(0);
            } else {
                WiFiUserClearFragment.this.mMyHandler.obtainMessage(3).sendToTarget();
                LogUtil.b(WiFiUserClearFragment.TAG, "mEventCallback delete is fail");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteLocalData() {
        Iterator<crz> it = sAdapter.d().iterator();
        while (it.hasNext()) {
            crz next = it.next();
            cfi d = next.d();
            if (d != null) {
                csf.b(this.mDeviceId, d.i());
            } else {
                LogUtil.h(TAG, "deleteLocalData delete user is null", next.c());
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cpw.a(false, TAG, "onCreate");
        this.mMyHandler = new MyHandler();
        this.mContext = cpp.a();
        if (getArguments() != null) {
            this.mDeviceId = getArguments().getString("deviceId");
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
            }
            try {
                this.mUserDataInfoList = getArguments().getParcelableArrayList("userData");
            } catch (Exception unused) {
                cpw.e(false, TAG, "getParcelableArrayList exception");
            }
        }
        EventBus.d(this.mEventCallback, 0, "delete_user_data_result");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.wifi_device_user_clear_layout, viewGroup, false);
        initView();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        initData();
        return viewGroup2;
    }

    private void initData() {
        mUserList = getUserClearBean();
        initAdpater();
    }

    private void initAdpater() {
        if (sAdapter == null) {
            sAdapter = new UserClearAdapter(mUserList, this.mContext, this, this.mProductId);
        }
        this.mRecyclerView.setAdapter(sAdapter);
    }

    private ArrayList<crz> getUserClearBean() {
        ArrayList<crz> arrayList = new ArrayList<>(16);
        List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        for (cfi cfiVar : allUser) {
            crz crzVar = new crz();
            String i = cfiVar.i();
            crzVar.e(cfiVar);
            if (!cpa.av(this.mProductId)) {
                setMiniUserClearBean(accountInfo, i, crzVar);
            } else {
                setUserClearBean(accountInfo, i, crzVar, cpa.ae(this.mProductId) ? 100 : 10);
            }
            addUserClearBean(crzVar, arrayList);
        }
        return arrayList;
    }

    private void addUserClearBean(crz crzVar, ArrayList<crz> arrayList) {
        if (crzVar == null || crzVar.a() <= 0.0d) {
            return;
        }
        arrayList.add(crzVar);
    }

    private void setMiniUserClearBean(String str, String str2, crz crzVar) {
        Iterator<MiniUserDataInfo> it = this.mUserDataInfoList.iterator();
        while (it.hasNext()) {
            MiniUserDataInfo next = it.next();
            String huid = next.getHuid();
            double weight = next.getWeight();
            String substring = ("0".equals(huid) || ctv.c(str2, str)) ? "" : huid.substring(0, str2.length());
            if ((!ctv.c(str2, str) || !"0".equals(huid)) && !str2.equals(substring)) {
                LogUtil.h(TAG, "setMiniUserClearBean User not found next data");
            } else if (weight > 0.0d) {
                crzVar.e(weight);
                return;
            }
        }
    }

    private void setUserClearBean(String str, String str2, crz crzVar, int i) {
        String e;
        double d;
        if (ctv.c(str2, str)) {
            e = csf.e(this.mDeviceId, String.valueOf(0));
        } else {
            e = csf.e(this.mDeviceId, str2);
        }
        String j = csf.j(e);
        if (j == null || TextUtils.isEmpty(j) || "0".equals(j)) {
            return;
        }
        try {
            d = Double.parseDouble(j);
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "userWeight parse double exception");
            d = 0.0d;
        }
        crzVar.e(d / i);
    }

    private void initView() {
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "initView mainActivity is null");
            return;
        }
        setTitle(this.mainActivity.getString(R.string.IDS_device_wifi_clear_user));
        this.mCustomTitleBar.setLeftButtonDrawable(ContextCompat.getDrawable(this.mContext, R.drawable._2131430298_res_0x7f0b0b9a), nsf.h(R.string._2130850611_res_0x7f023333));
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.child.findViewById(R.id._clear_user_data_recycler_view);
        this.mRecyclerView = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.mContext));
        HealthToolBar healthToolBar = (HealthToolBar) this.child.findViewById(R.id.clear_user_data_health_tool_bar);
        this.mHealthToolBar = healthToolBar;
        healthToolBar.cHc_(View.inflate(this.mainActivity, R.layout.hw_toolbar_bottomview, null));
        this.mHealthToolBar.setIcon(1, R.drawable._2131430849_res_0x7f0b0dc1);
        this.mHealthToolBar.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
        this.mHealthToolBar.setIconTitleColor(1, this.mainActivity.getResources().getString(R.string._2130841397_res_0x7f020f35), R.color._2131299244_res_0x7f090bac);
        this.mHealthToolBar.setIconTitle(3, this.mainActivity.getResources().getString(R.string._2130841399_res_0x7f020f37));
        this.mHealthToolBar.setIconEnabled(1, false);
        this.mHealthToolBar.cHf_(this.mainActivity);
        this.mHealthToolBar.setOnSingleTapListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setView() {
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "setView mainActivity is null");
            return;
        }
        Resources resources = this.mainActivity.getResources();
        int i = this.mChooseCount;
        String quantityString = resources.getQuantityString(R.plurals._2130903044_res_0x7f030004, i, Integer.valueOf(i));
        if (this.mChooseCount == 0) {
            setTitle(this.mainActivity.getString(R.string.IDS_device_wifi_selectNone));
            this.mHealthToolBar.setIcon(1, R.drawable._2131430849_res_0x7f0b0dc1);
            this.mHealthToolBar.setIconEnabled(1, false);
            this.mHealthToolBar.setIconTitleColor(1, this.mainActivity.getResources().getString(R.string._2130841397_res_0x7f020f35), R.color._2131299244_res_0x7f090bac);
            if (mUserList.isEmpty()) {
                this.mHealthToolBar.setIconEnabled(3, false);
            } else {
                this.mHealthToolBar.setIconEnabled(3, true);
            }
        } else {
            setTitle(quantityString);
            this.mHealthToolBar.setIcon(1, R.drawable._2131430848_res_0x7f0b0dc0);
            this.mHealthToolBar.setIconEnabled(1, true);
            this.mHealthToolBar.setIconTitleColor(1, this.mainActivity.getResources().getString(R.string._2130841397_res_0x7f020f35), R.color._2131299236_res_0x7f090ba4);
        }
        if (this.mChooseCount == mUserList.size()) {
            this.mHealthToolBar.setIconTitle(3, this.mainActivity.getResources().getString(R.string._2130841400_res_0x7f020f38));
        } else {
            this.mHealthToolBar.setIconTitle(3, this.mainActivity.getResources().getString(R.string._2130841399_res_0x7f020f37));
        }
        if (this.mNoSelect == 0) {
            cpw.a(false, TAG, "list is empty");
        }
    }

    @Override // com.huawei.health.device.wifi.interfaces.SelectClearUserInterface
    public void selectItem(int i, int i2) {
        this.mChooseCount = i2;
        this.mNoSelect = i;
        this.mMyHandler.sendEmptyMessage(1);
    }

    static class MyHandler extends StaticHandler<WiFiUserClearFragment> {
        private MyHandler(WiFiUserClearFragment wiFiUserClearFragment) {
            super(wiFiUserClearFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiUserClearFragment wiFiUserClearFragment, Message message) {
            if (wiFiUserClearFragment.isDestroy()) {
                return;
            }
            if (!wiFiUserClearFragment.isAdded()) {
                cpw.e(false, WiFiUserClearFragment.TAG, "MyHandler mFragment is not add");
                return;
            }
            cpw.a(false, WiFiUserClearFragment.TAG, "MyHandler what:", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                wiFiUserClearFragment.deleteLocalData();
                wiFiUserClearFragment.clearDataSuccess();
            } else {
                if (i == 1) {
                    wiFiUserClearFragment.setView();
                    return;
                }
                if (i == 2) {
                    wiFiUserClearFragment.clearDataSuccess();
                } else if (i == 3) {
                    wiFiUserClearFragment.showDeleteFail();
                } else {
                    cpw.d(false, "MyHandler what is other", new Object[0]);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDataSuccess() {
        nrh.b(this.mainActivity, R.string.IDS_device_wifi_clear_user_success);
        mUserList = sAdapter.a();
        sAdapter.notifyDataSetChanged();
        if (koq.b(mUserList)) {
            onBackPressed();
        }
    }

    @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
    public void onSingleTap(int i) {
        if (i == 1) {
            showDeleteConfirmDialog();
            return;
        }
        if (i == 3) {
            if (this.mChooseCount == mUserList.size()) {
                sAdapter.e();
                return;
            } else {
                sAdapter.c();
                return;
            }
        }
        LogUtil.h(TAG, "onSingleTap default");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.a(this.mEventCallback, "delete_user_data_result");
        List<crz> list = mUserList;
        if (list != null) {
            list.clear();
            mUserList = null;
        }
        if (sAdapter != null) {
            sAdapter = null;
        }
        ArrayList<MiniUserDataInfo> arrayList = this.mUserDataInfoList;
        if (arrayList != null) {
            arrayList.clear();
            this.mUserDataInfoList = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        cpw.e(false, TAG, "DeviceMainActivity is Destroyed");
        return true;
    }

    private void deleteChooseUserInfo() {
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.b(TAG, "ProductId is null");
            return;
        }
        if (!cpa.av(this.mProductId)) {
            deleteMiniUser();
            return;
        }
        Context context = BaseApplication.getContext();
        if (!ctv.d(context)) {
            nrh.b(context, R.string.IDS_device_wifi_not_network);
        } else {
            clearSingleUserInfo(sAdapter.d());
        }
    }

    private void deleteMiniUser() {
        cgz cgzVar = new cgz();
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        UserClearAdapter userClearAdapter = sAdapter;
        if (userClearAdapter == null) {
            LogUtil.h(TAG, "deleteMiniUser sAdapter is null");
            return;
        }
        Iterator<crz> it = userClearAdapter.d().iterator();
        while (it.hasNext()) {
            String i2 = it.next().d().i();
            if (i != null && i.equals(i2)) {
                byte[] d = cgzVar.d(i2);
                byte[] bArr = new byte[d.length + 32];
                System.arraycopy(d, 0, bArr, 0, d.length);
                Bundle bundle = new Bundle();
                bundle.putByteArray(JsbMapKeyNames.H5_USER_ID, bArr);
                EventBus.d(new EventBus.b("delete_user_data", bundle));
            } else {
                byte[] d2 = cgzVar.d(i);
                byte[] a2 = cgzVar.a(i2);
                byte[] bArr2 = new byte[d2.length + a2.length];
                System.arraycopy(d2, 0, bArr2, 0, d2.length);
                System.arraycopy(a2, 0, bArr2, d2.length, a2.length);
                Bundle bundle2 = new Bundle();
                bundle2.putByteArray(JsbMapKeyNames.H5_USER_ID, bArr2);
                EventBus.d(new EventBus.b("delete_user_data", bundle2));
            }
        }
    }

    private void getClearUserInfo(cfi cfiVar, ctk ctkVar, List<DeviceServiceInfo> list) {
        String j;
        String str;
        if (cfiVar == null) {
            cpw.d(false, TAG, "getClearUserInfo user is null");
            return;
        }
        if (cfiVar.h().equals(MultiUsersManager.INSTANCE.getMainUser().h())) {
            str = String.valueOf(0);
            j = cts.b;
        } else {
            String i = cfiVar.i();
            j = cfiVar.j();
            str = i;
        }
        if (ctkVar.b().k() != 2) {
            if (j.indexOf("_") >= 0) {
                j = j.substring(0, j.indexOf("_"));
            }
        } else if (j.indexOf("_") == -1) {
            j = j + "_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("id", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        hashMap.put("uid", str);
        hashMap.put("isClear", String.valueOf(1));
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid(j);
        cpw.a(false, TAG, "getClearUserInfo serviceInfo:", deviceServiceInfo);
        list.add(deviceServiceInfo);
    }

    private void clearSingleUserInfo(final ArrayList<crz> arrayList) {
        if (TextUtils.isEmpty(this.mDeviceId)) {
            cpw.d(false, TAG, "getClearUserInfo deviceId is null");
            return;
        }
        HealthDevice e = cjx.e().e(this.mDeviceId);
        if (e == null || !(e instanceof ctk)) {
            cpw.d(false, TAG, "getClearUserInfo device is null or type error");
            return;
        }
        ctk ctkVar = (ctk) e;
        ArrayList arrayList2 = new ArrayList(16);
        Iterator<crz> it = arrayList.iterator();
        while (it.hasNext()) {
            getClearUserInfo(it.next().d(), ctkVar, arrayList2);
        }
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDevId(this.mDeviceId);
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList2);
        jbs.a(this.mContext).d(wifiDeviceControlDataModelReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiUserClearFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WiFiUserClearFragment.this.m251xff46a62f(arrayList, (CloudCommonReponse) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$clearSingleUserInfo$0$com-huawei-health-device-ui-measure-fragment-WiFiUserClearFragment, reason: not valid java name */
    /* synthetic */ void m251xff46a62f(ArrayList arrayList, CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (z) {
            cpw.a(false, TAG, "clearSingleUserInfo clear userInfo success");
            this.mMyHandler.obtainMessage(2).sendToTarget();
            updataDataByWifiDeviceStatus(arrayList);
        } else {
            cpw.d(false, TAG, "clearSingleUserInfo clear userInfo fail");
            this.mMyHandler.obtainMessage(3).sendToTarget();
        }
    }

    private void updataDataByWifiDeviceStatus(final ArrayList<crz> arrayList) {
        WifiDeviceGetDeviceStatusReq wifiDeviceGetDeviceStatusReq = new WifiDeviceGetDeviceStatusReq();
        wifiDeviceGetDeviceStatusReq.setDevId(this.mDeviceId);
        jbs.a(this.mContext).a(wifiDeviceGetDeviceStatusReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiUserClearFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WiFiUserClearFragment.this.m253x23d7267f(arrayList, (WifiDeviceGetDeviceStatusRsp) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$updataDataByWifiDeviceStatus$1$com-huawei-health-device-ui-measure-fragment-WiFiUserClearFragment, reason: not valid java name */
    /* synthetic */ void m253x23d7267f(ArrayList arrayList, WifiDeviceGetDeviceStatusRsp wifiDeviceGetDeviceStatusRsp, String str, boolean z) {
        if (wifiDeviceGetDeviceStatusRsp != null) {
            if (!TextUtils.isEmpty(wifiDeviceGetDeviceStatusRsp.getStatus()) && wifiDeviceGetDeviceStatusRsp.getStatus().equals("online")) {
                LogUtil.a(TAG, "updataDataByWifiDeviceStatus device is online, get device response:", wifiDeviceGetDeviceStatusRsp.toString(), ",success:", Boolean.valueOf(z));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    csf.b(this.mDeviceId, ((crz) it.next()).d().i());
                }
                return;
            }
            LogUtil.h(TAG, "updataDataByWifiDeviceStatus device is offline");
            return;
        }
        LogUtil.h(TAG, "updataDataByWifiDeviceStatus response is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeleteFail() {
        nrh.b(this.mainActivity, R.string.IDS_device_wifi_delete_fail);
    }

    private void showDeleteConfirmDialog() {
        cpw.a(false, TAG, "showDeleteConfirmDialog");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mainActivity);
        builder.e(R.string.IDS_device_wifi_userinfo_delete_confirm).czz_(R.string._2130851415_res_0x7f023657, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiUserClearFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WiFiUserClearFragment.lambda$showDeleteConfirmDialog$2(view);
            }
        }).czC_(R.string._2130851418_res_0x7f02365a, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiUserClearFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WiFiUserClearFragment.this.m252xa702f53a(view);
            }
        });
        builder.e().show();
    }

    static /* synthetic */ void lambda$showDeleteConfirmDialog$2(View view) {
        LogUtil.a(TAG, "showDeleteConfirmDialog NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showDeleteConfirmDialog$3$com-huawei-health-device-ui-measure-fragment-WiFiUserClearFragment, reason: not valid java name */
    /* synthetic */ void m252xa702f53a(View view) {
        deleteChooseUserInfo();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (cpa.ae(this.mProductId)) {
            popupFragment(HagridDeviceManagerFragment.class);
            return false;
        }
        popupFragment(WiFiProductIntroductionFragment.class);
        SharedPreferenceManager.e(this.mainActivity, String.valueOf(10000), "health_is_wificlear", "true", (StorageParams) null);
        return false;
    }
}
