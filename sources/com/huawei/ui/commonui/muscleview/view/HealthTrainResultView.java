package com.huawei.ui.commonui.muscleview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.npu;
import defpackage.npw;
import defpackage.npx;
import defpackage.npy;
import defpackage.nqb;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthTrainResultView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTrainBodyView f8913a;
    private HealthTrainBodyView b;
    private View c;
    private View d;
    private List<npu> e;
    private HealthTrainBodyLineView f;
    private View i;
    private View j;

    public HealthTrainResultView(Context context) {
        super(context);
        cDY_(context, this);
    }

    public HealthTrainResultView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cDY_(context, this);
    }

    public HealthTrainResultView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cDY_(context, this);
    }

    private void cDY_(Context context, ViewGroup viewGroup) {
        if (context == null || viewGroup == null) {
            return;
        }
        LayoutInflater.from(context).inflate(R.layout.train_result_muscle_layout, viewGroup);
        this.b = (HealthTrainBodyView) findViewById(R.id.iv_positive);
        this.f8913a = (HealthTrainBodyView) findViewById(R.id.iv_negative);
        this.f = (HealthTrainBodyLineView) findViewById(R.id.train_line_view);
        this.i = findViewById(R.id.tv_relax_part_2);
        this.c = findViewById(R.id.tv_relax_part_1);
        this.j = findViewById(R.id.tv_relax_part_2_content);
        this.d = findViewById(R.id.tv_relax_part_1_content);
        this.e = new ArrayList();
    }

    public HealthTrainBodyView getIvPositive() {
        return this.b;
    }

    public HealthTrainBodyView getIvNegative() {
        return this.f8913a;
    }

    public HealthTrainBodyLineView getTrainBodyLineView() {
        return this.f;
    }

    public void setLineData(List<npu> list) {
        HealthTrainBodyLineView healthTrainBodyLineView = this.f;
        if (healthTrainBodyLineView == null) {
            return;
        }
        healthTrainBodyLineView.setLineData(list);
    }

    public void setFrontViewData(List<npy> list, List<npw> list2, SparseArray<Integer> sparseArray) {
        HealthTrainBodyView healthTrainBodyView;
        if (list == null || list2 == null || (healthTrainBodyView = this.b) == null) {
            LogUtil.h("HealthTrainResultView", "setFrontViewData input is null");
            return;
        }
        healthTrainBodyView.setFrontViewData(list, list2, sparseArray);
        if (sparseArray == null || sparseArray.size() == 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            if (sparseArray.keyAt(i3) > 28 || sparseArray.keyAt(i3) < 0) {
                LogUtil.h("HealthTrainResultView", "setFrontViewData, position is over 27 or below 0");
            } else if (i2 < sparseArray.valueAt(i3).intValue()) {
                i2 = sparseArray.valueAt(i3).intValue();
                i = sparseArray.keyAt(i3);
            }
        }
        npu npuVar = new npu();
        npuVar.d(getIvPositive());
        npuVar.c(i);
        npx e = nqb.c().e(getContext(), 1, i != 0 ? i - 1 : 0);
        if (e == null || e.c() == null || e.b() == null) {
            LogUtil.h("HealthTrainResultView", "setFrontViewData, info | muscleName | muscleFunction = null");
            return;
        }
        int identifier = getResources().getIdentifier(e.c(), "string", "com.huawei.health");
        int identifier2 = getResources().getIdentifier(e.b(), "string", "com.huawei.health");
        npuVar.cDO_(this.i);
        cDZ_(this.j, getResources().getString(identifier2));
        cDZ_(this.i, getResources().getString(identifier));
        this.e.add(npuVar);
    }

    public void setBackViewData(List<npy> list, List<npw> list2, SparseArray<Integer> sparseArray) {
        HealthTrainBodyView healthTrainBodyView;
        if (list == null || list2 == null || (healthTrainBodyView = this.f8913a) == null) {
            LogUtil.h("HealthTrainResultView", "setFrontViewData input is null");
            return;
        }
        healthTrainBodyView.setBackViewData(list, list2, sparseArray);
        if (sparseArray == null || sparseArray.size() == 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            if (sparseArray.keyAt(i3) > 23 || sparseArray.keyAt(i3) < 0) {
                LogUtil.h("HealthTrainResultView", "setBackViewData, position is over 27 or below 0");
            } else if (i2 < sparseArray.valueAt(i3).intValue()) {
                i2 = sparseArray.valueAt(i3).intValue();
                i = sparseArray.keyAt(i3);
            }
        }
        npu npuVar = new npu();
        npuVar.d(getIvNegative());
        npuVar.c(i);
        npx e = nqb.c().e(getContext(), 2, i != 0 ? i - 1 : 0);
        if (e == null || e.c() == null || e.b() == null) {
            LogUtil.h("HealthTrainResultView", "setBackViewData, info | muscleName | muscleFunction = null");
            return;
        }
        int identifier = getResources().getIdentifier(e.c(), "string", "com.huawei.health");
        int identifier2 = getResources().getIdentifier(e.b(), "string", "com.huawei.health");
        npuVar.cDO_(this.c);
        cDZ_(this.d, getResources().getString(identifier2));
        cDZ_(this.c, getResources().getString(identifier));
        this.e.add(npuVar);
        if (this.e.size() != 0) {
            setLineData(this.e);
        }
    }

    private void cDZ_(View view, String str) {
        if (view instanceof HealthTextView) {
            ((HealthTextView) view).setText(str);
        }
    }
}
