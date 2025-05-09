package com.huawei.phoneservice.feedback.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.huawei.health.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class e extends BaseAdapter {
    private final int b;
    private List<CharSequence> c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        b bVar;
        if (view == null) {
            bVar = new b();
            view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_sdk_item_list_dialog, viewGroup, false);
            bVar.b = (TextView) view2.findViewById(R.id.list_dialog_item_content);
            bVar.e = (RadioButton) view2.findViewById(R.id.list_dialog_item_radio);
            bVar.d = view2.findViewById(R.id.list_dialog_item_line);
            view2.setTag(bVar);
        } else {
            view2 = view;
            bVar = (b) view.getTag();
        }
        bVar.b.setText(getItem(i));
        bVar.e.setChecked(i == this.b);
        bVar.d.setVisibility(i == getCount() - 1 ? 8 : 0);
        return view2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    static class b {
        TextView b;
        View d;
        RadioButton e;

        b() {
        }
    }

    @Override // android.widget.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public CharSequence getItem(int i) {
        return this.c.get(i);
    }

    public e(CharSequence[] charSequenceArr, int i) {
        this.c = new ArrayList();
        if (charSequenceArr != null) {
            this.c = Arrays.asList(charSequenceArr);
        }
        this.b = i;
    }
}
