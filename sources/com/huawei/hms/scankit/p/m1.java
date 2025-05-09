package com.huawei.hms.scankit.p;

import android.graphics.Bitmap;
import android.util.Log;
import com.huawei.hms.feature.DynamicModuleInitializer;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes9.dex */
public class m1 {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f5828a = true;
    private static boolean b = false;
    private static boolean c = false;
    private static int d;
    private static LinkedList<i2> e = new LinkedList<>();
    private static LinkedList<c6> f = new LinkedList<>();
    private static LinkedList<c6> g = new LinkedList<>();
    private static boolean h;
    private static boolean i;
    private static long j;
    private static boolean k;

    static {
        h = !r3.f5870a || r3.c;
        i = false;
        k = false;
        if (DynamicModuleInitializer.getContext() == null) {
            Log.e("ScankitDecode", "static initializer: context null");
            return;
        }
        Log.i("ScankitDecode", "static initializer: InitModuleBegin");
        y4.c(DynamicModuleInitializer.getContext(), "detect.ms");
        y4.a(DynamicModuleInitializer.getContext(), "angle.ms");
        y4.b(DynamicModuleInitializer.getContext(), "corner.ms");
        Log.i("ScankitDecode", "static initializer: InitModuleEnd");
    }

    public static s6 a(List<BarcodeFormat> list, n1 n1Var) {
        if (list.size() > 0) {
            return n1Var.e(list, null);
        }
        return null;
    }

