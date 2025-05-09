package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IWeatherSearch;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;

/* loaded from: classes8.dex */
public final class hd implements IWeatherSearch {

    /* renamed from: a, reason: collision with root package name */
    private Context f1117a;
    private WeatherSearchQuery b;
    private WeatherSearch.OnWeatherSearchListener c;
    private LocalWeatherLiveResult d;
    private LocalWeatherForecastResult e;
    private Handler f;

    public hd(Context context) throws AMapException {
        this.f = null;
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1117a = context.getApplicationContext();
        this.f = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public final WeatherSearchQuery getQuery() {
        return this.b;
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public final void setQuery(WeatherSearchQuery weatherSearchQuery) {
        this.b = weatherSearchQuery;
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public final void searchWeatherAsyn() {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hd.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.arg1 = 13;
                    Bundle bundle = new Bundle();
                    if (hd.this.b == null) {
                        try {
                            throw new AMapException("无效的参数 - IllegalArgumentException");
                        } catch (AMapException e) {
                            fd.a(e, "WeatherSearch", "searchWeatherAsyn");
                            return;
                        }
                    }
                    if (hd.this.b.getType() == 1) {
                        try {
                            try {
                                hd hdVar = hd.this;
                                hdVar.d = hdVar.a();
                                bundle.putInt("errorCode", 1000);
                                return;
                            } finally {
                                fo.l lVar = new fo.l();
                                obtainMessage.what = CommonPickerConstant.RETCODE.H5_UNAUTHORIZED_CODE;
                                lVar.b = hd.this.c;
                                lVar.f1052a = hd.this.d;
                                obtainMessage.obj = lVar;
                                obtainMessage.setData(bundle);
                                hd.this.f.sendMessage(obtainMessage);
                            }
                        } catch (AMapException e2) {
                            bundle.putInt("errorCode", e2.getErrorCode());
                            fd.a(e2, "WeatherSearch", "searchWeatherAsyn");
                            return;
                        } catch (Throwable th) {
                            fd.a(th, "WeatherSearch", "searchWeatherAnsyThrowable");
                            return;
                        }
                    }
                    if (hd.this.b.getType() == 2) {
                        try {
                            try {
                                hd hdVar2 = hd.this;
                                hdVar2.e = hdVar2.b();
                                bundle.putInt("errorCode", 1000);
                            } finally {
                                fo.k kVar = new fo.k();
                                obtainMessage.what = 1302;
                                kVar.b = hd.this.c;
                                kVar.f1051a = hd.this.e;
                                obtainMessage.obj = kVar;
                                obtainMessage.setData(bundle);
                                hd.this.f.sendMessage(obtainMessage);
                            }
                        } catch (AMapException e3) {
                            bundle.putInt("errorCode", e3.getErrorCode());
                            fd.a(e3, "WeatherSearch", "searchWeatherAsyn");
                        } catch (Throwable th2) {
                            fd.a(th2, "WeatherSearch", "searchWeatherAnsyThrowable");
                        }
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalWeatherLiveResult a() throws AMapException {
        fm.a(this.f1117a);
        if (this.b == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        gn gnVar = new gn(this.f1117a, this.b);
        return LocalWeatherLiveResult.createPagedResult(gnVar.f(), gnVar.d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalWeatherForecastResult b() throws AMapException {
        fm.a(this.f1117a);
        if (this.b == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        gm gmVar = new gm(this.f1117a, this.b);
        return LocalWeatherForecastResult.createPagedResult(gmVar.f(), gmVar.d());
    }

    @Override // com.amap.api.services.interfaces.IWeatherSearch
    public final void setOnWeatherSearchListener(WeatherSearch.OnWeatherSearchListener onWeatherSearchListener) {
        this.c = onWeatherSearchListener;
    }
}
