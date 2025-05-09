package defpackage;

import android.content.Context;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs_credential.c;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class twr {

    /* renamed from: a, reason: collision with root package name */
    public final Map<Integer, c> f17397a = new HashMap();
    public CredentialClient b;
    public Context c;
    public NetworkCapability d;
    public txl e;
    public int h;

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        public Context f17398a;
        public CredentialClient b;
        public txl c;
        public NetworkCapability e;
    }

    public final void d() throws twc {
        this.f17397a.put(0, new twm(this.b, this.c, this.d, this.e));
        this.f17397a.put(1, new txr(this.b, this.c, this.d));
        this.f17397a.put(2, new txn(this.b, this.c, this.d));
        this.f17397a.put(3, new txf(this.b, this.c, this.d));
    }

    public String b() {
        int i = this.h;
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "" : "Huks_EC" : "AndroidKS_EC" : "AndroidKS" : "Kid";
    }

    public Credential a(int i, String str, String str2, String str3, String str4, twr twrVar) throws twc {
        this.h = i;
        c cVar = this.f17397a.get(Integer.valueOf(i));
        if (cVar != null) {
            return cVar.a(str, str2, str3, str4, twrVar);
        }
        throw new twc(2001L, "applyCredential get inner error, apply flag not found.");
    }

    public twr(e eVar) throws twc {
        this.b = eVar.b;
        this.c = eVar.f17398a;
        this.d = eVar.e;
        this.e = eVar.c;
        d();
    }
}
