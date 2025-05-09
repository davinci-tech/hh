package com.huawei.ui.main.stories.nps.interactors.mode;

import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class Records {
    private static final HashMap<Integer, List<Integer>> DATA_CENTER = new HashMap<>(16);
    private static final HashMap<Integer, String> OPTION_RESULT = new HashMap<>(16);
    private static final HashMap<Integer, String> STRING_DATA_CENTER = new HashMap<>(16);

    private Records() {
    }

    public static HashMap<Integer, List<Integer>> getDataCenter() {
        return DATA_CENTER;
    }

    public static HashMap<Integer, String> getOptionResult() {
        return OPTION_RESULT;
    }

    public static HashMap<Integer, String> getStringDataCenter() {
        return STRING_DATA_CENTER;
    }

    public static boolean queryInt(Integer num) {
        return getStringDataCenter().containsKey(num);
    }

    public static boolean queryInt(List<Integer> list, Integer num) {
        return list.contains(num);
    }

    public static boolean deleteInt(List<Integer> list, Integer num) {
        return list.remove(num);
    }

    public static boolean addInt(List<Integer> list, Integer num) {
        return list.add(num);
    }

    public static void clearAllResult() {
        HashMap<Integer, List<Integer>> hashMap = DATA_CENTER;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashMap<Integer, String> hashMap2 = OPTION_RESULT;
        if (hashMap2 != null) {
            hashMap2.clear();
        }
        HashMap<Integer, String> hashMap3 = STRING_DATA_CENTER;
        if (hashMap3 != null) {
            hashMap3.clear();
        }
    }
}
