package defpackage;

import com.huawei.wearengine.p2p.ReceiverCallback;
import java.util.Objects;

/* loaded from: classes9.dex */
public class tqb {

    /* renamed from: a, reason: collision with root package name */
    private ReceiverCallback f17342a;
    private int b;
    private int e;

    public tqb(int i, int i2, ReceiverCallback receiverCallback) {
        this.e = i;
        this.b = i2;
        this.f17342a = receiverCallback;
    }

    public int b() {
        return this.e;
    }

    public int d() {
        return this.b;
    }

    public ReceiverCallback c() {
        return this.f17342a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof tqb) {
            tqb tqbVar = (tqb) obj;
            return this.e == tqbVar.e && this.b == tqbVar.b;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.e), Integer.valueOf(this.b), this.f17342a);
    }
}
