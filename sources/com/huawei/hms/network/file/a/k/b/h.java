package com.huawei.hms.network.file.a.k.b;

import com.huawei.hms.network.embedded.g4;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private String f5614a;
    private List<a> b = new ArrayList();

    public String a() {
        String str;
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(this.f5614a);
        sb.append(g4.k);
        for (a aVar : this.b) {
            if (aVar.c != null) {
                sb.append("PRIMARY KEY (");
                for (String str2 : aVar.c) {
                    sb.append(str2);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                str = Constants.RIGHT_BRACKET_ONLY;
            } else {
                sb.append(aVar.f5609a);
                sb.append(" ");
                sb.append(aVar.b);
                if (aVar.d) {
                    sb.append(" PRIMARY KEY");
                }
                if (aVar.e) {
                    str = " unique";
                } else {
                    sb.append(",");
                }
            }
            sb.append(str);
            sb.append(",");
        }
        if (sb.toString().endsWith(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(g4.l);
        return sb.toString();
    }

    public h a(a aVar) {
        this.b.add(aVar);
        return this;
    }

    public h(String str) {
        this.f5614a = str;
    }
}
