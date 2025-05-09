package com.huawei.sim.multisim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes6.dex */
public class EsimOpenMethodAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private int f8719a;
    private OnRadioButtonClickListener b;
    private Context c;
    private int d = 0;
    private List<String> e;

    public interface OnRadioButtonClickListener {
        void onClick(int i, String str);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public EsimOpenMethodAdapter(Context context, List<String> list) {
        this.c = context;
        this.e = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<String> list = this.e;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<String> list = this.e;
        if (list == null) {
            LogUtil.h("EsimOpenMethodAdapter", "getItem mOpenMethodList is null");
            return null;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("EsimOpenMethodAdapter", "getItem position is out of range");
            return null;
        }
        return this.e.get(i);
    }

    public void e(OnRadioButtonClickListener onRadioButtonClickListener) {
        this.b = onRadioButtonClickListener;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar = null;
        if (view == null) {
            c cVar2 = new c();
            View inflate = LayoutInflater.from(this.c).inflate(R.layout.multi_sim_open_method_item, (ViewGroup) null);
            cVar2.f8720a = (LinearLayout) inflate.findViewById(R.id.open_method_item_layout);
            cVar2.c = (HealthTextView) inflate.findViewById(R.id.item_open_method_name);
            cVar2.d = (HealthTextView) inflate.findViewById(R.id.item_open_method_recommend);
            cVar2.e = (HealthTextView) inflate.findViewById(R.id.item_open_method_tips);
            cVar2.j = (HealthRadioButton) inflate.findViewById(R.id.item_radio_button);
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
            a(i, cVar);
        } else {
            LogUtil.h("EsimOpenMethodAdapter", "getView() holder is null");
        }
        return view;
    }

    private void a(final int i, c cVar) {
        List<String> list = this.e;
        if (list == null) {
            LogUtil.h("EsimOpenMethodAdapter", "setViewContent mOpenMethodList is null");
            return;
        }
        if (i == list.size() - 1) {
            LogUtil.a("EsimOpenMethodAdapter", "setViewContent position = ", Integer.valueOf(i));
            cVar.b.setVisibility(4);
        } else {
            cVar.b.setVisibility(0);
        }
        d(i, cVar);
        if (this.b == null) {
            cVar.f8720a.setClickable(false);
        } else {
            cVar.f8720a.setClickable(true);
            cVar.f8720a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.multisim.adapter.EsimOpenMethodAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    EsimOpenMethodAdapter.this.d = i;
                    if (EsimOpenMethodAdapter.this.b != null) {
                        int i2 = i;
                        if (i2 >= 0 && i2 < EsimOpenMethodAdapter.this.e.size()) {
                            EsimOpenMethodAdapter.this.b.onClick(i, (String) EsimOpenMethodAdapter.this.e.get(i));
                        } else {
                            LogUtil.h("EsimOpenMethodAdapter", "tempPosition is out of range");
                        }
                    }
                    EsimOpenMethodAdapter.this.notifyDataSetChanged();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        cVar.j.setChecked(i == this.d);
    }

    private void d(int i, c cVar) {
        if (i < 0 || i >= this.e.size()) {
            LogUtil.h("EsimOpenMethodAdapter", "position is out of range");
            return;
        }
        String str = this.e.get(i);
        cVar.d.setVisibility(8);
        if (str.equals(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            cVar.c.setText(R.string._2130847983_res_0x7f0228ef);
            if (!Utils.o()) {
                if (this.f8719a == 2) {
                    cVar.d.setVisibility(0);
                    if (LanguageUtil.bc(this.c)) {
                        cVar.d.setBackground(this.c.getResources().getDrawable(R.drawable._2131427605_res_0x7f0b0115));
                    } else {
                        cVar.d.setBackground(this.c.getResources().getDrawable(R.drawable._2131427604_res_0x7f0b0114));
                    }
                }
                cVar.c.setText(R.string._2130848137_res_0x7f022989);
            }
            cVar.e.setText(R.string._2130848106_res_0x7f02296a);
            return;
        }
        if (str.equals("06")) {
            cVar.c.setText(R.string._2130848052_res_0x7f022934);
            cVar.e.setText(R.string._2130848053_res_0x7f022935);
        } else {
            cVar.c.setText(R.string._2130848028_res_0x7f02291c);
            cVar.e.setText(R.string._2130848107_res_0x7f02296b);
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        LinearLayout f8720a;
        View b;
        HealthTextView c;
        HealthTextView d;
        HealthTextView e;
        HealthRadioButton j;

        c() {
        }
    }

    public void a(int i) {
        this.f8719a = i;
    }
}
