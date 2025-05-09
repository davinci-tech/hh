package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.hihonor.assistant.cardmgrsdk.model.CardDispReqBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

/* loaded from: classes2.dex */
public class kj {

    /* renamed from: a, reason: collision with root package name */
    static boolean f1258a = false;
    static int b = 20;
    private static int c = 20;
    private static WeakReference<kd> d;
    private static int e;

    public static void a(boolean z, int i) {
        synchronized (kj.class) {
            f1258a = z;
            e = Math.max(0, i);
        }
    }

    static final class a extends lb {

        /* renamed from: a, reason: collision with root package name */
        static int f1259a = 1;
        static int b = 2;
        static int c = 3;
        private Context d;
        private ki e;
        private int g;
        private List<ki> h;

        a(Context context, int i) {
            this.d = context;
            this.g = i;
        }

        a(Context context, int i, List<ki> list) {
            this(context, i);
            this.h = list;
        }

        a(Context context, int i, ki kiVar) {
            this(context, i);
            this.e = kiVar;
        }

        @Override // com.amap.api.col.p0003sl.lb
        public final void runTask() {
            ki kiVar;
            Throwable th;
            ByteArrayOutputStream byteArrayOutputStream;
            int i = this.g;
            if (i == 1) {
                try {
                    if (this.d != null && this.e != null) {
                        synchronized (kj.class) {
                            Context context = this.d;
                            if (context != null && (kiVar = this.e) != null) {
                                kj.a(context, kiVar.a());
                                return;
                            }
                            return;
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    iv.c(th2, "stm", "as");
                    return;
                }
            }
            if (i != 2) {
                if (i == 3) {
                    try {
                        if (this.d == null) {
                            return;
                        }
                        kd a2 = kk.a(kj.d);
                        kk.a(this.d, a2, it.h, 1000, CardDispReqBuilder.MAX_IMAGE_SIZE, "2");
                        if (a2.g == null) {
                            a2.g = new kl(new kp(this.d, new km(new kq(new ks()))));
                        }
                        a2.h = 3600000;
                        if (TextUtils.isEmpty(a2.i)) {
                            a2.i = "cKey";
                        }
                        if (a2.f == null) {
                            a2.f = new kw(this.d, a2.h, a2.i, new kt(a2.f1251a, new ku(this.d, kj.f1258a, kj.c * 1024, kj.b * 1024, "staticUpdate", kj.e * 1024)));
                        }
                        ke.a(a2);
                        return;
                    } catch (Throwable th3) {
                        iv.c(th3, "stm", "usd");
                        return;
                    }
                }
                return;
            }
            try {
                synchronized (kj.class) {
                    if (this.h != null && this.d != null) {
                        byte[] bArr = new byte[0];
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } catch (Throwable th4) {
                            th = th4;
                            byteArrayOutputStream = null;
                        }
                        try {
                            for (ki kiVar2 : this.h) {
                                if (kiVar2 != null) {
                                    byteArrayOutputStream.write(kiVar2.a());
                                }
                            }
                            bArr = byteArrayOutputStream.toByteArray();
                        } catch (Throwable th5) {
                            th = th5;
                            try {
                                iv.c(th, "stm", "aStB");
                            } finally {
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Throwable th6) {
                                        th6.printStackTrace();
                                    }
                                }
                            }
                        }
                        kj.a(this.d, bArr);
                    }
                }
            } catch (Throwable th7) {
                iv.c(th7, "stm", "apb");
            }
        }
    }

    public static void a(ki kiVar, Context context) {
        synchronized (kj.class) {
            la.a().a(new a(context, a.f1259a, kiVar));
        }
    }

    public static void a(List<ki> list, Context context) {
        synchronized (kj.class) {
            if (list != null) {
                try {
                    if (list.size() != 0) {
                        la.a().a(new a(context, a.b, list));
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static void b(List<ki> list, Context context) {
        synchronized (kj.class) {
            try {
                List<ki> b2 = jw.b();
                if (b2 != null && b2.size() > 0) {
                    list.addAll(b2);
                }
            } catch (Throwable unused) {
            }
            a(list, context);
        }
    }

    public static void a(Context context) {
        la.a().a(new a(context, a.c));
    }

    static /* synthetic */ void a(Context context, byte[] bArr) throws IOException {
        kd a2 = kk.a(d);
        kk.a(context, a2, it.h, 1000, CardDispReqBuilder.MAX_IMAGE_SIZE, "2");
        if (a2.e == null) {
            a2.e = new jj();
        }
        try {
            ke.a(Integer.toString(new Random().nextInt(100)) + Long.toString(System.nanoTime()), bArr, a2);
        } catch (Throwable th) {
            iv.c(th, "stm", "wts");
        }
    }
}
