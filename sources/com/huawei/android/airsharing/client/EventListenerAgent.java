package com.huawei.android.airsharing.client;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.huawei.android.airsharing.api.Event;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.android.airsharing.api.ProjectionDevice;
import com.huawei.android.airsharing.client.EventListenerAgent;
import com.huawei.android.airsharing.client.IAidlHwListener;

/* loaded from: classes2.dex */
public class EventListenerAgent extends IAidlHwListener.Stub {
    private final Handler d;
    private IEventListener e;

    public EventListenerAgent(IEventListener iEventListener, Looper looper) {
        this.e = iEventListener;
        if (looper == null) {
            this.d = new Handler();
        } else {
            this.d = new Handler(looper);
        }
    }

    @Override // com.huawei.android.airsharing.client.IAidlHwListener
    public boolean onEvent(final int i, final String str) {
        Handler handler = this.d;
        if (handler == null) {
            return true;
        }
        handler.post(new Runnable() { // from class: wo
            @Override // java.lang.Runnable
            public final void run() {
                EventListenerAgent.this.a(i, str);
            }
        });
        return true;
    }

    public /* synthetic */ void a(int i, String str) {
        this.e.onEvent(i, str);
    }

    @Override // com.huawei.android.airsharing.client.IAidlHwListener
    @Deprecated
    public void onDisplayUpdate(final int i, final String str, final String str2, final int i2) {
        Handler handler = this.d;
        if (handler != null) {
            handler.post(new Runnable() { // from class: wr
                @Override // java.lang.Runnable
                public final void run() {
                    EventListenerAgent.this.e(i, str, str2, i2);
                }
            });
        }
    }

    public /* synthetic */ void e(int i, String str, String str2, int i2) {
        this.e.onDisplayUpdate(i, str, str2, i2);
    }

    @Override // com.huawei.android.airsharing.client.IAidlHwListener
    @Deprecated
    public void onMirrorUpdate(final int i, final String str, final String str2, final int i2, final boolean z) {
        Handler handler = this.d;
        if (handler != null) {
            handler.post(new Runnable() { // from class: ws
                @Override // java.lang.Runnable
                public final void run() {
                    EventListenerAgent.this.d(i, str, str2, i2, z);
                }
            });
        }
    }

    public /* synthetic */ void d(int i, String str, String str2, int i2, boolean z) {
        this.e.onMirrorUpdate(i, str, str2, i2, z);
    }

    @Override // com.huawei.android.airsharing.client.IAidlHwListener
    public int getId() throws RemoteException {
        return this.e.hashCode();
    }

    @Override // com.huawei.android.airsharing.client.IAidlHwListener
    public void onProjectionDeviceUpdate(final int i, final ProjectionDevice projectionDevice) {
        Handler handler = this.d;
        if (handler != null) {
            handler.post(new Runnable() { // from class: wv
                @Override // java.lang.Runnable
                public final void run() {
                    EventListenerAgent.this.c(i, projectionDevice);
                }
            });
        }
    }

    public /* synthetic */ void c(int i, ProjectionDevice projectionDevice) {
        this.e.onProjectionDeviceUpdate(i, projectionDevice);
    }

    @Override // com.huawei.android.airsharing.client.IAidlHwListener
    public void onEventHandle(final Event event) throws RemoteException {
        Handler handler = this.d;
        if (handler != null) {
            handler.post(new Runnable() { // from class: wt
                @Override // java.lang.Runnable
                public final void run() {
                    EventListenerAgent.this.e(event);
                }
            });
        }
    }

    public /* synthetic */ void e(Event event) {
        this.e.onEventHandle(event);
    }
}
