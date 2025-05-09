package defpackage;

import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.DataReportModel;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class iqq {
    private static Bundle f = new Bundle();
    private static Bundle c = new Bundle();
    private static Bundle i = new Bundle();
    private static ConcurrentHashMap<String, List<IRegisterRealTimeCallback>> d = new ConcurrentHashMap<>();
    private static Map<String, Long> b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Long> f13543a = new HashMap();
    private static Map<String, Integer> e = new HashMap();

    public static void bBW_(DataReportModel dataReportModel, int i2, RemoteCallbackList<IRegisterRealTimeCallback> remoteCallbackList) {
        int reportType = dataReportModel.getReportType();
        int reportValue = dataReportModel.getReportValue();
        int maxReportValue = dataReportModel.getMaxReportValue();
        String dataReportModel2 = dataReportModel.toString();
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        boolean z = false;
        if (reportType == CharacteristicConstant.ReportType.TARGET.getReportTypeValue()) {
            if (d.get(dataReportModel2) != null && !d.get(dataReportModel2).isEmpty()) {
                z = true;
            }
            if (i2 < reportValue || i2 > maxReportValue) {
                if (z) {
                    ReleaseLogUtil.e("AutoReportHandler", "hasCheckCallback clear key = ", dataReportModel2, " realValue = ", Integer.valueOf(i2));
                    d.get(dataReportModel2).clear();
                    return;
                }
                return;
            }
            Long l = b.get(dataReportModel2);
            if (l == null || !iqr.d(l.longValue(), valueOf.longValue()) || z) {
                b.put(dataReportModel2, valueOf);
                bBY_(dataReportModel, reportValue, remoteCallbackList, z);
                return;
            } else {
                ReleaseLogUtil.e("HiH_AutoReportHandler", "not need report data: key = ", dataReportModel2, " realValue = ", Integer.valueOf(i2), "time = ", l);
                return;
            }
        }
        if (reportType == CharacteristicConstant.ReportType.VALUE_INTERVAL.getReportTypeValue()) {
            Long l2 = f13543a.get(dataReportModel2);
            if (l2 == null || !iqr.d(l2.longValue(), valueOf.longValue())) {
                LogUtil.a("AutoReportHandler", "isSameDay = false");
                f13543a.put(dataReportModel2, valueOf);
                e.put(dataReportModel2, 0);
            }
            if (i2 - e.get(dataReportModel2).intValue() >= reportValue) {
                f13543a.put(dataReportModel2, valueOf);
                e.put(dataReportModel2, Integer.valueOf((i2 / reportValue) * reportValue));
                bBY_(dataReportModel, i2, remoteCallbackList, false);
                return;
            }
            return;
        }
        ReleaseLogUtil.d("HiH_AutoReportHandler", "unsupported report type");
    }

    private static void bBY_(DataReportModel dataReportModel, int i2, RemoteCallbackList<IRegisterRealTimeCallback> remoteCallbackList, boolean z) {
        if (dataReportModel.getDataType() == CharacteristicConstant.ReportDataType.DATA_POINT_STEP_SUM.getDataType()) {
            if (f == null) {
                f = new Bundle();
            }
            f.putInt("step", i2);
            bBX_(dataReportModel, f, remoteCallbackList, z);
            return;
        }
        if (dataReportModel.getDataType() == CharacteristicConstant.ReportDataType.DATA_POINT_CALORIES_SUM.getDataType()) {
            if (c == null) {
                c = new Bundle();
            }
            c.putInt("calorie", i2);
            bBX_(dataReportModel, c, remoteCallbackList, z);
            return;
        }
        if (dataReportModel.getDataType() == CharacteristicConstant.ReportDataType.DATA_POINT_DISTANCE_SUM.getDataType()) {
            if (i == null) {
                i = new Bundle();
            }
            i.putInt("distance", i2);
            bBX_(dataReportModel, i, remoteCallbackList, z);
            return;
        }
        ReleaseLogUtil.d("HiH_AutoReportHandler", "invalid data type");
    }

    private static void bBX_(DataReportModel dataReportModel, Bundle bundle, RemoteCallbackList<IRegisterRealTimeCallback> remoteCallbackList, boolean z) {
        if (z) {
            LogUtil.h("AutoReportHandler", "hasCheckCallback size = ", Integer.valueOf(d.get(dataReportModel.toString()).size()));
            Iterator<IRegisterRealTimeCallback> it = d.get(dataReportModel.toString()).iterator();
            while (it.hasNext()) {
                try {
                    try {
                        it.next().onDataChanged(bundle);
                    } finally {
                        it.remove();
                    }
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c("HiH_AutoReportHandler", "hasCheckCallback RemoteException: ", e2.getMessage());
                }
            }
            return;
        }
        synchronized (remoteCallbackList) {
            try {
                int beginBroadcast = remoteCallbackList.beginBroadcast();
                LogUtil.a("AutoReportHandler", "calling remote callback, the length is ", Integer.valueOf(beginBroadcast));
                for (int i2 = 0; i2 < beginBroadcast; i2++) {
                    try {
                        remoteCallbackList.getBroadcastItem(i2).onDataChanged(bundle);
                    } catch (RemoteException e3) {
                        ReleaseLogUtil.c("HiH_AutoReportHandler", "report RemoteException: ", e3.getMessage());
                    }
                }
                remoteCallbackList.finishBroadcast();
            } catch (IllegalStateException e4) {
                ReleaseLogUtil.c("HiH_AutoReportHandler", "report IllegalStateException", LogAnonymous.b((Throwable) e4));
                if (dataReportModel.getReportType() == CharacteristicConstant.ReportType.TARGET.getReportTypeValue()) {
                    b.remove(dataReportModel.toString());
                }
            }
        }
    }

    public static void a(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) {
        String dataReportModel2 = dataReportModel.toString();
        Long l = b.get(dataReportModel2);
        if (l == null || !iqr.d(l.longValue(), System.currentTimeMillis())) {
            return;
        }
        if (d.get(dataReportModel2) == null) {
            d.put(dataReportModel2, Collections.synchronizedList(new ArrayList()));
        }
        d.get(dataReportModel2).add(iRegisterRealTimeCallback);
    }

    public static void e(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) {
        String dataReportModel2 = dataReportModel.toString();
        if (d.get(dataReportModel2) != null) {
            d.get(dataReportModel2).remove(iRegisterRealTimeCallback);
        }
    }
}
