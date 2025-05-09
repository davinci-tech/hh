package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.main.stories.health.adapter.SelectUserAdapter;
import defpackage.cfi;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import health.compact.a.utils.StringUtils;
import java.util.List;

/* loaded from: classes8.dex */
public class SelectUserAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f10124a;
    private int b = -1;
    private int d = -1;
    private List<cfi> e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SelectUserAdapter(Context context, List<cfi> list) {
        this.e = list;
        this.f10124a = context;
    }

    public void c(int i) {
        this.b = i;
        this.d = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<cfi> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.e, i)) {
            return null;
        }
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            e eVar2 = new e();
            View inflate = LayoutInflater.from(this.f10124a).inflate(R.layout.item_single_select, (ViewGroup) null);
            eVar2.d = (HealthTextView) inflate.findViewById(R.id.tv_claim_weight_data_select_user);
            eVar2.b = (HealthTextView) inflate.findViewById(R.id.tv_select_current);
            eVar2.c = (HealthRadioButton) inflate.findViewById(R.id.rb_claim_weight_data_select_user);
            inflate.setTag(eVar2);
            eVar = eVar2;
            view = inflate;
        } else {
            eVar = (e) view.getTag();
        }
        if (koq.d(this.e, i)) {
            String h = this.e.get(i).h();
            if (LanguageUtil.bc(BaseApplication.getContext()) && !TextUtils.isEmpty(h) && (StringUtils.b(h) || StringUtils.d(h))) {
                eVar.d.setTextDirection(3);
            }
            if (this.b == i) {
                eVar.b.setVisibility(0);
                eVar.d.setText(h + " ");
            } else {
                eVar.b.setVisibility(8);
                eVar.d.setText(h);
            }
        }
        eVar.c.setOnClickListener(new View.OnClickListener() { // from class: qgj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SelectUserAdapter.this.dCE_(i, view2);
            }
        });
        eVar.c.setChecked(i == this.d);
        return view;
    }

    public /* synthetic */ void dCE_(int i, View view) {
        if (this.d == i) {
            i = this.b;
        }
        this.d = i;
        notifyDataSetChanged();
        ViewClickInstrumentation.clickOnView(view);
    }

    public int c() {
        return this.d;
    }

    static class e {
        private HealthTextView b;
        private HealthRadioButton c;
        private HealthTextView d;

        private e() {
        }
    }
}
