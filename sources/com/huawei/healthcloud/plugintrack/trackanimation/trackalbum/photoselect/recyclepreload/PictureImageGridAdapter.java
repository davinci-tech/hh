package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import defpackage.hcl;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PictureImageGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<PhotoModel> f3629a = new ArrayList();
    private OnPhotoSelectChangedListener c = null;
    private Context d;

    public PictureImageGridAdapter(Context context) {
        this.d = null;
        this.d = context;
    }

    public void d(OnPhotoSelectChangedListener onPhotoSelectChangedListener) {
        this.c = onPhotoSelectChangedListener;
    }

    public void e(List<PhotoModel> list) {
        if (list != null) {
            this.f3629a = list;
            Log.i("PictureImageGridAdapter", "add data");
        }
        notifyDataSetChanged();
    }

    public boolean c() {
        List<PhotoModel> list = this.f3629a;
        return list == null || list.size() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.d).inflate(R.layout.trackalbum_image_grid_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        if (hcl.d(this.d)) {
            nrf.cHt_(this.d, viewHolder2.b);
        }
        viewHolder2.b.setImageDrawable(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= 0 && (viewHolder instanceof ViewHolder)) {
            final ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            PhotoModel photoModel = this.f3629a.get(i);
            viewHolder2.c = i;
            viewHolder2.b.setEnabled(false);
            viewHolder2.f3630a.setEnabled(false);
            viewHolder2.e.setEnabled(false);
            viewHolder2.f3630a.setChecked(this.f3629a.get(i).isSelected());
            LogUtil.a("PictureImageGridAdapter", "position is ", Integer.valueOf(i), ",and isselect is", Boolean.valueOf(this.f3629a.get(i).isSelected()));
            if (b(this.d) && (this.d instanceof Activity)) {
                RequestOptions override = new RequestOptions().priority(Priority.NORMAL).fitCenter().override(300, 300);
                nrf.e(override, R.drawable._2131431958_res_0x7f0b1216);
                nrf.cIF_(photoModel.getPath(), override, new RequestListener<Drawable>() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.PictureImageGridAdapter.5
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                        LogUtil.h("PictureImageGridAdapter", "onLoadFailed ", LogAnonymous.b((Throwable) glideException));
                        viewHolder2.b.setEnabled(false);
                        viewHolder2.f3630a.setEnabled(false);
                        viewHolder2.e.setEnabled(false);
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    /* renamed from: aZV_, reason: merged with bridge method [inline-methods] */
                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                        viewHolder2.b.setEnabled(true);
                        viewHolder2.f3630a.setEnabled(true);
                        viewHolder2.e.setEnabled(true);
                        return false;
                    }
                }, viewHolder2.b, true);
            }
        }
    }

    public boolean b(Context context) {
        if (context == null) {
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
            return false;
        }
        if (!(context instanceof Activity)) {
            return true;
        }
        Activity activity2 = (Activity) context;
        return (activity2.isDestroyed() || activity2.isFinishing()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f3629a.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        final HealthCheckBox f3630a;
        final ImageView b;
        private int c;
        final View e;

        public ViewHolder(View view) {
            super(view);
            this.c = -1;
            ImageView imageView = (ImageView) view.findViewById(R.id.ivPicture);
            this.b = imageView;
            this.f3630a = (HealthCheckBox) view.findViewById(R.id.image_select_cb);
            View findViewById = view.findViewById(R.id.btnCheck);
            this.e = findViewById;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.PictureImageGridAdapter.ViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (!nsn.cLk_(view2)) {
                        if (PictureImageGridAdapter.this.c != null && ViewHolder.this.c >= 0) {
                            PictureImageGridAdapter.this.c.onPictureClick(ViewHolder.this.c, PictureImageGridAdapter.this.f3629a.get(ViewHolder.this.c));
                        }
                        ViewClickInstrumentation.clickOnView(view2);
                        return;
                    }
                    LogUtil.a("PictureImageGridAdapter", "click too fast");
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.PictureImageGridAdapter.ViewHolder.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (PictureImageGridAdapter.this.c != null && ViewHolder.this.c >= 0) {
                        if (!((PhotoModel) PictureImageGridAdapter.this.f3629a.get(ViewHolder.this.c)).isSelected()) {
                            if (!PictureImageGridAdapter.this.c.isMaxCount()) {
                                ViewHolder.this.f3630a.setChecked(true);
                                PictureImageGridAdapter.this.c.onPictureAdd(PictureImageGridAdapter.this.f3629a.get(ViewHolder.this.c));
                                ((PhotoModel) PictureImageGridAdapter.this.f3629a.get(ViewHolder.this.c)).setSelected(true);
                            } else {
                                ViewHolder.this.f3630a.setChecked(false);
                                Toast.makeText(PictureImageGridAdapter.this.d, R.string._2130839942_res_0x7f020986, 0).show();
                            }
                        } else {
                            ViewHolder.this.f3630a.setChecked(false);
                            PictureImageGridAdapter.this.c.onPictureRemove(PictureImageGridAdapter.this.f3629a.get(ViewHolder.this.c));
                            ((PhotoModel) PictureImageGridAdapter.this.f3629a.get(ViewHolder.this.c)).setSelected(false);
                        }
                        ViewClickInstrumentation.clickOnView(view2);
                        return;
                    }
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
    }
}
