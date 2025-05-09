package com.huawei.hihealth.data.type;

import android.content.Context;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.hihealth.HiHealthStatusCodes;

/* loaded from: classes.dex */
public class HiSubscribeType {
    public static final int c = DicDataTypeUtil.DataType.WEIGHT_BODYFAT_BROAD.value();

    /* renamed from: a, reason: collision with root package name */
    public static final int f4119a = DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value();
    public static final int e = DicDataTypeUtil.DataType.CONTINUE_BLOODSUGAR_SET.value();

    public static boolean b(int i) {
        return i > 0;
    }

    private static int f(int i) {
        if (i <= 21000) {
            return 1;
        }
        if (i <= 22099) {
            return 2;
        }
        return i <= 22199 ? 3 : 0;
    }

    /* renamed from: com.huawei.hihealth.data.type.HiSubscribeType$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            b = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[HiHealthDataType.Category.SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[HiHealthDataType.Category.SESSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[HiHealthDataType.Category.SEQUENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[HiHealthDataType.Category.REALTIME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[HiHealthDataType.Category.STAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static int a(int i) {
        switch (AnonymousClass1.b[HiHealthDataType.e(i).ordinal()]) {
            case 1:
                return c(i);
            case 2:
                return h(i);
            case 3:
                return f(i);
            case 4:
                return e(i);
            case 5:
                return d(i);
            case 6:
                return i(i);
            default:
                return 0;
        }
    }

    private static int c(int i) {
        HiHealthDictType f;
        if (i < 1000) {
            return 1;
        }
        if (i == 2002) {
            return 5;
        }
        if (i == 2003) {
            return 11;
        }
        if (i == 2005) {
            return 12;
        }
        if (i == 2103) {
            return 18;
        }
        if (i == 2106) {
            return 10;
        }
        switch (i) {
            case 2008:
            case 2009:
            case 2010:
            case 2011:
            case 2012:
            case 2013:
            case 2014:
            case 2015:
                return 10;
            case 2016:
            case 2017:
                return 12;
            case 2018:
                return 6;
            default:
                switch (i) {
                    case 2034:
                    case 2035:
                    case 2036:
                    case 2037:
                        return 14;
                    default:
                        if (HiHealthDictManager.d((Context) null).c(i) != 0 || (f = HiHealthDictManager.d((Context) null).f(i)) == null) {
                            return 0;
                        }
                        return f.i();
                }
        }
    }

    private static int h(int i) {
        if (i == 10006) {
            return c;
        }
        if (i == 10010) {
            return 23;
        }
        switch (i) {
            case 10001:
                return 10;
            case 10002:
                return f4119a;
            case 10003:
                return 12;
            default:
                if (HiHealthDictManager.d((Context) null).d(i) != null) {
                    return i;
                }
                return 0;
        }
    }

    private static int e(int i) {
        if (i <= 30999) {
            return 4;
        }
        if (HiHealthDictManager.d((Context) null).l(i)) {
            return i;
        }
        return 0;
    }

    private static int d(int i) {
        if (i == 50001) {
            return 13;
        }
        if (i == 300001) {
            return 15;
        }
        switch (i) {
            case HiHealthStatusCodes.NOT_EXIST_DATA_TYPE_ERROR /* 50003 */:
                return 16;
            case HiHealthStatusCodes.APP_MISMATCH_ERROR /* 50004 */:
                return 17;
            case HiHealthStatusCodes.UNKNOWN_AUTH_ERROR /* 50005 */:
                return 19;
            case HiHealthStatusCodes.NO_BLE_PERMISSION_ERROR /* 50006 */:
                return 20;
            case HiHealthStatusCodes.UNSUPPORTED_PLATFORM_ERROR /* 50007 */:
                return 22;
            case HiHealthStatusCodes.TRY_AGAIN_ERROR /* 50008 */:
                return 24;
            case HiHealthStatusCodes.ACTIVITY_RECORD_ENDED_ERROR /* 50009 */:
                return 25;
            default:
                if (HiHealthDictManager.d((Context) null).d(i) != null) {
                    return i;
                }
                return 0;
        }
    }

    private static int i(int i) {
        HiHealthDictType h;
        if (i <= 41000) {
            return 1;
        }
        if (i <= 43900) {
            return 4;
        }
        if (i <= 44099) {
            return 2;
        }
        if (i <= 44299) {
            return 3;
        }
        if (i <= 46010 || i <= 47000) {
            return 5;
        }
        if (i > 47200 && i <= 47299) {
            return 18;
        }
        if (HiHealthDictManager.d((Context) null).c(i) != 2 || (h = HiHealthDictManager.d((Context) null).h(i)) == null) {
            return 0;
        }
        return h.i();
    }
}
