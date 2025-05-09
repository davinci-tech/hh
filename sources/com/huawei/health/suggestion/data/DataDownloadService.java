package com.huawei.health.suggestion.data;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.asc;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes4.dex */
public class DataDownloadService extends Service {
    private final IBinder e = new e(null);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("Suggestion_DataDownloadService", "onCreate");
        c();
    }

    /* renamed from: com.huawei.health.suggestion.data.DataDownloadService$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        AnonymousClass4() {
        }

        @Override // java.lang.Runnable
        public void run() {
            final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.h("Suggestion_DataDownloadService", "downloadFitnessData : planApi is null.");
                return;
            }
            RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
            if (recordApi == null) {
                LogUtil.h("Suggestion_DataDownloadService", "downloadFitnessData recordApi is null.");
            } else {
                recordApi.downloadFitnessRecordFromCloud(new ResultCallback() { // from class: com.huawei.health.suggestion.data.DataDownloadService.4.1
                    @Override // com.huawei.health.suggestion.ResultCallback
                    public void onResult(int i, Object obj) {
                        LogUtil.a("Suggestion_DataDownloadService", "downloadFitnessData resultCode = ", Integer.valueOf(i), " object ", "", obj);
                        planApi.setPlanType(3);
                        planApi.getPlanRecords(0, 0, new UiCallback<List<PlanRecord>>() { // from class: com.huawei.health.suggestion.data.DataDownloadService.4.1.5
                            @Override // com.huawei.basefitnessadvice.callback.UiCallback
                            public void onFailure(int i2, String str) {
                                LogUtil.a("Suggestion_DataDownloadService", "downloadFitnessData, getPlanRecords errcode = ", Integer.valueOf(i2), " errorInfo ", str);
                                DataDownloadService.this.stopSelf();
                            }

                            @Override // com.huawei.basefitnessadvice.callback.UiCallback
                            /* renamed from: d, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(List<PlanRecord> list) {
                                LogUtil.a("Suggestion_DataDownloadService", "downloadFitnessData getPlanRecords success.");
                                DataDownloadService.this.stopSelf();
                            }
                        });
                    }
                });
            }
        }
    }

    private void c() {
        asc.e().b(new AnonymousClass4());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("Suggestion_DataDownloadService", "onStartCommand");
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.e;
    }

    static class e extends Binder {
        private e() {
        }

        /* synthetic */ e(AnonymousClass4 anonymousClass4) {
            this();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Suggestion_DataDownloadService", "onDestroy");
    }
}
