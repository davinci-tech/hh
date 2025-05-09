package org.eclipse.californium.core.observe;

/* loaded from: classes7.dex */
public interface ObserveHealth {
    void receivingCancelRequest();

    void receivingObserveRequest();

    void receivingReject();

    void setObserveEndpoints(int i);

    void setObserveRelations(int i);
}
