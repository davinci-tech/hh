package com.huawei.ui.device.interactors;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jgp;

/* loaded from: classes6.dex */
public class UploadMaintLogInteractor {
    private jgp b;

    public UploadMaintLogInteractor(Context context) {
        this.b = jgp.a(context);
    }

    public void c(boolean z, String str) {
        LogUtil.c("UploadMaintLogInteractor", "startUploadLogFile()");
        this.b.d(z, str);
    }
}
