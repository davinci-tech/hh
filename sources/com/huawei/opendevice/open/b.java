package com.huawei.opendevice.open;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class b extends ClickableSpan {
    private final Context d;
    private Class<?> e;

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(this.d.getResources().getColor(R.color._2131297975_res_0x7f0906b7));
        textPaint.setUnderlineText(false);
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        String str;
        ho.b("ClickSpan", "onClick");
        if (this.e == null) {
            ho.c("ClickSpan", "onClick activity is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        try {
            Intent intent = new Intent(this.d, this.e);
            intent.setClipData(Constants.CLIP_DATA);
            this.d.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            str = "onClick startActivity ActivityNotFoundException";
            ho.d("ClickSpan", str);
            ViewClickInstrumentation.clickOnView(view);
        } catch (Exception unused2) {
            str = "onClick startActivity Exception";
            ho.d("ClickSpan", str);
            ViewClickInstrumentation.clickOnView(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(Class<?> cls) {
        this.e = cls;
    }

    public b(Context context) {
        this.d = context;
    }
}
