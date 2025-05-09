package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.DataReportModel;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealthservice.hihealthkit.model.DataObservable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class ipx extends DataObservable<IRegisterRealTimeCallback> {
    private static Map<Integer, String> dataTypeToBundleKey;
    private static Map<String, Long> sTargetCache = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        dataTypeToBundleKey = hashMap;
        hashMap.put(Integer.valueOf(CharacteristicConstant.ReportDataType.DATA_POINT_STEP_SUM.getDataType()), "step");
        dataTypeToBundleKey.put(Integer.valueOf(CharacteristicConstant.ReportDataType.DATA_POINT_CALORIES_SUM.getDataType()), "carior");
        dataTypeToBundleKey.put(Integer.valueOf(CharacteristicConstant.ReportDataType.DATA_POINT_DISTANCE_SUM.getDataType()), "distance");
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservable
    public void notifyDataChanged(Object obj) {
        if (obj instanceof Bundle) {
            for (String str : this.mObservers.keySet()) {
                String[] split = str.split("#");
                int intValue = Integer.valueOf(split[0]).intValue();
                iqq.bBW_(new DataReportModel(intValue, Integer.valueOf(split[1]).intValue(), Integer.valueOf(split[2]).intValue(), Integer.valueOf(split[3]).intValue()), ((Bundle) obj).getInt(dataTypeToBundleKey.get(Integer.valueOf(intValue))), (RemoteCallbackList) this.mObservers.get(str));
            }
        }
    }

    public void registerObserver(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) {
        super.registerObserver((Parcelable) dataReportModel, (DataReportModel) iRegisterRealTimeCallback);
        iqq.a(dataReportModel, iRegisterRealTimeCallback);
    }

    public void unregisterObserver(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) {
        super.unregisterObserver((Parcelable) dataReportModel, (DataReportModel) iRegisterRealTimeCallback);
        iqq.e(dataReportModel, iRegisterRealTimeCallback);
    }
}
