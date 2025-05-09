package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.routepoisearch.RoutePOIItem;
import com.amap.api.services.routepoisearch.RoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearchQuery;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class gf extends ew<RoutePOISearchQuery, RoutePOISearchResult> {
    public gf(Context context, RoutePOISearchQuery routePOISearchQuery) {
        super(context, routePOISearchQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        String str;
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        StringBuffer append = stringBuffer.append("&range=");
        StringBuilder sb = new StringBuilder();
        sb.append(((RoutePOISearchQuery) this.b).getRange());
        append.append(sb.toString());
        switch (AnonymousClass1.f1067a[((RoutePOISearchQuery) this.b).getSearchType().ordinal()]) {
            case 1:
                str = "010100";
                break;
            case 2:
                str = "030000";
                break;
            case 3:
                str = "160300";
                break;
            case 4:
                str = "200300";
                break;
            case 5:
                str = "010300";
                break;
            case 6:
                str = "180300";
                break;
            default:
                str = "";
                break;
        }
        if (((RoutePOISearchQuery) this.b).getPolylines() != null && ((RoutePOISearchQuery) this.b).getPolylines().size() > 0) {
            stringBuffer.append("&polyline=").append(fd.a(((RoutePOISearchQuery) this.b).getPolylines()));
        } else {
            stringBuffer.append("&origin=").append(fd.a(((RoutePOISearchQuery) this.b).getFrom()));
            stringBuffer.append("&destination=").append(fd.a(((RoutePOISearchQuery) this.b).getTo()));
            StringBuffer append2 = stringBuffer.append("&strategy=");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(((RoutePOISearchQuery) this.b).getMode());
            append2.append(sb2.toString());
        }
        stringBuffer.append("&types=").append(str);
        return stringBuffer.toString();
    }

    /* renamed from: com.amap.api.col.3sl.gf$1, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1067a;

        static {
            int[] iArr = new int[RoutePOISearch.RoutePOISearchType.values().length];
            f1067a = iArr;
            try {
                iArr[RoutePOISearch.RoutePOISearchType.TypeGasStation.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1067a[RoutePOISearch.RoutePOISearchType.TypeMaintenanceStation.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1067a[RoutePOISearch.RoutePOISearchType.TypeATM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1067a[RoutePOISearch.RoutePOISearchType.TypeToilet.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1067a[RoutePOISearch.RoutePOISearchType.TypeFillingStation.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1067a[RoutePOISearch.RoutePOISearchType.TypeServiceArea.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public RoutePOISearchResult a(String str) throws AMapException {
        ArrayList<RoutePOIItem> arrayList = new ArrayList<>();
        try {
            arrayList = fl.i(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new RoutePOISearchResult(arrayList, (RoutePOISearchQuery) this.b);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/place/route?";
    }
}
