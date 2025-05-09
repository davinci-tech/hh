package defpackage;

import com.huawei.health.devicemgr.api.phoneprocess.SampleSequenceAfterProcess;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cur {
    private static final Map<Integer, SampleSequenceAfterProcess> c = new LinkedHashMap(16);

    static {
        b();
    }

    public static SampleSequenceAfterProcess e(int i) {
        return c.get(Integer.valueOf(i));
    }

    private static void b() {
        Map<Integer, SampleSequenceAfterProcess> map = c;
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()), new mpg());
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_DETAILS.value()), new nhs());
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT.value()), new mpd());
    }
}
