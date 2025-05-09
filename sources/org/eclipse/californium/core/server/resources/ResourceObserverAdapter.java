package org.eclipse.californium.core.server.resources;

import org.eclipse.californium.core.observe.ObserveRelation;

/* loaded from: classes10.dex */
public abstract class ResourceObserverAdapter implements ResourceObserver {
    @Override // org.eclipse.californium.core.server.resources.ResourceObserver
    public void addedChild(Resource resource) {
    }

    @Override // org.eclipse.californium.core.server.resources.ResourceObserver
    public void addedObserveRelation(ObserveRelation observeRelation) {
    }

    @Override // org.eclipse.californium.core.server.resources.ResourceObserver
    public void changedName(String str) {
    }

    @Override // org.eclipse.californium.core.server.resources.ResourceObserver
    public void changedPath(String str) {
    }

    @Override // org.eclipse.californium.core.server.resources.ResourceObserver
    public void removedChild(Resource resource) {
    }

    @Override // org.eclipse.californium.core.server.resources.ResourceObserver
    public void removedObserveRelation(ObserveRelation observeRelation) {
    }
}
