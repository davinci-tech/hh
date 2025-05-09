package defpackage;

import android.app.Activity;
import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.api.LogApi;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class gor implements LogApi {
    @Override // com.huawei.hihealth.api.LogApi
    public void v(String str, Object... objArr) {
        ReleaseLogUtil.e(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void d(String str, Object... objArr) {
        ReleaseLogUtil.e(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void i(String str, Object... objArr) {
        ReleaseLogUtil.e(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void w(String str, Object... objArr) {
        ReleaseLogUtil.d(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public void e(String str, Object... objArr) {
        ReleaseLogUtil.c(str, objArr);
    }

    @Override // com.huawei.hihealth.api.LogApi
    public boolean isBeta() {
        return LogConfig.d();
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
