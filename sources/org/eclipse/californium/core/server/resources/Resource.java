package org.eclipse.californium.core.server.resources;

import defpackage.uzy;
import java.util.Collection;
import java.util.concurrent.Executor;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.observe.ObserveRelation;

/* loaded from: classes10.dex */
public interface Resource {
    void add(Resource resource);

    void addObserveRelation(ObserveRelation observeRelation);

    void addObserver(ResourceObserver resourceObserver);

    boolean delete(Resource resource);

    uzy getAttributes();

    Resource getChild(String str);

    Collection<Resource> getChildren();

    Executor getExecutor();

    String getName();

    Resource getParent();

    String getPath();

    String getURI();

    void handleRequest(Exchange exchange);

    boolean isCachable();

    boolean isObservable();

    boolean isVisible();

    void removeObserveRelation(ObserveRelation observeRelation);

    void removeObserver(ResourceObserver resourceObserver);

    void setName(String str);

    void setParent(Resource resource);

    void setPath(String str);
}
