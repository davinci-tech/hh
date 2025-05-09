package defpackage;

import android.content.Context;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hwcloudmodel.model.unite.DataDeleteCondition;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ivi {
    private static List<String> d = new ArrayList(10);

    static HiAggregateOption d() {
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setType(new int[]{2, 4});
        hiAggregateOption.setConstantsKey(new String[]{"step", "calorie"});
        return hiAggregateOption;
    }

    private static LatLng a(iuv iuvVar) {
        return new LatLng(iuvVar.c, iuvVar.e);
    }

    static double[] e(Context context, double[] dArr) {
        if (context != null && dArr != null && dArr.length >= 2) {
            CoordinateConverter coordinateConverter = new CoordinateConverter(context);
            coordinateConverter.from(CoordinateConverter.CoordType.GPS);
            coordinateConverter.coord(a(new iuv(dArr[0], dArr[1])));
            LatLng convert = coordinateConverter.convert();
            if (convert != null) {
                dArr[0] = convert.latitude;
                dArr[1] = convert.longitude;
            }
        }
        return dArr;
    }

    static List<DataDeleteCondition> a(Context context, List<HiHealthData> list) {
        int b;
        if (list == null) {
            return new ArrayList();
        }
        d.clear();
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            int clientId = hiHealthData.getClientId();
            ikv f = iis.d().f(clientId);
            if (f == null) {
                LogUtil.h("HiH_HiSyncUtilHelper", "createDelMap,nothing need to del ,no healthContext,clientId is ", Integer.valueOf(clientId));
            } else {
                long a2 = f.a();
                if (a2 <= 0) {
                    LogUtil.h("HiH_HiSyncUtilHelper", "createDelMap,nothing need to del ,no deviceCode,clientId is ", Integer.valueOf(clientId));
                } else {
                    int type = hiHealthData.getType();
                    if (iup.g(type)) {
                        b = iup.b(type);
                        if (b <= 0) {
                            LogUtil.h("HiH_HiSyncUtilHelper", "createDelMap , error type no such type can delete ,type is ", Integer.valueOf(type));
                        } else {
                            d(arrayList, hiHealthData, a2, b);
                        }
                    } else {
                        HiHealthDictType c = c(context, type);
                        if (c != null) {
                            b = c.i();
                            if (c.g() != 0) {
                                b = 30001;
                            }
                            String str = b + "_" + hiHealthData.getStartTime() + "_" + hiHealthData.getEndTime() + "_" + a2;
                            if (!d.contains(str)) {
                                d.add(str);
                            }
                        } else {
                            b = 0;
                        }
                        d(arrayList, hiHealthData, a2, b);
                    }
                }
            }
        }
        return arrayList;
    }

    private static void d(List<DataDeleteCondition> list, HiHealthData hiHealthData, long j, int i) {
        DataDeleteCondition dataDeleteCondition = new DataDeleteCondition();
        dataDeleteCondition.setDeviceCode(Long.valueOf(j));
        dataDeleteCondition.setStartTime(Long.valueOf(hiHealthData.getStartTime()));
        dataDeleteCondition.setEndTime(Long.valueOf(hiHealthData.getEndTime()));
        if (i == 0) {
            dataDeleteCondition.setType(null);
        } else {
            dataDeleteCondition.setType(Integer.valueOf(i));
        }
        if (hiHealthData.getType() == 2002) {
            dataDeleteCondition.setKey("DATA_POINT_DYNAMIC_HEARTRATE");
        }
        if (hiHealthData.getType() == 2018) {
            dataDeleteCondition.setKey("DATA_POINT_REST_HEARTRATE");
        }
        if (hiHealthData.getType() == 2105) {
            dataDeleteCondition.setKey("DATA_POINT_NEW_REST_HEARTRATE");
        }
        LogUtil.c("HiH_HiSyncUtilHelper", "addConditon conditon = ", dataDeleteCondition);
        list.add(dataDeleteCondition);
    }

    /* renamed from: ivi$1, reason: invalid class name */
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
                b[HiHealthDataType.Category.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static HiHealthDictType c(Context context, int i) {
        int i2 = AnonymousClass1.b[HiHealthDataType.e(i).ordinal()];
        if (i2 == 1) {
            return HiHealthDictManager.d(context).f(i);
        }
        if (i2 == 2) {
            return HiHealthDictManager.d(context).d(i);
        }
        LogUtil.h("HiH_HiSyncUtilHelper", "unknow category, type is ", Integer.valueOf(i));
        return null;
    }
}
