package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.nsy;
import defpackage.rsr;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class SourceSpinnerAdapter extends BaseAdapter {
    private List<Map<String, String>> e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SourceSpinnerAdapter(List<Map<String, String>> list) {
        this.e = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<Map<String, String>> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<Map<String, String>> list = this.e;
        if (list == null || koq.b(list, i)) {
            return new HashMap(16);
        }
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_spinner_item_with_icon, (ViewGroup) null);
            view.setBackground(null);
            eVar = new e(view);
            view.setTag(eVar);
        } else if (view.getTag() instanceof e) {
            eVar = (e) view.getTag();
        }
        if (eVar != null && koq.d(this.e, i)) {
            eVar.b(BaseApplication.getContext().getResources().getDimension(R.dimen._2131362955_res_0x7f0a048b), 0);
            eVar.c(this.e.get(i), false);
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        e eVar = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_spinner_item_with_icon, (ViewGroup) null);
            eVar = new e(view);
            view.setTag(eVar);
        } else if (view.getTag() instanceof e) {
            eVar = (e) view.getTag();
        }
        if (eVar != null && koq.d(this.e, i)) {
            eVar.c(this.e.get(i), true);
        }
        return view;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f10425a;
        private TextView b;
        private RelativeLayout c;

        e(View view) {
            this.c = (RelativeLayout) view.findViewById(R.id.spinner_item);
            this.f10425a = (ImageView) view.findViewById(R.id.spinner_icon);
            this.b = (TextView) view.findViewById(R.id.spinner_text);
        }

        void b(float f, int i) {
            nsy.cMx_(this.b, f, i);
        }

        void c(Map<String, String> map, boolean z) {
            String str = map.get("SpinnerItemIcon");
            String str2 = map.get("SpinnerItemText");
            if (!z) {
                this.c.setPadding(0, 0, 0, 0);
                this.f10425a.setVisibility(8);
                this.b.setText(str2);
                return;
            }
            Context context = this.c.getContext();
            this.c.setPadding(context.getResources().getDimensionPixelSize(R.dimen._2131362026_res_0x7f0a00ea), 0, context.getResources().getDimensionPixelSize(R.dimen._2131362026_res_0x7f0a00ea), 0);
            if (TextUtils.isEmpty(str)) {
                this.f10425a.setVisibility(8);
            } else {
                this.f10425a.setVisibility(0);
                try {
                    this.f10425a.setBackground(rsr.dQH_(Integer.parseInt(str)));
                } catch (NumberFormatException e) {
                    LogUtil.b("SourceSpinnerAdapter", e.getMessage());
                }
            }
            this.b.setText(str2);
        }
    }
}
