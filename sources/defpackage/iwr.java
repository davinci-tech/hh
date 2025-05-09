package defpackage;

import androidx.core.location.LocationRequestCompat;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.data.type.HiHealthDataType;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class iwr {
    public static void c(HiDataReadOption hiDataReadOption) throws iwt {
        if (hiDataReadOption == null) {
            throw new iwt("HiDataReadOption is null");
        }
        b(hiDataReadOption.getType());
        d(hiDataReadOption.getAlignType());
        b(hiDataReadOption);
        int readType = hiDataReadOption.getReadType();
        if ((readType == 2 || readType == 3) && hiDataReadOption.getDeviceUuid() == null) {
            throw new iwt("readType is " + readType + " but deviceUUID is null");
        }
    }

    private static void b(HiDataReadOption hiDataReadOption) throws iwt {
        long startTime = hiDataReadOption.getStartTime();
        long endTime = hiDataReadOption.getEndTime();
        if (startTime < 0) {
            ReleaseLogUtil.d("HiH_HiDataReadValid", "startTime cannot be less than 0");
        }
        if (startTime > endTime) {
            throw new iwt("startTime > endTime");
        }
        long modifiedStartTime = hiDataReadOption.getModifiedStartTime();
        long modifiedEndTime = hiDataReadOption.getModifiedEndTime();
        if (modifiedStartTime < 0) {
            throw new iwt("modifiedStartTime cannot be less than 0");
        }
        if (modifiedStartTime > modifiedEndTime) {
            throw new iwt("modifiedStartTime > modifiedEndTime");
        }
        if (modifiedEndTime <= 0 || hiDataReadOption.getEndTime() > 0) {
            return;
        }
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(LocationRequestCompat.PASSIVE_INTERVAL);
    }

    private static void b(int[] iArr) throws iwt {
        if (iArr == null || iArr.length <= 0) {
            throw new iwt("types is null");
        }
        for (int i : iArr) {
            switch (AnonymousClass4.f13639a[HiHealthDataType.e(i).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                default:
                    throw new iwt("Unknown data type: " + i);
            }
        }
    }

    /* renamed from: iwr$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f13639a;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            f13639a = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13639a[HiHealthDataType.Category.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13639a[HiHealthDataType.Category.SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13639a[HiHealthDataType.Category.STAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13639a[HiHealthDataType.Category.SESSION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13639a[HiHealthDataType.Category.REALTIME.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13639a[HiHealthDataType.Category.CONFIG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f13639a[HiHealthDataType.Category.CONFIGSTAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f13639a[HiHealthDataType.Category.CHECK_DWONLOAD.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f13639a[HiHealthDataType.Category.BUSINESS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private static void d(int i) throws iwt {
        if (i <= 0 || i == 20001) {
            return;
        }
        throw new iwt("alignType is error" + i);
    }
}
