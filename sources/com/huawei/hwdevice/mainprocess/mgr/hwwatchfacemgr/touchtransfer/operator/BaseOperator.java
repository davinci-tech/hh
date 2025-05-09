package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.operator;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.service.TagCardServer;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jmn;
import defpackage.jmr;
import defpackage.jmt;
import defpackage.jmu;
import defpackage.jmv;
import defpackage.jmw;
import defpackage.jnb;
import defpackage.jnk;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class BaseOperator {
    protected TagCardServer b;
    protected String d;
    protected jnk e = jnk.a();

    public BaseOperator(Context context) {
        this.b = new TagCardServer(context);
    }

    private List<jmu> b(List<jmn> list, jmn jmnVar) {
        ArrayList arrayList = new ArrayList(16);
        if (jmnVar != null) {
            for (jmn jmnVar2 : list) {
                jmu jmuVar = new jmu();
                jmuVar.d(String.valueOf(jmnVar2.e()));
                if (!TextUtils.isEmpty(jmnVar2.d())) {
                    jmuVar.a(jmnVar2.c() + jmnVar2.d());
                    jmuVar.c(jmnVar2.d());
                    jmuVar.b(jmnVar2.a());
                    jmuVar.e(jmnVar2.b());
                } else {
                    jmuVar.a("");
                    jmuVar.c((String) null);
                }
                arrayList.add(jmuVar);
            }
        }
        return arrayList;
    }

    private List<jmn> e(List<jmu> list) {
        ArrayList arrayList = new ArrayList(16);
        for (jmu jmuVar : list) {
            String b = jmuVar.b();
            if (TextUtils.isEmpty(b)) {
                LogUtil.c("BaseOperator", "changeServerAccessApduToApduCommand, invalid apduId");
            } else {
                try {
                    jmn jmnVar = new jmn();
                    jmnVar.e(Integer.parseInt(b));
                    jmnVar.d(jmuVar.c());
                    if (jmuVar.a() != null) {
                        jmnVar.d(jmuVar.a().split("[|]"));
                    }
                    arrayList.add(jmnVar);
                } catch (NumberFormatException unused) {
                    LogUtil.b("BaseOperator", "changeServerAccessApduToApduCommand, NumberFormatException");
                }
            }
        }
        return arrayList;
    }

    public jmv d(Map<String, String> map, List<jmu> list, String str) {
        jmv jmvVar = new jmv();
        if (list == null || list.isEmpty()) {
            jmvVar.c(-1);
            return jmvVar;
        }
        jmt jmtVar = null;
        while (true) {
            List<jmn> e = e(list);
            if (jmtVar == null) {
                jmtVar = new jmt();
            }
            LogUtil.c("BaseOperator", "executeApduList start");
            jmr<jmt> c = this.e.c(e, jmtVar);
            if (c.a() != 0) {
                this.e.d();
                if (c.a() == 4003) {
                    jmvVar.c(-8);
                } else {
                    jmvVar.c(-1);
                }
                return jmvVar;
            }
            LogUtil.c("BaseOperator", "executeApduList end");
            jmt e2 = c.e();
            jnb applyApdu = this.b.applyApdu(new jmw(map, b(e, c.b()), str));
            LogUtil.c("BaseOperator", "executeCommand, apply apdu response :", Integer.valueOf(applyApdu.a()));
            if (applyApdu.a() == 0) {
                List<jmu> d = applyApdu.d();
                String e3 = applyApdu.e();
                if (d == null || d.isEmpty()) {
                    jmvVar.c(0);
                }
                if (d == null || d.isEmpty()) {
                    break;
                }
                jmtVar = e2;
                str = e3;
                list = d;
            } else {
                jmvVar.c(applyApdu.a());
                break;
            }
        }
        this.e.d();
        return jmvVar;
    }

    public void b() {
        jnk jnkVar = this.e;
        if (jnkVar != null) {
            jnkVar.e();
        }
    }

    public void e() {
        jnk jnkVar = this.e;
        if (jnkVar != null) {
            jnkVar.g();
        }
    }
}
