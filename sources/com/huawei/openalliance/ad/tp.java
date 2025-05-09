package com.huawei.openalliance.ad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class tp implements Callable<Bitmap> {

    /* renamed from: a, reason: collision with root package name */
    private final rr f7540a;

    @Override // java.util.concurrent.Callable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bitmap call() {
        return BitmapFactory.decodeFile(this.f7540a.a().a());
    }

    public tp(rr rrVar) {
        this.f7540a = rrVar;
    }
}
