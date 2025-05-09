package com.huawei.ui.commonui.barlist;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import defpackage.nkq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthBarListView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f8767a;
    private BarViewAdapter b;
    private List<nkq> c;
    private LinearLayoutManager d;
    private int e;
    private TextView j;

    public HealthBarListView(Context context) {
        super(context);
        this.c = new ArrayList();
        this.f8767a = 0;
        e();
    }

    public HealthBarListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new ArrayList();
        this.f8767a = 0;
        e();
    }

    public HealthBarListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        this.f8767a = 0;
        e();
    }

    private void e() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.health_bar_list_view_layout, this);
        this.j = (TextView) inflate.findViewById(R.id.health_bar_list_title);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.health_bar_root);
        post(new Runnable() { // from class: com.huawei.ui.commonui.barlist.HealthBarListView.3
            @Override // java.lang.Runnable
            public void run() {
                HealthBarListView.this.cwV_(linearLayout, (HealthBarListView.this.getMeasuredWidth() - (HealthBarListView.this.getContext().getResources().getDimensionPixelSize(R.dimen._2131362839_res_0x7f0a0417) * 7)) / 7);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cwV_(LinearLayout linearLayout, int i) {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.d = new LinearLayoutManager(getContext(), 0, false);
        List<nkq> list = this.c;
        if (list != null && list.size() > 7 && this.f8767a > 7) {
            this.d.setStackFromEnd(true);
        }
        recyclerView.setLayoutManager(this.d);
        BarViewAdapter barViewAdapter = new BarViewAdapter(getContext(), i, this.e);
        this.b = barViewAdapter;
        recyclerView.setAdapter(barViewAdapter);
        setData(this.c, this.f8767a);
        linearLayout.addView(recyclerView);
    }

    public void setTitle(String str) {
        this.j.setText(str);
    }

    public void setBarColor(int i) {
        BarViewAdapter barViewAdapter = this.b;
        if (barViewAdapter == null) {
            this.e = i;
        } else {
            barViewAdapter.d(i);
        }
    }

    public void setData(List<nkq> list, int i) {
        if (this.b == null) {
            this.c.clear();
            this.c.addAll(list);
            this.f8767a = i;
            return;
        }
        if (list == null) {
            return;
        }
        if (list.size() > 7 && i > 7) {
            this.d.setStackFromEnd(true);
        }
        if (list.size() <= 7 || i <= 7) {
            this.b.e(list);
            return;
        }
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        int max = Math.max(i, 7);
        for (int i2 = 0; i2 < list.size(); i2++) {
            nkq nkqVar = list.get(i2);
            if (i2 < max) {
                arrayList.add(nkqVar);
            } else {
                arrayList2.add(nkqVar);
            }
        }
        this.b.e(arrayList);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huawei.ui.commonui.barlist.HealthBarListView.5
            @Override // java.lang.Runnable
            public void run() {
                HealthBarListView.this.b.d(arrayList2);
            }
        }, 200L);
    }
}
