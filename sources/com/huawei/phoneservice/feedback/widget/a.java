package com.huawei.phoneservice.feedback.widget;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.phoneservice.faq.base.util.l;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedbackcommon.entity.FeedMedia;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class a<T> extends com.huawei.phoneservice.feedback.widget.b {

    /* renamed from: a, reason: collision with root package name */
    protected List<T> f8303a = new ArrayList();

    /* renamed from: com.huawei.phoneservice.feedback.widget.a$a, reason: collision with other inner class name */
    public interface InterfaceC0234a {
        void a(int i, ImageView imageView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2, String str, String str2, long j, boolean z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f8303a.size();
    }

    public void b(Context context, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, FeedMedia feedMedia, MediaEntity mediaEntity, int i, InterfaceC0234a interfaceC0234a) {
        if (mediaEntity != null) {
            a(context, mediaEntity, imageView, imageView2, relativeLayout);
        } else {
            imageView2.setVisibility(0);
            relativeLayout.setVisibility(0);
        }
        b bVar = new b(imageView, imageView2, relativeLayout, relativeLayout2, feedMedia, i, true, true, interfaceC0234a);
        imageView2.setOnClickListener(bVar);
        relativeLayout.setOnClickListener(bVar);
        imageView.setOnClickListener(new b(imageView, imageView2, relativeLayout, relativeLayout2, feedMedia, i, false, false, interfaceC0234a));
    }

    public void a(List<T> list) {
        this.f8303a.clear();
        this.f8303a.addAll(list);
        notifyDataSetChanged();
    }

    public void a(Context context, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, FeedMedia feedMedia, MediaEntity mediaEntity, int i, InterfaceC0234a interfaceC0234a) {
        if (mediaEntity != null) {
            b(context, mediaEntity, imageView, imageView2, relativeLayout);
        } else {
            imageView2.setVisibility(0);
            relativeLayout.setVisibility(0);
        }
        b bVar = new b(imageView, imageView2, relativeLayout, relativeLayout2, feedMedia, i, true, true, interfaceC0234a);
        imageView2.setOnClickListener(bVar);
        relativeLayout.setOnClickListener(bVar);
        imageView.setOnClickListener(new b(imageView, imageView2, relativeLayout, relativeLayout2, feedMedia, i, false, false, interfaceC0234a));
    }

    public T a(int i) {
        return this.f8303a.get(i);
    }

    private void b(Context context, MediaEntity mediaEntity, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout) {
        String str;
        File a2 = a(mediaEntity.path);
        if (!l.e(mediaEntity.path) && a2.exists()) {
            if (a2.canRead()) {
                str = mediaEntity.path;
                a(context, str, imageView);
            } else {
                if (l.e(mediaEntity.strUri)) {
                    return;
                }
                a(context, Uri.parse(mediaEntity.strUri), imageView);
            }
        }
        File a3 = a(mediaEntity.cache);
        if (l.e(mediaEntity.cache) || !a3.exists()) {
            relativeLayout.setVisibility(0);
            imageView2.setVisibility(0);
        } else if (a3.canRead()) {
            str = mediaEntity.cache;
            a(context, str, imageView);
        } else {
            if (l.e(mediaEntity.strUri)) {
                return;
            }
            a(context, Uri.parse(mediaEntity.strUri), imageView);
        }
    }

    private void b(Context context, Uri uri, ImageView imageView) {
        if (context == null || uri == null || imageView == null) {
            return;
        }
        Glide.with(context).load(uri).into(imageView);
        imageView.setVisibility(0);
    }

    private void a(Context context, String str, ImageView imageView) {
        if (context == null || str == null || imageView == null) {
            return;
        }
        Glide.with(context).load(new File(str)).into(imageView);
        imageView.setVisibility(0);
    }

    private void a(Context context, File file, ImageView imageView) {
        if (context == null || file == null || imageView == null) {
            return;
        }
        Glide.with(context).load(file).into(imageView);
        imageView.setVisibility(0);
    }

    private void a(Context context, MediaEntity mediaEntity, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout) {
        String str;
        File a2 = a(mediaEntity.path);
        if (!l.e(mediaEntity.path) && a2.exists()) {
            if (a2.canRead()) {
                str = mediaEntity.path;
                a(context, a(str), imageView);
            } else {
                if (l.e(mediaEntity.strUri)) {
                    return;
                }
                b(context, Uri.parse(mediaEntity.strUri), imageView);
            }
        }
        File a3 = a(mediaEntity.cache);
        if (l.e(mediaEntity.cache) || !a3.exists()) {
            relativeLayout.setVisibility(0);
            imageView2.setVisibility(0);
        } else if (a3.canRead()) {
            str = mediaEntity.cache;
            a(context, a(str), imageView);
        } else {
            if (l.e(mediaEntity.strUri)) {
                return;
            }
            b(context, Uri.parse(mediaEntity.strUri), imageView);
        }
    }

    public static class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f8304a;
        private RelativeLayout b;
        private FeedMedia c;
        private InterfaceC0234a d;
        private ImageView e;
        private boolean f;
        private boolean g;
        private RelativeLayout h;
        private int j;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageView imageView;
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (this.d != null) {
                if (this.f && (imageView = this.e) != null) {
                    imageView.setVisibility(8);
                }
                FeedMedia feedMedia = this.c;
                if (feedMedia == null) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                this.d.a(this.j, this.f8304a, this.b, this.h, this.e, feedMedia.getDownloadURL(), this.c.getAttachId(), Long.parseLong(this.c.getSize()), this.g);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public b(ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, FeedMedia feedMedia, int i, boolean z, boolean z2, InterfaceC0234a interfaceC0234a) {
            this.f8304a = imageView;
            this.e = imageView2;
            this.b = relativeLayout;
            this.h = relativeLayout2;
            this.c = feedMedia;
            this.f = z;
            this.j = i;
            this.g = z2;
            this.d = interfaceC0234a;
        }
    }

    private void a(Context context, Uri uri, ImageView imageView) {
        if (context == null || uri == null || imageView == null) {
            return;
        }
        Glide.with(context).load(uri).into(imageView);
        imageView.setVisibility(0);
    }

    private File a(String str) {
        return new File(str);
    }
}
