package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionTypeActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.fqa;
import defpackage.gge;
import defpackage.koq;
import defpackage.nsj;
import defpackage.nsn;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessActionViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f3207a;
    private Context b;
    private HealthTextView c;
    private RelativeLayout d;
    private HealthTextView e;
    private ImageView f;
    private ImageView j;

    public FitnessActionViewHolder(View view) {
        super(view);
        this.b = arx.b();
        this.j = (ImageView) view.findViewById(R.id.sug_item_action_icon_left);
        this.e = (HealthTextView) view.findViewById(R.id.sug_item_action_type_left);
        this.d = (RelativeLayout) view.findViewById(R.id.sug_item_action_contain_left);
        this.f = (ImageView) view.findViewById(R.id.sug_item_action_icon_right);
        this.c = (HealthTextView) view.findViewById(R.id.sug_item_action_type_right);
        this.f3207a = (RelativeLayout) view.findViewById(R.id.sug_item_action_contain_right);
    }

    public void b(List<fqa> list, int i) {
        if (koq.b(list)) {
            LogUtils.w("Suggestion_FitnessActionViewHolder", "setActionData ActionData can not be null");
            return;
        }
        int i2 = i * 2;
        int i3 = i2 + 1;
        if (i3 > list.size()) {
            LogUtils.w("Suggestion_FitnessActionViewHolder", "setActionData itemIndex is error");
            return;
        }
        fqa fqaVar = list.get(i2);
        fqa fqaVar2 = list.get(i3);
        if (fqaVar == null || fqaVar2 == null) {
            LogUtils.w("Suggestion_FitnessActionViewHolder", "setActionData ActionData can not be null");
            return;
        }
        this.e.setText(this.b.getString(fqaVar.d()));
        this.j.setImageResource(fqaVar.c());
        aFx_(this.d, fqaVar);
        this.c.setText(this.b.getString(fqaVar2.d()));
        this.f.setImageResource(fqaVar2.c());
        aFx_(this.f3207a, fqaVar2);
    }

    private void aFx_(View view, final fqa fqaVar) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (nsn.o()) {
                    LogUtil.h("Suggestion_FitnessActionViewHolder", "onClick is fast click");
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                HashMap hashMap = new HashMap(3);
                hashMap.put("click", 1);
                hashMap.put("date", nsj.d());
                hashMap.put("type", Integer.valueOf(fqaVar.b()));
                gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_TYPE_1130028.value(), hashMap);
                ViewParent parent = FitnessActionViewHolder.this.itemView.getParent();
                if (!(parent instanceof RecyclerView)) {
                    LogUtil.h("Suggestion_FitnessActionViewHolder", "parentView is not RecyclerView.");
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                try {
                    Context context = ((RecyclerView) parent).getContext();
                    Intent intent = new Intent(context, (Class<?>) FitnessActionTypeActivity.class);
                    intent.putExtra("body_title", context.getString(fqaVar.d()));
                    intent.putExtra("body_title_type", fqaVar.b());
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("Suggestion_FitnessActionViewHolder", "setCustomItemClickListener ActivityNotFoundException.");
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }
}
