package defpackage;

import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mdx {
    private static Map<String, List<Integer>> d;

    static {
        HashMap hashMap = new HashMap(13);
        d = hashMap;
        hashMap.put(EnumAnnualType.REPORT_INITAL.value(), Arrays.asList(9002, 10015, 9001, 9003));
        d.put(EnumAnnualType.REPORT_CYCLE.value(), Arrays.asList(1003, 1004, 1002, 1001, Integer.valueOf(PrebakedEffectId.RT_SPEED_UP)));
        d.put(EnumAnnualType.REPORT_RUN.value(), Arrays.asList(2003, 2005, 2004, 2001, 2006, 2002, Integer.valueOf(MainLoginCallBack.PHASE_NOCLOUD_NOLOGIN), 10022, 10023, 10024, 10025));
        d.put(EnumAnnualType.REPORT_STEP.value(), Arrays.asList(3001, 3003, Integer.valueOf(IEventListener.EVENT_ID_DEVICE_CONN_FAIL), 3002, Integer.valueOf(IEventListener.EVENT_ID_DEVICE_DISCONN_SUCC), 9006, 10026));
        d.put(EnumAnnualType.REPORT_FITNESS.value(), Arrays.asList(4001, 4002, Integer.valueOf(WearableStatusCodes.DATA_ITEM_TOO_LARGE), Integer.valueOf(WearableStatusCodes.INVALID_TARGET_NODE), 4005, Integer.valueOf(PrebakedEffectId.RT_JUMP), 10029, Integer.valueOf(PrebakedEffectId.RT_COIN_DROP), Integer.valueOf(PrebakedEffectId.RT_HEARTBEAT), 10032, Integer.valueOf(PrebakedEffectId.RT_FOOTSTEP), Integer.valueOf(PrebakedEffectId.RT_ICE)));
        d.put(EnumAnnualType.REPORT_WEIGHT.value(), Arrays.asList(7001, 7002, 7003, Integer.valueOf(PrebakedEffectId.RT_LIGHTNING)));
        d.put(EnumAnnualType.REPORT_SLEEP.value(), Arrays.asList(8001, 8002, Integer.valueOf(MainLoginCallBack.MSG_HMS_VERSION_ERROR), Integer.valueOf(MainLoginCallBack.MSG_HWID_ACCOUNT_NOT_LOGIN), Integer.valueOf(MainLoginCallBack.MSG_NO_NETWORK), Integer.valueOf(MainLoginCallBack.MSG_HWID_STOPED), Integer.valueOf(MainLoginCallBack.MSG_START_APK_SERVICE_ERROR), 10017, 10018, 10019, 10020, 10021));
        d.put(EnumAnnualType.REPORT_REWARD.value(), Arrays.asList(5001, Integer.valueOf(PrebakedEffectId.RT_DRAWING_ARROW), 10034));
        d.put(EnumAnnualType.REPORT_SUMARY.value(), Arrays.asList(6001, 6002, Integer.valueOf(AuthCode.StatusCode.CERT_FINGERPRINT_ERROR), Integer.valueOf(AuthCode.StatusCode.PERMISSION_NOT_EXIST), Integer.valueOf(AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED)));
        d.put(EnumAnnualType.REPORT_MARATHON.value(), Arrays.asList(9004, 9005));
        d.put(EnumAnnualType.REPORT_DIET.value(), Arrays.asList(10016));
        d.put(EnumAnnualType.REPORT_MUSIC.value(), Arrays.asList(10035, Integer.valueOf(PrebakedEffectId.RT_SNIPER_RIFLE), Integer.valueOf(PrebakedEffectId.RT_ASSAULT_RIFLE), 10038, 10039, 10050));
        d.put(EnumAnnualType.REPORT_VIP.value(), Arrays.asList(Integer.valueOf(PrebakedEffectId.RT_FAST_MOVING), Integer.valueOf(PrebakedEffectId.RT_FLY)));
        d.put(EnumAnnualType.REPORT_LIVING.value(), Arrays.asList(Integer.valueOf(PrebakedEffectId.RT_SPRING), Integer.valueOf(PrebakedEffectId.RT_SWING), Integer.valueOf(PrebakedEffectId.RT_WIND), Integer.valueOf(PrebakedEffectId.RT_VICTORY), Integer.valueOf(PrebakedEffectId.RT_AWARD)));
        d.put(EnumAnnualType.REPORT_CLIMB_HILL.value(), Arrays.asList(10051, 10052, 10053, 10054, 10055, 10056, 10057, 10058));
        d.put(EnumAnnualType.REPORT_BADMINTON.value(), Arrays.asList(10059, 10060, 10061, 10062));
        d.put(EnumAnnualType.REPORT_JUMP_ROPE.value(), Arrays.asList(10063, 10064, 10065, 10066, 10067));
    }

    private static List<Integer> b(String str) {
        return d.get(str);
    }

    public static List<Integer> c(String str, String str2) {
        if (mht.b(str2) < 2020) {
            return b(str);
        }
        return new mhf().a(mht.b(str2)).getAnnualPageArrayByType(str);
    }
}
