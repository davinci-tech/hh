package com.huawei.hidatamanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.contentsensor.sdk.ICaptureDataService;
import com.huawei.hidatamanager.util.LogUtils;

/* loaded from: classes4.dex */
public class CaptureLocalService {
    private static final int RET_FAIL = 0;
    private static final String TAG = "CaptureLocalService";
    private static CaptureLocalService sAPIService;
    private ICaptureDataService mCaptureService = null;
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: com.huawei.hidatamanager.CaptureLocalService.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtils.i(CaptureLocalService.TAG, "onServiceConnected...");
            CaptureLocalService.this.mCaptureService = ICaptureDataService.Stub.asInterface(iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.i(CaptureLocalService.TAG, "onServiceDisconnected...");
            CaptureLocalService.this.mCaptureService = null;
        }
    };
    private Context mContext;

    private CaptureLocalService(Context context) {
        this.mContext = context;
    }

    public static CaptureLocalService getInstance(Context context) {
        CaptureLocalService captureLocalService;
        synchronized (CaptureLocalService.class) {
            if (sAPIService == null) {
                sAPIService = new CaptureLocalService(context);
            }
            captureLocalService = sAPIService;
        }
        return captureLocalService;
    }

    public boolean bindService() {
        if (this.mContext == null) {
            LogUtils.e(TAG, "mContext = null, local service not init.");
            return false;
        }
        if (this.mCaptureService == null) {
            try {
                LogUtils.i(TAG, "start bind CaptureDataService.");
                Intent intent = new Intent("huawei.intent.action.CAPTURE_DATA_SERVICE");
                intent.setPackage("com.huawei.contentsensor");
                LogUtils.i(TAG, "bind service ret : " + this.mContext.bindService(intent, this.mConnection, 1));
            } catch (Exception e) {
                LogUtils.e(TAG, "bindService err", e);
            }
        } else {
            LogUtils.i(TAG, "service is already binded.");
        }
        return this.mCaptureService != null;
    }

    public void unBindService() {
        Context context = this.mContext;
        if (context != null) {
            context.unbindService(this.mConnection);
        }
        this.mCaptureService = null;
    }

    public int subscribeFeatureData(long j, ComponentName componentName, String str) {
        try {
        } catch (RemoteException unused) {
            LogUtils.e(TAG, "subscribeFeatureData error");
        }
        if (!bindService()) {
            LogUtils.w(TAG, "have not bindService");
            return 0;
        }
        return this.mCaptureService.subscribeFeatureData(j, componentName, str);
    }

    public int unSubscribeFeatureData(long j, ComponentName componentName) {
        try {
        } catch (RemoteException unused) {
            LogUtils.e(TAG, "unSubscribeFeatureData error");
        }
        if (!bindService()) {
            LogUtils.w(TAG, "have not bindService");
            return 0;
        }
        return this.mCaptureService.unSubscribeFeatureData(j, componentName);
    }
}
