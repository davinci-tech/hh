package org.ahocorasick.trie;

import defpackage.uwy;

/* loaded from: classes10.dex */
public abstract class Token {
    private String fragment;

    public abstract uwy getEmit();

    public abstract boolean isMatch();

    public Token(String str) {
        this.fragment = str;
    }

    public String getFragment() {
        return this.fragment;
    }
}
