package defpackage;

import android.os.Build;
import com.huawei.haf.common.dfx.process.DefaultProcessHandler;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class ixi extends DefaultProcessHandler {
    public ixi() {
        super("TimeEat_ProcessHandler");
    }

    @Override // com.huawei.haf.common.dfx.process.DefaultProcessHandler
    public void a(String str, List<String> list, String str2) {
        if (LogUtil.a() || LogUtil.c()) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put(OpAnalyticsConstants.PROC_NAME, list.toString());
            d(linkedHashMap, str, str2);
        }
    }

    @Override // com.huawei.haf.common.dfx.process.DefaultProcessHandler
    public void a(String str, String str2, long j, String str3) {
        if (LogUtil.a() || LogUtil.c()) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put(OpAnalyticsConstants.PROC_NAME, str2);
            linkedHashMap.put("duration", String.valueOf(j));
            d(linkedHashMap, str, str3);
        }
    }

    private void d(LinkedHashMap<String, String> linkedHashMap, String str, String str2) {
        linkedHashMap.put(OpAnalyticsConstants.MONITOR_KEY, str);
        linkedHashMap.put(OpAnalyticsConstants.MONITOR_INFO, str2);
        linkedHashMap.put("deviceType", Build.MANUFACTURER);
        linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, Build.BOARD);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_PROCESS_90030007.value(), linkedHashMap);
    }

    public static List<String> d() {
        return Arrays.asList(ProcessUtil.b(null), ProcessUtil.b(":DaemonService"), ProcessUtil.b(":PhoneService"));
    }
}
