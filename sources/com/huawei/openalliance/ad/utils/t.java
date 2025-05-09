package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.dt;
import com.huawei.openalliance.ad.eu;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class t {
    public static void a(Map<String, List<IPlacementAd>> map, int i, boolean z, int i2) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, List<IPlacementAd>> entry : map.entrySet()) {
            ArrayList arrayList = new ArrayList(entry.getValue());
            String key = entry.getKey();
            if (!bg.a(arrayList)) {
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    IPlacementAd iPlacementAd = (IPlacementAd) arrayList.get(i3);
                    if (iPlacementAd != null) {
                        String contentId = iPlacementAd.getContentId();
                        if (z && (iPlacementAd instanceof com.huawei.openalliance.ad.inter.data.g)) {
                            com.huawei.openalliance.ad.inter.data.g gVar = (com.huawei.openalliance.ad.inter.data.g) iPlacementAd;
                            List<PlacementMediaFile> B = gVar.B();
                            if (!bg.a(B)) {
                                int i4 = 0;
                                for (int size2 = B.size(); i4 < size2; size2 = size2) {
                                    a(B.get(i4), i, z, contentId, key, gVar.h(), i2);
                                    i4++;
                                }
                            }
                        } else {
                            a(iPlacementAd.getMediaFile(), i, z, contentId, key, iPlacementAd instanceof com.huawei.openalliance.ad.inter.data.g ? ((com.huawei.openalliance.ad.inter.data.g) iPlacementAd).h() : null, i2);
                        }
                    }
                }
            }
        }
    }

    private static void a(final PlacementMediaFile placementMediaFile, final int i, final boolean z, final String str, final String str2, final Integer num, final int i2) {
        if (placementMediaFile == null || TextUtils.isEmpty(placementMediaFile.getUrl()) || placementMediaFile.getFileSize() <= 0) {
            return;
        }
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.t.2
            @Override // java.lang.Runnable
            public void run() {
                if (ho.a()) {
                    ho.a("ContentUtils", "download media:%s", dl.a(PlacementMediaFile.this.getUrl()));
                }
                com.huawei.openalliance.ad.dr drVar = new com.huawei.openalliance.ad.dr(PlacementMediaFile.this.getUrl(), (int) PlacementMediaFile.this.getFileSize(), PlacementMediaFile.this.getCheckSha256() == 0, PlacementMediaFile.this.getSha256(), Integer.valueOf(i), !z || 1 == PlacementMediaFile.this.getDownloadNetwork(), PlacementMediaFile.this.c(), str, str2, 60, z, i2);
                drVar.a(num);
                dt.h().a(drVar);
            }
        });
    }

    public static void a(final Context context, final String str, String str2, final String str3) {
        final Integer h;
        if (cz.b(str) || cz.b(str2) || (h = cz.h(str2)) == null) {
            return;
        }
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.t.1
            @Override // java.lang.Runnable
            public void run() {
                eu.a(context).a(str, h.intValue(), str3);
            }
        });
    }
}
