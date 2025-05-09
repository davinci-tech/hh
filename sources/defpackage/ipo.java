package defpackage;

import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;

/* loaded from: classes4.dex */
public class ipo {
    public static final String d;

    static {
        d = CommonUtil.cc() ? BleConstants.WEIGHT_KEY : "";
    }
}
