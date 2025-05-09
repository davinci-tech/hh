package com.huawei.health.receiver;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.receiver.MainProcessHelperService;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gge;
import defpackage.gso;
import defpackage.hps;
import defpackage.ixx;
import defpackage.jdh;
import defpackage.knu;
import defpackage.mmp;
import defpackage.mod;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class MainProcessHelperService extends Service {
    private Context c;
    private final IBinder d = new e();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.d;
    }

    static class e extends Binder {
        private e() {
        }
    }

    class a implements IBaseResponseCallback {
        private a() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("MainProcessHelperService", "onResponse ", Integer.valueOf(i));
            if (obj instanceof knu) {
                knu knuVar = (knu) obj;
                gso.e().init(MainProcessHelperService.this.getApplicationContext());
                gso.e().e(knuVar.d(), knuVar.b());
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        startForeground(20220115, jdh.c().xf_().build());
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int i, final int i2) {
        LogUtil.a("MainProcessHelperService", "onStartCommand ", intent);
        ThreadPoolManager.d().c("MainProcessHelperService", new Runnable() { // from class: ezn
            @Override // java.lang.Runnable
            public final void run() {
                MainProcessHelperService.this.aum_(intent, i2);
            }
        });
        super.onStartCommand(null, i, i2);
        return 2;
    }

    public /* synthetic */ void aum_(Intent intent, int i) {
        this.c = getApplicationContext();
        try {
            if (intent != null) {
                if ("start_main_process_for_pluginsuggestion".equals(intent.getAction())) {
                    LogUtil.a("MainProcessHelperService", "onStartCommand ACTION_START_MAIN_PROCESS_FOR_PLUGINSUGGESTION");
                    b();
                } else if ("start_main_process_for_pluginsuggestion_running_stretch".equals(intent.getAction())) {
                    LogUtil.a("MainProcessHelperService", "onStartCommand ACTION_START_MAIN_PROCESS_FOR_PLUGINSUGGESTION_STRETCH");
                    e();
                } else if (!"start_main_process_for_manager".equals(intent.getAction())) {
                    long longExtra = intent.getLongExtra("startTime", 0L);
                    long longExtra2 = intent.getLongExtra("endTime", 0L);
                    LogUtil.a("MainProcessHelperService", "onStartCommand(), ", "startTime: ", Long.valueOf(longExtra), " ;endTime: ", Long.valueOf(longExtra2));
                    String value = AnalyticsValue.HEALTH_NOTIFICATION_ACTIVITY_2110003.value();
                    HashMap hashMap = new HashMap(1);
                    hashMap.put("click", 1);
                    ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
                    ixx.d().c(this);
                    hps.a(longExtra, longExtra2, new a());
                } else {
                    LogUtil.h("MainProcessHelperService", "noCommand is match.");
                }
            } else {
                LogUtil.h("MainProcessHelperService", "onStartCommand intent is null.");
            }
        } catch (Exception e2) {
            LogUtil.b("MainProcessHelperService", "onStartCommand() = ", LogAnonymous.b((Throwable) e2));
        }
        stopSelf(i);
    }

    private void b() {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        gge.d((String) null);
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId("R002");
        workoutRecord.saveWorkoutName(BaseApplication.getContext().getString(R.string._2130842372_res_0x7f021304));
        workoutRecord.savePlanId("");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(workoutRecord);
        mod.c(this.c, new mmp("R002"), arrayList);
    }

    private void e() {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveVersion(null);
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId("R002");
        workoutRecord.savePlanId("");
        workoutRecord.saveWorkoutName(BaseApplication.getContext().getString(R.string._2130842372_res_0x7f021304));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(workoutRecord);
        mmp mmpVar = new mmp("R002");
        mmpVar.d("FitnessCourse_轨迹");
        mmpVar.b(true);
        mod.c(this.c, mmpVar, arrayList);
    }
}
