package com.huawei.hihealth.util;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.device.HiHealthDeviceInfo;
import com.huawei.hihealth.error.HiHealthError;
import com.huawei.hihealth.listener.IuniversalCallback;
import com.huawei.hihealthkit.data.type.HiHealthDataType;
import com.huawei.nfc.PluginPayAdapter;
import defpackage.idw;
import defpackage.idx;
import defpackage.idy;
import defpackage.idz;
import defpackage.iea;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class DictDataTransformUtil {
    private DictDataTransformUtil() {
    }

    public static void e(HiHealthDataType.Category category, List list, List list2) {
        Log.i("DictDataTransformUtil", "dealHiHealKitDicData hiHealthKitData size = " + list.size());
        for (Object obj : list) {
            if (obj instanceof HiHealthKitData) {
                HiHealthKitData hiHealthKitData = (HiHealthKitData) obj;
                int i = AnonymousClass1.b[category.ordinal()];
                if (i == 1) {
                    e(hiHealthKitData, list2);
                } else if (i == 2) {
                    a(hiHealthKitData, list2);
                } else if (i == 3) {
                    d(hiHealthKitData, list2);
                } else if (i == 4) {
                    b(hiHealthKitData, list2);
                }
            }
        }
    }

    /* renamed from: com.huawei.hihealth.util.DictDataTransformUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            b = iArr;
            try {
                iArr[HiHealthDataType.Category.SEQUENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[HiHealthDataType.Category.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[HiHealthDataType.Category.SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[HiHealthDataType.Category.SESSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void e(HiHealthKitData hiHealthKitData, List list) {
        String str;
        iea ieaVar = new iea();
        c(hiHealthKitData, ieaVar);
        try {
            str = com.huawei.hihealth.HiZipUtil.a(ieaVar.getSequenceData());
        } catch (IOException unused) {
            Log.w("DictDataTransformUtil", "uncompress detail data error");
            str = null;
        }
        ieaVar.setSequenceData(str);
        list.add(ieaVar);
    }

    private static void a(HiHealthKitData hiHealthKitData, List list) {
        idz idzVar = new idz();
        c(hiHealthKitData, idzVar);
        list.add(idzVar);
    }

    private static void d(HiHealthKitData hiHealthKitData, List list) {
        idx idxVar = new idx();
        c(hiHealthKitData, idxVar);
        list.add(idxVar);
    }

    private static void b(HiHealthKitData hiHealthKitData, List list) {
        idw idwVar = new idw();
        c(hiHealthKitData, idwVar);
        list.add(idwVar);
    }

    public static void a(int i, int i2, List list, IuniversalCallback iuniversalCallback) {
        if (i2 == 2) {
            if (i != 0) {
                iuniversalCallback.onResultHandler(i, null, HiHealthError.e(i));
            } else if (list.size() == 0) {
                iuniversalCallback.onResultHandler(i, Collections.EMPTY_LIST, HiHealthError.e(i));
            } else {
                iuniversalCallback.onResultHandler(i, list, HiHealthError.e(i));
            }
        }
    }

    private static void c(HiHealthKitData hiHealthKitData, idy idyVar) {
        idyVar.setType(hiHealthKitData.getType());
        idyVar.setStartTime(hiHealthKitData.getStartTime());
        idyVar.setEndTime(hiHealthKitData.getEndTime());
        idyVar.setValueHolder(hiHealthKitData.getContentValue());
        String string = hiHealthKitData.getString("device_uniquecode");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        idyVar.setSourceDevice(new HiHealthDeviceInfo(string, hiHealthKitData.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME), hiHealthKitData.getString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL)));
        idyVar.setUpdateTime(hiHealthKitData.getLong("update_time"));
    }
}