    public static s6[] b(Bitmap bitmap, x6 x6Var) {
        byte[] bArr = null;
        try {
            x6Var.f5921a = bitmap.getWidth();
            x6Var.b = bitmap.getHeight();
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
            bitmap.copyPixelsToBuffer(allocate);
            allocate.flip();
            bArr = new m6(x6Var.f5921a, x6Var.b, allocate).b();
            allocate.clear();
        } catch (IllegalArgumentException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "IllegalArgumentException");
        } catch (UnsupportedOperationException unused2) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsupportedArgumentException");
        } catch (Exception unused3) {
            o4.b(TrackConstants$Events.EXCEPTION, "Exception");
        } catch (OutOfMemoryError unused4) {
            o4.b(TrackConstants$Events.EXCEPTION, "OutOfMemoryError");
        } catch (UnsatisfiedLinkError unused5) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsatisfiedLinkError");
        }
        return c(bArr, x6Var);
    }

    public static s6[] a(Bitmap bitmap, x6 x6Var) {
        o4.b("scankit mul", "start decodeMultiCode pre");
        byte[] bArr = null;
        try {
            x6Var.f5921a = bitmap.getWidth();
            x6Var.b = bitmap.getHeight();
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
            bitmap.copyPixelsToBuffer(allocate);
            allocate.flip();
            bArr = new m6(x6Var.f5921a, x6Var.b, allocate).b();
            allocate.clear();
        } catch (IllegalArgumentException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "IllegalArgumentException");
        } catch (OutOfMemoryError unused2) {
            o4.b(TrackConstants$Events.EXCEPTION, "OutOfMemoryError");
        } catch (UnsatisfiedLinkError unused3) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsatisfiedLinkError");
        } catch (UnsupportedOperationException unused4) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsupportedArgumentException");
        } catch (Exception unused5) {
            o4.b(TrackConstants$Events.EXCEPTION, "Exception");
        }
        o4.b("scankit mul", "end decodeMultiCode pre");
        return b(bArr, x6Var);
    }

    public static s6[] c(byte[] bArr, x6 x6Var) {
        s6[] s6VarArr = new s6[0];
        try {
            return a(bArr, x6Var, true);
        } catch (IllegalArgumentException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "IllegalArgumentException");
            return s6VarArr;
        } catch (Exception unused2) {
            o4.b(TrackConstants$Events.EXCEPTION, "Exception");
            return s6VarArr;
        } catch (OutOfMemoryError unused3) {
            o4.b(TrackConstants$Events.EXCEPTION, "OutOfMemoryError");
            return s6VarArr;
        } catch (UnsatisfiedLinkError unused4) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsatisfiedLinkError");
            return s6VarArr;
        } catch (UnsupportedOperationException unused5) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsupportedArgumentException");
            return s6VarArr;
        }
    }

    public static s6[] b(p4 p4Var, x6 x6Var) {
        s6 s6Var;
        float f2;
        s6 s6Var2;
        boolean z;
        boolean z2;
        int i2;
        o4.d("ScankitDecode", "scankit mode:FULLSDK21200301 VERSION_NAME: 2.12.0.301");
        r3.a(x6Var);
        List<i2> arrayList = new ArrayList<>();
        if (x6Var.f5921a >= 30 && x6Var.b >= 30 && p4Var != null) {
            List<List<BarcodeFormat>> a2 = n3.a(x6Var.c);
            List<BarcodeFormat> list = a2.get(0);
            List<BarcodeFormat> list2 = a2.get(1);
            List<BarcodeFormat> list3 = a2.get(2);
            List<BarcodeFormat> list4 = a2.get(3);
            List<BarcodeFormat> list5 = a2.get(4);
            n1 n1Var = new n1(p4Var);
            o4.d("Scankit", "Start decoding the full image");
            s6 s6Var3 = null;
            if (!f5828a || c) {
                s6Var = null;
            } else {
                s6Var = a(list, n1Var);
                i = false;
                j = System.currentTimeMillis();
            }
            if (a(s6Var)) {
                o4.d("Scankit", "detection start.");
                arrayList = n1Var.a(0, r3.q);
                o4.d("Scankit", "detection results size: " + arrayList.size());
                if (arrayList.size() > 0) {
                    o4.d("Scankit", "Start decoding  with detection");
                    s6Var = b(arrayList, n1Var, a2);
                    i = true;
                } else {
                    o4.d("Scankit", "Start decoding  without detection");
                    if (r3.c || !r3.f5870a || r3.b) {
                        if (a(s6Var) && list3.size() > 0) {
                            s6Var = n1Var.d(list3, null);
                        }
                        if (a(s6Var) && list2.size() > 0 && h) {
                            s6Var = n1Var.a(list2, (i2) null);
                        }
                        if (a(s6Var) && list5.size() > 0) {
                            s6Var = n1Var.b(list5, (i2) null);
                        }
                        if (a(s6Var) && list4.size() > 0) {
                            s6Var = n1Var.b(list4, (i2) null);
                        }
                    }
                }
            }
            o4.d("Scankit", "Decoding completed");
            boolean z3 = (f5828a || !b || c) ? false : true;
            if (x6Var.e && a(s6Var) && z3) {
                s6Var = a(list, n1Var);
                b = false;
            }
            if (r3.c) {
                f2 = 1.0f;
                s6Var2 = null;
                z = false;
                z2 = false;
            } else {
                boolean b2 = n1Var.b();
                int i3 = r3.k - 1;
                if (i3 <= 0) {
                    i3 = 0;
                }
                r3.k = i3;
                if (arrayList.size() > 0) {
                    b2 = b2 || n1Var.b(arrayList);
                }
                if (b2 && n1Var.c(n1Var.a()) < 20.0f) {
                    b2 = false;
                }
                if (n1Var.e() > 0.0f) {
                    f2 = Math.max(1.0f, n1Var.e());
                } else {
                    f2 = Math.max(1.0f, Math.max(n1Var.c(), n1Var.d()));
                }
                s6 a3 = n1.a(arrayList, n1Var);
                s6 a4 = n1.a(n1Var);
                z2 = arrayList.size() > 0 ? n1.a(arrayList, b2) : false;
                z = b2;
                s6Var2 = a3;
                s6Var3 = a4;
            }
            if (s6Var3 != null && s6Var3.h() == -2) {
                d++;
                i2 = 0;
            } else {
                i2 = 0;
                d = 0;
            }
            o4.d("Scankit", "end zoom and expose cal");
            if (s6Var != null && s6Var.k() != null) {
                o4.d("ScankitDecode", "ScanCode successful");
                d = i2;
                s6Var.b(j);
                s6Var.a(System.currentTimeMillis());
                s6Var.a(i);
                return new s6[]{s6Var};
            }
            if (z) {
                o4.d("ScankitDecode", "ScanCode need zoom");
                s6 s6Var4 = new s6(f2);
                s6Var4.c(true);
                d = 0;
                return new s6[]{s6Var4};
            }
            if (arrayList.size() > 0 && s6Var2 != null) {
                o4.d("ScankitDecode", "ScanCode need exposure");
                d = 0;
                return new s6[]{s6Var2};
            }
            if (s6Var3 != null && d == 3) {
                s6Var3.b(true);
                s6Var3.a(-1);
                o4.d("ScankitDecode", "ScanCode need globalexposure");
                d = 0;
                return new s6[]{s6Var3};
            }
            if (z2) {
                s6 s6Var5 = new s6(1.0f, true);
                float[] fArr = r3.y;
                float f3 = fArr[0];
                float f4 = fArr[1];
                s6Var5.a(new i2(false, f3, f4, fArr[2] - f3, fArr[3] - f4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
                return new s6[]{s6Var5};
            }
            o4.d("ScankitDecode", "ScanCode null");
            return new s6[0];
        }
        throw new IllegalArgumentException("widthOrHeight is Illeagle");
    }

    public static s6[] a(ByteBuffer byteBuffer, x6 x6Var) {
        return b(byteBuffer.array(), x6Var);
    }

    public static s6[] a(p4 p4Var, x6 x6Var) {
        s6 a2;
        boolean z;
        List arrayList = new ArrayList();
        r3.a(x6Var);
        r3.a(1);
        if (x6Var.f5921a >= 30 && x6Var.b >= 30 && p4Var != null) {
            List<List<BarcodeFormat>> a3 = n3.a(x6Var.c);
            List<BarcodeFormat> list = a3.get(0);
            List<BarcodeFormat> list2 = a3.get(1);
            List<BarcodeFormat> list3 = a3.get(2);
            List<BarcodeFormat> list4 = a3.get(3);
            n1 n1Var = new n1(p4Var);
            o4.b("scankit mul", "start detectCodes");
            List<i2> a4 = n1Var.a(1, r3.q);
            o4.b("scankit mul", "end detectCodes");
            if (a4.size() > 0) {
                arrayList = a(a4, n1Var, a3);
            } else if ((r3.c || !r3.f5870a) && (a2 = a(n1Var, list, list2, list3, list4)) != null && a2.k() != null) {
                arrayList.add(a2);
            }
            List<s6> a5 = v7.a(arrayList);
            if (r3.g && a5.size() > 0 && a5.get(0).k() != null) {
                return (s6[]) a5.toArray(new s6[0]);
            }
            float f2 = 1.0f;
            if (r3.c || !r3.g) {
                z = false;
            } else {
                if (a5.size() > 0) {
                    z = a5.get(0).p();
                    f2 = Math.max(1.0f, a5.get(0).l());
                } else {
                    z = false;
                }
                int i2 = r3.k - 1;
                if (i2 <= 0) {
                    i2 = 0;
                }
                r3.k = i2;
                if (a4.size() > 0) {
                    z = z || n1Var.c(a4);
                }
                if (z && n1Var.c(n1Var.a()) < 20.0f) {
                    z = false;
                }
                if (z) {
                    f2 = Math.max(f2, n1Var.e());
                }
            }
            if (!r3.g || !z) {
                return a5.size() > 0 ? (s6[]) a5.toArray(new s6[0]) : new s6[0];
            }
            s6 s6Var = new s6(f2);
            s6Var.c(true);
            a5.clear();
            a5.add(s6Var);
            return (s6[]) a5.toArray(new s6[0]);
        }
        throw new IllegalArgumentException("width or Height is Illeagle");
    }

    private static s6 a(n1 n1Var, List<BarcodeFormat> list, List<BarcodeFormat> list2, List<BarcodeFormat> list3, List<BarcodeFormat> list4) {
        s6 f2 = list.size() > 0 ? n1Var.f(list, null) : null;
        if (a(f2) && list3.size() > 0) {
            f2 = n1Var.d(list3, null);
        }
        if (a(f2) && list2.size() > 0 && h) {
            f2 = n1Var.a(list2, (i2) null);
        }
        return (!a(f2) || list4.size() <= 0) ? f2 : n1Var.b(list4, (i2) null);
    }

    public static List<s6> a(List<i2> list, n1 n1Var, List<List<BarcodeFormat>> list2) {
        s6 g2;
        boolean z;
        float max;
        boolean z2 = false;
        List<BarcodeFormat> list3 = list2.get(0);
        boolean z3 = true;
        List<BarcodeFormat> list4 = list2.get(1);
        List<BarcodeFormat> list5 = list2.get(2);
        List<BarcodeFormat> list6 = list2.get(3);
        List<BarcodeFormat> list7 = list2.get(4);
        List<BarcodeFormat> list8 = list2.get(5);
        List<BarcodeFormat> list9 = list2.get(6);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            n1Var.i.a();
            i2 i2Var = list.get(i2);
            boolean z4 = i2Var.g() == 5.0f ? z3 : z2;
            boolean z5 = i2Var.g() == 1.0f ? z3 : false;
            boolean z6 = i2Var.g() == 3.0f ? z3 : false;
            boolean z7 = i2Var.g() == 2.0f ? z3 : false;
            boolean z8 = i2Var.g() == 4.0f ? z3 : false;
            boolean z9 = i2Var.g() == 6.0f ? z3 : false;
            boolean z10 = i2Var.g() == 7.0f ? z3 : false;
            if (r3.b) {
                z4 = i2Var.g() == 1.0f ? z3 : false;
                z5 = i2Var.g() == 2.0f ? z3 : false;
                z6 = i2Var.g() == 2.0f ? z3 : false;
                z8 = i2Var.g() == 1.0f ? z3 : false;
                z7 = i2Var.g() == 2.0f ? z3 : false;
            }
            o4.d("scankit mul", "start cropAndRotate");
            n1Var.b(i2Var);
            o4.d("scankit mul", "end cropAndRotate");
            o4.d("scankit mul", "start decode");
            s6 e2 = (a((s6) null) && list3.size() > 0 && z5) ? n1Var.e(list3, i2Var) : null;
            if (a(e2) && list6.size() > 0 && z6) {
                e2 = n1Var.b(list6, i2Var);
            }
            if (a(e2) && list5.size() > 0 && z8) {
                e2 = n1Var.d(list5, i2Var);
            }
            if (a(e2) && list7.size() > 0 && z7) {
                e2 = n1Var.b(list7, i2Var);
            }
            if (a(e2) && list4.size() > 0 && z4) {
                e2 = n1Var.a(list4, i2Var);
            }
            if (a(e2) && list8.size() > 0 && z10) {
                e2 = n1Var.c(list8, i2Var);
            }
            s6 s6Var = (!a(e2) || list9.size() <= 0 || !z9 || (((double) i2Var.h()) <= 0.6d && !r3.c) || (e2 = n1Var.h(list9, i2Var)) == null || n1.b(n1Var.a(), i2Var)) ? e2 : null;
            if (r3.g && s6Var != null && n1Var.b()) {
                if (n1Var.e() > 0.0f) {
                    max = Math.max(1.0f, n1Var.e());
                } else {
                    max = Math.max(1.0f, Math.max(n1Var.c(), n1Var.d()));
                }
                s6 s6Var2 = new s6(max);
                z = true;
                s6Var2.c(true);
                arrayList.add(s6Var2);
            } else {
                z = true;
                if (s6Var != null && s6Var.k() != null) {
                    arrayList.add(s6Var);
                }
            }
            i2++;
            z3 = z;
            z2 = false;
        }
        if (arrayList.size() == 0 && list3.size() > 0 && !r3.g && (g2 = n1Var.g(list3, null)) != null && g2.k() != null) {
            arrayList.add(g2);
        }
        o4.d("scankit mul", "end decode");
        return arrayList;
    }

    public static s6 b(List<i2> list, n1 n1Var, List<List<BarcodeFormat>> list2) {
        List<BarcodeFormat> list3;
        boolean z = false;
        List<BarcodeFormat> list4 = list2.get(0);
        List<BarcodeFormat> list5 = list2.get(1);
        List<BarcodeFormat> list6 = list2.get(2);
        List<BarcodeFormat> list7 = list2.get(3);
        List<BarcodeFormat> list8 = list2.get(4);
        List<BarcodeFormat> list9 = list2.get(5);
        List<BarcodeFormat> list10 = list2.get(6);
        int i2 = 0;
        s6 s6Var = null;
        while (i2 < list.size()) {
            if (i2 > 0) {
                n1Var.i.a();
            }
            i2 i2Var = list.get(i2);
            boolean z2 = i2Var.g() == 5.0f ? true : z;
            boolean z3 = i2Var.g() == 1.0f ? true : z;
            boolean z4 = i2Var.g() == 2.0f ? true : z;
            boolean z5 = i2Var.g() == 3.0f ? true : z;
            boolean z6 = i2Var.g() == 4.0f ? true : z;
            boolean z7 = i2Var.g() == 6.0f ? true : z;
            boolean z8 = i2Var.g() == 7.0f ? true : z;
            if (r3.b) {
                z2 = i2Var.g() == 1.0f ? true : z;
                z3 = i2Var.g() == 2.0f ? true : z;
                z4 = i2Var.g() == 2.0f ? true : z;
                z5 = i2Var.g() == 2.0f ? true : z;
                z6 = i2Var.g() == 1.0f ? true : z;
            }
            n1Var.b(i2Var);
            if (a(s6Var) && list4.size() > 0 && z3) {
                b = true;
                s6Var = n1Var.e(list4, i2Var);
            }
            if (a(s6Var) && list7.size() > 0 && z5) {
                s6Var = n1Var.b(list7, i2Var);
            }
            if (a(s6Var) && list8.size() > 0 && z4) {
                s6Var = n1Var.b(list8, i2Var);
            }
            if (a(s6Var) && list6.size() > 0 && z6) {
                s6Var = n1Var.d(list6, i2Var);
            }
            if (a(s6Var) && list5.size() > 0 && z2) {
                s6Var = n1Var.a(list5, i2Var);
            }
            if (a(s6Var) && list9.size() > 0 && z8) {
                s6Var = n1Var.c(list9, i2Var);
            }
            if (a(s6Var) && list10.size() > 0 && z7) {
                list3 = list4;
                if (i2Var.h() > 0.6d || r3.c) {
                    s6 h2 = n1Var.h(list10, i2Var);
                    s6Var = (h2 == null || n1.b(n1Var.a(), i2Var)) ? h2 : null;
                }
            } else {
                list3 = list4;
            }
            if (s6Var != null && s6Var.k() != null) {
                break;
            }
            i2++;
            list4 = list3;
            z = false;
        }
        return s6Var;
    }

    private static p4 a(byte[] bArr, x6 x6Var) {
        int i2 = x6Var.f5921a;
        int i3 = x6Var.b;
        if (x6Var.d) {
            int i4 = i2 * i3;
            byte[] bArr2 = new byte[i4];
            float f2 = 0.0f;
            for (int i5 = 0; i5 < i3; i5++) {
                for (int i6 = 0; i6 < i2; i6++) {
                    f2 += r9 & 255;
                    bArr2[(((i6 * i3) + i3) - i5) - 1] = bArr[(i5 * i2) + i6];
                }
            }
            float f3 = f2 / i4;
            if (f3 < 25.0f) {
                r3.e = true;
            } else if (f3 > 215.0f) {
                r3.d = true;
            }
            x6Var.f5921a = i3;
            x6Var.b = i2;
            return new e6(bArr2, i3, i2, 0, 0, i3, i2, false);
        }
        return new e6(bArr, i2, i3, 0, 0, i2, i3, false);
    }

    public static s6[] b(byte[] bArr, x6 x6Var) {
        s6[] a2;
        o4.b("scankit mul", "start decodeMultiCode");
        s6[] s6VarArr = new s6[0];
        try {
            a2 = a(bArr, x6Var, false);
        } catch (IllegalArgumentException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "IllegalArgumentException");
        } catch (Exception unused2) {
            o4.b(TrackConstants$Events.EXCEPTION, "Exception");
        } catch (OutOfMemoryError unused3) {
            o4.b(TrackConstants$Events.EXCEPTION, "OutOfMemoryError");
        } catch (UnsatisfiedLinkError unused4) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsatisfiedLinkError");
        } catch (UnsupportedOperationException unused5) {
            o4.b(TrackConstants$Events.EXCEPTION, "UnsupportedArgumentException");
        }
        if (r3.g && a2.length > 0 && a2[0].p()) {
            return a2;
        }
        int length = a2.length;
        int[] iArr = new int[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < a2.length) {
            int i4 = i2 + 1;
            for (int i5 = i4; i5 < a2.length; i5++) {
                if (v7.a(a2[i2].j(), a2[i5].j()) > 0.7d) {
                    iArr[i5] = 1;
                    i3++;
                }
            }
            i2 = i4;
        }
        int length2 = a2.length - i3;
        s6VarArr = new s6[length2];
        for (int i6 = 0; i6 < length2; i6++) {
            int i7 = i6;
            while (i7 < length) {
                if (iArr[i7] != 1) {
                    break;
                }
                i7++;
            }
            s6VarArr[i6] = a2[i7];
        }
        o4.b("scankit mul", "end decodeMultiCode");
        return s6VarArr;
    }

    public static void a(boolean z) {
        r3.f5870a = z;
    }

    private static void a() {
        c = false;
        e = new LinkedList<>();
        f = new LinkedList<>();
        g = new LinkedList<>();
        r3.d = false;
        r3.e = false;
    }

    public static s6[] a(byte[] bArr, x6 x6Var, boolean z) {
        int i2;
        int i3;
        o4.d("scankit mul", "start pre");
        LinkedList linkedList = new LinkedList();
        a();
        int min = Math.min(x6Var.f5921a, x6Var.b);
        float f2 = min;
        float max = Math.max(x6Var.f5921a, x6Var.b) / f2;
        int i4 = (int) (f2 * 1.78f);
        p4 a2 = a(bArr, x6Var);
        o4.d("Scankit", "init " + k);
        if ((r3.d || r3.e) && k) {
            return new s6[0];
        }
        k = true;
        x6 x6Var2 = new x6(x6Var);
        o4.d("scankit mul", "end pre");
        if (min > 500 && x6Var.f5921a >= x6Var.b && x6Var.e && max > 3.0f) {
            c = true;
            x6Var2.f5921a = i4;
            int i5 = x6Var.f5921a - 1;
            while (i5 >= 0) {
                i5 -= i4;
                int i6 = i5 >= 0 ? i5 : 0;
                x6Var2.h = i6;
                x6Var2.i = 0;
                a(a2, i6, 0, x6Var2);
            }
            Collections.sort(e);
            s6 a3 = a(a2, x6Var2, linkedList, z, true, i4);
            if (a3 != null) {
                return new s6[]{a3};
            }
            e = new LinkedList<>();
            Collections.sort(f);
            HashSet hashSet = new HashSet();
            Iterator<c6> it = f.iterator();
            while (it.hasNext()) {
                c6 next = it.next();
                if (hashSet.add(Integer.valueOf(next.b)) && (i3 = next.b) >= i4 && i3 <= (x6Var.f5921a - 1) - i4) {
                    x6Var2.f5921a = i4;
                    x6Var2.j = true;
                    int i7 = i3 - (i4 / 2);
                    x6Var2.h = i7;
                    x6Var2.i = 0;
                    a(a2, i7, 0, x6Var2);
                }
            }
            Collections.sort(e);
            s6 a4 = a(a2, x6Var2, linkedList, z, true, i4);
            if (a4 != null) {
                return new s6[]{a4};
            }
        } else if (min > 500 && x6Var.e && max > 3.0f) {
            c = true;
            x6Var2.b = i4;
            int i8 = x6Var.b - 1;
            while (i8 >= 0) {
                i8 -= i4;
                int i9 = i8 >= 0 ? i8 : 0;
                x6Var2.h = 0;
                x6Var2.i = i9;
                a(a2, 0, i9, x6Var2);
            }
            Collections.sort(e);
            s6 a5 = a(a2, x6Var, linkedList, z, false, i4);
            if (a5 != null) {
                return new s6[]{a5};
            }
            e = new LinkedList<>();
            Collections.sort(g);
            HashSet hashSet2 = new HashSet();
            Iterator<c6> it2 = g.iterator();
            while (it2.hasNext()) {
                c6 next2 = it2.next();
                if (hashSet2.add(Integer.valueOf(next2.b)) && (i2 = next2.b) >= i4 && i2 <= (x6Var.b - 1) - i4) {
                    int i10 = i2 - (i4 / 2);
                    x6Var2.b = i4;
                    x6Var2.j = true;
                    x6Var2.h = 0;
                    x6Var2.i = i10;
                    a(a2, 0, i10, x6Var2);
                }
            }
            Collections.sort(e);
            s6 a6 = a(a2, x6Var, linkedList, z, false, i4);
            if (a6 != null) {
                return new s6[]{a6};
            }
        } else {
            c = false;
            if (z) {
                return b(a2, x6Var);
            }
            return a(a2, x6Var);
        }
        s6[] s6VarArr = new s6[linkedList.size()];
        linkedList.toArray(s6VarArr);
        return s6VarArr;
    }

    private static void a(p4 p4Var, int i2, int i3, x6 x6Var) {
        r3.a(x6Var);
        byte[] b2 = p4Var.a(i2, i3, x6Var.f5921a, x6Var.b).b();
        int i4 = x6Var.f5921a;
        int i5 = x6Var.b;
        List<i2> a2 = new n1(new e6(b2, i4, i5, 0, 0, i4, i5, false)).a(0, r3.q);
        if (!x6Var.j) {
            a(a2, x6Var);
        }
        for (i2 i2Var : a2) {
            i2Var.a(x6Var.h, x6Var.i);
            e.offer(i2Var);
        }
    }

    private static s6 a(p4 p4Var, x6 x6Var, LinkedList<s6> linkedList, boolean z, boolean z2, int i2) {
        n1 n1Var = new n1(p4Var);
        List<List<BarcodeFormat>> a2 = n3.a(x6Var.c);
        if (z) {
            s6 b2 = b(e, n1Var, a2);
            if (b2 == null || b2.k() == null) {
                return null;
            }
            return b2;
        }
        Iterator<s6> it = a(e, n1Var, a2).iterator();
        while (it.hasNext()) {
            linkedList.offer(it.next());
        }
        return null;
    }

    private static void a(List<i2> list, x6 x6Var) {
        for (i2 i2Var : list) {
            if (i2Var.d() < x6Var.f5921a * 0.1f) {
                f.offer(new c6(i2Var, x6Var.h));
            } else {
                float d2 = i2Var.d();
                float f2 = i2Var.f();
                int i2 = x6Var.f5921a;
                if (d2 + f2 > i2 * 0.9f) {
                    f.offer(new c6(i2Var, x6Var.h + i2));
                }
            }
            if (i2Var.e() < x6Var.b * 0.1f) {
                g.offer(new c6(i2Var, x6Var.i));
            } else {
                float e2 = i2Var.e();
                float c2 = i2Var.c();
                int i3 = x6Var.b;
                if (e2 + c2 > i3 * 0.9f) {
                    g.offer(new c6(i2Var, x6Var.i + i3));
                }
            }
        }
    }

    private static boolean a(s6 s6Var) {
        return s6Var == null || s6Var.k() == null;
    }
}
