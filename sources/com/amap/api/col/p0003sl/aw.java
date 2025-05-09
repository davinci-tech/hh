package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class aw {

    /* renamed from: a, reason: collision with root package name */
    public static String f907a = "";
    public static boolean b = false;
    public static String d = "";
    private static volatile aw k;
    public ba f;
    bc g;
    private Context i;
    private a l;
    private bf m;
    private bl n;
    private boolean j = true;
    List<av> c = new Vector();
    private la o = null;
    private la p = null;
    private la q = null;
    b e = null;
    az h = null;
    private boolean r = true;

    public interface a {
        void a();

        void a(av avVar);

        void b(av avVar);

        void c(av avVar);
    }

    static /* synthetic */ boolean f(aw awVar) {
        awVar.j = false;
        return false;
    }

    private aw(Context context) {
        this.i = context;
    }

    public static aw a(Context context) {
        if (k == null) {
            synchronized (aw.class) {
                if (k == null && !b) {
                    k = new aw(context.getApplicationContext());
                }
            }
        }
        return k;
    }

    public final void a() {
        this.n = bl.a(this.i.getApplicationContext());
        g();
        this.e = new b(this.i.getMainLooper());
        this.f = new ba(this.i);
        this.m = bf.a();
        k(dv.c(this.i));
        try {
            h();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        synchronized (this.c) {
            Iterator<OfflineMapProvince> it = this.f.a().iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity next = it2.next();
                    if (next != null) {
                        this.c.add(new av(this.i, next));
                    }
                }
            }
        }
        az azVar = new az(this.i);
        this.h = azVar;
        azVar.start();
    }

    private void g() {
        try {
            bg a2 = this.n.a("000001");
            if (a2 != null) {
                this.n.c("000001");
                a2.c(SmartMsgConstant.SHOW_METHOD_SMART_CARD);
                this.n.a(a2);
            }
        } catch (Throwable th) {
            iv.c(th, "OfflineDownloadManager", "changeBadCase");
        }
    }

    private void h() {
        String c;
        if ("".equals(dv.c(this.i))) {
            return;
        }
        File file = new File(dv.c(this.i) + "offlinemapv4.png");
        if (!file.exists()) {
            c = bt.a(this.i, "offlinemapv4.png");
        } else {
            c = bt.c(file);
        }
        if (c != null) {
            try {
                h(c);
            } catch (JSONException e) {
                if (file.exists()) {
                    file.delete();
                }
                iv.c(e, "MapDownloadManager", "paseJson io");
                e.printStackTrace();
            }
        }
    }

    private void h(String str) throws JSONException {
        ba baVar;
        List<OfflineMapProvince> a2 = bt.a(str, this.i.getApplicationContext());
        if (a2 == null || a2.size() == 0 || (baVar = this.f) == null) {
            return;
        }
        baVar.a(a2);
    }

    private void i() {
        Iterator<bg> it = this.n.a().iterator();
        while (it.hasNext()) {
            bg next = it.next();
            if (next != null && next.c() != null && next.e().length() > 0) {
                if (next.l != 4 && next.l != 7 && next.l >= 0) {
                    next.l = 3;
                }
                av i = i(next.c());
                if (i != null) {
                    String d2 = next.d();
                    if (d2 != null && b(d, d2)) {
                        i.a(7);
                    } else {
                        i.a(next.l);
                        i.setCompleteCode(next.g());
                    }
                    if (next.d().length() > 0) {
                        i.setVersion(next.d());
                    }
                    List<String> b2 = this.n.b(next.e());
                    StringBuffer stringBuffer = new StringBuffer();
                    Iterator<String> it2 = b2.iterator();
                    while (it2.hasNext()) {
                        stringBuffer.append(it2.next());
                        stringBuffer.append(";");
                    }
                    i.a(stringBuffer.toString());
                    ba baVar = this.f;
                    if (baVar != null) {
                        baVar.a(i);
                    }
                }
            }
        }
    }

    public final void b() {
        i();
        a aVar = this.l;
        if (aVar != null) {
            try {
                aVar.a();
            } catch (Throwable th) {
                iv.c(th, "OfflineDownloadManager", "verifyCallBack");
            }
        }
    }

    public final void a(final String str) {
        try {
            if (str == null) {
                a aVar = this.l;
                if (aVar != null) {
                    aVar.b(null);
                    return;
                }
                return;
            }
            if (this.o == null) {
                this.o = du.a("AMapOfflineCheckUpdate");
            }
            this.o.a(new lb() { // from class: com.amap.api.col.3sl.aw.1
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    av i = aw.this.i(str);
                    if (i != null) {
                        try {
                            if (!i.c().equals(i.c) && !i.c().equals(i.e)) {
                                String pinyin = i.getPinyin();
                                if (pinyin.length() > 0) {
                                    String d2 = aw.this.n.d(pinyin);
                                    if (d2 == null) {
                                        d2 = i.getVersion();
                                    }
                                    if (aw.d.length() > 0 && d2 != null && aw.b(aw.d, d2)) {
                                        i.j();
                                    }
                                }
                            }
                            if (aw.this.l != null) {
                                synchronized (aw.this) {
                                    try {
                                        aw.this.l.b(i);
                                    } finally {
                                        return;
                                    }
                                }
                                return;
                            }
                            return;
                        } catch (Exception unused) {
                            if (aw.this.l != null) {
                                synchronized (aw.this) {
                                    try {
                                        aw.this.l.b(i);
                                    } finally {
                                        return;
                                    }
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable th) {
                            if (aw.this.l != null) {
                                synchronized (aw.this) {
                                    try {
                                        aw.this.l.b(i);
                                    } finally {
                                        throw th;
                                    }
                                }
                            }
                            throw th;
                        }
                    }
                    aw.this.j();
                    ax c = new ay(aw.this.i, aw.d).c();
                    if (aw.this.l != null) {
                        if (c == null) {
                            if (aw.this.l != null) {
                                synchronized (aw.this) {
                                    try {
                                        aw.this.l.b(i);
                                    } finally {
                                        return;
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        if (c.a()) {
                            aw.this.c();
                        }
                    }
                    if (aw.this.l != null) {
                        synchronized (aw.this) {
                            try {
                                aw.this.l.b(i);
                            } finally {
                            }
                        }
                    }
                }
            });
        } catch (Throwable th) {
            iv.c(th, "OfflineDownloadManager", "checkUpdate");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() throws AMapException {
        if (!dv.d(this.i)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    protected final void c() throws AMapException {
        if (this.f == null) {
            return;
        }
        bd bdVar = new bd(this.i, "");
        bdVar.a(this.i);
        List<OfflineMapProvince> c = bdVar.c();
        if (this.c != null) {
            this.f.a(c);
        }
        List<av> list = this.c;
        if (list != null) {
            synchronized (list) {
                Iterator<OfflineMapProvince> it = this.f.a().iterator();
                while (it.hasNext()) {
                    Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                    while (it2.hasNext()) {
                        OfflineMapCity next = it2.next();
                        for (av avVar : this.c) {
                            if (next.getPinyin().equals(avVar.getPinyin())) {
                                String version = avVar.getVersion();
                                if (avVar.getState() == 4 && d.length() > 0 && b(d, version)) {
                                    avVar.j();
                                    avVar.setUrl(next.getUrl());
                                    avVar.s();
                                } else {
                                    avVar.setCity(next.getCity());
                                    avVar.setUrl(next.getUrl());
                                    avVar.s();
                                    avVar.setAdcode(next.getAdcode());
                                    avVar.setVersion(next.getVersion());
                                    avVar.setSize(next.getSize());
                                    avVar.setCode(next.getCode());
                                    avVar.setJianpin(next.getJianpin());
                                    avVar.setPinyin(next.getPinyin());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str, String str2) {
        for (int i = 0; i < str2.length(); i++) {
            try {
                if (str.charAt(i) > str2.charAt(i)) {
                    return true;
                }
                if (str.charAt(i) < str2.charAt(i)) {
                    return false;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public final boolean b(String str) {
        return i(str) != null;
    }

    public final void c(String str) {
        av i = i(str);
        if (i == null) {
            a aVar = this.l;
            if (aVar != null) {
                try {
                    aVar.c(i);
                    return;
                } catch (Throwable th) {
                    iv.c(th, "OfflineDownloadManager", "remove");
                    return;
                }
            }
            return;
        }
        d(i);
        a(i, true);
    }

    public final void a(av avVar) {
        a(avVar, false);
    }

    private void a(final av avVar, final boolean z) {
        if (this.g == null) {
            this.g = new bc(this.i);
        }
        if (this.p == null) {
            this.p = du.a("AMapOfflineRemove");
        }
        try {
            this.p.a(new lb() { // from class: com.amap.api.col.3sl.aw.2
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    try {
                        if (avVar.c().equals(avVar.f904a)) {
                            if (aw.this.l != null) {
                                aw.this.l.c(avVar);
                                return;
                            }
                            return;
                        }
                        if (avVar.getState() != 7 && avVar.getState() != -1) {
                            aw.this.g.a(avVar);
                            if (aw.this.l != null) {
                                aw.this.l.c(avVar);
                                return;
                            }
                            return;
                        }
                        aw.this.g.a(avVar);
                        if (!z || aw.this.l == null) {
                            return;
                        }
                        aw.this.l.c(avVar);
                    } catch (Throwable th) {
                        iv.c(th, "requestDelete", "removeExcecRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            iv.c(th, "requestDelete", "removeExcecRunnable");
        }
    }

    public final void b(av avVar) {
        try {
            bf bfVar = this.m;
            if (bfVar != null) {
                bfVar.a(avVar, this.i);
            }
        } catch (hm e) {
            e.printStackTrace();
        }
    }

    public final void c(av avVar) {
        ba baVar = this.f;
        if (baVar != null) {
            baVar.a(avVar);
        }
        b bVar = this.e;
        if (bVar != null) {
            Message obtainMessage = bVar.obtainMessage();
            obtainMessage.obj = avVar;
            this.e.sendMessage(obtainMessage);
        }
    }

    public final void d() {
        synchronized (this.c) {
            for (av avVar : this.c) {
                if (avVar.c().equals(avVar.c) || avVar.c().equals(avVar.b)) {
                    d(avVar);
                    avVar.g();
                }
            }
        }
    }

    public final void e() {
        synchronized (this.c) {
            Iterator<av> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                av next = it.next();
                if (next.c().equals(next.c)) {
                    next.g();
                    break;
                }
            }
        }
    }

    public final void d(String str) {
        av i = i(str);
        if (i != null) {
            i.f();
        }
    }

    public final void f() {
        la laVar = this.o;
        if (laVar != null) {
            laVar.e();
        }
        la laVar2 = this.q;
        if (laVar2 != null) {
            laVar2.e();
            this.q = null;
        }
        az azVar = this.h;
        if (azVar != null) {
            if (azVar.isAlive()) {
                this.h.interrupt();
            }
            this.h = null;
        }
        b bVar = this.e;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
            this.e = null;
        }
        bf bfVar = this.m;
        if (bfVar != null) {
            bfVar.b();
            this.m = null;
        }
        ba baVar = this.f;
        if (baVar != null) {
            baVar.g();
        }
        k();
        this.j = true;
        l();
    }

    private static void k() {
        k = null;
        b = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public av i(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        synchronized (this.c) {
            for (av avVar : this.c) {
                if (str.equals(avVar.getCity()) || str.equals(avVar.getPinyin())) {
                    return avVar;
                }
            }
            return null;
        }
    }

    private av j(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        synchronized (this.c) {
            for (av avVar : this.c) {
                if (str.equals(avVar.getCode())) {
                    return avVar;
                }
            }
            return null;
        }
    }

    public final void e(String str) throws AMapException {
        av i = i(str);
        if (str == null || str.length() <= 0 || i == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        f(i);
    }

    public final void f(String str) throws AMapException {
        av j = j(str);
        if (j != null) {
            f(j);
            return;
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    private void f(final av avVar) throws AMapException {
        j();
        if (avVar == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        if (this.q == null) {
            this.q = du.a("AMapOfflineDownload");
        }
        try {
            this.q.a(new lb() { // from class: com.amap.api.col.3sl.aw.3
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    try {
                        if (aw.this.j) {
                            aw.this.j();
                            ax c = new ay(aw.this.i, aw.d).c();
                            if (c != null) {
                                aw.f(aw.this);
                                if (c.a()) {
                                    aw.this.c();
                                }
                            }
                        }
                        avVar.setVersion(aw.d);
                        avVar.f();
                    } catch (AMapException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        iv.c(th, "OfflineDownloadManager", "startDownloadRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            iv.c(th, "startDownload", "downloadExcecRunnable");
        }
    }

    public final void d(av avVar) {
        bf bfVar = this.m;
        if (bfVar != null) {
            bfVar.a(avVar);
        }
    }

    public final void e(av avVar) {
        bf bfVar = this.m;
        if (bfVar != null) {
            bfVar.b(avVar);
        }
    }

    public final void a(a aVar) {
        this.l = aVar;
    }

    private void l() {
        synchronized (this) {
            this.l = null;
        }
    }

    final class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                message.getData();
                Object obj = message.obj;
                if (obj instanceof av) {
                    av avVar = (av) obj;
                    avVar.getCity();
                    avVar.getcompleteCode();
                    avVar.getState();
                    if (aw.this.l != null) {
                        aw.this.l.a(avVar);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public final String g(String str) {
        av i;
        return (str == null || (i = i(str)) == null) ? "" : i.getAdcode();
    }

    private static void k(String str) {
        f907a = str;
    }
}
