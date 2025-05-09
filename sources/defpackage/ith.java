package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthDataReq;
import com.huawei.hwcloudmodel.model.unite.AddHealthDataRsp;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class ith {

    /* renamed from: a, reason: collision with root package name */
    private static Context f13597a;
    private ijn b;
    private jbs c;
    private ijj d;
    private int e;

    private ith() {
        this.e = 0;
        this.d = ijj.a(f13597a);
        this.b = ijn.a(f13597a);
        this.c = jbs.a(f13597a);
    }

    static class b {
        private static final ith e = new ith();
    }

    public static ith b(Context context) {
        f13597a = context.getApplicationContext();
        return b.e;
    }

    public void d(int i, HealthDataSwitch healthDataSwitch) {
        LogUtil.a("HiH_HiSyncHealthData", "pushData() begin !");
        this.e = 0;
        if (!ism.m()) {
            LogUtil.h("Debug_HiSyncHealthData", "pushData() dataPrivacy switch is closed ,can not pushData right now ,push end !");
            return;
        }
        ivc.c(2.0d, "SYNC_HEALTH_DATA_UPLOAD_PERCENT_MAX");
        List<Integer> i2 = iis.d().i(i);
        if (i2 != null && !i2.isEmpty()) {
            LogUtil.a("HiH_HiSyncHealthData", "clientid list size =", Integer.valueOf(i2.size()));
            int size = i2.size();
            for (Integer num : i2) {
                a(num.intValue(), healthDataSwitch);
                e(num.intValue(), healthDataSwitch);
            }
            ivc.b(f13597a, 1.0d, 1.0d / size, 2.0d);
            ivc.b(f13597a);
            LogUtil.a("HiH_HiSyncHealthData", "pushData() end !");
            return;
        }
        LogUtil.h("Debug_HiSyncHealthData", "pushData() end ! no client get, maybe no data need to pushData");
    }

    private void a(int i, HealthDataSwitch healthDataSwitch) {
        List<HiHealthData> e;
        while (this.e < 2 && (e = e(i)) != null && !e.isEmpty() && e(e, i, healthDataSwitch)) {
            e(e);
        }
        this.e = 0;
        LogUtil.c("Debug_HiSyncHealthData", "uploadSportData upLoadFailCount = ", 0);
    }

    private void e(int i, HealthDataSwitch healthDataSwitch) {
        List<HiHealthData> d;
        while (this.e < 2 && (d = d(i)) != null && !d.isEmpty() && e(d, i, healthDataSwitch)) {
            c(d);
        }
        this.e = 0;
        LogUtil.c("Debug_HiSyncHealthData", "uploadSportData upLoadFailCount = ", 0);
    }

    private List<HiHealthData> d(int i) {
        return this.b.b(i, Arrays.asList(22001, 22002, 22003), 50);
    }

    private List<HiHealthData> e(int i) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(2002);
        return this.d.c(i, arrayList, 50);
    }

    private void c(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            this.b.d(hiHealthData.getDataId(), hiHealthData.getLong("modified_time"), 1);
        }
    }

    private void e(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            this.d.e(hiHealthData.getDataId(), hiHealthData.getLong("modified_time"), 1);
        }
    }

    private boolean e(List<HiHealthData> list, int i, HealthDataSwitch healthDataSwitch) {
        List<HealthDetail> b2 = healthDataSwitch.b(list, i, 0);
        if (b2 == null || b2.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthData", "addHealthData sportDetails is null or empty");
            return false;
        }
        AddHealthDataReq addHealthDataReq = new AddHealthDataReq();
        addHealthDataReq.setDetailInfo(b2);
        addHealthDataReq.setTimeZone(list.get(0).getTimeZone());
        while (this.e < 2) {
            AddHealthDataRsp d = this.c.d(addHealthDataReq);
            if (d == null) {
                LogUtil.h("Debug_HiSyncHealthData", "addHealthData addHealthDataRsp is null");
                LogUtil.h("Debug_HiSyncHealthData", "addHealthData pushData healthData failed , upLoadFailCount = ", Integer.valueOf(this.e));
                this.e++;
            } else {
                if (d.getResultCode().intValue() == 0) {
                    return true;
                }
                this.e++;
                LogUtil.h("Debug_HiSyncHealthData", "addHealthData addHealthDataRsp resultCode is ", d.getResultCode());
                LogUtil.h("Debug_HiSyncHealthData", "addHealthData pushData healthData failed , upLoadFailCount = ", Integer.valueOf(this.e));
            }
        }
        return false;
    }
}
