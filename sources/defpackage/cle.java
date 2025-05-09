package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.BaseInteractor;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hihealth.device.open.IHealthDeviceCallback;
import com.huawei.hihealth.device.open.MeasureController;
import com.huawei.hihealth.device.open.MeasureKit;
import com.huawei.hihealth.device.open.data.MeasureRecord;
import com.huawei.hihealth.device.open.data.MeasureResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class cle extends BaseInteractor {

    /* renamed from: a, reason: collision with root package name */
    private static int f779a = -4;
    private static int b = 20;
    private boolean d;
    private RelativeLayout e;
    private HealthTextView f;
    private Context g;
    private RelativeLayout h;
    private HealthTextView i;
    private final String k;
    private HealthTextView l;
    private RelativeLayout m;
    private ImageView o;
    private final View q;
    private final String s;
    private HealthTextView t;
    private int c = 0;
    private int r = 0;
    private Runnable n = new Runnable() { // from class: cle.1
        @Override // java.lang.Runnable
        public void run() {
            cle.this.n();
        }
    };
    private Handler j = new Handler() { // from class: cle.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1000:
                    cle.this.f(message.arg1);
                    break;
                case 1001:
                    cle.this.j();
                    break;
                case 1002:
                    cle.this.f();
                    break;
                case 1003:
                    cle.this.e();
                    break;
                case 1004:
                    cle.this.b();
                    break;
                case 1005:
                default:
                    LogUtil.a("PluginDevice_HonourRateStatus", "Enter default");
                    break;
                case 1006:
                    cle.this.i();
                    break;
                case 1007:
                    if (((Integer) message.obj).intValue() == 2) {
                        cle.this.t.setText(cle.this.g.getString(R.string._2130841442_res_0x7f020f62));
                        cle.this.l.setVisibility(0);
                        cle.this.o.setVisibility(8);
                        cle.this.l();
                    } else {
                        cle.this.t.setText(cle.this.g.getString(R.string._2130841443_res_0x7f020f63));
                        cle.this.l.setVisibility(8);
                        cle.this.o.setVisibility(0);
                        cle.this.t();
                    }
                    cle.this.onResume();
                    break;
            }
            super.handleMessage(message);
        }
    };

    public cle(Context context, View view, String str, String str2) {
        this.d = true;
        this.d = true;
        this.g = context;
        this.q = view;
        this.k = str;
        this.s = str2;
        h();
        g();
        o();
    }

    private void o() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HEART_RATE_SEE_2060020.value(), hashMap, 0);
    }

    private void g() {
        this.e.setOnClickListener(new View.OnClickListener() { // from class: cle.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (cle.f779a == -5) {
                    int unused = cle.b = 20;
                    Message obtain = Message.obtain();
                    obtain.what = 1002;
                    cle.this.j.sendMessage(obtain);
                } else if (cle.f779a == -6) {
                    int unused2 = cle.b = 20;
                    cle.this.m();
                } else {
                    LogUtil.a("PluginDevice_HonourRateStatus", "Function initListener enters 'else' option.");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void h() {
        this.e = (RelativeLayout) nsy.cMd_(this.q, R.id.card_heart_rate_layout);
        this.o = (ImageView) nsy.cMd_(this.q, R.id.heart_rate_measuring_status_img);
        this.f = (HealthTextView) nsy.cMd_(this.q, R.id.heart_rate_measuring_count_value);
        this.i = (HealthTextView) nsy.cMd_(this.q, R.id.heart_rate_measure_date_tv);
        this.t = (HealthTextView) nsy.cMd_(this.q, R.id.head_phone_connect_tv);
        this.l = (HealthTextView) nsy.cMd_(this.q, R.id.head_phone_measuring_tv);
        this.h = (RelativeLayout) nsy.cMd_(this.q, R.id.heart_fine_index_number_layout);
        this.m = (RelativeLayout) nsy.cMd_(this.q, R.id.sleep_pressure_layout);
        this.t.setText(this.g.getString(R.string._2130841443_res_0x7f020f63));
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.d = false;
        this.r = 0;
        this.j.removeCallbacks(this.n);
        a(false, 1.0f);
        this.o.setVisibility(8);
        this.l.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.d = false;
        f779a = -2;
        this.r = 0;
        this.j.postDelayed(this.n, 15000L);
        this.j.removeCallbacks(this.n);
        this.t.setText(this.g.getString(R.string._2130841442_res_0x7f020f62));
        l();
        a(false, 1.0f);
        this.l.setVisibility(0);
        this.o.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.d = true;
        a(true, 1.0f);
        b = 20;
        f779a = -6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        this.d = false;
        a(false, 1.0f);
        if (i == 0) {
            this.f.setText("--");
            this.i.setVisibility(8);
        } else {
            this.f.setText(String.valueOf(i));
            this.i.setVisibility(0);
            this.i.setText(nsj.a(System.currentTimeMillis()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.d = true;
        this.r = 0;
        b = 20;
        this.j.removeCallbacks(this.n);
        a(false, 0.38f);
        this.t.setText(this.g.getString(R.string._2130841443_res_0x7f020f63));
        this.l.setVisibility(8);
        this.o.setVisibility(0);
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("PluginDevice_HonourRateStatus", "closeHeartRateStatus");
        this.r = 0;
        b = 20;
        this.d = true;
        this.j.removeCallbacks(this.n);
        a(false, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.d = false;
        a(true, 1.0f);
        if (f779a != -5) {
            f779a = -5;
        }
    }

    private void a(boolean z, float f) {
        this.e.setEnabled(z);
        this.e.setAlpha(f);
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void onDestroy() {
        LogUtil.a("PluginDevice_HonourRateStatus", "Enter onDestroy");
        d();
        cjx.e().b(this.k, this.s);
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void setClean() {
        LogUtil.a("PluginDevice_HonourRateStatus", "setClean to enter");
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void controller(int i, Object obj) {
        LogUtil.a("PluginDevice_HonourRateStatus", "controller to enter");
    }

    @Override // com.huawei.health.device.ui.measure.BaseInteractor
    public void onResume() {
        LogUtil.a("PluginDevice_HonourRateStatus", "Enter onResume");
        if (this.d) {
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        MeasureController measureController;
        LogUtil.a("PluginDevice_HonourRateStatus", "Enter openHeartRateRunningForeground");
        b = 20;
        HealthDevice b2 = ceo.d().b(this.k, this.s);
        MeasureKit c = cfl.a().c("54C9739F-CA5C-4347-9F00-75B9DDF2C649");
        if (c == null || (measureController = c.getMeasureController()) == null) {
            return;
        }
        measureController.prepare(b2, new IHealthDeviceCallback() { // from class: cle.5
            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, MeasureResult measureResult) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onProgressChanged(HealthDevice healthDevice, MeasureRecord measureRecord) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onStatusChanged(HealthDevice healthDevice, int i) {
                LogUtil.a("PluginDevice_HonourRateStatus", "-----prepare heartRateData_onStatusChanged---:", Integer.valueOf(i));
                cle.this.c = i;
                if (BaseApplication.isRunningForeground() && cle.this.c == 2) {
                    cle.this.k();
                    return;
                }
                if (i == 3 || i == 1 || i == 8) {
                    cle.this.c = i;
                    int unused = cle.f779a = -4;
                    Message obtain = Message.obtain();
                    obtain.what = 1004;
                    cle.this.j.sendMessage(obtain);
                    return;
                }
                LogUtil.a("PluginDevice_HonourRateStatus", "openHeartRateRunningForeground 'else' option.");
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onFailed(HealthDevice healthDevice, int i) {
                LogUtil.a("PluginDevice_HonourRateStatus", "openHeartRateRunningForeground onFailed code = ", Integer.valueOf(i));
            }
        }, cpa.Ka_());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("PluginDevice_HonourRateStatus", "Enter openHeartRate");
        this.j.removeCallbacks(this.n);
        this.d = false;
        f779a = -1;
        Message obtain = Message.obtain();
        obtain.what = 1001;
        this.j.sendMessage(obtain);
        this.j.postDelayed(this.n, 15000L);
        cjx.e().Gu_(this.k, this.s, new IHealthDeviceCallback() { // from class: cle.3
            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onProgressChanged(HealthDevice healthDevice, MeasureRecord measureRecord) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, MeasureResult measureResult) {
                cle.this.c(measureResult);
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onStatusChanged(HealthDevice healthDevice, int i) {
                cle.this.c(i);
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onFailed(HealthDevice healthDevice, int i) {
                LogUtil.a("PluginDevice_HonourRateStatus", "openHeartRateRunningForeground onFailed code = ", Integer.valueOf(i));
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("PluginDevice_HonourRateStatus", "-----heartRateData_onStatusChanged---:", Integer.valueOf(i));
        if (i == 3 || i == 8) {
            this.c = i;
            f779a = -4;
            Message obtain = Message.obtain();
            obtain.what = 1004;
            this.j.sendMessage(obtain);
            return;
        }
        if (i == 14) {
            d(10000);
            return;
        }
        if (i == 2) {
            this.c = i;
            Message obtain2 = Message.obtain();
            obtain2.what = 1007;
            obtain2.obj = Integer.valueOf(i);
            this.j.sendMessage(obtain2);
            return;
        }
        if (i == 12) {
            this.c = i;
            Message obtain3 = Message.obtain();
            obtain3.what = 1004;
            this.j.sendMessage(obtain3);
            return;
        }
        LogUtil.a("PluginDevice_HonourRateStatus", "openHeartRate 'else' option.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MeasureResult measureResult) {
        der derVar;
        if (dic.c().d(measureResult) instanceof der) {
            derVar = (der) dic.c().d(measureResult);
        } else {
            LogUtil.h("PluginDevice_HonourRateStatus", "ConvertDataUtil.getInstance().convertData(dat) not instanceof HeartRate");
            derVar = null;
        }
        if (derVar == null || derVar.getHeartRate() == -1) {
            return;
        }
        LogUtil.c("PluginDevice_HonourRateStatus", "-----heartRateData---:", Integer.valueOf(derVar.getHeartRate()));
        a(derVar.getHeartRate());
        if (BaseApplication.isRunningForeground()) {
            return;
        }
        a();
        cjx.e().b(this.k, this.s);
    }

    private void d(int i) {
        if (i == 100000) {
            f779a = -2;
            this.j.removeCallbacks(this.n);
            Message obtain = Message.obtain();
            obtain.what = 1002;
            this.j.sendMessage(obtain);
        }
    }

    public void d() {
        LogUtil.a("PluginDevice_HonourRateStatus", "Enter closeHeartRateRunningForeground");
        a();
    }

    private void a() {
        LogUtil.a("PluginDevice_HonourRateStatus", "Enter closeHeartRate");
        cjx.e().e(this.k, this.s);
        j(100000);
    }

    private void j(int i) {
        if (i == 100000) {
            f779a = -4;
            Message obtain = Message.obtain();
            obtain.what = 1003;
            this.j.sendMessage(obtain);
        }
    }

    private void a(int i) {
        this.d = false;
        this.j.removeCallbacks(this.n);
        this.j.removeCallbacks(this.n);
        this.j.postDelayed(this.n, 15000L);
        if (i == 0) {
            int i2 = this.r + 1;
            this.r = i2;
            if (i2 > b) {
                Message obtain = Message.obtain();
                obtain.what = 1006;
                this.j.sendMessage(obtain);
                return;
            }
        } else {
            this.r = 0;
            b = 15;
        }
        LogUtil.a("PluginDevice_HonourRateStatus", "heartRateStatus = ", Integer.valueOf(f779a));
        if (i == 0 && f779a == -2) {
            LogUtil.a("PluginDevice_HonourRateStatus", "heart rate status can not refresh");
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 1000;
        obtain2.arg1 = i;
        this.j.sendMessage(obtain2);
        f779a = -3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        this.m.setEnabled(false);
        this.h.setEnabled(false);
        this.m.setAlpha(0.38f);
        this.h.setAlpha(0.38f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.m.setEnabled(true);
        this.h.setEnabled(true);
        this.m.setAlpha(1.0f);
        this.h.setAlpha(1.0f);
    }
}
