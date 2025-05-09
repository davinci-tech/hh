package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.text.TextUtils;
import com.huawei.appgallery.coreservice.api.ApiCode;
import com.huawei.appgallery.coreservice.api.IConnectionResult;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class aew implements IConnectionResult {

    /* renamed from: a, reason: collision with root package name */
    private final PendingIntent f193a;
    private final String b;
    private final int e;

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.f193a.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.e), this.f193a, this.b});
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public boolean hasResolution() {
        return (this.e == 0 || this.f193a == null) ? false : true;
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public int getStatusCode() {
        return this.e;
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public PendingIntent getResolution() {
        return this.f193a;
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public String getErrorMessage() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof aew)) {
            return false;
        }
        aew aewVar = (aew) obj;
        if (this.e == aewVar.e && this.f193a == null) {
            if (aewVar.f193a == null) {
                return true;
            }
        } else if (this.f193a.equals(aewVar.f193a) && TextUtils.equals(this.b, aewVar.b)) {
            return true;
        }
        return false;
    }

    public aew(int i, PendingIntent pendingIntent, String str) {
        this.e = i;
        this.f193a = pendingIntent;
        this.b = str;
    }

    public aew(int i, PendingIntent pendingIntent) {
        this(i, pendingIntent, ApiCode.getStatusCodeString(i));
    }

    public aew(int i) {
        this(i, null);
    }
}
