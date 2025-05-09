package defpackage;

import android.content.Context;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsleepdetection.SleepRecordNotifyApi;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;

@ApiDefine(uri = SleepRecordNotifyApi.class)
@Singleton
/* loaded from: classes6.dex */
public class ppx implements SleepRecordNotifyApi {
    @Override // com.huawei.pluginsleepdetection.SleepRecordNotifyApi
    public void pullUpAlarm(Context context) {
        LogUtil.a("SleepNotifyImpl", "pullUpAlarm");
        ppk.e(context);
    }

    @Override // com.huawei.pluginsleepdetection.SleepRecordNotifyApi
    public void cancelAlarm() {
        LogUtil.a("SleepNotifyImpl", "cancelAlarm");
        ppk.c();
    }

    @Override // com.huawei.pluginsleepdetection.SleepRecordNotifyApi
    public void showNotification(Context context) {
        LogUtil.a("SleepNotifyImpl", "showNotification");
        ppk.b();
    }

    @Override // com.huawei.pluginsleepdetection.SleepRecordNotifyApi
    public void closeNotification() {
        LogUtil.a("SleepNotifyImpl", "closeNotification");
        ppk.a();
    }

    @Override // com.huawei.pluginsleepdetection.SleepRecordNotifyApi
    public boolean isInTimeRange() {
        return ppk.d();
    }

    @Override // com.huawei.pluginsleepdetection.SleepRecordNotifyApi
    public String getCacheKey() {
        LogUtil.a("SleepNotifyImpl", "getCacheKey");
        return KnitSleepDetailActivity.b();
    }
}
