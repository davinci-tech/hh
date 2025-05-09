package com.huawei.health.suggestion.ui.fitness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.CoachMotion;
import com.huawei.health.suggestion.ui.fitness.helper.LoadMoreInterface;
import com.huawei.health.suggestion.ui.fitness.module.TrainActionIntro;
import com.huawei.health.suggestion.ui.fitness.viewholder.CoachActionViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CoachActionRecyclerAdapter extends RecyclerView.Adapter<CoachActionViewHolder> {
    private TrainActionIntro b;
    private Context d;
    private CoachActionViewHolder e;
    private LoadMoreInterface i;
    private List<CoachMotion> c = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private int f3143a = 1;

    public CoachActionRecyclerAdapter(HealthRecycleView healthRecycleView, TrainActionIntro trainActionIntro, Context context) {
        this.d = context;
        this.b = trainActionIntro;
        if (healthRecycleView == null) {
            LogUtil.h("Suggestion_CoachActionRecyclerAdapter", "RecyclerView can not be null");
        } else {
            healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.CoachActionRecyclerAdapter.4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    if (ViewCompat.canScrollVertically(recyclerView, CoachActionRecyclerAdapter.this.f3143a) || CoachActionRecyclerAdapter.this.i == null) {
                        return;
                    }
                    CoachActionRecyclerAdapter.this.i.loadMore();
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aBh_, reason: merged with bridge method [inline-methods] */
    public CoachActionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CoachActionViewHolder coachActionViewHolder = new CoachActionViewHolder(LayoutInflater.from(this.d).inflate(R.layout.sug_item_coach_action, viewGroup, false), this.b);
        this.e = coachActionViewHolder;
        return coachActionViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(CoachActionViewHolder coachActionViewHolder, int i) {
        coachActionViewHolder.e(this.c, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CoachMotion> list = this.c;
        if (list == null || list.size() <= 0) {
            return 0;
        }
        return this.c.size();
    }

    public void d(List<CoachMotion> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.c.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    public void a(int i, boolean z) {
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            CoachMotion coachMotion = this.c.get(i2);
            if (i2 == i) {
                coachMotion.setButtonVisible(z);
            } else {
                coachMotion.setButtonVisible(false);
            }
        }
        notifyDataSetChanged();
    }
}
