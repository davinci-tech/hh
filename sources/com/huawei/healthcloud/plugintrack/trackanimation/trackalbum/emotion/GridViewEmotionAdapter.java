package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.emotion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import defpackage.hce;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public class GridViewEmotionAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<hce> f3626a;
    private Context e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public GridViewEmotionAdapter(Context context, List<hce> list) {
        this.e = context;
        this.f3626a = list;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (!(view instanceof ImageView)) {
            imageView = new ImageView(this.e);
        } else {
            imageView = (ImageView) view;
        }
        Object item = getItem(i);
        if (item instanceof hce) {
            imageView.setImageResource(((hce) item).b());
        }
        return imageView;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3626a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.f3626a, i)) {
            return null;
        }
        return this.f3626a.get(i);
    }
}
