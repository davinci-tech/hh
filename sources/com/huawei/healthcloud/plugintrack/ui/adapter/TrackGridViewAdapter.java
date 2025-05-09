package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class TrackGridViewAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Map<TrackMainFragmentShowType, b> f3706a;
    private Context b;
    private boolean d;
    private List<b> e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public TrackGridViewAdapter(Context context, List<b> list) {
        this.b = context;
        this.e = list;
        this.f3706a = e(list);
    }

    public TrackGridViewAdapter(Context context, List<b> list, boolean z) {
        this(context, list);
        this.d = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<b> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.e, i)) {
            return null;
        }
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (koq.b(this.e, i)) {
            Object[] objArr = new Object[4];
            objArr[0] = "getView(), position index out of bounds, position = ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = " mValueHolderList.size() = ";
            List<b> list = this.e;
            objArr[3] = Integer.valueOf(list != null ? list.size() : 0);
            LogUtil.b("TrackGridViewAdapter", objArr);
            return null;
        }
        Context context = this.b;
        if (context == null) {
            LogUtil.b("TrackGridViewAdapter", "getView(), mContext == null");
            return null;
        }
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.track_data_item_with_icon_layout, (ViewGroup) null);
            aVar = new a((ImageView) view.findViewById(R.id.icon), (HealthTextView) view.findViewById(R.id.text));
            view.setTag(aVar);
        } else {
            Object tag = view.getTag();
            aVar = tag instanceof a ? (a) tag : null;
        }
        ImageView imageView = aVar == null ? null : aVar.c;
        HealthTextView healthTextView = aVar != null ? aVar.d : null;
        bdH_(this.e.get(i), imageView);
        if (healthTextView != null) {
            healthTextView.setText(this.e.get(i).e());
            if (this.d) {
                healthTextView.setTextColor(this.b.getResources().getColor(R.color._2131299243_res_0x7f090bab));
            }
            if (nsn.ag(this.b)) {
                healthTextView.setMaxLines(2);
            } else {
                healthTextView.setMaxLines(3);
            }
            if (!LanguageUtil.m(BaseApplication.getContext())) {
                healthTextView.setTextSize(1, 9.0f);
            }
        }
        return view;
    }

    private void bdH_(b bVar, ImageView imageView) {
        Drawable cJH_;
        if (bVar == null || imageView == null) {
            return;
        }
        Resources resources = BaseApplication.getContext().getResources();
        if (bVar.a()) {
            cJH_ = resources.getDrawable(bVar.c());
        } else {
            cJH_ = nrf.cJH_(resources.getDrawable(bVar.b()), resources.getColor(this.d ? R.color._2131297782_res_0x7f0905f6 : R.color._2131297781_res_0x7f0905f5));
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            BitmapDrawable cKm_ = nrz.cKm_(BaseApplication.getContext(), cJH_);
            if (cKm_ != null) {
                imageView.setImageDrawable(cKm_);
                return;
            }
            return;
        }
        imageView.setImageDrawable(cJH_);
    }

    public void a() {
        if (koq.b(this.e)) {
            return;
        }
        for (b bVar : this.e) {
            if (bVar != null) {
                bVar.d(false);
            }
        }
    }

    public b c(TrackMainFragmentShowType trackMainFragmentShowType) {
        Map<TrackMainFragmentShowType, b> map = this.f3706a;
        if (map == null) {
            return null;
        }
        return map.get(trackMainFragmentShowType);
    }

    private Map<TrackMainFragmentShowType, b> e(List<b> list) {
        if (koq.b(list)) {
            return null;
        }
        HashMap hashMap = new HashMap(list.size());
        for (b bVar : list) {
            if (bVar != null) {
                hashMap.put(bVar.f3707a, bVar);
            }
        }
        return hashMap;
    }

    static class a {
        private ImageView c;
        private HealthTextView d;

        a(ImageView imageView, HealthTextView healthTextView) {
            this.c = imageView;
            this.d = healthTextView;
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private TrackMainFragmentShowType f3707a;
        private int b;
        private int c;
        private boolean d = false;
        private int e;

        public b(int i, int i2, int i3, TrackMainFragmentShowType trackMainFragmentShowType) {
            this.c = i;
            this.b = i2;
            this.e = i3;
            this.f3707a = trackMainFragmentShowType;
        }

        public int b() {
            return this.c;
        }

        public int c() {
            return this.b;
        }

        public int e() {
            return this.e;
        }

        public void d(boolean z) {
            this.d = z;
        }

        public boolean a() {
            return this.d;
        }

        public TrackMainFragmentShowType d() {
            return this.f3707a;
        }
    }
}
