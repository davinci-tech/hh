package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.CoachMotion;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.module.TrainActionIntro;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.fnw;
import defpackage.nrf;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CoachActionViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3199a;
    private HealthButton b;
    private int c;
    private TrainActionIntro d;
    private HealthTextView e;

    public CoachActionViewHolder(View view, TrainActionIntro trainActionIntro) {
        super(view);
        this.d = trainActionIntro;
        this.f3199a = (ImageView) view.findViewById(R.id.sug_item_coach_image);
        this.e = (HealthTextView) view.findViewById(R.id.sug_item_coach_text);
        this.b = (HealthButton) view.findViewById(R.id.sug_item_coach_button);
    }

    public void e(List<CoachMotion> list, int i) {
        CoachMotion coachMotion = list.get(i);
        String motionImageUrl = coachMotion.getMotionImageUrl();
        this.c = coachMotion.getTotalMotionNum();
        int currMotionNum = coachMotion.getCurrMotionNum();
        nrf.cIU_(motionImageUrl, this.f3199a, nrf.d);
        this.e.setText(UnitUtil.e(currMotionNum + 1, 1, 0) + "/" + UnitUtil.e(this.c, 1, 0));
        this.b.setText(R.string._2130848398_res_0x7f022a8e);
        this.b.setVisibility(coachMotion.isButtonVisible() ? 0 : 8);
        aFa_(this.f3199a, coachMotion);
    }

    private void aFa_(View view, final CoachMotion coachMotion) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.CoachActionViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CoachActionViewHolder.this.d(coachMotion);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(CoachMotion coachMotion) {
        this.d.setVisibility(0);
        this.d.a(UnitUtil.e(coachMotion.getCurrMotionNum() + 1, 1, 0), UnitUtil.e(this.c, 1, 0));
        this.d.getNextAction().setVisibility(8);
        this.d.getPreAction().setVisibility(8);
        ArrayList arrayList = new ArrayList(10);
        Motion motion = coachMotion.getMotion();
        arrayList.clear();
        arrayList.add(motion);
        if (ffy.c(motion.acquireCovers())) {
            arrayList.add(motion.acquireCovers());
        }
        fnw fnwVar = new fnw(arrayList, R.layout.sug_coach_vp_intro);
        this.d.setAdapter(fnwVar);
        fnwVar.notifyDataSetChanged();
        this.d.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.CoachActionViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachActionViewHolder.this.d.setVisibility(4);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }
}
