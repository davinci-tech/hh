package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSyncVersionsReq;
import com.huawei.hwcloudmodel.model.unite.GetSyncVersionsRsp;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwcloudmodel.model.userprofile.DeviceInfo;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class iuw {
    static List<SyncKey> b(Context context, int i, int i2) {
        if (context == null) {
            return null;
        }
        SyncKey syncKey = new SyncKey();
        syncKey.setDataType(Integer.valueOf(i));
        syncKey.setType(Integer.valueOf(i2));
        syncKey.setVersion(Long.valueOf(System.currentTimeMillis()));
        syncKey.setDeviceCode(0L);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(syncKey);
        return arrayList;
    }

    static HiDeviceInfo a(Context context, long j) throws iut {
        if (context == null || j == 0) {
            return null;
        }
        GetBindDeviceReq getBindDeviceReq = new GetBindDeviceReq();
        getBindDeviceReq.setDeviceCode(Long.valueOf(j));
        GetBindDeviceRsp c = jbs.a(context).c(getBindDeviceReq);
        if (!ius.a(c, true)) {
            LogUtil.b("HiH_HiSyncUtil", "getOneBindDevice error,no device get from cloud");
            return ivd.c().a(j);
        }
        List<DeviceInfo> deviceInfos = c.getDeviceInfos();
        if (deviceInfos == null || deviceInfos.isEmpty()) {
            LogUtil.b("HiH_HiSyncUtil", "getOneBindDevice error,deviceInfos is null or empty");
            return ivd.c().a(j);
        }
        DeviceInfo deviceInfo = deviceInfos.get(0);
        if (deviceInfo == null) {
            LogUtil.b("HiH_HiSyncUtil", "getOneBindDevice error,deviceInfo is null");
            return ivd.c().a(j);
        }
        return ivd.c().c(deviceInfo);
    }

    static DeviceInfo c(Context context, long j) throws iut {
        if (context != null && j != 0) {
            GetBindDeviceReq getBindDeviceReq = new GetBindDeviceReq();
            getBindDeviceReq.setDeviceCode(Long.valueOf(j));
            GetBindDeviceRsp c = jbs.a(context).c(getBindDeviceReq);
            if (!ius.a(c, true)) {
                LogUtil.b("HiH_HiSyncUtil", "getCloudBindDevice error,no device get from cloud");
                return null;
            }
            List<DeviceInfo> deviceInfos = c.getDeviceInfos();
            if (deviceInfos == null || deviceInfos.isEmpty()) {
                LogUtil.b("HiH_HiSyncUtil", "getCloudBindDevice error,deviceInfos is null or empty");
            } else {
                return deviceInfos.get(0);
            }
        }
        return null;
    }

    static SparseArray<Integer> bCE_(long j, long j2, int i) {
        if (j > j2 || j < 1388509200000L || i < 1) {
            LogUtil.h("HiH_HiSyncUtil", "divideDate error input startTime is ", Long.valueOf(j), " , endTime is ", Long.valueOf(j2), " , range is ", Integer.valueOf(i));
            return null;
        }
        if (HiDateUtil.c(j) < 20140101) {
            j = HiDateUtil.a(20140101);
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar2.setTimeInMillis(j2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SparseArray<Integer> sparseArray = new SparseArray<>();
        while (true) {
            try {
                Integer valueOf = Integer.valueOf(Integer.parseInt(simpleDateFormat.format(calendar.getTime())));
                calendar.add(5, i);
                if (!calendar.before(calendar2)) {
                    calendar.add(5, -i);
                    sparseArray.put(valueOf.intValue(), Integer.valueOf(Integer.parseInt(simpleDateFormat.format(calendar2.getTime()))));
                    return sparseArray;
                }
                int parseInt = Integer.parseInt(simpleDateFormat.format(calendar.getTime()));
                calendar.add(5, 1);
                sparseArray.put(valueOf.intValue(), Integer.valueOf(parseInt));
            } catch (Exception e) {
                ReleaseLogUtil.c("HiH_HiSyncUtil", "divideDate date change Exception", LogAnonymous.b((Throwable) e));
                return null;
            }
        }
    }

    static List<SyncKey> b(Context context, int i, List<Integer> list) throws iut {
        if (context == null || list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        for (Integer num : list) {
            SyncKey syncKey = new SyncKey();
            syncKey.setDataType(Integer.valueOf(i));
            syncKey.setType(num);
            arrayList.add(syncKey);
        }
        GetSyncVersionsReq getSyncVersionsReq = new GetSyncVersionsReq();
        getSyncVersionsReq.setSyncKeys(arrayList);
        GetSyncVersionsRsp d = jbs.a(context).d(getSyncVersionsReq);
        LogUtil.a("HiH_HiSyncUtil", "getVersionByType Rsp is ", d);
        ius.a(d, true);
        return d.getVersions();
    }

    static Map<Integer, Long> e(Context context, List<Integer> list) {
        if (context != null && list != null && !list.isEmpty()) {
            iud iudVar = new iud();
            iudVar.c(list);
            iug iugVar = (iug) lqi.d().d(iudVar.getUrl(), jbs.a(context).d().getHeaders(), lql.b(jbs.a(context).d().getBody(iudVar)), iug.class);
            LogUtil.a("HiH_HiSyncUtil", "getBusinessDataVersion Rsp is ", iugVar);
            try {
                ius.a(iugVar, true);
                if (iugVar == null) {
                    return null;
                }
                return iugVar.a();
            } catch (iut e) {
                ReleaseLogUtil.e("HiH_HiSyncUtil", "getBusinessDataVersion syncException ", e.getMessage());
            }
        }
        return null;
    }
}
