package com.huawei.agconnect.apms.collect.model.event;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
public class Events<T extends Event> extends CollectableArray {
    private final Collection<T> events = new CopyOnWriteArrayList();

    public void add(T t) {
        synchronized (this) {
            this.events.add(t);
        }
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        for (T t : this.events) {
            if (t != null) {
                jsonArray.add(t.asJsonArray());
            }
        }
        return jsonArray;
    }

    public void clear() {
        this.events.clear();
    }

    public int count() {
        return this.events.size();
    }

    public Collection<T> getEvents() {
        return this.events;
    }

    public void remove(T t) {
        synchronized (this) {
            this.events.remove(t);
        }
    }
}
