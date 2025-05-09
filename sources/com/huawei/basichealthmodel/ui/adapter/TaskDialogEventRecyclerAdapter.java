package com.huawei.basichealthmodel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basichealthmodel.ui.adapter.TaskDialogEventRecyclerAdapter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.RecyclerViewHolder;
import defpackage.atz;
import defpackage.koq;
import defpackage.nrz;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class TaskDialogEventRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final List<atz> f1916a;
    private final boolean b;
    private final boolean c;
    private ResponseCallback<atz> d;
    private final Context e;

    public TaskDialogEventRecyclerAdapter(List<atz> list) {
        this.f1916a = list;
        Context e = BaseApplication.e();
        this.e = e;
        this.c = LanguageUtil.m(e);
        this.b = LanguageUtil.bc(e);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: kV_, reason: merged with bridge method [inline-methods] */
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(this.e).inflate(R.layout.item_task_dialog_recycler_event, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {
        e(recyclerViewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i, List<Object> list) {
        e(recyclerViewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.f1916a)) {
            return 0;
        }
        return this.f1916a.size();
    }

    private void e(RecyclerViewHolder recyclerViewHolder, int i) {
        if (koq.b(this.f1916a, i)) {
            LogUtil.h("HealthLife_TaskDialogEventRecyclerAdapter", "bindData mList is out of bounds position ", Integer.valueOf(i), " mList ", this.f1916a);
            return;
        }
        final atz atzVar = this.f1916a.get(i);
        if (atzVar == null) {
            LogUtil.h("HealthLife_TaskDialogEventRecyclerAdapter", "bindData bean is null");
            return;
        }
        ImageView imageView = (ImageView) recyclerViewHolder.cEO_(R.id.item_task_dialog_recycler_event_icon);
        int c = atzVar.c();
        imageView.setImageDrawable(this.b ? nrz.cKn_(this.e, c) : nsf.cKq_(c));
        HealthTextView healthTextView = (HealthTextView) recyclerViewHolder.cEO_(R.id.item_task_dialog_recycler_event_text);
        if (this.c) {
            healthTextView.setText(nsf.j(atzVar.a()));
            healthTextView.setVisibility(0);
        } else {
            healthTextView.setVisibility(8);
        }
        ((RelativeLayout) recyclerViewHolder.cEO_(R.id.item_task_dialog_recycler_event_layout)).setOnClickListener(new View.OnClickListener() { // from class: ayc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TaskDialogEventRecyclerAdapter.this.kU_(atzVar, view);
            }
        });
    }

    public /* synthetic */ void kU_(atz atzVar, View view) {
        ResponseCallback<atz> responseCallback = this.d;
        if (responseCallback == null) {
            LogUtil.h("HealthLife_TaskDialogEventRecyclerAdapter", "bindData mCallback is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            responseCallback.onResponse(0, atzVar);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void c(ResponseCallback<atz> responseCallback) {
        this.d = responseCallback;
    }
}
