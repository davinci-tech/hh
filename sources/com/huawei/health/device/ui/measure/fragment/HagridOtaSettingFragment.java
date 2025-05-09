package com.huawei.health.device.ui.measure.fragment;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.csc;
import defpackage.csf;
import defpackage.jbs;
import defpackage.nrh;
import defpackage.nsn;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class HagridOtaSettingFragment extends BaseFragment {
    private static final String AUTO_UPGRADE_STATUS_CLOSE = "0";
    private static final String AUTO_UPGRADE_STATUS_OPEN = "1";
    private static final int MSG_SET_AUTO_UPGRADE_STATUS_FAIL = 1;
    private static final String TAG = "HagridOtaSettingFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridOtaSettingFragment";
    private HealthSwitchButton mAutoUpgradeSwitchButton;
    private String mDeviceId;
    private OtaSettingHandler mHandler;
    private IBaseResponseCallback mOnSetAutoUpDateStatusCallback = new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridOtaSettingFragment$$ExternalSyntheticLambda2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i, Object obj) {
            HagridOtaSettingFragment.this.m177x58375e23(i, obj);
        }
    };
    private View.OnClickListener mAutoUpgradeClickListener = new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridOtaSettingFragment$$ExternalSyntheticLambda3
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            HagridOtaSettingFragment.this.m178x3b631164(view);
        }
    };

    /* renamed from: lambda$new$0$com-huawei-health-device-ui-measure-fragment-HagridOtaSettingFragment, reason: not valid java name */
    /* synthetic */ void m177x58375e23(int i, Object obj) {
        if (i == 0) {
            csc.a(this.mDeviceId, this.mAutoUpgradeSwitchButton.isChecked() ? "1" : "0");
        } else {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
        }
    }

    /* renamed from: lambda$new$1$com-huawei-health-device-ui-measure-fragment-HagridOtaSettingFragment, reason: not valid java name */
    /* synthetic */ void m178x3b631164(View view) {
        if (nsn.o()) {
            this.mAutoUpgradeSwitchButton.setChecked(!r0.isChecked());
            LogUtil.a(TAG, "AutoUpgradeSwitchButton onClick: ", "is fast click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        setAutoUpgradeStatus(this.mAutoUpgradeSwitchButton.isChecked(), this.mOnSetAutoUpDateStatusCallback);
        ViewClickInstrumentation.clickOnView(view);
    }

    static class OtaSettingHandler extends StaticHandler<HagridOtaSettingFragment> {
        private OtaSettingHandler(HagridOtaSettingFragment hagridOtaSettingFragment) {
            super(hagridOtaSettingFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(HagridOtaSettingFragment hagridOtaSettingFragment, Message message) {
            if (hagridOtaSettingFragment == null) {
                LogUtil.h(HagridOtaSettingFragment.TAG_RELEASE, "OtaSettingHandler HagridOtaSettingFragment is null");
                return;
            }
            if (message != null) {
                if (hagridOtaSettingFragment.isDestroy()) {
                    LogUtil.h(HagridOtaSettingFragment.TAG_RELEASE, "OtaSettingHandler activity is destroy");
                    return;
                }
                if (!hagridOtaSettingFragment.isAdded()) {
                    LogUtil.h(HagridOtaSettingFragment.TAG_RELEASE, "OtaSettingHandler mFragment is not add");
                    return;
                }
                LogUtil.a(HagridOtaSettingFragment.TAG, "OtaSettingHandler what:", Integer.valueOf(message.what));
                if (message.what == 1) {
                    hagridOtaSettingFragment.modifyAutoUpgradeFail(hagridOtaSettingFragment.mAutoUpgradeSwitchButton.isChecked());
                    return;
                } else {
                    LogUtil.h(HagridOtaSettingFragment.TAG_RELEASE, "OtaSettingHandler what is other");
                    return;
                }
            }
            LogUtil.h(HagridOtaSettingFragment.TAG_RELEASE, "OtaSettingHandler message is null");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initData();
        this.mHandler = new OtaSettingHandler();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        this.child = layoutInflater.inflate(R.layout.hagrid_device_ota_setting_layout, viewGroup, false);
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        initView();
        setViewData();
        getDeviceAutoUpdateStatus();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void getDeviceAutoUpdateStatus() {
        final String b = csc.b(this.mDeviceId);
        this.mAutoUpgradeSwitchButton.setChecked("1".equals(b));
        if (TextUtils.isEmpty(this.mDeviceId)) {
            LogUtil.h(TAG, "getDeviceInfo deviceId is null");
            return;
        }
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        wifiDeviceServiceInfoReq.setDevId(this.mDeviceId);
        wifiDeviceServiceInfoReq.setSid("devOtaInfo");
        jbs.a(BaseApplication.getContext()).b(wifiDeviceServiceInfoReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridOtaSettingFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                HagridOtaSettingFragment.this.m176x1b2ef39b(b, (WifiDeviceServiceInfoRsp) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$getDeviceAutoUpdateStatus$2$com-huawei-health-device-ui-measure-fragment-HagridOtaSettingFragment, reason: not valid java name */
    /* synthetic */ void m176x1b2ef39b(String str, WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str2, boolean z) {
        onCloudAutoUpdateStatusResult(wifiDeviceServiceInfoRsp, z, str);
    }

    private void onCloudAutoUpdateStatusResult(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, boolean z, String str) {
        if (!z) {
            LogUtil.h(TAG, "onCloudAutoUpdateStatusResult : isSuccess = false");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "onCloudAutoUpdateStatusResult : autoUpgradeStatusSp is empty");
            return;
        }
        if (wifiDeviceServiceInfoRsp == null || wifiDeviceServiceInfoRsp.getDeviceServiceInfo() == null || wifiDeviceServiceInfoRsp.getDeviceServiceInfo().isEmpty()) {
            LogUtil.h(TAG, "onCloudAutoUpdateStatusResult : can not get DeviceServiceInfo from res");
            return;
        }
        LogUtil.a(TAG, "onCloudAutoUpdateStatusResult : DeviceServiceInfo info size: ", Integer.valueOf(wifiDeviceServiceInfoRsp.getDeviceServiceInfo().size()));
        Iterator<DeviceServiceInfo> it = wifiDeviceServiceInfoRsp.getDeviceServiceInfo().iterator();
        while (it.hasNext()) {
            Map<String, String> data = it.next().getData();
            if (data != null && data.containsKey("upgrade_auto")) {
                if (str.equals(data.get("upgrade_auto"))) {
                    return;
                }
                LogUtil.a(TAG, "onCloudAutoUpdateStatusResult : change the autoUpgradeStatus in cloud");
                setAutoUpgradeStatus("1".equals(str), null);
                return;
            }
        }
    }

    private void initView() {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) this.child.findViewById(R.id.hagrid_ota_auto_setting_switch_button);
        this.mAutoUpgradeSwitchButton = healthSwitchButton;
        healthSwitchButton.setOnClickListener(this.mAutoUpgradeClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        LogUtil.h(TAG, "DeviceMainActivity is Destroyed");
        return true;
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mDeviceId = arguments.getString("deviceId");
        } else {
            LogUtil.h(TAG_RELEASE, "initData getArguments is null");
        }
    }

    private void setViewData() {
        setTitle(getResources().getString(R.string.IDS_device_haige_auto_upgrade_title));
    }

    private void setAutoUpgradeStatus(boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(this.mDeviceId)) {
            LogUtil.h(TAG_RELEASE, "setAutoUpgradeStatus deviceId is null");
        } else {
            csf.c(this.mDeviceId, z ? "1" : "0", new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridOtaSettingFragment$$ExternalSyntheticLambda0
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z2) {
                    HagridOtaSettingFragment.lambda$setAutoUpgradeStatus$3(IBaseResponseCallback.this, obj, str, z2);
                }
            });
        }
    }

    static /* synthetic */ void lambda$setAutoUpgradeStatus$3(IBaseResponseCallback iBaseResponseCallback, Object obj, String str, boolean z) {
        LogUtil.h(TAG, "setAutoUpgradeStatus isSuccess:", Boolean.valueOf(z));
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(z ? 0 : -1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void modifyAutoUpgradeFail(boolean z) {
        nrh.b(this.mainActivity, R.string._2130841551_res_0x7f020fcf);
        this.mAutoUpgradeSwitchButton.setChecked(!z);
    }
}
