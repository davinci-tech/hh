package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.googlefit;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.huawei.health.main.api.FitnessDataInteractorApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.googlefit.GoogleFitDataManage;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import defpackage.ixx;
import defpackage.jdx;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class GoogleFitDataManage {
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private FitnessDataInteractorApi f10560a;
    private GoogleApiClient c;
    private Context e;
    private List<List<HiHealthData>> h = new ArrayList(16);
    private List<List<HiHealthData>> i = new ArrayList(16);
    private Handler d = new AnonymousClass4();

    /* renamed from: com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.googlefit.GoogleFitDataManage$4, reason: invalid class name */
    public class AnonymousClass4 extends Handler {
        AnonymousClass4() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                GoogleFitDataManage.this.h();
            } else {
                if (i != 2) {
                    return;
                }
                LogUtil.a("GoogleFitDataManage", "MSG_AFTER_CONNECT_DELAY_TIME", GoogleFitDataManage.this.c);
                if (GoogleFitDataManage.this.c != null) {
                    jdx.b(new Runnable() { // from class: shp
                        @Override // java.lang.Runnable
                        public final void run() {
                            GoogleFitDataManage.AnonymousClass4.this.e();
                        }
                    });
                }
            }
        }

        public /* synthetic */ void e() {
            GoogleFitDataManage.this.i();
            GoogleFitDataManage.this.f();
            GoogleFitDataManage.this.a();
        }
    }

    public GoogleFitDataManage(Context context) {
        if (context == null) {
            LogUtil.a("GoogleFitDataManage", "context == null");
            this.e = BaseApplication.getContext();
        } else {
            this.e = context;
        }
        this.f10560a = (FitnessDataInteractorApi) Services.c("Main", FitnessDataInteractorApi.class);
        this.c = new GoogleApiClient.Builder(this.e).addApi(Fitness.HISTORY_API).addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE)).addScope(new Scope(Scopes.FITNESS_BODY_READ_WRITE)).addScope(new Scope(Scopes.FITNESS_LOCATION_READ_WRITE)).addScope(new Scope(Scopes.CLOUD_SAVE)).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() { // from class: com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.googlefit.GoogleFitDataManage.1
            @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
            public void onConnected(Bundle bundle) {
                LogUtil.a("GoogleFitDataManage", "Connected!!!");
            }

            @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
            public void onConnectionSuspended(int i) {
                if (i == 2) {
                    LogUtil.a("GoogleFitDataManage", "Connection lost. Cause: Network Lost.");
                } else if (i == 1) {
                    LogUtil.a("GoogleFitDataManage", "Connection lost. Reason: Service Disconnected");
                } else {
                    LogUtil.a("GoogleFitDataManage", "Connection lost. resultCode: ", Integer.valueOf(i));
                }
            }
        }).build();
    }

    public void b() {
        g();
        m();
    }

    private void g() {
        LogUtil.a("GoogleFitDataManage", "enter requestGoogleFitPointDataList");
        this.f10560a.requestGoogleFitPonitDatas(new CommonUiBaseResponse() { // from class: sht
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i, Object obj) {
                GoogleFitDataManage.this.b(i, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Object obj) {
        LogUtil.a("GoogleFitDataManage", "requestGoogleFitPointDataList errCode = ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof List)) {
            this.h = (List) obj;
        }
        this.d.sendEmptyMessage(1);
    }

    private void m() {
        this.f10560a.requestGoogleFitSegentDatas(new CommonUiBaseResponse() { // from class: shq
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i, Object obj) {
                GoogleFitDataManage.this.c(i, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        synchronized (b) {
            if (i == 0) {
                if (obj instanceof List) {
                    LogUtil.a("GoogleFitDataManage", "requestGoogleSegmentDataList objData.");
                    this.i = (List) obj;
                }
            }
            this.d.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("GoogleFitDataManage", "mGoogleApiClient.isConnecting = ", Boolean.valueOf(this.c.isConnecting()), ",isConnected() = ", Boolean.valueOf(this.c.isConnected()));
        if (!this.c.isConnecting() && !this.c.isConnected()) {
            this.c.connect();
        }
        this.d.sendEmptyMessageDelayed(2, PreConnectManager.CONNECT_INTERNAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        long currentTimeMillis = System.currentTimeMillis();
        long t = HiDateUtil.t(System.currentTimeMillis());
        LogUtil.a("GoogleFitDataManage", "deleteTodayAllDataToGoogleFit startTime = ", Long.valueOf(t), " status= ", Fitness.HistoryApi.deleteData(this.c, new DataDeleteRequest.Builder().setTimeInterval(t, currentTimeMillis, TimeUnit.MILLISECONDS).deleteAllData().build()).await(1L, TimeUnit.MINUTES));
    }

    private void e(DataSet dataSet, String str, long j, long j2) {
        DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(j, j2, TimeUnit.MILLISECONDS);
        timeInterval.getValue(Field.FIELD_ACTIVITY).setActivity(str);
        dataSet.add(timeInterval);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(int r14, com.google.android.gms.fitness.data.DataSet r15, long r16, long r18) {
        /*
            r13 = this;
            switch(r14) {
                case 20002: goto L2f;
                case 20003: goto L23;
                case 20004: goto L17;
                case 20005: goto Lb;
                default: goto L3;
            }
        L3:
            switch(r14) {
                case 22001: goto L48;
                case 22002: goto L54;
                case 22003: goto L3c;
                default: goto L6;
            }
        L6:
            switch(r14) {
                case 22101: goto L54;
                case 22102: goto L54;
                case 22103: goto L48;
                case 22104: goto L3c;
                case 22105: goto L54;
                default: goto L9;
            }
        L9:
            goto L5f
        Lb:
            java.lang.String r2 = "biking"
            r0 = r13
            r1 = r15
            r3 = r16
            r5 = r18
            r0.e(r1, r2, r3, r5)
            goto L5f
        L17:
            java.lang.String r5 = "stair_climbing"
            r3 = r13
            r4 = r15
            r6 = r16
            r8 = r18
            r3.e(r4, r5, r6, r8)
            goto L5f
        L23:
            java.lang.String r8 = "running"
            r6 = r13
            r7 = r15
            r9 = r16
            r11 = r18
            r6.e(r7, r8, r9, r11)
            goto L5f
        L2f:
            java.lang.String r2 = "walking"
            r0 = r13
            r1 = r15
            r3 = r16
            r5 = r18
            r0.e(r1, r2, r3, r5)
            goto L5f
        L3c:
            java.lang.String r5 = "sleep.awake"
            r3 = r13
            r4 = r15
            r6 = r16
            r8 = r18
            r3.e(r4, r5, r6, r8)
            goto L5f
        L48:
            java.lang.String r8 = "sleep.deep"
            r6 = r13
            r7 = r15
            r9 = r16
            r11 = r18
            r6.e(r7, r8, r9, r11)
            goto L5f
        L54:
            java.lang.String r2 = "sleep.light"
            r0 = r13
            r1 = r15
            r3 = r16
            r5 = r18
            r0.e(r1, r2, r3, r5)
        L5f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.googlefit.GoogleFitDataManage.d(int, com.google.android.gms.fitness.data.DataSet, long, long):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        synchronized (b) {
            List<List<HiHealthData>> list = this.i;
            if (list != null && !list.isEmpty()) {
                GoogleApiClient googleApiClient = this.c;
                if (googleApiClient != null) {
                    DataSet create = DataSet.create(new DataSource.Builder().setAppPackageName("com.huawei.health").setDataType(DataType.TYPE_ACTIVITY_SEGMENT).setName("Huawei + steps dataset").setType(0).build());
                    for (List<HiHealthData> list2 : this.i) {
                        if (list2 != null && !list2.isEmpty()) {
                            for (HiHealthData hiHealthData : list2) {
                                d(hiHealthData.getType(), create, hiHealthData.getStartTime(), hiHealthData.getEndTime());
                            }
                        }
                    }
                    if (create.isEmpty()) {
                        return;
                    }
                    Status await = Fitness.HistoryApi.insertData(this.c, create).await(1L, TimeUnit.MINUTES);
                    if (await.equals(Status.RESULT_SUCCESS)) {
                        b("activity", SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_GOOGLTFIT);
                    }
                    LogUtil.a("GoogleFitDataManage", "activitySegmentDataSetStatus = ", await);
                } else {
                    LogUtil.a("GoogleFitDataManage", "mGoogleApiClient=null = ", googleApiClient);
                }
                return;
            }
            LogUtil.a("GoogleFitDataManage", "mHealthSegmentDataList = null or mHealthSegmentDataList.isEmpty()");
        }
    }

    protected void b(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(SyncDataConstant.BI_KEY_ACTIVITY_TYPE, str);
        hashMap.put(SyncDataConstant.BI_KEY_ACCOUNT_TYPE, str2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.THIRD_ACTIVITY_UPLOAD_2041094.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        synchronized (b) {
            List<List<HiHealthData>> list = this.h;
            if (list != null && !list.isEmpty()) {
                if (this.c != null) {
                    d();
                    return;
                } else {
                    LogUtil.a("GoogleFitDataManage", "mGoogleApiClient = null.");
                    return;
                }
            }
            LogUtil.a("GoogleFitDataManage", "mHealthDetailPointDataList = null or isEmpty()");
        }
    }

    private void d() {
        j();
        e();
        c();
    }

    private void c() {
        DataSet create = DataSet.create(b(DataType.TYPE_CALORIES_CONSUMED, "Huawei + calories dataset"));
        for (List<HiHealthData> list : this.h) {
            if (list != null && !list.isEmpty()) {
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData.getType() == 4 && hiHealthData.getValue() > 0.0d) {
                        create.add(a(create, Field.FIELD_CALORIES, hiHealthData));
                    }
                }
            }
        }
        if (create.isEmpty()) {
            return;
        }
        LogUtil.a("GoogleFitDataManage", "statusCaloriesDataStatus = ", Fitness.HistoryApi.insertData(this.c, create).await(1L, TimeUnit.MINUTES));
    }

    private void e() {
        DataSet create = DataSet.create(b(DataType.TYPE_DISTANCE_DELTA, "Huawei + ditance dataset"));
        for (List<HiHealthData> list : this.h) {
            if (list != null && !list.isEmpty()) {
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData.getType() == 3) {
                        create.add(a(create, Field.FIELD_DISTANCE, hiHealthData));
                    }
                }
            }
        }
        if (create.isEmpty()) {
            return;
        }
        LogUtil.a("GoogleFitDataManage", "statusDitanceDataStatus = ", Fitness.HistoryApi.insertData(this.c, create).await(1L, TimeUnit.MINUTES));
    }

    private void j() {
        DataSet create = DataSet.create(b(DataType.TYPE_STEP_COUNT_DELTA, "Huawei + steps dataset"));
        for (List<HiHealthData> list : this.h) {
            if (list != null && !list.isEmpty()) {
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData.getType() == 2) {
                        create.add(c(create, Field.FIELD_STEPS, hiHealthData));
                    }
                }
            }
        }
        if (create.isEmpty()) {
            return;
        }
        LogUtil.a("GoogleFitDataManage", "statusStepsDataStatus = ", Fitness.HistoryApi.insertData(this.c, create).await(1L, TimeUnit.MINUTES));
    }

    private DataPoint a(DataSet dataSet, Field field, HiHealthData hiHealthData) {
        DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime(), TimeUnit.MILLISECONDS);
        timeInterval.getValue(field).setFloat((float) hiHealthData.getValue());
        return timeInterval;
    }

    private DataPoint c(DataSet dataSet, Field field, HiHealthData hiHealthData) {
        DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime(), TimeUnit.MILLISECONDS);
        timeInterval.getValue(field).setInt((int) hiHealthData.getValue());
        return timeInterval;
    }

    private DataSource b(DataType dataType, String str) {
        return new DataSource.Builder().setAppPackageName("com.huawei.health").setDataType(dataType).setName(str).setType(0).build();
    }
}
