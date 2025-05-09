package com.huawei.hms.iapfull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hms.iapfull.network.model.MyPayType;
import java.util.List;

/* loaded from: classes4.dex */
public class m0 extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4725a;
    private List<MyPayType> b;
    private int c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        TextView textView;
        int i2;
        if (view == null) {
            view = LayoutInflater.from(this.f4725a).inflate(R.layout.pay_type_channel_full_item, viewGroup, false);
            aVar = new a();
            aVar.f4726a = (ImageView) view.findViewById(R.id.cardButton);
            aVar.b = (TextView) view.findViewById(R.id.paytype_title);
            aVar.c = (RadioButton) view.findViewById(R.id.radiobutton);
            aVar.d = (TextView) view.findViewById(R.id.paytype_descr);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.c.setChecked(false);
        int mode = this.b.get(i).getMode();
        int payType = this.b.get(i).getPayType();
        aVar.d.setVisibility(8);
        if (5 == payType) {
            aVar.f4726a.setBackgroundResource(R.drawable._2131429682_res_0x7f0b0932);
            textView = aVar.b;
            i2 = R.string._2130851255_res_0x7f0235b7;
        } else {
            aVar.f4726a.setBackgroundResource(R.drawable._2131429683_res_0x7f0b0933);
            textView = aVar.b;
            i2 = R.string._2130851293_res_0x7f0235dd;
        }
        textView.setText(i2);
        int i3 = this.c;
        if (i3 == i) {
            if (1 == mode) {
                aVar.c.setChecked(true);
            } else {
                this.c = i3 + 1;
            }
        }
        if (1 != mode) {
            aVar.c.setEnabled(false);
            aVar.b.setAlpha(0.38f);
            aVar.f4726a.setAlpha(0.38f);
            aVar.d.setAlpha(0.38f);
            aVar.d.setVisibility(0);
            aVar.d.setTextColor(this.f4725a.getResources().getColor(R.color._2131297370_res_0x7f09045a));
            aVar.d.setText(R.string._2130851271_res_0x7f0235c7);
        }
        return view;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<MyPayType> list = this.b;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.b.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<MyPayType> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void a(List<MyPayType> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        ImageView f4726a;
        TextView b;
        RadioButton c;
        TextView d;

        a() {
        }
    }

    public void a(int i) {
        List<MyPayType> list = this.b;
        if (list == null || list.isEmpty() || i >= this.b.size()) {
            return;
        }
        this.c = i;
    }

    public m0(Context context, List<MyPayType> list, int i) {
        this.f4725a = context;
        this.b = list;
        this.c = i;
    }
}
