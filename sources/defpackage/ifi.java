package defpackage;

import android.os.Parcel;
import android.util.Log;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.device.HiHealthDeviceInfo;
import com.huawei.hihealthkit.data.type.HiHealthDataType;
import com.huawei.nfc.PluginPayAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class ifi {
    public static boolean e(List list) {
        if (list == null) {
            return false;
        }
        int b = b(list);
        Log.i("HiHealthKitCommonUtil", "dataList size = " + list.size() + ", parcelSize = " + b);
        return b >= 524288;
    }

    public static int b(List list) {
        Parcel obtain = Parcel.obtain();
        obtain.writeList(list);
        int dataSize = obtain.dataSize();
        obtain.recycle();
        return dataSize;
    }

    public static HiHealthKitData d(idy idyVar, HiHealthDataType.Category category) {
        HiHealthKitData hiHealthKitData = new HiHealthKitData();
        hiHealthKitData.setContentValues(idyVar.getValueHolder());
        hiHealthKitData.setStartTime(idyVar.getStartTime());
        hiHealthKitData.setEndTime(idyVar.getEndTime());
        hiHealthKitData.setUpdateTime(idyVar.getUpdateTime());
        hiHealthKitData.setType(idyVar.getType());
        hiHealthKitData.putString("metadata", idyVar.getMetaData());
        HiHealthDeviceInfo sourceDevice = idyVar.getSourceDevice();
        if (sourceDevice != null) {
            hiHealthKitData.putString("device_uniquecode", sourceDevice.getDeviceUniqueCode());
            hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, sourceDevice.getDeviceName());
            hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, sourceDevice.getDeviceModel());
            hiHealthKitData.putString("deviceType", sourceDevice.getDeviceType());
        }
        c(idyVar, hiHealthKitData, category);
        return hiHealthKitData;
    }

    /* renamed from: ifi$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f13336a;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            f13336a = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13336a[HiHealthDataType.Category.STAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13336a[HiHealthDataType.Category.REALTIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13336a[HiHealthDataType.Category.SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13336a[HiHealthDataType.Category.SEQUENCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13336a[HiHealthDataType.Category.BUSINESS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13336a[HiHealthDataType.Category.SESSION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static void c(idy idyVar, HiHealthKitData hiHealthKitData, HiHealthDataType.Category category) {
        try {
            switch (AnonymousClass3.f13336a[category.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    idz idzVar = (idz) idyVar;
                    if (ifa.e(idzVar.getType())) {
                        hiHealthKitData.setDoubleValue(Double.valueOf(idzVar.getDoubleValue()));
                        break;
                    } else {
                        hiHealthKitData.setValue(idzVar.getValue());
                        break;
                    }
                case 4:
                case 5:
                case 6:
                    if (!(idyVar instanceof idx)) {
                        Log.w("HiHealthKitCommonUtil", "wrong data type for set data");
                        break;
                    } else {
                        hiHealthKitData.setMap(((idx) idyVar).getMap());
                        break;
                    }
                case 7:
                    Log.i("HiHealthKitCommonUtil", "nothing to do");
                    break;
                default:
                    Log.w("HiHealthKitCommonUtil", "wrong data type");
                    break;
            }
        } catch (ClassCastException unused) {
            Log.w("HiHealthKitCommonUtil", "class cast exception");
        }
    }
}
