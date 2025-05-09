package com.huawei.ui.device.views.selectcontact;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.datatype.Contact;
import com.huawei.datatype.PhoneNumber;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.device.activity.selectcontact.IUpdateListItemListener;
import com.huawei.ui.device.views.selectcontact.ManuallyAddContactAdapter;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ManuallyAddContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9340a;
    private List<Contact> b;
    private IUpdateListItemListener c;
    private FootViewHolder d;
    private int e;

    public interface EditTextChangeListener {
        void afterTextChanged(Editable editable);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ManuallyAddContactAdapter(Context context, List<Contact> list, IUpdateListItemListener iUpdateListItemListener, int i) {
        this.b = list;
        this.f9340a = context;
        this.c = iUpdateListItemListener;
        this.e = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.b.size() == i ? 2 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new d(LayoutInflater.from(this.f9340a).inflate(R.layout.manually_add_contact_list_item, viewGroup, false));
        }
        return new FootViewHolder(LayoutInflater.from(this.f9340a).inflate(R.layout.manually_add_foot_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof d) {
            d dVar = (d) viewHolder;
            if (this.b.size() > 1) {
                dVar.f9342a.setVisibility(0);
            } else {
                dVar.f9342a.setVisibility(8);
            }
            Drawable drawable = this.f9340a.getResources().getDrawable(R.drawable._2131430238_res_0x7f0b0b5e);
            drawable.setAutoMirrored(true);
            dVar.c.setImageDrawable(drawable);
            Contact contact = this.b.get(i);
            d(dVar, contact);
            e(dVar, contact);
            c(dVar, contact);
            return;
        }
        if (viewHolder instanceof FootViewHolder) {
            this.d = (FootViewHolder) viewHolder;
            c();
            this.d.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.selectcontact.ManuallyAddContactAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Contact contact2 = new Contact();
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(new PhoneNumber());
                    contact2.setPhoneNumbers(arrayList);
                    ManuallyAddContactAdapter.this.b.add(contact2);
                    ManuallyAddContactAdapter.this.notifyDataSetChanged();
                    ManuallyAddContactAdapter.this.c.updateSaveButtonState(true);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        LogUtil.a("ManuallyAddContactAdapter", "onBindViewHolder other");
    }

    private void d(d dVar, final Contact contact) {
        d(dVar.d, contact, new EditTextChangeListener() { // from class: oci
            @Override // com.huawei.ui.device.views.selectcontact.ManuallyAddContactAdapter.EditTextChangeListener
            public final void afterTextChanged(Editable editable) {
                ManuallyAddContactAdapter.this.cVP_(contact, editable);
            }
        });
    }

    public /* synthetic */ void cVP_(Contact contact, Editable editable) {
        if (TextUtils.isEmpty(editable.toString())) {
            contact.setName(null);
        } else {
            contact.setName(editable.toString());
        }
        c();
        b();
    }

    private void d(HealthEditText healthEditText, Contact contact, final EditTextChangeListener editTextChangeListener) {
        if (healthEditText.getTag() instanceof TextWatcher) {
            healthEditText.removeTextChangedListener((TextWatcher) healthEditText.getTag());
            healthEditText.clearFocus();
        }
        if (healthEditText.getId() == R.id.manually_add_name) {
            healthEditText.setText(contact.getName());
        } else {
            healthEditText.setText(e(contact));
        }
        TextWatcher textWatcher = new TextWatcher() { // from class: com.huawei.ui.device.views.selectcontact.ManuallyAddContactAdapter.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LogUtil.c("ManuallyAddContactAdapter", "beforeTextChanged");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LogUtil.c("ManuallyAddContactAdapter", "onTextChanged");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                editTextChangeListener.afterTextChanged(editable);
            }
        };
        healthEditText.addTextChangedListener(textWatcher);
        healthEditText.setTag(textWatcher);
    }

    public static String e(Contact contact) {
        List<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
        if (CollectionUtils.d(phoneNumbers)) {
            return null;
        }
        return phoneNumbers.get(0).getPhoneNumber();
    }

    private void e(d dVar, final Contact contact) {
        d(dVar.b, contact, new EditTextChangeListener() { // from class: ocl
            @Override // com.huawei.ui.device.views.selectcontact.ManuallyAddContactAdapter.EditTextChangeListener
            public final void afterTextChanged(Editable editable) {
                ManuallyAddContactAdapter.this.cVQ_(contact, editable);
            }
        });
    }

    public /* synthetic */ void cVQ_(Contact contact, Editable editable) {
        if (TextUtils.isEmpty(editable.toString())) {
            b(contact, null);
        } else {
            b(contact, editable.toString());
        }
        c();
        b();
    }

    private void b(Contact contact, String str) {
        List<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
        if (CollectionUtils.d(phoneNumbers)) {
            return;
        }
        phoneNumbers.get(0).setPhoneNumber(str);
    }

    private void c(d dVar, final Contact contact) {
        dVar.f9342a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.selectcontact.ManuallyAddContactAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ManuallyAddContactAdapter.this.b.remove(contact);
                ManuallyAddContactAdapter.this.notifyDataSetChanged();
                ManuallyAddContactAdapter.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c() {
        for (Contact contact : this.b) {
            if (TextUtils.isEmpty(contact.getName()) || TextUtils.isEmpty(e(contact))) {
                a(false);
                return;
            }
        }
        a(true);
    }

    private void a(boolean z) {
        if (!z || this.b.size() >= this.e) {
            this.d.e.setEnabled(false);
        } else {
            this.d.e.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        for (Contact contact : this.b) {
            if (!TextUtils.isEmpty(contact.getName()) && !TextUtils.isEmpty(e(contact))) {
                this.c.updateSaveButtonState(true);
                return;
            }
        }
        this.c.updateSaveButtonState(false);
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f9342a;
        private HealthEditText b;
        private ImageView c;
        private HealthEditText d;

        public d(View view) {
            super(view);
            this.d = (HealthEditText) nsy.cMd_(view, R.id.manually_add_name);
            this.b = (HealthEditText) nsy.cMd_(view, R.id.manually_add_number);
            this.c = (ImageView) nsy.cMd_(view, R.id.img_contact_phone_number);
            this.f9342a = (ImageView) nsy.cMd_(view, R.id.delete_add_item);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        private HealthButton e;

        public FootViewHolder(View view) {
            super(view);
            HealthButton healthButton = (HealthButton) nsy.cMd_(view, R.id.manual_add_contact_button);
            this.e = healthButton;
            healthButton.setEnabled(false);
        }
    }
}
