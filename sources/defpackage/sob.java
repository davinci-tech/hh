package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.StatusCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes7.dex */
public class sob {
    private static sob d;
    private static final Object e = new Object();
    private CopyOnWriteArraySet<StatusCallback> c = new CopyOnWriteArraySet<>();

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f17173a = new BroadcastReceiver() { // from class: sob.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.e("FileBtBroadcast", "intent is null");
                return;
            }
            LogUtil.c("FileBtBroadcast", "Action is: ", intent.getAction());
            if (TextUtils.isEmpty(intent.getAction())) {
                LogUtil.a("FileBtBroadcast", "action is empty.");
            } else if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                LogUtil.c("FileBtBroadcast", "action match");
                sob.this.ejJ_(intent);
            }
        }
    };

    private sob() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.e(), this.f17173a, intentFilter, bin.d, null);
    }

    protected static sob e() {
        sob sobVar;
        synchronized (e) {
            if (d == null) {
                d = new sob();
            }
            sobVar = d;
        }
        return sobVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ejJ_(Intent intent) {
        Parcelable parcelable;
        try {
            parcelable = intent.getParcelableExtra("deviceinfo");
        } catch (BadParcelableException unused) {
            LogUtil.e("FileBtBroadcast", "fuzzy test exception, no care this.");
            parcelable = null;
        }
        if (!(parcelable instanceof DeviceInfo)) {
            LogUtil.c("FileBtBroadcast", "device info is wrong, please check.");
            return;
        }
        DeviceInfo deviceInfo = (DeviceInfo) parcelable;
        UniteDevice e2 = sph.e(deviceInfo);
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.c("FileBtBroadcast", "deviceStatusChangeCallbacks size : " + this.c.size());
        Iterator<StatusCallback> it = this.c.iterator();
        while (it.hasNext()) {
            StatusCallback next = it.next();
            if (next != null) {
                try {
                    next.onStatusChanged(0, e2, deviceConnectState);
                } catch (RemoteException unused2) {
                    LogUtil.e("FileBtBroadcast", "reportCallback RemoteException");
                }
            } else {
                LogUtil.e("FileBtBroadcast", "onConnectStatusChanged: callback is null.");
            }
        }
    }

    protected void d(StatusCallback statusCallback) {
        if (statusCallback == null) {
            return;
        }
        LogUtil.c("FileBtBroadcast", "registerCallback : ", statusCallback);
        this.c.add(statusCallback);
    }

    protected void e(StatusCallback statusCallback) {
        if (statusCallback == null) {
            return;
        }
        this.c.remove(statusCallback);
    }
}
