package com.huawei.basichealthmodel.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.basichealthmodel.ui.adapter.TaskDialogGridAdapter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.azi;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nsf;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class TaskDialogGridAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1917a;
    private final int b;
    private final ArrayList<Drawable> c;
    private boolean d;
    private final int e;
    private final boolean j;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public TaskDialogGridAdapter(ArrayList<Drawable> arrayList, int i, boolean z) {
        Context e = BaseApplication.e();
        this.f1917a = e;
        Resources resources = e.getResources();
        this.c = arrayList;
        this.j = z;
        double dimensionPixelSize = (i - resources.getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2)) - resources.getDimensionPixelSize(R.dimen._2131362465_res_0x7f0a02a1);
        if (z) {
            int b2 = (int) azi.b(((dimensionPixelSize - resources.getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446)) - (resources.getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516) * 7)) / 8.0d, -1);
            this.b = b2;
            this.e = b2;
        } else {
            int b3 = (int) azi.b(((dimensionPixelSize - resources.getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d)) - (resources.getDimensionPixelSize(R.dimen._2131362869_res_0x7f0a0435) * 5)) / 6.0d, -1);
            this.b = b3;
            this.e = b3 - resources.getDimensionPixelSize(R.dimen._2131362869_res_0x7f0a0435);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (koq.b(this.c)) {
            return 0;
        }
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.c, i)) {
            return null;
        }
        return this.c.get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (koq.b(this.c, i)) {
            LogUtil.h("R_HealthLife_TaskDialogGridAdapter", "getView mDrawableList is out of bounds mDrawableList ", this.c, " position ", Integer.valueOf(i));
            return view;
        }
        b bVar = null;
        Object[] objArr = 0;
        if (view == null) {
            b bVar2 = new b();
            View inflate = LayoutInflater.from(this.f1917a).inflate(R.layout.item_task_dialog_grid, viewGroup, false);
            bVar2.c = (FrameLayout) inflate.findViewById(R.id.item_task_dialog_grid_background);
            bVar2.e = (ImageView) inflate.findViewById(R.id.item_task_dialog_grid_foreground);
            bVar2.f1918a = (HealthTextView) inflate.findViewById(R.id.item_task_dialog_grid_plan);
            inflate.setTag(bVar2);
            bVar = bVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (tag instanceof b) {
                bVar = (b) tag;
            }
        }
        if (bVar == null) {
            LogUtil.h("R_HealthLife_TaskDialogGridAdapter", "getView viewHolder is null");
            return view;
        }
        ViewGroup.LayoutParams layoutParams = bVar.c.getLayoutParams();
        layoutParams.width = this.b;
        layoutParams.height = this.b;
        bVar.c.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = bVar.e.getLayoutParams();
        layoutParams2.width = this.e;
        layoutParams2.height = this.e;
        bVar.e.setLayoutParams(layoutParams2);
        bVar.e.setPivotX(this.e / 2.0f);
        if (this.j) {
            bVar.e.setPivotY(this.e);
            jcf.bEE_(view, 4);
        } else {
            bVar.e.setPivotY(this.e / 2.0f);
        }
        bVar.e.setImageDrawable(this.c.get(i));
        final HealthTextView healthTextView = bVar.f1918a;
        e(healthTextView, i);
        healthTextView.a(new HealthTextView.TextLineListener() { // from class: aye
            @Override // com.huawei.ui.commonui.healthtextview.HealthTextView.TextLineListener
            public final void onCallback() {
                TaskDialogGridAdapter.b(HealthTextView.this);
            }
        });
        return view;
    }

    public static /* synthetic */ void b(HealthTextView healthTextView) {
        int textLine = healthTextView.getTextLine();
        if (textLine <= 1) {
            return;
        }
        ReleaseLogUtil.b("R_HealthLife_TaskDialogGridAdapter", "getView onCallback textLine ", Integer.valueOf(textLine));
        healthTextView.setTextSize(0, nsf.b(R.dimen._2131363136_res_0x7f0a0540));
        healthTextView.a((HealthTextView.TextLineListener) null);
    }

    private void e(HealthTextView healthTextView, int i) {
        if (this.d) {
            healthTextView.setVisibility(0);
            if (i == 0) {
                healthTextView.setText(this.f1917a.getString(R$string.IDS_blood_pressure_get_up));
            } else if (i == getCount() - 1) {
                healthTextView.setText(this.f1917a.getString(R$string.IDS_blood_pressure_before_sleep));
            } else {
                healthTextView.setText(this.f1917a.getString(R$string.IDS_blood_pressure_customize_title, Integer.valueOf(i)));
            }
        }
    }

    public void b(boolean z) {
        this.d = z;
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f1918a;
        private FrameLayout c;
        private ImageView e;

        private b() {
        }
    }
}
