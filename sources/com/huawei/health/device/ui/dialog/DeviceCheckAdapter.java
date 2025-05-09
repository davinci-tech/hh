package com.huawei.health.device.ui.dialog;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.device.ui.dialog.DeviceCheckAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.cjv;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceCheckAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private int f2235a = 0;
    private List<cjv> b;
    private final OnItemClickListener c;
    private final Context e;

    public interface OnItemClickListener {
        void onItemClick(cjv cjvVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DeviceCheckAdapter(Context context, List<cjv> list, OnItemClickListener onItemClickListener) {
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
        this.c = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: Hz_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(this.e).inflate(R.layout.device_custom_select_dialog_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, final int i) {
        OnItemClickListener onItemClickListener;
        if (koq.b(this.b, i)) {
            LogUtil.h("DeviceCheckAdapter", "mDeviceGroupInfoList isOutOfBounds");
            return;
        }
        cjv cjvVar = this.b.get(i);
        if (cjvVar == null) {
            return;
        }
        Object i2 = cjvVar.i();
        if (!(i2 instanceof dcz)) {
            LogUtil.h("DeviceCheckAdapter", "mDeviceGroupInfoList ProductInfo is null");
            return;
        }
        dcz dczVar = (dcz) i2;
        cVar.f2236a.setImageBitmap(dcx.TK_(dcq.b().a(dczVar.t(), dczVar.n().d())));
        c(cjvVar, dczVar, cVar);
        boolean z = i == this.f2235a;
        cVar.b.setChecked(z);
        if (z && (onItemClickListener = this.c) != null) {
            onItemClickListener.onItemClick(cjvVar);
        }
        cVar.b.setOnClickListener(new View.OnClickListener() { // from class: ckx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceCheckAdapter.this.Hy_(i, view);
            }
        });
    }

    public /* synthetic */ void Hy_(int i, View view) {
        if (i != this.f2235a) {
            this.f2235a = i;
            notifyDataSetChanged();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(cjv cjvVar, dcz dczVar, c cVar) {
        String str;
        ContentValues FT_ = cjvVar.FT_();
        if (FT_ == null) {
            str = "";
        } else if (!TextUtils.isEmpty(FT_.getAsString("sn"))) {
            str = FT_.getAsString("sn");
        } else {
            str = FT_.getAsString("uniqueId");
        }
        if (dczVar.e().size() <= 0) {
            LogUtil.a("DeviceCheckAdapter", "item.getDescriptions().size() <= 0");
            cVar.e.setText(c(dczVar.n().b(), str));
            return;
        }
        LogUtil.a("DeviceCheckAdapter", "item.getDescriptions().size() > 0");
        String str2 = "9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(dczVar.t()) ? "" : str;
        if (!TextUtils.isEmpty(str2)) {
            cVar.e.setText(c(dcx.d(dczVar.t(), dczVar.n().b()), str2));
        } else {
            cVar.e.setText(dcx.d(dczVar.t(), dczVar.n().b()));
        }
    }

    private String c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceCheckAdapter", "name is empty");
            return "";
        }
        if (str.toUpperCase().contains(e(str2).toUpperCase())) {
            return str;
        }
        return str + e(str2).toUpperCase();
    }

    private String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceCheckAdapter", "getDeviceIdentification identification is null");
            return "";
        }
        if (str.replace(":", "").length() < 3) {
            LogUtil.a("DeviceCheckAdapter", "identification's length less than 3");
            return Constants.LINK + str.replace(":", "");
        }
        return Constants.LINK + str.replace(":", "").substring(str.replace(":", "").length() - 3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f2236a;
        HealthRadioButton b;
        HealthTextView e;

        c(View view) {
            super(view);
            this.f2236a = (ImageView) view.findViewById(R.id.img_device);
            this.e = (HealthTextView) view.findViewById(R.id.tv_device_name);
            this.b = (HealthRadioButton) view.findViewById(R.id.rb_device_select);
        }
    }
}
