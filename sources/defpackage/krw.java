package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import com.huawei.hwidauth.h.b;

/* loaded from: classes5.dex */
public class krw extends AsyncTask<Integer, Integer, Boolean> {

    /* renamed from: a, reason: collision with root package name */
    private Uri f14562a;
    private Context c;
    private b d;

    public krw(Context context, Uri uri, b bVar) {
        this.c = context;
        this.f14562a = uri;
        this.d = bVar;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Integer... numArr) {
        ksy.b("CompressPictureCase", "startCompressPic", true);
        if (this.f14562a == null) {
            this.d.b(new Bundle());
            return false;
        }
        ksy.b("CompressPictureCase", "startCompressPic mFromUri = " + this.f14562a, false);
        Uri bQI_ = ktb.bQI_(this.c);
        if (bQI_ == null) {
            this.d.b(new Bundle());
            return false;
        }
        ksy.b("CompressPictureCase", "startCompressPic tmpUri = " + bQI_, false);
        ktb.bQJ_(this.c, this.f14562a, bQI_, false);
        if (ktb.e(this.c, bQI_.getPath(), 4096, bQI_.getPath())) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("request_pic_uri_tag", bQI_);
            this.d.a(bundle);
            ksy.b("CompressPictureCase", "startCompressPic onSuccess", true);
            return true;
        }
        this.d.b(new Bundle());
        ksy.b("CompressPictureCase", "startCompressPic onError", true);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        ksy.b("CompressPictureCase", "onPostExecute", true);
        super.onPostExecute(bool);
    }
}
