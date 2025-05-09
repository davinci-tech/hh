package com.huawei.updatesdk.b.d;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/* loaded from: classes7.dex */
public class c extends AsyncTask<Void, Void, Void> {

    /* renamed from: a, reason: collision with root package name */
    private String f10832a;
    private HttpURLConnection b;
    private final com.huawei.updatesdk.b.d.b d;
    private boolean c = false;
    private Handler e = new b(null);

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        a();
    }

    public void b() {
        this.e = null;
    }

    public void a() {
        com.huawei.updatesdk.b.g.c.f10843a.execute(new a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Void... voidArr) {
        com.huawei.updatesdk.b.g.b.a(this);
        a(this.d, d.b());
        return null;
    }

    private void c() {
        HttpURLConnection httpURLConnection = this.b;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.huawei.updatesdk.b.d.c, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.huawei.updatesdk.b.d.b r16, java.io.OutputStream r17, java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.b.d.c.a(com.huawei.updatesdk.b.d.b, java.io.OutputStream, java.lang.String):boolean");
    }

    private void a(boolean z, com.huawei.updatesdk.b.d.b bVar, int i, long j) {
        if (z) {
            return;
        }
        a(bVar.d(), i, j, this.f10832a, this.c ? 3 : 5);
    }

    private void a(String str, int i, long j, String str2, int i2) {
        com.huawei.updatesdk.b.d.b bVar = new com.huawei.updatesdk.b.d.b();
        bVar.a(i);
        bVar.a(j);
        bVar.a(str2);
        bVar.b(str);
        Handler handler = this.e;
        if (handler == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(i2, bVar));
    }

    static class b extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Object obj = message.obj;
            if (obj instanceof com.huawei.updatesdk.b.d.b) {
                com.huawei.updatesdk.b.d.b bVar = (com.huawei.updatesdk.b.d.b) obj;
                Intent intent = new Intent();
                com.huawei.updatesdk.a.b.b.b a2 = com.huawei.updatesdk.a.b.b.b.a(intent);
                int i = message.what;
                if (i == 2) {
                    intent.putExtra("download_apk_size", bVar.e());
                    intent.putExtra("download_apk_already", bVar.a());
                    com.huawei.updatesdk.b.f.c.b().a(a2);
                    return;
                }
                if (i != 3) {
                    if (i == 4) {
                        e.a(com.huawei.updatesdk.a.b.a.a.c().a(), d.b() + "/appmarket.apk", bVar.c(), bVar.d());
                    } else if (i != 5) {
                        return;
                    }
                }
                a(message, intent, a2);
            }
        }

        private void a(Message message, Intent intent, com.huawei.updatesdk.a.b.b.b bVar) {
            Bundle bundle = new Bundle();
            bundle.putInt("download_status_param", message.what);
            intent.putExtras(bundle);
            com.huawei.updatesdk.b.f.c.b().b(bVar);
        }

        /* synthetic */ b(a aVar) {
            this();
        }

        private b() {
        }
    }

    private void a(InputStream inputStream, OutputStream outputStream) {
        com.huawei.updatesdk.a.a.d.d.a(inputStream);
        com.huawei.updatesdk.a.a.d.d.a(outputStream);
    }

    private void a(com.huawei.updatesdk.b.d.b bVar, String str) {
        FileOutputStream fileOutputStream;
        String str2;
        if (bVar == null || TextUtils.isEmpty(bVar.b())) {
            return;
        }
        String str3 = str + "/appmarket.apk";
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(new File(str3));
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (Exception e) {
            e = e;
        }
        try {
            if (!a(bVar, fileOutputStream, str3)) {
                com.huawei.updatesdk.a.a.d.d.a(new File(d.b()));
            }
            com.huawei.updatesdk.a.a.d.d.a(fileOutputStream);
        } catch (FileNotFoundException unused2) {
            fileOutputStream2 = fileOutputStream;
            str2 = "DOWNLOAD market package FileNotFoundException error";
            com.huawei.updatesdk.a.a.c.a.a.a.b("DownloadMarketTask", str2);
            com.huawei.updatesdk.a.a.d.d.a(fileOutputStream2);
        } catch (Exception e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            str2 = "DOWNLOAD market package Exception error:" + e.getMessage();
            com.huawei.updatesdk.a.a.c.a.a.a.b("DownloadMarketTask", str2);
            com.huawei.updatesdk.a.a.d.d.a(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            com.huawei.updatesdk.a.a.d.d.a(fileOutputStream);
            throw th;
        }
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            if (c.this.b != null) {
                c.this.c = true;
                c.this.b.disconnect();
            }
            com.huawei.updatesdk.a.a.d.d.a(new File(d.b()));
        }

        a() {
        }
    }

    private int a(com.huawei.updatesdk.b.d.b bVar, BufferedInputStream bufferedInputStream, BufferedOutputStream bufferedOutputStream, long j) {
        byte[] bArr = new byte[8192];
        long j2 = 0;
        int i = 0;
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read == -1) {
                return i;
            }
            bufferedOutputStream.write(bArr, 0, read);
            i += read;
            if (Math.abs(System.currentTimeMillis() - j2) > 1000) {
                j2 = System.currentTimeMillis();
                if (i != j) {
                    a(bVar.d(), i, j, this.f10832a, 2);
                }
            }
        }
    }

    public c(com.huawei.updatesdk.b.d.b bVar) {
        this.d = bVar;
        if (TextUtils.isEmpty(bVar.c())) {
            return;
        }
        this.f10832a = bVar.c();
    }
}
