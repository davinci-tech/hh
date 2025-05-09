package com.huawei.ui.commonui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class CheckAdapter extends BaseAdapter {
    private static boolean[] d;

    /* renamed from: a, reason: collision with root package name */
    private Context f8757a;
    private String[] c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private HealthCheckBox f8758a;
        private HealthTextView b;
        private ImageView c;

        private e() {
        }
    }

    public CheckAdapter(Context context, String[] strArr, boolean[] zArr) {
        if (strArr != null) {
            this.c = (String[]) strArr.clone();
        }
        d(zArr);
        if (context != null) {
            this.f8757a = context;
        } else {
            this.f8757a = BaseApplication.getContext();
        }
    }

    private static void d(boolean[] zArr) {
        if (zArr != null) {
            d = (boolean[]) zArr.clone();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return nsn.a(this.c);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return nsn.c(i, this.c);
    }

    public boolean[] e() {
        if (d == null) {
            LogUtil.a("CheckAdapter", "getCheckedItem return null");
            return null;
        }
        for (int i = 0; i < getCount(); i++) {
            d[i] = ((HealthCheckBox) getView(i, null, null).findViewById(R.id.chk_selectone)).isChecked();
        }
        return (boolean[]) d.clone();
    }

    public static class OnMultiItemClick implements AdapterView.OnItemClickListener {
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (CheckAdapter.d[i]) {
                CheckAdapter.d[i] = false;
            } else {
                CheckAdapter.d[i] = true;
            }
            if (adapterView.getAdapter() instanceof CheckAdapter) {
                ((CheckAdapter) adapterView.getAdapter()).notifyDataSetChanged();
                LogUtil.a("CheckAdapter", "Entering notifyDataSetChanged()");
            }
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        LogUtil.a("CheckAdapter", "Entering getView");
        e eVar = null;
        Object[] objArr = 0;
        if (view == null) {
            e eVar2 = new e();
            View inflate = LayoutInflater.from(this.f8757a).inflate(R.layout.commonui_dialog_multi_choice_item, (ViewGroup) null);
            eVar2.b = (HealthTextView) inflate.findViewById(R.id.contact_name);
            eVar2.c = (ImageView) inflate.findViewById(R.id.clock_line);
            eVar2.f8758a = (HealthCheckBox) inflate.findViewById(R.id.chk_selectone);
            inflate.setTag(eVar2);
            eVar = eVar2;
            view = inflate;
        } else if (view.getTag() instanceof e) {
            eVar = (e) view.getTag();
        }
        if (getItem(i) instanceof String) {
            eVar.b.setText((String) getItem(i));
            LogUtil.a("CheckAdapter", "Entering setText(name)");
            boolean[] zArr = d;
            if (zArr == null || zArr.length <= i) {
                eVar.f8758a.setChecked(false);
            } else {
                eVar.f8758a.setChecked(d[i]);
            }
            if (i == getCount() - 1) {
                eVar.c.setVisibility(8);
            } else {
                eVar.c.setVisibility(0);
            }
        }
        return view;
    }
}
