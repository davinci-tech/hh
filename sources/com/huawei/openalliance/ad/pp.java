package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class pp {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7444a = new byte[0];
    private static pp b;
    private Context c;
    private ContentRecord d;
    private qq e;
    private String f = null;
    private String g = null;

    public boolean b(Context context, Bundle bundle, String str, String str2) {
        ho.b("TemplateActionProcessor", "onEasterEggClick");
        return a(context, bundle, str, str2, EventType.EASTEREGGCLICK);
    }

    public void b(String str) {
        String str2 = this.f;
        if (str2 != null && str2.equals(str)) {
            if (str != null || HiAd.getInstance(this.c).getExtensionActionListener() == null) {
                return;
            }
            HiAd.getInstance(this.c).getExtensionActionListener().onDismiss(null);
            return;
        }
        this.f = str;
        ho.b("TemplateActionProcessor", "onDismiss");
        if (HiAd.getInstance(this.c).getExtensionActionListener() != null) {
            HiAd.getInstance(this.c).getExtensionActionListener().onDismiss(str);
        }
    }

    public void b(Bundle bundle, String str) {
        try {
            ho.b("TemplateActionProcessor", "onClose");
            int i = bundle.getInt(ParamConstants.Param.X_AXIS);
            int i2 = bundle.getInt(ParamConstants.Param.Y_AXIS);
            qq qqVar = this.e;
            if (qqVar != null) {
                qqVar.a(i, i2);
            }
            b(str);
        } catch (Throwable th) {
            ho.c("TemplateActionProcessor", "onClose err: %s", th.getClass().getSimpleName());
        }
    }

    public boolean a(AdContentData adContentData) {
        if (adContentData == null) {
            return false;
        }
        ContentRecord a2 = adContentData.a() == 1 ? et.b(this.c).a(adContentData.d(), adContentData.i(), adContentData.c()) : db.a().a(adContentData.f());
        this.d = a2;
        if (a2 == null) {
            return false;
        }
        a2.v(adContentData.f());
        this.d.c(adContentData.b());
        this.d.b(adContentData.e());
        this.d.c(adContentData.h());
        this.d.y(adContentData.g());
        Context context = this.c;
        this.e = new ou(context, sc.a(context, adContentData.a()), this.d);
        return true;
    }

    public boolean a(Context context, Bundle bundle, String str, String str2) {
        ho.b("TemplateActionProcessor", "onClick");
        return a(context, bundle, str, str2, EventType.CLICK);
    }

    public void a(String str, String str2) {
        ho.b("TemplateActionProcessor", "onShow");
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(str);
        c0207a.d(Constants.DEF_SLOT_POSITION);
        qq qqVar = this.e;
        if (qqVar != null) {
            qqVar.b(c0207a.a());
        }
        if (HiAd.getInstance(this.c).getExtensionActionListener() != null) {
            HiAd.getInstance(this.c).getExtensionActionListener().onShow(str2);
        }
    }

    public void a(String str, Bundle bundle) {
        int i;
        try {
            if (!bundle.getBoolean(ParamConstants.Param.HAS_PLAYED, true)) {
                sz.a(this.c, this.d, new HashMap(0)).a();
            }
            i = bundle.getInt("errCode");
        } catch (Throwable th) {
            ho.b("TemplateActionProcessor", "get errCode err: %s", th.getClass().getSimpleName());
            i = -1;
        }
        a(str, i);
    }

    public void a(String str, int i) {
        String str2 = this.g;
        if (str2 == null || !str2.equals(str)) {
            this.g = str;
            ho.b("TemplateActionProcessor", "onFail");
            if (HiAd.getInstance(this.c).getExtensionActionListener() != null) {
                HiAd.getInstance(this.c).getExtensionActionListener().onFail(str, i);
            }
        }
    }

    public void a(String str) {
        ho.b("TemplateActionProcessor", "onPrepare");
        if (HiAd.getInstance(this.c).getExtensionActionListener() != null) {
            HiAd.getInstance(this.c).getExtensionActionListener().onPrepare(str);
        }
    }

    public void a(Bundle bundle, String str) {
        try {
            ho.b("TemplateActionProcessor", "onEnd");
            long j = bundle.getLong("startTime");
            long j2 = bundle.getLong("endTime");
            int i = bundle.getInt(ParamConstants.Param.START_PROGRESS);
            int i2 = bundle.getInt(ParamConstants.Param.END_PROGRESS);
            if (bundle.getBoolean(ParamConstants.Param.JUMP_LANDING, false)) {
                HashMap hashMap = new HashMap();
                hashMap.put(MapKeyNames.LINKED_CUSTOM_VIDEO_SOUND_SWITCH, bundle.getString(MapKeyNames.LINKED_CUSTOM_VIDEO_SOUND_SWITCH));
                hashMap.put(MapKeyNames.LINKED_CUSTOM_VIDEO_PROGRESS, String.valueOf(bundle.getInt(MapKeyNames.LINKED_CUSTOM_VIDEO_PROGRESS)));
                hashMap.put(MapKeyNames.USE_TEMPLATE, Boolean.TRUE.toString());
                sz.a(this.c, this.d, hashMap).a();
            }
            qq qqVar = this.e;
            if (qqVar != null) {
                qqVar.d(Long.valueOf(j).longValue(), Long.valueOf(j2).longValue(), Integer.valueOf(i).intValue(), Integer.valueOf(i2).intValue());
            }
            b(str);
        } catch (Throwable th) {
            ho.c("TemplateActionProcessor", "onEnd err: %s", th.getClass().getSimpleName());
        }
    }

    private void b(String str, String str2) {
        a.C0207a c0207a = new a.C0207a();
        c0207a.a((Long) null).a((Integer) null).b((Integer) 14).a(str).e(str2).d(String.format("%s,%s", 0, 0));
        this.e.a(c0207a.a());
    }

    private static pp b(Context context) {
        pp ppVar;
        synchronized (f7444a) {
            if (b == null) {
                b = new pp(context);
            }
            ppVar = b;
        }
        return ppVar;
    }

    private boolean a(Context context, Bundle bundle, String str, String str2, EventType eventType) {
        try {
            int i = bundle.getInt(ParamConstants.Param.X_AXIS);
            int i2 = bundle.getInt(ParamConstants.Param.Y_AXIS);
            int i3 = bundle.getInt(ParamConstants.Param.CLICK_SOURCE);
            MaterialClickInfo materialClickInfo = (MaterialClickInfo) com.huawei.openalliance.ad.utils.be.b(bundle.getString("click_info"), MaterialClickInfo.class, new Class[0]);
            ta a2 = sz.a(context, this.d, new HashMap(0));
            if (a2.a()) {
                if (this.e != null) {
                    if (eventType == EventType.CLICK) {
                        b(com.huawei.openalliance.ad.utils.b.a(context), (materialClickInfo == null || !com.huawei.openalliance.ad.utils.cz.p(materialClickInfo.c())) ? null : materialClickInfo.c());
                    }
                    b.a aVar = new b.a();
                    aVar.a(i).b(i2).b(a2.c()).a(Integer.valueOf(i3)).a(materialClickInfo).d(str).a(eventType);
                    this.e.a(aVar.a());
                }
                if (HiAd.getInstance(this.c).getExtensionActionListener() == null) {
                    return true;
                }
                HiAd.getInstance(this.c).getExtensionActionListener().onClick(str2);
                return true;
            }
        } catch (Throwable th) {
            ho.c("TemplateActionProcessor", "deal with click err: %s", th.getClass().getSimpleName());
        }
        return false;
    }

    public static pp a(Context context) {
        return b(context);
    }

    private pp(Context context) {
        this.c = context.getApplicationContext();
    }
}
