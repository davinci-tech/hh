package com.huawei.health.plan.model.data;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.courseplanservice.api.OnStateListener;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.model.data.DataSyncService;
import com.huawei.health.plan.model.model.DataSync;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.utils.RiskBiAnalytics;
import defpackage.eqa;
import defpackage.eto;
import defpackage.etr;
import defpackage.ett;
import defpackage.euj;
import defpackage.gic;
import defpackage.mof;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class DataSyncService extends Service {
    private final IBinder d = new DataSyncBinder();
    private long e = 0;

    public static boolean b(int i, int i2) {
        return i != 4 || i2 == 1 || i2 == 3;
    }

    public boolean d(int i) {
        return (i == 99 || i == 1001) || (i == 200019 || i == 20006 || i == 200030);
    }

    public class DataSyncBinder extends Binder {
        OnStateListener d;

        public DataSyncBinder() {
        }

        public OnStateListener getListener() {
            return this.d;
        }

        public void setListener(OnStateListener onStateListener) {
            this.d = onStateListener;
        }

        public DataSyncService getDataSyncService() {
            return DataSyncService.this;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        LogUtil.a("Suggestion_DataSyncService", "onCreate enter");
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("Suggestion_DataSyncService", "onStartCommand enter");
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.d;
    }

    public void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ets
            @Override // java.lang.Runnable
            public final void run() {
                DataSyncService.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        LogUtil.a("Suggestion_DataSyncService", "Start to syncDataToCloud");
        this.e = 0L;
        e();
    }

    private void e() {
        DataSync syncRecord = etr.b().getSyncRecord(this.e);
        if (syncRecord != null && CommonUtil.aa(this)) {
            this.e = syncRecord.getRecordId();
            switch (syncRecord.getType()) {
                case 1:
                    m(syncRecord);
                    break;
                case 2:
                    l(syncRecord);
                    break;
                case 3:
                    g(syncRecord);
                    break;
                case 4:
                    i(syncRecord);
                    break;
                case 5:
                    a(syncRecord);
                    break;
                case 6:
                    f(syncRecord);
                    break;
                case 7:
                    b(syncRecord);
                    break;
                case 8:
                    d(syncRecord);
                    break;
                case 9:
                    h(syncRecord);
                    break;
                case 10:
                    k(syncRecord);
                    break;
                case 11:
                    j(syncRecord);
                    break;
                default:
                    c(syncRecord);
                    break;
            }
        }
        b();
    }

    private void h(DataSync dataSync) {
        ReleaseLogUtil.e("Suggestion_DataSyncService", "postDeleteRecordDelete enter ");
        try {
            e(dataSync);
        } catch (NumberFormatException e) {
            LogUtil.b("Suggestion_DataSyncService", "postDeleteRecordDelete, ", LogAnonymous.b((Throwable) e));
        }
    }

    private void e(final DataSync dataSync) {
        eqa.a().deleteUserWorkoutRecords(Integer.parseInt(dataSync.getValue()), 1, new DataCallback() { // from class: com.huawei.health.plan.model.data.DataSyncService.5
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.d("Suggestion_DataSyncService", "postDeleteRecordDelete onFailure errorCode = ", Integer.valueOf(i));
                if (i == 15030200) {
                    onSuccess(null);
                } else {
                    DataSyncService.this.a(dataSync, i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("Suggestion_DataSyncService", "postDeleteRecordDelete onSuccess data = ", jSONObject);
                DataSyncService.this.c(dataSync);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DataSync dataSync) {
        etr.b().delSyncRecord(dataSync);
        e();
    }

    private void m(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "data sync and update plan name:", dataSync.getValue());
        String[] b = gic.b(dataSync.getValue());
        if (b.length != 2) {
            c(dataSync);
        } else {
            etr.b().postPlanName(b[0], gic.d(b[1]), new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_DataSyncService", "data sync and update plan name fail:", dataSync.toString(), str);
                    if (i == 200019 || i == 1001 || i == 20005 || i == 99) {
                        onSuccess(null);
                    } else if (i == 9999 || i == 1) {
                        DataSyncService.this.e(dataSync, i, str);
                    } else {
                        DataSyncService.this.a(dataSync, i, str);
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.a("Suggestion_DataSyncService", "data sync plan name:", dataSync.toString(), "update plan name successful");
                    DataSyncService.this.c(dataSync);
                }
            });
        }
    }

    private void l(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "sync data update plan progress:", dataSync);
        etr.b().postPlanProgress(dataSync.getValue(), new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.d("Suggestion_DataSyncService", "sync data update plan progress fail:", dataSync, str);
                RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_COURSE_CHECK_IN.value(), "updatePlanProgress errorCode: ", Integer.valueOf(i), "errorInfo: ", str);
                if (DataSyncService.this.d(i)) {
                    onSuccess(null);
                } else if (i == 1 || i == 9999) {
                    DataSyncService.this.e(dataSync, i, str);
                } else {
                    DataSyncService.this.a(dataSync, i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_DataSyncService", "sync data update plan progress:", dataSync, "update plan progress success");
                DataSyncService.this.c(dataSync);
            }
        });
    }

    private void k(final DataSync dataSync) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_DataSyncService", "postFeedback : planApi is null.");
            return;
        }
        mof mofVar = (mof) new Gson().fromJson(dataSync.getValue(), mof.class);
        if (mofVar == null) {
            LogUtil.b("Suggestion_DataSyncService", "updatePlanFeedBack userFeedbackBean is null");
        } else {
            LogUtil.a("Suggestion_DataSyncService", "updatePlanFeedBack userFeedbackBean:", new Gson().toJson(mofVar));
            planApi.postFeedback(mofVar, new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.10
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_DataSyncService", "updatePlanFeedBack onFailure: errorCode ", Integer.valueOf(i), "errorInfo:", str);
                    DataSyncService.this.c(dataSync);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.a("Suggestion_DataSyncService", "updatePlanFeedBack onSuccess:", str);
                    DataSyncService.this.c(dataSync);
                }
            });
        }
    }

    private void d() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataSyncService", "clearInvalidExerciseBehavior getAccountInfo == null");
            return;
        }
        eto o = ett.i().o();
        List<DataSync> c = o.c(accountInfo, 3);
        if (CollectionUtils.d(c)) {
            return;
        }
        LogUtil.a("Suggestion_DataSyncService", "clearInvalidExerciseBehavior size=", Integer.valueOf(c.size()));
        for (DataSync dataSync : c) {
            String[] b = gic.b(dataSync.getValue());
            if (b.length != 5) {
                LogUtil.h("Suggestion_DataSyncService", "clearInvalidExerciseBehavior delete ", c);
                o.e(dataSync);
            } else {
                if (!b(gic.e((Object) b[4]), gic.e((Object) b[3]))) {
                    LogUtil.a("Suggestion_DataSyncService", "clearInvalidExerciseBehavior delete ", c);
                    o.e(dataSync);
                }
            }
        }
    }

    private void g(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "sync data postExerciseBehavior:", dataSync);
        String[] b = gic.b(dataSync.getValue());
        if (b.length != 5) {
            c(dataSync);
            return;
        }
        int e = gic.e((Object) b[3]);
        int e2 = gic.e((Object) b[4]);
        if (!b(e2, e)) {
            ReleaseLogUtil.e("Suggestion_DataSyncService", "postExerciseBehavior isSupportPostExerciseBehavior false operType:", Integer.valueOf(e2), "courseBelongType:", Integer.valueOf(e));
            d();
            c(dataSync);
        } else {
            WorkoutRecord workoutRecord = new WorkoutRecord();
            workoutRecord.saveWorkoutId(b[0]);
            workoutRecord.saveVersion(b[1]);
            workoutRecord.saveWorkoutPackageId(b[2]);
            workoutRecord.saveCourseBelongType(e);
            etr.b().postExerciseBehavior(workoutRecord, e2, new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.8
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_DataSyncService", "postExerciseBehavior fail:", dataSync.toString(), str);
                    if (i == 99 || i == 1001) {
                        onSuccess(null);
                    } else if (i == 9999 || i == 1) {
                        DataSyncService.this.e(dataSync, i, str);
                    } else {
                        DataSyncService.this.a(dataSync, i, str);
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.a("Suggestion_DataSyncService", "postExerciseBehavior:", dataSync, "postExerciseBehavior successful");
                    DataSyncService.this.c(dataSync);
                }
            });
        }
    }

    private void i(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "upload plan remain:", dataSync);
        String[] b = gic.b(dataSync.getValue());
        if (b.length != 2) {
            c(dataSync);
        } else {
            etr.b().postPlanRemind(b[0], gic.e((Object) b[1]), new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.9
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_DataSyncService", "upload plan remain fail:", dataSync.toString(), str);
                    if (i == 99 || i == 1001 || i == 200019 || i == 20008) {
                        onSuccess(null);
                    } else if (i == 9999 || i == 1) {
                        DataSyncService.this.e(dataSync, i, str);
                    } else {
                        DataSyncService.this.a(dataSync, i, str);
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.a("Suggestion_DataSyncService", "postPlanRemind:", dataSync, "postPlanRemind successful");
                    DataSyncService.this.c(dataSync);
                }
            });
        }
    }

    private void a(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "postBestRecord:", dataSync);
        etr.b().postBestRecord(dataSync.getValue(), new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.7
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_DataSyncService", "postBestRecord fail:", dataSync, str);
                if (i == 99 || i == 1001 || i == 200019) {
                    onSuccess(null);
                } else if (i == 9999 || i == 1) {
                    DataSyncService.this.e(dataSync, i, str);
                } else {
                    DataSyncService.this.a(dataSync, i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_DataSyncService", "postBestRecord:", dataSync, "postBestRecord successful");
                DataSyncService.this.c(dataSync);
            }
        });
    }

    private void f(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "postBestRecordFit:", dataSync);
        etr.b().postBestRecordFit(dataSync.getValue(), new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_DataSyncService", "postBestRecordFit fail:", dataSync, str);
                if (i == 99 || i == 1001 || i == 200019) {
                    onSuccess(null);
                } else if (i == 9999 || i == 1) {
                    DataSyncService.this.e(dataSync, i, str);
                } else {
                    DataSyncService.this.a(dataSync, i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_DataSyncService", "upload behaviour best record :", dataSync, "upload successful");
                DataSyncService.this.c(dataSync);
            }
        });
    }

    private void b(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "postBestRecordCollect:", dataSync);
        etr.b().collectBehavior(dataSync.getValue(), new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.15
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_DataSyncService", "postBestRecordCollect fail:", dataSync, str);
                if (i == 99 || i == 1001 || i == 200019 || i == 200027) {
                    onSuccess(null);
                } else if (i == 9999 || i == 1) {
                    DataSyncService.this.e(dataSync, i, str);
                } else {
                    DataSyncService.this.a(dataSync, i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_DataSyncService", "postBestRecordCollect:", dataSync, "postBestRecordCollect successful");
                DataSyncService.this.c(dataSync);
            }
        });
    }

    private void d(final DataSync dataSync) {
        LogUtil.a("Suggestion_DataSyncService", "postBestRecordDelete:", dataSync);
        String[] b = gic.b(dataSync.getValue());
        etr.b().deleteBehavior(b[0], b.length == 2 ? CommonUtil.h(b[1]) : 0, new UiCallback<String>() { // from class: com.huawei.health.plan.model.data.DataSyncService.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_DataSyncService", "postBestRecordDelete fail:", dataSync, str);
                if (i == 99 || i == 1001 || i == 200019) {
                    onSuccess(null);
                } else if (i == 9999 || i == 1) {
                    DataSyncService.this.e(dataSync, i, str);
                } else {
                    DataSyncService.this.a(dataSync, i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_DataSyncService", "postBestRecordDelete:", dataSync, "postBestRecordDelete successful");
                DataSyncService.this.c(dataSync);
            }
        });
    }

    private void j(final DataSync dataSync) {
        ReleaseLogUtil.e("Suggestion_DataSyncService", "updatePlanClock:", dataSync.getValue());
        final WorkoutRecord e = euj.e(dataSync.getUserId(), dataSync.getValue());
        if (e == null) {
            ReleaseLogUtil.c("Suggestion_DataSyncService", "workoutRecord is null.");
            c(dataSync);
            a(dataSync, 0, null);
            return;
        }
        final d dVar = new d(this, dataSync);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.c("Suggestion_DataSyncService", "intPlanApi is null.");
            a(dataSync, 0, null);
        } else {
            planApi.b(new UiCallback<IntPlan>() { // from class: com.huawei.health.plan.model.data.DataSyncService.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataSyncService", "intPlanApi getCurrentIntPlan failed.", Integer.valueOf(i));
                    DataSyncService.this.a(dataSync, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IntPlan intPlan) {
                    if (intPlan == null) {
                        ReleaseLogUtil.c("Suggestion_DataSyncService", "updatePlanProgress() currentPlan == null");
                        DataSyncService.this.c(dataSync);
                    } else {
                        etr.b().updateDayRecord(intPlan, e, dVar);
                    }
                }
            });
        }
    }

    static class d extends DataCallback {
        private WeakReference<DataSyncService> b;
        private DataSync c;

        d(DataSyncService dataSyncService, DataSync dataSync) {
            this.b = new WeakReference<>(dataSyncService);
            this.c = dataSync;
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.e("Suggestion_DataSyncService", "updatePlanClock failed.", Integer.valueOf(i));
            DataSyncService dataSyncService = this.b.get();
            if (dataSyncService == null) {
                LogUtil.b("Suggestion_DataSyncService", "service has destroy.");
            } else if (i == 200019 || i == 200052) {
                ReleaseLogUtil.e("Suggestion_DataSyncService", "updatePlanClock error but finishDataSync");
                dataSyncService.c(this.c);
            } else {
                dataSyncService.a(this.c, i, str);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onSuccess(JSONObject jSONObject) {
            DataSyncService dataSyncService = this.b.get();
            if (dataSyncService == null) {
                LogUtil.b("Suggestion_DataSyncService", "service has destroy.");
            } else {
                ReleaseLogUtil.e("Suggestion_DataSyncService", "updatePlanClock success.", this.c);
                dataSyncService.c(this.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DataSync dataSync, int i, String str) {
        if (etr.b().isBeyondSyncTimes(dataSync)) {
            LogUtil.a("Suggestion_DataSyncService", "data sync name:", dataSync.toString(), " successful");
            c(dataSync);
        } else {
            a(dataSync, i, str);
        }
    }

    public void a(DataSync dataSync, int i, String str) {
        IBinder iBinder = this.d;
        OnStateListener listener = iBinder instanceof DataSyncBinder ? ((DataSyncBinder) iBinder).getListener() : null;
        if (i == 1001) {
            LogUtil.b("Suggestion_DataSyncService", "param valid.", str);
            etr.b().delSyncRecord(dataSync);
        }
        LogUtil.b("Suggestion_DataSyncService", "fail errorInfo:", str, " dataSync type:", Integer.valueOf(dataSync.getType()));
        if (listener != null) {
            listener.onFailure(i, str);
            stopSelf();
        } else {
            e();
        }
    }

    public void b() {
        LogUtil.a("Suggestion_DataSyncService", "finish sync service");
        OnStateListener listener = ((DataSyncBinder) this.d).getListener();
        if (listener != null) {
            listener.onFinish();
        }
        stopSelf();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Suggestion_DataSyncService", "onDestroy");
    }
}
