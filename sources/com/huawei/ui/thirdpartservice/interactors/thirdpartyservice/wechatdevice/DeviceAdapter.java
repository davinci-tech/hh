package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.wechatdevice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.wechatdevice.DeviceAdapter;
import defpackage.koq;
import defpackage.nrf;
import defpackage.sjy;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class DeviceAdapter extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private RecyclerView f10561a;
    private final Context b;
    private CallBack e;
    private List<sjy> d = new ArrayList();
    private int f = 0;
    private int c = 0;

    public interface CallBack {
        void onItemClicked(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
    }

    public DeviceAdapter(Context context, RecyclerView recyclerView) {
        this.b = context;
        this.f10561a = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dXT_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.item_wechat_device, viewGroup, false);
        if (this.c == 0) {
            inflate.setBackground(this.b.getDrawable(R.drawable.card_device_item_gradient_bg));
        } else {
            inflate.setBackgroundColor(this.b.getColor(R.color._2131297799_res_0x7f090607));
        }
        return new d(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final d dVar, int i, List<Object> list) {
        if (koq.b(this.d) || i >= this.d.size()) {
            return;
        }
        sjy sjyVar = this.d.get(i);
        if (this.c == 0) {
            dXQ_(dVar.d, sjyVar);
            dVar.c.setText(sjyVar.getDeviceName());
            dVar.b.setText(R.string.IDS_wx_device_local_unbound);
            dVar.b.setVisibility(sjyVar.isLocalBound() ? 8 : 0);
            dVar.f10562a.setVisibility(8);
            dVar.e.setVisibility(0);
            if (LanguageUtil.bc(this.b)) {
                dVar.e.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
            dVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: shw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceAdapter.this.dXR_(dVar, view);
                }
            });
        }
        if (this.c == 1) {
            if (list.isEmpty()) {
                dVar.d.setImageBitmap(sjyVar.getIcon());
                dVar.c.setText(sjyVar.getDeviceName());
            }
            dVar.b.setText(R.string.IDS_wx_device_connected_hint);
            dVar.b.setVisibility(sjyVar.isConnected() ? 0 : 8);
            dVar.e.setVisibility(8);
            dVar.f10562a.setVisibility(0);
            dVar.f10562a.setChecked(i == this.f);
            dVar.f10562a.setOnClickListener(new View.OnClickListener() { // from class: sib
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceAdapter.this.dXS_(dVar, view);
                }
            });
        }
    }

    public /* synthetic */ void dXR_(d dVar, View view) {
        int bindingAdapterPosition = dVar.getBindingAdapterPosition();
        this.f = bindingAdapterPosition;
        CallBack callBack = this.e;
        if (callBack != null) {
            callBack.onItemClicked(bindingAdapterPosition);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dXS_(d dVar, View view) {
        int bindingAdapterPosition = dVar.getBindingAdapterPosition();
        if (this.f == bindingAdapterPosition) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        dVar.f10562a.setChecked(true);
        int i = this.f;
        if (i != -1) {
            notifyItemChanged(i, 0);
        }
        this.f = bindingAdapterPosition;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dXQ_(ImageView imageView, sjy sjyVar) {
        if (sjyVar.isLocalBound() && sjyVar.getIcon() != null) {
            imageView.setImageBitmap(sjyVar.getIcon());
        } else {
            nrf.cIv_(sjyVar.getDeviceIconUrl(), RequestOptions.bitmapTransform(new RoundedCorners((int) this.b.getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238))), imageView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    public void d(int i) {
        this.c = i;
    }

    public void e(CallBack callBack) {
        this.e = callBack;
    }

    public void d(List<sjy> list) {
        this.d = list;
    }

    public int c() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthCheckBox f10562a;
        HealthTextView b;
        HealthTextView c;
        ImageView d;
        ImageView e;

        public d(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.device_img);
            this.c = (HealthTextView) view.findViewById(R.id.device_name);
            this.b = (HealthTextView) view.findViewById(R.id.device_bind_status);
            this.f10562a = (HealthCheckBox) view.findViewById(R.id.device_item_check);
            this.e = (ImageView) view.findViewById(R.id.device_item_arrow);
        }
    }
}
