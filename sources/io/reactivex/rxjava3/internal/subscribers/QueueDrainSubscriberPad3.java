package io.reactivex.rxjava3.internal.subscribers;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes10.dex */
class QueueDrainSubscriberPad3 extends QueueDrainSubscriberPad2 {
    final AtomicLong requested = new AtomicLong();

    QueueDrainSubscriberPad3() {
    }
}
