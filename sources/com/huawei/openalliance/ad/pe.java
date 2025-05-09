package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Template;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.AdParseConfig;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.inter.listeners.ContentIdListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class pe implements qs {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7432a = "pe";
    private AdContentRsp b;
    private a c;
    private fx d;
    private gc e;
    private String f;
    private Context g;
    private boolean h;
    private int i;
    private ContentIdListener j;
    private boolean k;
    private boolean l;
    private boolean m;

    public interface a {
        void a(int i);

        void a(Map<String, List<INativeAd>> map);
    }

    @Override // com.huawei.openalliance.ad.qs
    public void c(boolean z) {
        this.m = z;
    }

    @Override // com.huawei.openalliance.ad.qs
    public boolean b() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.qs
    public void b(boolean z) {
        this.h = z;
    }

    @Override // com.huawei.openalliance.ad.qs
    public boolean a() {
        return this.h;
    }

    @Override // com.huawei.openalliance.ad.qs
    public void a(boolean z) {
        this.l = z;
    }

    @Override // com.huawei.openalliance.ad.qs
    public void a(ContentIdListener contentIdListener) {
        this.j = contentIdListener;
    }

    @Override // com.huawei.openalliance.ad.qs
    public void a(AdContentRsp adContentRsp, long j, AdParseConfig adParseConfig) {
        try {
            this.b = adContentRsp;
            a(j, adParseConfig);
        } catch (Throwable th) {
            ho.c(f7432a, "parse ad err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.qs
    public void a(int i) {
        this.i = i;
    }

    private boolean b(List<Ad30> list) {
        Iterator<Ad30> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            List<Content> c = it.next().c();
            if (!com.huawei.openalliance.ad.utils.bg.a(c) && (i = i + c.size()) > 1) {
                return true;
            }
        }
        return false;
    }

    private boolean b(com.huawei.openalliance.ad.inter.data.e eVar) {
        return eVar.getVideoInfo() != null;
    }

    private boolean b(int i) {
        if (99 != i) {
            return false;
        }
        String a2 = e.a();
        return !TextUtils.isEmpty(a2) && 30461200 <= Integer.parseInt(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(com.huawei.openalliance.ad.inter.data.e eVar, long j, VideoInfo videoInfo, ImageInfo imageInfo) {
        if (this.e.aA() + 86400000 < com.huawei.openalliance.ad.utils.ao.c()) {
            this.e.b(com.huawei.openalliance.ad.utils.ao.c());
            com.huawei.openalliance.ad.utils.ae.a(this.f, 604800000L);
        }
        ContentRecord a2 = pd.a(eVar);
        rt rtVar = new rt();
        rtVar.a(a2);
        rtVar.c(imageInfo.getUrl());
        rtVar.a(imageInfo.a());
        rtVar.b(imageInfo.getSha256());
        rtVar.b(imageInfo.isCheckSha256());
        rtVar.a("video");
        rtVar.a(Long.valueOf(j));
        rtVar.c(true);
        ru a3 = this.d.a(rtVar);
        if (a3 == null || com.huawei.openalliance.ad.utils.cz.b(a3.a())) {
            ho.c(f7432a, "dealVideo, download cover failed!");
            return false;
        }
        imageInfo.c(a3.a());
        a(imageInfo, a3.a());
        if (1 == videoInfo.getVideoPlayMode() || this.m) {
            String str = f7432a;
            ho.b(str, "cacheVideo");
            rt rtVar2 = new rt();
            rtVar2.a(a2);
            rtVar2.c(videoInfo.getVideoDownloadUrl());
            rtVar2.a(videoInfo.a());
            rtVar2.b(videoInfo.getSha256());
            rtVar2.b(videoInfo.isCheckSha256());
            rtVar2.a("video");
            rtVar2.a(true);
            rtVar2.a(Long.valueOf(j));
            rtVar2.c(true);
            ru a4 = this.d.a(rtVar2);
            if (a4 == null || com.huawei.openalliance.ad.utils.cz.b(a4.a())) {
                ho.c(str, "dealVideo, download video failed!");
                return false;
            }
            String a5 = a4.a();
            videoInfo.a(a5);
            a2.i(a5);
            eVar.w(a5);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(VideoInfo videoInfo, ImageInfo imageInfo) {
        if (videoInfo == null || imageInfo == null) {
            return false;
        }
        if (1 != videoInfo.getVideoPlayMode() || a(videoInfo)) {
            return true;
        }
        ho.c(f7432a, "cache mode video is only allowed to download in network %d", Integer.valueOf(videoInfo.getDownloadNetwork()));
        return false;
    }

    private boolean a(VideoInfo videoInfo) {
        if (this.h || this.m || videoInfo.getDownloadNetwork() == 1) {
            return true;
        }
        return videoInfo.getDownloadNetwork() == 0 && com.huawei.openalliance.ad.utils.bv.c(this.g);
    }

    private boolean a(final long j, String str, List<INativeAd> list, final com.huawei.openalliance.ad.inter.data.e eVar) {
        boolean z = true;
        if (this.l && b(eVar)) {
            eVar.i(true);
        }
        if (this.l || !b(eVar)) {
            String str2 = f7432a;
            ho.b(str2, "parser, add nativeAd");
            if (com.huawei.openalliance.ad.utils.c.a(this.g, eVar.ab(), eVar.getSlotId(), eVar.e())) {
                eVar.a(99);
            }
            list.add(eVar);
            z = false;
            if (b(eVar.getCreativeType())) {
                ho.b(str2, "use engine down");
                return false;
            }
            if (this.m && b(eVar)) {
                com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.pe.2
                    @Override // java.lang.Runnable
                    public void run() {
                        VideoInfo videoInfo = eVar.getVideoInfo();
                        ImageInfo a2 = pe.this.a(eVar);
                        if (pe.this.a(videoInfo, a2)) {
                            pe.this.a(eVar, j, videoInfo, a2);
                        }
                    }
                });
            }
        } else {
            a(str, eVar, j);
        }
        return z;
    }

    private static void a(Map<String, List<INativeAd>> map, List<INativeAd> list) {
        if (map == null || list == null || list.isEmpty()) {
            return;
        }
        for (INativeAd iNativeAd : list) {
            String slotId = ((com.huawei.openalliance.ad.inter.data.e) iNativeAd).getSlotId();
            List<INativeAd> list2 = map.get(slotId);
            if (com.huawei.openalliance.ad.utils.bg.a(list2)) {
                list2 = new ArrayList<>();
            }
            list2.add(iNativeAd);
            map.put(slotId, list2);
        }
    }

    private void a(List<Template> list) {
        new pr(this.g).a(list);
    }

    private void a(final String str, final com.huawei.openalliance.ad.inter.data.e eVar, final long j) {
        ho.b(f7432a, "dealVideo, adId: %s, directCacheVideo: %s.", str, Boolean.valueOf(this.m));
        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.pe.3
            @Override // java.lang.Runnable
            public void run() {
                VideoInfo videoInfo = eVar.getVideoInfo();
                ImageInfo a2 = pe.this.a(eVar);
                if (pe.this.a(videoInfo, a2) && pe.this.a(eVar, j, videoInfo, a2)) {
                    if (com.huawei.openalliance.ad.utils.c.a(pe.this.g, eVar.ab(), eVar.getSlotId(), eVar.e())) {
                        eVar.a(99);
                        eVar.b(true);
                    }
                    HashMap hashMap = new HashMap();
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(eVar);
                    com.huawei.openalliance.ad.utils.c.a(hashMap, str, arrayList);
                    pe.this.c.a(hashMap);
                }
            }
        });
    }

    private void a(ImageInfo imageInfo, String str) {
        if (imageInfo.getWidth() <= 0 || imageInfo.getHeight() <= 0) {
            Rect a2 = com.huawei.openalliance.ad.utils.az.a(str);
            int width = a2.width();
            int height = a2.height();
            if (width <= 0 || height <= 0) {
                return;
            }
            imageInfo.b(width);
            imageInfo.a(height);
        }
    }

    private void a(MetaData metaData) {
        if (metaData == null || metaData.K() == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.e.a(this.g, metaData.K());
    }

    private void a(long j, AdParseConfig adParseConfig) {
        ArrayList arrayList;
        Vector vector;
        boolean[] zArr;
        ho.b(f7432a, "parser");
        this.k = adParseConfig.c();
        AdContentRsp adContentRsp = this.b;
        if (adContentRsp == null) {
            this.c.a(ErrorCode.ERROR_CODE_OTHER);
            return;
        }
        ContentIdListener contentIdListener = this.j;
        if (contentIdListener != null) {
            contentIdListener.a(adContentRsp.d());
        }
        List<Ad30> c = this.b.c();
        if (com.huawei.openalliance.ad.utils.bg.a(c)) {
            this.c.a(700);
            return;
        }
        boolean a2 = adParseConfig.a();
        if (a2) {
            a2 = b(c);
        }
        boolean z = a2;
        a(this.b.i());
        HashMap hashMap = new HashMap(0);
        char c2 = 1;
        boolean[] zArr2 = {false};
        byte[] b2 = com.huawei.openalliance.ad.utils.cp.b(this.g);
        Vector vector2 = new Vector();
        ArrayList arrayList2 = new ArrayList();
        for (Ad30 ad30 : c) {
            String a3 = ad30.a();
            int b3 = ad30.b();
            if (200 != b3) {
                String str = f7432a;
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(b3);
                objArr[c2] = a3;
                ho.b(str, "ad failed, retcode30: %s, slotId: %s.", objArr);
            }
            List<Content> c3 = ad30.c();
            String e = ad30.e();
            if (com.huawei.openalliance.ad.utils.bg.a(c3)) {
                ho.b(f7432a, "parser, contents is empty");
            } else {
                for (Content content : c3) {
                    if (z) {
                        arrayList = arrayList2;
                        vector = vector2;
                        zArr = zArr2;
                        arrayList.add(new b(j, zArr2, b2, a3, e, vector2, content));
                    } else {
                        arrayList = arrayList2;
                        vector = vector2;
                        zArr = zArr2;
                        a(j, zArr, b2, a3, e, vector, content);
                    }
                    arrayList2 = arrayList;
                    vector2 = vector;
                    zArr2 = zArr;
                    c2 = 1;
                }
            }
        }
        ArrayList arrayList3 = arrayList2;
        Vector vector3 = vector2;
        boolean[] zArr3 = zArr2;
        if (z && !arrayList3.isEmpty()) {
            com.huawei.openalliance.ad.utils.m.a(arrayList3, 2000L);
        }
        if (!vector3.isEmpty()) {
            a(hashMap, vector3);
        }
        if (!hashMap.isEmpty()) {
            this.c.a(hashMap);
            return;
        }
        ho.b(f7432a, "parser, nativeAdsMap is empty");
        if (zArr3[0]) {
            return;
        }
        this.c.a(700);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Void a(long j, boolean[] zArr, byte[] bArr, String str, String str2, List<INativeAd> list, Content content) {
        String str3;
        String str4;
        if (content == null) {
            str3 = f7432a;
            str4 = "parser, content is null";
        } else {
            content.a(this.b.h(), this.i);
            MetaData c = content.c();
            if (c != null) {
                com.huawei.openalliance.ad.inter.data.e a2 = a(str, content, bArr, c);
                a2.i(this.b.k());
                a2.c(this.b.n());
                a2.k(this.b.d());
                a2.n(this.b.p());
                a2.o(this.b.q());
                a2.Q(str2);
                a2.u(this.b.u());
                if ((a2.aa() == null || a2.aa().intValue() != 3) ? a(j, str, list, a2) : new qz(this.g, this.l, this.m, this.h).a(j, str, list, a2, this.c)) {
                    zArr[0] = true;
                }
                a(c);
                return null;
            }
            str3 = f7432a;
            str4 = "parser, metaData is null";
        }
        ho.b(str3, str4);
        return null;
    }

    private com.huawei.openalliance.ad.inter.data.e a(String str, Content content, byte[] bArr, MetaData metaData) {
        return pd.a(str, content, bArr, this.i, this.k, metaData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImageInfo a(com.huawei.openalliance.ad.inter.data.e eVar) {
        List<ImageInfo> imageInfos = eVar.getImageInfos();
        if (com.huawei.openalliance.ad.utils.bg.a(imageInfos)) {
            return null;
        }
        return imageInfos.get(0);
    }

    class b implements Callable<Void> {
        private long b;
        private boolean[] c;
        private byte[] d;
        private String e;
        private String f;
        private List<INativeAd> g;
        private Content h;

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() {
            return pe.this.a(this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public b(long j, boolean[] zArr, byte[] bArr, String str, String str2, List<INativeAd> list, Content content) {
            this.b = j;
            this.c = zArr;
            this.d = bArr;
            this.e = str;
            this.f = str2;
            this.g = list;
            this.h = content;
        }
    }

    public pe(final Context context, a aVar, boolean z) {
        this.h = false;
        this.i = 3;
        this.k = false;
        this.l = false;
        this.m = false;
        this.g = context.getApplicationContext();
        this.c = aVar;
        this.d = fb.a(context);
        this.e = fh.b(context);
        if (z) {
            com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.pe.1
                @Override // java.lang.Runnable
                public void run() {
                    pe.this.f = com.huawei.openalliance.ad.utils.cw.c(context) + File.separator + Constants.PPS_ROOT_PATH + File.separator + "video" + File.separator;
                }
            });
            return;
        }
        this.f = com.huawei.openalliance.ad.utils.cw.c(context) + File.separator + Constants.PPS_ROOT_PATH + File.separator + "video" + File.separator;
    }

    public pe(Context context, a aVar, int i, boolean z) {
        this(context, aVar, z);
        this.i = i;
    }
}
