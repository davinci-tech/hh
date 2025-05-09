package com.huawei.ui.openservice.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.ui.openservice.OpenServiceUtil;
import com.huawei.ui.openservice.db.model.OpenService;
import com.huawei.ui.openservice.ui.GridImage;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class OpenServiceGridAdapter extends BaseAdapter {
    private View.OnClickListener listener;
    private Context mContext;
    private List<OpenService> services;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public OpenServiceGridAdapter(List<OpenService> list, Context context, View.OnClickListener onClickListener) {
        new ArrayList();
        this.services = list;
        this.mContext = context;
        this.listener = onClickListener;
        if (CommonUtil.as()) {
            removeShuDongLi(list);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.services.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.services.get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ServiceTitleViewHolder serviceTitleViewHolder = null;
        Object[] objArr = 0;
        if (view == null) {
            ServiceTitleViewHolder serviceTitleViewHolder2 = new ServiceTitleViewHolder();
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_open_service_grid_item, (ViewGroup) null);
            if (inflate.findViewById(R.id.gird_item) instanceof GridImage) {
                serviceTitleViewHolder2.mIcon = (GridImage) inflate.findViewById(R.id.gird_item);
            }
            inflate.setTag(serviceTitleViewHolder2);
            serviceTitleViewHolder = serviceTitleViewHolder2;
            view = inflate;
        } else if (view.getTag() instanceof ServiceTitleViewHolder) {
            serviceTitleViewHolder = (ServiceTitleViewHolder) view.getTag();
        }
        if (serviceTitleViewHolder != null) {
            Bitmap icon = OpenServiceUtil.getIcon(this.mContext, this.services.get(i).getServiceMidIcon());
            if (icon != null) {
                serviceTitleViewHolder.mIcon.setImageBitmap(icon);
            } else {
                serviceTitleViewHolder.mIcon.setVisibility(8);
            }
            serviceTitleViewHolder.mIcon.setClickable(true);
            serviceTitleViewHolder.mIcon.setOnClickListener(this.listener);
            serviceTitleViewHolder.mIcon.setTag(Integer.valueOf(i));
        }
        return view;
    }

    static class ServiceTitleViewHolder {
        private GridImage mIcon;

        private ServiceTitleViewHolder() {
        }
    }

    private void removeShuDongLi(List<OpenService> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if ("shudongli".equals(list.get(i).getServiceID())) {
                list.remove(i);
            }
        }
    }
}
