package defpackage;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class iqj {
    private static HashMap<Integer, int[]> sportTypeMap = new HashMap<>();
    private static HashMap<Integer, String[]> sportTypeQueryKeys = new HashMap<>();

    static {
        sportTypeMap.put(258, new int[]{2, 4, 5, 3});
        sportTypeMap.put(283, new int[]{4, 5, 3});
        HashMap<Integer, int[]> hashMap = sportTypeMap;
        Integer valueOf = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
        hashMap.put(valueOf, new int[]{4, 5, 3});
        HashMap<Integer, int[]> hashMap2 = sportTypeMap;
        Integer valueOf2 = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
        hashMap2.put(valueOf2, new int[]{4, 5, 3});
        HashMap<Integer, int[]> hashMap3 = sportTypeMap;
        Integer valueOf3 = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE);
        hashMap3.put(valueOf3, new int[]{4, 5, 3});
        sportTypeQueryKeys.put(258, new String[]{"total_distance", "total_duration", "total_time", "total_calorie"});
        sportTypeQueryKeys.put(283, new String[]{"total_duration", "total_time", "total_calorie"});
        sportTypeQueryKeys.put(valueOf, new String[]{"total_duration", "total_time", "total_calorie"});
        sportTypeQueryKeys.put(valueOf2, new String[]{"total_duration", "total_time", "total_calorie"});
        sportTypeQueryKeys.put(valueOf3, new String[]{"total_duration", "total_time", "total_calorie"});
    }

    public static int[] getSportTypeMap(int i) {
        return sportTypeMap.get(Integer.valueOf(i));
    }

    public static String[] getSportTypeQueryKeys(int i) {
        return sportTypeQueryKeys.get(Integer.valueOf(i));
    }
}
