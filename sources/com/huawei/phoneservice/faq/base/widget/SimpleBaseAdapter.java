package com.huawei.phoneservice.faq.base.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class SimpleBaseAdapter<T> extends BaseAdapter {
    protected List<T> e = new ArrayList(0);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }

    public void c(List<T> list) {
        List<T> list2;
        if (list == null || (list2 = this.e) == null) {
            this.e = new ArrayList();
        } else {
            list2.clear();
            this.e.addAll(list);
        }
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<T> list = this.e;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.e;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void e(List<T> list) {
        c(this.e.size(), list);
    }

    public void c(int i, List<T> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        List<T> subList = this.e.subList(0, i);
        this.e = subList;
        subList.addAll(list);
    }
}
