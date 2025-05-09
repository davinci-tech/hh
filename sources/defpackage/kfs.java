package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.HwEncryptUtil;
import health.compact.a.util.LogUtil;
import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public class kfs {

    /* renamed from: a, reason: collision with root package name */
    private int f14353a;
    private String d;
    private String e;

    public String c() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String b() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public int e() {
        return this.f14353a;
    }

    public void c(int i) {
        this.f14353a = i;
    }

    public String toString() {
        try {
            return "SyncWifiSendMessageEntity{wifiSsid='" + this.e + "', password='" + HwEncryptUtil.c(BaseApplication.getContext()).a(1, this.d) + "', encodeType=" + this.f14353a + '}';
        } catch (GeneralSecurityException unused) {
            LogUtil.c("SyncWifiSendMessageEntity", "decryptData is error");
            return "";
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return toString().equals(obj.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
