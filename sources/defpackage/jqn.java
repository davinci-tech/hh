package defpackage;

import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.syncmgr.calendarsync.CalendarSyncApi;
import health.compact.a.EnvironmentUtils;

@ApiDefine(uri = CalendarSyncApi.class)
@Singleton
/* loaded from: classes5.dex */
public class jqn implements CalendarSyncApi {
    @Override // com.huawei.syncmgr.calendarsync.CalendarSyncApi
    public void syncCalendarSwitch(int i) {
        LogUtil.a("CalendarSwitchApiImpl", "syncCalendarSwitch isPhoneServiceProcess is ", Boolean.valueOf(EnvironmentUtils.e()));
        if (EnvironmentUtils.e()) {
            jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, i);
            return;
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                e.notifyPhoneService("syncCalendarSwitch", new DeviceInfo(), i, null);
                return;
            } catch (RemoteException e2) {
                jez.a(BaseApplication.getContext());
                LogUtil.b("CalendarSwitchApiImpl", "syncCalendarSwitch exception:", ExceptionUtils.d(e2));
                return;
            }
        }
        LogUtil.h("CalendarSwitchApiImpl", "syncCalendarSwitch() iPhoneServiceAIDL is null");
        jez.a(BaseApplication.getContext());
    }
}
