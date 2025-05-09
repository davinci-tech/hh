package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.view.RoundedImageView;
import defpackage.koq;
import defpackage.nrf;
import java.util.List;

/* loaded from: classes8.dex */
public class WeightEditShareGridAdapter extends BaseAdapter {
    private Context b;
    private List<Bitmap> c;
    private int e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WeightEditShareGridAdapter(Context context, List<Bitmap> list, int i) {
        this.b = context;
        this.c = list;
        this.e = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<Bitmap> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.d(this.c, i)) {
            return this.c.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.hw_health_edit_share_gridview_item, (ViewGroup) null);
        if (inflate == null) {
            LogUtil.h("WeightEditShareGridAdapter", "getView convertView is null");
            return inflate;
        }
        RoundedImageView roundedImageView = (RoundedImageView) inflate.findViewById(R.id.hw_health_edit_share_select);
        nrf.cIn_(R.mipmap.hw_health_edit_share_select_img, roundedImageView);
        RoundedImageView roundedImageView2 = (RoundedImageView) inflate.findViewById(R.id.hw_health_edit_share_gridview_img);
        if (this.e != 0) {
            roundedImageView.setVisibility(8);
        } else if (i == 1) {
            roundedImageView.setVisibility(0);
        }
        if (koq.d(this.c, i)) {
            roundedImageView2.setImageBitmap(this.c.get(i));
        }
        return inflate;
    }
}
