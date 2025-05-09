package com.huawei.health.ecologydevice.ui.measure.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceSilentGuideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private String f2328a;
    private List<dcz.d> b;
    private Context e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DeviceSilentGuideAdapter(Context context, String str, List<dcz.d> list) {
        if (list != null) {
            this.b = list;
        } else {
            this.b = new ArrayList(16);
        }
        if (context == null) {
            this.e = BaseApplication.getContext();
        } else {
            this.e = context;
        }
        this.f2328a = str;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(this.e).inflate(R.layout.device_silent_guid_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        dcz.d dVar;
        if (!(viewHolder instanceof c) || (dVar = this.b.get(i)) == null) {
            return;
        }
        c cVar = (c) viewHolder;
        cVar.b.setText(dcx.d(this.f2328a, dVar.c()));
        if (TextUtils.isEmpty(dVar.e())) {
            cVar.d.setVisibility(8);
            return;
        }
        c(cVar);
        Bitmap TK_ = dcx.TK_(dcq.b().a(this.f2328a, dVar.e()));
        if (TK_ != null) {
            cVar.d.setVisibility(0);
            cVar.c.setImageBitmap(TK_);
        } else {
            cVar.d.setVisibility(8);
        }
    }

    private void c(c cVar) {
        int c2 = nsn.c(this.e, 240.0f);
        cVar.c.setLayoutParams(new LinearLayout.LayoutParams(c2, c2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    class c extends RecyclerView.ViewHolder {
        HealthTextView b;
        ImageView c;
        LinearLayout d;

        c(View view) {
            super(view);
            this.c = (ImageView) view.findViewById(R.id.hw_device_silent_img);
            this.b = (HealthTextView) view.findViewById(R.id.hw_device_silent_text);
            this.d = (LinearLayout) view.findViewById(R.id.hw_device_auto_read_omron_introduction_step);
        }
    }
}
