package defpackage;

import android.content.Context;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.wisesecurity.ucs_credential.o;

/* loaded from: classes7.dex */
public class txh implements o {
    public GrsClient b;

    @Override // com.huawei.wisesecurity.ucs_credential.o
    public String b() {
        return e("HA");
    }

    @Override // com.huawei.wisesecurity.ucs_credential.o
    public String a(String str, String str2) {
        return e("CDN") + "tsms/" + str + "/" + str2;
    }

    public final String e(String str) {
        String synGetGrsUrl = this.b.synGetGrsUrl("com.huawei.tsms", str);
        twb.a("InnerGrsUrlImpl", "synGetGrsUrl : {0}", synGetGrsUrl);
        return synGetGrsUrl;
    }

    @Override // com.huawei.wisesecurity.ucs_credential.o
    public String a() {
        return e("ROOT") + "/tsms/v2/credentials";
    }

    public txh(Context context, String str) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setSerCountry(str);
        this.b = new GrsClient(context, grsBaseInfo);
    }
}
