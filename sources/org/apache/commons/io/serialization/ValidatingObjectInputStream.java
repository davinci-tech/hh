package org.apache.commons.io.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/* loaded from: classes10.dex */
public class ValidatingObjectInputStream extends ObjectInputStream {
    private final List<ClassNameMatcher> acceptMatchers;
    private final List<ClassNameMatcher> rejectMatchers;

    public ValidatingObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        this.acceptMatchers = new ArrayList();
        this.rejectMatchers = new ArrayList();
    }

    static /* synthetic */ FullClassNameMatcher lambda$accept$0(Class cls) {
        return new FullClassNameMatcher(cls.getName());
    }

    public ValidatingObjectInputStream accept(Class<?>... clsArr) {
        Stream map = Stream.of((Object[]) clsArr).map(new Function() { // from class: org.apache.commons.io.serialization.ValidatingObjectInputStream$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ValidatingObjectInputStream.lambda$accept$0((Class) obj);
            }
        });
        List<ClassNameMatcher> list = this.acceptMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ValidatingObjectInputStream$$ExternalSyntheticLambda3(list));
        return this;
    }

    public ValidatingObjectInputStream accept(ClassNameMatcher classNameMatcher) {
        this.acceptMatchers.add(classNameMatcher);
        return this;
    }

    public ValidatingObjectInputStream accept(Pattern pattern) {
        this.acceptMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }

    public ValidatingObjectInputStream accept(String... strArr) {
        Stream map = Stream.of((Object[]) strArr).map(new ValidatingObjectInputStream$$ExternalSyntheticLambda0());
        List<ClassNameMatcher> list = this.acceptMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ValidatingObjectInputStream$$ExternalSyntheticLambda1(list));
        return this;
    }

    private void checkClassName(String str) throws InvalidClassException {
        Iterator<ClassNameMatcher> it = this.rejectMatchers.iterator();
        while (it.hasNext()) {
            if (it.next().matches(str)) {
                invalidClassNameFound(str);
            }
        }
        Iterator<ClassNameMatcher> it2 = this.acceptMatchers.iterator();
        while (it2.hasNext()) {
            if (it2.next().matches(str)) {
                return;
            }
        }
        invalidClassNameFound(str);
    }

    protected void invalidClassNameFound(String str) throws InvalidClassException {
        throw new InvalidClassException("Class name not accepted: " + str);
    }

    static /* synthetic */ FullClassNameMatcher lambda$reject$1(Class cls) {
        return new FullClassNameMatcher(cls.getName());
    }

    public ValidatingObjectInputStream reject(Class<?>... clsArr) {
        Stream map = Stream.of((Object[]) clsArr).map(new Function() { // from class: org.apache.commons.io.serialization.ValidatingObjectInputStream$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ValidatingObjectInputStream.lambda$reject$1((Class) obj);
            }
        });
        List<ClassNameMatcher> list = this.rejectMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ValidatingObjectInputStream$$ExternalSyntheticLambda3(list));
        return this;
    }

    public ValidatingObjectInputStream reject(ClassNameMatcher classNameMatcher) {
        this.rejectMatchers.add(classNameMatcher);
        return this;
    }

    public ValidatingObjectInputStream reject(Pattern pattern) {
        this.rejectMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }

    public ValidatingObjectInputStream reject(String... strArr) {
        Stream map = Stream.of((Object[]) strArr).map(new ValidatingObjectInputStream$$ExternalSyntheticLambda0());
        List<ClassNameMatcher> list = this.rejectMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ValidatingObjectInputStream$$ExternalSyntheticLambda1(list));
        return this;
    }

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        checkClassName(objectStreamClass.getName());
        return super.resolveClass(objectStreamClass);
    }
}
