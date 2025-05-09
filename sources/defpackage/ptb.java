package defpackage;

import java.util.List;

/* loaded from: classes6.dex */
public class ptb {
    private final int c;
    private final List<pti> d;
    private final String e;

    public ptb(int i, String str, List<pti> list) {
        this.c = i;
        this.e = str;
        this.d = list;
    }

    public int d() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public List<pti> b() {
        return this.d;
    }

    public String toString() {
        return "QuestionBean [questionId=" + this.c + ", description=" + this.e + ", questionOptions=" + this.d + "]";
    }
}
