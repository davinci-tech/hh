package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class az extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private Context f913a;
    private bl b;

    public az(Context context) {
        this.f913a = context;
        this.b = bl.a(context);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static bg a(File file) {
        String a2 = dv.a(file);
        bg bgVar = new bg();
        bgVar.b(a2);
        return bgVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ec, code lost:
    
        r10.b.b(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c0, code lost:
    
        r10.b.b(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0088, code lost:
    
        r10.b.b(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a() {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.az.a():void");
    }

    private bg a(String str) {
        if (str.equals("quanguo")) {
            str = "quanguogaiyaotu";
        }
        aw a2 = aw.a(this.f913a);
        bg bgVar = null;
        if (a2 != null) {
            String g = a2.g(str);
            File[] listFiles = new File(dv.c(this.f913a)).listFiles();
            if (listFiles == null) {
                return null;
            }
            for (File file : listFiles) {
                if ((file.getName().contains(g) || file.getName().contains(str)) && file.getName().endsWith(".zip.tmp.dt")) {
                    bgVar = a(file);
                    if (bgVar.c() != null) {
                        return bgVar;
                    }
                }
            }
        }
        return bgVar;
    }

    private static boolean a(String str, ArrayList<bg> arrayList) {
        Iterator<bg> it = arrayList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().h())) {
                return true;
            }
        }
        return false;
    }

    private void a(ArrayList<String> arrayList, String str) {
        File[] listFiles;
        String name;
        int lastIndexOf;
        File file = new File(dv.b(this.f913a) + str);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.getName().endsWith(".dat") && (lastIndexOf = (name = file2.getName()).lastIndexOf(46)) >= 0 && lastIndexOf < name.length()) {
                    String substring = name.substring(0, lastIndexOf);
                    if (!arrayList.contains(substring)) {
                        arrayList.add(substring);
                    }
                }
            }
        }
    }

    private void b(ArrayList<String> arrayList, String str) {
        File[] listFiles;
        String[] list;
        File file = new File(dv.a(this.f913a) + str);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    String name = file2.getName();
                    if (!TextUtils.isEmpty(name) && (list = file2.list()) != null && list.length > 0 && !arrayList.contains(name)) {
                        if (!name.equals("a0")) {
                            boolean z = false;
                            boolean z2 = false;
                            for (String str2 : list) {
                                if ("m1.ans".equals(str2)) {
                                    z = true;
                                }
                                if ("m3.ans".equals(str2)) {
                                    z2 = true;
                                }
                            }
                            if (z) {
                                if (!z2) {
                                }
                                arrayList.add(name);
                                break;
                                break;
                            }
                        } else {
                            for (String str3 : list) {
                                if ("m1.ans".equals(str3)) {
                                    arrayList.add(name);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList<String> b() {
        File[] listFiles;
        String name;
        int lastIndexOf;
        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(dv.c(this.f913a));
        if (!file.exists() || (listFiles = file.listFiles()) == null) {
            return arrayList;
        }
        for (File file2 : listFiles) {
            if (file2.getName().endsWith(".zip") && (lastIndexOf = (name = file2.getName()).lastIndexOf(46)) >= 0 && lastIndexOf < name.length()) {
                arrayList.add(name.substring(0, lastIndexOf));
            }
        }
        return arrayList;
    }
}
