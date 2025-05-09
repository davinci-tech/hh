package com.huawei.watchface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

/* loaded from: classes9.dex */
public class cz {

    public interface a {
        void onResourceReady(Bitmap bitmap);
    }

    public static void a(Context context, String str, int i, int i2, ImageView imageView) {
        Glide.with(context).load(str).placeholder(i).error(i2).into(imageView);
    }

    public static void a(Context context, String str, final a aVar) {
        Glide.with(context).asBitmap().load(str).into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.huawei.watchface.cz.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                a aVar2 = a.this;
                if (aVar2 == null) {
                    return;
                }
                aVar2.onResourceReady(bitmap);
            }
        });
    }
}
