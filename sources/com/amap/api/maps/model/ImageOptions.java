package com.amap.api.maps.model;

/* loaded from: classes8.dex */
public class ImageOptions {
    public int color;
    public float radius;
    public int type;

    public enum ShapeType {
        CIRCLE;

        private int index = 0;

        /* JADX WARN: Incorrect types in method signature: (I)V */
        ShapeType(String str) {
        }

        public final int value() {
            return this.index;
        }
    }
}
