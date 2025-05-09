package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.hms.network.embedded.j;
import com.huawei.hms.network.embedded.w;
import com.huawei.hms.support.picker.common.AccountPickerUtil;
import com.huawei.hwidauth.h.f;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.intent.SafeBundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class ksm {

    /* renamed from: a, reason: collision with root package name */
    private static ksm f14573a;
    private Context b;
    private String c = "";
    private boolean d = false;
    private ArrayList<krk> e = new ArrayList<>();

    ksm(Context context) {
        this.b = context;
    }

    public static ksm e(Context context) {
        if (f14573a == null) {
            f14573a = new ksm(context);
        }
        return f14573a;
    }

    public boolean b() {
        return !ksk.b(this.e).booleanValue();
    }

    public boolean a() {
        return kss.e(this.b.getFilesDir().getAbsolutePath() + "/configuration.xml");
    }

    public void e() {
        synchronized (this) {
            ksy.b(j.h, "Enter saveConfigurationTimestamp", true);
            krl.e(this.b).b("configurationLastUpdate", System.currentTimeMillis());
        }
    }

    public void d(XmlPullParser xmlPullParser, final String str) throws XmlPullParserException, IOException {
        synchronized (this) {
            ksy.c(j.h, "saveConfigurationFromServer.", true);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            krq.a().execute(new Runnable() { // from class: ksm.2
                @Override // java.lang.Runnable
                public void run() {
                    kss.d(ksm.this.b.getFilesDir().getAbsolutePath() + "/", "configuration.xml", ksi.b(str));
                }
            });
            a(xmlPullParser);
        }
    }

    public String c() {
        String str;
        synchronized (this) {
            if (ksk.b(this.e).booleanValue()) {
                this.c = "";
                a((f.a) null);
            }
            ksy.b(j.h, "getConfigurationTimeStampFromServer Timestamp:" + this.c, false);
            str = this.c;
        }
        return str;
    }

    public void d() {
        if (f()) {
            ksy.b(j.h, "over one day, then update configuration.xml from server.", true);
            krs.a().execute(new Runnable() { // from class: ksm.5
                @Override // java.lang.Runnable
                public void run() {
                    ksm.this.c((f.a) null);
                }
            });
        }
    }

    public void a(final f.a aVar) {
        krs.a().execute(new Runnable() { // from class: ksm.4
            @Override // java.lang.Runnable
            public void run() {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                krq.a().execute(new Runnable() { // from class: ksm.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String h;
                        try {
                            h = ksm.this.h();
                        } catch (Exception e) {
                            ksy.c(j.h, "Exception:" + e.getClass().getSimpleName(), true);
                        }
                        if (!TextUtils.isEmpty(h)) {
                            ksm.this.a(kri.a(h.getBytes("UTF-8")));
                            countDownLatch.countDown();
                        } else {
                            ksy.c(j.h, "getConfigurationData readConfigurationFile is failed.", true);
                            countDownLatch.countDown();
                        }
                    }
                });
                try {
                    boolean await = countDownLatch.await(3L, TimeUnit.SECONDS);
                    ksy.b(j.h, "getConfigurationData awaitValue:" + await, true);
                    if (await && aVar != null && !ksk.b(ksm.this.e).booleanValue()) {
                        ksm.this.d(aVar);
                        return;
                    }
                    f.a aVar2 = aVar;
                    if (aVar2 != null) {
                        ksm.this.a(aVar2, 2015, "read file timeout or config data list empty.");
                    }
                } catch (InterruptedException unused) {
                    ksy.c(j.h, "getConfigurationData InterruptedException.", true);
                    f.a aVar3 = aVar;
                    if (aVar3 != null) {
                        ksm.this.a(aVar3, 2015, "read file exception.");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final f.a aVar) {
        new Handler(this.b.getMainLooper()).post(new Runnable() { // from class: ksm.1
            @Override // java.lang.Runnable
            public void run() {
                aVar.a(new SafeBundle());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final f.a aVar, final int i, final String str) {
        new Handler(this.b.getMainLooper()).post(new Runnable() { // from class: ksm.3
            @Override // java.lang.Runnable
            public void run() {
                aVar.a(i, str);
            }
        });
    }

    public void c(final f.a aVar) {
        String r = krv.o().r();
        if (TextUtils.isEmpty(r)) {
            ksy.c(j.h, "getResourceUrl is null.", true);
            if (aVar != null) {
                aVar.a(2015, "getResourceUrl is null.");
                return;
            }
            return;
        }
        kqu kquVar = new kqu(w.j, "", c(), this.b);
        new krz(kquVar, this.b, r + kquVar.c(), new f.a() { // from class: ksm.6
            @Override // com.huawei.hwidauth.h.f.a
            public void a(SafeBundle safeBundle) {
                f.a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a(safeBundle);
                }
            }

            @Override // com.huawei.hwidauth.h.f.a
            public void a(int i, String str) {
                f.a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a(i, str);
                }
            }
        }).a();
    }

    private boolean f() {
        if (AccountPickerUtil.isPhoneStillInLockMode(this.b)) {
            return false;
        }
        if (a()) {
            return Math.abs(System.currentTimeMillis() - krl.e(this.b).c("configurationLastUpdate", 0L)) > (((long) ktc.a().nextInt(1000)) * 3600) + 86400000;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0052 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x013e A[Catch: all -> 0x01a6, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0017, B:7:0x001f, B:11:0x002b, B:13:0x0031, B:15:0x0039, B:19:0x0045, B:25:0x0058, B:26:0x006c, B:28:0x0072, B:29:0x007d, B:31:0x0083, B:33:0x008f, B:35:0x0092, B:37:0x00a0, B:40:0x00a4, B:41:0x015a, B:45:0x00dc, B:47:0x00fc, B:49:0x011c, B:51:0x013e, B:54:0x0156), top: B:2:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0156 A[Catch: all -> 0x01a6, TRY_ENTER, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0017, B:7:0x001f, B:11:0x002b, B:13:0x0031, B:15:0x0039, B:19:0x0045, B:25:0x0058, B:26:0x006c, B:28:0x0072, B:29:0x007d, B:31:0x0083, B:33:0x008f, B:35:0x0092, B:37:0x00a0, B:40:0x00a4, B:41:0x015a, B:45:0x00dc, B:47:0x00fc, B:49:0x011c, B:51:0x013e, B:54:0x0156), top: B:2:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(java.lang.String r10, java.lang.String r11, com.huawei.secure.android.common.webview.SafeWebView r12) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ksm.d(java.lang.String, java.lang.String, com.huawei.secure.android.common.webview.SafeWebView):boolean");
    }

    public String c(String str) {
        synchronized (this) {
            if (ksk.b(this.e).booleanValue()) {
                this.c = "";
                ksy.c(j.h, "mConfigDataList is empty.", true);
                return Constants.LOG_ERROR;
            }
            String a2 = a(str);
            return TextUtils.isEmpty(a2) ? Constants.LOG_ERROR : a2;
        }
    }

    private String a(String str) {
        if (ksk.b(this.e).booleanValue() || TextUtils.isEmpty(str)) {
            return "";
        }
        Iterator<krk> it = this.e.iterator();
        while (it.hasNext()) {
            krk next = it.next();
            if (next != null && w.j.equalsIgnoreCase(next.e())) {
                Iterator<kro> it2 = next.b().iterator();
                while (it2.hasNext()) {
                    kro next2 = it2.next();
                    if (next2 != null && str.equalsIgnoreCase(next2.c())) {
                        return next2.d();
                    }
                }
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (1 != eventType) {
            String name = xmlPullParser.getName();
            if (eventType == 2) {
                if ("dataVer".equals(name)) {
                    this.c = xmlPullParser.nextText();
                    ksy.b(j.h, "Response mResponseTimestamp = " + this.c, true);
                } else if ("Country".equals(name)) {
                    d(xmlPullParser.getAttributeValue(null, "name"));
                } else if ("Config".equals(name)) {
                    if (krg.a(xmlPullParser.getAttributeValue(null, "size")) > 0) {
                        this.d = true;
                    }
                } else if (this.d) {
                    if (ksk.b(this.e).booleanValue()) {
                        ksy.c(j.h, "arrConfigData is empty", true);
                    } else {
                        this.e.get(0).d(new kro(xmlPullParser.getName(), xmlPullParser.nextText()));
                    }
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String h() throws IOException {
        return kss.c(this.b.getFilesDir().getCanonicalPath() + "/configuration.xml");
    }

    private void d(String str) {
        if (TextUtils.isEmpty(str)) {
            ksy.b(j.h, "countryCode is empty", true);
        } else {
            i();
            this.e.add(new krk(str));
        }
    }

    private void i() {
        if (ksk.d(this.e).booleanValue()) {
            for (int i = 0; i < this.e.size(); i++) {
                krk krkVar = this.e.get(i);
                if (krkVar != null) {
                    krkVar.a();
                }
            }
        }
        ArrayList<krk> arrayList = this.e;
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
