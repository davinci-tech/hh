package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class gyd implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final int f13002a;
    private final String c;
    private gyf d;

    public gyd(Context context, int i, String str) {
        if (context != null) {
            this.d = gyf.b(context);
        } else {
            ReleaseLogUtil.e("Track_OfflineMapOnClickListener", "context == null");
        }
        this.f13002a = i;
        this.c = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.d == null) {
            ReleaseLogUtil.e("Track_OfflineMapOnClickListener", "onClick mController is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(750)) {
            LogUtil.a("Track_OfflineMapOnClickListener", "onClick isFastClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.a("Track_OfflineMapOnClickListener", "onClick() ", this.c, Integer.valueOf(this.f13002a));
        int i = this.f13002a;
        if (i != -1) {
            if (i == 0) {
                this.d.a(this.c);
            } else if (i != 3) {
                if (i != 10) {
                    if (i != 1002 && i != 5 && i != 6) {
                        if (i != 7) {
                            switch (i) {
                                case 102:
                                    this.d.d(this.c, false);
                                    this.d.c(this.c, false);
                                    break;
                            }
                        }
                    }
                }
                this.d.c(this.c, true);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
        this.d.c(this.c, false);
        ViewClickInstrumentation.clickOnView(view);
    }
}
