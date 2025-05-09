package com.huawei.health.manager;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.IRealStepDataReport;
import com.huawei.health.icommon.IStepReport;
import com.huawei.health.manager.util.Consts;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogicalStepCounter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class RealTimeStepDataReportHelper implements IStepReport {
    private Context c;
    private LogicalStepCounter f;
    private SensorManager k;
    private long b = 0;
    private int j = 0;
    private int h = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f2780a = 1000;
    private IRealStepDataReport d = null;
    private ExtendHandler e = null;
    private int g = 0;
    private final SensorEventListener i = new SensorEventListener() { // from class: com.huawei.health.manager.RealTimeStepDataReportHelper.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
        }
    };

    static /* synthetic */ int b(RealTimeStepDataReportHelper realTimeStepDataReportHelper) {
        int i = realTimeStepDataReportHelper.g;
        realTimeStepDataReportHelper.g = i + 1;
        return i;
    }

    public RealTimeStepDataReportHelper(Context context) {
        this.f = null;
        this.c = context;
        if (context != null) {
            this.f = LogicalStepCounter.c(context);
            Object systemService = this.c.getSystemService("sensor");
            if (systemService instanceof SensorManager) {
                this.k = (SensorManager) systemService;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.d != null) {
            try {
                LogicalStepCounter logicalStepCounter = this.f;
                if (logicalStepCounter == null) {
                    return;
                }
                if (z) {
                    this.j = logicalStepCounter.c();
                }
                this.d.report(this.j, this.h);
            } catch (RemoteException e) {
                LogUtil.b("Step_RTStepDataRptHlp", "RemoteException", e.getMessage());
            } catch (Exception unused) {
                LogUtil.b("Step_RTStepDataRptHlp", "Exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        if (context == null) {
            return;
        }
        String packageName = context.getPackageName();
        LogUtil.a("Step_RTStepDataRptHlp", "sendBroadcast() ", " action == ", str);
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction("com.huawei.health.track.broadcast");
        intent.putExtra("command_type", str);
        context.sendBroadcast(intent, Consts.f2803a);
    }

    public boolean d(IRealStepDataReport iRealStepDataReport, int i) {
        if (iRealStepDataReport == null || i < 1000) {
            return false;
        }
        this.d = iRealStepDataReport;
        this.f2780a = i;
        this.e = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.health.manager.RealTimeStepDataReportHelper.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000) {
                    if (RealTimeStepDataReportHelper.this.e == null) {
                        ReleaseLogUtil.d("Step_RTStepDataRptHlp", "mExtendHandler is null.");
                        return false;
                    }
                    RealTimeStepDataReportHelper.this.b(TextUtils.equals((String) message.obj, "refresh"));
                    RealTimeStepDataReportHelper.this.e.sendEmptyMessage(1000, RealTimeStepDataReportHelper.this.f2780a);
                    return true;
                }
                if (i2 == 2000) {
                    LogUtil.a("Step_RTStepDataRptHlp", "try recovery Motion track ", Integer.valueOf(RealTimeStepDataReportHelper.this.g));
                    if (RealTimeStepDataReportHelper.this.g >= 3) {
                        return true;
                    }
                    RealTimeStepDataReportHelper realTimeStepDataReportHelper = RealTimeStepDataReportHelper.this;
                    realTimeStepDataReportHelper.a(realTimeStepDataReportHelper.c, "com.huawei.track.restart");
                    RealTimeStepDataReportHelper.b(RealTimeStepDataReportHelper.this);
                    return true;
                }
                if (i2 == 1002) {
                    RealTimeStepDataReportHelper.this.j = message.arg1;
                    return true;
                }
                if (i2 != 1003) {
                    return false;
                }
                RealTimeStepDataReportHelper.this.h = message.arg1;
                return true;
            }
        }, "flush_worker_thread");
        this.h = 0;
        LogicalStepCounter logicalStepCounter = this.f;
        if (logicalStepCounter != null) {
            logicalStepCounter.e(this);
        }
        SensorManager sensorManager = this.k;
        if (sensorManager != null) {
            try {
                ReleaseLogUtil.e("Step_RTStepDataRptHlp", " startReportRealTimeStepData registerListener = ", Boolean.valueOf(sensorManager.registerListener(this.i, sensorManager.getDefaultSensor(19), 0)));
            } catch (RuntimeException unused) {
                ReleaseLogUtil.c("Step_RTStepDataRptHlp", "startReportRealTimeStepData happened exception");
                return false;
            }
        }
        if (this.e != null) {
            Message obtain = Message.obtain();
            obtain.what = 1000;
            obtain.obj = "refresh";
            this.e.sendMessage(obtain);
        }
        return true;
    }

    public boolean a() {
        LogUtil.a("Step_RTStepDataRptHlp", "enter stopReportRealTimeStepData.");
        this.d = null;
        this.h = 0;
        ExtendHandler extendHandler = this.e;
        if (extendHandler != null) {
            extendHandler.removeMessages(1000);
            this.e.quit(false);
        }
        LogicalStepCounter logicalStepCounter = this.f;
        if (logicalStepCounter != null) {
            logicalStepCounter.c(this);
        }
        SensorManager sensorManager = this.k;
        if (sensorManager == null) {
            return true;
        }
        sensorManager.unregisterListener(this.i, sensorManager.getDefaultSensor(19));
        return true;
    }

    public void e() {
        ExtendHandler extendHandler = this.e;
        if (extendHandler != null) {
            this.g = 0;
            extendHandler.sendEmptyMessage(2000, 60000L);
        }
    }

    public void c() {
        ExtendHandler extendHandler = this.e;
        if (extendHandler != null) {
            extendHandler.removeMessages(2000);
        }
    }

    public void d() {
        ExtendHandler extendHandler = this.e;
        if (extendHandler != null) {
            extendHandler.removeMessages(2000);
            this.e.sendEmptyMessage(2000, 60000L);
        }
    }

    @Override // com.huawei.health.icommon.IStepReport
    public void onStandStepChanged(int i) {
        if (this.e != null) {
            Message obtain = Message.obtain();
            obtain.what = 1002;
            obtain.arg1 = i;
            this.e.sendMessage(obtain);
        }
    }

    @Override // com.huawei.health.icommon.IStepReport
    public void onExtendStepChanged(long j, int i, int i2, int i3) {
        if (this.e != null) {
            Message obtain = Message.obtain();
            obtain.what = 1003;
            obtain.arg1 = i3;
            this.e.sendMessage(obtain);
        }
    }

    public void b() {
        SensorManager sensorManager = this.k;
        if (sensorManager != null) {
            boolean z = false;
            try {
                z = sensorManager.registerListener(this.i, sensorManager.getDefaultSensor(19), 0);
            } catch (RuntimeException unused) {
                ReleaseLogUtil.c("Step_RTStepDataRptHlp", "forceRefreshStepCounter happened exception");
            }
            long b = LogUtil.b(5000, this.b);
            if (b != -1) {
                ReleaseLogUtil.e("Step_RTStepDataRptHlp", " fRefrushStepCt registerListener=", Boolean.valueOf(z));
                this.b = b;
            }
            SensorManager sensorManager2 = this.k;
            sensorManager2.unregisterListener(this.i, sensorManager2.getDefaultSensor(19));
            if (ScreenUtil.a()) {
                return;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                LogUtil.h("Step_RTStepDataRptHlp", "fRefrushStepCt ", e.getMessage());
            }
        }
    }
}
