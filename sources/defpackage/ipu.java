package defpackage;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.HealthAlarmInfo;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorItem;
import com.huawei.wearengine.monitor.MonitorListener;
import health.compact.a.DeviceUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes7.dex */
public class ipu extends DataObservableNoCallback<Subscriber> {
    private static Context sContext;
    private static ipu sInstance;
    private String currentListeningDeviceId;
    private tph monitorClient;
    private final MonitorListener monitorListener = new MonitorListener() { // from class: iqb
        @Override // com.huawei.wearengine.monitor.MonitorListener
        public final void onChanged(int i, MonitorItem monitorItem, MonitorData monitorData) {
            ipu.this.m969xcf5409ab(i, monitorItem, monitorData);
        }
    };

    /* renamed from: lambda$new$0$com-huawei-hihealthservice-hihealthkit-model-DeviceObservable, reason: not valid java name */
    /* synthetic */ void m969xcf5409ab(int i, MonitorItem monitorItem, MonitorData monitorData) {
        if (MonitorItem.MONITOR_ITEM_HEART_RATE_ALARM.getName().equals(monitorItem.getName())) {
            LogUtil.a("DeviceObservable", "monitorListener result:", moj.e(monitorData));
            notifyDataChanged(monitorData);
        }
    }

    public static ipu getInstance(Context context) {
        ipu ipuVar;
        synchronized (ipu.class) {
            if (sInstance == null) {
                sInstance = new ipu();
                sContext = context;
            }
            ipuVar = sInstance;
        }
        return ipuVar;
    }

    public static boolean hasObservableInit() {
        return sInstance != null;
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback
    public boolean registerObserver(Parcelable parcelable, Subscriber subscriber) {
        if (!(parcelable instanceof HealthAlarmInfo)) {
            ReleaseLogUtil.c("HiH_DeviceObservable", "registerObserver, error model");
            return false;
        }
        if (!DeviceUtil.a()) {
            ReleaseLogUtil.c("HiH_DeviceObservable", "registerObserver, get device from db is empty");
            return false;
        }
        boolean registerObserver = super.registerObserver(parcelable, (Parcelable) subscriber);
        ReleaseLogUtil.d("HiH_DeviceObservable", "registerObserver result: ", Boolean.valueOf(registerObserver));
        if (registerObserver) {
            subscribeToDevice();
        }
        return registerObserver;
    }

    public boolean unregisterObserver(EventTypeInfo eventTypeInfo, Subscriber subscriber) {
        if (super.unregisterObserver((Parcelable) eventTypeInfo, (EventTypeInfo) subscriber)) {
            if (!isEmpty()) {
                return true;
            }
            clearObserver();
            unRegisterDeviceListener();
            return true;
        }
        ReleaseLogUtil.c("HiH_DeviceObservable", "unregisterObserver failed");
        return false;
    }

    public void notifyConnectionStateChange(DeviceInfo deviceInfo) {
        synchronized (this) {
            if (!deviceInfo.getDeviceUdid().equals(this.currentListeningDeviceId)) {
                ReleaseLogUtil.e("HiH_DeviceObservable", "connected device has changed!");
                unRegisterDeviceListener();
                subscribeToDevice();
            }
        }
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback
    public void notifyDataChanged(Object obj) {
        String str;
        if (!(obj instanceof MonitorData)) {
            ReleaseLogUtil.d("HiH_DeviceObservable", "wrong data construct");
            return;
        }
        int asInt = ((MonitorData) obj).asInt();
        ReleaseLogUtil.e("HiH_DeviceObservable", "monitorData status is:", Integer.valueOf(asInt));
        if (asInt == 1) {
            str = HealthAlarmInfo.Type.TACHYCARDIA.getName();
        } else if (asInt == 2) {
            str = HealthAlarmInfo.Type.BRADYCARDIA.getName();
        } else {
            ReleaseLogUtil.e("HiH_DeviceObservable", "status is not support.");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (EventTypeInfo eventTypeInfo : this.eventInfos.keySet()) {
            if (eventTypeInfo.getSubType().equals(str)) {
                irf.c(sContext, eventTypeInfo, (Set) this.eventInfos.get(eventTypeInfo));
            }
        }
    }

    private void subscribeToDevice() {
        ReleaseLogUtil.e("HiH_DeviceObservable", "enter subscribeToDevice!");
        tnu.c(sContext).c().addOnSuccessListener(new OnSuccessListener() { // from class: iqa
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                ipu.this.m970x996fdb27((List) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: iqd
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                ReleaseLogUtil.c("HiH_DeviceObservable", "getCommonDevice Exception!");
            }
        });
    }

    /* renamed from: lambda$subscribeToDevice$1$com-huawei-hihealthservice-hihealthkit-model-DeviceObservable, reason: not valid java name */
    /* synthetic */ void m970x996fdb27(List list) {
        Device device;
        if (list != null && !list.isEmpty()) {
            ReleaseLogUtil.e("HiH_DeviceObservable", "getCommonDevice size is:", Integer.valueOf(list.size()));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                device = (Device) it.next();
                if (device.isConnected()) {
                    ReleaseLogUtil.e("HiH_DeviceObservable", "connected device is:", device.getName());
                    this.currentListeningDeviceId = device.getUuid();
                    break;
                }
            }
        }
        device = null;
        if (device == null) {
            ReleaseLogUtil.d("HiH_DeviceObservable", "There is no connected device.");
        } else if (device.getMonitorCapability() != 0) {
            ReleaseLogUtil.d("HiH_DeviceObservable", "The connected device not support register monitor.");
        } else {
            registerMonitor(device);
        }
    }

    private void registerMonitor(Device device) {
        ReleaseLogUtil.e("HiH_DeviceObservable", "enter registerMonitor!");
        if (this.monitorClient == null) {
            this.monitorClient = tnu.a(sContext);
        }
        this.monitorClient.b(device, MonitorItem.MONITOR_ITEM_HEART_RATE_ALARM, this.monitorListener).addOnSuccessListener(new OnSuccessListener() { // from class: ipz
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                ReleaseLogUtil.e("HiH_DeviceObservable", "register monitor success.");
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: iqc
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                ReleaseLogUtil.c("HiH_DeviceObservable", "register monitor failure.");
            }
        });
    }

    private void unRegisterDeviceListener() {
        ReleaseLogUtil.e("DeviceObservable", "HiH_DeviceObservable");
        tph tphVar = this.monitorClient;
        if (tphVar == null) {
            ReleaseLogUtil.e("HiH_DeviceObservable", "no monitor listener started!");
        } else {
            tphVar.c(this.monitorListener);
        }
    }
}
