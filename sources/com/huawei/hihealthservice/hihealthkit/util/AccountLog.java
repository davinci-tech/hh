package com.huawei.hihealthservice.hihealthkit.util;

import android.content.Context;
import com.huawei.hms.support.log.HMSExtLogger;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class AccountLog implements HMSExtLogger {
    private Context d;

    public AccountLog(Context context) {
        this.d = context;
    }

    @Override // com.huawei.hms.support.log.HMSExtLogger
    public void d(String str, String str2) {
        LogUtil.c(str, str2);
    }

    @Override // com.huawei.hms.support.log.HMSExtLogger
    public void i(String str, String str2) {
        LogUtil.a(str, str2);
    }

    @Override // com.huawei.hms.support.log.HMSExtLogger
    public void w(String str, String str2) {
        LogUtil.h(str, str2);
    }

    @Override // com.huawei.hms.support.log.HMSExtLogger
    public void e(String str, String str2) {
        LogUtil.b(str, str2);
    }
}
