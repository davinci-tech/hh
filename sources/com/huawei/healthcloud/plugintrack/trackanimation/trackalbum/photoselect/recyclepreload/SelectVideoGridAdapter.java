package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import defpackage.hcl;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class SelectVideoGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f3632a;
    private List<VideoModel> e = new ArrayList(16);
    private OnPhotoSelectChangedListener c = null;
    private boolean d = false;

    public SelectVideoGridAdapter(Context context) {
        this.f3632a = null;
        this.f3632a = context;
    }

    public void b(OnPhotoSelectChangedListener onPhotoSelectChangedListener) {
        this.c = onPhotoSelectChangedListener;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void b(List<VideoModel> list) {
        if (list != null) {
            this.e = list;
        }
        notifyDataSetChanged();
    }

    public boolean b() {
        List<VideoModel> list = this.e;
        return list == null || list.size() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.f3632a).inflate(R.layout.trackalbum_video_grid_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        if (hcl.d(this.f3632a)) {
            nrf.cHt_(this.f3632a, viewHolder2.f3633a);
        }
        viewHolder2.f3633a.setImageDrawable(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i < 0) {
            LogUtil.h("Track_SelectVideoGridAdapter", "position is < 0");
            return;
        }
        if (!(viewHolder instanceof ViewHolder)) {
            LogUtil.h("Track_SelectVideoGridAdapter", "holder not instanceof SelectVideoGridAdapter.ViewHolder");
            return;
        }
        final ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.h = i;
        viewHolder2.f3633a.setEnabled(false);
        viewHolder2.b.setEnabled(false);
        viewHolder2.c.setEnabled(false);
        VideoModel videoModel = this.e.get(i);
        viewHolder2.b.setChecked(videoModel.isSelected());
        LogUtil.a("Track_SelectVideoGridAdapter", "position is ", Integer.valueOf(i), ",and isSelect is", Boolean.valueOf(videoModel.isSelected()));
        if (c(this.f3632a)) {
            RequestOptions override = new RequestOptions().priority(Priority.NORMAL).fitCenter().override(300, 300);
            nrf.e(override, R.drawable._2131431958_res_0x7f0b1216);
            nrf.cIF_(videoModel.getThumbnailPath(), override, new RequestListener<Drawable>() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.SelectVideoGridAdapter.3
                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    LogUtil.h("Track_SelectVideoGridAdapter", "onLoadFailed ", LogAnonymous.b((Throwable) glideException));
                    viewHolder2.f3633a.setEnabled(false);
                    viewHolder2.b.setEnabled(false);
                    viewHolder2.c.setEnabled(false);
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                /* renamed from: aZW_, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    viewHolder2.f3633a.setEnabled(true);
                    viewHolder2.b.setEnabled(true);
                    viewHolder2.c.setEnabled(true);
                    return false;
                }
            }, viewHolder2.f3633a, true);
            viewHolder2.d.setText(e(videoModel.getDuration()));
        }
    }

    private boolean c(Context context) {
        if (context == null) {
            LogUtil.h("Track_SelectVideoGridAdapter", "context is null");
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            return (activity.isDestroyed() || activity.isFinishing()) ? false : true;
        }
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context == null) {
            LogUtil.h("Track_SelectVideoGridAdapter", "validContext is null");
            return false;
        }
        if (context instanceof Activity) {
            Activity activity2 = (Activity) context;
            if (activity2.isDestroyed() || activity2.isFinishing()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        final ImageView f3633a;
        final HealthCheckBox b;
        final View c;
        final TextView d;
        private int h;

        public ViewHolder(View view) {
            super(view);
            this.h = -1;
            ImageView imageView = (ImageView) view.findViewById(R.id.videoThumbnail);
            this.f3633a = imageView;
            this.b = (HealthCheckBox) view.findViewById(R.id.image_select_cb);
            this.d = (TextView) view.findViewById(R.id.video_duration);
            this.c = view.findViewById(R.id.btnCheck);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.SelectVideoGridAdapter.ViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (!nsn.cLk_(view2)) {
                        if (SelectVideoGridAdapter.this.c != null && ViewHolder.this.h >= 0) {
                            SelectVideoGridAdapter.this.c.onPictureClick(ViewHolder.this.h, SelectVideoGridAdapter.this.e.get(ViewHolder.this.h));
                        }
                        ViewClickInstrumentation.clickOnView(view2);
                        return;
                    }
                    LogUtil.a("Track_SelectVideoGridAdapter", "click too fast");
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
            b();
        }

        private void b() {
            this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.SelectVideoGridAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (SelectVideoGridAdapter.this.c != null && ViewHolder.this.h >= 0) {
                        if (!((VideoModel) SelectVideoGridAdapter.this.e.get(ViewHolder.this.h)).isSelected()) {
                            ViewHolder.this.e();
                        } else {
                            ViewHolder.this.b.setChecked(false);
                            SelectVideoGridAdapter.this.c.onPictureRemove(SelectVideoGridAdapter.this.e.get(ViewHolder.this.h));
                            ((VideoModel) SelectVideoGridAdapter.this.e.get(ViewHolder.this.h)).setIsSelected(false);
                        }
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    LogUtil.a("Track_SelectVideoGridAdapter", "mListener is null or mId < 0");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            int i = 0;
            while (true) {
                if (i < SelectVideoGridAdapter.this.e.size()) {
                    if (((VideoModel) SelectVideoGridAdapter.this.e.get(i)).isSelected()) {
                        ((VideoModel) SelectVideoGridAdapter.this.e.get(i)).setIsSelected(false);
                        SelectVideoGridAdapter.this.c.onPictureRemove(SelectVideoGridAdapter.this.e.get(i));
                        nrh.c(SelectVideoGridAdapter.this.f3632a, SelectVideoGridAdapter.this.f3632a.getResources().getString(R.string._2130840091_res_0x7f020a1b, 1));
                        SelectVideoGridAdapter.this.notifyItemChanged(i);
                        break;
                    }
                    i++;
                } else if (SelectVideoGridAdapter.this.d) {
                    nrh.d(SelectVideoGridAdapter.this.f3632a, SelectVideoGridAdapter.this.f3632a.getString(R.string._2130840089_res_0x7f020a19));
                }
            }
            this.b.setChecked(true);
            SelectVideoGridAdapter.this.c.onPictureAdd(SelectVideoGridAdapter.this.e.get(this.h));
            ((VideoModel) SelectVideoGridAdapter.this.e.get(this.h)).setIsSelected(true);
        }
    }

    private String e(long j) {
        StringBuilder sb = new StringBuilder(31);
        Formatter formatter = new Formatter(sb, Locale.getDefault());
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        int i4 = i / 3600;
        sb.setLength(0);
        if (i4 == 0) {
            return formatter.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2)).toString();
        }
        return formatter.format("%02d:%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)).toString();
    }
}
