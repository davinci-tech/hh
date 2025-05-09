package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import org.slf4j.Marker;

/* loaded from: classes8.dex */
public class dcn {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11585a;
    private Stack<String> b;
    private boolean c;
    private boolean d;
    private String e;
    private String g;
    private Map<String, Object> i;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final dcn f11586a = new dcn();
    }

    private dcn() {
        this.d = false;
        this.e = "";
        this.g = "";
        this.f11585a = false;
        this.c = false;
    }

    public static dcn c() {
        return a.f11586a;
    }

    public boolean a(String str, Map<String, Object> map) {
        this.i = map;
        if (map.isEmpty()) {
            LogUtil.a("MassageGunRulesFilteringUtil", "params is null");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("MassageGunRulesFilteringUtil", "matchingRules is null");
            return false;
        }
        Stack<String> stack = new Stack<>();
        int length = str.length();
        for (int length2 = str.length(); length2 >= 1; length2--) {
            length = e(str, stack, length, length2, str.substring(length2 - 1, length2));
            if (length2 > 2) {
                length = c(str, stack, length, length2, str.substring(length2 - 2, length2));
            }
        }
        if (length != 0) {
            stack.add(str.substring(0, length).replaceAll(" ", ""));
        }
        return b(stack);
    }

    private int c(String str, Stack<String> stack, int i, int i2, String str2) {
        char c;
        str2.hashCode();
        int hashCode = str2.hashCode();
        if (hashCode == 1084) {
            if (str2.equals("!=")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 1216) {
            if (str2.equals("&&")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 1921) {
            if (str2.equals(HiDataFilter.DataFilterExpression.LESS_EQUAL)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == 1952) {
            if (str2.equals("==")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 1983) {
            if (hashCode == 3968 && str2.equals("||")) {
                c = 5;
            }
            c = 65535;
        } else {
            if (str2.equals(HiDataFilter.DataFilterExpression.BIGGER_EQUAL)) {
                c = 4;
            }
            c = 65535;
        }
        if ((c != 0 && c != 1 && c != 2 && c != 3 && c != 4 && c != 5) || i < i2) {
            return i;
        }
        String replaceAll = str.substring(i2, i).trim().replaceAll(" ", "");
        if (!TextUtils.isEmpty(replaceAll)) {
            stack.add(replaceAll);
        }
        stack.add(str2);
        return i2 - 2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private int e(String str, Stack<String> stack, int i, int i2, String str2) {
        char c;
        str2.hashCode();
        int hashCode = str2.hashCode();
        if (hashCode == 45) {
            if (str2.equals(Constants.LINK)) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode == 47) {
            if (str2.equals("/")) {
                c = 5;
            }
            c = 65535;
        } else if (hashCode == 60) {
            if (str2.equals(HiDataFilter.DataFilterExpression.LESS_THAN)) {
                c = 6;
            }
            c = 65535;
        } else if (hashCode != 62) {
            switch (hashCode) {
                case 40:
                    if (str2.equals(com.huawei.operation.utils.Constants.LEFT_BRACKET_ONLY)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 41:
                    if (str2.equals(com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 42:
                    if (str2.equals("*")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 43:
                    if (str2.equals(Marker.ANY_NON_NULL_MARKER)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str2.equals(HiDataFilter.DataFilterExpression.BIGGER_THAN)) {
                c = 7;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                if (i >= i2) {
                    String replaceAll = str.substring(i2, i).trim().replaceAll(" ", "");
                    if (!TextUtils.isEmpty(replaceAll)) {
                        stack.add(replaceAll);
                    }
                    stack.add(str2);
                    break;
                } else {
                    return i;
                }
            case 1:
                stack.add(str2);
                break;
            default:
                return i;
        }
        return i2 - 1;
    }

    private boolean b(Stack<String> stack) {
        LogUtil.a("MassageGunRulesFilteringUtil", "judge");
        String pop = !stack.isEmpty() ? stack.pop() : null;
        String pop2 = !stack.isEmpty() ? stack.pop() : null;
        String pop3 = stack.isEmpty() ? null : stack.pop();
        if (b(pop, pop2, pop3, stack)) {
            return b(stack);
        }
        return d(stack, pop, pop2, pop3);
    }

    private boolean d(Stack<String> stack, String str, String str2, String str3) {
        char c;
        if (TextUtils.isEmpty(str2)) {
            return str != null && str.toLowerCase(Locale.ENGLISH).equals("true");
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            LogUtil.a("MassageGunRulesFilteringUtil", "leftValue or rightValue is null");
            return false;
        }
        str2.hashCode();
        int hashCode = str2.hashCode();
        if (hashCode == 42) {
            if (str2.equals("*")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 43) {
            if (str2.equals(Marker.ANY_NON_NULL_MARKER)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 45) {
            if (str2.equals(Constants.LINK)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == 47) {
            if (str2.equals("/")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode == 60) {
            if (str2.equals(HiDataFilter.DataFilterExpression.LESS_THAN)) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode == 62) {
            if (str2.equals(HiDataFilter.DataFilterExpression.BIGGER_THAN)) {
                c = 5;
            }
            c = 65535;
        } else if (hashCode == 1084) {
            if (str2.equals("!=")) {
                c = 6;
            }
            c = 65535;
        } else if (hashCode == 1216) {
            if (str2.equals("&&")) {
                c = 7;
            }
            c = 65535;
        } else if (hashCode == 1921) {
            if (str2.equals(HiDataFilter.DataFilterExpression.LESS_EQUAL)) {
                c = '\b';
            }
            c = 65535;
        } else if (hashCode == 1952) {
            if (str2.equals("==")) {
                c = '\t';
            }
            c = 65535;
        } else if (hashCode != 1983) {
            if (hashCode == 3968 && str2.equals("||")) {
                c = 11;
            }
            c = 65535;
        } else {
            if (str2.equals(HiDataFilter.DataFilterExpression.BIGGER_EQUAL)) {
                c = '\n';
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return e(stack, c(a(str), a(str3)));
            case 1:
                return c(stack, a(a(str), a(str3)));
            case 2:
                return d(stack, b(a(str), a(str3)));
            case 3:
                return b(stack, e(a(str), a(str3)));
            case 4:
                return e(stack, str, str3, -1);
            case 5:
                return e(stack, str, str3, 1);
            case 6:
                return a(stack, str, str3, 0);
            case 7:
                return a(stack, str, str3);
            case '\b':
                return a(stack, str, str3, 1);
            case '\t':
                return e(stack, str, str3, 0);
            case '\n':
                return a(stack, str, str3, -1);
            case 11:
                return c(stack, str, str3);
            default:
                return false;
        }
    }

    private boolean b(String str, String str2, String str3, Stack<String> stack) {
        LogUtil.a("MassageGunRulesFilteringUtil", "checkBracket");
        if (this.b == null) {
            this.b = new Stack<>();
        }
        if (str != null) {
            str.hashCode();
            if (str.equals(com.huawei.operation.utils.Constants.LEFT_BRACKET_ONLY)) {
                LogUtil.a("MassageGunRulesFilteringUtil", "leftValue is LEFT_BRACKET");
                stack.add(str3);
                stack.add(str2);
                return true;
            }
            if (str.equals(com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY)) {
                LogUtil.a("MassageGunRulesFilteringUtil", "leftValue is RIGHT_BRACKET");
                stack.add(str2);
                stack.add(str);
                return true;
            }
        }
        if (com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY.equals(str2)) {
            LogUtil.a("MassageGunRulesFilteringUtil", "operator is RIGHT_BRACKET ", Boolean.valueOf(this.c));
            if (this.c) {
                this.c = false;
                stack.add(this.b.pop());
                stack.add(this.b.pop());
            }
            if (!TextUtils.isEmpty(str3)) {
                stack.add(str3);
            }
            stack.add(str);
            return true;
        }
        if (!com.huawei.operation.utils.Constants.LEFT_BRACKET_ONLY.equals(str3)) {
            return false;
        }
        LogUtil.a("MassageGunRulesFilteringUtil", "rightValue is LEFT_BRACKET");
        this.c = true;
        this.b.add(str2);
        this.b.add(str);
        return true;
    }

    private boolean b(Stack<String> stack, String str) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doDividedAction");
        a(stack);
        stack.add(str);
        return b(stack);
    }

    private boolean e(Stack<String> stack, String str) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doMultiplyingAction");
        a(stack);
        stack.add(str);
        return b(stack);
    }

    private boolean d(Stack<String> stack, String str) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doMinusAction");
        a(stack);
        stack.add(str);
        return b(stack);
    }

    private boolean c(Stack<String> stack, String str) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doAddAction");
        a(stack);
        stack.add(str);
        return b(stack);
    }

    private boolean a(Stack<String> stack, String str, String str2, int i) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doUnequalRelationalOperationAction");
        a(stack);
        stack.add(String.valueOf(d(a(str), a(str2)) != i));
        return b(stack);
    }

    private boolean e(Stack<String> stack, String str, String str2, int i) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doRelationalOperationAction");
        a(stack);
        stack.add(String.valueOf(d(a(str), a(str2)) == i));
        return b(stack);
    }

    private boolean c(Stack<String> stack, String str, String str2) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doOrAction");
        boolean z = true;
        if ((TextUtils.isEmpty(str2) || !"true".toLowerCase(Locale.ENGLISH).equals(str2)) && !"false".toLowerCase(Locale.ENGLISH).equals(str2)) {
            this.g = str;
            this.f11585a = true;
            stack.add(str2);
        } else {
            this.f11585a = false;
            if (!"true".toLowerCase(Locale.ENGLISH).equals(str) && !"true".toLowerCase(Locale.ENGLISH).equals(str2)) {
                z = false;
            }
            stack.add(String.valueOf(z));
        }
        return b(stack);
    }

    private boolean a(Stack<String> stack, String str, String str2) {
        LogUtil.a("MassageGunRulesFilteringUtil", "doAndAction");
        if ((TextUtils.isEmpty(str2) || !"true".toLowerCase(Locale.ENGLISH).equals(str2)) && !"false".toLowerCase(Locale.ENGLISH).equals(str2)) {
            this.e = str;
            this.d = true;
            stack.add(str2);
        } else {
            this.d = false;
            stack.add(String.valueOf("true".toLowerCase(Locale.ENGLISH).equals(str) && "true".toLowerCase(Locale.ENGLISH).equals(str2)));
        }
        return b(stack);
    }

    private Stack<String> a(Stack<String> stack) {
        LogUtil.a("MassageGunRulesFilteringUtil", "isNeedAddAndOrOrResult isAndBeforeResult: ", Boolean.valueOf(this.d), " isOrBeforeResult: ", Boolean.valueOf(this.f11585a));
        if (this.d) {
            stack.add(this.e);
            stack.add("&&");
        }
        if (this.f11585a) {
            stack.add(this.g);
            stack.add("||");
        }
        return stack;
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("MassageGunRulesFilteringUtil", "key is null");
            return "";
        }
        LogUtil.a("MassageGunRulesFilteringUtil", "getValueFromMap key ", str);
        return str.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$") ? str : this.i.containsKey(str) ? String.valueOf(this.i.get(str)) : "";
    }

    private String d(String str) {
        return TextUtils.isEmpty(str) ? String.valueOf(0) : str;
    }

    private String a(String str, String str2) {
        return new BigDecimal(d(str)).add(new BigDecimal(d(str2))).toString();
    }

    private String b(String str, String str2) {
        return new BigDecimal(d(str)).subtract(new BigDecimal(d(str2))).toString();
    }

    private String c(String str, String str2) {
        return new BigDecimal(d(str)).multiply(new BigDecimal(d(str2))).toString();
    }

    private String e(String str, String str2) {
        return b(str, str2, RoundingMode.DOWN);
    }

    private String b(String str, String str2, RoundingMode roundingMode) {
        return c(str, str2, roundingMode, 2);
    }

    private String c(String str, String str2, RoundingMode roundingMode, int i) {
        try {
            return new BigDecimal(d(str)).divide(new BigDecimal(d(str2)), i, roundingMode).toString();
        } catch (ArithmeticException unused) {
            return "0";
        }
    }

    public int d(String str, String str2) {
        return new BigDecimal(d(str)).compareTo(new BigDecimal(d(str2)));
    }
}
