package defpackage;

import com.huawei.health.devicemgr.api.phoneprocess.SamplePointAfterProcess;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cuq {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, SamplePointAfterProcess> f11486a = new LinkedHashMap(16);

    static {
        c();
    }

    public static SamplePointAfterProcess c(int i) {
        return f11486a.get(Integer.valueOf(i));
    }

    private static void c() {
        Map<Integer, SamplePointAfterProcess> map = f11486a;
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR.value()), new niu());
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()), new nja());
    }
}
