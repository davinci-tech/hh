package defpackage;

import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class ffz {
    public static int b() {
        if (UnitUtil.h()) {
            return R$string.sug_pace_mile;
        }
        return R$string.sug_event_time_formart;
    }

    public static int c() {
        return UnitUtil.h() ? R.plurals._2130903109_res_0x7f030045 : R.plurals._2130903108_res_0x7f030044;
    }
}
