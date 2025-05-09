package com.huawei.ui.commonui.dialog;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.Locale;

/* loaded from: classes6.dex */
public class HuaweiMobileServiceSetDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8812a = "HuaweiMobileServiceSetDialog";

    public HuaweiMobileServiceSetDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Context f8813a;

        public Builder(Context context) {
            this.f8813a = context;
        }

        public HuaweiMobileServiceSetDialog a() {
            LogUtil.a(HuaweiMobileServiceSetDialog.f8812a, "enter HuaweiMobileServiceSetDialog create.");
            HuaweiMobileServiceSetDialog huaweiMobileServiceSetDialog = new HuaweiMobileServiceSetDialog(this.f8813a, R.style.CustomDialog);
            View inflate = ((LayoutInflater) this.f8813a.getSystemService("layout_inflater")).inflate(R.layout.huawei_mobile_service_alert_dialog, (ViewGroup) null);
            ((HealthTextView) inflate.findViewById(R.id.note_message)).setText(String.format(Locale.ENGLISH, this.f8813a.getResources().getString(R$string.IDS_hw_plugin_account_hwid), nsn.i(this.f8813a)));
            c((HealthButton) inflate.findViewById(R.id.note_positiveButton), huaweiMobileServiceSetDialog);
            huaweiMobileServiceSetDialog.setContentView(inflate);
            huaweiMobileServiceSetDialog.setCancelable(false);
            return huaweiMobileServiceSetDialog;
        }

        private void c(HealthButton healthButton, final HuaweiMobileServiceSetDialog huaweiMobileServiceSetDialog) {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.HuaweiMobileServiceSetDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    huaweiMobileServiceSetDialog.dismiss();
                    Builder.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        public void d(HuaweiMobileServiceSetDialog huaweiMobileServiceSetDialog) {
            LogUtil.a(HuaweiMobileServiceSetDialog.f8812a, "enter dismissDialog.");
            if (huaweiMobileServiceSetDialog == null || !huaweiMobileServiceSetDialog.isShowing()) {
                return;
            }
            huaweiMobileServiceSetDialog.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.commonui.dialog.HuaweiMobileServiceSetDialog.Builder.4
                @Override // java.lang.Runnable
                public void run() {
                    final String str;
                    GRSManager a2 = GRSManager.a(Builder.this.f8813a);
                    String commonCountryCode = a2.getCommonCountryCode();
                    String noCheckUrl = a2.getNoCheckUrl("domainTipsResDbankcdn", commonCountryCode);
                    if (TextUtils.isEmpty(noCheckUrl)) {
                        LogUtil.h(HuaweiMobileServiceSetDialog.f8812a, "obtainHelpUrlDomain onCallBackSuccess urlDomain is empty");
                        return;
                    }
                    if ("CN".equalsIgnoreCase(commonCountryCode)) {
                        str = noCheckUrl + "/SmartWear/mobilephone_note/EMUI8.0/C001B001/zh-CN/index.html";
                    } else {
                        str = noCheckUrl + "/handbook/SmartWear/mobilephone_note/EMUI8.0/C001B001/zh-CN/index.html";
                    }
                    HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.commonui.dialog.HuaweiMobileServiceSetDialog.Builder.4.3
                        @Override // java.lang.Runnable
                        public void run() {
                            HuaweiMobileServiceSetDialog.e(Builder.this.f8813a, 0, str);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Context context, int i, String str) {
        LogUtil.c(f8812a, "startWebViewActivity() jumpModeKey = ", Integer.valueOf(i));
        if (!CommonUtil.u(context)) {
            str = str.replace("zh-CN", "en-US");
        }
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
        intent.putExtra(Constants.JUMP_MODE_KEY, i);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b(f8812a, "ActivityNotFoundException", e.getMessage());
        }
    }
}
