package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import java.util.List;

/* loaded from: classes5.dex */
public class ev extends ep implements fu {
    private static final byte[] c = new byte[0];

    @Override // com.huawei.openalliance.ad.fu
    public void a(String str, String str2, String str3) {
        ho.b("ContentTemplateRecordDao", "deleteContentByIds ids: %s %s, reason: %s", str, str2, str3);
        a(ContentTemplateRecord.class, fi.CONTENT_TEMPLATE_RECORD_BY_IDS, new String[]{str, str2});
    }

    @Override // com.huawei.openalliance.ad.fu
    public void a(ContentTemplateRecord contentTemplateRecord) {
        if (contentTemplateRecord == null) {
            return;
        }
        synchronized (c) {
            if (a(contentTemplateRecord.a(), contentTemplateRecord.b()) != null) {
                a(ContentTemplateRecord.class, contentTemplateRecord.d(this.f6846a), fi.CONTENT_TEMPLATE_RECORD_BY_IDS, new String[]{contentTemplateRecord.a(), contentTemplateRecord.b()});
            } else {
                b(contentTemplateRecord);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.fu
    public ContentTemplateRecord a(String str, String str2) {
        List a2 = a(ContentTemplateRecord.class, null, fi.CONTENT_TEMPLATE_RECORD_BY_IDS, new String[]{str, str2}, null, null);
        if (a2.isEmpty()) {
            return null;
        }
        return (ContentTemplateRecord) a2.get(0);
    }

    private void b(ContentTemplateRecord contentTemplateRecord) {
        a(ContentTemplateRecord.class, contentTemplateRecord.d(this.f6846a));
    }

    public static ev a(Context context) {
        return new ev(context);
    }

    protected ev(Context context) {
        super(context);
    }
}
