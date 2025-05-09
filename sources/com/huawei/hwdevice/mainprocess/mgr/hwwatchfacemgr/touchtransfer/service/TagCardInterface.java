package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service;

import defpackage.jmw;
import defpackage.jmy;
import defpackage.jmz;
import defpackage.jna;
import defpackage.jnb;
import defpackage.jnc;
import defpackage.jnd;
import defpackage.jne;
import defpackage.jnh;
import defpackage.jni;

/* loaded from: classes5.dex */
public interface TagCardInterface {
    jnb applyApdu(jmw jmwVar);

    jne deleteApplet(jmz jmzVar);

    jnd downloadAndInstallApplet(jna jnaVar);

    jnh personalizeApplet(jmy jmyVar);

    jni queryInfoInitTsmParam(jnc jncVar);
}
