package com.huawei.hihealth.data.type;

import android.content.Context;
import android.util.SparseArray;
import com.amap.api.services.core.AMapException;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HiHealthDataType {

    /* renamed from: a, reason: collision with root package name */
    private static ArrayList<Integer> f4118a;
    public static final int b = DicDataTypeUtil.DataType.BODY_TEMPERATURE.value();
    private static ArrayList<Integer> c;
    private static ArrayList<Integer> d;
    private static ArrayList<Integer> e;
    private static SparseArray<int[]> g;
    private static ArrayList<Integer> h;
    private static ArrayList<Integer> i;

    public enum Category {
        POINT,
        SET,
        SESSION,
        SEQUENCE,
        STAT,
        REALTIME,
        CONFIG,
        CONFIGSTAT,
        CHECK_DWONLOAD,
        BUSINESS,
        UNKOWN
    }

    public enum ChangeType {
        UPDATE,
        INCREMENT
    }

    public static class SequenceDataType {
    }

    public enum TriggerParam {
        TRIGGER_FOREGROUND,
        TRIGGER_BACKGROUND,
        TRIGGER_LOCK,
        TRIGGER_OFFLINE
    }

    public enum TypeSelector {
        ALL,
        VALID,
        AUTHORIZED
    }

    public static int[] a() {
        return new int[]{44101, 44102, 44103, 44104, 44105, 44106, 44107, 44108, 44109, 44110, 44201, 44202, 44203, 44204, 44205, 44206, 44207, 44208, 44209, 44210, 44211, 44212, 44213, 44214, 44215, 44216, 44217, 44218, 44219, 44220, 44221, 44222, 44223, 44224, 44225, 44226, 44227, 44228, 44229, 44230, 44231, 44232, 44233, 44234};
    }

    public static int[] b() {
        return new int[]{46016, 46017, 46018, 46019, 46020};
    }

    public static int[] f() {
        return new int[]{44302, 44303};
    }

    public static int[] g() {
        return new int[]{47301, 47302, 47303, 47304, 47305};
    }

    public static boolean h(int i2) {
        return i2 >= 22100 && i2 <= 22199;
    }

    public static int[] h() {
        return new int[]{46016, 46017, 46018, 46019};
    }

    public static boolean i(int i2) {
        if (i2 == 44205) {
            return true;
        }
        switch (i2) {
            case 44101:
            case 44102:
            case 44103:
            case 44104:
            case 44105:
                return true;
            default:
                switch (i2) {
                    case 44107:
                    case 44108:
                    case 44109:
                    case 44110:
                        return true;
                    default:
                        switch (i2) {
                            case 44201:
                            case 44202:
                                return true;
                            default:
                                switch (i2) {
                                    case 44209:
                                    case 44210:
                                    case 44211:
                                    case 44212:
                                    case 44213:
                                    case 44214:
                                        return true;
                                    default:
                                        switch (i2) {
                                            case 44216:
                                            case 44217:
                                            case 44218:
                                            case 44219:
                                            case 44220:
                                            case 44221:
                                            case 44222:
                                            case 44223:
                                            case 44224:
                                            case 44225:
                                            case 44226:
                                            case 44227:
                                            case 44228:
                                            case 44229:
                                            case 44230:
                                            case 44231:
                                            case 44232:
                                            case 44233:
                                            case 44234:
                                                return true;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
        }
    }

    public static int[] i() {
        return new int[]{44304, 44305, 44306, 44307, 44308};
    }

    public static int[] j() {
        return new int[]{44209, 44210, 44211, 44212, 44213, 44214, 44217, 44219, 44220, 44221, 44222, 44223, 44224, 44225, 44226, 44227, 44228, 44229, 44230, 44231, 44232, 44233, 44234};
    }

    public static boolean k(int i2) {
        return i2 == 2105 || i2 == 2002 || i2 == 2018;
    }

    public static int[] k() {
        return new int[]{44001, 44002, 44003, 44004, 44005, 44006, 44007, 44008, 44009};
    }

    public static int[] l() {
        return new int[]{40011, 40012, 40013, 40031, 40032, 40033, 40034, 40035, 40041, 40042, 40043, 40044, 40045, 40021, 40022, 40023, 40024, 40025, SmartMsgConstant.MSG_TYPE_RIDE_USER};
    }

    public static boolean m(int i2) {
        return i2 >= 10010 && i2 <= 10060;
    }

    public static int[] m() {
        return new int[]{2, 4, 3, 5, 7};
    }

    public static boolean n(int i2) {
        boolean z = 31001 <= i2 && i2 <= 31007;
        boolean z2 = i2 == 2200 || i2 == 10009;
        return z | z2 | (i2 == 10065) | (i2 == 61001);
    }

    public static int[] n() {
        return new int[]{44399, 44300};
    }

    public static boolean p(int i2) {
        return i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5 || i2 == 7;
    }

    public static int[] p() {
        return new int[]{44209, 44210, 44211, 44212, 44213, 44214};
    }

    public static boolean r(int i2) {
        return i2 >= 22000 && i2 <= 22099;
    }

    public static boolean s(int i2) {
        if (i2 == 10001 || i2 == 10002 || i2 == 10006 || i2 == 10010 || i2 == 10065 || i2 == 31001) {
            return true;
        }
        switch (i2) {
            case 101202:
            case 101203:
            case 101204:
                return true;
            default:
                return false;
        }
    }

    public static boolean t(int i2) {
        if (i2 == 2034 || i2 == 2104 || i2 == 10010 || i2 == 10065 || i2 == 44000 || i2 == 47101 || i2 == 50001 || i2 == 10001 || i2 == 10002) {
            return true;
        }
        switch (i2) {
            case 10006:
            case 10007:
            case 10008:
                return true;
            default:
                switch (i2) {
                    case 30005:
                    case 30006:
                    case 30007:
                        return true;
                    default:
                        switch (i2) {
                            case 31001:
                            case 31002:
                            case 31003:
                            case 31004:
                            case 31005:
                            case 31006:
                            case 31007:
                                return true;
                            default:
                                switch (i2) {
                                    case 40002:
                                    case 40003:
                                    case 40004:
                                        return true;
                                    default:
                                        switch (i2) {
                                            case 47201:
                                            case 47202:
                                            case 47203:
                                            case 47204:
                                                return true;
                                            default:
                                                switch (i2) {
                                                    case 101001:
                                                    case 101002:
                                                    case 101003:
                                                        return true;
                                                    default:
                                                        switch (i2) {
                                                            case 101201:
                                                            case 101202:
                                                                return true;
                                                            default:
                                                                return false;
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static int[] t() {
        return new int[]{44219, 44220, 44221, 44222, 44223, 44224, 44225, 44226, 44227, 44228, 44229, 44230, 44231, 44232, 44233, 44234};
    }

    public static boolean x(int i2) {
        return (i2 >= 2019 && i2 <= 2021) || (i2 >= 2034 && i2 <= 2050);
    }

    static {
        SparseArray<int[]> sparseArray = new SparseArray<>(4);
        g = sparseArray;
        sparseArray.put(10001, new int[]{2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106});
        g.put(10010, new int[]{47401, 47405, 47402, 47403, 47404});
        g.put(10003, new int[]{2016, 2017, 2005});
        ArrayList<Integer> arrayList = new ArrayList<>(3);
        i = arrayList;
        arrayList.add(30005);
        i.add(30006);
        i.add(30007);
        i.add(30029);
        ArrayList<Integer> arrayList2 = new ArrayList<>(4);
        c = arrayList2;
        arrayList2.add(47201);
        c.add(47202);
        c.add(47203);
        c.add(47204);
        ArrayList<Integer> arrayList3 = new ArrayList<>(9);
        f4118a = arrayList3;
        arrayList3.add(31001);
        f4118a.add(31002);
        ArrayList<Integer> arrayList4 = f4118a;
        Integer valueOf = Integer.valueOf(AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR);
        arrayList4.add(valueOf);
        f4118a.add(10009);
        f4118a.add(31006);
        f4118a.add(31005);
        f4118a.add(31003);
        f4118a.add(31004);
        f4118a.add(31007);
        ArrayList<Integer> arrayList5 = new ArrayList<>();
        e = arrayList5;
        arrayList5.add(31001);
        e.add(2019);
        e.add(2020);
        e.add(2021);
        e.add(2034);
        e.add(2035);
        e.add(2036);
        e.add(2037);
        e.add(2050);
        e.add(22103);
        e.add(22102);
        e.add(22105);
        e.add(22101);
        e.add(22104);
        e.add(22106);
        e.add(22107);
        e.add(22001);
        e.add(22002);
        e.add(22003);
        e.add(2104);
        e.add(valueOf);
        e.add(2109);
        e.add(2018);
        e.add(2002);
        e.add(2101);
        e.add(2102);
        e.add(2105);
        ArrayList<Integer> arrayList6 = new ArrayList<>(2);
        h = arrayList6;
        arrayList6.add(10007);
        h.add(44000);
        ArrayList<Integer> arrayList7 = new ArrayList<>(10);
        d = arrayList7;
        arrayList7.add(Integer.valueOf(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()));
    }

    static class MensturalMapHolder {
        static final Map<Integer, String> c;

        private MensturalMapHolder() {
        }

        static {
            HashMap hashMap = new HashMap(16);
            c = hashMap;
            hashMap.put(10010, "menstrual_status");
            hashMap.put(10015, String.valueOf(100));
            hashMap.put(10016, String.valueOf(101));
            hashMap.put(10017, String.valueOf(102));
            hashMap.put(10018, String.valueOf(300));
            hashMap.put(10019, String.valueOf(301));
            hashMap.put(10020, String.valueOf(302));
            hashMap.put(10021, String.valueOf(400));
            hashMap.put(10022, String.valueOf(401));
            hashMap.put(10023, String.valueOf(402));
            hashMap.put(10024, String.valueOf(403));
            hashMap.put(10025, String.valueOf(0));
            hashMap.put(10026, String.valueOf(2));
        }
    }

    public static int[] d(int i2) {
        int[] iArr = g.get(i2);
        if (iArr != null) {
            return iArr;
        }
        List<Integer> g2 = HiHealthDictManager.d((Context) null).g(i2);
        if (g2.isEmpty()) {
            return iArr;
        }
        int[] iArr2 = new int[g2.size()];
        for (int i3 = 0; i3 < g2.size(); i3++) {
            iArr2[i3] = g2.get(i3).intValue();
        }
        return iArr2;
    }

    public static Category e(int i2) {
        if (i2 < 1) {
            return Category.UNKOWN;
        }
        if (i2 < 10000) {
            return Category.POINT;
        }
        if (i2 < 20000) {
            return Category.SET;
        }
        if (i2 < 30000) {
            return Category.SESSION;
        }
        if (i2 < 40000) {
            return Category.SEQUENCE;
        }
        if (i2 < 50000) {
            return Category.STAT;
        }
        if (i2 < 60000) {
            return Category.REALTIME;
        }
        if (i2 < 70000) {
            return Category.BUSINESS;
        }
        if (i2 < 80000) {
            return Category.CONFIG;
        }
        if (i2 < 90000) {
            return Category.CONFIGSTAT;
        }
        if (i2 < 100000) {
            return Category.CHECK_DWONLOAD;
        }
        int c2 = HiHealthDictManager.d((Context) null).c(i2);
        if (c2 == 0) {
            return Category.POINT;
        }
        if (c2 == 2) {
            return Category.STAT;
        }
        HiHealthDictType d2 = HiHealthDictManager.d((Context) null).d(i2);
        if (d2 != null) {
            if (d2.e() == 1) {
                return Category.SEQUENCE;
            }
            if (d2.e() == 3) {
                return Category.REALTIME;
            }
            return Category.SET;
        }
        return Category.UNKOWN;
    }

    public static int a(int i2) {
        if (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) {
            return 20001;
        }
        if (i2 == 7) {
            return i2;
        }
        if (i2 != 2002) {
            if (i2 == 2034) {
                return i2;
            }
            if (i2 != 2105) {
                if (i2 == 2200) {
                    return i2;
                }
                switch (i2) {
                    case 2018:
                        break;
                    case 2019:
                    case 2020:
                    case 2021:
                        break;
                    default:
                        switch (i2) {
                            case 2101:
                            case 2102:
                            case 2103:
                                break;
                            default:
                                if (HiHealthDictManager.d((Context) null).c(i2) == 0) {
                                }
                                break;
                        }
                }
                return i2;
            }
        }
        return 2002;
    }

    public static boolean g(int i2) {
        if (i2 >= 2019 && i2 != 2200) {
            return (i2 <= 2109 && i2 > 2050) || HiHealthDictManager.d((Context) null).c(i2) == 0;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0052 A[FALL_THROUGH, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean f(int r4) {
        /*
            r0 = 2002(0x7d2, float:2.805E-42)
            r1 = 1
            if (r4 == r0) goto L52
            r0 = 2050(0x802, float:2.873E-42)
            if (r4 == r0) goto L52
            r0 = 2109(0x83d, float:2.955E-42)
            if (r4 == r0) goto L52
            r0 = 2200(0x898, float:3.083E-42)
            if (r4 == r0) goto L52
            r0 = 30001(0x7531, float:4.204E-41)
            if (r4 == r0) goto L52
            r0 = 31001(0x7919, float:4.3442E-41)
            if (r4 == r0) goto L52
            switch(r4) {
                case 2008: goto L52;
                case 2009: goto L52;
                case 2010: goto L52;
                case 2011: goto L52;
                case 2012: goto L52;
                case 2013: goto L52;
                case 2014: goto L52;
                case 2015: goto L52;
                default: goto L1c;
            }
        L1c:
            switch(r4) {
                case 2018: goto L52;
                case 2019: goto L52;
                case 2020: goto L52;
                case 2021: goto L52;
                default: goto L1f;
            }
        L1f:
            switch(r4) {
                case 2034: goto L52;
                case 2035: goto L52;
                case 2036: goto L52;
                case 2037: goto L52;
                default: goto L22;
            }
        L22:
            switch(r4) {
                case 2101: goto L52;
                case 2102: goto L52;
                case 2103: goto L52;
                case 2104: goto L52;
                case 2105: goto L52;
                case 2106: goto L52;
                case 2107: goto L52;
                default: goto L25;
            }
        L25:
            switch(r4) {
                case 22001: goto L52;
                case 22002: goto L52;
                case 22003: goto L52;
                default: goto L28;
            }
        L28:
            switch(r4) {
                case 22101: goto L52;
                case 22102: goto L52;
                case 22103: goto L52;
                case 22104: goto L52;
                case 22105: goto L52;
                case 22106: goto L52;
                case 22107: goto L52;
                default: goto L2b;
            }
        L2b:
            switch(r4) {
                case 47401: goto L52;
                case 47402: goto L52;
                case 47403: goto L52;
                case 47404: goto L52;
                case 47405: goto L52;
                default: goto L2e;
            }
        L2e:
            r0 = 0
            com.huawei.hihealth.dictionary.HiHealthDictManager r2 = com.huawei.hihealth.dictionary.HiHealthDictManager.d(r0)
            int r2 = r2.c(r4)
            if (r2 == 0) goto L52
            r3 = 2
            if (r2 != r3) goto L3d
            goto L52
        L3d:
            com.huawei.hihealth.dictionary.HiHealthDictManager r0 = com.huawei.hihealth.dictionary.HiHealthDictManager.d(r0)
            com.huawei.hihealth.dictionary.model.HiHealthDictType r4 = r0.d(r4)
            r0 = 0
            if (r4 == 0) goto L51
            int r4 = r4.e()
            if (r4 != r1) goto L4f
            goto L50
        L4f:
            r1 = r0
        L50:
            return r1
        L51:
            return r0
        L52:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealth.data.type.HiHealthDataType.f(int):boolean");
    }

    public static boolean j(int i2) {
        if (i2 <= 2000 || i2 > 2018) {
            return (i2 >= 2100 && i2 <= 2200) || HiHealthDictManager.d((Context) null).c(i2) == 0;
        }
        return true;
    }

    public static ArrayList<Integer> s() {
        return i;
    }

    public static ArrayList<Integer> e() {
        return c;
    }

    public static ArrayList<Integer> c() {
        return f4118a;
    }

    public static ArrayList<Integer> o() {
        return h;
    }

    public static ArrayList<Integer> d() {
        return d;
    }

    public static boolean q(int i2) {
        return i.contains(Integer.valueOf(i2));
    }

    public static boolean o(int i2) {
        return c.contains(Integer.valueOf(i2));
    }

    public static boolean l(int i2) {
        return h.contains(Integer.valueOf(i2));
    }

    public static int c(int i2) {
        if (n(i2)) {
            return 31001;
        }
        if (m(i2)) {
            return 10010;
        }
        return i2;
    }

    public static String b(int i2) {
        return MensturalMapHolder.c.get(Integer.valueOf(i2));
    }
}
