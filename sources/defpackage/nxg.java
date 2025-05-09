package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.TypefaceSpan;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
class nxg implements EarPhoneResponseCallback {

    /* renamed from: a, reason: collision with root package name */
    private DevicePairGuideActivity f15541a;
    private DeviceInfo e;
    private String j;
    private boolean c = false;
    private boolean d = false;
    private Handler b = new Handler(Looper.getMainLooper()) { // from class: nxg.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == -2) {
                ReleaseLogUtil.d("DEVMGR_EarPhonePairManager", "pair time out");
                sqo.l("earphone pair time out ");
                jgs.c().a(nxg.this.e);
                nxg.this.i();
                return;
            }
            if (i == -1) {
                ReleaseLogUtil.e("DEVMGR_EarPhonePairManager", "handleMessage, pair fail");
                removeMessages(-2);
                nxg.this.i();
                return;
            }
            if (i == 1) {
                ReleaseLogUtil.e("DEVMGR_EarPhonePairManager", "handleMessage, pair success");
                removeMessages(-2);
                nxg.this.o();
                return;
            }
            if (i == 4) {
                ReleaseLogUtil.e("DEVMGR_EarPhonePairManager", "handleMessage, get info success");
                nxg.this.cRH_(message);
                return;
            }
            if (i == 5) {
                ReleaseLogUtil.e("DEVMGR_EarPhonePairManager", "handleMessage, device skip pair");
                removeMessages(-2);
                if (nxg.this.c) {
                    return;
                }
                nxg.this.g();
                return;
            }
            if (i == 6) {
                ReleaseLogUtil.e("DEVMGR_EarPhonePairManager", "handleMessage, device retry pair");
                removeMessages(-2);
                if (nxg.this.c) {
                    return;
                }
                nxg.this.c();
                return;
            }
            d(message.what);
        }

        private void d(int i) {
            if (i == 0) {
                ReleaseLogUtil.d("DEVMGR_EarPhonePairManager", "earphone is ou side");
                removeMessages(-2);
                nxg.this.a();
            } else if (i == 2) {
                ReleaseLogUtil.d("DEVMGR_EarPhonePairManager", "device ignore pair");
                nxg.this.n();
            } else {
                LogUtil.h("EarPhonePairManager", "other status");
            }
        }
    };

    nxg() {
    }

    void c(DeviceInfo deviceInfo, DevicePairGuideActivity devicePairGuideActivity) {
        this.e = deviceInfo;
        this.f15541a = devicePairGuideActivity;
        e();
        j();
    }

    private void e() {
        this.f15541a.dk.setOnClickListener(new View.OnClickListener() { // from class: nxg.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nxg.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f15541a.df.setOnClickListener(new View.OnClickListener() { // from class: nxg.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nxg.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void j() {
        ReleaseLogUtil.e("EarPhonePairManager", "enter startPair");
        this.b.sendEmptyMessageDelayed(-2, 90000L);
        h();
        if (this.e == null) {
            LogUtil.h("EarPhonePairManager", "mCurrentDeviceInfo is null");
        } else {
            jgs.c().e(this.e, this);
        }
    }

    private void h() {
        DevicePairGuideActivity devicePairGuideActivity = this.f15541a;
        if (devicePairGuideActivity == null) {
            return;
        }
        this.c = true;
        devicePairGuideActivity.ct.setVisibility(0);
        this.f15541a.ct.setImageResource(R.drawable._2131430621_res_0x7f0b0cdd);
        this.f15541a.cb.setVisibility(0);
        this.f15541a.cv.setVisibility(8);
        this.f15541a.cu.setVisibility(8);
        this.f15541a.bv.setVisibility(8);
        if (this.f15541a.cb.getDrawable() instanceof AnimationDrawable) {
            DevicePairGuideActivity devicePairGuideActivity2 = this.f15541a;
            devicePairGuideActivity2.c = (AnimationDrawable) devicePairGuideActivity2.cb.getDrawable();
        }
        this.f15541a.c.start();
        this.f15541a.ab.setVisibility(0);
        this.f15541a.cv.setVisibility(8);
        this.f15541a.cw.setText(R.string._2130841387_res_0x7f020f2b);
        this.f15541a.x.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cRH_(Message message) {
        if (message.obj instanceof cuz) {
            h();
            cuz cuzVar = (cuz) message.obj;
            String b = cuzVar.b();
            this.j = cuzVar.c();
            if (TextUtils.isEmpty(b)) {
                this.f15541a.ab.setText("");
            } else {
                this.f15541a.ab.setText(this.f15541a.getResources().getString(R.string.IDS_blite_guide_paire_current_device, b));
            }
            if (!a(cuzVar)) {
                sqo.l("earphone and watch is not connect");
                a();
                return;
            }
            if (TextUtils.isEmpty(this.j) || !BluetoothAdapter.checkBluetoothAddress(this.j)) {
                i();
                jgs.c().a(this.e);
                this.b.removeMessages(-2);
            } else if (d(cuzVar) || this.d) {
                LogUtil.a("EarPhonePairManager", "start pair");
                jgs.c().d(this.e, this);
            } else {
                LogUtil.a("EarPhonePairManager", "waite device ignore");
                this.f15541a.cu.setVisibility(0);
                this.f15541a.cu.setText(R.string._2130845797_res_0x7f022065);
            }
        }
    }

    private boolean a(cuz cuzVar) {
        return (cuzVar.d() & 1) != 0;
    }

    private boolean d(cuz cuzVar) {
        int a2 = cuzVar.a();
        int e = cuzVar.e();
        int i = cuzVar.i();
        LogUtil.a("EarPhonePairManager", "earphone status is: ", Integer.valueOf(a2), " earCloseStatus: ", Integer.valueOf(e), " earphoneIgnore: ", Integer.valueOf(i));
        if (a2 == 3 && e == 1) {
            return true;
        }
        return i == 1 && e == 1 && a2 != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        DevicePairGuideActivity devicePairGuideActivity = this.f15541a;
        if (devicePairGuideActivity == null) {
            return;
        }
        this.d = true;
        devicePairGuideActivity.cu.setVisibility(8);
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        DevicePairGuideActivity devicePairGuideActivity = this.f15541a;
        if (devicePairGuideActivity == null) {
            return;
        }
        this.c = false;
        devicePairGuideActivity.bu.setVisibility(8);
        this.f15541a.bs.setVisibility(8);
        this.f15541a.ai.setVisibility(8);
        this.f15541a.cs.setVisibility(0);
        this.f15541a.cu.setVisibility(8);
        this.f15541a.l.setVisibility(8);
        this.f15541a.k.setVisibility(8);
        this.f15541a.n.setVisibility(8);
        this.f15541a.m.setVisibility(8);
        this.f15541a.cb.setVisibility(8);
        this.f15541a.s.setVisibility(8);
        this.f15541a.c.stop();
        this.f15541a.cb.setVisibility(8);
        this.f15541a.ab.setVisibility(8);
        this.f15541a.cv.setVisibility(0);
        this.f15541a.cv.setImageResource(R.drawable._2131430228_res_0x7f0b0b54);
        this.f15541a.cw.setText(R.string._2130845793_res_0x7f022061);
        this.f15541a.cu.setVisibility(0);
        this.f15541a.cu.setText(R.string._2130845794_res_0x7f022062);
        this.f15541a.bv.setVisibility(0);
        this.f15541a.x.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        jgs.c().d();
        if (this.f15541a == null) {
            return;
        }
        this.c = false;
        LogUtil.a("EarPhonePairManager", "updatePairSuccessView");
        this.f15541a.bv.setVisibility(8);
        this.f15541a.d = false;
        this.f15541a.ay.d(R.string._2130841385_res_0x7f020f29);
        this.f15541a.ct.setImageResource(R.drawable._2131430621_res_0x7f0b0cdd);
        f();
        d();
    }

    private void f() {
        this.f15541a.bp.setVisibility(0);
        String string = this.f15541a.getResources().getString(R.string._2130846328_res_0x7f022278);
        String string2 = this.f15541a.getResources().getString(R.string._2130841418_res_0x7f020f4a);
        String string3 = this.f15541a.getResources().getString(R.string.IDS_device_pair_minute_and_second, string, string2);
        SpannableString spannableString = new SpannableString(string3);
        int length = string3.length() - string2.length();
        spannableString.setSpan(new rvq(this.f15541a.getBaseContext(), "SagaPrivacyStatement"), length, spannableString.toString().length(), 18);
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), length, spannableString.toString().length(), 33);
        this.f15541a.bp.setMovementMethod(LinkMovementMethod.getInstance());
        this.f15541a.bp.setHighlightColor(this.f15541a.getResources().getColor(android.R.color.transparent));
        this.f15541a.bp.setText(spannableString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        jgs.c().d(this.e);
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        jgs.c().b(this.e);
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        jgs.c().d();
        if (this.f15541a == null) {
            return;
        }
        Handler handler = this.b;
        if (handler != null) {
            handler.removeMessages(-2);
        }
        this.f15541a.at.setVisibility(0);
        this.f15541a.at.setText(String.format(Locale.ROOT, this.f15541a.getResources().getString(R.string._2130845798_res_0x7f022066), this.f15541a.getResources().getString(R.string.IDS_wear_home_device_earphone)));
        this.f15541a.bv.setVisibility(8);
        this.f15541a.ay.d(R.string._2130845799_res_0x7f022067);
        this.f15541a.d = false;
        f();
        d();
    }

    protected void b() {
        sqo.l("user cancel pair");
        jgs.c().a(this.e, this.j);
        a();
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
    public void onResponse(int i, cuz cuzVar) {
        LogUtil.a("EarPhonePairManager", "executeResponse, errCode: ", Integer.valueOf(i));
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = cuzVar;
        this.b.sendMessage(obtain);
    }

    public void d() {
        LogUtil.a("EarPhonePairManager", "destroyInstance() start.");
        jgs.c().d();
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
