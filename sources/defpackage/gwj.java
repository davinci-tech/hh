package defpackage;

import com.huawei.health.pluginlocation.logger.Logger;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gwj implements Logger {
    @Override // com.huawei.health.pluginlocation.logger.Logger
    public void v(String str, Object... objArr) {
        LogUtil.i(str, objArr);
    }

    @Override // com.huawei.health.pluginlocation.logger.Logger
    public void d(String str, Object... objArr) {
        LogUtil.c(str, objArr);
    }

    @Override // com.huawei.health.pluginlocation.logger.Logger
    public void i(String str, Object... objArr) {
        LogUtil.a(str, objArr);
    }

    @Override // com.huawei.health.pluginlocation.logger.Logger
    public void w(String str, Object... objArr) {
        LogUtil.h(str, objArr);
    }

    @Override // com.huawei.health.pluginlocation.logger.Logger
    public void e(String str, Object... objArr) {
        LogUtil.b(str, objArr);
    }
}
