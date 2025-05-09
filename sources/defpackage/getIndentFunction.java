package defpackage;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\f\u0010\u0013\u001a\u00020\u0002*\u00020\u0002H\u0007\u001a\u0016\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002H\u0007¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
/* renamed from: ujv, reason: from Kotlin metadata */
/* loaded from: classes7.dex */
public class getIndentFunction extends append {
    public static /* synthetic */ String a(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return ujy.d(str, str2);
    }

    public static final String d(String str, String str2) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        return ujy.a(str, "", str2);
    }

    public static final String a(String str, String str2, String str3) {
        Iterator<T> it;
        Appendable b;
        int i;
        String str4;
        String str5;
        String substring;
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        if (!(!ujy.a((CharSequence) str3))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List<String> c = ujy.c((CharSequence) str);
        int length = str.length();
        int length2 = str2.length();
        int size = c.size();
        Function1<String, String> e = e(str2);
        int e2 = ufe.e((List) c);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (Object obj : c) {
            if (i2 < 0) {
                ufe.e();
            }
            String str6 = (String) obj;
            if ((i2 == 0 || i2 == e2) && ujy.a((CharSequence) str6)) {
                i = i2;
                str4 = null;
            } else {
                int length3 = str6.length();
                int i3 = 0;
                while (true) {
                    if (i3 >= length3) {
                        i3 = -1;
                        break;
                    }
                    if (!ujm.d(r0.charAt(i3))) {
                        break;
                    }
                    i3++;
                }
                if (i3 == -1) {
                    str5 = str6;
                    i = i2;
                } else {
                    int i4 = i3;
                    str5 = str6;
                    i = i2;
                    if (ujy.c(str6, str3, i3, false, 4, (Object) null)) {
                        int length4 = str3.length();
                        uhy.b((Object) str5, "");
                        substring = str5.substring(i4 + length4);
                        uhy.a(substring, "");
                        if (substring != null || (str4 = e.invoke(substring)) == null) {
                            str4 = str5;
                        }
                    }
                }
                substring = null;
                if (substring != null) {
                }
                str4 = str5;
            }
            if (str4 != null) {
                arrayList.add(str4);
            }
            i2 = i + 1;
        }
        b = ufe.b(arrayList, new StringBuilder(length + (length2 * size)), (r14 & 2) != 0 ? ", " : "\n", (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : null);
        String sb = ((StringBuilder) b).toString();
        uhy.a(sb, "");
        return sb;
    }

    public static final String a(String str) {
        uhy.e((Object) str, "");
        return ujy.e(str, "");
    }

    public static final String e(String str, String str2) {
        Appendable b;
        String invoke;
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        List<String> c = ujy.c((CharSequence) str);
        List<String> list = c;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!ujy.a((CharSequence) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(ufe.d(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(Integer.valueOf(b((String) it.next())));
        }
        Integer num = (Integer) ufe.c((Iterable) arrayList3);
        int i = 0;
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length();
        int length2 = str2.length();
        int size = c.size();
        Function1<String, String> e = e(str2);
        int e2 = ufe.e((List) c);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : list) {
            if (i < 0) {
                ufe.e();
            }
            String str3 = (String) obj2;
            if ((i == 0 || i == e2) && ujy.a((CharSequence) str3)) {
                str3 = null;
            } else {
                String b2 = ujy.b(str3, intValue);
                if (b2 != null && (invoke = e.invoke(b2)) != null) {
                    str3 = invoke;
                }
            }
            if (str3 != null) {
                arrayList4.add(str3);
            }
            i++;
        }
        b = ufe.b(arrayList4, new StringBuilder(length + (length2 * size)), (r14 & 2) != 0 ? ", " : "\n", (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : null);
        String sb = ((StringBuilder) b).toString();
        uhy.a(sb, "");
        return sb;
    }

    private static final int b(String str) {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (!ujm.d(r0.charAt(i))) {
                break;
            }
            i++;
        }
        return i == -1 ? str.length() : i;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "line", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: ujv$a */
    static final class a extends Lambda implements Function1<String, String> {
        final /* synthetic */ String e;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            uhy.e((Object) str, "");
            return this.e + str;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(1);
            this.e = str;
        }
    }

    private static final Function1<String, String> e(String str) {
        return str.length() == 0 ? d.b : new a(str);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "line", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: ujv$d */
    static final class d extends Lambda implements Function1<String, String> {
        public static final d b = new d();

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            uhy.e((Object) str, "");
            return str;
        }

        d() {
            super(1);
        }
    }
}
