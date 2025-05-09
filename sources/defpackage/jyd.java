package defpackage;

import android.database.ContentObserver;
import android.os.Handler;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jyd extends ContentObserver {

    /* renamed from: a, reason: collision with root package name */
    private Runnable f14189a;
    private Handler d;

    public jyd(Handler handler) {
        super(handler);
        this.d = null;
        this.f14189a = new Runnable() { // from class: jyd.5
            @Override // java.lang.Runnable
            public void run() {
                jyc b = jyc.b();
                if (jyo.d()) {
                    b.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1);
                } else {
                    b.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 6);
                    LogUtil.h("CalendarObserver", "onChange: cannot sync Calendar without contact permissions.");
                }
            }
        };
        this.d = handler;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        LogUtil.a("CalendarObserver", "Listen for calendar schedule changes.");
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacks(this.f14189a);
            this.d.postDelayed(this.f14189a, 1000L);
        }
    }
}
