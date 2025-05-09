package defpackage;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.tencent.open.apireq.BaseResp;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingFormatArgumentException;

/* loaded from: classes4.dex */
public class goc {

    /* renamed from: a, reason: collision with root package name */
    private int f12880a;
    private d e;
    private HealthTextView f;
    private HealthTextView j;
    private static final Float c = Float.valueOf(38.0f);
    private static final Float d = Float.valueOf(32.0f);
    private static int[] b = {R.style.setting_goal_sport_target_text, R.style.settarget_unit_text_style, R.style.setting_goal_sport_target_bigger, R.style.settarget_unit_text_style_bigger};
    private MotionGoal h = new MotionGoal();
    private boolean g = false;
    private HealthOpenSDK i = null;
    private HealthSeekBar.OnSeekBarChangeListener l = new HealthSeekBar.OnSeekBarChangeListener() { // from class: goc.4
        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
        }

        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
        }

        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
            LogUtil.a("Step_GoalSettingDialogUtil", "onProgressChanged() value:", Integer.valueOf(i));
            int i2 = ((i / 1000) * 1000) + 2000;
            goc.this.b(i2);
            goc.this.h.setGoalType(1);
            goc.this.h.setStepGoal(i2);
        }
    };

    public void aQu_(Handler handler) {
        String b2 = ash.b("is_goal_setting_dialog_show");
        LogUtil.a("Step_GoalSettingDialogUtil", "goalFirstInit ", b2, "mGoalStep ", Integer.valueOf(this.f12880a));
        if ("".equals(b2)) {
            nip.d("900200006", new c(this, handler));
        }
    }

    public void aQv_(Context context, Handler handler) {
        LogUtil.a("Step_GoalSettingDialogUtil", "mGoalStep ", Integer.valueOf(this.f12880a));
        if (this.f12880a == -1) {
            handler.sendEmptyMessageDelayed(4022, 3000L);
        }
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<Handler> b;
        private WeakReference<goc> d;

        public c(goc gocVar, Handler handler) {
            this.d = new WeakReference<>(gocVar);
            this.b = new WeakReference<>(handler);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Step_GoalSettingDialogUtil", "GoalInfoCommonListener Enter");
            goc gocVar = this.d.get();
            Handler handler = this.b.get();
            if (!(obj instanceof Integer)) {
                LogUtil.h("Step_GoalSettingDialogUtil", "data not inatnceOf integer");
                return;
            }
            if (gocVar != null) {
                gocVar.f12880a = ((Integer) obj).intValue();
                if (handler == null) {
                    LogUtil.b("Step_GoalSettingDialogUtil", "GoalInfoCommonListener mHandler == null");
                    return;
                } else {
                    handler.sendEmptyMessage(4023);
                    return;
                }
            }
            LogUtil.b("Step_GoalSettingDialogUtil", "GoalInfoCommonListener mainActivity == null");
        }
    }

    public void c(final Context context) {
        ash.a("is_goal_setting_dialog_show", String.valueOf(false));
        this.h.setStepGoal(10000);
        this.i = dss.c(BaseApplication.getContext()).d();
        this.e = new d("getGoalNotifiState");
        View aQt_ = aQt_(context);
        b(this.h.getStepGoal());
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.czg_(aQt_).cze_(R.string._2130841772_res_0x7f0210ac, new View.OnClickListener() { // from class: goc.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Step_GoalSettingDialogUtil", "mGoalsInfo ", Integer.valueOf(goc.this.h.getStepGoal()));
                if (goc.this.e != null) {
                    goc.this.i.c(goc.this.e);
                }
                goc.this.d();
                LogUtil.a("Step_GoalSettingDialogUtil", "buildGoalStepDialog mIsOpenCompleteGoad ", Boolean.valueOf(goc.this.g));
                goc.this.c();
                goc.this.e(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130844746_res_0x7f021c4a, new View.OnClickListener() { // from class: goc.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap(2);
                hashMap.put("click", 1);
                hashMap.put("type", 1);
                ixx.d().d(context, AnalyticsValue.REMIND_DIALOG_SELECT_BUTTON_DO_NOT_SET.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        e.setCanceledOnTouchOutside(false);
        e.show();
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        ixx.d().d(context, AnalyticsValue.REMIND_DIALOG_SHOW_COUNT.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        nip.e("900200006", this.h.getStepGoal());
        LogUtil.a("Step_GoalSettingDialogUtil", "saveStepGoalValue step is ", Integer.valueOf(this.h.getStepGoal()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 20);
        calendar.set(12, 0);
        calendar.set(13, 0);
        long timeInMillis = calendar.getTimeInMillis();
        if (System.currentTimeMillis() > timeInMillis) {
            calendar.add(5, 1);
            timeInMillis = calendar.getTimeInMillis();
        }
        LogUtil.a("Step_GoalSettingDialogUtil", "setting time ", jec.e(timeInMillis));
        if (this.g) {
            riy.b(1000, timeInMillis, 86400000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        ixx.d().d(context, AnalyticsValue.REMIND_DIALOG_SELECT_BUTTON_DONE.value(), hashMap, 0);
        HashMap hashMap2 = new HashMap(2);
        hashMap2.put("click", 1);
        hashMap2.put(MedalConstants.EVENT_STEPS, Integer.valueOf(this.h.getStepGoal()));
        ixx.d().d(context, AnalyticsValue.REMIND_DIALOG_SELECT_STEP_COUNT.value(), hashMap2, 0);
    }

    private View aQt_(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_target_setting_view, (ViewGroup) null);
        this.j = (HealthTextView) inflate.findViewById(R.id.step_target_content);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.calorie_transfer_img);
        this.f = (HealthTextView) inflate.findViewById(R.id.target_calorie_exchange);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.goal_setting_title);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.goal_setting_tip);
        if (nsn.s()) {
            HealthTextView healthTextView3 = this.f;
            Float f = d;
            healthTextView3.setTextSize(1, f.floatValue());
            healthTextView.setTextSize(1, c.floatValue());
            healthTextView2.setTextSize(1, f.floatValue());
        }
        if (LanguageUtil.bc(context)) {
            BitmapDrawable cKn_ = nrz.cKn_(context, R.drawable._2131430690_res_0x7f0b0d22);
            if (cKn_ != null) {
                imageView.setImageDrawable(cKn_);
            }
        } else {
            imageView.setImageResource(R.drawable._2131430690_res_0x7f0b0d22);
        }
        HealthSeekBar healthSeekBar = (HealthSeekBar) inflate.findViewById(R.id.target_setting_dialog_progressbar);
        healthSeekBar.setTouchable(true);
        healthSeekBar.setOnSeekBarChangeListener(this.l);
        healthSeekBar.setMax(18000);
        int stepGoal = this.h.getStepGoal() + BaseResp.CODE_ERROR_PARAMS;
        LogUtil.a("Step_GoalSettingDialogUtil", "mStepGoalSeekbar.setProgress: ", Integer.valueOf(stepGoal), " mGoalsInfo.getStepGoal() ", Integer.valueOf(this.h.getStepGoal()));
        healthSeekBar.setProgress(stepGoal);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        float c2 = ((float) pvm.c(i)) / 1000.0f;
        String e = UnitUtil.e(Math.round(c2), 1, 0);
        int b2 = pvm.b(c2, 258, 1);
        LogUtil.a("Step_GoalSettingDialogUtil", "calorieValue=", e, " duration= ", Integer.valueOf(b2));
        double d2 = b2;
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903312_res_0x7f030110, Integer.parseInt(UnitUtil.e(d2, 1, 0)), UnitUtil.e(d2, 1, 0));
        HealthTextView healthTextView = this.f;
        if (healthTextView != null) {
            healthTextView.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844745_res_0x7f021c49), e, quantityString));
        } else {
            LogUtil.h("Step_GoalSettingDialogUtil", "mRunSportTimeString == null");
        }
        try {
            String quantityString2 = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903205_res_0x7f0300a5, this.h.getStepGoal(), String.valueOf(this.h.getStepGoal()));
            HealthTextView healthTextView2 = this.j;
            if (healthTextView2 != null) {
                healthTextView2.setText(quantityString2);
                b(this.j, R.plurals._2130903205_res_0x7f0300a5, i);
            } else {
                LogUtil.h("Step_GoalSettingDialogUtil", "mGoalValue == null");
            }
        } catch (MissingFormatArgumentException e2) {
            LogUtil.b("Step_GoalSettingDialogUtil", "refreshStepGoalView exception: ", LogAnonymous.b((Throwable) e2));
        }
    }

    private void b(HealthTextView healthTextView, int i, int i2) {
        LogUtil.a("Step_GoalSettingDialogUtil", "setDifferentTextSize() ");
        String e = UnitUtil.e(i2, 1, 0);
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(i, i2, e);
        SpannableString spannableString = new SpannableString(quantityString);
        if (nsn.s()) {
            nsi.cKH_(spannableString, quantityString, new TextAppearanceSpan(BaseApplication.getContext(), b[2]));
            nsi.cKH_(spannableString, e, new TextAppearanceSpan(BaseApplication.getContext(), b[3]));
        } else {
            nsi.cKH_(spannableString, quantityString, new TextAppearanceSpan(BaseApplication.getContext(), b[0]));
            nsi.cKH_(spannableString, e, new TextAppearanceSpan(BaseApplication.getContext(), b[1]));
        }
        healthTextView.setText(spannableString);
    }

    class d implements IExecuteResult {
        String c;

        d(String str) {
            this.c = str;
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("Step_GoalSettingDialogUtil", "onSuccess initSDK ", this.c);
            if (obj instanceof Bundle) {
                Bundle bundle = (Bundle) obj;
                String str = this.c;
                if (str == null || !str.equals("getGoalNotifiState")) {
                    return;
                }
                goc.this.g = bundle.getBoolean("goalNotifiState");
                LogUtil.a("Step_GoalSettingDialogUtil", "onSuccess isOpenCompleteGoad :", Boolean.valueOf(goc.this.g));
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.h("Step_GoalSettingDialogUtil", "OpenSdkCommonCallback onFailed ", this.c);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.h("Step_GoalSettingDialogUtil", "OpenSdkCommonCallback onServiceException ", this.c);
        }
    }
}
