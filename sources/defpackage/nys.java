package defpackage;

import android.text.TextUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public final class nys {
    private static final nys c = new nys();

    public static nys c() {
        return c;
    }

    public nzf d(String str, String str2) throws IOException, XmlPullParserException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("DeclarationHelper", "param is empty");
            return new nzf();
        }
        return a(e(str), str2);
    }

    public nyx c(String str, String str2) throws IOException {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.h("DeclarationHelper", "emuiVersion or featureDirPath is null");
            return null;
        }
        nyt e = nyw.e(str);
        if (e == null) {
            LogUtil.h("DeclarationHelper", "configJson is null");
            return null;
        }
        String c2 = nyw.c(str2, str, e);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("DeclarationHelper", "featureName is null");
            return null;
        }
        return new nyx(c2, str + File.separator + c2 + WatchFaceConstant.XML_SUFFIX);
    }

    private nzf e(String str) throws IOException, XmlPullParserException {
        nzq nzqVar = new nzq();
        try {
            FileInputStream openInputStream = FileUtils.openInputStream(FileUtils.getFile(str));
            try {
                nzf parse = nzqVar.parse(openInputStream);
                if (openInputStream != null) {
                    openInputStream.close();
                }
                return parse;
            } catch (Throwable th) {
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            LogUtil.b("DeclarationHelper", "parseConfig excption");
            throw e;
        }
    }

    private nzf a(nzf nzfVar, String str) throws IOException, XmlPullParserException {
        if (nzfVar == null) {
            return new nzf();
        }
        if (TextUtils.isEmpty(str)) {
            return new nzf();
        }
        nzu nzuVar = new nzu();
        try {
            FileInputStream openInputStream = FileUtils.openInputStream(FileUtils.getFile(str));
            try {
                nze parse = nzuVar.parse(openInputStream);
                if (openInputStream != null) {
                    openInputStream.close();
                }
                nzfVar.e(e(nzfVar, parse));
                nzfVar.a(b(nzfVar.n(), parse));
                nzfVar.e(e(nzfVar.b(), parse));
                return nzfVar;
            } catch (Throwable th) {
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            LogUtil.b("DeclarationHelper", "parseLanguage catch excption");
            throw e;
        }
    }

    private String e(nzf nzfVar, nze nzeVar) {
        String c2 = nzfVar.c();
        LogUtil.a("DeclarationHelper", "fillContentVersion", c2);
        return nzeVar.a(c2);
    }

    private nzj b(nzj nzjVar, nze nzeVar) {
        nzjVar.d(nzeVar.a(nzjVar.d()));
        return nzjVar;
    }

    private nzd e(nzd nzdVar, nze nzeVar) {
        List<nzn> b = nzdVar.b();
        for (int i = 0; i < b.size(); i++) {
            a(b.get(i), nzeVar);
        }
        return nzdVar;
    }

    private void a(nzn nznVar, nze nzeVar) {
        if (nznVar instanceof nzl) {
            c((nzl) nznVar, nzeVar);
            return;
        }
        if (nznVar instanceof nzk) {
            c((nzk) nznVar, nzeVar);
        } else if (nznVar instanceof nzo) {
            c((nzo) nznVar, nzeVar);
        } else {
            LogUtil.a("DeclarationHelper", "fillPart: not supported type");
        }
    }

    private void c(nzl nzlVar, nze nzeVar) {
        if (nzlVar == null || nzeVar == null) {
            LogUtil.h("DeclarationHelper", "paragraph or stringResource is null");
            return;
        }
        String a2 = nzeVar.a(nzlVar.b());
        c((nzn) nzlVar, nzeVar);
        nzlVar.c(a2);
    }

    private void c(nzn nznVar, nze nzeVar) {
        List<nzr> e = nznVar.e();
        int size = e.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            nzr nzrVar = e.get(i);
            e(nzeVar, strArr, i, nzrVar);
            if (nzrVar instanceof nzt) {
                d(nzeVar, (nzt) nzrVar);
            } else if (nzrVar instanceof nzp) {
                e(nzeVar, (nzp) nzrVar);
            } else {
                LogUtil.a("DeclarationHelper", "parsePlaceHolder: not supported placeholder.");
            }
        }
    }

    private void e(nze nzeVar, Object[] objArr, int i, nzr nzrVar) {
        String a2 = nzeVar.a(nzrVar.e());
        objArr[i] = a2;
        nzrVar.e(a2);
    }

    private void d(nze nzeVar, nzt nztVar) {
        nztVar.b(nzeVar.a(nztVar.d()));
    }

    private void e(nze nzeVar, nzp nzpVar) {
        nzpVar.c(nzeVar.a(nzpVar.c()));
    }

    private void c(nzk nzkVar, nze nzeVar) {
        if (nzkVar == null || nzeVar == null) {
            LogUtil.h("DeclarationHelper", "checkbox or stringResource is null");
            return;
        }
        nzkVar.a(nzeVar.a(nzkVar.b()));
        nzkVar.d(nzeVar.a(nzkVar.a()));
        c((nzn) nzkVar, nzeVar);
    }

    private void c(nzo nzoVar, nze nzeVar) {
        if (nzoVar == null || nzeVar == null) {
            LogUtil.h("DeclarationHelper", "switchFace or stringResource is null");
            return;
        }
        nzoVar.a(nzeVar.a(nzoVar.c()));
        nzg d = nzoVar.d();
        if (d == null) {
            LogUtil.h("DeclarationHelper", "SwitchFaceConfig is null");
        } else {
            d.b(nzeVar.a(d.b()));
            nzoVar.d(d);
        }
    }
}
