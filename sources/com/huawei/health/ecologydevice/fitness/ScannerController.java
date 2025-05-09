package com.huawei.health.ecologydevice.fitness;

import android.text.TextUtils;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cev;
import defpackage.cwz;
import defpackage.dcz;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class ScannerController {

    /* renamed from: a, reason: collision with root package name */
    private dcz f2285a;
    private e b;
    private cwz c;
    private String d;
    private String e;
    private ScanResultCallback i;

    public interface ScanResultCallback {
        void onResult(int i, HealthDevice healthDevice);
    }

    public ScannerController(dcz dczVar, String str) {
        this.f2285a = dczVar;
        this.e = str;
        this.b = new e();
    }

    public ScannerController(String str) {
        this(null, null);
        this.d = str;
    }

    class e implements IDeviceEventHandler {
        private e() {
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onDeviceFound(HealthDevice healthDevice) {
            LogUtil.a("ScannerController", "Callback, onDeviceFound device = ", healthDevice.getDeviceName());
            d(1, healthDevice);
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onScanFailed(int i) {
            LogUtil.c("ScannerController", "onScanFailed");
            if (i == 1) {
                d(2, null);
            }
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onStateChanged(int i) {
            if (i == 1001) {
                d(3, null);
            }
        }

        private void d(int i, HealthDevice healthDevice) {
            if (ScannerController.this.i != null) {
                ScannerController.this.i.onResult(i, healthDevice);
            }
        }
    }

    public void c() {
        this.c = new cwz();
        if (this.f2285a != null) {
            LogUtil.a("ScannerController", "productInfo is not null");
            if (this.f2285a.w() == null) {
                this.f2285a.a(ScanFilter.b("moredevice", this.e, ScanFilter.MatchRule.FRONT));
            }
            this.c.d(this.f2285a.x(), this.f2285a.w(), this.b);
            return;
        }
        if (!TextUtils.isEmpty(this.d)) {
            LogUtil.a("ScannerController", "scanDevice from mac");
            cev.b bVar = new cev.b();
            bVar.a(1);
            bVar.c(10L, TimeUnit.SECONDS);
            this.c.d(bVar.c(), ScanFilter.b(null, this.d, ScanFilter.MatchRule.STRICT), this.b);
            return;
        }
        LogUtil.a("ScannerController", "for more heart rate device");
        cev.b bVar2 = new cev.b();
        bVar2.a(1);
        bVar2.c(10L, TimeUnit.SECONDS);
        this.c.d(bVar2.c(), ScanFilter.b("moredevice", this.e, ScanFilter.MatchRule.FRONT), this.b);
    }

    public void a() {
        this.b = null;
        cwz cwzVar = this.c;
        if (cwzVar != null) {
            cwzVar.c();
        }
    }

    public void a(ScanResultCallback scanResultCallback) {
        this.i = scanResultCallback;
    }
}
