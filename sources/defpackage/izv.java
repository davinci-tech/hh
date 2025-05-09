package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.linklayer.DataReceivedCallback;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class izv {

    /* renamed from: a, reason: collision with root package name */
    private DataReceivedCallback f13696a;
    private izs b;
    private int d;
    private izj e = new izj();

    public izv(int i, DataReceivedCallback dataReceivedCallback) {
        this.d = i;
        this.f13696a = dataReceivedCallback;
        this.b = new izs(BaseApplication.e(), this.d);
    }

    public void e(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length;
        String spliceMTUPackage = this.b.spliceMTUPackage(length, bArr);
        if (spliceMTUPackage == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtLinkLayerParser", "dataContent is null.");
            return;
        }
        if (spliceMTUPackage.length() != 0) {
            List<izj> parseResponsePacket = this.b.parseResponsePacket(length, blq.a(spliceMTUPackage));
            if (parseResponsePacket == null) {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtLinkLayerParser", "sliceResponseList obtain error");
                return;
            } else {
                b(parseResponsePacket);
                return;
            }
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtLinkLayerParser", "package and length less than MTU threshold.");
    }

    private void b(List<izj> list) {
        for (izj izjVar : list) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BtLinkLayerParser", "slice info : ", Boolean.valueOf(izjVar.d()), ". success info : ", Boolean.valueOf(izjVar.c()), ". receivedAll info : ", Boolean.valueOf(izjVar.b()));
            if (!izjVar.d() && izjVar.c()) {
                a(izjVar.a(), izjVar.e());
            } else {
                if (this.e.a() != 0) {
                    byte[] bArr = new byte[this.e.e().length + izjVar.e().length];
                    System.arraycopy(this.e.e(), 0, bArr, 0, this.e.e().length);
                    System.arraycopy(izjVar.e(), 0, bArr, this.e.e().length, izjVar.e().length);
                    this.e.d(bArr);
                } else {
                    this.e.d(izjVar.e());
                }
                this.e.c(izjVar.a() + this.e.a());
                this.e.a(izjVar.c());
                this.e.b(izjVar.b());
                if (this.e.b()) {
                    a(this.e.a(), this.e.e());
                    e();
                }
            }
        }
    }

    private void a(int i, byte[] bArr) {
        DataReceivedCallback dataReceivedCallback = this.f13696a;
        if (dataReceivedCallback != null) {
            dataReceivedCallback.onDataReceived(i, bArr);
        }
    }

    private void e() {
        this.e.b(false);
        this.e.c(0);
        this.e.d(null);
        this.e.e(false);
    }

    public ArrayList<byte[]> c(byte[] bArr) {
        return this.b.wrapCommandPackets(bArr.length, bArr);
    }
}
