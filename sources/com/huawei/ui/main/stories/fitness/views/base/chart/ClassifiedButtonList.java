package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.health.R;
import com.huawei.ui.commonui.subtab.HealthSubTabListener;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList;
import defpackage.koq;
import defpackage.smy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ClassifiedButtonList extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9931a;
    private OnSwitchClassifies b;
    private List<smy> c;
    private a d;
    private HealthSubTabWidget e;
    private List<ClassifiedViewList.ClassifiedView> f;

    public interface OnSwitchClassifies {
        void onSwitchClassifies(ClassifiedViewList.ClassifiedView classifiedView);
    }

    public ClassifiedButtonList(Context context) {
        super(context);
        this.c = new ArrayList(4);
        this.f9931a = false;
    }

    public ClassifiedButtonList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new ArrayList(4);
        this.f9931a = false;
        dvu_(attributeSet);
    }

    public ClassifiedButtonList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList(4);
        this.f9931a = false;
        dvu_(attributeSet);
    }

    public void c(int i) {
        if (this.e != null) {
            removeAllViews();
        }
        HealthSubTabWidget healthSubTabWidget = (HealthSubTabWidget) LayoutInflater.from(getContext()).inflate(i, (ViewGroup) this, false);
        this.e = healthSubTabWidget;
        addView(healthSubTabWidget);
        this.d = new a();
        int i2 = 0;
        while (i2 < 4) {
            smy smyVar = new smy(this.e, " ");
            this.c.add(smyVar);
            this.e.a(smyVar, i2 == 0);
            smyVar.d(this.d);
            i2++;
        }
        this.d.b();
    }

    private void dvu_(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099995_res_0x7f06015b});
        this.f9931a = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        if (this.f9931a) {
            return;
        }
        c(R.layout.knit_sub_tab_widget_default);
    }

    public boolean a() {
        return this.f9931a;
    }

    public void setDeferSubTabWidgetInflating(boolean z) {
        this.f9931a = z;
    }

    public void d(int i) {
        if (this.b == null || !koq.d(this.f, i)) {
            return;
        }
        this.b.onSwitchClassifies(this.f.get(i));
    }

    public void a(List<ClassifiedViewList.ClassifiedView> list, OnSwitchClassifies onSwitchClassifies) {
        if (list.size() != this.e.getSubTabCount()) {
            return;
        }
        this.f = list;
        this.b = onSwitchClassifies;
        this.e.h();
        int i = 0;
        while (i < list.size()) {
            smy smyVar = new smy(this.e, list.get(i).getClassStr());
            this.e.a(smyVar, i == 0);
            smyVar.d(this.d);
            i++;
        }
    }

    public void e(int i) {
        this.e.setSubTabSelected(i);
        this.e.setSubTabScrollingOffsets(i, 0.0f);
    }

    public class a implements HealthSubTabListener {
        private boolean d = false;

        @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
        public void onSubTabReselected(smy smyVar, FragmentTransaction fragmentTransaction) {
        }

        @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
        public void onSubTabUnselected(smy smyVar, FragmentTransaction fragmentTransaction) {
        }

        public a() {
        }

        public void b() {
            this.d = true;
        }

        @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
        public void onSubTabSelected(smy smyVar, FragmentTransaction fragmentTransaction) {
            if (this.d) {
                ClassifiedButtonList.this.a(smyVar);
            }
        }
    }

    protected void a(smy smyVar) {
        List<ClassifiedViewList.ClassifiedView> list;
        for (int i = 0; i < this.e.getSubTabCount(); i++) {
            smy a2 = this.e.a(i);
            if (a2 != null && a2.equals(smyVar) && (list = this.f) != null) {
                this.b.onSwitchClassifies(list.get(i));
                return;
            }
        }
    }
}
