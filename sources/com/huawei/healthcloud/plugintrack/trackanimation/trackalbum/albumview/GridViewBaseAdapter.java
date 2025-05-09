package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.mld;
import defpackage.nrf;
import defpackage.nrp;
import defpackage.nsn;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class GridViewBaseAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3618a;
    private LayoutInflater b;
    private OnGridViewListener c = null;
    private ArrayList<PhotoModel> d;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public GridViewBaseAdapter(Context context, ArrayList<PhotoModel> arrayList) {
        this.f3618a = context;
        this.d = arrayList;
        this.b = LayoutInflater.from(context);
    }

    public void b(OnGridViewListener onGridViewListener) {
        this.c = onGridViewListener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<PhotoModel> arrayList = this.d;
        if (arrayList == null) {
            return 0;
        }
        if (arrayList.size() > 3) {
            return 3;
        }
        return this.d.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.d, i)) {
            return null;
        }
        return this.d.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        c cVar;
        if (i < 0) {
            return null;
        }
        if (view != null && (view.getTag() instanceof c)) {
            cVar = (c) view.getTag();
        } else {
            view = this.b.inflate(R.layout.trackalbum_griditem_addphoto, viewGroup, false);
            cVar = new c(view);
            view.setTag(cVar);
        }
        PhotoModel photoModel = this.d.get(i);
        if (photoModel == null) {
            if (nsn.v(this.f3618a)) {
                cVar.f3619a.setImageDrawable(this.f3618a.getResources().getDrawable(R.drawable._2131431947_res_0x7f0b120b));
            } else {
                cVar.f3619a.setImageDrawable(this.f3618a.getResources().getDrawable(R.drawable._2131431946_res_0x7f0b120a));
            }
            if (i == 3) {
                cVar.f3619a.setVisibility(8);
                cVar.f3619a.setClickable(false);
            }
            cVar.d.setVisibility(8);
            return view;
        }
        RequestOptions override = new RequestOptions().dontAnimate().override(200, 200);
        Context context = this.f3618a;
        nrf.cIv_(photoModel.getPath(), override.transform(new CenterCrop(), new nrp(context, mld.d(context, 8.0f))), cVar.f3619a);
        cVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.GridViewBaseAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GridViewBaseAdapter.this.d.remove(i);
                GridViewBaseAdapter.this.c.onPhotoOrVideoDelete(i);
                GridViewBaseAdapter.this.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        return view;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private final ImageView f3619a;
        private final HealthTextView d;

        public c(View view) {
            this.f3619a = (ImageView) view.findViewById(R.id.photoView);
            this.d = (HealthTextView) view.findViewById(R.id.iv_del);
        }
    }
}
