package com.huawei.hihealthservice.hihealthkit.model;

import android.os.IInterface;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public abstract class DataObservable<T extends IInterface> {
    protected final Map<String, RemoteCallbackList<T>> mObservers = new ConcurrentHashMap();

    public abstract void notifyDataChanged(Object obj);

    public void registerObserver(Parcelable parcelable, T t) {
        if (this.mObservers.get(parcelable.toString()) == null) {
            this.mObservers.put(parcelable.toString(), new RemoteCallbackList<>());
        }
        this.mObservers.get(parcelable.toString()).register(t);
    }

    public void unregisterObserver(Parcelable parcelable, T t) {
        RemoteCallbackList<T> remoteCallbackList = this.mObservers.get(parcelable.toString());
        if (remoteCallbackList != null) {
            remoteCallbackList.unregister(t);
            if (remoteCallbackList.getRegisteredCallbackCount() == 0) {
                this.mObservers.remove(parcelable.toString());
            }
        }
    }

    public boolean isEmpty() {
        return this.mObservers.isEmpty();
    }

    public void unregisterAll() {
        this.mObservers.clear();
    }
}
