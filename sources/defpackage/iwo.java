package defpackage;

import android.text.TextUtils;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class iwo {
    public static void a(HiAggregateOption hiAggregateOption) throws iwt {
        d(hiAggregateOption);
        c(hiAggregateOption.getStartTime(), hiAggregateOption.getEndTime());
        d(hiAggregateOption.getFilterStartTime(), hiAggregateOption.getFilterEndTime());
        int[] type = hiAggregateOption.getType();
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        a(type);
        if (!d(type)) {
            throw new iwt("HiAggregateOption types is not all sensitive or normal");
        }
        e(constantsKey);
        c(type.length, constantsKey.length);
        b(hiAggregateOption);
        int alignType = hiAggregateOption.getAlignType();
        a(alignType);
        HiHealthDataType.Category e = HiHealthDataType.e(type[0]);
        d(e, type);
        d(e, hiAggregateOption.getAggregateType(), alignType);
        e(hiAggregateOption);
        e(hiAggregateOption.getEndTime() - hiAggregateOption.getStartTime(), hiAggregateOption.getGroupUnitType());
        b(hiAggregateOption.getAggregateType());
    }

    private static void e(HiAggregateOption hiAggregateOption) throws iwt {
        long modifiedStartTime = hiAggregateOption.getModifiedStartTime();
        long modifiedEndTime = hiAggregateOption.getModifiedEndTime();
        if (modifiedStartTime < 0) {
            throw new iwt("HiAggregateOption modifiedStartTime cannot be less than 0");
        }
        if (modifiedStartTime > modifiedEndTime) {
            throw new iwt("modifiedStartTime > modifiedEndTime");
        }
        if (modifiedEndTime <= 0 || hiAggregateOption.getEndTime() > 0) {
            return;
        }
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(LocationRequestCompat.PASSIVE_INTERVAL);
    }

    public static void d(HiSportStatDataAggregateOption hiSportStatDataAggregateOption) throws iwt {
        c(hiSportStatDataAggregateOption);
        long startTime = hiSportStatDataAggregateOption.getStartTime();
        long endTime = hiSportStatDataAggregateOption.getEndTime();
        c(startTime, endTime);
        int[] type = hiSportStatDataAggregateOption.getType();
        String[] constantsKey = hiSportStatDataAggregateOption.getConstantsKey();
        a(type);
        if (!d(type)) {
            throw new iwt("HiAggregateOption types is not all sensitive or normal");
        }
        e(constantsKey);
        c(type.length, constantsKey.length);
        e(hiSportStatDataAggregateOption);
        a(hiSportStatDataAggregateOption.getAlignType());
        e(endTime - startTime, hiSportStatDataAggregateOption.getGroupUnitType());
        b(hiSportStatDataAggregateOption.getAggregateType());
    }

    private static boolean d(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return true;
        }
        boolean g = ivu.g(BaseApplication.getContext(), iArr[0]);
        for (int i : iArr) {
            if (ivu.g(BaseApplication.getContext(), i) != g) {
                return false;
            }
        }
        return true;
    }

    private static void d(HiAggregateOption hiAggregateOption) throws iwt {
        if (hiAggregateOption == null) {
            throw new iwt("HiAggregateOption is null");
        }
    }

    private static void c(HiSportStatDataAggregateOption hiSportStatDataAggregateOption) throws iwt {
        if (hiSportStatDataAggregateOption == null) {
            throw new iwt("HiSportStatDataAggregateOption is null");
        }
    }

    private static void c(long j, long j2) throws iwt {
        if (j < 0) {
            ReleaseLogUtil.d("HiH_HiDateAggregateValid", "HiAggregateOption startTime cannot be less than 0");
        }
        if (j > j2) {
            throw new iwt("HiAggregateOption startTime is bigger than endTime");
        }
    }

    private static void d(String str, String str2) throws iwt {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        if (str.length() != 8 || str2.length() != 8) {
            throw new iwt("filterTime length error");
        }
        Pattern compile = Pattern.compile("([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])");
        if (!compile.matcher(str).matches() || !compile.matcher(str2).matches()) {
            throw new iwt("filterTime Format error");
        }
    }

    private static void a(int[] iArr) throws iwt {
        if (iArr == null || iArr.length <= 0) {
            throw new iwt("HiAggregateOption types is null");
        }
    }

    private static void e(String[] strArr) throws iwt {
        if (strArr == null || strArr.length <= 0) {
            throw new iwt("HiAggregateOption constantsKey is null");
        }
    }

    private static void c(int i, int i2) throws iwt {
        if (i != i2) {
            throw new iwt("HiAggregateOption constantsKey length not equal types length");
        }
    }

    private static void d(String str) throws iwt {
        if (str == null || str.isEmpty()) {
            throw new iwt("HiAggregateOption readType is READ_USER_DEVICE deviceUuid should be not null");
        }
    }

    private static void b(HiAggregateOption hiAggregateOption) throws iwt {
        int readType = hiAggregateOption.getReadType();
        if (readType == 0 || readType == 1) {
            return;
        }
        if (readType == 2 || readType == 3) {
            d(hiAggregateOption.getDeviceUuid());
            return;
        }
        throw new iwt("HiAggregateOption readType is out of rang");
    }

    private static void e(HiSportStatDataAggregateOption hiSportStatDataAggregateOption) throws iwt {
        int readType = hiSportStatDataAggregateOption.getReadType();
        if (readType == 0 || readType == 1) {
            return;
        }
        if (readType == 2) {
            d(hiSportStatDataAggregateOption.getDeviceUuid());
            return;
        }
        throw new iwt("HiSportStatDataAggregateOption readType is out of rang");
    }

    private static void a(int i) throws iwt {
        if (i > 0 && HiHealthDataType.e(i) != HiHealthDataType.Category.SESSION) {
            throw new iwt("HiAggregateOption alignType is not SESSION");
        }
    }

    private static void d(HiHealthDataType.Category category, int[] iArr) throws iwt {
        for (int i : iArr) {
            if (category != HiHealthDataType.e(i)) {
                throw new iwt("HiAggregateOption types is not same category");
            }
        }
    }

    /* renamed from: iwo$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            d = iArr;
            try {
                iArr[HiHealthDataType.Category.SESSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HiHealthDataType.Category.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[HiHealthDataType.Category.SEQUENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[HiHealthDataType.Category.STAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                d[HiHealthDataType.Category.SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static void d(HiHealthDataType.Category category, int i, int i2) throws iwt {
        int i3 = AnonymousClass3.d[category.ordinal()];
        if (i3 == 1) {
            if (i2 > 0) {
                throw new iwt("HiAggregateOption types is SESSION alignType should equal O");
            }
            if (i != 1 && i != 6) {
                throw new iwt("HiAggregateOption types is SESSION aggregateType should be SUM or CLUSTER_COUNT");
            }
            return;
        }
        if (i3 != 2) {
            if (i3 == 3) {
                if (i2 > 0) {
                    throw new iwt("HiAggregateOption types is SEQUENCE alignType should equal O");
                }
                if (i != 2) {
                    throw new iwt("HiAggregateOption types is SEQUENCE aggregateType should be SUM");
                }
                return;
            }
            if (i3 != 4) {
                if (i3 != 5) {
                    throw new iwt("HiAggregateOption types is out of rang ");
                }
            } else if (i2 > 0) {
                throw new iwt("HiAggregateOption types is STAT alignType should equal O");
            }
        }
    }

    private static void e(long j, int i) throws iwt {
        switch (i) {
            case 0:
            case 7:
                return;
            case 1:
            case 2:
                d(j);
                return;
            case 3:
                b(j);
                return;
            case 4:
                e(j);
                return;
            case 5:
                a(j);
                return;
            case 6:
                c(j);
                return;
            default:
                throw new iwt("HiAggregateOption groupUnitType is out of rang ");
        }
    }

    private static void d(long j) throws iwt {
        if (j > 90000000) {
            throw new iwt("HiAggregateOption groupUnitType is MINUTE or HOUR endTime - startTime should < one day");
        }
    }

    private static void b(long j) throws iwt {
        if (j < 82740000) {
            throw new iwt("HiAggregateOption groupUnitType is DAY endTime - startTime should >= one day");
        }
    }

    private static void e(long j) throws iwt {
        if (j < 601140000) {
            throw new iwt("HiAggregateOption groupUnitType is WEEK endTime - startTime should >= one week");
        }
    }

    private static void a(long j) throws iwt {
        if (j < 2415540000L) {
            throw new iwt("HiAggregateOption groupUnitType is MONTH endTime - startTime should >= one month");
        }
    }

    private static void c(long j) throws iwt {
        if (j < 31532340000L) {
            throw new iwt("HiAggregateOption groupUnitType is YEAR endTime - startTime should >= one year");
        }
    }

    private static void b(int i) throws iwt {
        if (i < 0 || i > 7) {
            throw new iwt("HiAggregateOption aggregateType has input a invalid type");
        }
    }
}
