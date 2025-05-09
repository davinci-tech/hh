package defpackage;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.haf.bundle.InstallSessionState;
import java.util.List;

/* loaded from: classes8.dex */
public class xx implements InstallSessionState {

    /* renamed from: a, reason: collision with root package name */
    private final int f17750a;
    private final int b;
    private final long c;
    private final List<String> d;
    private final List<String> e;
    private final List<Intent> f;
    private final int g;
    private final long h;
    private final PendingIntent i;

    private xx(Bundle bundle) {
        this.b = bundle.getInt("session_id");
        this.g = bundle.getInt("status");
        this.f17750a = bundle.getInt("error_code");
        this.c = bundle.getLong("bytes_downloaded");
        this.h = bundle.getLong("total_bytes_to_download");
        this.e = bundle.getStringArrayList("module_names");
        this.d = bundle.getStringArrayList("download_module_names");
        this.i = (PendingIntent) bundle.getParcelable("user_confirmation_intent");
        this.f = bundle.getParcelableArrayList("split_file_intents");
    }

    private xx(int i, int i2, xx xxVar) {
        this.b = xxVar.b;
        this.g = i;
        this.f17750a = i2;
        this.c = xxVar.c;
        this.h = xxVar.h;
        this.e = xxVar.e;
        this.d = xxVar.d;
        this.i = xxVar.i;
        this.f = xxVar.f;
    }

    final xx c(int i) {
        return d(i, errorCode());
    }

    final xx d(int i, int i2) {
        return new xx(i, i2, this);
    }

    static xx eo_(Bundle bundle) {
        return new xx(bundle);
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public int sessionId() {
        return this.b;
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public int status() {
        return this.g;
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public int errorCode() {
        return this.f17750a;
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public long bytesDownloaded() {
        return this.c;
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public long totalBytesToDownload() {
        return this.h;
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public List<String> moduleNames() {
        return this.e;
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public List<String> downloadModuleNames() {
        return this.d;
    }

    @Override // com.huawei.haf.bundle.InstallSessionState
    public final PendingIntent resolutionIntent() {
        return this.i;
    }

    final List<Intent> d() {
        return this.f;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("InstallSessionState{taskId=");
        sb.append(this.b);
        sb.append(", status=");
        sb.append(this.g);
        sb.append(", errorCode=");
        sb.append(this.f17750a);
        sb.append(", bytes=");
        sb.append(this.c);
        sb.append(", totalBytes=");
        sb.append(this.h);
        sb.append(", moduleNames=");
        sb.append(this.e);
        sb.append("}");
        return sb.toString();
    }
}
