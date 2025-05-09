package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fhw {
    public static Map e = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    public static Map f12519a = new HashMap();
    public static List b = new ArrayList();
    public static List d = new ArrayList();

    static {
        e.put(264, HealthDevice.HealthDeviceKind.HDK_TREADMILL);
        Map map = e;
        Integer valueOf = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
        map.put(valueOf, HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE);
        Map map2 = e;
        Integer valueOf2 = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE);
        map2.put(valueOf2, HealthDevice.HealthDeviceKind.HDK_ROWING_MACHINE);
        Map map3 = e;
        Integer valueOf3 = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
        map3.put(valueOf3, HealthDevice.HealthDeviceKind.HDK_ELLIPTICAL_MACHINE);
        f12519a.put(264, Integer.valueOf(CharacteristicConstant.DeviceType.TYPE_TREADMILL_INDEX.getDeviceTypeValue()));
        f12519a.put(valueOf, Integer.valueOf(CharacteristicConstant.DeviceType.TYPE_INDOORBIKE_INDEX.getDeviceTypeValue()));
        f12519a.put(valueOf2, Integer.valueOf(CharacteristicConstant.DeviceType.TYPE_ROWER_INDEX.getDeviceTypeValue()));
        f12519a.put(valueOf3, Integer.valueOf(CharacteristicConstant.DeviceType.TYPE_CROSSTRAINER_INDEX.getDeviceTypeValue()));
        b.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT));
        d.add(257);
        d.add(258);
        d.add(259);
    }
}
