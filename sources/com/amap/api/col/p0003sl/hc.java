package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.LatLonSharePoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.interfaces.IShareSearch;
import com.amap.api.services.share.ShareSearch;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;

/* loaded from: classes8.dex */
public final class hc implements IShareSearch {
    private static String b = "http://wb.amap.com/?r=%f,%f,%s,%f,%f,%s,%d,%d,%d,%s,%s,%s&sourceapplication=openapi/0";
    private static String c = "http://wb.amap.com/?q=%f,%f,%s&sourceapplication=openapi/0";
    private static String d = "http://wb.amap.com/?n=%f,%f,%f,%f,%d&sourceapplication=openapi/0";
    private static String e = "http://wb.amap.com/?p=%s,%f,%f,%s,%s&sourceapplication=openapi/0";
    private static final String f = "";

    /* renamed from: a, reason: collision with root package name */
    private Context f1110a;
    private ShareSearch.OnShareSearchListener g;

    public hc(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1110a = context;
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final void setOnShareSearchListener(ShareSearch.OnShareSearchListener onShareSearchListener) {
        this.g = onShareSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final void searchPoiShareUrlAsyn(final PoiItem poiItem) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hc.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (hc.this.g == null) {
                        return;
                    }
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.arg1 = 11;
                    obtainMessage.what = 1100;
                    obtainMessage.obj = hc.this.g;
                    try {
                        try {
                            String searchPoiShareUrl = hc.this.searchPoiShareUrl(poiItem);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchPoiShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        }
                    } finally {
                        fo.a().sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final void searchBusRouteShareUrlAsyn(final ShareSearch.ShareBusRouteQuery shareBusRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hc.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (hc.this.g == null) {
                        return;
                    }
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.arg1 = 11;
                    obtainMessage.what = 1103;
                    obtainMessage.obj = hc.this.g;
                    try {
                        try {
                            String searchBusRouteShareUrl = hc.this.searchBusRouteShareUrl(shareBusRouteQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchBusRouteShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        }
                    } finally {
                        fo.a().sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final void searchWalkRouteShareUrlAsyn(final ShareSearch.ShareWalkRouteQuery shareWalkRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hc.3
                @Override // java.lang.Runnable
                public final void run() {
                    if (hc.this.g == null) {
                        return;
                    }
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.arg1 = 11;
                    obtainMessage.what = ExceptionCode.CHECK_FILE_SIZE_FAILED;
                    obtainMessage.obj = hc.this.g;
                    try {
                        try {
                            String searchWalkRouteShareUrl = hc.this.searchWalkRouteShareUrl(shareWalkRouteQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchWalkRouteShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        }
                    } finally {
                        fo.a().sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final void searchDrivingRouteShareUrlAsyn(final ShareSearch.ShareDrivingRouteQuery shareDrivingRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hc.4
                @Override // java.lang.Runnable
                public final void run() {
                    if (hc.this.g == null) {
                        return;
                    }
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.arg1 = 11;
                    obtainMessage.what = ExceptionCode.CHECK_FILE_HASH_FAILED;
                    obtainMessage.obj = hc.this.g;
                    try {
                        try {
                            String searchDrivingRouteShareUrl = hc.this.searchDrivingRouteShareUrl(shareDrivingRouteQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchDrivingRouteShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        }
                    } finally {
                        fo.a().sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final void searchNaviShareUrlAsyn(final ShareSearch.ShareNaviQuery shareNaviQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hc.5
                @Override // java.lang.Runnable
                public final void run() {
                    if (hc.this.g == null) {
                        return;
                    }
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.arg1 = 11;
                    obtainMessage.what = 1102;
                    obtainMessage.obj = hc.this.g;
                    try {
                        try {
                            String searchNaviShareUrl = hc.this.searchNaviShareUrl(shareNaviQuery);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchNaviShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        }
                    } finally {
                        fo.a().sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final void searchLocationShareUrlAsyn(final LatLonSharePoint latLonSharePoint) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hc.6
                @Override // java.lang.Runnable
                public final void run() {
                    if (hc.this.g == null) {
                        return;
                    }
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.arg1 = 11;
                    obtainMessage.what = 1101;
                    obtainMessage.obj = hc.this.g;
                    try {
                        try {
                            String searchLocationShareUrl = hc.this.searchLocationShareUrl(latLonSharePoint);
                            Bundle bundle = new Bundle();
                            bundle.putString("shareurlkey", searchLocationShareUrl);
                            obtainMessage.setData(bundle);
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e2) {
                            obtainMessage.arg2 = e2.getErrorCode();
                        }
                    } finally {
                        fo.a().sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final String searchPoiShareUrl(PoiItem poiItem) throws AMapException {
        if (poiItem != null) {
            try {
                if (poiItem.getLatLonPoint() != null) {
                    LatLonPoint latLonPoint = poiItem.getLatLonPoint();
                    return new gg(this.f1110a, String.format(e, poiItem.getPoiId(), Double.valueOf(latLonPoint.getLatitude()), Double.valueOf(latLonPoint.getLongitude()), poiItem.getTitle(), poiItem.getSnippet())).d();
                }
            } catch (AMapException e2) {
                fd.a(e2, "ShareSearch", "searchPoiShareUrl");
                throw e2;
            }
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final String searchNaviShareUrl(ShareSearch.ShareNaviQuery shareNaviQuery) throws AMapException {
        String format;
        try {
            if (shareNaviQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            ShareSearch.ShareFromAndTo fromAndTo = shareNaviQuery.getFromAndTo();
            if (fromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = fromAndTo.getFrom();
            LatLonPoint to = fromAndTo.getTo();
            int naviMode = shareNaviQuery.getNaviMode();
            if (fromAndTo.getFrom() == null) {
                format = String.format(d, null, null, Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), Integer.valueOf(naviMode));
            } else {
                format = String.format(d, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), Integer.valueOf(naviMode));
            }
            return new gg(this.f1110a, format).d();
        } catch (AMapException e2) {
            fd.a(e2, "ShareSearch", "searchNaviShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final String searchLocationShareUrl(LatLonSharePoint latLonSharePoint) throws AMapException {
        try {
            if (latLonSharePoint == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new gg(this.f1110a, String.format(c, Double.valueOf(latLonSharePoint.getLatitude()), Double.valueOf(latLonSharePoint.getLongitude()), latLonSharePoint.getSharePointName())).d();
        } catch (AMapException e2) {
            fd.a(e2, "ShareSearch", "searchLocationShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final String searchBusRouteShareUrl(ShareSearch.ShareBusRouteQuery shareBusRouteQuery) throws AMapException {
        try {
            if (shareBusRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            int busMode = shareBusRouteQuery.getBusMode();
            ShareSearch.ShareFromAndTo shareFromAndTo = shareBusRouteQuery.getShareFromAndTo();
            if (shareFromAndTo.getFrom() == null || shareFromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = shareFromAndTo.getFrom();
            LatLonPoint to = shareFromAndTo.getTo();
            String fromName = shareFromAndTo.getFromName();
            String toName = shareFromAndTo.getToName();
            String str = b;
            String str2 = f;
            return new gg(this.f1110a, String.format(str, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), fromName, Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), toName, Integer.valueOf(busMode), 1, 0, str2, str2, str2)).d();
        } catch (AMapException e2) {
            fd.a(e2, "ShareSearch", "searchBusRouteShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final String searchDrivingRouteShareUrl(ShareSearch.ShareDrivingRouteQuery shareDrivingRouteQuery) throws AMapException {
        try {
            if (shareDrivingRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            int drivingMode = shareDrivingRouteQuery.getDrivingMode();
            ShareSearch.ShareFromAndTo shareFromAndTo = shareDrivingRouteQuery.getShareFromAndTo();
            if (shareFromAndTo.getFrom() == null || shareFromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = shareFromAndTo.getFrom();
            LatLonPoint to = shareFromAndTo.getTo();
            String fromName = shareFromAndTo.getFromName();
            String toName = shareFromAndTo.getToName();
            String str = b;
            String str2 = f;
            return new gg(this.f1110a, String.format(str, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), fromName, Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), toName, Integer.valueOf(drivingMode), 0, 0, str2, str2, str2)).d();
        } catch (AMapException e2) {
            fd.a(e2, "ShareSearch", "searchDrivingRouteShareUrl");
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.IShareSearch
    public final String searchWalkRouteShareUrl(ShareSearch.ShareWalkRouteQuery shareWalkRouteQuery) throws AMapException {
        try {
            if (shareWalkRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            int walkMode = shareWalkRouteQuery.getWalkMode();
            ShareSearch.ShareFromAndTo shareFromAndTo = shareWalkRouteQuery.getShareFromAndTo();
            if (shareFromAndTo.getFrom() == null || shareFromAndTo.getTo() == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            LatLonPoint from = shareFromAndTo.getFrom();
            LatLonPoint to = shareFromAndTo.getTo();
            String fromName = shareFromAndTo.getFromName();
            String toName = shareFromAndTo.getToName();
            String str = b;
            String str2 = f;
            return new gg(this.f1110a, String.format(str, Double.valueOf(from.getLatitude()), Double.valueOf(from.getLongitude()), fromName, Double.valueOf(to.getLatitude()), Double.valueOf(to.getLongitude()), toName, Integer.valueOf(walkMode), 2, 0, str2, str2, str2)).d();
        } catch (AMapException e2) {
            fd.a(e2, "ShareSearch", "searchWalkRouteShareUrl");
            throw e2;
        }
    }
}
