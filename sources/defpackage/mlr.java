package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import com.huawei.health.R;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import defpackage.mls;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes6.dex */
public class mlr extends mls {
    public mlr(Context context, Map<String, ArrayList<MedalInfoDesc>> map, Map<Integer, String> map2, Map<String, MedalInfo> map3) {
        super(context, map, map2, map3);
    }

    public View cle_(int i, View view) {
        View view2;
        e eVar;
        if (view == null) {
            eVar = new e();
            view2 = LayoutInflater.from(this.f15055a).inflate(R.layout.achieve_medal_expandable_child_item, (ViewGroup) null);
            cld_(eVar, view2);
            view2.setTag(eVar);
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof e)) {
                return view;
            }
            e eVar2 = (e) tag;
            view2 = view;
            eVar = eVar2;
        }
        eVar.b.removeAllViews();
        String str = this.b.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str)) {
            return view2;
        }
        ArrayList<MedalInfoDesc> arrayList = this.e.get(str);
        if (!koq.b(arrayList) && arrayList.size() > 3) {
            c(eVar, arrayList);
        }
        return view2;
    }

    private void c(e eVar, ArrayList<MedalInfoDesc> arrayList) {
        for (int i = 3; i < arrayList.size(); i++) {
            eVar.b.addView(cla_(arrayList, i));
        }
        if (arrayList.size() == 4) {
            eVar.b.addView(clb_());
            eVar.b.addView(clb_());
        }
        if (arrayList.size() == 5) {
            eVar.b.addView(clb_());
        }
    }

    private void cld_(e eVar, View view) {
        eVar.b = (GridLayout) mfm.cgM_(view, R.id.child_grid_layout);
    }

    static class e extends mls.d {
        private e() {
        }
    }
}
