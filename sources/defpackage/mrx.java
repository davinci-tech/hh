package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.pluginmgr.EzPluginConfigBase;
import com.huawei.pluginmgr.EzPluginType;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/* loaded from: classes.dex */
public final class mrx {

    /* renamed from: a, reason: collision with root package name */
    private String f15137a;
    private String b;
    private msi c;
    private boolean d;
    private final EzPluginConfigBase e;

    public mrx(EzPluginType ezPluginType, String str) {
        this(new msm(ezPluginType, str));
    }

    public mrx(EzPluginConfigBase ezPluginConfigBase) {
        if (ezPluginConfigBase == null) {
            throw new IllegalArgumentException("config == null");
        }
        this.e = ezPluginConfigBase;
        h();
    }

    public void h() {
        i(null);
    }

    public EzPluginConfigBase a() {
        return this.e;
    }

    private void i(String str) {
        if (TextUtils.isEmpty(str)) {
            str = h(null);
        }
        this.b = str;
        this.f15137a = null;
        this.d = false;
    }

    public String e() {
        return this.b;
    }

    private String h(String str) {
        if (TextUtils.isEmpty(str)) {
            str = mrv.b;
        }
        return str + this.e.getPluginConfigId();
    }

    private List<msa> f() {
        msi msiVar = this.c;
        if (msiVar != null) {
            return msiVar.d();
        }
        return null;
    }

