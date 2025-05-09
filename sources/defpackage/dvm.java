package defpackage;

import com.huawei.health.hwhealthlinkage.linkage.parsedata.LinkageDataHandler;

/* loaded from: classes3.dex */
public class dvm implements LinkageDataHandler {
    @Override // com.huawei.health.hwhealthlinkage.linkage.parsedata.LinkageDataHandler
    public void handle(double d, ldo ldoVar) {
        ldoVar.e(d);
    }
}
