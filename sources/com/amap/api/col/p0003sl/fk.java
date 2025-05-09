package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class fk extends ew<InputtipsQuery, ArrayList<Tip>> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public fk(Context context, InputtipsQuery inputtipsQuery) {
        super(context, inputtipsQuery);
    }

    private static ArrayList<Tip> c(String str) throws AMapException {
        try {
            return fl.h(new JSONObject(str));
        } catch (JSONException e) {
            fd.a(e, "InputtipsHandler", "paseJSON");
            return null;
        }
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/assistant/inputtips?";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("output=json");
        String b = b(((InputtipsQuery) this.b).getKeyword());
        if (!TextUtils.isEmpty(b)) {
            stringBuffer.append("&keywords=").append(b);
        }
        String city = ((InputtipsQuery) this.b).getCity();
        if (!fl.g(city)) {
            stringBuffer.append("&city=").append(b(city));
        }
        String type = ((InputtipsQuery) this.b).getType();
        if (!fl.g(type)) {
            stringBuffer.append("&type=").append(b(type));
        }
        if (((InputtipsQuery) this.b).getCityLimit()) {
            stringBuffer.append("&citylimit=true");
        } else {
            stringBuffer.append("&citylimit=false");
        }
        LatLonPoint location = ((InputtipsQuery) this.b).getLocation();
        if (location != null) {
            stringBuffer.append("&location=").append(location.getLongitude()).append(",").append(location.getLatitude());
        }
        stringBuffer.append("&key=").append(hn.f(this.i));
        return stringBuffer.toString();
    }
}
