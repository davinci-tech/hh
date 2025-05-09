package io.reactivex.rxjava3.observers;

import com.huawei.hms.network.embedded.g4;
import com.huawei.operation.utils.Constants;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.VolatileSizeArrayList;
import io.reactivex.rxjava3.observers.BaseTestConsumer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes10.dex */
public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> {
    protected boolean checkSubscriptionOnce;
    protected long completions;
    protected Thread lastThread;
    protected CharSequence tag;
    protected boolean timeout;
    protected final List<T> values = new VolatileSizeArrayList();
    protected final List<Throwable> errors = new VolatileSizeArrayList();
    protected final CountDownLatch done = new CountDownLatch(1);

    protected abstract U assertSubscribed();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void dispose();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isDisposed();

    public final List<T> values() {
        return this.values;
    }

    protected final AssertionError fail(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 64);
        sb.append(str);
        sb.append(" (latch = ");
        sb.append(this.done.getCount());
        sb.append(", values = ");
        sb.append(this.values.size());
        sb.append(", errors = ");
        sb.append(this.errors.size());
        sb.append(", completions = ");
        sb.append(this.completions);
        if (this.timeout) {
            sb.append(", timeout!");
        }
        if (isDisposed()) {
            sb.append(", disposed!");
        }
        CharSequence charSequence = this.tag;
        if (charSequence != null) {
            sb.append(", tag = ");
            sb.append(charSequence);
        }
        sb.append(g4.l);
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                assertionError.initCause(this.errors.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.errors));
            }
        }
        return assertionError;
    }

    public final U await() throws InterruptedException {
        if (this.done.getCount() == 0) {
            return this;
        }
        this.done.await();
        return this;
    }

    public final boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z = this.done.getCount() == 0 || this.done.await(j, timeUnit);
        this.timeout = !z;
        return z;
    }

    public final U assertComplete() {
        long j = this.completions;
        if (j == 0) {
            throw fail("Not completed");
        }
        if (j <= 1) {
            return this;
        }
        throw fail("Multiple completions: " + j);
    }

    public final U assertNotComplete() {
        long j = this.completions;
        if (j == 1) {
            throw fail("Completed!");
        }
        if (j <= 1) {
            return this;
        }
        throw fail("Multiple completions: " + j);
    }

    public final U assertNoErrors() {
        if (this.errors.size() == 0) {
            return this;
        }
        throw fail("Error(s) present: " + this.errors);
    }

    public final U assertError(Throwable th) {
        return assertError(Functions.equalsWith(th), true);
    }

    public final U assertError(Class<? extends Throwable> cls) {
        return assertError(Functions.isInstanceOf(cls), true);
    }

    public final U assertError(Predicate<Throwable> predicate) {
        return assertError(predicate, false);
    }

    private U assertError(Predicate<Throwable> predicate, boolean z) {
        int size = this.errors.size();
        if (size == 0) {
            throw fail("No errors");
        }
        Iterator<Throwable> it = this.errors.iterator();
        while (it.hasNext()) {
            try {
                if (predicate.test(it.next())) {
                    if (size == 1) {
                        return this;
                    }
                    if (z) {
                        throw fail("Error present but other errors as well");
                    }
                    throw fail("One error passed the predicate but other errors are present as well");
                }
            } catch (Throwable th) {
                throw ExceptionHelper.wrapOrThrow(th);
            }
        }
        if (z) {
            throw fail("Error not present");
        }
        throw fail("No error(s) passed the predicate");
    }

    public final U assertValue(T t) {
        if (this.values.size() != 1) {
            throw fail("\nexpected: " + valueAndClass(t) + "\ngot: " + this.values);
        }
        T t2 = this.values.get(0);
        if (Objects.equals(t, t2)) {
            return this;
        }
        throw fail("\nexpected: " + valueAndClass(t) + "\ngot: " + valueAndClass(t2));
    }

    public final U assertValue(Predicate<T> predicate) {
        assertValueAt(0, (Predicate) predicate);
        if (this.values.size() <= 1) {
            return this;
        }
        throw fail("The first value passed the predicate but this consumer received more than one value");
    }

    public final U assertValueAt(int i, T t) {
        int size = this.values.size();
        if (size == 0) {
            throw fail("No values");
        }
        if (i < 0 || i >= size) {
            throw fail("Index " + i + " is out of range [0, " + size + Constants.RIGHT_BRACKET_ONLY);
        }
        T t2 = this.values.get(i);
        if (Objects.equals(t, t2)) {
            return this;
        }
        throw fail("\nexpected: " + valueAndClass(t) + "\ngot: " + valueAndClass(t2) + "; Value at position " + i + " differ");
    }

    public final U assertValueAt(int i, Predicate<T> predicate) {
        int size = this.values.size();
        if (size == 0) {
            throw fail("No values");
        }
        if (i < 0 || i >= size) {
            throw fail("Index " + i + " is out of range [0, " + size + Constants.RIGHT_BRACKET_ONLY);
        }
        T t = this.values.get(i);
        try {
            if (predicate.test(t)) {
                return this;
            }
            throw fail("Value " + valueAndClass(t) + " at position " + i + " did not pass the predicate");
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    public static String valueAndClass(Object obj) {
        if (obj == null) {
            return Constants.NULL;
        }
        return obj + " (class: " + obj.getClass().getSimpleName() + Constants.RIGHT_BRACKET_ONLY;
    }

    public final U assertValueCount(int i) {
        int size = this.values.size();
        if (size == i) {
            return this;
        }
        throw fail("\nexpected: " + i + "\ngot: " + size + "; Value counts differ");
    }

    public final U assertNoValues() {
        return assertValueCount(0);
    }

    @SafeVarargs
    public final U assertValues(T... tArr) {
        int size = this.values.size();
        if (size != tArr.length) {
            throw fail("\nexpected: " + tArr.length + " " + Arrays.toString(tArr) + "\ngot: " + size + " " + this.values + "; Value count differs");
        }
        for (int i = 0; i < size; i++) {
            T t = this.values.get(i);
            T t2 = tArr[i];
            if (!Objects.equals(t2, t)) {
                throw fail("\nexpected: " + valueAndClass(t2) + "\ngot: " + valueAndClass(t) + "; Value at position " + i + " differ");
            }
        }
        return this;
    }

    @SafeVarargs
    public final U assertValuesOnly(T... tArr) {
        return (U) assertSubscribed().assertValues(tArr).assertNoErrors().assertNotComplete();
    }

    public final U assertValueSequence(Iterable<? extends T> iterable) {
        boolean hasNext;
        boolean hasNext2;
        Iterator<T> it = this.values.iterator();
        Iterator<? extends T> it2 = iterable.iterator();
        int i = 0;
        while (true) {
            hasNext = it2.hasNext();
            hasNext2 = it.hasNext();
            if (!hasNext2 || !hasNext) {
                break;
            }
            T next = it2.next();
            T next2 = it.next();
            if (!Objects.equals(next, next2)) {
                throw fail("\nexpected: " + valueAndClass(next) + "\ngot: " + valueAndClass(next2) + "; Value at position " + i + " differ");
            }
            i++;
        }
        if (hasNext2) {
            throw fail("More values received than expected (" + i + Constants.RIGHT_BRACKET_ONLY);
        }
        if (!hasNext) {
            return this;
        }
        throw fail("Fewer values received than expected (" + i + Constants.RIGHT_BRACKET_ONLY);
    }

    @SafeVarargs
    public final U assertResult(T... tArr) {
        return (U) assertSubscribed().assertValues(tArr).assertNoErrors().assertComplete();
    }

    @SafeVarargs
    public final U assertFailure(Class<? extends Throwable> cls, T... tArr) {
        return (U) assertSubscribed().assertValues(tArr).assertError(cls).assertNotComplete();
    }

    public final U awaitDone(long j, TimeUnit timeUnit) {
        try {
            if (!this.done.await(j, timeUnit)) {
                this.timeout = true;
                dispose();
            }
            return this;
        } catch (InterruptedException e) {
            dispose();
            throw ExceptionHelper.wrapOrThrow(e);
        }
    }

    public final U assertEmpty() {
        return (U) assertSubscribed().assertNoValues().assertNoErrors().assertNotComplete();
    }

    public final U withTag(CharSequence charSequence) {
        this.tag = charSequence;
        return this;
    }

    public final U awaitCount(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - currentTimeMillis >= 5000) {
                this.timeout = true;
                break;
            }
            if (this.done.getCount() == 0 || this.values.size() >= i) {
                break;
            }
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }
}
