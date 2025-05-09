package defpackage;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.android.bundlecore.download.DownloadRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
final class yt {

    /* renamed from: a, reason: collision with root package name */
    private List<DownloadRequest> f17766a;
    final List<yi> b;
    private List<String> c;
    private int d;
    private long e;
    private final int f;
    private int g;
    private List<Intent> h;
    private long i;
    private final List<String> j;
    private PendingIntent m;

    yt(int i, List<String> list, List<yi> list2, List<DownloadRequest> list3, long j) {
        this.f = i;
        this.j = list;
        this.b = list2;
        e(list3, j);
    }

    void e(List<DownloadRequest> list, long j) {
        this.f17766a = list;
        this.c = b(list);
        this.i = j;
    }

    int d() {
        return this.f;
    }

    List<String> c() {
        return this.j;
    }

    List<DownloadRequest> a() {
        return this.f17766a;
    }

    List<String> e() {
        return this.c;
    }

    long f() {
        return this.i;
    }

    int b() {
        return this.g;
    }

    void e(int i) {
        this.g = i;
    }

    void d(long j) {
        this.e = j;
    }

    void b(int i) {
        this.d = i;
    }

    void es_(PendingIntent pendingIntent) {
        this.m = pendingIntent;
    }

    void e(List<Intent> list) {
        this.h = list;
    }

    static Bundle er_(yt ytVar) {
        int b = ytVar.b();
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", ytVar.d());
        bundle.putInt("status", b);
        bundle.putInt("error_code", ytVar.d);
        bundle.putLong("total_bytes_to_download", ytVar.f());
        bundle.putLong("bytes_downloaded", ytVar.e);
        bundle.putStringArrayList("module_names", (ArrayList) ytVar.c());
        bundle.putStringArrayList("download_module_names", (ArrayList) ytVar.e());
        if (b == 8) {
            bundle.putParcelable("user_confirmation_intent", ytVar.m);
        } else if (b == 10) {
            bundle.putParcelableArrayList("split_file_intents", (ArrayList) ytVar.h);
        }
        return bundle;
    }

    private static List<String> b(List<DownloadRequest> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<DownloadRequest> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getModuleName());
        }
        return arrayList;
    }
}
