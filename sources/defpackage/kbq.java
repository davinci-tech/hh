package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kbq {
    private static final Map<Integer, Integer> b = new LinkedHashMap(16);

    static {
        e();
    }

    public static void c() {
        if (Utils.o()) {
            b.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()), 113);
        } else {
            b.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()), 168);
        }
    }

    public static Map<Integer, Integer> d() {
        return b;
    }

    public static int d(int i) {
        Integer num = b.get(Integer.valueOf(i));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    private static void e() {
        Map<Integer, Integer> map = b;
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SPO2_RESP_INFECTION.value()), 117);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.RRI_RESP_INFECTION.value()), 117);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.TEMPERATURE_RESP_INFECTION.value()), 117);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_FRAGMENT_RESP_INFECTION.value()), 117);
        if (Utils.o()) {
            map.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()), 113);
        } else {
            map.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()), 168);
        }
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.CNTBPORIGINPPGDATA.value()), 118);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_DETAILS.value()), 143);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BG_DAILY_PROB_WIN.value()), 150);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BG_COMBINED_PPG_FEATURE.value()), 150);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT.value()), 157);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.DYNAMIC_BP_REPORT.value()), 205);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.PPG_OF_VASCULAR_HEALTH.value()), Integer.valueOf(a.L));
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.ECG_OF_VASCULAR_HEALTH.value()), Integer.valueOf(a.L));
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT_RESEARCH.value()), 157);
        if (cwi.c(e("DictionarySequenceCapability"), a.w)) {
            map.put(Integer.valueOf(DicDataTypeUtil.DataType.ECG_OF_VASCULAR_HEALTH.value()), Integer.valueOf(a.w));
        }
    }

    private static DeviceInfo e(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        LogUtil.a("DictionarySequenceCapability", "getConnectDeviceInfo() deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }
}
