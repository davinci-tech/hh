package com.huawei.maps.offlinedata.service.device.receivers;

import com.huawei.maps.offlinedata.service.device.consts.a;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class g implements d {

    /* renamed from: a, reason: collision with root package name */
    private final int f6494a;
    private int b;

    public g(int i, int i2) {
        this.f6494a = i;
        this.b = i2;
    }

    @Override // com.huawei.maps.offlinedata.service.device.receivers.d
    public void a(int i, byte[] bArr) {
        try {
            com.huawei.maps.offlinedata.service.device.tlvtools.d a2 = com.huawei.maps.offlinedata.service.device.tlvtools.e.a(bArr);
            if (a2 == null) {
                com.huawei.maps.offlinedata.utils.g.c("PoliticalPerspectiveReceiver", "politicalPerspective TlvByteFather is null");
                return;
            }
            String str = "";
            for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : a2.a()) {
                if (fVar.a() == 2) {
                    str = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(fVar.b());
                    com.huawei.maps.offlinedata.utils.g.a("PoliticalPerspectiveReceiver", "handlePoliticalPerspectiveResponse politicalPerspective.");
                } else if (fVar.a() == 19) {
                    com.huawei.maps.offlinedata.utils.g.a("PoliticalPerspectiveReceiver", "handlePoliticalPerspectiveResponse msgId:" + com.huawei.maps.offlinedata.service.device.tlvtools.a.b(fVar.b(), -1));
                } else if (fVar.a() == 20) {
                    com.huawei.maps.offlinedata.utils.g.a("PoliticalPerspectiveReceiver", "handlePoliticalPerspectiveResponse reviewNumberWatchMap.");
                } else {
                    com.huawei.maps.offlinedata.utils.g.d("PoliticalPerspectiveReceiver", "handlePoliticalPerspectiveResponse type error:" + ((int) fVar.a()));
                }
            }
            if (str.equals(a.c.SUCCESS.a())) {
                com.huawei.maps.offlinedata.utils.g.a("PoliticalPerspectiveReceiver", "syc political perspective success. ");
            }
            if (str.equals(a.c.FAILURE.a())) {
                com.huawei.maps.offlinedata.utils.g.c("PoliticalPerspectiveReceiver", "syc political perspective failed, retryTimes is " + this.b);
                final Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() { // from class: com.huawei.maps.offlinedata.service.device.receivers.g.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        com.huawei.maps.offlinedata.service.device.d.a().a(g.this.f6494a, g.this.b);
                        timer.cancel();
                    }
                };
                int i2 = this.b;
                if (i2 >= 3) {
                    com.huawei.maps.offlinedata.utils.g.d("PoliticalPerspectiveReceiver", "syc political perspective failed after retry, retryTimes is " + this.b);
                } else {
                    this.b = i2 + 1;
                    timer.schedule(timerTask, 3000L);
                }
            }
        } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g unused) {
            com.huawei.maps.offlinedata.utils.g.d("PoliticalPerspectiveReceiver", "catch TlvException");
        }
    }
}
