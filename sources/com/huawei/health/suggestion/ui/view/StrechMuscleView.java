package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.view.StrechMuscleView;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.muscleview.view.HealthTrainResultView;
import defpackage.asc;
import defpackage.npw;
import defpackage.npy;
import defpackage.nqb;
import java.util.List;

/* loaded from: classes4.dex */
public class StrechMuscleView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f3426a;
    private List<npy> b;
    private HealthTextView c;
    private List<npy> d;
    private List<npw> e;
    private List<npw> h;
    private View i;
    private HealthTrainResultView j;

    public StrechMuscleView(Context context) {
        this(context, null);
    }

    public StrechMuscleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StrechMuscleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3426a = context;
        c();
    }

    private void c() {
        View inflate = View.inflate(this.f3426a, R.layout.strech_part_layout, this);
        this.i = inflate;
        this.j = (HealthTrainResultView) inflate.findViewById(R.id.muscle_result);
        this.c = (HealthTextView) this.i.findViewById(R.id.strech_part_subheader_illustration);
    }

    private void b() {
        this.h = nqb.c().b(getContext(), 1);
        this.e = nqb.c().b(getContext(), 2);
        this.b = nqb.c().d(getContext(), 1);
        this.d = nqb.c().d(getContext(), 2);
    }

    public void setData(final SparseArray<Integer> sparseArray, final SparseArray<Integer> sparseArray2, int i) {
        b();
        if (i < 60) {
            this.c.setText(getResources().getString(R$string.IDS_fitness_strechpart_low));
        } else if (i >= 80) {
            this.c.setText(getResources().getString(R$string.IDS_fitness_strechpart_high));
        } else {
            this.c.setText(getResources().getString(R$string.IDS_fitness_strechpart_middle));
        }
        asc.e().b(new Runnable() { // from class: gfn
            @Override // java.lang.Runnable
            public final void run() {
                StrechMuscleView.this.aMq_(sparseArray, sparseArray2);
            }
        });
    }

    public /* synthetic */ void aMq_(SparseArray sparseArray, SparseArray sparseArray2) {
        this.j.setFrontViewData(this.b, this.h, sparseArray);
        this.j.setBackViewData(this.d, this.e, sparseArray2);
    }
}
