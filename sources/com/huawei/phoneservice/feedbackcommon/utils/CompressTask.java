package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import java.io.File;
import java.nio.charset.StandardCharsets;

/* loaded from: classes6.dex */
public class CompressTask implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private String f8339a;
    private Context c;
    private String d;
    private TaskCallBack e;

    public interface TaskCallBack {
        void compressDone(Throwable th, String str);
    }

    class b implements RequestListener<Bitmap> {

        /* renamed from: a, reason: collision with root package name */
        private final String f8340a;
        private final String b;

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
            CompressTask.this.e.compressDone(new Throwable("onLoadFailed:" + glideException), this.f8340a);
            return false;
        }

        @Override // com.bumptech.glide.request.RequestListener
        /* renamed from: cfr_, reason: merged with bridge method [inline-methods] */
        public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
            TaskCallBack taskCallBack;
            Throwable th;
            String str;
            if (bitmap != null) {
                com.huawei.phoneservice.faq.base.util.i.d("CompressTask", "after compress ,picture size：" + (e.cfo_(bitmap, this.b).length() / 1024) + "KB, width: " + bitmap.getWidth() + " height:" + bitmap.getHeight());
                taskCallBack = CompressTask.this.e;
                th = null;
                str = this.b;
            } else {
                taskCallBack = CompressTask.this.e;
                th = new Throwable("can't find file by path:" + this.f8340a);
                str = this.f8340a;
            }
            taskCallBack.compressDone(th, str);
            return false;
        }

        b(String str, String str2) {
            this.f8340a = str;
            this.b = str2;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        new File(this.d).mkdirs();
        String str = this.f8339a;
        if (str == null) {
            str = String.valueOf(System.currentTimeMillis());
        }
        String str2 = this.d + File.separator + (Base64.encodeToString(str.getBytes(StandardCharsets.UTF_8), 8) + ".jpg");
        if (new File(str2).exists()) {
            this.e.compressDone(null, str2);
        } else {
            b(this.f8339a, str2);
        }
    }

    private void b(String str, String str2) {
        Glide.with(this.c).asBitmap().load(str).listener(new b(str, str2)).into((RequestBuilder<Bitmap>) new d());
    }

    class d extends SimpleTarget<Bitmap> {
        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: cfq_, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        }

        d() {
        }
    }

    public CompressTask(Context context, String str, String str2, TaskCallBack taskCallBack) {
        this.c = context;
        this.f8339a = str;
        this.d = str2;
        this.e = taskCallBack;
    }
}
