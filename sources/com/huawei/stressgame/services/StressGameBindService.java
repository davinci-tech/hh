package com.huawei.stressgame.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.algorithm.callback.StressGameBindCallback;
import com.huawei.health.algorithm.callback.StressGameNoticeInterface;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.stressgame.algorithm.BIO;
import defpackage.dwo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class StressGameBindService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private StressGameBinder f8723a;
    private d c;
    private long d;
    private c e;
    private long f;
    private StressGameNoticeInterface k;
    private StressGameBindCallback l;
    private TimerTask r;
    private Timer t;
    private int i = 0;
    private float[] j = new float[43];
    private int h = 0;
    private short[] m = new short[14];
    private byte[] o = new byte[14];
    private int b = 0;
    private boolean g = true;
    private a n = new a(this);

    public StressGameBindService() {
        this.e = new c();
        this.c = new d();
    }

    static /* synthetic */ int b(StressGameBindService stressGameBindService) {
        int i = stressGameBindService.i;
        stressGameBindService.i = i + 1;
        return i;
    }

    static /* synthetic */ int k(StressGameBindService stressGameBindService) {
        int i = stressGameBindService.b;
        stressGameBindService.b = i + 1;
        return i;
    }

    public void d(StressGameNoticeInterface stressGameNoticeInterface) {
        if (stressGameNoticeInterface != null) {
            this.k = stressGameNoticeInterface;
        }
    }

    static class a extends BaseHandler<StressGameBindService> {
        a(StressGameBindService stressGameBindService) {
            super(stressGameBindService);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cuV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(StressGameBindService stressGameBindService, Message message) {
            if (message.what == 10000) {
                stressGameBindService.d(message.obj.toString(), stressGameBindService.m, stressGameBindService.o);
                StressGameBindService.k(stressGameBindService);
                LogUtil.a("StressGameBindService", " mHandler mCallbackCounter: ", Integer.valueOf(stressGameBindService.b));
                stressGameBindService.h();
                stressGameBindService.d = System.currentTimeMillis();
            }
        }
    }

    static class c extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<StressGameBindService> f8725a;

        private c(StressGameBindService stressGameBindService) {
            this.f8725a = new WeakReference<>(stressGameBindService);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            StressGameBindService stressGameBindService = this.f8725a.get();
            if (stressGameBindService == null || stressGameBindService.l == null) {
                LogUtil.h("StressGameBindService", "BluetoothConnect mStressGameBindService is null ");
                return;
            }
            if (context == null || intent == null) {
                LogUtil.h("StressGameBindService", "BluetoothConnect context == null or BluetoothConnect intent == null");
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                DeviceInfo deviceInfo = intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo ? (DeviceInfo) intent.getParcelableExtra("deviceinfo") : null;
                if (deviceInfo == null) {
                    LogUtil.h("StressGameBindService", "deviceInfo is null");
                    return;
                }
                if (deviceInfo.getRelationship() == null || !"followed_relationship".equals(deviceInfo.getRelationship())) {
                    stressGameBindService.l.connectState(0.0f);
                    stressGameBindService.b();
                    LogUtil.a("StressGameBindService", "Bluetooth no connect 200");
                    return;
                }
                LogUtil.a("StressGameBindService", "This device does not have the correspond capability.");
                return;
            }
            if ("com.huawei.bone.action.REQUEST_BIND_DEVICE".equals(intent.getAction()) && TextUtils.isEmpty(intent.getStringExtra("connect_status"))) {
                stressGameBindService.l.requstConnet(1.0f);
            } else {
                LogUtil.h("StressGameBindService", "BluetoothConnect mStressGameBindService error ");
            }
        }
    }

    static class d implements IBaseResponseCallback {
        private final WeakReference<StressGameBindService> d;

        private d(StressGameBindService stressGameBindService) {
            this.d = new WeakReference<>(stressGameBindService);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            StressGameBindService stressGameBindService = this.d.get();
            if (stressGameBindService == null) {
                LogUtil.h("StressGameBindService", "IBaseResponseCallbackImp service is null ");
                return;
            }
            LogUtil.a("StressGameBindService", "enterCallBackTime: ", Long.valueOf(System.currentTimeMillis()));
            stressGameBindService.g();
            if (i == 0 && obj != null) {
                stressGameBindService.n.obtainMessage(10000, obj).sendToTarget();
                return;
            }
            LogUtil.h("StressGameBindService", "device callback error and errorCode: ", Integer.valueOf(i));
            switch (i) {
                case 125001:
                    LogUtil.h("StressGameBindService", "bluetooth input err param and measure fail ");
                    break;
                case 125002:
                    LogUtil.h("StressGameBindService", "measure fail ");
                    break;
                default:
                    LogUtil.h("StressGameBindService", "other err errCode");
                    break;
            }
            LogUtil.h("StressGameBindService", "enter is12SecondWithoutData ");
            if (stressGameBindService.l != null) {
                stressGameBindService.l.measureEnd(400.0f);
            }
            stressGameBindService.d();
        }
    }

    public class StressGameBinder extends Binder {
        public StressGameBinder() {
        }

        public StressGameBindService getService() {
            return StressGameBindService.this;
        }
    }

    public void b(StressGameBindCallback stressGameBindCallback) {
        if (stressGameBindCallback == null) {
            LogUtil.h("StressGameBindService", "setStressGameBindCallback callback = null;");
        } else {
            LogUtil.c("StressGameBindService", "this.mStressGameBindCallback = callback;");
            this.l = stressGameBindCallback;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.g = true;
        LogUtil.a("StressGameBindService", "service onCreate");
        StressGameNoticeInterface stressGameNoticeInterface = this.k;
        if (stressGameNoticeInterface != null) {
            stressGameNoticeInterface.setCloseMeasure(false);
            this.k.setStopTimer(false);
        }
        LogUtil.a("StressGameBindService", "service start resetArrayData();");
        g();
        LogUtil.a("StressGameBindService", "service start emptyArraysAndDatas();");
        i();
        LogUtil.a("StressGameBindService", "service startTimer();");
        j();
        LogUtil.a("StressGameBindService", "Bluetooth insert;");
        this.f8723a = new StressGameBinder();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a("StressGameBindService", "onBind");
        return this.f8723a;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.a("StressGameBindService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("StressGameBindService", "onStartCommand");
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("StressGameBindService", "onDestroy");
        Timer timer = this.t;
        if (timer != null) {
            timer.cancel();
            this.t = null;
        }
        TimerTask timerTask = this.r;
        if (timerTask != null) {
            timerTask.cancel();
            this.r = null;
        }
        StressGameNoticeInterface stressGameNoticeInterface = this.k;
        if (stressGameNoticeInterface != null && !stressGameNoticeInterface.getCloseMeasure()) {
            this.k.setCloseMeasure(true);
            LogUtil.a("StressGameBindService", "bindService destroy, closeMeasure(4)");
            b();
        }
        unregisterReceiver(this.e);
    }

    private void j() {
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.REQUEST_BIND_DEVICE");
        BroadcastManagerUtil.bFC_(this, this.e, intentFilter, LocalBroadcast.c, null);
    }

    public void a() {
        LogUtil.a("StressGameBindService", "begin startTimer");
        this.t = new Timer("StressGameBindService");
        TimerTask timerTask = new TimerTask() { // from class: com.huawei.stressgame.services.StressGameBindService.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (StressGameBindService.this.g) {
                    StressGameBindService.b(StressGameBindService.this);
                    if (StressGameBindService.this.i >= 45) {
                        StressGameBindService.this.l();
                    } else {
                        StressGameBindService.this.m();
                    }
                }
            }
        };
        this.r = timerTask;
        this.t.schedule(timerTask, 6200L, 4000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("StressGameBindService", "measure end");
        StressGameBindCallback stressGameBindCallback = this.l;
        if (stressGameBindCallback != null) {
            stressGameBindCallback.measureEnd(100.0f);
        }
        StressGameNoticeInterface stressGameNoticeInterface = this.k;
        if (stressGameNoticeInterface != null && !stressGameNoticeInterface.getCloseMeasure()) {
            this.k.setCloseMeasure(true);
            b();
            LogUtil.a("StressGameBindService", " measure end and close device");
        }
        g();
        StressGameNoticeInterface stressGameNoticeInterface2 = this.k;
        if (stressGameNoticeInterface2 == null || stressGameNoticeInterface2.getStopTimer()) {
            return;
        }
        this.k.setStopTimer(true);
        LogUtil.a("StressGameBindService", " stopTimer()");
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        int i = this.b;
        this.f = System.currentTimeMillis();
        LogUtil.a("StressGameBindService", "mFourSecondCycleTime");
        if ((this.i >= 4 && this.b == 0) || (e(this.d, this.f) && this.b > 0)) {
            LogUtil.a("StressGameBindService", "enter is12SecondWithoutData ");
            StressGameBindCallback stressGameBindCallback = this.l;
            if (stressGameBindCallback != null) {
                stressGameBindCallback.measureEnd(400.0f);
            }
            StressGameNoticeInterface stressGameNoticeInterface = this.k;
            if (stressGameNoticeInterface != null && !stressGameNoticeInterface.getCloseMeasure()) {
                this.k.setCloseMeasure(true);
                LogUtil.a("StressGameBindService", "12s no data  ");
                b();
            }
            g();
            StressGameNoticeInterface stressGameNoticeInterface2 = this.k;
            if (stressGameNoticeInterface2 == null || stressGameNoticeInterface2.getStopTimer()) {
                return;
            }
            this.k.setStopTimer(true);
            LogUtil.a("StressGameBindService", " 12s nodata and stopTimer()");
            d();
            return;
        }
        if (i == this.b) {
            g();
        }
    }

    public void d() {
        this.g = false;
        LogUtil.a("StressGameBindService", "stopTimer");
        i();
        g();
        c();
    }

    private void c() {
        Timer timer = this.t;
        if (timer != null) {
            timer.cancel();
            this.t = null;
        }
        TimerTask timerTask = this.r;
        if (timerTask != null) {
            timerTask.cancel();
            this.r = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        int i = this.h;
        short[] sArr = new short[i];
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < this.h; i2++) {
            sArr[i2] = this.m[i2];
            bArr[i2] = this.o[i2];
        }
        BIO.b();
        float[] bioFeedbackAlgorithm = BIO.bioFeedbackAlgorithm(sArr, bArr, this.h, 180, this.b);
        this.j = bioFeedbackAlgorithm;
        if (this.l != null) {
            try {
                if (bioFeedbackAlgorithm.length >= 28) {
                    LogUtil.a("StressGameBindService", "mStressGameBindCallback!=null");
                    f();
                } else {
                    LogUtil.h("StressGameBindService", "data error");
                }
            } catch (JSONException unused) {
                LogUtil.b("StressGameBindService", "sendData JSONException");
            }
        }
    }

    private void f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("BreathTrainReturnFlag", this.j[0]);
        jSONObject.put("Balance", this.j[1]);
        jSONObject.put("BreGrade", this.j[2]);
        jSONObject.put("Score", this.j[3]);
        jSONObject.put("SuccessRetFlag", this.j[4]);
        jSONObject.put("mForwardState", this.j[5]);
        jSONObject.put("mEnergyValue", this.j[6]);
        jSONObject.put("BubbleChartRetFlag", this.j[7]);
        jSONObject.put("SubZoneColor", this.j[8]);
        jSONObject.put("Height", this.j[9]);
        jSONObject.put("Radius", this.j[10]);
        jSONObject.put("DisplayResultFlag", this.j[11]);
        jSONObject.put("calm", this.j[12]);
        jSONObject.put("control", this.j[13]);
        jSONObject.put("fluency", this.j[14]);
        jSONObject.put("stabilization", this.j[15]);
        jSONObject.put("resist", this.j[16]);
        jSONObject.put("GoodPercent", this.j[17]);
        jSONObject.put("CommonPercent", this.j[18]);
        jSONObject.put("BadPercent", this.j[19]);
        jSONObject.put("mScore", this.j[20]);
        jSONObject.put("mGrade", this.j[21]);
        jSONObject.put("MaxHeartRate", this.j[22]);
        jSONObject.put("MeanHeartRate", this.j[23]);
        jSONObject.put("MinHeartRate", this.j[24]);
        jSONObject.put("RealTimeRemind", this.j[25]);
        jSONObject.put("Algorithm_version", this.j[26]);
        jSONObject.put("variableC", this.j[27]);
        this.l.algResult(jSONObject.toString());
    }

    private boolean e(long j, long j2) {
        LogUtil.a("StressGameBindService", " enter is12SecondWithoutData");
        if ((j2 - j) / 1000 < 12) {
            return false;
        }
        LogUtil.a("StressGameBindService", " enter is12SecondWithoutData return true");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        int i = 0;
        while (true) {
            short[] sArr = this.m;
            if (i < sArr.length) {
                sArr[i] = 0;
                this.o[i] = 0;
                i++;
            } else {
                Arrays.fill(this.j, 0.0f);
                this.h = 0;
                return;
            }
        }
    }

    private void i() {
        this.b = 0;
        this.i = 0;
        this.d = 0L;
        this.f = 0L;
    }

    public void e() {
        LogUtil.a("StressGameBindService", "begin openMeasure = ", 3);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 3);
        } catch (JSONException unused) {
            LogUtil.b("StressGameBindService", "openMeasure () JSONException");
        }
        dwo.d().b(jSONObject, new e(1));
        LogUtil.a("StressGameBindService", "end openMeasure");
    }

    public void b() {
        LogUtil.a("StressGameBindService", "closeMeasure()");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 4);
        } catch (JSONException unused) {
            LogUtil.b("StressGameBindService", "closeMeasure () JSONException ");
        }
        dwo.d().b(jSONObject, new e(2));
    }

    static class e implements IBaseResponseCallback {
        private int d;
        private final WeakReference<StressGameBindService> e;

        private e(StressGameBindService stressGameBindService, int i) {
            this.d = i;
            this.e = new WeakReference<>(stressGameBindService);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            StressGameBindService stressGameBindService = this.e.get();
            if (stressGameBindService == null) {
                LogUtil.h("StressGameBindService", "StressGameBindService is null");
                return;
            }
            int i2 = this.d;
            if (i2 == 1) {
                e(i, obj, stressGameBindService);
            } else if (i2 == 2) {
                d(i, obj, stressGameBindService);
            } else {
                LogUtil.a("StressGameBindService", "default mCallbacktype: ", Integer.valueOf(i2));
            }
        }

        private void d(int i, Object obj, StressGameBindService stressGameBindService) {
            if (obj == null) {
                LogUtil.h("StressGameBindService", "closeMeasure Message is null!!!");
                return;
            }
            LogUtil.a("StressGameBindService", "closeMeasure errCode", Integer.valueOf(i));
            if (i == 100000) {
                dwo.d().s(stressGameBindService.c);
            } else {
                LogUtil.h("StressGameBindService", "Message is not right!!!");
            }
        }

        private void e(int i, Object obj, StressGameBindService stressGameBindService) {
            if (obj == null) {
                LogUtil.h("StressGameBindService", "setHeartRateReportStatus callback objData is null");
                return;
            }
            LogUtil.a("StressGameBindService", "setHeartRateReportStatus errorCode: ", Integer.valueOf(i));
            if (i == 100000) {
                dwo.d().g(stressGameBindService.c);
            } else {
                stressGameBindService.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, short[] sArr, byte[] bArr) {
        this.h = 0;
        if (str == null) {
            LogUtil.h("StressGameBindService", "heartRateInfos is null");
            return;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("rri_list");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (this.h < sArr.length && Integer.parseInt(String.valueOf(jSONArray.getJSONObject(i).get("rri_value"))) != 0) {
                    sArr[this.h] = Short.parseShort(String.valueOf(jSONArray.getJSONObject(i).get("rri_value")));
                    bArr[this.h] = Byte.parseByte(String.valueOf(jSONArray.getJSONObject(i).get("rri_sqi")));
                    this.h++;
                }
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("StressGameBindService", "getRriAndSqi NumberFormatException");
        } catch (JSONException unused2) {
            LogUtil.b("StressGameBindService", "getRriAndSqi JSONException");
        }
    }
}
