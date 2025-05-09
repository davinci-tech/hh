package org.eclipse.californium.elements;

/* loaded from: classes7.dex */
public interface EndpointContextMatcher extends EndpointIdentityResolver {
    @Override // org.eclipse.californium.elements.EndpointIdentityResolver
    String getName();

    boolean isResponseRelatedToRequest(EndpointContext endpointContext, EndpointContext endpointContext2);

    boolean isToBeSent(EndpointContext endpointContext, EndpointContext endpointContext2);

    String toRelevantState(EndpointContext endpointContext);
}
