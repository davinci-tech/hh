package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.bean.TaskConfigBean;
import com.huawei.basichealthmodel.cloud.bean.Record;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.service.SyncService;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes3.dex */
public class bck {
    public static void c(final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_SyncUtils", "host is empty");
            return;
        }
        final int b = DateFormatUtil.b(System.currentTimeMillis());
        final int h = CommonUtil.h(bao.e("flush_data_time"));
        if (h == 0) {
            h = azi.t();
        }
        if (h == 0) {
            LogUtil.h("HealthLife_SyncUtils", "user is not join");
        } else if (h == b) {
            LogUtil.a("HealthLife_SyncUtils", "data is new");
        } else {
            LogUtil.a("HealthLife_SyncUtils", "start to flush, startDay=", Integer.valueOf(h), " endDay=", Integer.valueOf(b));
            HandlerExecutor.d(new Runnable() { // from class: bcn
                @Override // java.lang.Runnable
                public final void run() {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: bcp
                        @Override // java.lang.Runnable
                        public final void run() {
                            bck.a(r1, r2, r3, r4);
                        }
                    });
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final String str, final int i, int i2, final String str2) {
        if (i >= i2) {
            LogUtil.a("HealthLife_SyncUtils", "flush end");
            bao.e("flush_data_time", String.valueOf(i2));
            ObserverManagerUtil.c("HEALTH_LIFE_CLOUD_SYNC_FINISH", new Object[0]);
            return;
        }
        awq.e().a(str2, i, new TaskDataListener() { // from class: bck.3
            @Override // com.huawei.basichealthmodel.listener.TaskDataListener
            public void onDataChange(int i3, SparseArray<HealthLifeBean> sparseArray) {
            }

            @Override // com.huawei.basichealthmodel.listener.TaskDataListener
            public void onAllDataChange(int i3, SparseArray<List<HealthLifeBean>> sparseArray) {
                long c = DateFormatUtil.c(i);
                bck.b(str, DateFormatUtil.b(jdl.c(c, 1, 0)), DateFormatUtil.b(jdl.b(c, 1, 0)), str2);
                if (!azi.ai()) {
                    LogUtil.h("HealthLife_SyncUtils", "flush isSupportStartService false");
                    return;
                }
                int b = DateFormatUtil.b(jdl.c(c, 1, 1));
                bao.e("flush_data_time", String.valueOf(b));
                LogUtil.a("HealthLife_SyncUtils", "flush day ", Integer.valueOf(i), " nextStartDate ", Integer.valueOf(b));
                try {
                    Context e = BaseApplication.e();
                    Intent intent = new Intent(e, (Class<?>) SyncService.class);
                    intent.putExtra("sync_type", 5);
                    intent.putExtra("sync_user_id", str2);
                    e.startService(intent);
                } catch (IllegalStateException | SecurityException e2) {
                    LogUtil.b("HealthLife_SyncUtils", "flush exception ", LogAnonymous.b(e2));
                }
            }
        });
    }

    public static long ni_(String str, boolean z, auj aujVar, SparseArray<TaskConfigBean> sparseArray) {
        if (aujVar == null || sparseArray == null) {
            LogUtil.b("HealthLife_SyncUtils", "parseLifeRecordJsonObject result or healthTaskConfigEntity is empty");
            return 0L;
        }
        LogUtil.c("HealthLife_SyncUtils", "parseLifeRecordJsonObject isByVersion = ", Boolean.valueOf(z), ", data =", aujVar.toString());
        int a2 = aujVar.a();
        if (a2 != 0) {
            LogUtil.b("HealthLife_SyncUtils", "getHealthLifeRecordByVersion resultCode != 0,resultCode=", Integer.valueOf(a2));
            return 0L;
        }
        long d = z ? aujVar.d() : 0L;
        if (auz.a(bcm.nz_(aujVar.b(), sparseArray), str) == -1) {
            LogUtil.b("HealthLife_SyncUtils", "parseLifeRecordJsonObject insertTaskList fail");
            return 0L;
        }
        if (z) {
            bao.e("health_life_last_version", String.valueOf(d));
        }
        return d;
    }

    public static void b(String str, int i, int i2, String str2) {
        if (!"true".equals(gmz.d().c(7)) || TextUtils.isEmpty(str)) {
            LogUtil.a("HealthLife_SyncUtils", "hasUploadHealthLifeRecord privacyHealthData false or host is empty");
            return;
        }
        List<HealthLifeBean> b = auz.b(i, i2, str2);
        LogUtil.c("HealthLife_SyncUtils", "uploadHealthLifeRecord list is ", b.toString());
        if (koq.b(b)) {
            LogUtil.h("HealthLife_SyncUtils", "uploadHealthLifeRecord list is empty");
            bao.c("update_cloud_data_time", System.currentTimeMillis());
            return;
        }
        List<List<HealthLifeBean>> c = c(b);
        if (c.size() == 0) {
            LogUtil.a("HealthLife_SyncUtils", "uploadHealthLifeRecord sGroupRecordList size 0");
            return;
        }
        for (List<HealthLifeBean> list : c) {
            if (!BaseApplication.j()) {
                break;
            }
            if (!d(aue.e().e(str, d(list)), list, str2)) {
                return;
            }
        }
        bao.c("update_cloud_data_time", System.currentTimeMillis());
    }

    private static boolean d(auh auhVar, List<HealthLifeBean> list, String str) {
        if (auhVar == null) {
            LogUtil.h("HealthLife_SyncUtils", "parseUploadRecordResult result is empty");
            return false;
        }
        LogUtil.c("HealthLife_SyncUtils", "parseUploadRecordResult result = ", auhVar.toString());
        int a2 = auhVar.a();
        if (a2 != 0) {
            LogUtil.b("HealthLife_SyncUtils", "addHealthLifeRecord resultCode != 0,resultCode=", Integer.valueOf(a2));
            return false;
        }
        Iterator<HealthLifeBean> it = list.iterator();
        while (it.hasNext()) {
            it.next().setSyncStatus(1);
        }
        auz.d(list, str);
        return true;
    }

    private static List<List<HealthLifeBean>> c(List<HealthLifeBean> list) {
        ArrayList arrayList = new ArrayList(16);
        while (list.size() > 0) {
            HealthLifeBean healthLifeBean = list.get(0);
            int recordDay = healthLifeBean.getRecordDay();
            TimeZone d = jdl.d(healthLifeBean.getTimeZone());
            ArrayList arrayList2 = new ArrayList(16);
            for (HealthLifeBean healthLifeBean2 : list) {
                if (healthLifeBean2 != null) {
                    TimeZone d2 = jdl.d(healthLifeBean2.getTimeZone());
                    if (jdl.c(DateFormatUtil.c(recordDay, d), d, DateFormatUtil.c(healthLifeBean2.getRecordDay(), d2), d2) >= 10) {
                        break;
                    }
                    arrayList2.add(healthLifeBean2);
                }
            }
            arrayList.add(arrayList2);
            if (arrayList2.size() > 0) {
                list.subList(0, arrayList2.size()).clear();
            }
        }
        return arrayList;
    }

    public static List<Record> d(List<HealthLifeBean> list) {
        ArrayList arrayList = new ArrayList(16);
        if (list == null) {
            LogUtil.b("HealthLife_SyncUtils", "convertTaskRecordDbBean taskRecordDbBeanList is null");
            return arrayList;
        }
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                int id = healthLifeBean.getId();
                int complete = healthLifeBean.getComplete();
                String result = healthLifeBean.getResult();
                Record record = new Record();
                d(healthLifeBean, record);
                if (id == 5 && complete > 0 && TextUtils.isEmpty(result)) {
                    result = String.valueOf(0);
                }
                record.setResult(result);
                record.setId(id);
                record.setIsComplete(complete);
                record.setRecordDay(healthLifeBean.getRecordDay());
                record.setIsRest(healthLifeBean.getRest());
                record.setTimestamp(healthLifeBean.getTimestamp());
                record.setTimeZone(healthLifeBean.getTimeZone());
                int challengeId = healthLifeBean.getChallengeId();
                record.setChallengeId(challengeId >= 200001 ? Integer.valueOf(challengeId) : null);
                int taskType = healthLifeBean.getTaskType();
                record.setTaskType(taskType != 0 ? Integer.valueOf(taskType) : null);
                arrayList.add(record);
            }
        }
        return arrayList;
    }

    private static void d(HealthLifeBean healthLifeBean, Record record) {
        String target = healthLifeBean.getTarget();
        if (TextUtils.isEmpty(target)) {
            LogUtil.h("HealthLife_SyncUtils", "filterTarget target ", target);
            record.setTarget("");
            return;
        }
        int id = healthLifeBean.getId();
        if (id != 1 && id != 100001) {
            record.setTarget(target);
            return;
        }
        String[] split = target.split(",");
        if (koq.b(split, 0)) {
            LogUtil.h("HealthLife_SyncUtils", "filterTarget splitArray ", split);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            int h = CommonUtils.h(str);
            if (h > 1 && h != 100001) {
                arrayList.add(Integer.valueOf(h));
            }
        }
        record.setTarget(arrayList.toString().replace("[", "").replace("]", "").replace(" ", ""));
    }
}
