package defpackage;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.device.wifi.entity.model.Entity;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.interfaces.ScanDeviceCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.profile.profile.ProfileExtendConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class cuh {
    private static volatile cuh b;
    private static final Object d = new Object();
    private csp c;
    private ScanDeviceCallbackInterface h;
    private BaseCallbackInterface j;
    private ctn k;
    private d l;
    private int g = 5;

    /* renamed from: a, reason: collision with root package name */
    private boolean f11480a = false;
    private dcz i = null;
    private CountDownTimer f = new CountDownTimer(60000, ProfileExtendConstants.TIME_OUT) { // from class: cuh.5
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            cpw.a(false, "ScanManager", "mScanDeviceWifiStatusTimer: onTick");
            String c = cuh.this.c.c(cuh.this.e());
            if (cuh.this.j != null) {
                cuh.this.j.onSuccess(c);
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            cpw.a(false, "ScanManager", "mScanDeviceWifiStatusTimer: onFinish");
            if (cuh.this.h != null) {
                cuh.this.h.onDeviceDiscoveryFinished();
            }
        }
    };
    private csv e = new csv();

    static /* synthetic */ int h(cuh cuhVar) {
        int i = cuhVar.g - 1;
        cuhVar.g = i;
        return i;
    }

    private cuh(Context context) {
        this.l = null;
        this.c = csp.c(context);
        if (this.l == null) {
            HandlerThread handlerThread = new HandlerThread("wifi_scan");
            handlerThread.start();
            this.l = new d(this, handlerThread.getLooper());
        }
    }

    private void a(dcz dczVar) {
        this.i = dczVar;
    }

    public static cuh e(Context context) {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new cuh(context);
                }
            }
        }
        return b;
    }

    public void d(dcz dczVar, int i, ScanDeviceCallbackInterface scanDeviceCallbackInterface) {
        a(dczVar);
        e(i, scanDeviceCallbackInterface);
    }

    private void e(int i, ScanDeviceCallbackInterface scanDeviceCallbackInterface) {
        cpw.a(false, "ScanManager", "scanDeviceWifiAp: in");
        if (scanDeviceCallbackInterface == null) {
            return;
        }
        this.h = scanDeviceCallbackInterface;
        this.g = i;
        d dVar = this.l;
        if (dVar != null) {
            dVar.removeMessages(1001);
            this.l.removeMessages(1000);
            this.l.sendEmptyMessage(1000);
        }
    }

    static class d extends StaticHandler<cuh> {
        d(cuh cuhVar, Looper looper) {
            super(cuhVar, looper);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: MO_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(cuh cuhVar, Message message) {
            if (cuhVar == null || message == null) {
                cpw.a(false, "ScanManager", "ScanDevice TimerHandler object or msg is null");
                return;
            }
            int i = message.what;
            if (i == 1000) {
                cpw.a(false, "ScanManager", "ScanDevice TimerHandler: start...");
                cub.l(cpp.a());
                if (cuhVar.f11480a) {
                    cuhVar.b();
                }
                sendEmptyMessageDelayed(1001, 3000L);
                return;
            }
            if (i == 1001) {
                cpw.a(false, "ScanManager", "ScanDevice TimerHandler: query...");
                List<ctn> e = cuhVar.c.e(cuhVar.i);
                if (cuhVar.h != null && e.size() > 0) {
                    cuhVar.h.onDeviceDiscovered(e);
                }
                if (cuh.h(cuhVar) > 0) {
                    sendEmptyMessageDelayed(1000, 1000L);
                    return;
                }
                cpw.a(false, "ScanManager", "ScanDevice TimerHandler Finished!");
                if (cuhVar.h != null) {
                    cuhVar.h.onDeviceDiscoveryFinished();
                    return;
                }
                return;
            }
            cpw.d(false, "ScanManager", "TimerHandler default");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        cta ctaVar = new cta();
        ctaVar.c("1.0");
        ctaVar.b(ctx.c(8));
        this.e.a(ctaVar, new Entity.EntityResponseCallback() { // from class: cuh.1
            @Override // com.huawei.health.device.wifi.entity.model.Entity.EntityResponseCallback
            public void onResponse(ctc ctcVar) {
                cth cthVar;
                cpw.a(false, "ScanManager", "startScanDeviceCoap: onResponse");
                if (ctcVar != null) {
                    if (ctcVar instanceof cth) {
                        cthVar = (cth) ctcVar;
                        cpw.c(true, "ScanManager", "startScanDeviceCoap: response ", cthVar.toString());
                    } else {
                        cthVar = null;
                    }
                    ctn a2 = cuh.this.c.a(cthVar);
                    if (a2.e() != null && !a2.e().isEmpty()) {
                        cpw.c(true, "ScanManager", "startScanDeviceCoap: device has been registered, deviceId is ", a2.e(), ",Scan device info is", a2);
                        return;
                    }
                    if (a2.c() == null) {
                        return;
                    }
                    ArrayList arrayList = new ArrayList(16);
                    if (cuh.this.i == null || cuh.this.i.w().a(a2.g())) {
                        arrayList.add(a2);
                    }
                    cpw.a(false, "ScanManager", "startScanDeviceCoap infos size ", Integer.valueOf(arrayList.size()));
                    if (arrayList.size() <= 0 || cuh.this.h == null) {
                        return;
                    }
                    cuh.this.h.onDeviceDiscovered(arrayList);
                    return;
                }
                cpw.d(false, "ScanManager", "startScanDeviceCoap: response is null");
            }
        });
    }

    public void a() {
        d dVar = this.l;
        if (dVar != null) {
            dVar.removeMessages(1000);
            this.l.removeMessages(1001);
            this.l.removeCallbacksAndMessages(null);
            this.g = 0;
            this.h = null;
        }
    }

    public void c(ctn ctnVar) {
        this.k = ctnVar;
    }

    public ctn e() {
        return this.k;
    }

    public void c(ctn ctnVar, BaseCallbackInterface baseCallbackInterface) {
        cpw.a(false, "ScanManager", "Scan the specified device, temporarily set to 60 seconds, 1.5 times/sec to perform the check once");
        this.j = baseCallbackInterface;
        c(ctnVar);
        CountDownTimer countDownTimer = this.f;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    public void c() {
        CountDownTimer countDownTimer = this.f;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
