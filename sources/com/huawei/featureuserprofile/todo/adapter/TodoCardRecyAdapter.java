package com.huawei.featureuserprofile.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gka;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class TodoCardRecyAdapter extends RecyclerView.Adapter<TodoCardInnerViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<gka> f2052a;
    private OnItemClickListener b;
    private int d;
    private Context e;

    /* loaded from: classes.dex */
    public interface OnItemClickListener<T> {
        void onItemClicked(int i);
    }

    public TodoCardRecyAdapter(Context context, List<gka> list) {
        ArrayList arrayList = new ArrayList();
        this.f2052a = arrayList;
        this.e = context;
        arrayList.clear();
        this.f2052a.addAll(list);
        LogUtil.a("TodoCardRecyAdapter", "mTodoCardRecyModels");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: vF_, reason: merged with bridge method [inline-methods] */
    public TodoCardInnerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("TodoCardRecyAdapter", "onCreateViewHolder");
        return new TodoCardInnerViewHolder(this.e, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_todo_recy_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(TodoCardInnerViewHolder todoCardInnerViewHolder, final int i) {
        LogUtil.a("TodoCardRecyAdapter", "onBindViewHolder");
        List<gka> list = this.f2052a;
        if (list == null || list.get(i) == null) {
            return;
        }
        todoCardInnerViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.todo.adapter.TodoCardRecyAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TodoCardRecyAdapter.this.b != null) {
                    TodoCardRecyAdapter.this.b.onItemClicked(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (i == 0) {
            todoCardInnerViewHolder.a();
        }
        todoCardInnerViewHolder.a(this.f2052a.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.d <= this.f2052a.size()) {
            return this.d;
        }
        LogUtil.h("TodoCardRecyAdapter", "mShowCount should not > ", Integer.valueOf(this.f2052a.size()));
        return this.f2052a.size();
    }

    public void d(int i) {
        this.d = i;
    }

    public void e(List<gka> list) {
        LogUtil.a("TodoCardRecyAdapter", "save todoCardRecyModels.size():", Integer.valueOf(list.size()));
        this.f2052a.clear();
        this.f2052a.addAll(list);
    }

    public void c(OnItemClickListener onItemClickListener) {
        this.b = onItemClickListener;
    }
}
