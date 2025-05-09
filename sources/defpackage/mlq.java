package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.mls;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes6.dex */
public class mlq extends mls {

    public static class d extends mls.d {

        /* renamed from: a, reason: collision with root package name */
        View f15054a;
        LinearLayout c;
        HealthTextView d;
        ImageView e;
    }

    public mlq(Context context, Map<String, ArrayList<MedalInfoDesc>> map, Map<Integer, String> map2, Map<String, MedalInfo> map3) {
        super(context, map, map2, map3);
    }

    public View clg_(int i, boolean z, View view) {
        View view2;
        d dVar;
        if (view == null) {
            dVar = new d();
            view2 = LayoutInflater.from(this.f15055a).inflate(R.layout.achieve_medal_expandable_father_item, (ViewGroup) null);
            clf_(dVar, view2);
            view2.setTag(dVar);
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof d)) {
                return view;
            }
            d dVar2 = (d) tag;
            view2 = view;
            dVar = dVar2;
        }
        if (i == 0) {
            dVar.f15054a.setVisibility(8);
        } else {
            dVar.f15054a.setVisibility(4);
        }
        d(dVar, i, z);
        return view2;
    }

    private void d(d dVar, int i, boolean z) {
        String str = this.b.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        dVar.d.setText(str);
        if (z) {
            dVar.c.setBackground(this.c);
            dVar.e.setImageResource(R.drawable._2131430862_res_0x7f0b0dce);
        } else {
            dVar.c.setBackground(this.d);
            dVar.e.setImageResource(R.drawable._2131430857_res_0x7f0b0dc9);
        }
        ArrayList<MedalInfoDesc> arrayList = this.e.get(str);
        if (koq.b(arrayList)) {
            return;
        }
        if (arrayList.size() <= 3) {
            dVar.e.setVisibility(8);
        } else {
            dVar.e.setVisibility(0);
        }
        dVar.b.removeAllViews();
        if (arrayList.size() == 1) {
            dVar.b.addView(cla_(arrayList, 0));
            dVar.b.addView(clb_());
            dVar.b.addView(clb_());
        }
        if (arrayList.size() == 2) {
            dVar.b.addView(cla_(arrayList, 0));
            dVar.b.addView(cla_(arrayList, 1));
            dVar.b.addView(clb_());
        }
        if (arrayList.size() >= 3) {
            dVar.b.addView(cla_(arrayList, 0));
            dVar.b.addView(cla_(arrayList, 1));
            dVar.b.addView(cla_(arrayList, 2));
        }
    }

    private void clf_(d dVar, View view) {
        dVar.d = (HealthTextView) mfm.cgM_(view, R.id.medal_type_name);
        dVar.e = (ImageView) mfm.cgM_(view, R.id.group_arrow);
        dVar.c = (LinearLayout) mfm.cgM_(view, R.id.father_item_ll);
        dVar.f15054a = mfm.cgM_(view, R.id.fatherMargin);
        dVar.b = (GridLayout) mfm.cgM_(view, R.id.father_grid_layout);
    }
}
