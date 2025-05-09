package com.huawei.pluginachievement.ui.kakatask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.mkg;
import defpackage.mkj;
import defpackage.mle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes8.dex */
public class AchieveKaKaTaskRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private AchieveKaKaTaskClickListener f8441a;
    private Context b;
    private ArrayList<mkg> c;
    private int d;
    private int e;
    private int h;
    private int i;
    private int j;

    private int c(boolean z) {
        return z ? 1 : 0;
    }

    public AchieveKaKaTaskRVAdapter(Context context, ArrayList<mkg> arrayList) {
        new ArrayList(0);
        this.b = context;
        this.c = arrayList;
    }

    public ArrayList<mkg> d(ArrayList<mkg> arrayList, int i, int i2) {
        if (koq.b(arrayList)) {
            return arrayList;
        }
        b(arrayList);
        this.i = i;
        this.e = i2;
        boolean z = false;
        this.h = 0;
        if (i == 1 && i2 == 1) {
            this.c = arrayList;
            Iterator<mkg> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                mkg next = it.next();
                mkj b = next.b();
                if (b != null && mle.d(b.n())) {
                    if (next.j() && next.c()) {
                        z = true;
                    }
                    this.h = c(z);
                }
            }
            notifyDataSetChanged();
            return this.c;
        }
        ArrayList<mkg> arrayList2 = new ArrayList<>(2);
        ArrayList<mkg> arrayList3 = new ArrayList<>(2);
        Iterator<mkg> it2 = arrayList.iterator();
        boolean z2 = false;
        int i3 = 0;
        while (it2.hasNext()) {
            mkg next2 = it2.next();
            mkj b2 = next2.b();
            if (this.b.getString(R.string._2130840770_res_0x7f020cc2).equals(next2.e())) {
                arrayList2.add(next2);
            } else if (this.b.getString(R.string._2130840769_res_0x7f020cc1).equals(next2.e())) {
                arrayList3.add(next2);
            } else if (b2 != null) {
                boolean d = mle.d(b2.n());
                boolean z3 = !z2 || this.i == 1;
                if (d && z3) {
                    arrayList2.add(next2);
                    z2 = true;
                } else {
                    boolean z4 = i3 < 3 || this.e == 1;
                    if (!d && z4) {
                        i3++;
                        arrayList3.add(next2);
                    }
                }
            }
        }
        this.c = d(arrayList2, arrayList3);
        notifyDataSetChanged();
        return this.c;
    }

    private void b(ArrayList<mkg> arrayList) {
        Map<String, Object> a2 = mle.a(arrayList, 1);
        if (a2.containsKey("task_size")) {
            Object obj = a2.get("task_size");
            if (obj instanceof Integer) {
                this.j = ((Integer) obj).intValue();
            }
        }
        if (a2.containsKey("finish_count")) {
            Object obj2 = a2.get("finish_count");
            if (obj2 instanceof Integer) {
                this.d = ((Integer) obj2).intValue();
            }
        }
        LogUtil.a("AchieveKaKaTaskRVAdapter", "checkTaskFinishInfo taskSize ", Integer.valueOf(this.j), " finishCount ", Integer.valueOf(this.d));
    }

    private ArrayList<mkg> d(ArrayList<mkg> arrayList, ArrayList<mkg> arrayList2) {
        ArrayList<mkg> arrayList3 = new ArrayList<>(0);
        e(arrayList3, arrayList);
        a(arrayList3, arrayList, arrayList2);
        return arrayList3;
    }

    private void a(ArrayList<mkg> arrayList, ArrayList<mkg> arrayList2, ArrayList<mkg> arrayList3) {
        if (koq.b(arrayList3)) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
            if (arrayList3.get(i2).b() == null) {
                arrayList.add(arrayList3.get(i2));
            } else {
                mkg mkgVar = new mkg();
                mkgVar.e(arrayList3.get(i2).a());
                mkgVar.a(arrayList3.get(i2).e());
                mkgVar.e(arrayList3.get(i2).b());
                mkgVar.c(arrayList3.get(i2).d());
                mkgVar.d(arrayList3.get(i2).j());
                if (i2 == arrayList3.size() - 1) {
                    mkgVar.b(true);
                }
                arrayList.add(mkgVar);
                i++;
                if (d(arrayList2, i)) {
                    mkgVar.b(true);
                    return;
                }
            }
        }
    }

    private void e(ArrayList<mkg> arrayList, ArrayList<mkg> arrayList2) {
        if (koq.b(arrayList2)) {
            return;
        }
        for (int i = 0; i < arrayList2.size(); i++) {
            if (arrayList2.get(i).b() == null) {
                arrayList.add(arrayList2.get(i));
            } else {
                mkg mkgVar = new mkg();
                mkgVar.e(arrayList2.get(i).a());
                mkgVar.a(arrayList2.get(i).e());
                mkgVar.e(arrayList2.get(i).b());
                mkgVar.c(arrayList2.get(i).d());
                mkgVar.d(arrayList2.get(i).j());
                this.h = c(arrayList2.get(i).j() == arrayList2.get(i).c());
                if (i == arrayList2.size() - 1) {
                    mkgVar.b(true);
                }
                arrayList.add(mkgVar);
            }
        }
    }

    private boolean d(ArrayList<mkg> arrayList, int i) {
        int i2;
        int i3;
        if (this.e == 1) {
            return false;
        }
        if (!koq.b(arrayList) || (i2 = this.d) == (i3 = this.j)) {
            return i == 1;
        }
        int i4 = i3 - i2;
        return i4 >= 3 ? i == 3 : i4 == 2 ? i == 2 : i == 1;
    }

    public void e(AchieveKaKaTaskClickListener achieveKaKaTaskClickListener) {
        this.f8441a = achieveKaKaTaskClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -2);
        if (i == 0) {
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.achieve_task_ka_ka_title, viewGroup, false);
            inflate.setLayoutParams(layoutParams);
            return new AchieveKaKaTaskTitleHolder(inflate);
        }
        if (i == 1) {
            View inflate2 = LayoutInflater.from(this.b).inflate(R.layout.achieve_task_ka_ka_content, viewGroup, false);
            inflate2.setLayoutParams(layoutParams);
            return new AchieveKaKaTaskContentHolder(inflate2, this.f8441a);
        }
        LogUtil.h("AchieveKaKaTaskRVAdapter", "viewType unknow:", Integer.valueOf(i));
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i < 0 || i >= this.c.size()) {
            return;
        }
        viewHolder.setIsRecyclable(false);
        mkg mkgVar = this.c.get(i);
        if (mkgVar != null) {
            if (viewHolder instanceof AchieveKaKaTaskTitleHolder) {
                ((AchieveKaKaTaskTitleHolder) viewHolder).d(mkgVar, this.d, this.j);
            } else {
                if (viewHolder instanceof AchieveKaKaTaskContentHolder) {
                    AchieveKaKaTaskContentHolder achieveKaKaTaskContentHolder = (AchieveKaKaTaskContentHolder) viewHolder;
                    achieveKaKaTaskContentHolder.a(this.h);
                    achieveKaKaTaskContentHolder.e(mkgVar, i, this.i, this.e, this.d == this.j);
                    return;
                }
                LogUtil.c("AchieveKaKaTaskRVAdapter", "onBindViewHolder holder is not matching");
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.c, i)) {
            LogUtil.b("AchieveKaKaTaskRVAdapter", "getItemViewType isOutOfBounds");
            return -1;
        }
        return this.c.get(i).a();
    }
}
