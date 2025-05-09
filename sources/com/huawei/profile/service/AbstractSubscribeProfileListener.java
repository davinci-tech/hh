package com.huawei.profile.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ParcelableDeviceProfileChange;
import com.huawei.profile.profile.SubscribeInfo;
import com.huawei.profile.service.SubscribeProfileListener;
import com.huawei.profile.subscription.deviceinfo.DeviceProfileChange;
import com.huawei.profile.subscription.event.EventInfo;
import com.huawei.profile.utils.ProfileSdkCallPermission;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class AbstractSubscribeProfileListener extends Service {
    private static final String TAG = "SubscribeListener";

    protected void onNotifyDeviceListChange(int i, List<DeviceProfile> list, Bundle bundle) {
    }

    protected void onNotifyDeviceListInfoChange(int i, List<DeviceProfile> list, Bundle bundle) {
    }

    protected void onNotifyDeviceProfileChange(SubscribeInfo subscribeInfo, Bundle bundle) {
    }

    protected void onNotifyDeviceProfileChange(com.huawei.profile.subscription.deviceinfo.SubscribeInfo subscribeInfo, List<? extends DeviceProfileChange> list, Bundle bundle) {
    }

    protected void onNotifyEvent(EventInfo eventInfo, String str) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "receive bind request");
        return new SubscribeProfileListener.Stub() { // from class: com.huawei.profile.service.AbstractSubscribeProfileListener.1
            private final Context context;

            {
                this.context = AbstractSubscribeProfileListener.this.getApplicationContext();
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener.Stub, android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                Context context = this.context;
                if (context == null) {
                    Log.e(AbstractSubscribeProfileListener.TAG, "checkAppTrust failed, reason is mContext is null");
                    return false;
                }
                if (context.getPackageManager() == null) {
                    Log.e(AbstractSubscribeProfileListener.TAG, "onTransact packageManager is null");
                    return false;
                }
                if (!ProfileSdkCallPermission.checkAppTrust(AbstractSubscribeProfileListener.getCallingPkgListByUid(this.context, Binder.getCallingUid()), this.context)) {
                    Log.e(AbstractSubscribeProfileListener.TAG, "onTransact checkAppTrust failed");
                    return false;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceProfileChange(SubscribeInfo subscribeInfo, Bundle bundle) {
                AbstractSubscribeProfileListener.this.onNotifyDeviceProfileChange(subscribeInfo, bundle);
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceListChange(int i, List<DeviceProfile> list, Bundle bundle) {
                AbstractSubscribeProfileListener.this.onNotifyDeviceListChange(i, list, bundle);
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceProfileChangeList(com.huawei.profile.subscription.deviceinfo.SubscribeInfo subscribeInfo, List<ParcelableDeviceProfileChange> list, Bundle bundle) {
                AbstractSubscribeProfileListener.this.onNotifyDeviceProfileChange(subscribeInfo, list, bundle);
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyDeviceListInfoChange(int i, List<DeviceProfile> list, Bundle bundle) {
                AbstractSubscribeProfileListener.this.onNotifyDeviceListInfoChange(i, list, bundle);
            }

            @Override // com.huawei.profile.service.SubscribeProfileListener
            public void onNotifyEvent(EventInfo eventInfo, String str) {
                AbstractSubscribeProfileListener.this.onNotifyEvent(eventInfo, str);
            }
        };
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> getCallingPkgListByUid(Context context, int i) {
        String[] packagesForUid;
        if (context == null) {
            return Collections.emptyList();
        }
        List<String> emptyList = Collections.emptyList();
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || (packagesForUid = packageManager.getPackagesForUid(i)) == null) ? emptyList : Arrays.asList(packagesForUid);
        } catch (RuntimeException unused) {
            Log.e(TAG, "Failed to get pkg list of uid: " + i);
            return emptyList;
        }
    }
}
