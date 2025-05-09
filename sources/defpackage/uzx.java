package defpackage;

/* loaded from: classes7.dex */
public class uzx<T> {
    private final String key;
    private final Class<T> valueType;

    public uzx(String str, Class<T> cls) {
        this(str, cls, null);
    }

    public uzx(String str, Class<T> cls, vac<uzx<?>> vacVar) {
        if (str == null) {
            throw new NullPointerException("Key must not be null!");
        }
        if (cls == null) {
            throw new NullPointerException("Value Type must not be null!");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty!");
        }
        this.key = str;
        this.valueType = cls;
        if (vacVar != null) {
            vacVar.d(this);
        }
    }

    public final Class<T> getValueType() {
        return this.valueType;
    }

    public final String getKey() {
        return this.key;
    }

    public String toString() {
        return this.key;
    }
}
