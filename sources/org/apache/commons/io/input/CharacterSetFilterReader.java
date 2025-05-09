package org.apache.commons.io.input;

import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;

/* loaded from: classes10.dex */
public class CharacterSetFilterReader extends AbstractCharacterFilterReader {
    private static IntPredicate toIntPredicate(Set<Integer> set) {
        if (set == null) {
            return SKIP_NONE;
        }
        final Set unmodifiableSet = Collections.unmodifiableSet(set);
        return new IntPredicate() { // from class: org.apache.commons.io.input.CharacterSetFilterReader$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                boolean contains;
                contains = unmodifiableSet.contains(Integer.valueOf(i));
                return contains;
            }
        };
    }

    public CharacterSetFilterReader(Reader reader, Integer... numArr) {
        this(reader, new HashSet(Arrays.asList(numArr)));
    }

    public CharacterSetFilterReader(Reader reader, Set<Integer> set) {
        super(reader, toIntPredicate(set));
    }
}
