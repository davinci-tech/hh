package org.eclipse.californium.elements.config;

import defpackage.uzx;
import defpackage.vbh;
import defpackage.vha;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public abstract class DocumentedDefinition<T> extends uzx<T> {
    protected static final Logger LOGGER = vha.a((Class<?>) DocumentedDefinition.class);
    private final T defaultValue;
    private final String documentation;

    public T checkValue(T t) throws vbh {
        return t;
    }

    protected abstract T parseValue(String str) throws vbh;

    protected boolean useTrim() {
        return true;
    }

    public abstract String writeValue(T t);

    public DocumentedDefinition(String str, String str2, Class<T> cls, T t) {
        super(str, cls);
        this.documentation = str2;
        this.defaultValue = t;
    }

    public String getTypeName() {
        return getValueType().getSimpleName();
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public T readValue(String str) {
        String format;
        if (str == null) {
            throw new NullPointerException(String.format("Key '%s': textual value must not be null!", getKey()));
        }
        if (useTrim()) {
            str = str.trim();
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException(String.format("Key '%s': textual value must not be empty!", getKey()));
        }
        try {
            return checkValue(parseValue(str));
        } catch (NumberFormatException unused) {
            format = String.format("Key '%s': value '%s' is no %s", getKey(), str, getTypeName());
            throw new IllegalArgumentException(format);
        } catch (IllegalArgumentException e) {
            format = String.format("Key '%s': value '%s' %s", getKey(), str, e.getMessage());
            throw new IllegalArgumentException(format);
        } catch (vbh e2) {
            format = String.format("Key '%s': %s", getKey(), e2.getMessage());
            throw new IllegalArgumentException(format);
        }
    }

    public boolean isAssignableFrom(Object obj) {
        return getValueType().isInstance(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected Object checkRawValue(Object obj) throws vbh {
        return checkValue(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected String write(Object obj) {
        return writeValue(obj);
    }
}
