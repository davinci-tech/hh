package com.huawei.hms.framework.network.restclient.hwhttp;

import com.huawei.hms.framework.common.ExtLogger;
import com.huawei.hms.network.api.advance.WrapperLogger;

/* loaded from: classes9.dex */
public class WrapExtLogger extends WrapperLogger {
    private final ExtLogger extLogger;

    public WrapExtLogger(ExtLogger extLogger) {
        this.extLogger = extLogger;
    }

    @Override // com.huawei.hms.network.api.advance.WrapperLogger
    public void v(String str, String str2) {
        this.extLogger.v(str, str2);
    }

    @Override // com.huawei.hms.network.api.advance.WrapperLogger
    public void d(String str, String str2) {
        this.extLogger.d(str, str2);
    }

    @Override // com.huawei.hms.network.api.advance.WrapperLogger
    public void i(String str, String str2) {
        this.extLogger.i(str, str2);
    }

    @Override // com.huawei.hms.network.api.advance.WrapperLogger
    public void w(String str, String str2) {
        this.extLogger.w(str, str2);
    }

    @Override // com.huawei.hms.network.api.advance.WrapperLogger
    public void w(String str, String str2, Throwable th) {
        this.extLogger.w(str, str2, th);
    }

    @Override // com.huawei.hms.network.api.advance.WrapperLogger
    public void e(String str, String str2) {
        this.extLogger.e(str, str2);
    }

    @Override // com.huawei.hms.network.api.advance.WrapperLogger
    public void e(String str, String str2, Throwable th) {
        this.extLogger.e(str, str2, th);
    }
}
