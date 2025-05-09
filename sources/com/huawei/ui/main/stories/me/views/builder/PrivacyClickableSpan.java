package com.huawei.ui.main.stories.me.views.builder;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.ui.main.stories.me.views.builder.PrivacyClickableSpan;
import defpackage.nsn;
import health.compact.a.GRSManager;
import java.util.Locale;

/* loaded from: classes7.dex */
public class PrivacyClickableSpan extends ClickableSpan {
    private Context b;
    private String e;

    public PrivacyClickableSpan(Context context) {
        this.b = context;
        ThreadPoolManager.d().execute(new Runnable() { // from class: rib
            @Override // java.lang.Runnable
            public final void run() {
                PrivacyClickableSpan.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        this.e = a();
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        String str;
        LogUtil.a("PrivacyClickableSpan", "ClickableSpan_onClick");
        if (nsn.a(500)) {
            LogUtil.h("PrivacyClickableSpan", "ClickableSpan_onClick isFastClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        Context context = this.b;
        if (context != null && (str = this.e) != null) {
            H5proUtil.jumpFromDeeplink(context, str);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        if (textPaint != null) {
            super.updateDrawState(textPaint);
            textPaint.setColor(Color.parseColor("#fb6522"));
            textPaint.setUnderlineText(false);
        }
    }

    private String a() {
        String commonCountryCode = GRSManager.a(this.b).getCommonCountryCode();
        return GRSManager.a(this.b).getNoCheckUrl("domainConsumerHuawei", commonCountryCode) + "/" + commonCountryCode.toLowerCase(Locale.ROOT) + "/legal/privacy-questions/?forceDarkMode=0";
    }
}
