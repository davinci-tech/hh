package defpackage;

import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;

/* loaded from: classes7.dex */
public class ipc {
    public static boolean c(int i) {
        return i == 2104 || i == HiHealthDataType.b || i == 2103 || i == 2109;
    }

    public static boolean d(int i) {
        return i == DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value();
    }
}
