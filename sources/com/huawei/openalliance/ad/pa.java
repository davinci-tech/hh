package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.magazine.inter.listener.IAdInvalidHandler;
import com.huawei.openalliance.ad.magazine.inter.listener.IPPSDownloadService;
import com.huawei.openalliance.ad.magazine.inter.listener.PPSDownloadResult;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class pa implements qr {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7427a = "or";
    private static int j;
    private List<ContentRecord> b;
    private List<String> c;
    private int d = 2;
    private fs e;
    private Context f;
    private IAdInvalidHandler g;
    private IPPSDownloadService h;
    private com.huawei.openalliance.ad.analysis.c i;

    @Override // com.huawei.openalliance.ad.qr
    public void a(List<String> list) {
        this.c = list;
    }

    public void a(final String str) {
        this.e.c(str);
        if (this.g != null) {
            com.huawei.openalliance.ad.utils.m.g(new Runnable() { // from class: com.huawei.openalliance.ad.pa.1
                @Override // java.lang.Runnable
                public void run() {
                    ho.b(pa.f7427a, "onInvalid: %s", str);
                    pa.this.g.onInvalid(str);
                }
            });
        } else {
            ho.c(f7427a, "invalid handler is null");
        }
    }

    @Override // com.huawei.openalliance.ad.qr
    public void a(int i) {
        this.d = i;
    }

    @Override // com.huawei.openalliance.ad.qr
    public void a() {
        if (com.huawei.openalliance.ad.utils.bg.a(this.c)) {
            ho.b(f7427a, "invalidContentIds is empty");
            return;
        }
        Iterator<String> it = this.c.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    @Override // com.huawei.openalliance.ad.qr
    public String a(boolean z) {
        ho.b(f7427a, "download contents start");
        String str = null;
        if (com.huawei.openalliance.ad.utils.bg.a(this.b)) {
            return null;
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(this.f);
        for (ContentRecord contentRecord : this.b) {
            if (c()) {
                contentRecord.a(b);
                ho.b(f7427a, "start to downloadContent , contentId : %s", contentRecord.m());
                str = a(contentRecord, z || contentRecord.bg());
            } else {
                ho.b(f7427a, "downloadContents - content creativeType %d not supported", Integer.valueOf(contentRecord.D()));
            }
        }
        ho.b(f7427a, "download contents ends");
        return str;
    }

    private boolean c() {
        return 2 == this.d;
    }

    private boolean b(String str) {
        ContentRecord a2 = this.e.a(str);
        return (a2 == null || TextUtils.isEmpty(a2.y()) || com.huawei.openalliance.ad.utils.cz.j(a2.y())) ? false : true;
    }

    private String a(ContentRecord contentRecord, boolean z) {
        String m = contentRecord.m();
        if (!b(m)) {
            return a(contentRecord, m, z);
        }
        ho.b(f7427a, "contentId %s exist,no download.update DB!", contentRecord.m());
        ArrayList arrayList = new ArrayList();
        arrayList.add("_id");
        arrayList.add(ContentRecord.DISPLAY_COUNT);
        arrayList.add(ContentRecord.DISPLAY_DATE);
        if (TextUtils.isEmpty(contentRecord.y()) || com.huawei.openalliance.ad.utils.cz.j(contentRecord.y())) {
            arrayList.add(ContentRecord.SPLASH_MEDIA_PATH);
        }
        arrayList.add(ContentRecord.LAST_SHOW_TIME);
        arrayList.add(ContentRecord.FC_CTRL_DATE);
        this.e.b(contentRecord, arrayList, m);
        return m;
    }

    private String a(ContentRecord contentRecord, String str, boolean z) {
        if (this.h == null) {
            ho.c(f7427a, "there is no download service");
            return null;
        }
        ImageInfo Q = contentRecord.Q();
        if (Q == null) {
            ho.c(f7427a, "image info is null");
            return null;
        }
        PPSDownloadResult download = this.h.download(str, Q.c(), Q.a(), Q.f(), z);
        if (download == null || !download.isResult() || com.huawei.openalliance.ad.utils.cz.b(download.getPath())) {
            ho.c(f7427a, RecommendConstants.DOWNLOAD_FAIL);
            this.i.a(contentRecord.l(), str, false);
            return null;
        }
        contentRecord.i(download.getPath());
        String str2 = f7427a;
        ho.b(str2, "insert to db start:" + contentRecord.m());
        this.e.b(contentRecord);
        ho.b(str2, "insert to db end:" + contentRecord.m());
        this.i.a(contentRecord.l(), str, true);
        return str;
    }

    public pa(Context context, List<ContentRecord> list) {
        this.f = context.getApplicationContext();
        this.b = list;
        this.e = es.a(context);
        this.g = HiAd.getInstance(context).getAdInvalidHandler();
        this.h = HiAd.getInstance(context).getPPSDownloadService();
        this.i = new com.huawei.openalliance.ad.analysis.c(context);
    }
}
