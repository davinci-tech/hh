package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class r extends AsyncTask<Object, Void, Integer> {

    /* renamed from: a, reason: collision with root package name */
    private ZipCompressListener f8367a;
    private WeakReference<Context> b;
    private String c;
    private String d;
    private long e;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        super.onPostExecute(num);
        com.huawei.phoneservice.faq.base.util.i.e("ZipCompressTask", "integer:" + num);
        this.f8367a.zipCompressFinished(num.intValue(), this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006f A[Catch: Exception -> 0x0155, TRY_LEAVE, TryCatch #0 {Exception -> 0x0155, blocks: (B:3:0x0005, B:5:0x0019, B:8:0x001e, B:10:0x0024, B:13:0x002d, B:14:0x0054, B:16:0x006f, B:19:0x00a0, B:22:0x00c2, B:25:0x00dc, B:28:0x0105, B:29:0x00f9, B:30:0x00cd, B:31:0x00b3, B:32:0x010c, B:34:0x011a, B:36:0x011d, B:39:0x012c, B:40:0x013a, B:41:0x014d, B:42:0x013e, B:43:0x004b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x013e A[Catch: Exception -> 0x0155, TryCatch #0 {Exception -> 0x0155, blocks: (B:3:0x0005, B:5:0x0019, B:8:0x001e, B:10:0x0024, B:13:0x002d, B:14:0x0054, B:16:0x006f, B:19:0x00a0, B:22:0x00c2, B:25:0x00dc, B:28:0x0105, B:29:0x00f9, B:30:0x00cd, B:31:0x00b3, B:32:0x010c, B:34:0x011a, B:36:0x011d, B:39:0x012c, B:40:0x013a, B:41:0x014d, B:42:0x013e, B:43:0x004b), top: B:2:0x0005 }] */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Integer doInBackground(java.lang.Object... r11) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.r.doInBackground(java.lang.Object[]):java.lang.Integer");
    }

    public r(Context context, ZipCompressListener zipCompressListener) {
        this.f8367a = zipCompressListener;
        this.b = new WeakReference<>(context);
    }

    public r(Context context, long j, ZipCompressListener zipCompressListener) {
        this.e = j;
        this.f8367a = zipCompressListener;
        this.b = new WeakReference<>(context);
    }
}
