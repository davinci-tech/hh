package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.gge;
import defpackage.ggg;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsj;
import defpackage.nsn;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessActionTypeHolder extends RecyclerView.ViewHolder {
    private HealthTextView c;
    private ImageView d;
    private RelativeLayout e;

    public FitnessActionTypeHolder(View view) {
        super(view);
        this.d = (ImageView) view.findViewById(R.id.sug_item_action_icon);
        this.c = (HealthTextView) view.findViewById(R.id.sug_item_action_type);
        this.e = (RelativeLayout) view.findViewById(R.id.sug_item_action_contain);
    }

    public void c(AtomicAction atomicAction) {
        String str;
        if (atomicAction == null) {
            LogUtil.h("FitnessActionTypeHolder", "setActionData ActionInfo can not null");
            return;
        }
        this.c.setText(atomicAction.getName());
        List extendPropertyList = atomicAction.getExtendPropertyList("actionVideo", Video[].class);
        aFw_(this.e, atomicAction);
        if (koq.b(extendPropertyList)) {
            LogUtil.h("FitnessActionTypeHolder", "setActionData videos can not null");
            return;
        }
        int a2 = ggg.a();
        Iterator it = extendPropertyList.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            Video video = (Video) it.next();
            if (video != null && video.getGender() == a2) {
                str = video.getThumbnail();
                break;
            }
        }
        if (TextUtils.isEmpty(str)) {
            Video video2 = (Video) extendPropertyList.get(0);
            if (video2 == null) {
                LogUtil.h("FitnessActionTypeHolder", "setActionData video can not null");
                return;
            }
            str = video2.getThumbnail();
        }
        nrf.cIS_(this.d, str, nrf.e, 0, R.drawable._2131427609_res_0x7f0b0119);
    }

    private void aFw_(View view, final AtomicAction atomicAction) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionTypeHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AtomicAction atomicAction2 = atomicAction;
                if (atomicAction2 == null) {
                    LogUtil.h("setCustomOnClickListener actionInfo can not null", new Object[0]);
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                String id = atomicAction2.getId();
                String version = atomicAction.getVersion();
                if (nsn.o()) {
                    LogUtil.h("FitnessActionTypeHolder", "onClick is fast click");
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                HashMap hashMap = new HashMap(5);
                hashMap.put("click", 1);
                hashMap.put("date", nsj.d());
                hashMap.put("type", ffy.e(atomicAction.getTrainingPoints()));
                hashMap.put("acton_id", atomicAction.getId());
                hashMap.put("action_name", atomicAction.getName());
                gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_DETAIL_1130029.value(), hashMap);
                ViewParent parent = FitnessActionTypeHolder.this.itemView.getParent();
                if (!(parent instanceof RecyclerView)) {
                    LogUtil.h("FitnessActionTypeHolder", "setCustomOnClickListener parentView is not RecyclerView.");
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                try {
                    Context context = ((RecyclerView) parent).getContext();
                    Intent intent = new Intent(context, (Class<?>) FitnessActionDetailActivity.class);
                    intent.putExtra("action_id", id);
                    intent.putExtra("action_version", version);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("FitnessActionTypeHolder", "setCustomOnClickListener exception.");
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }
}
