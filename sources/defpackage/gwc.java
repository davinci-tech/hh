package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class gwc {
    public static Marker d(GoogleMap googleMap, hjd hjdVar, int i) {
        if (googleMap == null) {
            ReleaseLogUtil.c("Track_GoogleSuportUtils", "addGoogleStartMarker googleMap is null.");
            return null;
        }
        if (hjdVar == null) {
            ReleaseLogUtil.c("Track_GoogleSuportUtils", "addGoogleStartMarker latLng is null.");
            return null;
        }
        MarkerOptions anchor = new MarkerOptions().position(c(hjdVar)).draggable(false).anchor(0.5f, 0.9f);
        a(i, anchor);
        return googleMap.addMarker(anchor);
    }

    private static void a(int i, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), gwe.d.get(i, R.drawable._2131428718_res_0x7f0b056e))));
    }

    public static Marker b(GoogleMap googleMap, hjd hjdVar, String str) {
        BitmapDescriptor fromResource;
        if (googleMap == null) {
            ReleaseLogUtil.c("Track_GoogleSuportUtils", "addGoogleEndMarker googleMap is null.");
            return null;
        }
        if (hjdVar == null) {
            ReleaseLogUtil.c("Track_GoogleSuportUtils", "addGoogleEndMarker latLng is null.");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            fromResource = BitmapDescriptorFactory.fromResource(R.drawable._2131428710_res_0x7f0b0566);
        } else {
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            BitmapDescriptor fromBitmap = decodeFile != null ? BitmapDescriptorFactory.fromBitmap(decodeFile) : null;
            fromResource = fromBitmap == null ? BitmapDescriptorFactory.fromResource(R.drawable._2131428710_res_0x7f0b0566) : fromBitmap;
        }
        return googleMap.addMarker(new MarkerOptions().position(c(hjdVar)).draggable(false).anchor(0.5f, 0.5f).icon(fromResource));
    }

    public static MarkerOptions aUJ_(Resources resources, LatLng latLng, String str) {
        if (latLng == null) {
            ReleaseLogUtil.d("Track_GoogleSuportUtils", "addMarker latLng is null.");
            return null;
        }
        MarkerOptions zIndex = new MarkerOptions().position(latLng).draggable(false).anchor(0.5f, 1.0f).zIndex(14.0f);
        TextPaint aUF_ = gwe.aUF_(resources, str.length());
        aUF_.getTextBounds(str, 0, str.length(), new Rect());
        Bitmap aUE_ = gwe.aUE_(resources);
        new Canvas(aUE_).drawText(str, aUE_.getWidth() / 2.0f, (((aUE_.getHeight() - aUF_.getFontMetrics().top) - aUF_.getFontMetrics().bottom) / 2.0f) - nsn.c(BaseApplication.getContext(), 2.0f), aUF_);
        zIndex.icon(BitmapDescriptorFactory.fromBitmap(aUE_));
        return zIndex;
    }

    public static LatLng c(hjd hjdVar) {
        if (hjdVar == null) {
            return null;
        }
        return new LatLng(hjdVar.b, hjdVar.d);
    }

    public static hjd e(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new hjd(latLng.latitude, latLng.longitude);
    }

    public static Marker e(GoogleMap googleMap, hjd hjdVar, String str) {
        BitmapDescriptor fromResource;
        if (googleMap == null) {
            ReleaseLogUtil.c("Track_GoogleSuportUtils", "addMarker addGoogleEndMarkerBg is null.");
            return null;
        }
        if (hjdVar == null) {
            ReleaseLogUtil.c("Track_GoogleSuportUtils", "addMarker addGoogleEndMarkerBg is null.");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            fromResource = BitmapDescriptorFactory.fromResource(R.drawable._2131428709_res_0x7f0b0565);
        } else {
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            BitmapDescriptor fromBitmap = decodeFile != null ? BitmapDescriptorFactory.fromBitmap(decodeFile) : null;
            fromResource = fromBitmap == null ? BitmapDescriptorFactory.fromResource(R.drawable._2131428709_res_0x7f0b0565) : fromBitmap;
        }
        return googleMap.addMarker(new MarkerOptions().position(c(hjdVar)).draggable(false).anchor(0.5f, 0.5f).icon(fromResource));
    }
}
