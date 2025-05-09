package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes6.dex */
public class qkl {
    public static qkg c(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("HealthDataInteractorUtil", "data is null");
            return null;
        }
        long endTime = hiHealthData.getEndTime();
        int type = hiHealthData.getType();
        double doubleValue = new BigDecimal(String.valueOf(hiHealthData.getDouble("point_value"))).setScale(1, RoundingMode.HALF_UP).doubleValue();
        qkg qkgVar = new qkg();
        qkgVar.a(endTime);
        qkgVar.b(hiHealthData.getModifiedTime());
        qkgVar.c(type);
        qkgVar.b(doubleValue);
        qkgVar.a(hiHealthData.getDouble("point_value"));
        qkgVar.b(hiHealthData.getMetaData());
        qkgVar.e(hiHealthData.getInt("trackdata_deviceType"));
        qkgVar.d(hiHealthData.getString("device_uniquecode"));
        qkgVar.b(hiHealthData.getClientId());
        LogUtil.c("HealthDataInteractorUtil", "bloodsugarData.time = ", Long.valueOf(qkgVar.h()));
        LogUtil.c("HealthDataInteractorUtil", "bloodsugarData.value1 = ", Double.valueOf(qkgVar.o()));
        LogUtil.c("HealthDataInteractorUtil", "bloodsugarData.value2 = ", Double.valueOf(qkgVar.m()));
        LogUtil.c("HealthDataInteractorUtil", "bloodsugarData.value3 = ", Double.valueOf(qkgVar.n()));
        return qkgVar;
    }

    public static qkg e(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("HealthDataInteractorUtil", "hiHealthData is null");
            return null;
        }
        LogUtil.c("HealthDataInteractorUtil", "addWeightData");
        qkg qkgVar = new qkg();
        qkgVar.a(hiHealthData.getStartTime());
        qkgVar.c(hiHealthData.getDouble("bodyWeight"));
        qkgVar.b(hiHealthData.getDouble(BleConstants.BODY_FAT_RATE));
        LogUtil.a("HealthDataInteractorUtil", Integer.valueOf(hiHealthData.getInt("trackdata_deviceType")));
        qkgVar.c(hiHealthData.getInt("trackdata_deviceType"));
        qkgVar.i(hiHealthData.getDouble(BleConstants.BMI));
        qkgVar.f(hiHealthData.getDouble(BleConstants.BASAL_METABOLISM));
        qkgVar.j(hiHealthData.getDouble(BleConstants.BODY_SCORE));
        qkgVar.g(hiHealthData.getDouble(BleConstants.BONE_SALT));
        qkgVar.l(hiHealthData.getDouble(BleConstants.MOISTURE));
        qkgVar.k(hiHealthData.getDouble(BleConstants.MOISTURE_RATE));
        qkgVar.m(hiHealthData.getDouble(BleConstants.MUSCLE_MASS));
        qkgVar.h(hiHealthData.getDouble(BleConstants.VISCERAL_FAT_LEVEL));
        qkgVar.n(dks.b(hiHealthData));
        return qkgVar;
    }
}
