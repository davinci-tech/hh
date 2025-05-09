package com.huawei.hms.health;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.hihealth.HiHealthKitClient;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.hihealth.activity.HealthKitMainActivity;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.options.DataTypeAddOptions;
import com.huawei.hms.hihealth.result.HealthKitAuthResult;
import com.huawei.hms.support.api.client.Status;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class aacp implements com.huawei.hms.hihealth.aabo {
    private static volatile aacp aaba;
    private HiHealthKitClient aab = HiHealthKitClient.getInstance();

    static /* synthetic */ boolean aab(aacp aacpVar, String str) {
        String packageName = aacpVar.aab.getContext().getPackageName();
        String str2 = packageName + ".";
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(str) || str.length() <= str2.length()) {
            return false;
        }
        return str2.equals(str.substring(0, str2.length()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApiException aab(Exception exc) {
        if (exc == null || exc.getMessage() == null) {
            return new ApiException(new Status(HiHealthStatusCodes.API_EXCEPTION_ERROR));
        }
        String message = exc.getMessage();
        return (message == null || message.length() == 0 || !Pattern.matches("^[-\\+]?[\\d]*$", message)) ? new ApiException(new Status(HiHealthStatusCodes.API_EXCEPTION_ERROR, exc.getMessage())) : new ApiException(new Status(Integer.parseInt(exc.getMessage()), HiHealthStatusCodes.getStatusCodeMessage(Integer.parseInt(exc.getMessage()))));
    }

    public boolean aabf() {
        String str;
        try {
            if (aack.aabe().aabd() == null) {
                if (!HiHealthKitClient.getInstance().isConnected()) {
                    return false;
                }
                HiHealthKitClient.getInstance().bindServiceWithOutCheckEmui(5);
            }
            return aack.aabe().aabd().aab();
        } catch (IllegalStateException unused) {
            str = "openAuthFromCloud IllegalState Exception";
            aabz.aabc("SettingControllerImpl", str);
            return false;
        } catch (Exception unused2) {
            str = "openAuthFromCloud common exception";
            aabz.aabc("SettingControllerImpl", str);
            return false;
        }
    }

    public Task<Boolean> aabe() {
        return aacq.aaba(5, new aabf());
    }

    public Task<com.huawei.hms.hihealth.data.aabc> aabd() {
        return aacq.aaba(5, new aabh());
    }

    public Task<Boolean> aabc() {
        return aacq.aaba(5, new aabd());
    }

    public Task<String> aabb() {
        return aacq.aaba(5, new aabe());
    }

    public Task<DataType> aaba(String str) {
        return aacq.aab(5, new aaba(str));
    }

    public Task<Void> aaba() {
        return aacq.aab(5, new aabb());
    }

    public Boolean aab(String str) {
        String str2;
        try {
            if (aack.aabe().aabd() == null) {
                if (!HiHealthKitClient.getInstance().isConnected()) {
                    return false;
                }
                HiHealthKitClient.getInstance().bindServiceWithOutCheckEmui(5);
            }
            return Boolean.valueOf(aack.aabe().aabd().aabc(str));
        } catch (IllegalStateException unused) {
            str2 = "isAppInTrustList IllegalState Exception";
            aabz.aabc("SettingControllerImpl", str2);
            return false;
        } catch (Exception unused2) {
            str2 = "isAppInTrustList common exception";
            aabz.aabc("SettingControllerImpl", str2);
            return false;
        }
    }

    public HealthKitAuthResult aab(Intent intent) {
        String str;
        if (intent != null) {
            try {
                return new HealthKitAuthResult().fromJson(intent.getStringExtra("HEALTHKIT_AUTH_RESULT"));
            } catch (JSONException unused) {
                str = "JSONException";
                aabz.aab("SettingControllerImpl", str);
                return null;
            } catch (Throwable unused2) {
                str = "Exception";
                aabz.aab("SettingControllerImpl", str);
                return null;
            }
        }
        return null;
    }

    public Task<Boolean> aab(boolean z) {
        return aacq.aaba(5, new aabg(z));
    }

    public Task<DataType> aab(DataTypeAddOptions dataTypeAddOptions) {
        return aacq.aab(5, new aab(dataTypeAddOptions));
    }

    public Task<Void> aab() {
        return aacq.aaba(5, new aabc());
    }

    public Intent aab(String[] strArr, boolean z) {
        Intent intent = new Intent(this.aab.getContext(), (Class<?>) HealthKitMainActivity.class);
        intent.putExtra("scopes", strArr);
        intent.putExtra("enableHealthAuth", z);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApiException aabj() {
        return new ApiException(new Status(HiHealthStatusCodes.MISMATCH_DATA_TYPE_ERROR, "DataType's name does not match package name."));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApiException aabi() {
        return new ApiException(new Status(HiHealthStatusCodes.NOT_EXIST_DATA_TYPE_ERROR, ""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApiException aabh() {
        return new ApiException(new Status(50001, ""));
    }

    public static aacp aabg() {
        if (aaba == null) {
            synchronized (aacp.class) {
                if (aaba == null) {
                    aaba = new aacp();
                }
            }
        }
        return aaba;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aabb(String str) {
        if (!TextUtils.isEmpty(str) && str.length() > 11) {
            return "com.huawei.".equals(str.substring(0, 11));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ApiException aab(SecurityException securityException) {
        try {
            int parseInt = Integer.parseInt(securityException.getMessage());
            return new ApiException(new Status(parseInt, HiHealthStatusCodes.getStatusCodeMessage(parseInt)));
        } catch (NumberFormatException unused) {
            aabz.aab("SettingControllerImpl", "parse statusCode Error");
            return new ApiException(new Status(HiHealthStatusCodes.API_EXCEPTION_ERROR, HiHealthStatusCodes.getStatusCodeMessage(HiHealthStatusCodes.API_EXCEPTION_ERROR)));
        }
    }

    class aab implements Callable<DataType> {
        final /* synthetic */ DataTypeAddOptions aab;

        @Override // java.util.concurrent.Callable
        public DataType call() throws Exception {
            if (!aacp.aab(aacp.this, this.aab.getName())) {
                aabz.aabc("SettingControllerImpl", "addDataType name is illegal");
                throw aacp.this.aabj();
            }
            StringBuilder aab = com.huawei.hms.health.aab.aab("addDataType name:");
            aab.append(this.aab.getName());
            aab.append(" is available");
            aab.toString();
            try {
                DataType aab2 = aack.aabe().aabd().aab(this.aab);
                if (aab2 != null) {
                    return aab2;
                }
                if (aacp.aab(aacp.this, this.aab.getName())) {
                    throw aacp.this.aabh();
                }
                throw aacp.this.aabj();
            } catch (SecurityException e) {
                aabz.aabc("SettingControllerImpl", "addDataType security exception");
                throw aacp.this.aab(e);
            } catch (Exception e2) {
                aabz.aabc("SettingControllerImpl", "addDataType common exception");
                throw aacp.this.aab(e2);
            }
        }

        aab(DataTypeAddOptions dataTypeAddOptions) {
            this.aab = dataTypeAddOptions;
        }
    }

    class aaba implements Callable<DataType> {
        final /* synthetic */ String aab;

        @Override // java.util.concurrent.Callable
        public DataType call() throws Exception {
            if (!aacp.this.aabb(this.aab) && !aacp.aab(aacp.this, this.aab)) {
                aabz.aabc("SettingControllerImpl", "readDataType name is illegal");
                throw aacp.this.aabj();
            }
            StringBuilder aab = com.huawei.hms.health.aab.aab("readDataType name:");
            aab.append(this.aab);
            aab.append(" is available");
            aab.toString();
            try {
                DataType aaba = aack.aabe().aabd().aaba(this.aab);
                if (aaba != null) {
                    return aaba;
                }
                if (aacp.aab(aacp.this, this.aab)) {
                    throw aacp.this.aabi();
                }
                throw aacp.this.aabj();
            } catch (SecurityException e) {
                aabz.aabc("SettingControllerImpl", "readDataType security exception");
                throw aacp.this.aab(e);
            } catch (Exception e2) {
                aabz.aabc("SettingControllerImpl", "readDataType common exception");
                throw aacp.this.aab(e2);
            }
        }

        aaba(String str) {
            this.aab = str;
        }
    }

    class aabb implements Callable<Void> {
        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            try {
                aack.aabe().aabd().aaba();
                return null;
            } catch (SecurityException e) {
                aabz.aabc("SettingControllerImpl", "disableHiHealth security exception");
                throw aacp.this.aab(e);
            } catch (Exception e2) {
                aabz.aabc("SettingControllerImpl", "disableHiHealth common exception");
                throw aacp.this.aab(e2);
            }
        }

        aabb() {
        }
    }

    class aabc implements Callable<Void> {
        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            try {
                aack.aabe().aabd().aabd();
                return null;
            } catch (SecurityException e) {
                aabz.aabc("SettingControllerImpl", "checkAuthorisation security exception");
                throw aacp.this.aab((Exception) e);
            } catch (Exception e2) {
                aabz.aabc("SettingControllerImpl", "checkAuthorisation common exception");
                throw aacp.this.aab(e2);
            }
        }

        aabc() {
        }
    }

    class aabd implements Callable<Boolean> {
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            try {
                return Boolean.valueOf(aack.aabe().aabd().aabf());
            } catch (Exception e) {
                aabz.aabc("SettingControllerImpl", "getAuthorisation common exception");
                throw aacp.this.aab(e);
            }
        }

        aabd() {
        }
    }

    class aabe implements Callable<String> {
        @Override // java.util.concurrent.Callable
        public String call() throws Exception {
            try {
                return aack.aabe().aabd().aabc();
            } catch (Exception e) {
                aabz.aabc("SettingControllerImpl", "getAuthUrl common exception");
                throw aacp.this.aab(e);
            }
        }

        aabe() {
        }
    }

    class aabf implements Callable<Boolean> {
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            try {
                return Boolean.valueOf(aack.aabe().aabd().aabg());
            } catch (Exception e) {
                aabz.aabc("SettingControllerImpl", "getLinkHealthKitStatus common exception");
                throw aacp.this.aab(e);
            }
        }

        aabf() {
        }
    }

    class aabg implements Callable<Boolean> {
        final /* synthetic */ boolean aab;

        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            try {
                return Boolean.valueOf(aack.aabe().aabd().aaba(this.aab));
            } catch (Exception e) {
                aabz.aabc("SettingControllerImpl", "setLinkHealthKitStatus common exception");
                throw aacp.this.aab(e);
            }
        }

        aabg(boolean z) {
            this.aab = z;
        }
    }

    class aabh implements Callable<com.huawei.hms.hihealth.data.aabc> {
        @Override // java.util.concurrent.Callable
        public com.huawei.hms.hihealth.data.aabc call() throws Exception {
            try {
                return aack.aabe().aabd().aabh();
            } catch (Exception e) {
                aabz.aabc("SettingControllerImpl", "getInterfacePolicy common exception");
                throw aacp.this.aab(e);
            }
        }

        aabh() {
        }
    }
}
