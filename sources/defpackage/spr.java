package defpackage;

import android.text.TextUtils;
import com.huawei.unitedevice.p2p.P2pReceiver;
import java.util.Objects;

/* loaded from: classes9.dex */
public class spr {
    private String b;
    private P2pReceiver c;
    private String d;
    private String e;

    public spr(String str, String str2, String str3, P2pReceiver p2pReceiver) {
        this.d = str;
        this.b = str2;
        this.e = str3;
        this.c = p2pReceiver;
    }

    public String b() {
        return this.d;
    }

    public P2pReceiver e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof spr) {
            spr sprVar = (spr) obj;
            boolean equals = TextUtils.isEmpty(this.d) ? true : this.d.equals(sprVar.d);
            if (equals && !TextUtils.isEmpty(this.b)) {
                equals = this.b.equals(sprVar.b);
            }
            return (!equals || TextUtils.isEmpty(this.e)) ? equals : this.b.equals(sprVar.b);
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return Objects.hash(this.d, this.b, this.e);
    }
}
