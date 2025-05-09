package com.huawei.ui.device.views.selectcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.datatype.Contact;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.List;

/* loaded from: classes6.dex */
public class ContactMainListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<Contact> f9339a;
    private LayoutInflater c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ContactMainListAdapter(Context context, List<Contact> list) {
        this.f9339a = list;
        this.c = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f9339a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.f9339a.size() > i) {
            return this.f9339a.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = this.c.inflate(R.layout.activity_device_settings_contact_main_item_layout_black, (ViewGroup) null);
            eVar = new e();
            eVar.d = (HealthTextView) view.findViewById(R.id.content);
            eVar.b = (HealthTextView) view.findViewById(R.id.summary);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        try {
            Contact contact = this.f9339a.get(i);
            eVar.d.setText(contact.getName());
            if (contact.getPhoneNumbers() != null && contact.getPhoneNumbers().get(0) != null) {
                eVar.b.setText(contact.getPhoneNumbers().get(0).getPhoneNumber());
            } else {
                LogUtil.h("ContactMainListAdapter", "if (item.getPhoneNumbers() != null && item.getPhoneNumbers().get(0) != null) ELSE");
            }
            return view;
        } catch (IndexOutOfBoundsException e2) {
            LogUtil.b("ContactMainListAdapter", e2.getMessage());
            return null;
        }
    }

    static class e {
        HealthTextView b;
        HealthTextView d;

        private e() {
        }
    }
}
