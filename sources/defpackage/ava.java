package defpackage;

import android.text.TextUtils;
import com.google.protobuf.DescriptorProtos;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.devicelink.bean.CloverProgress;
import com.huawei.basichealthmodel.devicelink.bean.NoticesBean;
import com.huawei.basichealthmodel.devicelink.bean.ResponseMsgBody;
import com.huawei.basichealthmodel.devicelink.bean.TaskBean;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class ava {

    /* renamed from: a, reason: collision with root package name */
    private static String f245a;
    private static final int[] b = {1, 100001};

    public static void a(int i, String str, int i2, boolean z, ResponseCallback<ResponseMsgBody> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_DeviceTaskManager", "requestTasksMessage callback is null");
            return;
        }
        if (!Utils.k()) {
            LogUtil.h("HealthLife_DeviceTaskManager", "requestTasksMessage is not support HealthModel");
            responseCallback.onResponse(-1, bae.b(2001, 10003, ""));
            return;
        }
        if (!azi.aa()) {
            LogUtil.h("HealthLife_DeviceTaskManager", "requestTasksMessage not joined or upgradeTime is 0");
            responseCallback.onResponse(-1, bae.b(2001, 10001, ""));
            return;
        }
        int q = azi.q();
        LogUtil.a("HealthLife_DeviceTaskManager", "requestTasksMessage recordDay ", Integer.valueOf(i), " requestTaskIds ", str, " pageSize ", Integer.valueOf(i2), " upgradeTime ", Integer.valueOf(q));
        int b2 = i <= 0 ? DateFormatUtil.b(System.currentTimeMillis()) : i;
        if (b2 < q || q <= 0) {
            responseCallback.onResponse(DescriptorProtos.Edition.EDITION_99999_TEST_ONLY_VALUE, bae.b(2001, 10004, ""));
            return;
        }
        List arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList = Arrays.asList(str.split(","));
        }
        d(b2, arrayList, i2, z, responseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final int i, final List<String> list, final int i2, final boolean z, final ResponseCallback<ResponseMsgBody> responseCallback) {
        HealthLifeBean healthLifeBean;
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelGetTaskList", new Runnable() { // from class: avg
                @Override // java.lang.Runnable
                public final void run() {
                    ava.d(i, list, i2, z, responseCallback);
                }
            });
            return;
        }
        if (z) {
            List<HealthLifeBean> d = bda.d();
            LogUtil.a("HealthLife_DeviceTaskManager", "getTaskData recordDay ", Integer.valueOf(i), " list ", d);
            if (koq.c(d) && (healthLifeBean = d.get(0)) != null && healthLifeBean.getRecordDay() == i) {
                Collections.sort(d, new bay());
                b(i, list, i2, d, responseCallback);
                return;
            }
        }
        awq.e().b(i, new TaskDayDataListener() { // from class: ava.1
            @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
            public void onAllDataChange(int i3, List<HealthLifeBean> list2) {
                LogUtil.a("HealthLife_DeviceTaskManager", "getTaskData onAllDataChange result ", Integer.valueOf(i3), " list ", list2);
                if (i3 == -1 || koq.b(list2) || list2.size() < 3) {
                    ResponseCallback.this.onResponse(-1, bae.b(2001, -1, ""));
                } else {
                    Collections.sort(list2, new bay());
                    ava.b(i, list, i2, list2, ResponseCallback.this);
                }
            }

            @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
            public void onDataChange(int i3, HealthLifeBean healthLifeBean2) {
                LogUtil.a("HealthLife_DeviceTaskManager", "getTaskData onDataChange result ", Integer.valueOf(i3), " bean ", healthLifeBean2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i, List<String> list, int i2, List<HealthLifeBean> list2, ResponseCallback<ResponseMsgBody> responseCallback) {
        boolean b2 = koq.b(list);
        ArrayList arrayList = new ArrayList();
        String p = azi.p();
        StringBuilder sb = new StringBuilder();
        for (int i3 : b) {
            if (b2 || list.contains(String.valueOf(i3))) {
                HealthLifeBean a2 = auz.a(i3, i, p);
                LogUtil.a("HealthLife_DeviceTaskManager", "handleResult cloverBean ", a2);
                if (azi.j(a2)) {
                    arrayList.add(a2);
                    sb.append(i3);
                    sb.append(",");
                }
            }
        }
        StringBuilder sb2 = new StringBuilder();
        int size = list2.size();
        for (int i4 = 0; i4 < size; i4++) {
            HealthLifeBean healthLifeBean = list2.get(i4);
            if (healthLifeBean == null) {
                LogUtil.h("HealthLife_DeviceTaskManager", "handleResult bean is null");
            } else {
                int id = healthLifeBean.getId();
                if (b2 || list.contains(String.valueOf(id))) {
                    arrayList.add(healthLifeBean);
                }
                sb.append(id);
                if (i4 != size - 1) {
                    sb.append(",");
                }
                if (healthLifeBean.getComplete() > 0) {
                    if (sb2.length() > 0) {
                        sb2.append(",");
                    }
                    sb2.append(id);
                }
            }
        }
        ResponseMsgBody b3 = b(i, i2, arrayList);
        b3.setMsgType(2001);
        b3.setAllTasks(((Object) sb) + bar.a(arrayList));
        b3.setCompletedTasks(sb2.toString());
        responseCallback.onResponse(0, b3);
    }

    private static ResponseMsgBody b(int i, int i2, List<HealthLifeBean> list) {
        ResponseMsgBody responseMsgBody = new ResponseMsgBody();
        responseMsgBody.setRecordDay(i);
        responseMsgBody.setResultCode(0);
        responseMsgBody.setTasks(a(i, i2, list));
        CloverProgress cloverProgress = new CloverProgress();
        cloverProgress.setTop(azi.b(list, 1, i));
        cloverProgress.setRight(azi.b(list, 2, i));
        cloverProgress.setLeft(azi.b(list, 3, i));
        responseMsgBody.setCloverProgress(cloverProgress);
        return responseMsgBody;
    }

    private static List<TaskBean> a(int i, int i2, List<HealthLifeBean> list) {
        int i3;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (i2 > 0) {
            size = Math.min(size, i2);
        }
        for (int i4 = 0; i4 < size; i4++) {
            HealthLifeBean healthLifeBean = list.get(i4);
            if (healthLifeBean == null) {
                LogUtil.h("HealthLife_DeviceTaskManager", "getTaskMessageArray lifeBean is null");
            } else {
                TaskBean taskBean = new TaskBean();
                int id = healthLifeBean.getId();
                taskBean.setTaskId(id);
                taskBean.setTaskTarget(healthLifeBean.getTarget());
                taskBean.setTaskValue(healthLifeBean.getResult());
                taskBean.setStatus(healthLifeBean.getComplete());
                taskBean.setRestStatus(healthLifeBean.getRest());
                taskBean.setTaskType(c(id, healthLifeBean.getType()));
                taskBean.setTimestamp(healthLifeBean.getTimestamp());
                if (id == 1) {
                    i3 = R$string.IDS_perfect_clover;
                } else if (id == 100001) {
                    i3 = R$string.IDS_vibrant_clover;
                } else {
                    taskBean.setStatusDes(azi.a(healthLifeBean));
                    i3 = dsl.ZJ_().get(id);
                }
                taskBean.setTaskName(nsf.h(i3));
                arrayList.add(taskBean);
                if (id == 2) {
                    taskBean.setProgress(azi.e(list, 1, i));
                }
            }
        }
        bar.b(arrayList, bar.b(list));
        return arrayList;
    }

    private static int c(int i, int i2) {
        ArrayList<Integer> b2 = awq.e().b();
        if (koq.b(b2) || !b2.contains(Integer.valueOf(i))) {
            return 0;
        }
        LogUtil.a("HealthLife_DeviceTaskManager", "getTaskType taskId ", Integer.valueOf(i), " type ", Integer.valueOf(i2), " basicTaskIdList ", b2);
        return i2;
    }

    public static void d() {
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelRemind_", new Runnable() { // from class: avf
                @Override // java.lang.Runnable
                public final void run() {
                    ava.d();
                }
            });
            return;
        }
        if (!avm.a().d()) {
            LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceCelebratingMsg is not supported");
            e("");
            return;
        }
        String[] c = bcm.c();
        LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceCelebratingMsg celebratingSavedArray ", c);
        ResponseCallback responseCallback = new ResponseCallback() { // from class: avh
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ava.c(i, (ResponseMsgBody) obj);
            }
        };
        if (c == null || c.length < 2) {
            NoticesBean a2 = a(0);
            LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceCelebratingMsg noticesBean ", a2);
            if (a2 == null) {
                e("");
                return;
            } else {
                bae.a(a2, 2003, 3, responseCallback);
                return;
            }
        }
        NoticesBean b2 = b(c);
        LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceCelebratingMsg noticesBean ", b2);
        if (b2 == null) {
            e("");
        } else {
            bae.a(b2, 2003, 3, responseCallback);
        }
    }

    static /* synthetic */ void c(int i, ResponseMsgBody responseMsgBody) {
        LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceCelebratingMsg resultCode ", Integer.valueOf(i), " jsonString ", responseMsgBody);
        e("");
        if (i == 0) {
            bcm.d(f245a);
        }
    }

    private static NoticesBean b(String[] strArr) {
        int h = CommonUtil.h(strArr[0]);
        int h2 = CommonUtil.h(strArr[1]);
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        int b3 = DateFormatUtil.b(jdl.v(currentTimeMillis));
        LogUtil.a("HealthLife_DeviceTaskManager", "getNoticesBean savedDate ", Integer.valueOf(h), " savedId ", Integer.valueOf(h2), " currentDate ", Integer.valueOf(b2), " yesterdayDate ", Integer.valueOf(b3));
        if (h == b2) {
            if (h2 != 1) {
                return a(3);
            }
            return null;
        }
        if (h == b3) {
            if (h2 == 100001) {
                return a(1);
            }
            return a(2);
        }
        if (h < b3) {
            return a(0);
        }
        LogUtil.h("HealthLife_DeviceTaskManager", "getNoticesBean other branch");
        return null;
    }

    public static void e(String str) {
        bao.c("health_model_device_celebrating", str);
    }

    private static NoticesBean a(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        NoticesBean a2 = a("celebrating_messages_today_perfect", b2, 1);
        LogUtil.a("HealthLife_DeviceTaskManager", "getCelebratingMsg type ", Integer.valueOf(i), " currentDate ", Integer.valueOf(b2), " todayClover ", a2);
        if (a2 != null || i == 3) {
            return a2;
        }
        NoticesBean a3 = a("celebrating_messages_today_energy", b2, 100001);
        LogUtil.a("HealthLife_DeviceTaskManager", "getCelebratingMsg todayEnergyClover ", a3);
        if (a3 != null || i == 2) {
            return a3;
        }
        int b3 = DateFormatUtil.b(jdl.v(currentTimeMillis));
        NoticesBean a4 = a("celebrating_messages_yesterday_perfect", b3, 1);
        LogUtil.a("HealthLife_DeviceTaskManager", "getCelebratingMsg yesterdayClover ", a4, " yesterdayDate ", Integer.valueOf(b3));
        return (a4 != null || i == 1) ? a4 : a("celebrating_messages_yesterday_energy", b3, 100001);
    }

    private static NoticesBean a(String str, int i, int i2) {
        f245a = "";
        long currentTimeMillis = System.currentTimeMillis();
        if (i != DateFormatUtil.b(currentTimeMillis) && currentTimeMillis < jdl.e(currentTimeMillis, 9, 0)) {
            return null;
        }
        HealthLifeBean a2 = auz.a(i2, i, azi.p());
        LogUtil.a("HealthLife_DeviceTaskManager", "getMessageString scenario ", str, " recordDay ", Integer.valueOf(i), " id ", Integer.valueOf(i2), " bean ", a2);
        if (!azi.j(a2) || a2.getComplete() <= 0) {
            return null;
        }
        NoticesBean a3 = bae.a(str, i, i2);
        if (a3 == null) {
            ReleaseLogUtil.d("HealthLife_DeviceTaskManager", "getMessageString noticesBean is null");
            return null;
        }
        f245a = i + "_" + i2;
        return a3;
    }

    private static boolean c(String str) {
        return !TextUtils.isEmpty(str) && jdl.f(DateFormatUtil.c(CommonUtils.h(str)), System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final int i) {
        if (!azi.aa()) {
            LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceReminder not join");
        } else {
            final int b2 = DateFormatUtil.b(jdl.c(System.currentTimeMillis(), 1, -1));
            azi.b(b2, (ResponseCallback<dsb>) new ResponseCallback() { // from class: ave
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj) {
                    ava.e(b2, i, i2, (dsb) obj);
                }
            });
        }
    }

    static /* synthetic */ void e(int i, int i2, int i3, dsb dsbVar) {
        LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceReminder errorCode ", Integer.valueOf(i3), " object ", dsbVar, " startDateOfWeek ", Integer.valueOf(i));
        if (i3 != 0 || dsbVar == null) {
            return;
        }
        if (i2 == 0) {
            LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceReminder week report");
            a();
        } else if (dsbVar.a() == 2) {
            d(dsbVar);
        }
    }

    public static void e() {
        if (!avm.a().d()) {
            LogUtil.h("HealthLife_DeviceTaskManager", "sendDeviceCommentedMsg isSupportHealthModel false");
        } else if (c(bao.e("weekly_doctor_commenting_date"))) {
            LogUtil.a("HealthLife_DeviceTaskManager", "sendDeviceCommentedMsg The review time is the same week");
        } else {
            c(1);
        }
    }

    private static void d(final dsb dsbVar) {
        bae.a(bae.a(), 2002, 3, new ResponseCallback() { // from class: avd
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ava.b(dsb.this, i, (ResponseMsgBody) obj);
            }
        });
    }

    static /* synthetic */ void b(dsb dsbVar, int i, ResponseMsgBody responseMsgBody) {
        LogUtil.a("HealthLife_DeviceTaskManager", "commentedMsgData resultCode ", Integer.valueOf(i), " jsonString ", responseMsgBody);
        if (i == 0) {
            drs e = dsbVar.e();
            if (e == null) {
                LogUtil.h("HealthLife_DeviceTaskManager", "commentedMsgData commentInfo is null");
                return;
            }
            drr drrVar = (drr) HiJsonUtil.e(e.c(), drr.class);
            if (drrVar == null) {
                LogUtil.h("HealthLife_DeviceTaskManager", "commentedMsgData commentInfoMap is null");
                return;
            }
            long g = CommonUtils.g(drrVar.e());
            if (g <= 0) {
                LogUtil.h("HealthLife_DeviceTaskManager", "commentedMsgData commentTime ", Long.valueOf(g));
                return;
            }
            String valueOf = String.valueOf(DateFormatUtil.b(g));
            bao.e("weekly_doctor_commenting_date", valueOf);
            LogUtil.a("HealthLife_DeviceTaskManager", "commentedMsgData commentDay ", valueOf);
        }
    }

    public static void b() {
        if (!avm.a().d()) {
            LogUtil.a("HealthLife_DeviceTaskManager", "sendWeekReportToDevice is not supported");
            return;
        }
        String e = bao.e("week_report_remind_date");
        LogUtil.a("HealthLife_DeviceTaskManager", "sendWeekReportToDevice reminderDate ", e);
        if (c(e)) {
            LogUtil.a("HealthLife_DeviceTaskManager", "sendWeekReportToDevice isSameWeek");
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900300021");
        LogUtil.a("HealthLife_DeviceTaskManager", "query week report switch");
        njj.d("9003", arrayList, new HiDataReadResultListener() { // from class: ava.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e("HealthLife_DeviceTaskManager", "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (koq.e(obj, HiSampleConfig.class)) {
                    List list = (List) obj;
                    if (!koq.b(list)) {
                        HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                        LogUtil.a("HealthLife_DeviceTaskManager", "week report switch is ", hiSampleConfig.getConfigData());
                        if ("1".equals(dsl.c(hiSampleConfig.getConfigData(), "healthLifeWeekReportSwitch"))) {
                            ava.c(0);
                            return;
                        }
                        return;
                    }
                }
                LogUtil.h("HealthLife_DeviceTaskManager", "isListTypeMatch false or list is empty");
                if (bao.b("week_report_remind_switch")) {
                    ava.g();
                } else {
                    LogUtil.h("HealthLife_DeviceTaskManager", "old sp not exist");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        String e = bao.e("week_report_remind_switch");
        boolean a2 = jfa.a();
        LogUtil.a("HealthLife_DeviceTaskManager", "reminderSwitch:", e, " isPullAllStatus:", Boolean.valueOf(a2));
        if (a2) {
            LogUtil.a("HealthLife_DeviceTaskManager", "already sync all data");
            njj.a("9003", "900300021", dsl.e("healthLifeWeekReportSwitch", TextUtils.isEmpty(e) ? "0" : "1"), new HiDataOperateListener() { // from class: ava.5
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    LogUtil.a("HealthLife_DeviceTaskManager", "saveSampleConfig errorCode: ", Integer.valueOf(i), ", object: ", obj);
                }
            }, System.currentTimeMillis());
            bao.c("week_report_remind_switch");
        }
        if (TextUtils.isEmpty(e)) {
            return;
        }
        c(0);
    }

    private static void a() {
        final int b2 = DateFormatUtil.b(System.currentTimeMillis());
        bae.a(bae.d(b2), 2002, 3, new ResponseCallback() { // from class: avb
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ava.b(b2, i, (ResponseMsgBody) obj);
            }
        });
    }

    static /* synthetic */ void b(int i, int i2, ResponseMsgBody responseMsgBody) {
        LogUtil.a("HealthLife_DeviceTaskManager", "sendWeekReport resultCode ", Integer.valueOf(i2), " jsonString ", responseMsgBody);
        if (i2 == 0) {
            bao.e("week_report_remind_date", String.valueOf(i));
        }
    }
}
