package defpackage;

import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes7.dex */
public class skm<E> extends LinkedBlockingQueue<E> {
    @Override // java.util.concurrent.LinkedBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e) {
        if (size() < 7) {
            return super.offer(e);
        }
        return true;
    }
}
