package com.huawei.ui.homewear21.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homewear21.home.WearHomeBaseActivity;
import com.huawei.ui.homewear21.home.adapter.WearHomeGeneralAdapter;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.obz;
import defpackage.oxa;
import defpackage.ozf;
import defpackage.ozh;
import defpackage.ozj;
import defpackage.pep;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class WearHomeBaseActivity extends BaseActivity {
    private static final Object ADD_LOCK = new Object();
    protected static final String TAG = "WearHomeBaseActivity";
    protected Context mApplicationContext;
    protected Context mContext;
    protected CustomTitleBar mCustomTitleBar;
    protected DeviceSettingsInteractors mDeviceInteractors;
    private HealthRecycleView mRecyclerView;
    protected WearHomeGeneralAdapter mWearHomeGeneralAdapter;
    public boolean mIsClickScreen = false;
    protected String mDeviceMac = "";
    protected boolean mIsConnected = false;
    protected List<obz> mGeneralList = new ArrayList(0);
    protected DeviceInfo mCurrentDeviceInfo = null;
    protected DeviceCapability mDeviceCapability = null;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.ui.homewear21.home.WearHomeBaseActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h(WearHomeBaseActivity.TAG, "intent is null");
                return;
            }
            LogUtil.a(WearHomeBaseActivity.TAG, "mNonLocalBroadcastReceiver(): intent is ", intent.getAction());
            try {
                DeviceInfo deviceInfo = intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo ? (DeviceInfo) intent.getParcelableExtra("deviceinfo") : null;
                LogUtil.a(WearHomeBaseActivity.TAG, "handleConnectStateChanged() Process.myPid():", Integer.valueOf(Process.myPid()));
                if (deviceInfo != null) {
                    WearHomeBaseActivity.this.connectedChanged(deviceInfo);
                }
            } catch (ClassCastException e) {
                LogUtil.b(WearHomeBaseActivity.TAG, "DeviceInfo deviceInfo error", e.getMessage());
            }
        }
    };

    protected abstract void initGeneralList();

    protected abstract void setItemClick(int i);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApplicationContext = BaseApplication.getContext();
        this.mContext = this;
        setContentView(R.layout.wear_home_other_sitting_layout);
        this.mCustomTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.wear_home_sitting_bar);
        this.mRecyclerView = (HealthRecycleView) nsy.cMc_(this, R.id.recyclerView);
        this.mWearHomeGeneralAdapter = new WearHomeGeneralAdapter(this.mContext, this.mGeneralList);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.setAdapter(this.mWearHomeGeneralAdapter);
        this.mRecyclerView.setLayerType(2, null);
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mRecyclerView.a(false);
        this.mRecyclerView.d(false);
        this.mWearHomeGeneralAdapter.e(new WearHomeListener() { // from class: oxy
            @Override // com.huawei.ui.homewear21.home.listener.WearHomeListener
            public final void onItemClick(int i) {
                WearHomeBaseActivity.this.m795xbe8bd0db(i);
            }
        });
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.mDeviceMac = intent.getStringExtra("device_id");
        DeviceInfo a2 = oxa.a().a(this.mDeviceMac);
        this.mCurrentDeviceInfo = a2;
        if (a2 == null) {
            return;
        }
        this.mDeviceCapability = DeviceSettingsInteractors.d(this.mApplicationContext).e(this.mDeviceMac);
        this.mDeviceInteractors = DeviceSettingsInteractors.d(this.mApplicationContext);
        this.mIsConnected = this.mCurrentDeviceInfo.getDeviceConnectState() == 2;
        pep.dmT_(this.mContext, this.mBroadcastReceiver, "com.huawei.bone.action.CONNECTION_STATE_CHANGED");
    }

    /* renamed from: lambda$onCreate$0$com-huawei-ui-homewear21-home-WearHomeBaseActivity, reason: not valid java name */
    public /* synthetic */ void m795xbe8bd0db(int i) {
        if (nsn.o()) {
            return;
        }
        setItemClick(i);
    }

    private void initData() {
        if (this.mCurrentDeviceInfo == null || this.mDeviceCapability == null || this.mDeviceInteractors == null || this.mWearHomeGeneralAdapter == null || this.mRecyclerView == null) {
            return;
        }
        this.mIsClickScreen = false;
        initGeneralList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectedChanged(DeviceInfo deviceInfo) {
        String str;
        if (deviceInfo == null || (str = this.mDeviceMac) == null) {
            LogUtil.h(TAG, "deviceInfo or mDeviceMac is null!");
        } else if (!str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            LogUtil.h(TAG, "deviceConnectionChange return!");
        } else if (deviceInfo.getDeviceConnectState() != 2) {
            finish();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initData();
        this.mWearHomeGeneralAdapter.b(this.mGeneralList);
        this.mRecyclerView.getRecycledViewPool().clear();
        this.mRecyclerView.setAdapter(this.mWearHomeGeneralAdapter);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        pep.dmY_(this.mContext, this.mBroadcastReceiver);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void startActivity(Class<?> cls, int i) {
        Intent intent = new Intent(this.mContext, cls);
        intent.putExtra("device_id", this.mDeviceMac);
        startActivityForResult(intent, i);
    }

    protected obz getItem(List<obz> list, int i) {
        obz obzVar = new obz();
        for (obz obzVar2 : list) {
            if (obzVar2.e() == i) {
                LogUtil.c(TAG, "getItem getId is ", Integer.valueOf(obzVar2.e()));
                obzVar = obzVar2;
            }
        }
        return obzVar;
    }

    protected void setSettingItemRightText(int i, List<obz> list, String str) {
        LogUtil.a(TAG, "setSettingItemSwitchChecked id is ", Integer.valueOf(i), ",rightText is ", str);
        getItem(list, i).e(str);
        refreshGeneralDataAdapter();
    }

    protected boolean setEnable(int i, String str, boolean z) {
        return (i != 0 || str == null) ? z : CommonUtil.e(str, -1) == 1;
    }

    protected void refreshGeneralDataAdapter() {
        WearHomeGeneralAdapter wearHomeGeneralAdapter = this.mWearHomeGeneralAdapter;
        if (wearHomeGeneralAdapter != null) {
            wearHomeGeneralAdapter.notifyDataSetChanged();
        }
    }

    protected void addGeneralSettingItem(boolean z, ozj ozjVar, ozh ozhVar, CompoundButton.OnCheckedChangeListener... onCheckedChangeListenerArr) {
        synchronized (ADD_LOCK) {
            this.mGeneralList.add(ozf.djU_(z, ozjVar, ozhVar, onCheckedChangeListenerArr));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mIsClickScreen = true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
