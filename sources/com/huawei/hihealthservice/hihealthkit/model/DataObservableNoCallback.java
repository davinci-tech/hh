package com.huawei.hihealthservice.hihealthkit.model;

import android.content.Context;
import android.os.Parcelable;
import android.util.ArraySet;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Subscriber;
import defpackage.iqy;
import defpackage.irc;
import health.compact.a.HiDateUtil;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public abstract class DataObservableNoCallback<T extends Parcelable> {
    public final Map<EventTypeInfo, Set<T>> eventInfos = new ConcurrentHashMap();

    public abstract void notifyDataChanged(Object obj);

    public boolean registerObserver(Parcelable parcelable, T t) {
        if (!(parcelable instanceof EventTypeInfo) || !(t instanceof Subscriber)) {
            return false;
        }
        EventTypeInfo eventTypeInfo = (EventTypeInfo) parcelable;
        if (this.eventInfos.get(eventTypeInfo) == null) {
            this.eventInfos.put(eventTypeInfo, new ArraySet());
        }
        if (this.eventInfos.get(eventTypeInfo).contains(t)) {
            return true;
        }
        return this.eventInfos.get(eventTypeInfo).add(t);
    }

    public boolean unregisterObserver(Parcelable parcelable, T t) {
        if (!(parcelable instanceof EventTypeInfo)) {
            return false;
        }
        Set<T> set = this.eventInfos.get((EventTypeInfo) parcelable);
        return set == null || !set.contains(t) || set.remove(t);
    }

    public boolean isEmpty() {
        for (Map.Entry<EventTypeInfo, Set<T>> entry : this.eventInfos.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void clearObserver() {
        this.eventInfos.clear();
    }

    public boolean isAllSubscriberNotified(EventTypeInfo eventTypeInfo) {
        int c = HiDateUtil.c(System.currentTimeMillis());
        for (T t : this.eventInfos.get(eventTypeInfo)) {
            if ((t instanceof Subscriber) && ((Subscriber) t).getLastNotifyDate() != c) {
                return false;
            }
        }
        return true;
    }

    public void apiEventReport(Context context, Subscriber subscriber, String str, int i) {
        new irc(context, new iqy(str, i, subscriber.getAppId(), subscriber.getFilter().getPkgName())).d();
    }

    public boolean isNewObserver(Parcelable parcelable, T t) {
        return this.eventInfos.get(parcelable) == null || !this.eventInfos.get(parcelable).contains(t);
    }
}
