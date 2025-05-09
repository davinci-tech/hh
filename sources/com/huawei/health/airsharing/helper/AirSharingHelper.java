package com.huawei.health.airsharing.helper;

import android.content.Context;
import android.os.CountDownTimer;
import android.provider.Settings;
import com.huawei.android.airsharing.api.Event;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.android.airsharing.api.ProjectionDevice;
import com.huawei.android.airsharing.client.PlayerClient;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes3.dex */
public class AirSharingHelper {
    private static volatile AirSharingHelper b;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private EventCallback f2189a;
    private Context c;
    private PlayerClient s;
    private boolean j = false;
    private String n = null;
    private ProjectionDevice o = null;
    private boolean m = false;
    private boolean f = false;
    private boolean k = false;
    private boolean g = false;
    private int p = -1;
    private boolean l = false;
    private boolean i = true;
    private CountDownTimer e = new CountDownTimer(120000, 1000) { // from class: com.huawei.health.airsharing.helper.AirSharingHelper.4
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            LogUtil.a("Distribute_AirSharingHelper", "PreSearch CountDownTimer Finished");
            AirSharingHelper.this.f = false;
            AirSharingHelper.this.g = false;
        }
    };
    private IEventListener h = new IEventListener() { // from class: com.huawei.health.airsharing.helper.AirSharingHelper.1
        @Override // com.huawei.android.airsharing.api.IEventListener
        public boolean onEvent(int i, String str) {
            LogUtil.a("Distribute_AirSharingHelper", "onEvent...id ", Integer.valueOf(i), "---string ", str);
            if (i == 3005) {
                AirSharingHelper.this.j = false;
                if (AirSharingHelper.this.f) {
                    AirSharingHelper.this.o();
                }
                if (AirSharingHelper.this.l) {
                    AirSharingHelper.this.r();
                    AirSharingHelper.this.l = false;
                    AirSharingHelper.this.k();
                }
            } else if (i == 3006 && AirSharingHelper.this.g && !AirSharingHelper.this.j && !AirSharingHelper.this.f) {
                AirSharingHelper.this.m();
            }
            if (AirSharingHelper.this.f2189a != null) {
                LogUtil.c("Distribute_AirSharingHelper", "mEventCallback onEvent isSuccess = ", Boolean.valueOf(AirSharingHelper.this.f2189a.onEvent(i, str)));
            }
            return false;
        }

        @Override // com.huawei.android.airsharing.api.IEventListener
        public void onDisplayUpdate(int i, String str, String str2, int i2) {
            LogUtil.a("Distribute_AirSharingHelper", "onDisplayUpdate...");
            if (AirSharingHelper.this.f2189a != null) {
                AirSharingHelper.this.f2189a.onDisplayUpdate(i, str, str2, i2);
            }
        }

        @Override // com.huawei.android.airsharing.api.IEventListener
        public void onMirrorUpdate(int i, String str, String str2, int i2, boolean z) {
            LogUtil.a("Distribute_AirSharingHelper", "onMirrorUpdate...");
            if (AirSharingHelper.this.f2189a != null) {
                AirSharingHelper.this.f2189a.onMirrorUpdate(i, str, str2, i2, z);
            }
        }

        @Override // com.huawei.android.airsharing.api.IEventListener
        public void onProjectionDeviceUpdate(int i, ProjectionDevice projectionDevice) {
            LogUtil.a("Distribute_AirSharingHelper", "onProjectionDeviceUpdate...id ", Integer.valueOf(i));
            AirSharingHelper.this.c(i, projectionDevice);
            if (AirSharingHelper.this.f2189a != null) {
                AirSharingHelper.this.f2189a.onProjectionDeviceUpdate(i, projectionDevice);
            }
        }

        @Override // com.huawei.android.airsharing.api.IEventListener
        public void onEventHandle(Event event) {
            LogUtil.a("Distribute_AirSharingHelper", "onEventHandle...id ", Integer.valueOf(event.getEventId()));
        }
    };

    public interface EventCallback {
        void onDisplayUpdate(int i, String str, String str2, int i2);

        boolean onEvent(int i, String str);

        void onMirrorUpdate(int i, String str, String str2, int i2, boolean z);

        void onProjectionDeviceUpdate(int i, ProjectionDevice projectionDevice);
    }

    private AirSharingHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, ProjectionDevice projectionDevice) {
        String str;
        if (projectionDevice == null) {
            LogUtil.h("Distribute_AirSharingHelper", "device is null");
        }
        switch (i) {
            case 3001:
                LogUtil.a("Distribute_AirSharingHelper", "Device add:", projectionDevice.getIndication());
                int capability = projectionDevice.getCapability();
                LogUtil.a("Distribute_AirSharingHelper", "device capability:", Integer.valueOf(capability));
                if ((capability & 1) == 1 && (str = this.n) != null && this.f && str.equals(projectionDevice.getIndication())) {
                    LogUtil.a("Distribute_AirSharingHelper", "is last device exist:", this.n);
                    this.m = true;
                    this.o = projectionDevice;
                    break;
                }
                break;
            case 3002:
                LogUtil.a("Distribute_AirSharingHelper", "Device remove");
                break;
            case 3003:
                e(projectionDevice);
                break;
            case IEventListener.EVENT_ID_DEVICE_CONN_FAIL /* 3004 */:
                LogUtil.a("Distribute_AirSharingHelper", "Device connect failed");
                if (this.l) {
                    r();
                    this.l = false;
                } else if (this.f) {
                    o();
                }
                this.j = false;
                this.g = false;
                k();
                break;
        }
    }

    private void e(ProjectionDevice projectionDevice) {
        LogUtil.a("Distribute_AirSharingHelper", "Device connect succeed", projectionDevice.getIndication());
        if (this.l) {
            SharedPreferenceManager.e(this.c, Integer.toString(1019), "projection_last_device_id", projectionDevice.getIndication(), new StorageParams());
        } else if (this.f) {
            o();
        }
        this.j = true;
        this.g = false;
    }

    public static AirSharingHelper e() {
        LogUtil.a("Distribute_AirSharingHelper", "getInstance...");
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new AirSharingHelper();
                }
            }
        }
        return b;
    }

    public boolean e(Context context) {
        LogUtil.a("Distribute_AirSharingHelper", "init...");
        if (this.k) {
            return true;
        }
        this.c = context;
        PlayerClient a2 = PlayerClient.a();
        this.s = a2;
        if (a2 == null) {
            LogUtil.h("Distribute_AirSharingHelper", "playerclient is null");
            return false;
        }
        try {
            if (!a2.init(this.c)) {
                LogUtil.h("Distribute_AirSharingHelper", "init failed ....");
                return false;
            }
        } catch (SecurityException e) {
            LogUtil.b("Distribute_AirSharingHelper", "init failed ", LogAnonymous.b((Throwable) e));
        }
        this.k = true;
        LogUtil.a("Distribute_AirSharingHelper", "init success 01");
        this.s.d(this.h);
        this.s.subscribServers("7");
        LogUtil.a("Distribute_AirSharingHelper", "init success 02");
        return true;
    }

    public void e(EventCallback eventCallback) {
        LogUtil.a("Distribute_AirSharingHelper", "setEventCallback...");
        this.f2189a = eventCallback;
    }

    public void e(String str) {
        if (i()) {
            LogUtil.a("Distribute_AirSharingHelper", "startPreSearch.", str);
            this.n = str;
            this.f = true;
            LogUtil.a("Distribute_AirSharingHelper", "PreSearch CountDownTimer start...");
            this.e.start();
            m();
        }
    }

    public void o() {
        LogUtil.a("Distribute_AirSharingHelper", "stopPreSearch...");
        this.e.cancel();
        l();
    }

    public void n() {
        LogUtil.a("Distribute_AirSharingHelper", "resetPreSearch...");
        this.m = false;
    }

    public boolean j() {
        LogUtil.a("Distribute_AirSharingHelper", "isInPreSearch:", Boolean.valueOf(this.f));
        return this.f;
    }

    public boolean h() {
        LogUtil.a("Distribute_AirSharingHelper", "isLastDeviceExist:", Boolean.valueOf(this.m), Boolean.valueOf(this.f));
        return this.m && this.f;
    }

    public ProjectionDevice b() {
        LogUtil.a("Distribute_AirSharingHelper", "getLastDevice...");
        return this.o;
    }

    public void m() {
        LogUtil.a("Distribute_AirSharingHelper", "startScanDevice...");
        PlayerClient playerClient = this.s;
        if (playerClient != null) {
            if (this.g) {
                playerClient.e(true);
            }
            this.s.a(true);
            this.g = true;
            if (this.f) {
                return;
            }
            q();
        }
    }

    public void l() {
        LogUtil.a("Distribute_AirSharingHelper", "stopScanDevice...");
        PlayerClient playerClient = this.s;
        if (playerClient != null) {
            playerClient.e(true);
            this.g = false;
            if (!this.f) {
                k();
            }
            this.f = false;
        }
    }

    private void p() {
        LogUtil.a("Distribute_AirSharingHelper", "releaseResource...");
        IEventListener iEventListener = this.h;
        if (iEventListener != null) {
            this.s.e(iEventListener);
            this.s.unsubscribServers();
        }
        this.s.deInit();
    }

    public boolean g() {
        PlayerClient playerClient = this.s;
        if (playerClient != null) {
            boolean h = playerClient.h();
            LogUtil.a("Distribute_AirSharingHelper", "isConnected:", Boolean.valueOf(h));
            return h;
        }
        LogUtil.a("Distribute_AirSharingHelper", "isConnected:mPlayerClient is null");
        return false;
    }

    public void a() {
        LogUtil.a("Distribute_AirSharingHelper", "disconnected...");
        PlayerClient playerClient = this.s;
        if (playerClient == null || !this.l) {
            return;
        }
        playerClient.e();
    }

    public void c() {
        LogUtil.a("Distribute_AirSharingHelper", "forceDisconnect...");
        PlayerClient playerClient = this.s;
        if (playerClient != null) {
            playerClient.e();
        }
    }

    public void b(ProjectionDevice projectionDevice) {
        if (projectionDevice == null) {
            LogUtil.b("Distribute_AirSharingHelper", "device is null");
            return;
        }
        LogUtil.a("Distribute_AirSharingHelper", "connectToDevice:", projectionDevice.getDeviceName(), projectionDevice);
        if (this.s != null) {
            int s = s();
            this.p = s;
            LogUtil.a("Distribute_AirSharingHelper", "mOriginMode:", Integer.valueOf(s));
            if (this.p != 0) {
                u();
            }
            this.s.e(projectionDevice);
            q();
            this.l = true;
        }
    }

    public String f() {
        LogUtil.a("Distribute_AirSharingHelper", "getTargetDevName...");
        PlayerClient playerClient = this.s;
        if (playerClient == null) {
            return null;
        }
        String c = playerClient.c();
        LogUtil.a("Distribute_AirSharingHelper", "Target device name is ", c);
        return c;
    }

    private void u() {
        LogUtil.a("Distribute_AirSharingHelper", "setPhoneMode:", 0);
        Context context = this.c;
        if (context != null) {
            Settings.Secure.putInt(context.getContentResolver(), "selected-proj-mode", 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogUtil.a("Distribute_AirSharingHelper", "setOriginMode:", Integer.valueOf(this.p));
        Context context = this.c;
        if (context != null) {
            Settings.Secure.putInt(context.getContentResolver(), "selected-proj-mode", this.p);
        }
    }

    private int s() {
        Context context = this.c;
        if (context != null) {
            return Settings.Secure.getInt(context.getContentResolver(), "selected-proj-mode", -1);
        }
        return 0;
    }

    private void q() {
        LogUtil.a("Distribute_AirSharingHelper", "lightWirelessProjectionIcon...");
        Context context = this.c;
        if (context != null) {
            Settings.Global.putInt(context.getContentResolver(), "wireless_projection_state", 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("Distribute_AirSharingHelper", "extinguishWirelessProjectionIcon...");
        Context context = this.c;
        if (context != null) {
            Settings.Global.putInt(context.getContentResolver(), "wireless_projection_state", 0);
        }
    }

    public void d(boolean z) {
        LogUtil.a("Distribute_AirSharingHelper", "setIsCanPreSearch:", Boolean.valueOf(z));
        this.i = z;
    }

    public void d() {
        LogUtil.a("Distribute_AirSharingHelper", "finish...");
        if (this.l) {
            k();
            r();
        }
        if (g()) {
            a();
        }
        if (this.f) {
            o();
        }
        if (this.g) {
            l();
        }
        p();
        this.k = false;
        t();
        this.m = false;
        this.n = null;
        this.o = null;
        this.l = false;
        this.f = false;
        this.g = false;
        this.j = false;
        this.f2189a = null;
    }

    private static void t() {
        synchronized (d) {
            b = null;
        }
    }

    public boolean i() {
        LogUtil.a("Distribute_AirSharingHelper", "isCanPreSearch...");
        return this.i && !this.f;
    }

    public void a(boolean z) {
        LogUtil.a("Distribute_AirSharingHelper", "setIsInit:", Boolean.valueOf(z));
        this.k = z;
        if (z) {
            return;
        }
        p();
    }
}
