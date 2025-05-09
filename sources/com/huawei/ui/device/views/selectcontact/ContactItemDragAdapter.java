package com.huawei.ui.device.views.selectcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.datatype.Contact;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class ContactItemDragAdapter extends RecyclerView.Adapter<SlideViewHolder> implements ContactItemDragListener {
    private final LayoutInflater b;
    private List<Contact> c;
    private Context d;

    public ContactItemDragAdapter(Context context, List<Contact> list) {
        this.d = context;
        this.c = list;
        this.b = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cVO_, reason: merged with bridge method [inline-methods] */
    public SlideViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SlideViewHolder(this.b.inflate(R.layout.activity_device_settings_contact_orderby_item_layout_black, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SlideViewHolder slideViewHolder, int i) {
        Contact contact = this.c.get(i);
        slideViewHolder.f9338a.setText(contact.getName());
        slideViewHolder.d.setText(contact.getPhoneNumbers().get(0).getPhoneNumber());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @Override // com.huawei.ui.device.views.selectcontact.ContactItemDragListener
    public void onItemDrag(int i, int i2) {
        List<Contact> list = this.c;
        if (list == null) {
            LogUtil.h("ContactItemDragAdapter", "onItemDrag mContactList is null");
            return;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("ContactItemDragAdapter", "onItemDrag fromPosition IndexOutOfBoundsException");
        } else if (i2 < 0 || i2 >= this.c.size()) {
            LogUtil.h("ContactItemDragAdapter", "onItemDrag toPosition IndexOutOfBoundsException");
        } else {
            Collections.swap(this.c, i, i2);
            notifyItemMoved(i, i2);
        }
    }

    public static class SlideViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f9338a;
        private HealthTextView d;

        public SlideViewHolder(View view) {
            super(view);
            this.f9338a = (HealthTextView) view.findViewById(R.id.contact_name_tv);
            this.d = (HealthTextView) view.findViewById(R.id.contact_phone_number_tv);
        }
    }
}
