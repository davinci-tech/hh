package com.huawei.health.suggestion.ui.run.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffj;

/* loaded from: classes8.dex */
public class ShowPlanItemWeekHolder implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    private final ImageButton f3373a;
    private View b;
    private float c;
    private float d;
    private ffj e;
    private final HealthTextView f;
    private final HealthTextView g;
    private int j;

    public ShowPlanItemWeekHolder(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sug_run_item_show_week, (ViewGroup) new ListView(context), false);
        this.b = inflate;
        this.f3373a = (ImageButton) inflate.findViewById(R.id.sug_ibtn_desc);
        this.f = (HealthTextView) this.b.findViewById(R.id.sug_txt_week_index);
        this.g = (HealthTextView) this.b.findViewById(R.id.sug_txt_week_phrase);
        this.b.setTag(this);
        this.j = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.c = motionEvent.getRawX();
            this.d = motionEvent.getRawY();
        } else if (motionEvent.getAction() == 1) {
            float abs = Math.abs(motionEvent.getRawX() - this.c);
            float abs2 = Math.abs(motionEvent.getRawY() - this.d);
            float f = this.j;
            if (f >= abs && f >= abs2) {
                d();
            }
        }
        return true;
    }

    private void d() {
        new CustomTextAlertDialog.Builder(this.b.getContext()).b(R.string._2130848356_res_0x7f022a64).cyU_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.holder.ShowPlanItemWeekHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e(this.e.d().getSentence()).a().show();
    }
}
