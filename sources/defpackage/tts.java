package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.wisesecurity.kfs.ha.HiAnalyticsType;
import com.huawei.wisesecurity.kfs.ha.message.ReportMsgBuilder;
import com.huawei.wisesecurity.kfs.log.ILogKfs;

/* loaded from: classes7.dex */
public class tts {
    private final ILogKfs b;
    private final ttt d;
    private HiAnalyticsInstance e;

    public tts(Context context, String str, String str2, ILogKfs iLogKfs) throws ttv {
        if (TextUtils.isEmpty(str2)) {
            throw new ttv("hiAnalyticsUrl is empty");
        }
        this.b = iLogKfs;
        iLogKfs.i("HaReporter", "hiAnalyticsUrl is " + str2);
        this.d = new ttt(iLogKfs);
        a(str2, context, str);
    }

    public void d() {
        this.d.b();
    }

    private void a(String str, Context context, String str2) {
        HiAnalyticsConfig build = new HiAnalyticsConfig.Builder().setCollectURL(str).setEnableUUID(false).build();
        HiAnalyticsInstance refresh = new HiAnalyticsInstance.Builder(context).setMaintConf(build).setOperConf(build).refresh(str2);
        this.e = refresh;
        if (refresh == null) {
            this.b.i("HaReporter", "The analytics instance was not successfully obtained, and the analytics capability cannot be used");
        } else {
            refresh.setAppid("com.huawei.wisesecurity.common");
        }
    }

    public void d(Context context, ReportMsgBuilder reportMsgBuilder) {
        d(context, reportMsgBuilder, HiAnalyticsType.HIANALYTICS_MAINTENANCE);
    }

    public void d(Context context, ReportMsgBuilder reportMsgBuilder, HiAnalyticsType hiAnalyticsType) {
        if (this.e == null) {
            this.b.i("HaReporter", "onEvent null == analyticsInstance");
            return;
        }
        if (this.d.b(context)) {
            this.b.i("HaReporter", "onEvent isEnabledUserExperience is false");
            return;
        }
        try {
            this.e.onEvent(hiAnalyticsType.getCode(), reportMsgBuilder.getEventId(), reportMsgBuilder.build());
            this.b.i("HaReporter", "onEvent success");
        } catch (Exception e) {
            this.b.w("HaReporter", "onEvent fail : " + e.getMessage());
        }
    }
}
