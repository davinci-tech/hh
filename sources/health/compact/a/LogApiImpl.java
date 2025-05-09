package health.compact.a;

import android.app.Activity;
import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.api.LogApi;
import com.huawei.utils.FoundationCommonUtil;

/* loaded from: classes.dex */
public class LogApiImpl implements LogApi {
    @Override // com.huawei.hihealth.api.LogApi
    public void v(String str, Object... objArr) {
        com.huawei.hwlogsmodel.LogUtil.i(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void d(String str, Object... objArr) {
        com.huawei.hwlogsmodel.LogUtil.c(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void i(String str, Object... objArr) {
        com.huawei.hwlogsmodel.LogUtil.a(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void w(String str, Object... objArr) {
        com.huawei.hwlogsmodel.LogUtil.h(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void e(String str, Object... objArr) {
        com.huawei.hwlogsmodel.LogUtil.b(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public boolean isBeta() {
        return com.huawei.hwlogsmodel.common.LogConfig.d();
    }

    @Override // com.huawei.hihealth.api.LogApi
    public String getCurrentActivity() {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            return null;
        }
        return wa_.toString();
    }

    @Override // com.huawei.hihealth.api.LogApi
    public boolean isSystemInfoAuthorized(Context context) {
        return FoundationCommonUtil.isSystemInfoAuthorized(context);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public String logAnonymousException(Exception exc) {
        return LogAnonymous.b((Throwable) exc);
    }
}
