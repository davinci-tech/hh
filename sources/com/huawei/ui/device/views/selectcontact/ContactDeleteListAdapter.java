package com.huawei.ui.device.views.selectcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.datatype.Contact;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ContactDeleteListAdapter extends BaseAdapter {
    private static HashMap<Integer, Boolean> d = new HashMap<>(16);

    /* renamed from: a, reason: collision with root package name */
    private List<Contact> f9336a;
    private LayoutInflater e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f9337a;
        public HealthCheckBox b;
        private HealthTextView d;
        private HealthTextView e;
    }

    public ContactDeleteListAdapter(Context context, List<Contact> list) {
        this.f9336a = list;
        e();
        this.e = LayoutInflater.from(context);
    }

    private void e() {
        for (int i = 0; i < this.f9336a.size(); i++) {
            d.put(Integer.valueOf(i), false);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f9336a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.f9336a.size() > i) {
            return this.f9336a.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        d dVar;
        try {
            Contact contact = this.f9336a.get(i);
            LogUtil.a("ContactDeleteListAdapter", "ContactDeleteListAdapter getView() vie=", view, ", item=", contact);
            if (view == null) {
                dVar = new d();
                view2 = this.e.inflate(R.layout.activity_device_settings_contact_delete_item_layout_black, (ViewGroup) null);
                dVar.d = (HealthTextView) nsy.cMd_(view2, R.id.content);
                dVar.e = (HealthTextView) nsy.cMd_(view2, R.id.summary);
                dVar.b = (HealthCheckBox) nsy.cMd_(view2, R.id.isCheckBox);
                dVar.f9337a = (ImageView) nsy.cMd_(view2, R.id.item_layout_line);
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
            if (i == this.f9336a.size() - 1) {
                dVar.f9337a.setVisibility(8);
            } else {
                dVar.f9337a.setVisibility(0);
            }
            dVar.d.setText(contact.getName());
            dVar.e.setText(contact.getPhoneNumbers().get(0).getPhoneNumber());
            dVar.b.setChecked(d.get(Integer.valueOf(i)).booleanValue());
            return view2;
        } catch (IndexOutOfBoundsException e) {
            LogUtil.b("ContactDeleteListAdapter", e.getMessage());
            return null;
        }
    }

    public static HashMap<Integer, Boolean> b() {
        return d;
    }
}
