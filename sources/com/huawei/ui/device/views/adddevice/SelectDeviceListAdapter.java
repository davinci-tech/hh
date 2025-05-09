package com.huawei.ui.device.views.adddevice;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jfu;
import defpackage.obu;
import health.compact.a.EzPluginManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SelectDeviceListAdapter extends BaseAdapter {
    private List<obu> e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 3;
    }

    public SelectDeviceListAdapter(List<obu> list) {
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        arrayList.clear();
        this.e.addAll(list);
    }

    public void a(List<obu> list) {
        this.e.clear();
        this.e.addAll(list);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.e.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.e.get(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return this.e.get(i).b();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            obu obuVar = this.e.get(i);
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                return cUP_(view, viewGroup, obuVar);
            }
            if (itemViewType == 1) {
                return cUO_(i, view, viewGroup, obuVar);
            }
            if (itemViewType == 2) {
                return cUQ_(view, viewGroup, obuVar);
            }
            LogUtil.h("SelectDeviceListAdapter", " getView default warning ");
            return cUO_(i, view, viewGroup, obuVar);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    private View cUP_(View view, ViewGroup viewGroup, obu obuVar) {
        c cVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_select_device_list_item_title, viewGroup, false);
            cVar = cUM_(view);
            view.setClickable(false);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        a(cVar, obuVar);
        return view;
    }

    private void a(c cVar, obu obuVar) {
        cVar.f9309a.setText(obuVar.i());
        if (obuVar.k()) {
            cVar.b.setVisibility(0);
        } else {
            cVar.b.setVisibility(4);
        }
    }

    private View cUO_(int i, View view, ViewGroup viewGroup, obu obuVar) {
        d dVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_select_device_list_item_info, viewGroup, false);
            BaseActivity.setViewSafeRegion(false, view);
            dVar = cUL_(view);
            view.setTag(dVar);
        } else {
            dVar = view.getTag() instanceof d ? (d) view.getTag() : null;
        }
        if (dVar != null) {
            e(viewGroup.getContext(), dVar, obuVar, i);
        }
        if (obuVar.cUS_() != null) {
            view.setOnClickListener(obuVar.cUS_());
        }
        return view;
    }

    private View cUQ_(View view, ViewGroup viewGroup, obu obuVar) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_select_device_list_item_more, viewGroup, false);
            BaseActivity.setViewSafeRegion(false, view);
        }
        if (obuVar.cUS_() != null) {
            view.setOnClickListener(obuVar.cUS_());
        }
        return view;
    }

    private c cUM_(View view) {
        c cVar = new c();
        cVar.b = view.findViewById(R.id.subheader_splitter);
        cVar.f9309a = (HealthTextView) view.findViewById(R.id.item_title);
        return cVar;
    }

    private d cUL_(View view) {
        d dVar = new d();
        dVar.d = (HealthTextView) view.findViewById(R.id.select_device_content);
        dVar.e = (HealthTextView) view.findViewById(R.id.select_device_summary);
        dVar.f9310a = (ImageView) view.findViewById(R.id.select_device_icon);
        dVar.b = view.findViewById(R.id.select_device_summary_line);
        dVar.c = (ImageView) view.findViewById(R.id.rightarrow_icon);
        return dVar;
    }

    private void e(Context context, d dVar, obu obuVar, int i) {
        if (LanguageUtil.bc(context)) {
            dVar.d.setGravity(5);
            dVar.e.setGravity(5);
            dVar.c.setImageDrawable(context.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            dVar.d.setGravity(3);
            dVar.e.setGravity(3);
            dVar.c.setImageDrawable(context.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        if (!cUN_(i, dVar.b)) {
            if (this.e.size() > 1 && i == this.e.size() - 1) {
                dVar.b.setVisibility(4);
            } else {
                dVar.b.setVisibility(0);
            }
        }
        d(obuVar, dVar.d);
        if (obuVar.h() != null) {
            dVar.e.setText(obuVar.h());
            dVar.e.setVisibility(0);
        } else {
            dVar.e.setVisibility(8);
        }
        if (obuVar.g()) {
            dVar.f9310a.setBackgroundResource(0);
            Bitmap loadImageByImagePath = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(obuVar.c());
            if (loadImageByImagePath != null) {
                dVar.f9310a.setImageBitmap(loadImageByImagePath);
            } else {
                LogUtil.h("SelectDeviceListAdapter", "setItemStyle() item.isFromPlugin() bitmap is null");
                EzPluginManager.a().d(jfu.d(obuVar.e()), (PullListener) null);
            }
            dVar.f9310a.setVisibility(0);
            return;
        }
        if (obuVar.j() != -1) {
            dVar.f9310a.setBackgroundResource(obuVar.j());
            dVar.f9310a.setImageBitmap(null);
            dVar.f9310a.setVisibility(0);
            return;
        }
        dVar.f9310a.setVisibility(8);
    }

    private void d(obu obuVar, HealthTextView healthTextView) {
        if (obuVar.d() != null) {
            healthTextView.setText(obuVar.d());
            healthTextView.setVisibility(0);
        } else {
            healthTextView.setVisibility(8);
        }
    }

    private boolean cUN_(int i, View view) {
        if (view == null) {
            return false;
        }
        int i2 = i + 1;
        if (i2 >= getCount()) {
            view.setVisibility(4);
            return true;
        }
        if (getItemViewType(i2) != 0) {
            return false;
        }
        view.setVisibility(4);
        return true;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        ImageView f9310a;
        View b;
        ImageView c;
        HealthTextView d;
        HealthTextView e;

        private d() {
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9309a;
        View b;

        private c() {
        }
    }
}
