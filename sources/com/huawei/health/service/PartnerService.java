package com.huawei.health.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.clockwork.companion.partnerapi.PartnerHostedApi;
import com.google.android.clockwork.companion.partnerapi.SmartWatchInfo;
import com.huawei.datatype.GoogleDeviceCache;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class PartnerService extends Service {
    private static final String TAG = "PartnerService";
    private final PartnerHostedApi.Stub mBinder = new PartnerHostedApi.Stub() { // from class: com.huawei.health.service.PartnerService.1
        @Override // com.google.android.clockwork.companion.partnerapi.PartnerHostedApi
        public SmartWatchInfo getPendingPairingSmartWatchInfo() {
            GoogleDeviceCache.QrBleCache cache = GoogleDeviceCache.getInstance().getCache();
            if (cache == null) {
                LogUtil.h(PartnerService.TAG, "getPendingPairingSmartWatchInfo deviceCache null");
                return null;
            }
            try {
                if (cache.getBluetoothDevice() != null) {
                    LogUtil.a(PartnerService.TAG, "getPendingPairingSmartWatchInfo cache :", cache.getBluetoothDevice().getName());
                }
            } catch (SecurityException e) {
                LogUtil.b(PartnerService.TAG, "PartnerHostedApi SecurityException:", ExceptionUtils.d(e));
            }
            SmartWatchInfo smartWatchInfo = new SmartWatchInfo();
            smartWatchInfo.setBluetoothDevice(cache.getBluetoothDevice());
            smartWatchInfo.setRssiValue(cache.getRssi());
            smartWatchInfo.setTimeStampMs(cache.getTime());
            smartWatchInfo.setDeviceModelImageResId(R.mipmap._2131821253_res_0x7f1102c5);
            LogUtil.a(PartnerService.TAG, "getPendingPairingSmartWatchInfo");
            return smartWatchInfo;
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a(TAG, "onBind");
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        boolean onUnbind = super.onUnbind(intent);
        LogUtil.a(TAG, "onUnbind status :", Boolean.valueOf(onUnbind));
        return onUnbind;
    }
}
