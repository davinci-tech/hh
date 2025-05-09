package com.huawei.ui.homehealth.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.FunctionMenuCardData;
import com.huawei.ui.homehealth.homeinterface.ItemTouchHelperInterface;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.ovg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class HomeCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperInterface {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f9367a;
    private List<AbstractBaseCardData> c;

    public void c() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public HomeCardAdapter(Context context, List<AbstractBaseCardData> list) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        arrayList.clear();
        this.c.addAll(list);
        this.f9367a = LayoutInflater.from(context);
    }

    @Override // com.huawei.ui.homehealth.homeinterface.ItemTouchHelperInterface
    public boolean onItemMove(int i, int i2) {
        LogUtil.c("R_HomeCardAdapter", "onItemMove mDataList=", this.c);
        Collections.swap(this.c, i, i2);
        notifyItemMoved(i, i2);
        return true;
    }

    @Override // com.huawei.ui.homehealth.homeinterface.ItemTouchHelperInterface
    public void onItemSwiped(int i) {
        LogUtil.c("R_HomeCardAdapter", "onItemSwiped position=", Integer.valueOf(i));
        this.c.remove(i);
        notifyItemRemoved(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (i >= this.c.size()) {
            LogUtil.b("R_HomeCardAdapter", "wrong position!!!");
            return null;
        }
        AbstractBaseCardData abstractBaseCardData = this.c.get(i);
        RecyclerView.ViewHolder cardViewHolder = abstractBaseCardData.getCardViewHolder(viewGroup, this.f9367a);
        LogUtil.a("R_HomeCardAdapter", "onCreateViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime) + ", index:", i + ", name: " + abstractBaseCardData.getCardName());
        return cardViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (i >= this.c.size()) {
            return;
        }
        AbstractBaseCardData abstractBaseCardData = this.c.get(i);
        if (abstractBaseCardData instanceof FunctionMenuCardData) {
            ((FunctionMenuCardData) abstractBaseCardData).a(viewHolder, i);
        }
        if (abstractBaseCardData instanceof ovg) {
            ((ovg) abstractBaseCardData).d(viewHolder, i);
        }
        if (viewHolder instanceof CardViewHolder) {
            ((CardViewHolder) viewHolder).bindViewHolder(abstractBaseCardData);
        }
        LogUtil.a("R_HomeCardAdapter", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime) + ", index:", i + ", name: " + abstractBaseCardData.getCardName());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public void b(ArrayList<AbstractBaseCardData> arrayList) {
        this.c.clear();
        this.c.addAll(arrayList);
    }
}
