package com.huawei.hms.common.internal;

import java.util.ArrayList;
import java.util.ListIterator;

/* loaded from: classes4.dex */
public class BindResolveClients {
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<ResolveClientBean> f4451a;

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final BindResolveClients f4452a = new BindResolveClients();
    }

    public static BindResolveClients getInstance() {
        return b.f4452a;
    }

    public boolean isClientRegistered(ResolveClientBean resolveClientBean) {
        boolean contains;
        synchronized (b) {
            contains = this.f4451a.contains(resolveClientBean);
        }
        return contains;
    }

    public void notifyClientReconnect() {
        synchronized (b) {
            ListIterator<ResolveClientBean> listIterator = this.f4451a.listIterator();
            while (listIterator.hasNext()) {
                listIterator.next().clientReconnect();
            }
            this.f4451a.clear();
        }
    }

    public void register(ResolveClientBean resolveClientBean) {
        if (resolveClientBean == null) {
            return;
        }
        synchronized (b) {
            if (!this.f4451a.contains(resolveClientBean)) {
                this.f4451a.add(resolveClientBean);
            }
        }
    }

    public void unRegister(ResolveClientBean resolveClientBean) {
        if (resolveClientBean == null) {
            return;
        }
        synchronized (b) {
            if (this.f4451a.contains(resolveClientBean)) {
                ListIterator<ResolveClientBean> listIterator = this.f4451a.listIterator();
                while (true) {
                    if (!listIterator.hasNext()) {
                        break;
                    } else if (resolveClientBean.equals(listIterator.next())) {
                        listIterator.remove();
                        break;
                    }
                }
            }
        }
    }

    public void unRegisterAll() {
        synchronized (b) {
            this.f4451a.clear();
        }
    }

    private BindResolveClients() {
        this.f4451a = new ArrayList<>();
    }
}
