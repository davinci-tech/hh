package defpackage;

import android.content.Context;
import com.huawei.android.location.activityrecognition.HwActivityChangedEvent;
import com.huawei.android.location.activityrecognition.HwActivityChangedExtendEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognition;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionExtendEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection;
import com.huawei.android.location.activityrecognition.HwEnvironmentChangedEvent;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.connection.RealConnection;

/* loaded from: classes5.dex */
public class kce {

    /* renamed from: a, reason: collision with root package name */
    private static kce f14270a;
    private static final Object d = new Object();
    private HwActivityRecognition j = null;
    private boolean b = false;
    private HwActivityRecognitionHardwareSink c = new HwActivityRecognitionHardwareSink() { // from class: kce.1
        @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
        public void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) {
            if (hwActivityChangedEvent == null || hwActivityChangedEvent.getActivityRecognitionEvents() == null) {
                return;
            }
            LogUtil.a("HwActivityRecognitionManager", "handleActivityChange hwActivityChangedEvent");
            for (HwActivityRecognitionEvent hwActivityRecognitionEvent : hwActivityChangedEvent.getActivityRecognitionEvents()) {
                if (hwActivityRecognitionEvent != null && hwActivityRecognitionEvent.getActivity() != null) {
                    kce.this.a(hwActivityRecognitionEvent.getActivity(), hwActivityRecognitionEvent.getEventType());
                } else {
                    LogUtil.h("HwActivityRecognitionManager", "event or event getActivity is null, system error");
                    return;
                }
            }
        }

        @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
        public void onActivityExtendChanged(HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
            if (hwActivityChangedExtendEvent == null || hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents() == null) {
                return;
            }
            LogUtil.a("HwActivityRecognitionManager", "onActivityExtendChanged hwActivityChangedExtendEvent");
            for (HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
                if (hwActivityRecognitionExtendEvent != null && hwActivityRecognitionExtendEvent.getActivity() != null) {
                    kce.this.e(hwActivityRecognitionExtendEvent.getActivity(), hwActivityRecognitionExtendEvent.getEventType());
                } else {
                    LogUtil.h("HwActivityRecognitionManager", "event or event getActivity is null, system error");
                    return;
                }
            }
        }

