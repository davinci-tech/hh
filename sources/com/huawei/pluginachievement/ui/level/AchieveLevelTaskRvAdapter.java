package com.huawei.pluginachievement.ui.level;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.ui.kakatask.AchieveKaKaTaskClickListener;
import defpackage.koq;
import defpackage.mkg;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class AchieveLevelTaskRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<mkg> b;
    private AchieveKaKaTaskClickListener c;
    private int d;
    private Context e;

    public AchieveLevelTaskRvAdapter(Context context, ArrayList<mkg> arrayList, int i) {
        new ArrayList(0);
        this.e = context;
        this.b = arrayList;
        this.d = i;
    }

    public void b(ArrayList<mkg> arrayList, int i) {
        if (koq.b(arrayList)) {
            return;
        }
        this.b = arrayList;
        this.d = i;
        notifyDataSetChanged();
    }

    public void c(AchieveKaKaTaskClickListener achieveKaKaTaskClickListener) {
        this.c = achieveKaKaTaskClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -2);
        if (i == 0) {
            View inflate = LayoutInflater.from(this.e).inflate(R.layout.achieve_task_level_title, viewGroup, false);
            inflate.setLayoutParams(layoutParams);
            return new AchieveLevelTaskTitleHolder(inflate);
        }
        if (i == 1) {
            View inflate2 = LayoutInflater.from(this.e).inflate(R.layout.achieve_task_level_content, viewGroup, false);
            inflate2.setLayoutParams(layoutParams);
            return new AchieveLevelTaskContentHolder(inflate2, this.c, this.d);
        }
        LogUtil.h("AchieveLevelTaskRvAdapter", "viewType unknow:", Integer.valueOf(i));
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (koq.b(this.b, i)) {
            return;
        }
        viewHolder.setIsRecyclable(false);
        mkg mkgVar = this.b.get(i);
        if (mkgVar == null) {
            return;
        }
        if (viewHolder instanceof AchieveLevelTaskTitleHolder) {
            ((AchieveLevelTaskTitleHolder) viewHolder).b(mkgVar);
        } else if (viewHolder instanceof AchieveLevelTaskContentHolder) {
            ((AchieveLevelTaskContentHolder) viewHolder).b(mkgVar, i);
        } else {
            LogUtil.c("AchieveLevelTaskRvAdapter", "onBindViewHolder holder is not matching");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.b)) {
            return 0;
        }
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.b, i)) {
            LogUtil.b("AchieveLevelTaskRvAdapter", "getItemViewType isOutOfBounds");
            return -1;
        }
        return this.b.get(i).a();
    }
}
