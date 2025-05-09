package io.reactivex.rxjava3.internal.subscribers;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes10.dex */
class QueueDrainSubscriberWip extends QueueDrainSubscriberPad0 {
    final AtomicInteger wip = new AtomicInteger();

    QueueDrainSubscriberWip() {
    }
}
