package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.stories.settings.activity.heartrate.InstructionOfHrrHeartRateActivity;
import com.huawei.ui.main.stories.settings.activity.heartrate.InstructionOfMaxHeartRateActivity;

/* loaded from: classes7.dex */
public class rvd extends ClickableSpan {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16924a;

    public rvd(boolean z) {
        this.f16924a = z;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Intent intent;
        Context context = BaseApplication.getContext();
        if (context == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.f16924a) {
            intent = new Intent(context, (Class<?>) InstructionOfMaxHeartRateActivity.class);
        } else {
            intent = new Intent(context, (Class<?>) InstructionOfHrrHeartRateActivity.class);
        }
        intent.addFlags(268435456);
        gnm.aPB_(context, intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(Color.parseColor("#fb6522"));
        textPaint.setUnderlineText(false);
    }
}
