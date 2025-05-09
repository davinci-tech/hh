package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class nlg {
    private static boolean a(long j, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("UIHLH_CommonAutoTestToast", "currentTime = ", Long.valueOf(currentTimeMillis));
        long j2 = currentTimeMillis - j;
        LogUtil.a("UIHLH_CommonAutoTestToast", "period = ", Long.valueOf(j2));
        LogUtil.a("UIHLH_CommonAutoTestToast", "count = ", Integer.valueOf(i));
        if (i > 4 || i < 0) {
            return false;
        }
        if (i == 0) {
            return true;
        }
        int pow = (int) Math.pow(2.0d, i - 1);
        LogUtil.a("UIHLH_CommonAutoTestToast", "time = ", Integer.valueOf(pow));
        long j3 = pow * 604800000;
        LogUtil.a("UIHLH_CommonAutoTestToast", "duration = ", Long.valueOf(j3));
        return j2 >= j3;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void cxS_(final android.content.Context r11, final com.huawei.hwbasemgr.IBaseResponseCallback r12, final android.widget.RelativeLayout r13, java.lang.String r14, java.lang.String[] r15) {
        /*
            java.lang.String r0 = "UIHLH_CommonAutoTestToast"
            if (r15 == 0) goto Le6
            int r1 = r15.length
            r2 = 4
            if (r1 == r2) goto La
            goto Le6
        La:
            r1 = 0
            r6 = r15[r1]
            r2 = 1
            r9 = r15[r2]
            r2 = 2
            r10 = r15[r2]
            r2 = 3
            r15 = r15[r2]
            r2 = 10006(0x2716, float:1.4021E-41)
            java.lang.String r3 = java.lang.Integer.toString(r2)
            java.lang.String r3 = health.compact.a.SharedPreferenceManager.b(r11, r3, r15)
            java.lang.String r4 = "isShow = "
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)
            cxR_(r14, r11, r13)
            java.lang.String r14 = java.lang.Integer.toString(r2)
            java.lang.String r14 = health.compact.a.SharedPreferenceManager.b(r11, r14, r10)
            boolean r4 = defpackage.nsn.a(r14)
            if (r4 == 0) goto L48
            int r14 = java.lang.Integer.parseInt(r14)     // Catch: java.lang.NumberFormatException -> L3f
            goto L49
        L3f:
            java.lang.String r14 = "showBeginDeviceAutoDetect integerCount NumberFormatException"
            java.lang.Object[] r14 = new java.lang.Object[]{r14}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r14)
        L48:
            r14 = r1
        L49:
            java.lang.String r4 = "true"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto Lb1
            java.lang.String r3 = java.lang.Integer.toString(r2)
            java.lang.String r3 = health.compact.a.SharedPreferenceManager.b(r11, r3, r6)
            java.lang.String r5 = " showBeginDeviceStressAutoDetect notReMind = "
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L69
            return
        L69:
            java.lang.String r3 = java.lang.Integer.toString(r2)
            java.lang.String r3 = health.compact.a.SharedPreferenceManager.b(r11, r3, r9)
            boolean r5 = defpackage.nsn.a(r3)
            if (r5 == 0) goto L85
            long r7 = java.lang.Long.parseLong(r3)     // Catch: java.lang.NumberFormatException -> L7c
            goto L87
        L7c:
            java.lang.String r3 = "showBeginDeviceAutoDetect lastShowDialogTimeLong NumberFormatException"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)
        L85:
            r7 = 0
        L87:
            java.lang.String r3 = "lastShowDialogTimeLong = "
            java.lang.Long r5 = java.lang.Long.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r5}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            boolean r3 = a(r7, r14)
            java.lang.String r5 = "isDialogPeriod= "
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r3)
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
            if (r3 != 0) goto Lb1
            java.lang.String r11 = "time interval is not invalid"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)
            return
        Lb1:
            java.lang.String r0 = java.lang.Integer.toString(r2)
            health.compact.a.StorageParams r2 = new health.compact.a.StorageParams
            r2.<init>()
            health.compact.a.SharedPreferenceManager.e(r11, r0, r15, r4, r2)
            r0 = -1
            r2 = 0
            r12.d(r0, r2)
            boolean r0 = com.huawei.haf.handler.HandlerExecutor.b()
            if (r0 != 0) goto Ld7
            nlg$3 r0 = new nlg$3
            r2 = r0
            r3 = r13
            r4 = r14
            r5 = r11
            r7 = r12
            r8 = r15
            r2.<init>()
            com.huawei.haf.handler.HandlerExecutor.a(r0)
            return
        Ld7:
            r13.setVisibility(r1)
            r2 = r14
            r3 = r13
            r4 = r11
            r5 = r6
            r6 = r12
            r7 = r15
            r8 = r9
            r9 = r10
            cxQ_(r2, r3, r4, r5, r6, r7, r8, r9)
            return
        Le6:
            java.lang.String r11 = "sharedPreferenceKeys is invalid"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nlg.cxS_(android.content.Context, com.huawei.hwbasemgr.IBaseResponseCallback, android.widget.RelativeLayout, java.lang.String, java.lang.String[]):void");
    }

    public static void e(Context context, String str, String str2, int i) {
        LogUtil.a("UIHLH_CommonAutoTestToast", "integerCount = ", Integer.valueOf(i));
        String valueOf = String.valueOf(System.currentTimeMillis());
        LogUtil.a("UIHLH_CommonAutoTestToast", "currentTimeString = ", valueOf);
        SharedPreferenceManager.e(context, Integer.toString(10006), str, valueOf, new StorageParams());
        int i2 = i + 1;
        LogUtil.a("UIHLH_CommonAutoTestToast", "setToastCilckInfo already show count = ", Integer.valueOf(i2));
        SharedPreferenceManager.e(context, Integer.toString(10006), str2, Integer.toString(i2), new StorageParams());
    }

    public static void c(Context context, String str, String str2) {
        String b = SharedPreferenceManager.b(context, Integer.toString(10006), str);
        LogUtil.a("UIHLH_CommonAutoTestToast", " autoTestCloseSwitchRule notReMind = ", b);
        if ("true".equals(b)) {
            return;
        }
        String b2 = SharedPreferenceManager.b(context, Integer.toString(10006), str2);
        if (nsn.a(b2)) {
            try {
                if (Integer.parseInt(b2) >= 5) {
                    return;
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("UIHLH_CommonAutoTestToast", "autoTestCloseSwitchRule NumberFormatException");
            }
        }
        LogUtil.a("UIHLH_CommonAutoTestToast", " autoTestCloseSwitchRule set notReMind");
        SharedPreferenceManager.e(context, Integer.toString(10006), str, "true", new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, String str2, Context context) {
        HashMap hashMap = new HashMap();
        if (str.equals("pressure_auto_detector_agree_no_again_tip")) {
            hashMap.put("moduleType", "1");
        } else if (!str.equals("core_sleep_btn_tips_do_not_remind_again")) {
            return;
        } else {
            hashMap.put("moduleType", "0");
        }
        hashMap.put("click", "1");
        hashMap.put("buttonType", str2);
        ixx.d().d(context, AnalyticsValue.HEALTH_PRESSURE_OR_SLEEP_AUTO_DETECT_TOAST_BUTTON_CLICK_2160027.value(), hashMap, 0);
    }

    public static boolean cxO_(Context context, RelativeLayout relativeLayout, String str) {
        if (!"true".equals(SharedPreferenceManager.b(context, Integer.toString(10006), str))) {
            return true;
        }
        relativeLayout.setVisibility(8);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int cxN_(android.content.Context r2, android.widget.RelativeLayout r3, java.lang.String r4, int r5, com.huawei.hwbasemgr.IBaseResponseCallback r6) {
        /*
            r0 = 2131571628(0x7f0d33ac, float:1.8768944E38)
            android.view.View r0 = defpackage.nsy.cMd_(r3, r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r1 = 10006(0x2716, float:1.4021E-41)
            java.lang.String r1 = java.lang.Integer.toString(r1)
            java.lang.String r2 = health.compact.a.SharedPreferenceManager.b(r2, r1, r4)
            boolean r4 = defpackage.nsn.a(r2)
            r1 = 0
            if (r4 == 0) goto L2a
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L1f
            goto L2b
        L1f:
            java.lang.String r2 = "showToastLayoutByCount NumberFormatException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r4 = "UIHLH_CommonAutoTestToast"
            com.huawei.hwlogsmodel.LogUtil.b(r4, r2)
        L2a:
            r2 = r1
        L2b:
            if (r2 >= r5) goto L33
            r4 = 8
            r0.setVisibility(r4)
            goto L36
        L33:
            r0.setVisibility(r1)
        L36:
            r3.setVisibility(r1)
            r3 = -1
            r4 = 0
            r6.d(r3, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nlg.cxN_(android.content.Context, android.widget.RelativeLayout, java.lang.String, int, com.huawei.hwbasemgr.IBaseResponseCallback):int");
    }

    public static void cya_(Context context, String str, IBaseResponseCallback iBaseResponseCallback, RelativeLayout relativeLayout, String str2, String str3) {
        if (str3 == null) {
            LogUtil.h("UIHLH_CommonAutoTestToast", "content is null");
            return;
        }
        if (relativeLayout == null) {
            LogUtil.h("UIHLH_CommonAutoTestToast", "layout is null");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("UIHLH_CommonAutoTestToast", "title is isEmpty");
            return;
        }
        if (TextUtils.isEmpty(str3)) {
            LogUtil.h("UIHLH_CommonAutoTestToast", "content is isEmpty");
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.h("UIHLH_CommonAutoTestToast", "callback is null");
            return;
        }
        if (cxX_(context, "wear_activity_tip_no_again" + str, relativeLayout, str2, str3)) {
            iBaseResponseCallback.d(160001, "current dialog is notRemind.");
        } else {
            cxY_(context, str, iBaseResponseCallback, relativeLayout);
        }
    }

    private static void cxY_(final Context context, String str, final IBaseResponseCallback iBaseResponseCallback, final RelativeLayout relativeLayout) {
        final String str2 = "wear_activity_tip_no_again" + str;
        final String str3 = "wear_activity_tip_last_time" + str;
        final String str4 = "wear_activity_tip_open_count " + str;
        final String str5 = "wear_activity_tip_is_shown" + str;
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_no_notice_layout);
        final int cxZ_ = cxZ_(context, str4, relativeLayout, linearLayout, str5);
        iBaseResponseCallback.d(-1, null);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: nlg.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nlg.cxL_(relativeLayout, iBaseResponseCallback, context, str2, str5);
                nlg.e(context, str3, str4, cxZ_);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_cancel_layout);
        linearLayout2.setVisibility(0);
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: nlg.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "cancelLayout");
                relativeLayout.setVisibility(8);
                iBaseResponseCallback.d(100000, null);
                SharedPreferenceManager.e(context, Integer.toString(10006), str5, "false", new StorageParams());
                nlg.e(context, str3, str4, cxZ_);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ((LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_try_layout)).setOnClickListener(new View.OnClickListener() { // from class: nlg.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "startLayout");
                IBaseResponseCallback.this.d(0, null);
                relativeLayout.setVisibility(8);
                SharedPreferenceManager.e(context, Integer.toString(10006), str5, "false", new StorageParams());
                nlg.e(context, str3, str4, cxZ_);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int cxZ_(android.content.Context r3, java.lang.String r4, android.widget.RelativeLayout r5, android.widget.LinearLayout r6, java.lang.String r7) {
        /*
            r0 = 10006(0x2716, float:1.4021E-41)
            java.lang.String r1 = java.lang.Integer.toString(r0)
            java.lang.String r4 = health.compact.a.SharedPreferenceManager.b(r3, r1, r4)
            boolean r1 = defpackage.nsn.a(r4)
            r2 = 0
            if (r1 == 0) goto L21
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L16
            goto L22
        L16:
            java.lang.String r4 = "showToastInfos NumberFormatException"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r1 = "UIHLH_CommonAutoTestToast"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r4)
        L21:
            r4 = r2
        L22:
            r1 = 1
            if (r4 >= r1) goto L2b
            r1 = 8
            r6.setVisibility(r1)
            goto L2e
        L2b:
            r6.setVisibility(r2)
        L2e:
            r5.setVisibility(r2)
            java.lang.String r5 = java.lang.Integer.toString(r0)
            health.compact.a.StorageParams r6 = new health.compact.a.StorageParams
            r6.<init>()
            java.lang.String r0 = "true"
            health.compact.a.SharedPreferenceManager.e(r3, r5, r7, r0, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nlg.cxZ_(android.content.Context, java.lang.String, android.widget.RelativeLayout, android.widget.LinearLayout, java.lang.String):int");
    }

    private static boolean cxX_(Context context, String str, RelativeLayout relativeLayout, String str2, String str3) {
        if ("true".equals(SharedPreferenceManager.b(context, Integer.toString(10006), str))) {
            relativeLayout.setVisibility(8);
            return true;
        }
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv_bold);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv);
        if (!TextUtils.isEmpty(str2)) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str2);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView2.getLayoutParams();
        layoutParams.topMargin = (int) context.getResources().getDimension(R.dimen._2131363844_res_0x7f0a0804);
        healthTextView2.setLayoutParams(layoutParams);
        healthTextView2.setText(str3);
        return false;
    }

    public static void cxP_(final Context context, final IBaseResponseCallback iBaseResponseCallback, final RelativeLayout relativeLayout, final String str, final String str2, final String str3, final int i) {
        ((LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_cancel_layout)).setOnClickListener(new View.OnClickListener() { // from class: nlg.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "cancelLayout");
                IBaseResponseCallback.this.d(100000, null);
                relativeLayout.setVisibility(8);
                SharedPreferenceManager.e(context, Integer.toString(10006), str3, "false", new StorageParams());
                nlg.e(context, str, str2, i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ((LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_try_layout)).setOnClickListener(new View.OnClickListener() { // from class: nlg.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "startLayout");
                IBaseResponseCallback.this.d(0, null);
                relativeLayout.setVisibility(8);
                SharedPreferenceManager.e(context, Integer.toString(10006), str3, "false", new StorageParams());
                nlg.e(context, str, str2, i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static void cxT_(Context context, final IBaseResponseCallback iBaseResponseCallback, final RelativeLayout relativeLayout, String str, String str2) {
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv_bold);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv);
        if (healthTextView == null) {
            nsz.cLX_("showCommonTipLayout textContent == null", relativeLayout, R.id.toast_title_tv_bold);
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView2.getLayoutParams();
        layoutParams.topMargin = (int) context.getResources().getDimension(R.dimen._2131363844_res_0x7f0a0804);
        healthTextView2.setLayoutParams(layoutParams);
        healthTextView2.setText(str2);
        relativeLayout.setVisibility(0);
        ((LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_cancel_layout)).setVisibility(8);
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_no_notice_layout);
        linearLayout.setVisibility(0);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: nlg.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "showBindWechatLayout click noticeLayout");
                relativeLayout.setVisibility(8);
                iBaseResponseCallback.d(100000, null);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_try_layout);
        linearLayout2.setVisibility(0);
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: nlg.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "showBindWechatLayout click startLayout");
                IBaseResponseCallback.this.d(0, null);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static void cxW_(final Context context, final IBaseResponseCallback iBaseResponseCallback, final RelativeLayout relativeLayout, String str, String str2) {
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv_bold);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv);
        if (!TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView2.getLayoutParams();
        layoutParams.topMargin = (int) context.getResources().getDimension(R.dimen._2131363844_res_0x7f0a0804);
        healthTextView2.setLayoutParams(layoutParams);
        healthTextView2.setText(str2);
        relativeLayout.setVisibility(0);
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_cancel_layout);
        linearLayout.setVisibility(0);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: nlg.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nlg.a(context, 3);
                LogUtil.a("UIHLH_CommonAutoTestToast", "showEcgServiceActiveLayout click cancel");
                relativeLayout.setVisibility(8);
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(100000, "onClick cancelLayout");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ((LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_no_notice_layout)).setVisibility(8);
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_try_layout);
        linearLayout2.setVisibility(0);
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: nlg.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nlg.a(context, 2);
                LogUtil.a("UIHLH_CommonAutoTestToast", "showEcgServiceActiveLayout click start");
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, "onClick startLayout");
                }
                relativeLayout.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        LogUtil.a("UIHLH_CommonAutoTestToast", "end showEcgServiceActiveLayout");
        a(context, 1);
    }

    public static void cxV_(IBaseResponseCallback iBaseResponseCallback, RelativeLayout relativeLayout, String str, String str2, String str3) {
        LogUtil.a("UIHLH_CommonAutoTestToast", "showCommonTipLayout. ");
        if (str3 != null) {
            if ("true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), "banner_do_not_ask_again_" + str3))) {
                relativeLayout.setVisibility(8);
                return;
            }
        }
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv_bold);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv);
        if (healthTextView2 == null) {
            nsz.cLX_("showCommonTipLayout textContent == null", relativeLayout, R.id.toast_title_tv);
            return;
        }
        if (healthTextView != null && !TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
        if (healthTextView2.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView2.getLayoutParams();
            layoutParams.topMargin = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363844_res_0x7f0a0804);
            healthTextView2.setLayoutParams(layoutParams);
            healthTextView2.setText(str2);
            cxM_(iBaseResponseCallback, relativeLayout, str3);
            relativeLayout.setVisibility(0);
        }
    }

    public static void cxU_(IBaseResponseCallback iBaseResponseCallback, RelativeLayout relativeLayout, String str, String str2, String str3, Object... objArr) {
        LogUtil.a("UIHLH_CommonAutoTestToast", "showCommonTipLayout. ");
        if (str3 != null) {
            if ("true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), "banner_do_not_ask_again_" + str3))) {
                relativeLayout.setVisibility(8);
                return;
            }
        }
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv_bold);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv);
        if (healthTextView2 == null) {
            nsz.cLX_("showCommonTipLayout textContent == null", relativeLayout, R.id.toast_title_tv);
            return;
        }
        if (healthTextView != null && !TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
        if (healthTextView2.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView2.getLayoutParams();
            String format = String.format(str2, objArr);
            SpannableString spannableString = new SpannableString(format);
            for (Object obj : objArr) {
                StyleSpan styleSpan = new StyleSpan(1);
                String obj2 = obj.toString();
                int indexOf = format.indexOf(obj2);
                spannableString.setSpan(styleSpan, indexOf, obj2.length() + indexOf, 33);
            }
            layoutParams.topMargin = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363844_res_0x7f0a0804);
            healthTextView2.setLayoutParams(layoutParams);
            healthTextView2.setText(spannableString);
            cxM_(iBaseResponseCallback, relativeLayout, str3);
            relativeLayout.setVisibility(0);
        }
    }

    private static void cxM_(final IBaseResponseCallback iBaseResponseCallback, final RelativeLayout relativeLayout, final String str) {
        LogUtil.a("UIHLH_CommonAutoTestToast", "commonTipSetClickEvent. ");
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_cancel_layout);
        linearLayout.setVisibility(0);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: nlg.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "showCommonTipLayout click cancel");
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(100000, "onClick cancelLayout");
                }
                relativeLayout.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_no_notice_layout);
        if (str != null) {
            linearLayout2.setVisibility(0);
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: nlg.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("UIHLH_CommonAutoTestToast", "showCommonTipLayout click cancel");
                    IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(100000, "onClick cancelLayout");
                    }
                    relativeLayout.setVisibility(8);
                    SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10006), "banner_do_not_ask_again_" + str, "true", new StorageParams());
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            linearLayout2.setVisibility(8);
        }
        LinearLayout linearLayout3 = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_try_layout);
        linearLayout3.setVisibility(0);
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: nlg.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CommonAutoTestToast", "showCommonTipLayout click start");
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, "onClick startLayout");
                }
                relativeLayout.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Context context, int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("dialogType", 2);
        String value = AnalyticsValue.ECG_DIALOG_SHOW_COUNT.value();
        ixx.d().d(context, value, hashMap, 0);
        LogUtil.a("UIHLH_CommonAutoTestToast", "BI ecg banner, value: ", value, ", typeMap: ", hashMap.toString());
    }

    public static void cxL_(RelativeLayout relativeLayout, IBaseResponseCallback iBaseResponseCallback, Context context, String str, String str2) {
        LogUtil.a("UIHLH_CommonAutoTestToast", "noticeLayout");
        relativeLayout.setVisibility(8);
        iBaseResponseCallback.d(100000, null);
        SharedPreferenceManager.e(context, Integer.toString(10006), str, "true", new StorageParams());
        SharedPreferenceManager.e(context, Integer.toString(10006), str2, "false", new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cxQ_(final int i, final RelativeLayout relativeLayout, final Context context, final String str, final IBaseResponseCallback iBaseResponseCallback, final String str2, final String str3, final String str4) {
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_no_notice_layout);
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_try_layout);
        LinearLayout linearLayout3 = (LinearLayout) nsy.cMd_(relativeLayout, R.id.toast_cancel_layout);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: nlg.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("UIHLH_CommonAutoTestToast", "click no again notice , ", str);
                    nlg.e(str, "1", context);
                    relativeLayout.setVisibility(8);
                    iBaseResponseCallback.d(100000, null);
                    SharedPreferenceManager.e(context, Integer.toString(10006), str, "true", new StorageParams());
                    SharedPreferenceManager.e(context, Integer.toString(10006), str2, "false", new StorageParams());
                    nlg.e(context, str3, str4, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (linearLayout3 != null) {
            linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: nlg.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("UIHLH_CommonAutoTestToast", "click cancel , ", str);
                    nlg.e(str, "0", context);
                    iBaseResponseCallback.d(100000, null);
                    relativeLayout.setVisibility(8);
                    SharedPreferenceManager.e(context, Integer.toString(10006), str2, "false", new StorageParams());
                    nlg.e(context, str3, str4, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: nlg.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("UIHLH_CommonAutoTestToast", "click open , ", str);
                    nlg.e(str, "2", context);
                    iBaseResponseCallback.d(0, null);
                    relativeLayout.setVisibility(8);
                    SharedPreferenceManager.e(context, Integer.toString(10006), str2, "false", new StorageParams());
                    nlg.e(context, str3, str4, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cxR_(final String str, final Context context, final RelativeLayout relativeLayout) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: nlg.8
                @Override // java.lang.Runnable
                public void run() {
                    nlg.cxR_(str, context, relativeLayout);
                }
            });
        } else {
            nsy.cMr_((HealthTextView) nsy.cMd_(relativeLayout, R.id.toast_title_tv), str);
        }
    }
}
