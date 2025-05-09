package com.huawei.featureuserprofile.todo.adapter;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import com.huawei.featureuserprofile.todo.datatype.TodoCheckEntity;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.koq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class TodoCheckListAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2054a;
    private boolean b;
    private LayoutInflater c;
    private List<TodoCheckEntity> d;
    private CompoundButton.OnCheckedChangeListener e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public void vH_(ViewGroup viewGroup) {
        registerDataSetObserver(new d(viewGroup, this));
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public TodoCheckEntity getItem(int i) {
        if (koq.b(this.d, i)) {
            return null;
        }
        return this.d.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.c == null) {
            this.c = LayoutInflater.from(viewGroup.getContext());
        }
        if (view == null) {
            view = this.c.inflate(R.layout.item_todo_check_layout, viewGroup, false);
        }
        view.setId(View.generateViewId());
        vG_(getItem(i), view, i);
        return view;
    }

    public List<TodoCheckEntity> b() {
        return this.d;
    }

    private void vG_(TodoCheckEntity todoCheckEntity, View view, int i) {
        if (todoCheckEntity == null || view == null) {
            return;
        }
        view.findViewById(R.id.health_todo_live_item_divider).setVisibility(i == getCount() - 1 ? 8 : 0);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) view.findViewById(R.id.health_todo_live_item_switch);
        healthSwitchButton.setChecked(todoCheckEntity.isChecked());
        healthSwitchButton.setEnabled(this.f2054a && this.b);
        healthSwitchButton.setOnCheckedChangeListener(this);
        healthSwitchButton.setTag(R.id.health_todo_live_item_switch, Integer.valueOf(i));
        ((HealthTextView) view.findViewById(R.id.health_todo_live_item_switch_title)).setText(todoCheckEntity.getTitle());
    }

    public void vI_(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.e = onCheckedChangeListener;
    }

    public void c(boolean z) {
        if (this.b != z) {
            this.b = z;
            notifyDataSetChanged();
        }
    }

    public void a(boolean z) {
        if (this.f2054a != z) {
            this.f2054a = z;
            notifyDataSetChanged();
        }
    }

    public void e(List<TodoCheckEntity> list) {
        if (koq.b(list)) {
            return;
        }
        List<TodoCheckEntity> list2 = this.d;
        if (list2 == null) {
            this.d = new ArrayList();
        } else {
            list2.clear();
        }
        this.d.addAll(list);
        notifyDataSetChanged();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.e;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(compoundButton, z);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    static class d extends DataSetObserver {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<ViewGroup> f2055a;
        private final WeakReference<BaseAdapter> d;

        d(ViewGroup viewGroup, BaseAdapter baseAdapter) {
            this.d = new WeakReference<>(baseAdapter);
            this.f2055a = new WeakReference<>(viewGroup);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ViewGroup viewGroup = this.f2055a.get();
            BaseAdapter baseAdapter = this.d.get();
            if (viewGroup == null) {
                return;
            }
            if (baseAdapter == null || baseAdapter.getCount() <= 0) {
                viewGroup.removeAllViews();
                viewGroup.setVisibility(8);
                return;
            }
            int count = baseAdapter.getCount();
            for (int i = 0; i < count; i++) {
                vJ_(baseAdapter.getView(i, vK_(i, viewGroup), viewGroup), viewGroup, i);
            }
            int childCount = viewGroup.getChildCount();
            if (childCount > count) {
                while (true) {
                    childCount--;
                    if (childCount < count) {
                        break;
                    } else {
                        viewGroup.removeViewAt(childCount);
                    }
                }
            }
            viewGroup.setVisibility(viewGroup.getChildCount() > 0 ? 0 : 8);
        }

        private void vJ_(View view, ViewGroup viewGroup, int i) {
            if (view == null || viewGroup == null) {
                return;
            }
            if (view.getParent() != null) {
                ViewParent parent = view.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(view);
                }
            }
            if (i < viewGroup.getChildCount()) {
                viewGroup.addView(view, i);
            } else {
                viewGroup.addView(view);
            }
        }

        private View vK_(int i, ViewGroup viewGroup) {
            if (i < viewGroup.getChildCount()) {
                return viewGroup.getChildAt(i);
            }
            return null;
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
        }
    }
}
