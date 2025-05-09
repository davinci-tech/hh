package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.util.Pair;
import com.huawei.basichealthmodel.HealthModelH5ProService;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.basichealthmodel.service.SyncService;
import com.huawei.basichealthmodel.ui.dialog.HealthLifeTaskDialog;
import com.huawei.basichealthmodel.ui.view.SleepTaskShareView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.healthmodel.HealthLifeApi;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.hihealth.api.SyncApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.util.HealthSyncUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@ApiDefine(uri = HealthLifeApi.class)
@Singleton
/* loaded from: classes3.dex */
public class asy implements HealthLifeApi {
    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void resetCache() {
        ReleaseLogUtil.b("HealthLife_HealthLifeImpl", "resetCache");
        bao.a();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public boolean isAgreeProtocol() {
        boolean u = azi.u();
        LogUtil.a("HealthLife_HealthLifeImpl", "isAgreeProtocol isAgreeProtocol ", Boolean.valueOf(u));
        return u;
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public boolean isJoinHealthModel() {
        boolean aa = azi.aa();
        LogUtil.a("HealthLife_HealthLifeImpl", "isJoinHealthModel isJoinHealthModel ", Boolean.valueOf(aa));
        return aa;
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public int getJoinTime() {
        int t = azi.t();
        LogUtil.a("HealthLife_HealthLifeImpl", "getJoinTime joinTime ", Integer.valueOf(t));
        return t;
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public ArrayList<Integer> getBloodPressurePlanIdList() {
        return azi.a();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public List<cbk> getBloodPressureAlarmInfoList() {
        return azi.e();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void queryInterventionPlanInfo(String str, ResponseCallback<dso> responseCallback) {
        LogUtil.a("HealthLife_HealthLifeImpl", "queryInterventionPlanInfo source ", str, " callback ", responseCallback);
        aug.d().c(responseCallback);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void getHealthLifeConDays(int i, ResponseCallback<dsa> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeImpl", "getHealthLifeConDays callback is null date ", Integer.valueOf(i));
            return;
        }
        dsa e2 = bda.e();
        if (e2 != null && e2.b() == i) {
            ReleaseLogUtil.b("HealthLife_HealthLifeImpl", "getHealthLifeConDays date ", Integer.valueOf(i));
            responseCallback.onResponse(0, e2);
            return;
        }
        int b = DateFormatUtil.b(System.currentTimeMillis());
        int h = CommonUtils.h(bao.e("flush_data_time"));
        long d = bao.d("update_cloud_data_time");
        int b2 = DateFormatUtil.b(d);
        ReleaseLogUtil.b("R_HealthLife_HealthLifeImpl", "getHealthLifeConDays today ", Integer.valueOf(b), " flushDate ", Integer.valueOf(h), " todayUpdateCloudDataTime ", Long.valueOf(d), " todayUpdateCloudDataDate ", Integer.valueOf(b2));
        if (b == h && b == b2) {
            aug.d().d(dsl.j(), responseCallback);
        } else {
            responseCallback.onResponse(-1, new dsa());
        }
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void setHealthLifeTaskConsInfoCache(dsa dsaVar) {
        LogUtil.a("HealthLife_HealthLifeImpl", "setHealthLifeTaskConsInfoCache consInfo ", dsaVar);
        bda.d(dsaVar);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void setSmallShownIdsCache(List<Integer> list) {
        LogUtil.a("HealthLife_HealthLifeImpl", "setSmallShownIdsCache ids ", list);
        bda.d(list);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void setHealthLifeSwitch(String str, boolean z) {
        ReleaseLogUtil.b("HealthLife_HealthLifeImpl", "setHealthLifeSwitch: key ", str);
        LogUtil.a("HealthLife_HealthLifeImpl", "value ", Boolean.valueOf(z));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        bao.c(str, z);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public boolean getHealthLifeSwitch(String str, boolean z) {
        ReleaseLogUtil.b("HealthLife_HealthLifeImpl", "getHealthLifeSwitch: key ", str);
        boolean b = bao.b(str, z);
        LogUtil.a("HealthLife_HealthLifeImpl", "value ", Boolean.valueOf(b));
        return b;
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public List<Integer> getSmallShownIdsCache(int i) {
        LogUtil.a("HealthLife_HealthLifeImpl", "getHealthLifeTaskConsInfoCache");
        return bda.a(i);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void getHealthLifeChallenge(String str, ResponseCallback<drx> responseCallback) {
        bam.c(str, responseCallback);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public ArrayList<Integer> subscribeHiHealthData(String str, HiSubscribeListener hiSubscribeListener) {
        return bda.d(str, hiSubscribeListener);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void unSubscribeHiHealthData(String str, List<Integer> list, HiUnSubscribeListener hiUnSubscribeListener) {
        bda.e(str, list, hiUnSubscribeListener);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void refreshTask(int i) {
        awq.e().d(i);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public int getCloverComplete(int i, int i2) {
        HealthLifeBean a2 = auz.a(i, i2, azi.p());
        if (azi.j(a2)) {
            return a2.getComplete();
        }
        return 0;
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public List<HealthLifeBean> queryHealthLifeBeanList(long j, long j2) {
        return auz.d(DateFormatUtil.b(j), DateFormatUtil.b(j2), azi.p());
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void initHealthLife() {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: asu
                @Override // java.lang.Runnable
                public final void run() {
                    asy.this.initHealthLife();
                }
            });
            return;
        }
        boolean ag = azi.ag();
        LogUtil.a("HealthLife_HealthLifeImpl", "initHealthModel isUseNewHealthModel ", Boolean.valueOf(ag));
        if (ag) {
            return;
        }
        bad.b().b("HealthLife_HealthLifeImpl");
        auy.d().b();
        avm.a().f();
        if (!Utils.k()) {
            LogUtil.h("HealthLife_HealthLifeImpl", "initHealthModel isSupportHealthModel false");
            return;
        }
        bcr.a().c(new ResponseCallback() { // from class: asw
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                LogUtil.a("HealthLife_HealthLifeImpl", "initHealthModel errorCode=", Integer.valueOf(i));
            }
        }, "cloverLife");
        try {
            e eVar = new e();
            HealthSyncUtil.a(eVar);
            HealthSyncUtil.b(eVar);
        } catch (IllegalArgumentException e2) {
            LogUtil.h("HealthLife_HealthLifeImpl", "initHealthModel exception ", LogAnonymous.b((Throwable) e2));
        }
        SyncService.d();
        avm.a().h();
        avm.a().i();
        avm.a().k();
        bcm.e(azi.p());
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public Object getCardReader(Context context, Object obj) {
        if (obj instanceof CardConfig) {
            return new ayy(context, (CardConfig) obj);
        }
        return null;
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void setBinder() {
        bbu.d();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void initDeviceManager() {
        avm.a().e();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void registerH5ProService() {
        bzs.e().initH5Pro();
        H5ProClient.getServiceManager().registerService(HealthModelH5ProService.class);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public String getSubscriptionData(int i) {
        SparseArray<HealthLifeBean> kE_ = awq.e().kE_(azi.p(), i, false);
        if (kE_ == null || kE_.size() <= 0) {
            LogUtil.h("HealthLife_HealthLifeImpl", "getSubscriptionData sparseArray ", kE_);
            return "";
        }
        String my_ = bax.my_(kE_);
        LogUtil.a("HealthLife_HealthLifeImpl", "getSubscriptionData json ", my_);
        return TextUtils.isEmpty(my_) ? "" : my_;
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void refreshLastDayRecordListCache(List<HealthLifeBean> list) {
        bda.b(list);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public List<HealthLifeTaskBean> getLastDayRecordListCache() {
        return bda.a(bda.c());
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public List<HealthLifeTaskBean> getDayRecordListCache() {
        if (!azi.aa()) {
            return Collections.emptyList();
        }
        return bda.a(bda.d());
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    /* renamed from: getDayRecordList, reason: merged with bridge method [inline-methods] */
    public void b(final int i, final ResponseCallback<List<HealthLifeTaskBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_HealthLifeImpl", "getDayRecordList callback is null");
            return;
        }
        if (!azi.aa()) {
            responseCallback.onResponse(-1, Collections.emptyList());
        } else if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: asv
                @Override // java.lang.Runnable
                public final void run() {
                    asy.this.b(i, responseCallback);
                }
            });
        } else {
            awq.e().b(i, new TaskDayDataListener() { // from class: asy.3
                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onDataChange(int i2, HealthLifeBean healthLifeBean) {
                }

                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onAllDataChange(int i2, List<HealthLifeBean> list) {
                    if (koq.b(list)) {
                        LogUtil.h("HealthLife_HealthLifeImpl", "getDayRecordList onAllTaskDataChange lifeBeanList ", list);
                        responseCallback.onResponse(i2, Collections.emptyList());
                    } else {
                        responseCallback.onResponse(i2, bda.a(list));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void queryWeekHealthReport(int i, ResponseCallback<dsb> responseCallback) {
        azi.b(i, responseCallback);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void updateBloodPressureTarget(List<cbk> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_HealthLifeImpl", "updateBloodPressureTarget bloodPressureAlarmInfoList ", list);
            return;
        }
        int size = list.size();
        azp.e(String.valueOf(size), azi.p());
        bao.e("bloodPressureMeasurePlan", HiJsonUtil.e(list));
        bao.e("bloodPressureMeasurePlanSaveTime", String.valueOf(System.currentTimeMillis()));
        cbk cbkVar = list.get(0);
        if (cbkVar != null) {
            bao.e("blood_pressure_day_of_week", String.valueOf(cbkVar.d()));
        }
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public List<HealthLifeTaskBean> getTaskDayRecordList(List<Integer> list) {
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        bda.d(DateFormatUtil.b(System.currentTimeMillis()), list, new ResponseCallback() { // from class: atc
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                asy.e(arrayList, countDownLatch, i, (List) obj);
            }
        });
        azi.d(countDownLatch, "getDayRecordArray");
        return arrayList;
    }

    static /* synthetic */ void e(List list, CountDownLatch countDownLatch, int i, List list2) {
        list.addAll(list2);
        countDownLatch.countDown();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void showTaskDialog(Context context, HealthLifeTaskBean healthLifeTaskBean, String str, ResponseCallback<HealthLifeTaskBean> responseCallback) {
        if ("inputSleepDialog".equals(str)) {
            new bba(context, healthLifeTaskBean, responseCallback).d();
            return;
        }
        HealthLifeTaskDialog a2 = new HealthLifeTaskDialog.Builder(context, healthLifeTaskBean, str, responseCallback).a();
        a2.setCancelable(true);
        a2.show();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public float getCloverScale(List<HealthLifeBean> list, int i) {
        return azi.e(list, i, azi.q());
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public boolean isPerfectClover(List<HealthLifeBean> list, float f, float f2, float f3) {
        return azi.b(list, f, f2, f3);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public int getActiveTaskId(List<HealthLifeBean> list, int i) {
        return azi.d(list, i);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void syncUpdateHealthGoal(List<HealthLifeBean> list, ResponseCallback<List<HealthLifeBean>> responseCallback, String str) {
        bcr.a().b(list, responseCallback, str);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public List<ImageBean> getTaskDialogImageList(String str) {
        return bad.b().a(str);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void setAboutEvent(int i, Context context) {
        bcl.d(i, context);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void shareSleepTask(int i, int i2, Bitmap bitmap) {
        SleepTaskShareView sleepTaskShareView = new SleepTaskShareView(BaseApplication.e());
        sleepTaskShareView.setTaskBenefit(i);
        sleepTaskShareView.setTaskText(i, i2);
        sleepTaskShareView.measure(-2, -2);
        sleepTaskShareView.layout(0, 0, sleepTaskShareView.getMeasuredWidth(), sleepTaskShareView.getMeasuredHeight());
        sleepTaskShareView.setBackground(bitmap);
        sleepTaskShareView.d();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public void sendDeviceTaskData() {
        avm.a().g();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public int getPlayType() {
        return azi.m();
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public Pair<Integer, String> getRemindTipMap(List<HealthLifeBean> list) {
        return bby.a(list);
    }

    @Override // com.huawei.health.healthmodel.HealthLifeApi
    public Calendar getHourAndMinute(String str) {
        return azi.f(str);
    }

    static class e implements SyncApi<Void> {
        private e() {
        }

        @Override // com.huawei.hihealth.api.SyncApi
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Void syncCloud() {
            SyncService.d();
            return null;
        }

        @Override // com.huawei.hihealth.api.SyncApi
        public void cleanCloud(boolean z, Object obj) {
            if (obj instanceof ResponseCallback) {
                baq.a(z, true, (ResponseCallback<Object>) obj);
            }
        }

        @Override // com.huawei.hihealth.api.SyncApi
        public String getLabel() {
            return SyncApi.HEALTH_LIFE;
        }
    }
}
