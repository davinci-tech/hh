package defpackage;

import com.huawei.wisesecurity.ucs.credential.outer.GrsCapability;
import com.huawei.wisesecurity.ucs_credential.o;

/* loaded from: classes7.dex */
public class twx implements o {
    public GrsCapability e;

    @Override // com.huawei.wisesecurity.ucs_credential.o
    public String b() {
        return e("HA");
    }

    @Override // com.huawei.wisesecurity.ucs_credential.o
    public String a(String str, String str2) {
        return e("CDN") + "tsms/" + str + "/" + str2;
    }

    public final String e(String str) {
        String synGetGrsUrl = this.e.synGetGrsUrl("com.huawei.tsms", str);
        twb.a("OuterGrsUrlImpl", "synGetGrsUrl : {0}", synGetGrsUrl);
        return synGetGrsUrl;
    }

    @Override // com.huawei.wisesecurity.ucs_credential.o
    public String a() {
        return e("ROOT") + "/tsms/v2/credentials";
    }

    public twx(GrsCapability grsCapability) {
        this.e = grsCapability;
    }
}
