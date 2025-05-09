package com.google.protobuf;

import java.nio.Buffer;

/* loaded from: classes8.dex */
final class Java8Compatibility {
    static void clear(Buffer buffer) {
        buffer.clear();
    }

    static void flip(Buffer buffer) {
        buffer.flip();
    }

    static void limit(Buffer buffer, int i) {
        buffer.limit(i);
    }

    static void mark(Buffer buffer) {
        buffer.mark();
    }

    static void position(Buffer buffer, int i) {
        buffer.position(i);
    }

    static void reset(Buffer buffer) {
        buffer.reset();
    }

    private Java8Compatibility() {
    }
}
