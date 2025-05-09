package org.apache.commons.io.input;

import com.google.android.gms.fitness.data.WorkoutExercises;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* loaded from: classes10.dex */
final class ByteBufferCleaner {
    private static final Cleaner INSTANCE = getCleaner();

    interface Cleaner {
        void clean(ByteBuffer byteBuffer) throws ReflectiveOperationException;
    }

    ByteBufferCleaner() {
    }

    static final class Java8Cleaner implements Cleaner {
        private final Method cleanMethod;
        private final Method cleanerMethod;

        private Java8Cleaner() throws ReflectiveOperationException, SecurityException {
            this.cleanMethod = Class.forName("sun.misc.Cleaner").getMethod(WorkoutExercises.CLEAN, new Class[0]);
            this.cleanerMethod = Class.forName("sun.nio.ch.DirectBuffer").getMethod("cleaner", new Class[0]);
        }

        @Override // org.apache.commons.io.input.ByteBufferCleaner.Cleaner
        public void clean(ByteBuffer byteBuffer) throws ReflectiveOperationException {
            Object invoke = this.cleanerMethod.invoke(byteBuffer, new Object[0]);
            if (invoke != null) {
                this.cleanMethod.invoke(invoke, new Object[0]);
            }
        }
    }

    static final class Java9Cleaner implements Cleaner {
        private final Method invokeCleaner;
        private final Object theUnsafe;

        private Java9Cleaner() throws ReflectiveOperationException, SecurityException {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            this.theUnsafe = declaredField.get(null);
            this.invokeCleaner = cls.getMethod("invokeCleaner", ByteBuffer.class);
        }

        @Override // org.apache.commons.io.input.ByteBufferCleaner.Cleaner
        public void clean(ByteBuffer byteBuffer) throws ReflectiveOperationException {
            this.invokeCleaner.invoke(this.theUnsafe, byteBuffer);
        }
    }

    static void clean(ByteBuffer byteBuffer) {
        try {
            INSTANCE.clean(byteBuffer);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to clean direct buffer.", e);
        }
    }

    private static Cleaner getCleaner() {
        try {
            return new Java8Cleaner();
        } catch (Exception e) {
            try {
                return new Java9Cleaner();
            } catch (Exception unused) {
                throw new IllegalStateException("Failed to initialize a Cleaner.", e);
            }
        }
    }

    static boolean isSupported() {
        return INSTANCE != null;
    }
}
