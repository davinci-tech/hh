package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import org.ahocorasick.trie.handler.EmitHandler;

/* loaded from: classes7.dex */
public class uxc {

    /* renamed from: a, reason: collision with root package name */
    private uxf f17569a;
    private uxg c;

    private uxc(uxf uxfVar) {
        this.f17569a = uxfVar;
        this.c = new uxg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        uxg uxgVar = this.c;
        for (char c : str.toCharArray()) {
            Character valueOf = Character.valueOf(c);
            if (this.f17569a.c()) {
                valueOf = Character.valueOf(Character.toLowerCase(valueOf.charValue()));
            }
            uxgVar = uxgVar.c(valueOf);
        }
        if (this.f17569a.c()) {
            str = str.toLowerCase();
        }
        uxgVar.d(str);
    }

    public Collection<uwy> c(CharSequence charSequence) {
        uxd uxdVar = new uxd();
        d(charSequence, uxdVar);
        List<uwy> c = uxdVar.c();
        if (this.f17569a.e()) {
            b(charSequence, c);
        }
        if (this.f17569a.b()) {
            c(charSequence, c);
        }
        if (!this.f17569a.d()) {
            new uwx(c).a(c);
        }
        return c;
    }

    public void d(CharSequence charSequence, EmitHandler emitHandler) {
        uxg uxgVar = this.c;
        for (int i = 0; i < charSequence.length(); i++) {
            Character valueOf = Character.valueOf(charSequence.charAt(i));
            if (this.f17569a.c()) {
                valueOf = Character.valueOf(Character.toLowerCase(valueOf.charValue()));
            }
            uxgVar = d(uxgVar, valueOf);
            if (b(i, uxgVar, emitHandler) && this.f17569a.a()) {
                return;
            }
        }
    }

    private boolean d(CharSequence charSequence, uwy uwyVar) {
        if (uwyVar.getStart() == 0 || !Character.isAlphabetic(charSequence.charAt(uwyVar.getStart() - 1))) {
            return uwyVar.getEnd() + 1 != charSequence.length() && Character.isAlphabetic(charSequence.charAt(uwyVar.getEnd() + 1));
        }
        return true;
    }

    private void b(CharSequence charSequence, List<uwy> list) {
        ArrayList arrayList = new ArrayList();
        for (uwy uwyVar : list) {
            if (d(charSequence, uwyVar)) {
                arrayList.add(uwyVar);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            list.remove((uwy) it.next());
        }
    }

    private void c(CharSequence charSequence, List<uwy> list) {
        long length = charSequence.length();
        ArrayList arrayList = new ArrayList();
        for (uwy uwyVar : list) {
            if ((uwyVar.getStart() != 0 && !Character.isWhitespace(charSequence.charAt(uwyVar.getStart() - 1))) || (uwyVar.getEnd() + 1 != length && !Character.isWhitespace(charSequence.charAt(uwyVar.getEnd() + 1)))) {
                arrayList.add(uwyVar);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            list.remove((uwy) it.next());
        }
    }

    private uxg d(uxg uxgVar, Character ch) {
        uxg d = uxgVar.d(ch);
        while (d == null) {
            uxgVar = uxgVar.d();
            d = uxgVar.d(ch);
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        for (uxg uxgVar : this.c.b()) {
            uxgVar.d(this.c);
            linkedBlockingDeque.add(uxgVar);
        }
        while (!linkedBlockingDeque.isEmpty()) {
            uxg uxgVar2 = (uxg) linkedBlockingDeque.remove();
            for (Character ch : uxgVar2.c()) {
                uxg d = uxgVar2.d(ch);
                linkedBlockingDeque.add(d);
                uxg d2 = uxgVar2.d();
                while (d2.d(ch) == null) {
                    d2 = d2.d();
                }
                uxg d3 = d2.d(ch);
                d.d(d3);
                d.c(d3.a());
            }
        }
    }

    private boolean b(int i, uxg uxgVar, EmitHandler emitHandler) {
        Collection<String> a2 = uxgVar.a();
        boolean z = false;
        if (a2 != null && !a2.isEmpty()) {
            for (String str : a2) {
                emitHandler.emit(new uwy((i - str.length()) + 1, i, str));
                z = true;
            }
        }
        return z;
    }

    public static b b() {
        return new b();
    }

    /* loaded from: classes10.dex */
    public static class b {
        private uxc d;
        private uxf e;

        private b() {
            this.e = new uxf();
            this.d = new uxc(this.e);
        }

        public b a(String str) {
            this.d.e(str);
            return this;
        }

        public uxc b() {
            this.d.e();
            return this.d;
        }
    }
}