    public String e(String str, String str2) {
        String h = h(str);
        if (TextUtils.isEmpty(str2)) {
            str2 = this.e.getPluginQueryString(null);
        }
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder sb = new StringBuilder(128);
            sb.append(h);
            sb.append('?');
            sb.append(str2);
            h = sb.toString();
        }
        if (this.b.compareToIgnoreCase(h) != 0) {
            i(h);
        }
        return str2;
    }

    public void a(String str) {
        this.f15137a = str;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean c() {
        return this.d;
    }

    public void e(PullListener pullListener) {
        mru mruVar = new mru();
        mruVar.e(this.b);
        mruVar.a(this.f15137a);
        String indexFileFiled = this.e.getIndexFileFiled();
        String indexFileSavePath = this.e.getIndexFileSavePath();
        LogUtil.c("EzPlugin_EzPluginHelper", "updateIndex plugin_down_url=", this.b, ", savePath=", indexFileSavePath);
        msj.a().a(indexFileFiled, mruVar, indexFileSavePath, true, pullListener);
    }

    public List<msa> b() {
        List<msa> f = f();
        if (f != null) {
            return f;
        }
        if (!i()) {
            b(false);
            return null;
        }
        return f();
    }

    private void c(final String str, final PullListener pullListener) {
        LogUtil.c("EzPlugin_EzPluginHelper", "updateDescription fileId=", str);
        e(new PullListener() { // from class: mrx.1
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar.i() == 1) {
                    mrx.this.i();
                    mrx.this.a(str, pullListener);
                } else {
                    if (msoVar.i() != 0) {
                        PullListener pullListener2 = pullListener;
                        if (pullListener2 != null) {
                            pullListener2.onPullingChange(msqVar, msoVar);
                            return;
                        }
                        return;
                    }
                    LogUtil.d("EzPlugin_EzPluginHelper", "updateDescription else");
                }
            }
        });
    }

    public void a(String str, PullListener pullListener) {
        LogUtil.c("EzPlugin_EzPluginHelper", "downloadDescription fileId=", str);
        if (!j(str)) {
            msj.a().b(str, this.b, d(str).getPath(), this.f15137a, pullListener);
            return;
        }
        mso msoVar = new mso();
        msq msqVar = new msq();
        msoVar.b(-5);
        if (pullListener != null) {
            pullListener.onPullingChange(msqVar, msoVar);
        }
    }

    public void e(final String str, final PullListener pullListener) {
        LogUtil.c("EzPlugin_EzPluginHelper", "updatePlugin fileId=", str);
        msc f = f(str);
        if (f != null) {
            LogUtil.c("EzPlugin_EzPluginHelper", "updatePlugin pluginUrl is = ", this.b);
            String sb = this.e.getPluginSavePath(str).toString();
            msj.a().a(new mru(str, this.b, this.f15137a, f), sb, pullListener);
            return;
        }
        c(str, new PullListener() { // from class: mrx.5
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar.i() == 1) {
                    if (mrx.this.f(str) != null) {
                        mrx.this.e(str, pullListener);
                        return;
                    } else {
                        LogUtil.a("EzPlugin_EzPluginHelper", "updatePlugin inform error");
                        return;
                    }
                }
                if (msoVar.i() != 0) {
                    PullListener pullListener2 = pullListener;
                    if (pullListener2 != null) {
                        pullListener2.onPullingChange(msqVar, msoVar);
                        return;
                    }
                    return;
                }
                LogUtil.d("EzPlugin_EzPluginHelper", "updatePlugin onPullingChange else");
            }
        });
    }

    public msa c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EzPlugin_EzPluginHelper", "getPluginIndexInfo uuid is empty");
            return null;
        }
        List<msa> b = b();
        if (b != null) {
            for (msa msaVar : b) {
                if (str.equals(msaVar.b())) {
                    return msaVar;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public msc f(String str) {
        if (!this.e.needDescriptionFile()) {
            msa c = c(str);
            if (c == null) {
                return null;
            }
            msc mscVar = new msc();
            mscVar.b("Resource");
            mscVar.g(c.b());
            mscVar.j(c.h());
            mscVar.c(c.d());
            mscVar.e(c.e());
            mscVar.e(c.a());
            mscVar.d("2");
            return mscVar;
        }
        if (j(str)) {
            return null;
        }
        String e = e(d(str));
        LogUtil.d("EzPlugin_EzPluginHelper", "descriptionJson = ", e);
        return msb.d(e);
    }

    private File d(String str) {
        StringBuilder pluginSavePath = this.e.getPluginSavePath(str);
        pluginSavePath.append("description.json");
        return new File(pluginSavePath.toString());
    }

    private File g(String str) {
        StringBuilder pluginSavePath = this.e.getPluginSavePath(str);
        pluginSavePath.append("done");
        return new File(pluginSavePath.toString());
    }

    public boolean b(String str) {
        if (!this.e.needDescriptionFile()) {
            return g(str).exists();
        }
        if (!j(str) && g(str).exists()) {
            File d = d(str);
            if (d.exists()) {
                LogUtil.c("EzPlugin_EzPluginHelper", "isPluginAvaiable the plugin available, descriptionFileSavePath is = ", d.getPath());
                return true;
            }
        }
        LogUtil.a("EzPlugin_EzPluginHelper", "the plugin not available");
        return false;
    }

    private boolean j(String str) {
        msa c = c(str);
        if (c != null) {
            if (!"Deprecated".equalsIgnoreCase(c.h())) {
                return false;
            }
            LogUtil.c("EzPlugin_EzPluginHelper", "isPluginDeprecated this plugin is deprecated ");
            e(str);
            return true;
        }
        LogUtil.a("EzPlugin_EzPluginHelper", "isPluginDeprecated uuid is not exists");
        return false;
    }

    public String b(String str, boolean z) {
        if (z && !b(str)) {
            LogUtil.a("EzPlugin_EzPluginHelper", "getPluginFileSavePath the plugin is not done. uuid is unavailable");
            return null;
        }
        StringBuilder pluginSavePath = this.e.getPluginSavePath(str);
        if (!this.e.isPluginDirectory()) {
            msc f = f(str);
            if (f == null || TextUtils.isEmpty(f.e())) {
                return null;
            }
            pluginSavePath.append(f.e());
        }
        return pluginSavePath.toString();
    }

    public boolean i() {
        File file = new File(this.e.getIndexFileSavePath());
        if (!file.exists()) {
            LogUtil.a("EzPlugin_EzPluginHelper", "updateIndexCache ", file.getName(), " is not exist.");
            return false;
        }
        String e = e(file);
        LogUtil.d("EzPlugin_EzPluginHelper", "updateIndexCache indexJson = ", e);
        msi c = msb.c(e);
        List<msa> d = c.d();
        if (d == null || d.isEmpty()) {
            LogUtil.a("EzPlugin_EzPluginHelper", "updateIndexCache ", file.getName(), " is not correct.");
            return false;
        }
        this.c = c;
        return true;
    }

    private void e(String str) {
        FileUtils.d(g(str));
        FileUtils.d(d(str));
    }

    public static String e(File file) {
        return msb.a(file);
    }

    public static void d(FilenameFilter filenameFilter) {
        if (filenameFilter == null) {
            LogUtil.a("EzPlugin_EzPluginHelper", "deletePlugins filter is null");
        } else {
            FileUtils.d(new File(mrv.d), filenameFilter);
        }
    }

    public String d() {
        msi msiVar = this.c;
        if (msiVar != null) {
            return msiVar.b();
        }
        return null;
    }
}
