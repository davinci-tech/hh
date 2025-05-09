package com.huawei.ui.commonui.dialog;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.webview.WebViewActivity;
import com.huawei.ui.main.stories.nps.component.NpsConstantValue;
import defpackage.ixx;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes6.dex */
public class SecurityManagerSettingSwitchDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private static long f8821a = 0;
    private static long c = 0;
    private static final String e = "SecurityManagerSettingSwitchDialog";

    public SecurityManagerSettingSwitchDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        private static SecurityManagerSettingSwitchDialog b;
        private HealthButton c;
        private HealthButton d;
        private Context e;
        private HealthTextView f;
        private String h;
        private HealthTextView j;
        private RelativeLayout g = null;

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f8822a = null;

        public Builder(Context context) {
            this.e = context;
        }

        public void d(String str) {
            this.h = "<a href=\"" + str + "/help/mobilephone/zh-CN/index.html\">$</a>";
        }

        public SecurityManagerSettingSwitchDialog d() {
            long unused = SecurityManagerSettingSwitchDialog.c = System.currentTimeMillis();
            c(this.e);
            View inflate = ((LayoutInflater) this.e.getSystemService("layout_inflater")).inflate(R.layout.custom_securitymanagersetting_alert_dialog, (ViewGroup) null);
            View findViewById = inflate.findViewById(R.id.custom_text_alert_dailog_title);
            if (findViewById instanceof HealthTextView) {
                this.j = (HealthTextView) findViewById;
            }
            View findViewById2 = inflate.findViewById(R.id.dialog_text_alert_message);
            if (findViewById2 instanceof HealthTextView) {
                this.f = (HealthTextView) findViewById2;
            }
            czR_(inflate);
            j();
            TypedValue typedValue = new TypedValue();
            this.e.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            TypedArray obtainStyledAttributes = this.e.getTheme().obtainStyledAttributes(typedValue.resourceId, R$styleable.customDialogDefinition);
            TypedValue typedValue2 = new TypedValue();
            TypedValue typedValue3 = new TypedValue();
            obtainStyledAttributes.getValue(R$styleable.customDialogDefinition_titleTextSize, typedValue2);
            obtainStyledAttributes.getValue(R$styleable.customDialogDefinition_contentTextSize, typedValue3);
            this.j.setTextSize(1, (int) TypedValue.complexToFloat(typedValue2.data));
            this.j.setText(R$string.IDS_service_area_notice_title);
            int complexToFloat = (int) TypedValue.complexToFloat(typedValue3.data);
            if (nsn.r()) {
                this.f.setTextSize(1, 28.0f);
            } else {
                this.f.setTextSize(1, complexToFloat);
            }
            o();
            n();
            Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.customDialogDefinition_dialogBackground);
            obtainStyledAttributes.recycle();
            inflate.setBackground(drawable);
            b.setContentView(inflate);
            b.setCancelable(false);
            f();
            return b;
        }

        private void f() {
            Window window = b.getWindow();
            window.setGravity(80);
            Display defaultDisplay = ((WindowManager) this.e.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
            WindowManager.LayoutParams attributes = window.getAttributes();
            int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0);
            int dimensionPixelSize2 = this.e.getResources().getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2);
            int dimensionPixelSize3 = this.e.getResources().getDimensionPixelSize(R.dimen._2131362465_res_0x7f0a02a1);
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            attributes.width = (((defaultDisplay.getWidth() - dimensionPixelSize2) - dimensionPixelSize3) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
            attributes.y = dimensionPixelSize;
            window.setAttributes(attributes);
            window.setWindowAnimations(R.style.track_dialog_anim);
        }

        private void czR_(View view) {
            View findViewById = view.findViewById(R.id.dialog_linearlayout1);
            if (findViewById instanceof RelativeLayout) {
                this.g = (RelativeLayout) findViewById;
            }
            View findViewById2 = view.findViewById(R.id.dialog_linearlayout2);
            if (findViewById2 instanceof LinearLayout) {
                this.f8822a = (LinearLayout) findViewById2;
            }
            this.f8822a.setVisibility(8);
        }

        private void j() {
            this.h = this.h.replace(SampleEvent.SEPARATOR, this.e.getResources().getString(R$string.IDS_unusual_stopped_message_more_new_health));
            Locale locale = Locale.getDefault();
            String language = locale.getLanguage();
            String country = locale.getCountry();
            LogUtil.a(SecurityManagerSettingSwitchDialog.e, "the language code is ", language);
            LogUtil.a(SecurityManagerSettingSwitchDialog.e, "the country code is ", country);
            if (MLAsrConstants.LAN_ZH.equals(language) && "CN".equals(country)) {
                this.h = this.h.replace("zh-CN", "en-US");
            }
            String str = this.e.getResources().getString(R$string.IDS_unusual_stopped_message_new_health) + " " + this.h;
            if (LanguageUtil.q(this.e)) {
                str = str + ".";
            }
            LogUtil.a(SecurityManagerSettingSwitchDialog.e, "the mUrl is ", str);
            this.f.setText(czS_(this.e, str, b));
            this.f.setMovementMethod(LinkMovementMethod.getInstance());
        }

        private void o() {
            View findViewById = this.g.findViewById(R.id.dialog_text_alert_btn_positive);
            if (findViewById instanceof HealthButton) {
                this.d = (HealthButton) findViewById;
            }
            HealthButton healthButton = this.d;
            if (healthButton != null) {
                healthButton.setText(R$string.IDS_main_btn_state_settings);
                this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.SecurityManagerSettingSwitchDialog.Builder.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Builder.b.dismiss();
                        long unused = SecurityManagerSettingSwitchDialog.f8821a = System.currentTimeMillis();
                        SecurityManagerSettingSwitchDialog.c(Builder.this.e, 2);
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME", NpsConstantValue.QUERY_SYSTEM_BUSY, (StorageParams) null);
                        LogUtil.a(SecurityManagerSettingSwitchDialog.e, "setPositiveButton Dialog status: ", SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME"));
                        try {
                            Builder.this.i();
                        } catch (SecurityException unused2) {
                            LogUtil.a(SecurityManagerSettingSwitchDialog.e, "SecurityException when opening security manager");
                            Builder.this.h();
                        } catch (Exception e) {
                            LogUtil.b(SecurityManagerSettingSwitchDialog.e, "Unknown exception occurred when opening security manager ", ExceptionUtils.d(e));
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }

        private void n() {
            View findViewById = this.g.findViewById(R.id.dialog_text_alert_btn_negative);
            if (findViewById instanceof HealthButton) {
                this.c = (HealthButton) findViewById;
            }
            HealthButton healthButton = this.c;
            if (healthButton != null) {
                healthButton.setText(R$string.IDS_settings_button_cancal);
                this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.SecurityManagerSettingSwitchDialog.Builder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Builder.b.dismiss();
                        long unused = SecurityManagerSettingSwitchDialog.f8821a = System.currentTimeMillis();
                        SecurityManagerSettingSwitchDialog.c(Builder.this.e, 1);
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME", NpsConstantValue.QUERY_SYSTEM_BUSY, (StorageParams) null);
                        LogUtil.a(SecurityManagerSettingSwitchDialog.e, "setNegativeButton Dialog status: ", SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME"));
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }

        private static void c(Context context) {
            b = new SecurityManagerSettingSwitchDialog(context, R.style.CustomDialog);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i() {
            try {
                ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                this.e.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.a(SecurityManagerSettingSwitchDialog.e, "not XIAOMI, start meizu process");
                c();
            }
        }

        private void c() {
            try {
                Context context = this.e;
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.meizu.safe"));
            } catch (ActivityNotFoundException unused) {
                LogUtil.a(SecurityManagerSettingSwitchDialog.e, "not MEIZU, start oppo process");
                b();
            } catch (Exception unused2) {
                b();
            }
        }

        private void b() {
            try {
                ComponentName componentName = new ComponentName("com.coloros.safecenter", "com.coloros.privacypermissionsentry.PermissionTopActivity");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                this.e.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.a(SecurityManagerSettingSwitchDialog.e, "not oppo, start system setting process");
                g();
            }
        }

        private void g() {
            try {
                Context context = this.e;
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure"));
            } catch (ActivityNotFoundException unused) {
                LogUtil.a(SecurityManagerSettingSwitchDialog.e, "not vivo, start system setting process");
                a();
            } catch (Exception unused2) {
                a();
            }
        }

        private void a() {
            try {
                ComponentName componentName = new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.BackgroundAppManageActivity");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                this.e.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                h();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h() {
            try {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setClassName(this.e, "com.letv.android.letvsafe.BackgroundAppManageActivity");
                intent.setData(Uri.parse("package:com.huawei.bone"));
                this.e.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b(SecurityManagerSettingSwitchDialog.e, "can't open system setting page. ingnored!!");
            }
        }

        public static SpannableStringBuilder czS_(Context context, String str, Dialog dialog) {
            if (!TextUtils.isEmpty(str)) {
                Spanned fromHtml = Html.fromHtml(str);
                if (fromHtml instanceof SpannableStringBuilder) {
                    SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) fromHtml;
                    Object[] spans = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class);
                    if (spans != null && spans.length != 0) {
                        czQ_(context, dialog, spannableStringBuilder, spans);
                    }
                    return spannableStringBuilder;
                }
            }
            return new SpannableStringBuilder(str);
        }

        private static void czQ_(final Context context, final Dialog dialog, SpannableStringBuilder spannableStringBuilder, Object[] objArr) {
            for (Object obj : objArr) {
                int spanStart = spannableStringBuilder.getSpanStart(obj);
                int spanEnd = spannableStringBuilder.getSpanEnd(obj);
                if (obj instanceof URLSpan) {
                    final String url = ((URLSpan) obj).getURL();
                    spannableStringBuilder.removeSpan(obj);
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.huawei.ui.commonui.dialog.SecurityManagerSettingSwitchDialog.Builder.4
                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            dialog.dismiss();
                            long unused = SecurityManagerSettingSwitchDialog.f8821a = System.currentTimeMillis();
                            SecurityManagerSettingSwitchDialog.c(context, 0);
                            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME", NpsConstantValue.QUERY_SYSTEM_BUSY, (StorageParams) null);
                            LogUtil.a(SecurityManagerSettingSwitchDialog.e, "setTextLinkOpenByWebView Dialog status: ", SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME"));
                            SecurityManagerSettingSwitchDialog.c(context, url, 0);
                            ViewClickInstrumentation.clickOnView(view);
                        }

                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor("#fb6522"));
                            textPaint.setUnderlineText(false);
                        }
                    }, spanStart, spanEnd, 17);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str, int i) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
        intent.putExtra(com.huawei.operation.utils.Constants.JUMP_MODE_KEY, i);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(e, "ActivityNotFoundException", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, int i) {
        long j = f8821a;
        long j2 = c;
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("duration", Long.valueOf(j - j2));
        ixx.d().d(context, AnalyticsValue.HEALTH_HOME_SHOW__ADD_PROTECTED_APPLICATION_2010077.value(), hashMap, 0);
    }
}
