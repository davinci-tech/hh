package com.huawei.hms.common.size;

import com.huawei.hms.common.internal.Objects;

/* loaded from: classes9.dex */
public class Size {

    /* renamed from: a, reason: collision with root package name */
    private final int f4468a;
    private final int b;

    public Size(int i, int i2) {
        this.f4468a = i;
        this.b = i2;
    }

    public static Size parseSize(String str) {
        try {
            int indexOf = str.indexOf("x");
            if (indexOf < 0) {
                indexOf = str.indexOf("*");
            }
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (Exception unused) {
            throw new IllegalArgumentException("Size parses failed");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.f4468a == size.f4468a && this.b == size.b;
    }

    public final int getHeight() {
        return this.b;
    }

    public final int getWidth() {
        return this.f4468a;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
    }

    public final String toString() {
        return "Width is " + this.f4468a + " Height is " + this.b;
    }
}
