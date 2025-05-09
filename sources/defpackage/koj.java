package defpackage;

import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class koj {
    public static int d(float f, List<kob> list) {
        if (f > 360.0f) {
            ReleaseLogUtil.c("Track_AbnormalSkipUtil", "avgSkipSpeed SKIP_SPEED_ABNORMAL", Float.valueOf(f));
            return 1;
        }
        if (koq.b(list)) {
            return 0;
        }
        for (kob kobVar : list) {
            if (kobVar != null && kobVar.c() > 360) {
                ReleaseLogUtil.c("Track_AbnormalSkipUtil", "list SKIP_SPEED_ABNORMAL", Integer.valueOf(kobVar.c()));
                return 1;
            }
        }
        return 0;
    }
}
