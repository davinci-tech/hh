package defpackage;

import android.content.Context;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs_credential.o;
import defpackage.twr;

/* loaded from: classes7.dex */
public class twt {

    /* renamed from: a, reason: collision with root package name */
    public txl f17399a;
    public NetworkCapability b;
    public Context c;
    public o d;
    public String e;
    public String h;
    public CredentialClient i;

    public final Credential b(int i, String str, String str2) throws twc {
        twr.e eVar = new twr.e();
        eVar.b = this.i;
        eVar.f17398a = this.c;
        eVar.c = this.f17399a;
        eVar.e = this.b;
        twr twrVar = new twr(eVar);
        try {
            return twrVar.a(i, this.d.a(), this.e, str, str2, twrVar);
        } finally {
            this.h = twrVar.b();
        }
    }

    public twt(CredentialClient credentialClient, Context context, NetworkCapability networkCapability, o oVar, String str) {
        this.i = credentialClient;
        this.c = context;
        this.b = networkCapability;
        this.e = str;
        this.d = oVar;
        this.f17399a = new txl(context, oVar, networkCapability);
    }
}
