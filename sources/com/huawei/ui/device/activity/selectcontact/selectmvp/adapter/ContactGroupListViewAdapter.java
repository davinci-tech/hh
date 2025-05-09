package com.huawei.ui.device.activity.selectcontact.selectmvp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmn;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nxv;
import health.compact.a.util.LogUtil;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ContactGroupListViewAdapter extends BaseAdapter implements SectionIndexer {
    private Context b;
    private List<nxv> c;

    public ContactGroupListViewAdapter(List<nxv> list, Context context) {
        this.c = list;
        this.b = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i >= this.c.size()) {
            LogUtil.c("ContactGroupListViewAdapter", "getItem position out of list size");
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        if (i < this.c.size()) {
            return i;
        }
        LogUtil.c("ContactGroupListViewAdapter", "getItemId position out of list size");
        return -1L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar = null;
        Object[] objArr = 0;
        if (view == null) {
            c cVar2 = new c();
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.item_contact_select, (ViewGroup) null);
            cVar2.f9221a = (LinearLayout) inflate.findViewById(R.id.group_layout);
            cVar2.f = (HealthTextView) inflate.findViewById(R.id.text_grout_name);
            cVar2.b = (ImageView) inflate.findViewById(R.id.image_contact_photo);
            cVar2.c = (HealthTextView) inflate.findViewById(R.id.text_contact_name);
            cVar2.e = (ImageView) inflate.findViewById(R.id.check_image_view);
            cVar2.j = (HealthTextView) inflate.findViewById(R.id.text_contact_phone_number);
            cVar2.d = (RelativeLayout) inflate.findViewById(R.id.relative_contact_data);
            inflate.setTag(cVar2);
            cVar = cVar2;
            view = inflate;
        } else if (view.getTag() instanceof c) {
            cVar = (c) view.getTag();
        }
        if (i >= this.c.size()) {
            LogUtil.c("ContactGroupListViewAdapter", "getView position out of list size");
            return view;
        }
        b(i, cVar);
        return view;
    }

    private void b(int i, c cVar) {
        int dimensionPixelSize;
        if (cVar != null) {
            e(i, cVar);
            c(i, cVar);
            a(i, cVar);
            if (a(i)) {
                cVar.b.setVisibility(0);
                cVar.c.setVisibility(0);
                dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131363790_res_0x7f0a07ce);
            } else {
                dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131363775_res_0x7f0a07bf);
                cVar.b.setVisibility(4);
                cVar.c.setVisibility(8);
            }
            if (nsn.r()) {
                dimensionPixelSize = -2;
            }
            cVar.d.setLayoutParams(new LinearLayout.LayoutParams(-1, dimensionPixelSize));
            cVar.c.setText(this.c.get(i).d());
            StringBuilder sb = new StringBuilder(16);
            sb.append(this.c.get(i).c());
            sb.append(" ");
            sb.append(this.c.get(i).a());
            cVar.j.setText(sb.toString());
        }
    }

    private void e(int i, c cVar) {
        if (i >= this.c.size()) {
            LogUtil.c("ContactGroupListViewAdapter", "initItemGroup position out of list size");
            return;
        }
        if (i == getPositionForSection(getSectionForPosition(i))) {
            LogUtil.d("ContactGroupListViewAdapter", "item show title");
            cVar.f9221a.setVisibility(0);
            cVar.f.setText(this.c.get(i).g());
        } else {
            LogUtil.d("ContactGroupListViewAdapter", "item not show title");
            cVar.f9221a.setVisibility(8);
        }
    }

    private boolean a(int i) {
        if (i >= 1 && i < this.c.size()) {
            int i2 = i - 1;
            String b = this.c.get(i2).b();
            String b2 = this.c.get(i).b();
            if (this.c.get(i2).d().equals(this.c.get(i).d()) && b.equals(b2)) {
                return false;
            }
        }
        return true;
    }

    private void c(int i, c cVar) {
        if (i >= this.c.size()) {
            LogUtil.c("ContactGroupListViewAdapter", "initItemCheck position out of list size");
        } else if (this.c.get(i).h()) {
            cVar.e.setBackground(this.b.getResources().getDrawable(R.drawable._2131429864_res_0x7f0b09e8));
        } else {
            cVar.e.setBackground(this.b.getResources().getDrawable(R.drawable._2131429956_res_0x7f0b0a44));
        }
    }

    private void a(int i, c cVar) {
        if (i >= this.c.size()) {
            LogUtil.c("ContactGroupListViewAdapter", "initItemImage position out of list size");
            return;
        }
        Bitmap cBg_ = nmn.cBg_(nrf.cHA_(this.c.get(i).e()), true);
        if (cBg_ != null) {
            cVar.b.setImageBitmap(cBg_);
        } else {
            cVar.b.setImageDrawable(nmn.cBh_(BaseApplication.getContext().getResources(), new nmn.c(b(this.c.get(i).d()), this.c.get(i).b(), true)));
        }
    }

    private String b(String str) {
        return !TextUtils.isEmpty(str) ? str.substring(str.length() - 1, str.length()) : "";
    }

    @Override // android.widget.SectionIndexer
    public int getPositionForSection(int i) {
        for (int i2 = 0; i2 < getCount(); i2++) {
            if (this.c.get(i2).g().toUpperCase(Locale.ENGLISH).charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }

    @Override // android.widget.SectionIndexer
    public int getSectionForPosition(int i) {
        if (i >= this.c.size()) {
            return -1;
        }
        return this.c.get(i).g().charAt(0);
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        LinearLayout f9221a;
        ImageView b;
        HealthTextView c;
        RelativeLayout d;
        ImageView e;
        HealthTextView f;
        HealthTextView j;

        private c() {
        }
    }

    @Override // android.widget.SectionIndexer
    public Object[] getSections() {
        return new Object[0];
    }
}
