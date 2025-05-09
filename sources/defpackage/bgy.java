package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes3.dex */
class bgy {

    /* renamed from: a, reason: collision with root package name */
    private String f368a;
    private String c;
    private biw d;
    private biy e = new biy();

    bgy(biw biwVar) {
        this.d = biwVar;
    }

    HandshakeCommandBase b(HandshakeCommandBase handshakeCommandBase, int i) {
        byte[] e = e();
        if (e == null || this.d == null) {
            LogUtil.e("DealWithHiChain", "startAuthentic randB is null");
            this.e.c(13);
            this.e.a(i);
            return handshakeCommandBase;
        }
        String d = blq.d(e);
        this.f368a = d;
        this.c = bhh.c(this.d, d, "0100");
        int i2 = this.d.i();
        if (!bjn.c(i2) && !bjr.c(i2) && !bjk.d(i2)) {
            LogUtil.c("DealWithHiChain", "Device support traditional authentication.");
            if (CommonUtil.aq()) {
                LogUtil.c("DealWithHiChain", "dealWithHiChainModule is debug, use old.");
                return e(handshakeCommandBase, i);
            }
            return d(handshakeCommandBase, i);
        }
        LogUtil.c("DealWithHiChain", "Device support HiChain or HiChainLite.");
        this.e.c(15);
        this.e.a(100000);
        return new bho(i2, this.f368a, this.d);
    }

    private HandshakeCommandBase e(HandshakeCommandBase handshakeCommandBase, int i) {
        if (this.d.a() != 0) {
            LogUtil.c("DealWithHiChain", "Device need authentic Application after check device available.");
            return d(handshakeCommandBase, i);
        }
        LogUtil.c("DealWithHiChain", "Device do not need authentic after check device available.");
        this.e.c(15);
        this.e.a(100000);
        return new bhk(false);
    }

    biy b() {
        return this.e;
    }

    private HandshakeCommandBase d(HandshakeCommandBase handshakeCommandBase, int i) {
        LogUtil.c("DealWithHiChain", "Enter startAuthenticBTDevice().");
        if (this.d.j() != null && this.d.j().length() != 32) {
            LogUtil.c("DealWithHiChain", "RandA parameter is incorrect so stop authentic");
            this.e.c(13);
            this.e.a(i);
            return handshakeCommandBase;
        }
        if (!TextUtils.isEmpty(this.c)) {
            this.e.c(15);
            this.e.a(100000);
            return new bgs(this.c, this.f368a, this.d);
        }
        this.e.c(13);
        this.e.a(i);
        return handshakeCommandBase;
    }

    private byte[] e() {
        try {
            byte[] d = bgv.d(16);
            if (d == null) {
                LogUtil.e("DealWithHiChain", "generateRandomBytes fail.");
            }
            return d;
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.e("DealWithHiChain", "generateRandomBytes exception");
            return null;
        }
    }
}
