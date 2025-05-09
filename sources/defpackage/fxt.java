package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class fxt {
    private Context b;
    private IntPlan d;

    public fxt(Context context, IntPlan intPlan) {
        this.b = context;
        this.d = intPlan;
    }

    public void d() {
        String e = SharedPreferenceManager.e("MMKV_SUGGEST_MODULE_TAG", "isSendCourseDevice", "");
        ReleaseLogUtil.e("Suggestion_IntPlanWatchInteract", "pushPlanFirstCreated:", e);
        if (TextUtils.isEmpty(e) || e.split(" ").length < 2) {
            if (gij.d()) {
                fyc.b(this.d, this.b);
                return;
            }
            return;
        }
        String[] split = e.split(" ");
        String str = split[0];
        String str2 = split[1];
        if (!(this.d.getPlanId().equals(str) && Boolean.parseBoolean(str2)) && gij.d()) {
            fyc.b(this.d, this.b);
        }
    }

    public void b() {
        if (!ggx.a()) {
            LogUtil.h("Suggestion_IntPlanWatchInteract", "initPushToWatchView bluetooth is disable");
            a();
            return;
        }
        if (!fyc.d()) {
            fpq.d(this.b, this.b.getString(R.string.IDS_plan_device_connect), this.b.getString(R.string.IDS_plan_view_supported_devices));
        } else {
            if (!gij.d()) {
                Context context = this.b;
                fpq.c(context, context.getString(R.string._2130844897_res_0x7f021ce1), this.b.getString(R.string.IDS_plan_view_supported_devices));
                return;
            }
            boolean x = fyw.x(this.d);
            ReleaseLogUtil.e("Suggestion_IntPlanWatchInteract", "handlePushToWatch isPlanOverdue ", Boolean.valueOf(x));
            if (x) {
                nrh.b(this.b, R.string._2130844898_res_0x7f021ce2);
            } else {
                fyc.b(this.d, this.b);
            }
        }
    }

    public void a() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.b).e(this.b.getResources().getString(R$string.IDS_device_bluetooth_open_request)).czC_(com.huawei.ui.commonui.R$string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: fxt.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_IntPlanWatchInteract", "user choose open BT");
                try {
                    BluetoothAdapter.getDefaultAdapter().enable();
                } catch (RuntimeException e2) {
                    ReleaseLogUtil.c("Suggestion_IntPlanWatchInteract", "user choose open BT error :", LogAnonymous.b((Throwable) e2));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(com.huawei.ui.commonui.R$string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: fxv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }
}
