package defpackage;

import com.huawei.multisimsdk.attacheddevicemanager.common.IAttachedDeviceManagerCallback;
import com.huawei.multisimservice.model.SimInfo;
import java.util.List;

/* loaded from: classes5.dex */
public class lnd {

    /* renamed from: a, reason: collision with root package name */
    private List<SimInfo> f14777a;
    private String b;
    private String c;
    private IAttachedDeviceManagerCallback d;
    private String e;
    private int j;

    public IAttachedDeviceManagerCallback d() {
        return this.d;
    }

    public int e() {
        return this.j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        lnd lndVar = (lnd) obj;
        if (this.j != lndVar.j) {
            return false;
        }
        IAttachedDeviceManagerCallback iAttachedDeviceManagerCallback = this.d;
        if (iAttachedDeviceManagerCallback == null ? lndVar.d != null : !iAttachedDeviceManagerCallback.equals(lndVar.d)) {
            return false;
        }
        String str = this.b;
        if (str == null ? lndVar.b != null : !str.equals(lndVar.b)) {
            return false;
        }
        String str2 = this.e;
        if (str2 == null ? lndVar.e != null : !str2.equals(lndVar.e)) {
            return false;
        }
        String str3 = this.c;
        if (str3 == null ? lndVar.c != null : !str3.equals(lndVar.c)) {
            return false;
        }
        List<SimInfo> list = this.f14777a;
        return list != null ? list.equals(lndVar.f14777a) : lndVar.f14777a == null;
    }

    public int hashCode() {
        int i = this.j;
        IAttachedDeviceManagerCallback iAttachedDeviceManagerCallback = this.d;
        if (iAttachedDeviceManagerCallback != null) {
            i = (i * 31) + iAttachedDeviceManagerCallback.hashCode();
        }
        String str = this.b;
        if (str != null) {
            i = (i * 31) + str.hashCode();
        }
        String str2 = this.e;
        if (str2 != null) {
            i = (i * 31) + str2.hashCode();
        }
        String str3 = this.c;
        if (str3 != null) {
            i = (i * 31) + str3.hashCode();
        }
        List<SimInfo> list = this.f14777a;
        return list != null ? (i * 31) + list.hashCode() : i;
    }
}
