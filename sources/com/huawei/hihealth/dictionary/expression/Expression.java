package com.huawei.hihealth.dictionary.expression;

import health.compact.a.util.LogUtil;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/* loaded from: classes.dex */
public class Expression {
    private Set<String> c;
    private List<Token> e;

    public Expression(List<Token> list, Set<String> set) {
        this.e = list;
        this.c = set;
    }

    public Set<String> b() {
        return this.c;
    }

    public double b(Map<String, Double> map) {
        Set<String> set = this.c;
        if (set != null && !set.isEmpty() && (map == null || !map.keySet().containsAll(this.c))) {
            throw new IllegalArgumentException("paramMap does not contain all params.");
        }
        return e(this.e, map);
    }

    public boolean a(Map<String, Double> map) {
        try {
            return b(map) > 0.0d;
        } catch (IllegalArgumentException e) {
            LogUtil.c("HiH_Expression", "illegal argument：", e.getMessage());
            return false;
        } catch (Exception unused) {
            LogUtil.c("HiH_Expression", "unknown error.");
            return false;
        }
    }

    private double e(List<Token> list, Map<String, Double> map) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("expressStack is null or empty.");
        }
        if (list.size() == 1) {
            return Double.valueOf(this.e.get(0).d()).doubleValue();
        }
        Stack stack = new Stack();
        double d = 0.0d;
        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            stack.push(token);
            if (token.a() == 0) {
                if (stack.size() < 3) {
                    throw new IllegalArgumentException("expression is illegal.");
                }
                d = c((Token) stack.pop(), (Token) stack.pop(), (Token) stack.pop(), map);
                stack.push(new Token(2, d + ""));
            }
        }
        return d;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00e7, code lost:
    
        if (r9.equals(org.slf4j.Marker.ANY_NON_NULL_MARKER) == false) goto L74;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:40:0x014a A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0147 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static double c(com.huawei.hihealth.dictionary.expression.Token r6, com.huawei.hihealth.dictionary.expression.Token r7, com.huawei.hihealth.dictionary.expression.Token r8, java.util.Map<java.lang.String, java.lang.Double> r9) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealth.dictionary.expression.Expression.c(com.huawei.hihealth.dictionary.expression.Token, com.huawei.hihealth.dictionary.expression.Token, com.huawei.hihealth.dictionary.expression.Token, java.util.Map):double");
    }
}
