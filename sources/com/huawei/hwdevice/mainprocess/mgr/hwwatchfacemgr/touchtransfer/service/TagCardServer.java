package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service;

import android.content.Context;
import defpackage.jmw;
import defpackage.jmy;
import defpackage.jmz;
import defpackage.jna;
import defpackage.jnb;
import defpackage.jnc;
import defpackage.jnd;
import defpackage.jne;
import defpackage.jng;
import defpackage.jnh;
import defpackage.jni;
import defpackage.jnl;
import defpackage.jnm;
import defpackage.jno;
import defpackage.jnp;
import defpackage.jnu;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class TagCardServer implements TagCardInterface {
    private Context c;

    public TagCardServer(Context context) {
        this.c = context;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service.TagCardInterface
    public jni queryInfoInitTsmParam(jnc jncVar) {
        return new jnp(this.c, b()).processTask(jncVar);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service.TagCardInterface
    public jnd downloadAndInstallApplet(jna jnaVar) {
        return new jnm(this.c, a()).processTask(jnaVar);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service.TagCardInterface
    public jnh personalizeApplet(jmy jmyVar) {
        return new jnl(this.c, a()).processTask(jmyVar);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service.TagCardInterface
    public jne deleteApplet(jmz jmzVar) {
        return new jno(this.c, a()).processTask(jmzVar);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service.TagCardInterface
    public jnb applyApdu(jmw jmwVar) {
        return new jng(this.c, a()).processTask(jmwVar);
    }

    private String b() {
        return GRSManager.a(this.c).getUrl("domainVcardmgtWalletHicloud") + "/WiseCloudVirtualCardMgmtService/app/gateway?clientVersion=" + jnu.h();
    }

    private String a() {
        return GRSManager.a(this.c).getUrl("domainPassWalletHicloud") + "/WiseCloudWalletPassService/app/gateway?clientVersion=" + jnu.h();
    }
}
