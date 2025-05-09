package defpackage;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
class nwz {
    nwz() {
    }

    protected boolean e(DevicePairGuideActivity devicePairGuideActivity) {
        LogUtil.a("DevicePairGuideMidWareSetting", "isShowMidWare activity:", devicePairGuideActivity);
        if (devicePairGuideActivity == null) {
            return false;
        }
        if (d(devicePairGuideActivity)) {
            if (!devicePairGuideActivity.bm) {
                LogUtil.a("DevicePairGuideMidWareSetting", "isShowMidWare mIsSkipShowMidWare is false");
                return true;
            }
        } else {
            devicePairGuideActivity.ca.setVisibility(8);
        }
        return false;
    }

    protected boolean d(DevicePairGuideActivity devicePairGuideActivity) {
        boolean z = false;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DevicePairGuideMidWareSetting", "isSupportMidWare() activity is null");
            return false;
        }
        if (jpo.c(devicePairGuideActivity.ae) == 2) {
            return false;
        }
        if (jfu.o(devicePairGuideActivity.an) && cvz.c()) {
            z = true;
        }
        ReleaseLogUtil.e("DEVMGR_DevicePairGuideMidWareSetting", "isSupportMidWare() isSupportMidWare:", Boolean.valueOf(z));
        return z;
    }

    protected void c(final DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.ca = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.ll_pairing_guide_mid_ware);
        devicePairGuideActivity.u = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.btn_pairing_guide_mid_ware_sure);
        devicePairGuideActivity.v = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.btn_pairing_guide_mid_ware_cancel);
        b(devicePairGuideActivity, devicePairGuideActivity.u);
        b(devicePairGuideActivity, devicePairGuideActivity.v);
        devicePairGuideActivity.u.setOnClickListener(new View.OnClickListener() { // from class: nwz.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("DEVMGR_DevicePairGuideMidWareSetting", "mButtonPairGuideMidWareSure onclick");
                nwz.this.b(devicePairGuideActivity, true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        devicePairGuideActivity.v.setOnClickListener(new View.OnClickListener() { // from class: nwz.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("DEVMGR_DevicePairGuideMidWareSetting", "mButtonPairGuideMidWareCancel onclick");
                nwz.this.b(devicePairGuideActivity, false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(DevicePairGuideActivity devicePairGuideActivity, HealthButton healthButton) {
        if (Utils.i() && CommonUtil.h(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)) == 7) {
            healthButton.setBackgroundResource(R.drawable._2131428850_res_0x7f0b05f2);
            healthButton.setTextColor(devicePairGuideActivity.getColor(R.color._2131296927_res_0x7f09029f));
        }
    }

    protected void b(DevicePairGuideActivity devicePairGuideActivity, boolean z) {
        if (devicePairGuideActivity == null) {
            return;
        }
        cvz.c(Boolean.valueOf(z));
        jrg.a(z);
        cvz.b(knl.d(devicePairGuideActivity.ae), true);
        devicePairGuideActivity.bm = true;
        devicePairGuideActivity.ca.setVisibility(8);
        if (devicePairGuideActivity.bb) {
            i(devicePairGuideActivity);
        } else {
            devicePairGuideActivity.ax.sendEmptyMessage(1);
            a(devicePairGuideActivity);
        }
    }

    protected void cRE_(DevicePairGuideActivity devicePairGuideActivity, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("is_support_special_midware", false);
        LogUtil.a("DevicePairGuideMidWareSetting", "onActivityForResult: isSupportSpecialMidWare:", Boolean.valueOf(booleanExtra));
        d(devicePairGuideActivity, booleanExtra);
    }

    protected void d(DevicePairGuideActivity devicePairGuideActivity, boolean z) {
        if (!d(devicePairGuideActivity) && !z) {
            devicePairGuideActivity.ca.setVisibility(8);
            a(devicePairGuideActivity);
        } else {
            b(devicePairGuideActivity);
        }
    }

    protected boolean d(int i, int i2) {
        boolean z = !jfu.o(i) && jfu.o(i2) && cvz.c();
        LogUtil.a("DevicePairGuideMidWareSetting", "receiverPairRequest: isSupportSpecialMidWare:", Boolean.valueOf(z));
        return z;
    }

    private void a(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            LogUtil.h("DevicePairGuideMidWareSetting", "receiverPairRequest activity is null");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 13;
        obtain.obj = true;
        devicePairGuideActivity.ax.sendMessage(obtain);
    }

    private void i(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.e();
        devicePairGuideActivity.b();
    }

    protected void b(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        LogUtil.a("DevicePairGuideMidWareSetting", "showMidWareLayout() activity:", devicePairGuideActivity);
        devicePairGuideActivity.be = true;
        devicePairGuideActivity.bu.setVisibility(8);
        devicePairGuideActivity.bs.setVisibility(8);
        devicePairGuideActivity.ai.setVisibility(8);
        devicePairGuideActivity.t.setVisibility(8);
        devicePairGuideActivity.cs.setVisibility(0);
        devicePairGuideActivity.am.setVisibility(0);
        devicePairGuideActivity.cb.setVisibility(0);
        devicePairGuideActivity.c.start();
        devicePairGuideActivity.dl.setVisibility(8);
        devicePairGuideActivity.h.setVisibility(8);
        devicePairGuideActivity.f.setVisibility(8);
        devicePairGuideActivity.cx.setVisibility(8);
        devicePairGuideActivity.cv.setVisibility(8);
        devicePairGuideActivity.ca.setVisibility(0);
        devicePairGuideActivity.cw.setVisibility(0);
        devicePairGuideActivity.cw.setText(devicePairGuideActivity.getString(R.string.IDS_device_midware_authority_title));
        String string = devicePairGuideActivity.getString(R.string._2130841387_res_0x7f020f2b);
        devicePairGuideActivity.cu.setVisibility(8);
        devicePairGuideActivity.cu.setText(string);
        String string2 = BaseApplication.getContext().getResources().getString(R.string.IDS_device_midware_authority_text_frame);
        if (nsn.ae(BaseApplication.getContext())) {
            string2 = BaseApplication.getContext().getResources().getString(R.string._2130844228_res_0x7f021a44);
        }
        String format = String.format(Locale.ENGLISH, string2, BaseApplication.getContext().getResources().getString(R.string.IDS_device_midware_authority_networking), BaseApplication.getContext().getResources().getString(R.string.IDS_device_midware_authority_subtext));
        LogUtil.a("DevicePairGuideMidWareSetting", "processNote:", format);
        devicePairGuideActivity.cy.setVisibility(0);
        devicePairGuideActivity.cy.setGravity(GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK);
        devicePairGuideActivity.cy.setText(format);
    }

    public void c(DevicePairGuideActivity devicePairGuideActivity, boolean z) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.bm = z;
    }
}
