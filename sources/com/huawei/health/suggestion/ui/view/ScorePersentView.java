package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.ui.commonui.progressbar.HealthMultiProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.fqq;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class ScorePersentView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f3425a;
    private HealthRecycleView d;
    private Context e;

    public ScorePersentView(Context context) {
        this(context, null);
    }

    public ScorePersentView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScorePersentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
        c();
    }

    private void c() {
        View inflate = View.inflate(this.e, R.layout.action_finish_main, this);
        this.f3425a = inflate;
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.action_finish_score_view);
        this.d = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        this.d.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
    }

    public void setActionScoreAdapter(List<fqq> list) {
        this.d.setAdapter(new BaseRecyclerViewAdapter<fqq>(list, R.layout.action_finish_score_item) { // from class: com.huawei.health.suggestion.ui.view.ScorePersentView.1
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, fqq fqqVar) {
                recyclerHolder.e(R.id.sug_coach_item_finish_actionname, fqqVar.c());
                String valueOf = String.valueOf(fqqVar.d());
                recyclerHolder.e(R.id.sug_coach_item_score, ScorePersentView.this.getResources().getQuantityString(R.plurals._2130903221_res_0x7f0300b5, CommonUtil.h(valueOf), valueOf));
                HealthMultiProgressBar healthMultiProgressBar = (HealthMultiProgressBar) recyclerHolder.aCA_(R.id.sug_coach_item_progress);
                Integer[] numArr = {Integer.valueOf(fqqVar.i()), Integer.valueOf(fqqVar.b()), Integer.valueOf(fqqVar.a()), Integer.valueOf(fqqVar.e()), Integer.valueOf(fqqVar.g())};
                if (ScorePersentView.this.b(numArr) == 0) {
                    numArr[4] = 1;
                }
                healthMultiProgressBar.c(4).b(10).d(numArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(Integer[] numArr) {
        if (numArr == null) {
            return 0;
        }
        int i = 0;
        for (Integer num : numArr) {
            i += num.intValue();
        }
        return i;
    }
}
