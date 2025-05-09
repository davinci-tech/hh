package org.apache.commons.io.serialization;

import java.util.Objects;
import java.util.regex.Pattern;

/* loaded from: classes10.dex */
final class RegexpClassNameMatcher implements ClassNameMatcher {
    private final Pattern pattern;

    public RegexpClassNameMatcher(Pattern pattern) {
        this.pattern = (Pattern) Objects.requireNonNull(pattern, "pattern");
    }

    public RegexpClassNameMatcher(String str) {
        this(Pattern.compile(str));
    }

    @Override // org.apache.commons.io.serialization.ClassNameMatcher
    public boolean matches(String str) {
        return this.pattern.matcher(str).matches();
    }
}
