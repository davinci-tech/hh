package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity;

/* loaded from: classes7.dex */
public class rvq extends ClickableSpan {

    /* renamed from: a, reason: collision with root package name */
    private String f16934a;
    private boolean b;
    private Context e;

    public rvq(Context context, String str) {
        this(context, str, true);
    }

    public rvq(Context context, String str, boolean z) {
        this.e = context;
        this.f16934a = str;
        this.b = z;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        LogUtil.a("MyClickableSpan", "ClickableSpan_onClick");
        if (nsn.a(500)) {
            LogUtil.a("MyClickableSpan", "isFastClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.e != null) {
            LogUtil.a("MyClickableSpan", "ClickableSpan_START");
            Intent intent = new Intent(this.e, (Class<?>) ServiceItemActivity.class);
            intent.putExtra("Agreement_key", this.f16934a);
            intent.putExtra("Is_show_cancel_key", this.b);
            intent.addFlags(268435456);
            this.e.startActivity(intent);
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
}