        @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
        public void onEnvironmentChanged(HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
            LogUtil.a("HwActivityRecognitionManager", "onEnvironmentChanged()");
        }
    };
    private HwActivityRecognitionServiceConnection g = new HwActivityRecognitionServiceConnection() { // from class: kce.3
        @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection
        public void onServiceConnected() {
            kce.this.b = true;
            kce.this.h();
            LogUtil.a("HwActivityRecognitionManager", "HwActivityRecognitionServiceConnection onServiceConnected()");
            if (kce.this.j != null) {
                kce.this.j.b();
            }
        }

        @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection
        public void onServiceDisconnected() {
            kce.this.b = false;
            LogUtil.a("HwActivityRecognitionManager", "HwActivityRecognitionServiceConnection onServiceDisconnected()");
        }
    };
    private Context e = BaseApplication.getContext();

    private kce() {
    }

    public static kce b() {
        kce kceVar;
        synchronized (d) {
            if (f14270a == null) {
                f14270a = new kce();
            }
            kceVar = f14270a;
        }
        return kceVar;
    }

    public void d() {
        if (!e()) {
            LogUtil.h("HwActivityRecognitionManager", "startMotionDetector() isSupportActivityRecognitionCapability is null");
            return;
        }
        Object[] objArr = new Object[4];
        objArr[0] = "initMotionDetector() mStillActivityRecognition is:";
        objArr[1] = Boolean.valueOf(this.j == null);
        objArr[2] = " mIsConnectServiceSuccess:";
        objArr[3] = Boolean.valueOf(this.b);
        LogUtil.a("HwActivityRecognitionManager", objArr);
        HwActivityRecognition hwActivityRecognition = this.j;
        if (hwActivityRecognition == null || !this.b) {
            if (hwActivityRecognition != null) {
                g();
                this.j = null;
            }
            try {
                HwActivityRecognition hwActivityRecognition2 = new HwActivityRecognition(BaseApplication.getContext());
                this.j = hwActivityRecognition2;
                hwActivityRecognition2.b(this.c, this.g);
            } catch (SecurityException unused) {
                LogUtil.b("HwActivityRecognitionManager", "initMotionDetector() SecurityException");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("HwActivityRecognitionManager", "serviceConnectedEnable() start serviceConnectedEnable");
        e("android.activity_recognition.still", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        e("android.activity_recognition.still", 2, 2000000000L);
        e("android.activity_recognition.walking", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        e("android.activity_recognition.walking", 2, 2000000000L);
        e("android.activity_recognition.fast_walking", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        e("android.activity_recognition.fast_walking", 2, 2000000000L);
        e("android.activity_recognition.running", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        e("android.activity_recognition.running", 2, 2000000000L);
        j();
    }

    private void j() {
        e("android.activity_recognition.on_bicycle", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        e("android.activity_recognition.on_bicycle", 2, 2000000000L);
        e("android.activity_recognition.in_vehicle", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        e("android.activity_recognition.in_vehicle", 2, 2000000000L);
        e("android.activity_recognition.high_speed_rail", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        e("android.activity_recognition.high_speed_rail", 2, 2000000000L);
    }

    private void g() {
        LogUtil.a("HwActivityRecognitionManager", "disconnectDisableActivityEvent()");
        try {
            d("android.activity_recognition.still");
            d("android.activity_recognition.walking");
            d("android.activity_recognition.running");
            d("android.activity_recognition.in_vehicle");
            d("android.activity_recognition.on_bicycle");
            d("android.activity_recognition.fast_walking");
            d("android.activity_recognition.high_speed_rail");
            this.j.c();
        } catch (SecurityException unused) {
            LogUtil.b("HwActivityRecognitionManager", "disconnectDisableActivityEvent() SecurityException");
        } catch (Exception unused2) {
            LogUtil.b("HwActivityRecognitionManager", "disconnectDisableActivityEvent() exception");
        }
    }

    private void e(String str, int i, long j) {
        HwActivityRecognition hwActivityRecognition = this.j;
        if (hwActivityRecognition == null) {
            LogUtil.h("HwActivityRecognitionManager", "enableActivityEvent() mStillActivityRecognition is null");
        } else {
            LogUtil.a("HwActivityRecognitionManager", "enableActivityEvent() activityStatus:", str, ", type:", Integer.valueOf(i), ", delayTime:", Long.valueOf(j), ", isSuccess:", Boolean.valueOf(hwActivityRecognition.d(str, i, j)));
        }
    }

    private void d(String str) {
        HwActivityRecognition hwActivityRecognition = this.j;
        if (hwActivityRecognition == null) {
            LogUtil.h("HwActivityRecognitionManager", "disableActivityEvent() mStillActivityRecognition is null");
            return;
        }
        LogUtil.a("HwActivityRecognitionManager", "disableActivityEvent() activityStatus:", str, ", isDisableActivityEnter:", Boolean.valueOf(hwActivityRecognition.b(str, 1)), ", isDisableActivityExit:", Boolean.valueOf(this.j.b(str, 2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        LogUtil.a("HwActivityRecognitionManager", "activityChanged() ", str, " ", Integer.valueOf(i));
        int i2 = 1;
        if (!"android.activity_recognition.still".equals(str) || i != 1) {
            if ("android.activity_recognition.still".equals(str) && i == 2) {
                i2 = 2;
            } else if ("android.activity_recognition.walking".equals(str) && i == 1) {
                i2 = 3;
            } else if ("android.activity_recognition.walking".equals(str) && i == 2) {
                i2 = 4;
            } else if ("android.activity_recognition.running".equals(str) && i == 1) {
                i2 = 5;
            } else if ("android.activity_recognition.running".equals(str) && i == 2) {
                i2 = 6;
            } else if ("android.activity_recognition.on_bicycle".equals(str) && i == 1) {
                i2 = 7;
            } else if ("android.activity_recognition.on_bicycle".equals(str) && i == 2) {
                i2 = 8;
            } else {
                i2 = ("android.activity_recognition.in_vehicle".equals(str) && i == 1) ? 9 : b(str, i);
            }
        }
        c(i2);
    }

    private int b(String str, int i) {
        if ("android.activity_recognition.in_vehicle".equals(str) && i == 2) {
            return 10;
        }
        if ("android.activity_recognition.fast_walking".equals(str) && i == 1) {
            return 11;
        }
        if ("android.activity_recognition.fast_walking".equals(str) && i == 2) {
            return 12;
        }
        if ("android.activity_recognition.high_speed_rail".equals(str) && i == 1) {
            return 13;
        }
        if ("android.activity_recognition.high_speed_rail".equals(str) && i == 2) {
            return 14;
        }
        LogUtil.h("HwActivityRecognitionManager", "default");
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i) {
        LogUtil.a("HwActivityRecognitionManager", "activityExtendChange() ", str, " ", Integer.valueOf(i));
        int i2 = 1;
        if (!"android.activity_recognition.still".equals(str) || i != 1) {
            if ("android.activity_recognition.still".equals(str) && i == 2) {
                i2 = 2;
            } else if ("android.activity_recognition.walking".equals(str) && i == 1) {
                i2 = 3;
            } else if ("android.activity_recognition.walking".equals(str) && i == 2) {
                i2 = 4;
            } else if ("android.activity_recognition.running".equals(str) && i == 1) {
                i2 = 5;
            } else {
                i2 = ("android.activity_recognition.running".equals(str) && i == 2) ? 6 : c(str, i);
            }
        }
        c(i2);
    }

    private int c(String str, int i) {
        if ("android.activity_recognition.on_bicycle".equals(str) && i == 1) {
            return 7;
        }
        if ("android.activity_recognition.on_bicycle".equals(str) && i == 2) {
            return 8;
        }
        if ("android.activity_recognition.in_vehicle".equals(str) && i == 1) {
            return 9;
        }
        if ("android.activity_recognition.in_vehicle".equals(str) && i == 2) {
            return 10;
        }
        if ("android.activity_recognition.fast_walking".equals(str) && i == 1) {
            return 11;
        }
        if ("android.activity_recognition.fast_walking".equals(str) && i == 2) {
            return 12;
        }
        if ("android.activity_recognition.high_speed_rail".equals(str) && i == 1) {
            return 13;
        }
        if ("android.activity_recognition.high_speed_rail".equals(str) && i == 2) {
            return 14;
        }
        LogUtil.h("HwActivityRecognitionManager", "default");
        return 0;
    }

    public boolean e() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", "HwActivityRecognitionManager");
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        if (deviceCapability == null) {
            LogUtil.h("HwActivityRecognitionManager", "isSupportActivityRecognitionCapability() Capability is null");
            return false;
        }
        if (deviceCapability.isSupportActivityRecognitionStatus()) {
            return true;
        }
        LogUtil.h("HwActivityRecognitionManager", "isSupportActivityRecognitionCapability() has not Capability");
        return false;
    }

    public void c(int i) {
        if (!e()) {
            LogUtil.h("HwActivityRecognitionManager", "sendActivityRecognitionStatus() has not Capability");
        } else {
            LogUtil.a("HwActivityRecognitionManager", "sendActivityRecognitionStatus() status:", Integer.valueOf(i));
            a(i);
        }
    }

    public void c() {
        if (this.j != null) {
            g();
            this.j = null;
        }
        a();
    }

    public static void a() {
        synchronized (d) {
            f14270a = null;
        }
    }

    public void a(int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(24);
        deviceCommand.setCommandID(9);
        String e = cvx.e(i);
        String e2 = cvx.e(e.length() / 2);
        String e3 = cvx.e(1);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e3);
        sb.append(e2);
        sb.append(e);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("HwActivityRecognitionManager", "enter sendCommand value :", Integer.valueOf(i), "sendCommand() deviceCommand:", deviceCommand.toString());
        jsz.b(this.e).sendDeviceData(deviceCommand);
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.a("HwActivityRecognitionManager", "notifyDeviceReceiveSuccess");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(24);
        deviceCommand.setCommandID(8);
        byte[] b = b(100000);
        deviceCommand.setDataLen(b.length);
        deviceCommand.setDataContent(b);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(this.e).sendDeviceData(deviceCommand);
    }

    private byte[] b(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(cvx.e(127) + cvx.e(4) + cvx.b(i));
        String sb2 = sb.toString();
        LogUtil.a("HwActivityRecognitionManager", "packageCommand（） Code Command :", sb2);
        return cvx.a(sb2);
    }
}
