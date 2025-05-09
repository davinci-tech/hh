package com.huawei.ui.commonui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class SingleCheckAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f8762a;
    private String[] c;
    private boolean[] d;
    private LayoutInflater e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f8763a;
        private HealthRadioButton b;
        private HealthTextView d;

        private e() {
        }
    }

    public SingleCheckAdapter(Context context, String[] strArr, boolean[] zArr) {
        if (strArr != null) {
            this.c = (String[]) strArr.clone();
        }
        if (zArr != null) {
            this.d = (boolean[]) zArr.clone();
        }
        if (context == null) {
            this.f8762a = BaseApplication.getContext();
        } else {
            this.f8762a = context;
        }
        this.e = (LayoutInflater) this.f8762a.getSystemService("layout_inflater");
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return nsn.a(this.c);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return nsn.c(i, this.c);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = this.e.inflate(R.layout.commonui_dialog_single_choice_item, (ViewGroup) null);
            eVar = new e();
            eVar.d = (HealthTextView) view.findViewById(R.id.contact_name);
            eVar.f8763a = (ImageView) view.findViewById(R.id.line);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        eVar.b = (HealthRadioButton) view.findViewById(R.id.chk_selectone);
        if (i < 0) {
            return view;
        }
        boolean[] zArr = this.d;
        if (zArr != null && i < zArr.length) {
            eVar.b.setChecked(this.d[i]);
        }
        if (getItem(i) instanceof String) {
            String str = (String) getItem(i);
            if (eVar.d != null) {
                eVar.d.setText(str);
            }
            if (eVar.f8763a != null) {
                if (i == getCount() - 1) {
                    eVar.f8763a.setVisibility(8);
                } else {
                    eVar.f8763a.setVisibility(0);
                }
            }
        }
        return view;
    }
}
