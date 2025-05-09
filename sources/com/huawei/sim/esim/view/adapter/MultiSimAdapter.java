package com.huawei.sim.esim.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.nca;
import defpackage.nce;
import defpackage.ncf;
import defpackage.nsn;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/* loaded from: classes6.dex */
public class MultiSimAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private int f8700a;
    private Context b;
    private boolean c;
    private List<Map<String, Object>> d;
    private boolean e;
    private OnRadioButtonClickListener h;
    private String[] i;

    public interface OnRadioButtonClickListener {
        void onClick(int i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public MultiSimAdapter(Context context, List<Map<String, Object>> list) {
        this.f8700a = 0;
        this.c = false;
        this.b = context;
        this.d = list;
        this.e = false;
    }

    public MultiSimAdapter(Context context, String[] strArr) {
        this.f8700a = 0;
        this.c = false;
        this.b = context;
        e(strArr);
        this.e = true;
    }

    private void e(String[] strArr) {
        this.i = strArr == null ? new String[0] : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.e) {
            return this.i.length;
        }
        return this.d.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Optional.empty();
    }

    public void b(OnRadioButtonClickListener onRadioButtonClickListener) {
        this.h = onRadioButtonClickListener;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar = null;
        if (view == null) {
            c cVar2 = new c();
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.multi_sim_item, (ViewGroup) null);
            cVar2.d = (LinearLayout) inflate.findViewById(R.id.multi_sim_item_layout);
            cVar2.e = (RelativeLayout) inflate.findViewById(R.id.multi_sim_content_layout);
            cVar2.f8701a = (HealthTextView) inflate.findViewById(R.id.item_display_provider);
            cVar2.c = (HealthTextView) inflate.findViewById(R.id.item_phone_number);
            c(cVar2);
            cVar2.i = (HealthRadioButton) inflate.findViewById(R.id.item_radio_button);
            cVar2.b = inflate.findViewById(R.id.item_display_line);
            inflate.setTag(cVar2);
            cVar = cVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (tag instanceof c) {
                cVar = (c) tag;
            }
        }
        if (cVar != null) {
            if (this.e) {
                b(i, cVar);
            } else {
                e(i, cVar);
            }
        } else {
            LogUtil.h("MultiSimAdapter", "getView() SimViewHolder is null");
        }
        return view;
    }

    private void c(c cVar) {
        if (this.e) {
            cVar.e.setPadding(nsn.c(this.b, 12.0f), 0, nsn.c(this.b, 12.0f), 0);
        } else {
            cVar.e.setPadding(nsn.c(this.b, 12.0f), nsn.c(this.b, 8.0f), nsn.c(this.b, 12.0f), nsn.c(this.b, 8.0f));
        }
    }

    private void b(int i, c cVar) {
        String str = this.i[i];
        cVar.f8701a.setText(str);
        cVar.c.setVisibility(8);
        if (i == this.i.length - 1) {
            cVar.b.setVisibility(4);
        } else {
            cVar.b.setVisibility(0);
        }
        if (str.equals(this.b.getString(R.string._2130848142_res_0x7f02298e)) || str.equals(this.b.getString(R.string._2130848145_res_0x7f022991))) {
            cVar.i.setVisibility(4);
        } else {
            cVar.i.setVisibility(0);
            c(i, cVar);
        }
    }

    private void e(int i, c cVar) {
        Object obj = this.d.get(i).get("imsi");
        String str = obj instanceof String ? (String) obj : "";
        Object obj2 = this.d.get(i).get("number");
        String str2 = obj2 instanceof String ? (String) obj2 : "";
        int i2 = i + 1;
        String b = ncf.b(this.b, str);
        if (nce.d()) {
            nca e = nce.e(this.d.get(i));
            String d = d(e, b, i);
            if (e == null || TextUtils.isEmpty(e.a())) {
                a(i2, d, cVar);
                a(i, cVar);
                return;
            } else if (!TextUtils.isEmpty(e.a())) {
                if (i == 0) {
                    this.c = true;
                }
                if (!this.c) {
                    this.f8700a = i;
                }
                c(i2, d, cVar);
                a(str2, cVar);
                c(i, cVar);
            } else {
                b(i2, d, cVar);
            }
        } else {
            c(i2, b, cVar);
            a(str2, cVar);
            c(i, cVar);
        }
        a(i, cVar);
    }

    private void a(int i, String str, c cVar) {
        cVar.d.setClickable(false);
        cVar.f8701a.setVisibility(8);
        cVar.i.setVisibility(8);
        String format = String.format(Locale.ENGLISH, this.b.getResources().getString(R.string._2130847987_res_0x7f0228f3), Integer.valueOf(i), str);
        cVar.c.setVisibility(0);
        cVar.c.setTextDirection(5);
        cVar.c.setText(String.format(Locale.ENGLISH, this.b.getResources().getString(R.string._2130848045_res_0x7f02292d), format));
    }

    private void a(int i, c cVar) {
        if (i == this.d.size() - 1) {
            cVar.b.setVisibility(4);
        } else {
            cVar.b.setVisibility(0);
        }
    }

    private String d(nca ncaVar, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            Object obj = this.d.get(i).get("spn");
            if (obj instanceof String) {
                str = (String) obj;
            }
        }
        return (!TextUtils.isEmpty(str) || ncaVar == null) ? str : ncaVar.b();
    }

    private void c(int i, String str, c cVar) {
        cVar.f8701a.setVisibility(0);
        cVar.f8701a.setText(String.format(Locale.ENGLISH, this.b.getResources().getString(R.string._2130847987_res_0x7f0228f3), Integer.valueOf(i), str));
    }

    private void a(String str, c cVar) {
        if (TextUtils.isEmpty(str)) {
            cVar.c.setVisibility(8);
        } else {
            cVar.c.setText(str);
            cVar.c.setVisibility(0);
        }
    }

    private void b(int i, String str, c cVar) {
        cVar.d.setClickable(false);
        cVar.f8701a.setVisibility(8);
        cVar.i.setVisibility(8);
        String format = String.format(Locale.ENGLISH, this.b.getResources().getString(R.string._2130847987_res_0x7f0228f3), Integer.valueOf(i), str);
        cVar.c.setVisibility(0);
        cVar.c.setTextDirection(5);
        cVar.c.setText(String.format(Locale.ENGLISH, this.b.getResources().getString(R.string._2130848030_res_0x7f02291e), format));
    }

    private void c(final int i, c cVar) {
        cVar.i.setVisibility(0);
        if (this.h == null) {
            LogUtil.a("MultiSimAdapter", "setRadioButton mListener is null");
            cVar.d.setClickable(false);
        } else {
            cVar.d.setClickable(true);
            cVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.adapter.MultiSimAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MultiSimAdapter.this.f8700a = i;
                    MultiSimAdapter.this.h.onClick(i);
                    MultiSimAdapter.this.notifyDataSetChanged();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        cVar.i.setChecked(i == this.f8700a);
    }

    public int e() {
        return this.f8700a;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f8701a;
        View b;
        HealthTextView c;
        LinearLayout d;
        RelativeLayout e;
        HealthRadioButton i;

        c() {
        }
    }
}
