package com.huawei.ui.device.views.onelevelmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;
import defpackage.oai;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class OneLevelMenuAddListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<Integer, Boolean> f9335a;
    private LayoutInflater b;
    private List<Integer> d;
    private Context e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public static class b {
        private ImageView b;
        private HealthCheckBox d;
        private HealthTextView e;

        public HealthCheckBox b() {
            return this.d;
        }
    }

    public OneLevelMenuAddListAdapter(Context context, List<Integer> list) {
        this.d = list;
        this.e = context;
        a();
        this.b = LayoutInflater.from(this.e);
    }

    private void a() {
        if (f9335a == null) {
            f9335a = new HashMap<>(16);
        }
        for (int i = 0; i < this.d.size(); i++) {
            f9335a.put(Integer.valueOf(i), false);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.d.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i < 0 || i > this.d.size() - 1) {
            return null;
        }
        return this.d.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            int intValue = this.d.get(i).intValue();
            b bVar = new b();
            View inflate = this.b.inflate(R.layout.activity_one_level_add_menu_item_layout, (ViewGroup) null);
            bVar.e = (HealthTextView) nsy.cMd_(inflate, R.id.summary);
            bVar.d = (HealthCheckBox) nsy.cMd_(inflate, R.id.isCheckBox);
            bVar.b = (ImageView) nsy.cMd_(inflate, R.id.item_line);
            inflate.setTag(bVar);
            LogUtil.a("ContactDeleteListAdapter", "ContactDeleteListAdapter getView() arg0=", Integer.valueOf(i), ", mAddList.size()=", Integer.valueOf(this.d.size()));
            if (i == this.d.size() - 1) {
                bVar.b.setVisibility(8);
            } else {
                bVar.b.setVisibility(0);
            }
            bVar.e.setText(oai.a().a(this.e, intValue));
            HashMap<Integer, Boolean> hashMap = f9335a;
            if (hashMap != null && hashMap.get(Integer.valueOf(i)) != null) {
                bVar.d.setChecked(f9335a.get(Integer.valueOf(i)).booleanValue());
            }
            return inflate;
        } catch (IndexOutOfBoundsException e) {
            LogUtil.b("ContactDeleteListAdapter", e.getMessage());
            return null;
        }
    }

    public static HashMap<Integer, Boolean> c() {
        return f9335a;
    }
}
