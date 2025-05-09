package com.huawei.animationkit.computationalwallpaper.pattern.variable;

/* loaded from: classes8.dex */
public interface Variable {

    public interface VariableObserver {
        void onUpdate();
    }

    default void updateFloat(String str, float f) {
        throw new UnsupportedOperationException();
    }

    default void updateInt(String str, int i) {
        throw new UnsupportedOperationException();
    }

    default float nextFloat(String str, float f, float f2) {
        throw new UnsupportedOperationException();
    }

    default int nextInt(String str, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    default float nextFraction(String str) {
        throw new UnsupportedOperationException();
    }

    default void addUpdateObserver(String str, VariableObserver variableObserver) {
        throw new UnsupportedOperationException();
    }

    public static class d extends RuntimeException {
        private static final long serialVersionUID = 698817886654350404L;

        public d(String str) {
            super("Variable " + str + " is not exist");
        }
    }

    public static class b extends RuntimeException {
        private static final long serialVersionUID = -4391436704975842462L;

        public b(String str) {
            super("Variable " + str + "  does not match current data type");
        }
    }
}
