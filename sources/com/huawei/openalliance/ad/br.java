package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.template.downloadbuttonstyle.RemoteButtonStyleAttr;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.views.AppDownloadButton;

/* loaded from: classes5.dex */
public class br {
    public static com.huawei.hms.ads.template.downloadbuttonstyle.a b(Context context, AppDownloadButton appDownloadButton, int i, RemoteButtonStyleAttr remoteButtonStyleAttr) {
        ho.a("UiEngineProxyUtils", "btnStyle: %s", Integer.valueOf(i));
        com.huawei.hms.ads.template.downloadbuttonstyle.a eVar = i != 1 ? i != 2 ? (i == 3 || i == 4) ? new com.huawei.hms.ads.template.downloadbuttonstyle.e(context, appDownloadButton, remoteButtonStyleAttr) : new com.huawei.hms.ads.template.downloadbuttonstyle.b(context, appDownloadButton) : new com.huawei.hms.ads.template.downloadbuttonstyle.c(context, appDownloadButton) : new com.huawei.hms.ads.template.downloadbuttonstyle.d(context, appDownloadButton);
        eVar.a(context);
        return eVar;
    }

    public static com.huawei.hms.ads.template.downloadbuttonstyle.a a(Context context, AppDownloadButton appDownloadButton, int i, RemoteButtonStyleAttr remoteButtonStyleAttr) {
        ho.a("UiEngineProxyUtils", "updateBtnStyle: %s", Integer.valueOf(i));
        com.huawei.hms.ads.template.downloadbuttonstyle.a bVar = i != 4 ? new com.huawei.hms.ads.template.downloadbuttonstyle.b(context, appDownloadButton) : new com.huawei.hms.ads.template.downloadbuttonstyle.e(context, appDownloadButton, remoteButtonStyleAttr);
        bVar.a();
        return bVar;
    }

    public static RemoteButtonStyleAttr a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        try {
            gk gkVar = new gk(bundle);
            RemoteButtonStyleAttr remoteButtonStyleAttr = (RemoteButtonStyleAttr) com.huawei.openalliance.ad.utils.be.b(gkVar.d(ParamConstants.BtnParams.BUTTON_STYLE_JSON), RemoteButtonStyleAttr.class, new Class[0]);
            if (remoteButtonStyleAttr != null) {
                IBinder e = gkVar.e(ParamConstants.BtnParams.NORMAL_BG_DRAWABLE);
                if (e != null) {
                    remoteButtonStyleAttr.a((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e));
                }
                IBinder e2 = gkVar.e(ParamConstants.BtnParams.PROCESS_BG_DRAWABLE);
                if (e2 != null) {
                    remoteButtonStyleAttr.b((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e2));
                }
                IBinder e3 = gkVar.e(ParamConstants.BtnParams.INSTALL_BG_DRAWABLE);
                if (e3 != null) {
                    remoteButtonStyleAttr.c((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e3));
                }
                IBinder binder = bundle.getBinder(ParamConstants.BtnParams.CANCEL_BG_DRAWABLE);
                if (binder != null) {
                    remoteButtonStyleAttr.f((Drawable) ObjectWrapper.unwrap((IObjectWrapper) binder));
                }
                IBinder e4 = gkVar.e(ParamConstants.BtnParams.DOWN_CANCEL_BTN);
                if (e4 != null) {
                    remoteButtonStyleAttr.d((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e4));
                }
                IBinder e5 = gkVar.e(ParamConstants.BtnParams.NORMAL_BG_DRAWABLE_DARK);
                if (e5 != null) {
                    remoteButtonStyleAttr.e((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e5));
                }
                IBinder e6 = gkVar.e(ParamConstants.BtnParams.PROCESS_BG_DRAWABLE_DARK);
                if (e6 != null) {
                    remoteButtonStyleAttr.h((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e6));
                }
                IBinder e7 = gkVar.e(ParamConstants.BtnParams.INSTALL_BG_DRAWABLE_DARK);
                if (e7 != null) {
                    remoteButtonStyleAttr.i((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e7));
                }
                IBinder e8 = gkVar.e(ParamConstants.BtnParams.DOWN_CANCEL_BTN_DARK);
                if (e8 != null) {
                    remoteButtonStyleAttr.j((Drawable) ObjectWrapper.unwrap((IObjectWrapper) e8));
                }
                IBinder binder2 = bundle.getBinder(ParamConstants.BtnParams.CANCEL_BG_DRAWABLE_DARK);
                if (binder2 != null) {
                    remoteButtonStyleAttr.g((Drawable) ObjectWrapper.unwrap((IObjectWrapper) binder2));
                }
                remoteButtonStyleAttr.a(gkVar.d("download_text"));
                remoteButtonStyleAttr.b(gkVar.d("installed_text"));
                remoteButtonStyleAttr.e(gkVar.d(ParamConstants.BtnParams.WEB_PAGE_BUTTON_TEXT));
                remoteButtonStyleAttr.a(gkVar.a(ParamConstants.BtnParams.APP_RELATED, false));
                remoteButtonStyleAttr.c(gkVar.d(ParamConstants.BtnParams.PRIOR_BEFORE_DOWNLOAD_BUTTON_TEXT));
                remoteButtonStyleAttr.f(gkVar.d(ParamConstants.BtnParams.PRIOR_WEB_PAGE_BUTTON_TEXT));
                remoteButtonStyleAttr.d(gkVar.d(ParamConstants.BtnParams.PRIOR_PROMT_DOWNLOAD_BUTTON_TEXT));
            }
            return remoteButtonStyleAttr;
        } catch (Throwable th) {
            ho.c("UiEngineProxyUtils", "getAttrs err: %s", th.getClass().getSimpleName());
            return null;
        }
    }
}
