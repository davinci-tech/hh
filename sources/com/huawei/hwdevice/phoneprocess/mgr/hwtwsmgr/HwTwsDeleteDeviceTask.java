package com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jsz;
import defpackage.kfg;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class HwTwsDeleteDeviceTask extends BaseTwsTask {
    private static final int CONVERT_TIME = 2;
    private static final int DEFAULT_VALUE = 0;
    private static final int LENGTH_VALUE = 1;
    private static final int RECEIVE_ACCOUNT_ORDER = 2;
    private static final int RECEIVE_TIMESTAMP_ORDER = 3;
    private static final int RECEIVE_UDID_ORDER = 1;
    private static final int RESULT_INFO = 4;
    private static final String TAG = "HwTwsDeleteDeviceTask";
    private static final String TAG_RELEASE = "R_HwTwsDeleteDeviceTask";
    private Context mContext;
    private String mData;
    private cwl mTlvUtils;

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public boolean onWaitFor(String str, Object obj) {
        return false;
    }

    public HwTwsDeleteDeviceTask(String str, cwl cwlVar, Context context) {
        this.mData = str;
        this.mTlvUtils = cwlVar;
        this.mContext = context;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public boolean onExecutor(ProfileClient profileClient) {
        doExecutor(profileClient);
        return true;
    }

    private void doExecutor(ProfileClient profileClient) {
        kfg buildPairDevice = buildPairDevice();
        if (buildPairDevice == null || TextUtils.isEmpty(buildPairDevice.n())) {
            LogUtil.h(TAG, "doExecutor but pair device is error");
            return;
        }
        String n = buildPairDevice.n();
        boolean tryDeleteDeviceById = tryDeleteDeviceById(profileClient, buildPairDevice.e(), n);
        ReleaseLogUtil.e(TAG_RELEASE, "onExecutor tryDeleteDeviceById is success:", Boolean.valueOf(tryDeleteDeviceById));
        sendCommand21(tryDeleteDeviceById, n);
    }

    private void sendCommand21(boolean z, String str) {
        String str2;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(43);
        deviceCommand.setCommandID(21);
        if (str == null || str.length() == 0) {
            str2 = "";
        } else {
            str2 = cvx.e(1) + cvx.e(cvx.c(str).length() / 2) + cvx.c(str);
        }
        String str3 = str2 + cvx.e(4) + cvx.e(1) + cvx.e(!z ? 1 : 0);
        deviceCommand.setDataContent(cvx.a(str3));
        deviceCommand.setDataLen(cvx.a(str3).length);
        LogUtil.a(TAG, "sendCommand21, deviceCommand:", deviceCommand.toString());
        jsz.b(this.mContext).sendDeviceData(deviceCommand);
    }

    private boolean tryDeleteDeviceById(ProfileClient profileClient, String str, String str2) {
        if (!isSameAccount(str)) {
            LogUtil.h(TAG, "tryDeleteDeviceById but account not match");
            return false;
        }
        deleteServiceProfileAndCharacter(profileClient, str2);
        if (profileClient.deleteDevice(str2)) {
            return true;
        }
        LogUtil.h(TAG, "tryDeleteDeviceById delete device failed");
        return false;
    }

    private void deleteServiceProfileAndCharacter(ProfileClient profileClient, String str) {
        List<ServiceProfile> servicesOfDevice = profileClient.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (koq.b(servicesOfDevice)) {
            LogUtil.h(TAG, "deleteServiceProfileAndCharacter do not find any service profile list");
            return;
        }
        Iterator<ServiceProfile> it = servicesOfDevice.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            deleteAllCharacter(profileClient, str, id);
            if (!profileClient.deleteServiceOfDevice(str, id)) {
                LogUtil.h(TAG, "deleteAllServices find one service device failed!");
            }
        }
    }

    private boolean isSameAccount(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1000);
        if (TextUtils.isEmpty(accountInfo)) {
            return false;
        }
        return accountInfo.equals(str);
    }

    private kfg buildPairDevice() {
        cwe cweVar;
        try {
            cweVar = this.mTlvUtils.a(this.mData);
        } catch (cwg unused) {
            LogUtil.b(TAG, "buildPairDevice but find TlvException");
            cweVar = null;
        }
        if (cweVar == null) {
            return null;
        }
        List<cwd> e = cweVar.e();
        if (koq.b(e)) {
            return null;
        }
        kfg kfgVar = new kfg();
        for (cwd cwdVar : e) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 1) {
                kfgVar.m(cvx.e(cwdVar.c()));
            } else if (w == 2) {
                kfgVar.c(cvx.e(cwdVar.c()));
            } else if (w == 3) {
                LogUtil.a(TAG, "buildPairDevice timestamp:", Long.valueOf(CommonUtil.y(cwdVar.c())));
            }
        }
        return kfgVar;
    }

    private void deleteAllCharacter(ProfileClient profileClient, String str, String str2) {
        ServiceCharacteristicProfile serviceCharacteristics = profileClient.getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h(TAG, "deleteAllCharacter but not find characteristic profile.");
            return;
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (profile.isEmpty()) {
            LogUtil.h(TAG, "deleteAllCharacter profile map is empty");
            return;
        }
        Iterator<Map.Entry<String, Object>> it = profile.entrySet().iterator();
        while (it.hasNext()) {
            if (!profileClient.deleteServiceCharacteristic(str, str2, it.next().getKey())) {
                LogUtil.h(TAG, "deleteAllCharacter find one characteristic delete failed");
            }
        }
    }
}
