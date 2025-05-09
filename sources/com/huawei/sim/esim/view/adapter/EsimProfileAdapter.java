package com.huawei.sim.esim.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nbp;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class EsimProfileAdapter extends BaseAdapter {
    private Context b;
    private ArrayList<nbp> d;
    private LayoutInflater e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    public EsimProfileAdapter(ArrayList<nbp> arrayList, Context context) {
        Object clone = arrayList.clone();
        if (koq.e(clone, nbp.class)) {
            this.d = (ArrayList) clone;
        } else {
            this.d = new ArrayList<>();
        }
        this.b = context;
        this.e = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.d.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.d.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        View inflate;
        if (i < 0 || i >= this.d.size()) {
            LogUtil.h("EsimProfileAdapter", "getView position is invalid");
            return null;
        }
        nbp nbpVar = this.d.get(i);
        if (nbpVar.a() == 0) {
            bVar = new b();
            inflate = this.e.inflate(R.layout.profile_item_normal, (ViewGroup) null);
            bVar.c = (HealthTextView) inflate.findViewById(R.id.profile_title_tips);
            bVar.d = (HealthTextView) inflate.findViewById(R.id.profile_title);
        } else {
            bVar = new b();
            inflate = this.e.inflate(R.layout.profile_item_image, (ViewGroup) null);
            bVar.c = (HealthTextView) inflate.findViewById(R.id.profile_title_tips);
            bVar.d = (HealthTextView) inflate.findViewById(R.id.profile_title);
            bVar.b = (ImageView) inflate.findViewById(R.id.profile_image);
        }
        if (nbpVar.a() == 0) {
            bVar.c.setText(nbpVar.e());
            bVar.d.setText(nbpVar.d());
        } else {
            bVar.c.setText(nbpVar.e());
            bVar.d.setText(nbpVar.d());
            bVar.b.setImageBitmap(csl_(nbpVar.c()));
        }
        return inflate;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return this.d.get(i).a();
    }

    private Bitmap csl_(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    static class b {
        ImageView b;
        HealthTextView c;
        HealthTextView d;

        b() {
        }
    }
}
