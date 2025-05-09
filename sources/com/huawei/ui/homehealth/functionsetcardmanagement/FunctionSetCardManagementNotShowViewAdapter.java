package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;
import defpackage.ojs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class FunctionSetCardManagementNotShowViewAdapter extends RecyclerView.Adapter<FunctionSetCardManagementDeleteChineseViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private FunctionSetCardManagementActivity f9446a;
    private List<ojs> b;
    private Context d;
    private List<ojs> f = new ArrayList();
    private List<ojs> e = new ArrayList();
    private boolean c = true;

    public FunctionSetCardManagementNotShowViewAdapter(List<ojs> list, FunctionSetCardManagementActivity functionSetCardManagementActivity) {
        ArrayList arrayList = new ArrayList(list);
        this.b = arrayList;
        LogUtil.a("FunctionSetCardManagementViewAdapter", "mDataList: ", arrayList);
        this.f9446a = functionSetCardManagementActivity;
        this.d = functionSetCardManagementActivity;
        e();
    }

    private void e() {
        c();
        a();
        for (int i = 0; i < this.f.size(); i++) {
            this.f.get(i).b(i);
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            this.e.get(i2).b(i2 + 1000);
        }
        Collections.sort(this.b);
        LogUtil.a("FunctionSetCardManagementViewAdapter", "mDataList: ", this.b);
        LogUtil.a("FunctionSetCardManagementViewAdapter", "mShowedDataList: ", this.f);
        LogUtil.a("FunctionSetCardManagementViewAdapter", "mNotShowedDataList: ", this.e);
    }

    private void c() {
        this.f.clear();
        List<ojs> list = this.b;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "getShowedDataList : mDataList is null");
            return;
        }
        for (ojs ojsVar : list) {
            if (ojsVar.c() == 1) {
                this.f.add(ojsVar);
            }
        }
    }

    private void a() {
        this.e.clear();
        List<ojs> list = this.b;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "getNotShowedDataList : mDataList is null");
            return;
        }
        for (ojs ojsVar : list) {
            if (ojsVar.c() != 1) {
                this.e.add(ojsVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dbS_, reason: merged with bridge method [inline-methods] */
    public FunctionSetCardManagementDeleteChineseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FunctionSetCardManagementDeleteChineseViewHolder(LayoutInflater.from(this.d).inflate(R.layout.function_set_management_view_card_delete_chinese, viewGroup, false), this.d, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(FunctionSetCardManagementDeleteChineseViewHolder functionSetCardManagementDeleteChineseViewHolder, final int i) {
        List<ojs> list;
        if (functionSetCardManagementDeleteChineseViewHolder == null || (list = this.e) == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "holder or mDataList is null");
            return;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "position is wrong :", Integer.valueOf(i));
            return;
        }
        dbR_(functionSetCardManagementDeleteChineseViewHolder.itemView, i);
        functionSetCardManagementDeleteChineseViewHolder.setIsRecyclable(false);
        LogUtil.c("FunctionSetCardManagementViewAdapter", "DeleteViewHolder cardName:", this.e.get(i).e(), "position", Integer.valueOf(i));
        functionSetCardManagementDeleteChineseViewHolder.e(this.e.get(i), this.c);
        dbQ_(functionSetCardManagementDeleteChineseViewHolder.dbO_(), i);
        functionSetCardManagementDeleteChineseViewHolder.dbN_().setOnLongClickListener(new View.OnLongClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementNotShowViewAdapter.4
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                LogUtil.a("FunctionSetCardManagementViewAdapter", "not show card is long clicked, position: ", Integer.valueOf(i));
                if (FunctionSetCardManagementNotShowViewAdapter.this.c) {
                    return false;
                }
                FunctionSetCardManagementNotShowViewAdapter.this.f9446a.d(true);
                return false;
            }
        });
    }

    private void dbR_(View view, int i) {
        if (view.getLayoutParams() instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516);
            int dimensionPixelSize2 = this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize3 = this.d.getResources().getDimensionPixelSize(R.dimen._2131362860_res_0x7f0a042c);
            int dimensionPixelSize4 = this.d.getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516);
            if (nsn.ae(this.d) || nsn.ag(this.d)) {
                int i2 = i % 4;
                if (i2 == 0) {
                    layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4);
                } else if (i2 == 3) {
                    layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize, dimensionPixelSize4);
                } else {
                    layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4);
                }
            } else if (i % 2 == 0) {
                layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4);
            } else {
                layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize, dimensionPixelSize4);
            }
            view.setLayoutParams(layoutParams);
        }
    }

    private void dbQ_(ImageView imageView, final int i) {
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementNotShowViewAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2 = i;
                if (i2 >= 0 && i2 < FunctionSetCardManagementNotShowViewAdapter.this.e.size()) {
                    FunctionSetCardManagementNotShowViewAdapter.this.e(i);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.h("FunctionSetCardManagementViewAdapter", "setAddOnClick : position is wrong :", Integer.valueOf(i));
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i < 0 || i >= this.e.size()) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "setItemAddStatus : position is wrong :", Integer.valueOf(i));
            return;
        }
        LogUtil.a("FunctionSetCardManagementViewAdapter", "setItemAddStatus position = ", Integer.valueOf(i));
        this.e.get(i).d(1);
        e();
        notifyDataSetChanged();
        FunctionSetCardManagementActivity functionSetCardManagementActivity = this.f9446a;
        if (functionSetCardManagementActivity != null) {
            functionSetCardManagementActivity.d(i + this.f.size(), this.f.size());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public void d(int i) {
        e();
        notifyDataSetChanged();
    }

    public void b(boolean z) {
        this.c = z;
        notifyDataSetChanged();
    }

    public void e(final List<ojs> list) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementNotShowViewAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardManagementNotShowViewAdapter.this.e(list);
                }
            });
            return;
        }
        this.b.clear();
        this.b.addAll(list);
        e();
        notifyDataSetChanged();
    }
}
