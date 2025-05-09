package com.huawei.ui.main.stories.template;

import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.EzPluginType;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.main.stories.template.ResourceDownloadHelper;
import defpackage.koq;
import defpackage.mrv;
import defpackage.mrx;
import defpackage.msa;
import defpackage.mso;
import defpackage.msq;
import defpackage.ryb;
import defpackage.rye;
import health.compact.a.GrsDownloadPluginUrl;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* loaded from: classes7.dex */
public class ResourceDownloadHelper {

    /* renamed from: a, reason: collision with root package name */
    private FileResult f10511a;
    private Vector<String> h = new Vector<>(10);
    private Vector<String> b = new Vector<>(10);
    private Vector<String> f = new Vector<>(10);
    private volatile boolean c = false;
    private volatile boolean e = false;
    private volatile boolean d = false;
    private PullListener i = new PullListener() { // from class: com.huawei.ui.main.stories.template.ResourceDownloadHelper.2
        @Override // com.huawei.pluginmgr.filedownload.PullListener
        public void onPullingChange(msq msqVar, mso msoVar) {
            if (msoVar == null) {
                LogUtil.h("ResourceDownloadHelper", "onPullingChange pullResult is null");
                ResourceDownloadHelper.this.f10511a.onFail(ResourceDownloadHelper.this.h);
                return;
            }
            int i = msoVar.i();
            if (i != 1) {
                if (i != 0) {
                    LogUtil.h("ResourceDownloadHelper", "onPullingChange status ", Integer.valueOf(i));
                    ResourceDownloadHelper.this.f10511a.onFail(ResourceDownloadHelper.this.h);
                    return;
                }
                return;
            }
            ResourceDownloadHelper.this.j.a(msoVar.a());
            if (!koq.b(ResourceDownloadHelper.this.j.b())) {
                Iterator it = ResourceDownloadHelper.this.h.iterator();
                while (it.hasNext()) {
                    ResourceDownloadHelper.this.e((String) it.next());
                }
                return;
            }
            LogUtil.h("ResourceDownloadHelper", "onPullingChange PluginIndexInfoList is empty");
            if (ResourceDownloadHelper.this.c) {
                return;
            }
            ResourceDownloadHelper.this.f10511a.onFail(ResourceDownloadHelper.this.h);
            ResourceDownloadHelper.this.c = true;
        }
    };
    private mrx j = new mrx(EzPluginType.UX_TEMPLATE_TYPE, null);

    public interface FileResult {
        void onFail(List<String> list);

        void onSuccess();
    }

    public void b(FileResult fileResult) {
        Vector vector = new Vector(10);
        for (String str : ryb.e) {
            vector.add(str);
        }
        LogUtil.a("ResourceDownloadHelper", "startDownload");
        this.h.clear();
        this.f.clear();
        this.h.addAll(vector);
        this.f.addAll(vector);
        this.f10511a = fileResult;
        this.b.clear();
        this.b.addAll(vector);
        this.c = false;
        this.e = false;
        this.d = false;
        d();
    }

    public void d(List<String> list, FileResult fileResult) {
        LogUtil.a("ResourceDownloadHelper", "startDownload");
        this.h.clear();
        this.f.clear();
        this.h.addAll(list);
        this.f.addAll(list);
        this.f10511a = fileResult;
        this.b.clear();
        this.b.addAll(list);
        this.c = false;
        this.e = false;
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void d() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rxz
                @Override // java.lang.Runnable
                public final void run() {
                    ResourceDownloadHelper.this.d();
                }
            });
        } else {
            LogUtil.c("ResourceDownloadHelper", "updateIndexFile url ", this.j.e(new GrsDownloadPluginUrl().getDownloadPluginUrl(null, true), (String) null));
            this.j.e(this.i);
        }
    }

    public void e(String str) {
        if (!d(str)) {
            this.f.remove(str);
            this.b.remove(str);
            if (this.f.size() == 0) {
                if (this.b.size() == 0 && !this.e) {
                    c();
                    return;
                } else if (this.b.size() != 0 && !this.d) {
                    this.f10511a.onFail(this.b);
                    return;
                } else {
                    LogUtil.a("ResourceDownloadHelper", "update fail");
                    return;
                }
            }
            return;
        }
        a(str);
    }

    private void a(final String str) {
        this.j.e(str, new PullListener() { // from class: com.huawei.ui.main.stories.template.ResourceDownloadHelper.4
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar == null) {
                    ResourceDownloadHelper.this.f.remove(str);
                    ResourceDownloadHelper.this.e();
                    LogUtil.h("ResourceDownloadHelper", "updatePluginInfo onPullingChange result is null");
                    return;
                }
                int i = msoVar.i();
                if (i != 1) {
                    if (i != 0) {
                        ResourceDownloadHelper.this.f.remove(str);
                        LogUtil.h("ResourceDownloadHelper", "updatePluginInfo onPullingChange error uuid ", str, " status ", Integer.valueOf(i));
                        if (i != -10 || ResourceDownloadHelper.this.d) {
                            ResourceDownloadHelper.this.e();
                            return;
                        } else {
                            ResourceDownloadHelper.this.f10511a.onFail(ResourceDownloadHelper.this.b);
                            ResourceDownloadHelper.this.d = true;
                            return;
                        }
                    }
                    return;
                }
                msa c = ResourceDownloadHelper.this.j.c(str);
                if (c == null) {
                    ResourceDownloadHelper.this.f.remove(str);
                    ResourceDownloadHelper.this.e();
                    LogUtil.h("ResourceDownloadHelper", "updatePluginInfo onPullingChange pluginInfo is null ", str);
                } else {
                    ResourceDownloadHelper.this.f.remove(str);
                    ResourceDownloadHelper.this.b.remove(str);
                    LogUtil.a("ResourceDownloadHelper", "updatePluginInfo onPullingChange uuid ", str);
                    rye.a(str, c.h());
                    ResourceDownloadHelper.this.b();
                }
            }
        });
    }

    private void c() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.f10511a.onSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f.size() == 0) {
            if (this.b.size() == 0) {
                c();
            } else {
                if (this.d) {
                    return;
                }
                this.f10511a.onFail(this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f.size() != 0 || this.d) {
            return;
        }
        this.f10511a.onFail(this.b);
    }

    public boolean d(String str) {
        msa c = this.j.c(str);
        if (c == null) {
            LogUtil.h("ResourceDownloadHelper", "pluginInfo is null ", str);
            return false;
        }
        String b = rye.b(str);
        return rye.e(b(str)) || TextUtils.isEmpty(b) || !b.equals(c.h());
    }

    private String b(String str) {
        return mrv.d + "ux_model_res" + File.separator + str + File.separator;
    }
}
