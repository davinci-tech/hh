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
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.healthcloud.plugintrack.ui.activity.SelectedVideoPreviewActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.mld;
import defpackage.nrf;
import defpackage.nrp;
import defpackage.nsn;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class VideoViewAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<VideoModel> f3621a;
    private OnGridViewListener c = null;
    private Context d;
    private LayoutInflater e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void b(OnGridViewListener onGridViewListener) {
        this.c = onGridViewListener;
    }

    public VideoViewAdapter(Context context, ArrayList<VideoModel> arrayList) {
        this.d = context;
        this.f3621a = arrayList;
        this.e = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<VideoModel> arrayList = this.f3621a;
        if (arrayList == null) {
            LogUtil.h("Track_VideoViewAdapter", "mDatas is null");
            return 0;
        }
        if (arrayList.size() > 1) {
            return 1;
        }
        return this.f3621a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.f3621a, i)) {
            LogUtil.h("Track_VideoViewAdapter", "getItem mDatas position is out of bound");
            return null;
        }
        return this.f3621a.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (koq.b(this.f3621a, i)) {
            return view;
        }
        if (view != null && (view.getTag() instanceof e)) {
            eVar = (e) view.getTag();
        } else {
            view = this.e.inflate(R.layout.trackalbum_griditem_addphotoandvideo, viewGroup, false);
            eVar = new e(view);
            view.setTag(eVar);
        }
        VideoModel videoModel = this.f3621a.get(i);
        if (videoModel == null) {
            return aZO_(i, eVar, view);
        }
        RequestOptions override = new RequestOptions().dontAnimate().override(200, 200);
        Context context = this.d;
        nrf.cIv_(videoModel.getThumbnailPath(), override.transform(new CenterCrop(), new nrp(context, mld.d(context, 8.0f))), eVar.b);
        eVar.d.setVisibility(0);
        d(i, eVar);
        return view;
    }

    private View aZO_(int i, e eVar, View view) {
        if (nsn.v(this.d)) {
            eVar.b.setImageDrawable(this.d.getResources().getDrawable(R.drawable._2131431947_res_0x7f0b120b));
        } else {
            eVar.b.setImageDrawable(this.d.getResources().getDrawable(R.drawable._2131431946_res_0x7f0b120a));
        }
        if (i == 1) {
            eVar.b.setVisibility(8);
            eVar.b.setClickable(false);
        }
        eVar.f3624a.setVisibility(8);
        eVar.d.setVisibility(8);
        return view;
    }

    private void d(final int i, e eVar) {
        eVar.f3624a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.VideoViewAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoViewAdapter.this.f3621a.remove(i);
                if (VideoViewAdapter.this.c != null) {
                    VideoViewAdapter.this.c.onPhotoOrVideoDelete(i);
                }
                VideoViewAdapter.this.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        eVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview.VideoViewAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectedVideoPreviewActivity.e(VideoViewAdapter.this.d, (VideoModel) VideoViewAdapter.this.f3621a.get(i));
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTextView f3624a;
        private final ImageView b;
        private final HealthTextView d;

        public e(View view) {
            this.b = (ImageView) view.findViewById(R.id.photoView);
            this.f3624a = (HealthTextView) view.findViewById(R.id.iv_del);
            this.d = (HealthTextView) view.findViewById(R.id.iv_play);
        }
    }
}
