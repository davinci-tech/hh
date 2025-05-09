package com.huawei.hms.push;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class f extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<a> f5676a;

    public interface a {
        void a(Message message);
    }

    public f(a aVar) {
        this.f5676a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        a aVar = this.f5676a.get();
        if (aVar != null) {
            aVar.a(message);
        }
    }
}
