package com.huawei.health.h5pro.core;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import com.huawei.health.h5pro.jsbridge.H5ProJsExtModule;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class H5ProLaunchOption implements Parcelable {
    public static final Parcelable.Creator<H5ProLaunchOption> CREATOR = new Parcelable.Creator<H5ProLaunchOption>() { // from class: com.huawei.health.h5pro.core.H5ProLaunchOption.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProLaunchOption[] newArray(int i) {
            return new H5ProLaunchOption[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProLaunchOption createFromParcel(Parcel parcel) {
            return new H5ProLaunchOption(parcel);
        }
    };
    public static final int DEFAULT_BACKGROUND_COLOR = 0;
    public static final int DEFAULT_FORCE_DARK_MODE = -1;
    public static final String KEY_H5PRO_BUNDLES = "KEY_H5PRO_BUNDLES";
    public static final String KEY_H5PRO_GLOBAL_BI_PARAMS = "KEY_H5PRO_GLOBAL_BI_PARAMS";
    public static final String KEY_H5PRO_JS_EXT_MODULES = "KEY_H5PRO_JS_EXT_MODULES";
    public static final String KEY_JS_MODULE_CLASS = "KEY_JS_MODULE_CLASS";
    public static final int PREFER_WEB_THEME_OVER_USER_AGENT_DARKENING = 2;
    public static final int START_MODE_ACTIVITY_CLEAR_TOP = 1;
    public static final int START_MODE_DEFAULT = 0;
    public static final int START_MODE_H5_CLEAR_TOP = 2;
    public static final int START_MODE_H5_SINGLE_TOP = 3;
    public static final String TAG = "H5ProLaunchOption";
    public static final int THEME_LIGHT_ONLY = -1;
    public static final int USER_AGENT_DARKENING_ONLY = 0;
    public static final int WEB_THEME_DARKENING_ONLY = 1;
    public String anim;
    public int backgroundColor;
    public ArrayMap<String, String> customizeArgMap;
    public int forceDarkMode;
    public HashMap<String, String> globalBiParams;
    public HashMap<String, H5ProBundle> h5ProBundles;
    public boolean isDisableAllJsExtModules;
    public boolean isDisableImageProxy;
    public boolean isEnableImageCache;
    public boolean isEnableOnDestroyCallback;
    public boolean isEnableOnPauseCallback;
    public boolean isEnableOnResumeCallback;
    public boolean isEnableSelfProtection;
    public boolean isEnableSlowWholeDocumentDraw;
    public boolean isForResult;
    public boolean isHideBottomVirtualKeys;
    public boolean isImmerse;
    public boolean isNeedSoftInputAdapter;
    public boolean isShowStatusBar;
    public boolean isStartAtBottomOfStatusBar;
    public boolean isStatusBarTextBlack;
    public ArrayList<H5ProJsExtModule> jsExtModules;
    public HashMap<String, Class<? extends JsModuleBase>> jsModuleClassMap;
    public String path;
    public int requestCode;
    public int screenOrientation;
    public int startFlag;
    public int startMode;

    @Target({ElementType.PARAMETER, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ScreenOrientation {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static class Builder {
        public HashMap<String, String> c;
        public HashMap<String, H5ProBundle> g;
        public ArrayList<H5ProJsExtModule> u;
        public String x;
        public ArrayMap<String, String> e = new ArrayMap<>();
        public boolean h = false;
        public boolean s = false;
        public boolean k = false;

        /* renamed from: a, reason: collision with root package name */
        public int f2369a = 0;
        public boolean t = false;
        public boolean y = false;
        public int b = -1;
        public int ab = 268435456;
        public int ad = -1;
        public boolean q = false;
        public String d = "";
        public boolean n = true;
        public boolean j = true;
        public boolean o = true;
        public boolean r = false;
        public boolean p = false;
        public boolean l = false;
        public boolean m = false;
        public int w = -1;
        public boolean f = false;
        public boolean i = false;
        public int ac = 0;
        public HashMap<String, Class<? extends JsModuleBase>> v = new HashMap<>();

        public Builder startAtBottomOfStatusBar() {
            this.r = true;
            return this;
        }

        public Builder showStatusBar() {
            this.t = true;
            return this;
        }

        public Builder setStatusBarTextBlack(boolean z) {
            this.y = z;
            return this;
        }

        public Builder setStartSource(String str) {
            return addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, str);
        }

        public Builder setStartMode(int i) {
            this.ac = i;
            return this;
        }

        public Builder setScreenOrientation(int i) {
            this.ad = i;
            return this;
        }

        public Builder setRequestCode(int i) {
            this.w = i;
            return this;
        }

        public Builder setNeedSoftInputAdapter() {
            this.q = true;
            return this;
        }

        public Builder setIsForResult(boolean z) {
            this.m = z;
            return this;
        }

        public Builder setImmerse() {
            this.s = true;
            return this;
        }

        public Builder setFromPageTitle(String str) {
            return addGlobalBiParam("fromPageTitle", str);
        }

        public Builder setForceDarkMode(int i) {
            this.b = i;
            return this;
        }

        public Builder setBackgroundColor(int i) {
            this.f2369a = i;
            return this;
        }

        public Builder setAnim(String str) {
            this.d = str;
            return this;
        }

        public Builder setActivityStartFlag(int i) {
            this.ab = i;
            return this;
        }

        public Builder hideBottomVirtualKeys() {
            this.p = true;
            return this;
        }

        public Builder enableSlowWholeDocumentDraw() {
            this.k = true;
            return this;
        }

        public Builder enableSelfProtection() {
            this.l = true;
            return this;
        }

        public Builder enableOnResumeCallback() {
            this.n = true;
            return this;
        }

        public Builder enableOnPauseCallback() {
            this.o = true;
            return this;
        }

        public Builder enableOnDestroyCallback() {
            this.j = true;
            return this;
        }

        public Builder enableImageCache() {
            this.f = true;
            return this;
        }

        public Builder disableImageProxy() {
            this.i = true;
            return this;
        }

        public Builder disableAllJsExtModules() {
            this.h = true;
            return this;
        }

        public Builder copy(H5ProLaunchOption h5ProLaunchOption) {
            this.v = h5ProLaunchOption.jsModuleClassMap;
            this.e = h5ProLaunchOption.customizeArgMap;
            this.x = h5ProLaunchOption.path;
            this.s = h5ProLaunchOption.isImmerse;
            this.k = h5ProLaunchOption.isEnableSlowWholeDocumentDraw;
            this.f2369a = h5ProLaunchOption.backgroundColor;
            this.t = h5ProLaunchOption.isShowStatusBar;
            this.y = h5ProLaunchOption.isStatusBarTextBlack;
            this.b = h5ProLaunchOption.forceDarkMode;
            this.ab = h5ProLaunchOption.startFlag;
            this.ad = h5ProLaunchOption.screenOrientation;
            this.q = h5ProLaunchOption.isNeedSoftInputAdapter;
            this.d = h5ProLaunchOption.anim;
            this.c = h5ProLaunchOption.globalBiParams;
            this.n = h5ProLaunchOption.isEnableOnResumeCallback;
            this.j = h5ProLaunchOption.isEnableOnDestroyCallback;
            this.o = h5ProLaunchOption.isEnableOnPauseCallback;
            this.r = h5ProLaunchOption.isStartAtBottomOfStatusBar;
            this.p = h5ProLaunchOption.isHideBottomVirtualKeys;
            this.l = h5ProLaunchOption.isEnableSelfProtection;
            this.g = h5ProLaunchOption.h5ProBundles;
            this.h = h5ProLaunchOption.isDisableAllJsExtModules;
            this.u = h5ProLaunchOption.jsExtModules;
            this.m = h5ProLaunchOption.isForResult;
            this.w = h5ProLaunchOption.requestCode;
            this.f = h5ProLaunchOption.isEnableImageCache;
            this.i = h5ProLaunchOption.isDisableImageProxy;
            this.ac = h5ProLaunchOption.startMode;
            return this;
        }

        public H5ProLaunchOption build() {
            return new H5ProLaunchOption(this);
        }

        public Builder addPath(String str) {
            if (this.x != null) {
                throw new RuntimeException("path already set");
            }
            this.x = str;
            return this;
        }

        public Builder addJsExtModule(H5ProJsExtModule h5ProJsExtModule) {
            if (this.u == null) {
                this.u = new ArrayList<>();
            }
            if (!this.u.contains(h5ProJsExtModule)) {
                this.u.add(h5ProJsExtModule);
            }
            return this;
        }

        public Builder addGlobalBiParam(String str, String str2) {
            if (this.c == null) {
                this.c = new HashMap<>();
            }
            this.c.put(str, str2);
            return this;
        }

        public Builder addCustomizeJsModule(String str, Class<? extends JsBaseModule> cls, H5ProBundle h5ProBundle) {
            if (this.v.get(str) != null) {
                LogUtil.w(H5ProLaunchOption.TAG, String.format(Locale.ENGLISH, "js module '%s' already exist, don't add it again.", str));
                return this;
            }
            this.v.put(str, cls);
            return c(str, h5ProBundle);
        }

        public Builder addCustomizeJsModule(String str, Class<? extends JsModuleBase> cls) {
            if (this.v.get(str) != null) {
                LogUtil.w(H5ProLaunchOption.TAG, String.format(Locale.ROOT, "js module '%s' already exist, don't add it again.", str));
                return this;
            }
            this.v.put(str, cls);
            return this;
        }

        public Builder addCustomizeArg(String str, String str2) {
            this.e.put(str, str2);
            return this;
        }

        public Builder addBundle(H5ProBundle h5ProBundle) {
            return h5ProBundle == null ? this : c(h5ProBundle.getModuleName(), h5ProBundle);
        }

        private Builder c(String str, H5ProBundle h5ProBundle) {
            if (h5ProBundle == null) {
                LogUtil.w(H5ProLaunchOption.TAG, "h5ProBundle is null");
                return this;
            }
            if (this.g == null) {
                this.g = new HashMap<>(1);
            }
            H5ProBundle h5ProBundle2 = this.g.get(str);
            if (h5ProBundle2 == null) {
                h5ProBundle2 = new H5ProBundle(str);
            }
            h5ProBundle2.putAll(h5ProBundle);
            this.g.put(str, h5ProBundle2);
            return this;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.path);
        parcel.writeByte(this.isImmerse ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowStatusBar ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isEnableSlowWholeDocumentDraw ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isStatusBarTextBlack ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.forceDarkMode);
        parcel.writeInt(this.backgroundColor);
        parcel.writeInt(this.startFlag);
        parcel.writeInt(this.startFlag);
        parcel.writeInt(this.screenOrientation);
        parcel.writeByte(this.isNeedSoftInputAdapter ? (byte) 1 : (byte) 0);
        parcel.writeString(this.anim);
        parcel.writeByte(this.isEnableOnResumeCallback ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isEnableOnDestroyCallback ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isEnableOnPauseCallback ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isStartAtBottomOfStatusBar ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isHideBottomVirtualKeys ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isEnableSelfProtection ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isForResult ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.requestCode);
        parcel.writeByte(this.isEnableImageCache ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isDisableImageProxy ? (byte) 1 : (byte) 0);
        parcel.writeMap(this.customizeArgMap);
        parcel.writeByte(this.isDisableAllJsExtModules ? (byte) 1 : (byte) 0);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_JS_MODULE_CLASS, this.jsModuleClassMap);
        bundle.putSerializable(KEY_H5PRO_BUNDLES, this.h5ProBundles);
        bundle.putParcelableArrayList(KEY_H5PRO_JS_EXT_MODULES, this.jsExtModules);
        bundle.putSerializable(KEY_H5PRO_GLOBAL_BI_PARAMS, this.globalBiParams);
        parcel.writeBundle(bundle);
    }

    public boolean isStartAtBottomOfStatusBar() {
        return this.isStartAtBottomOfStatusBar;
    }

    public boolean isShowStatusBar() {
        return this.isShowStatusBar;
    }

    public boolean isNeedSoftInputAdapter() {
        return this.isNeedSoftInputAdapter;
    }

    public boolean isImmerse() {
        return this.isImmerse;
    }

    public boolean isHideBottomVirtualKeys() {
        return this.isHideBottomVirtualKeys;
    }

    public boolean isForResult() {
        return this.isForResult;
    }

    public boolean isEnableSlowWholeDocumentDraw() {
        return this.isEnableSlowWholeDocumentDraw;
    }

    public boolean isEnableSelfProtection() {
        return this.isEnableSelfProtection;
    }

    public boolean isEnableOnResumeCallback() {
        return this.isEnableOnResumeCallback;
    }

    public boolean isEnableOnPauseCallback() {
        return this.isEnableOnPauseCallback;
    }

    public boolean isEnableOnDestroyCallback() {
        return this.isEnableOnDestroyCallback;
    }

    public boolean isEnableImageCache() {
        return this.isEnableImageCache;
    }

    public boolean isDisableImageProxy() {
        return this.isDisableImageProxy;
    }

    public boolean isDisableAllJsExtModules() {
        return this.isDisableAllJsExtModules;
    }

    public boolean getStatusBarTextColor() {
        return this.isStatusBarTextBlack;
    }

    public int getStartMode() {
        return this.startMode;
    }

    public int getScreenOrientation() {
        return this.screenOrientation;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public String getPath() {
        return this.path;
    }

    public ArrayList<H5ProJsExtModule> getJsExtModules() {
        return this.jsExtModules;
    }

    public HashMap<String, String> getGlobalBiParams() {
        return this.globalBiParams;
    }

    public int getForceDarkMode() {
        return this.forceDarkMode;
    }

    public Map<String, Class<? extends JsModuleBase>> getCustomizeJsModules() {
        HashMap<String, Class<? extends JsModuleBase>> hashMap = this.jsModuleClassMap;
        return hashMap == null ? Collections.emptyMap() : hashMap;
    }

    public Map<String, H5ProBundle> getBundles() {
        HashMap<String, H5ProBundle> hashMap = this.h5ProBundles;
        return hashMap == null ? Collections.emptyMap() : Collections.unmodifiableMap(hashMap);
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public String getAnim() {
        return this.anim;
    }

    public String getAllCustomizeArg() {
        return new JSONObject(this.customizeArgMap).toString();
    }

    public int getActivityStartFlag() {
        return this.startFlag;
    }

    @Deprecated
    public void addCustomizeArg(String str, String str2) {
        this.customizeArgMap.put(str, str2);
    }

    public H5ProLaunchOption(Builder builder) {
        this.customizeArgMap = new ArrayMap<>();
        this.isDisableAllJsExtModules = false;
        this.isImmerse = false;
        this.isShowStatusBar = false;
        this.isEnableSlowWholeDocumentDraw = false;
        this.isStatusBarTextBlack = false;
        this.forceDarkMode = -1;
        this.backgroundColor = 0;
        this.startFlag = 268435456;
        this.screenOrientation = -1;
        this.isNeedSoftInputAdapter = false;
        this.anim = "";
        this.isEnableOnResumeCallback = false;
        this.isEnableOnDestroyCallback = false;
        this.isEnableOnPauseCallback = false;
        this.isStartAtBottomOfStatusBar = false;
        this.isHideBottomVirtualKeys = false;
        this.isEnableSelfProtection = false;
        this.isForResult = false;
        this.requestCode = -1;
        this.isEnableImageCache = false;
        this.isDisableImageProxy = false;
        this.startMode = 0;
        this.jsModuleClassMap = builder.v;
        this.customizeArgMap = builder.e;
        this.path = builder.x;
        this.isImmerse = builder.s;
        this.isShowStatusBar = builder.t;
        this.isStatusBarTextBlack = builder.y;
        this.forceDarkMode = builder.b;
        this.isEnableSlowWholeDocumentDraw = builder.k;
        this.backgroundColor = builder.f2369a;
        this.startFlag = builder.ab;
        this.screenOrientation = builder.ad;
        this.isNeedSoftInputAdapter = builder.q;
        this.anim = builder.d;
        this.globalBiParams = builder.c;
        this.isEnableOnResumeCallback = builder.n;
        this.isEnableOnDestroyCallback = builder.j;
        this.isEnableOnPauseCallback = builder.o;
        this.isStartAtBottomOfStatusBar = builder.r;
        this.isHideBottomVirtualKeys = builder.p;
        this.isEnableSelfProtection = builder.l;
        this.h5ProBundles = builder.g;
        this.isDisableAllJsExtModules = builder.h;
        this.jsExtModules = builder.u;
        this.isForResult = builder.m;
        this.requestCode = builder.w;
        this.isEnableImageCache = builder.f;
        this.isDisableImageProxy = builder.i;
        this.startMode = builder.ac;
    }

    public H5ProLaunchOption(Parcel parcel) {
        this.customizeArgMap = new ArrayMap<>();
        this.isDisableAllJsExtModules = false;
        this.isImmerse = false;
        this.isShowStatusBar = false;
        this.isEnableSlowWholeDocumentDraw = false;
        this.isStatusBarTextBlack = false;
        this.forceDarkMode = -1;
        this.backgroundColor = 0;
        this.startFlag = 268435456;
        this.screenOrientation = -1;
        this.isNeedSoftInputAdapter = false;
        this.anim = "";
        this.isEnableOnResumeCallback = false;
        this.isEnableOnDestroyCallback = false;
        this.isEnableOnPauseCallback = false;
        this.isStartAtBottomOfStatusBar = false;
        this.isHideBottomVirtualKeys = false;
        this.isEnableSelfProtection = false;
        this.isForResult = false;
        this.requestCode = -1;
        this.isEnableImageCache = false;
        this.isDisableImageProxy = false;
        this.startMode = 0;
        this.path = parcel.readString();
        this.isImmerse = parcel.readByte() != 0;
        this.isShowStatusBar = parcel.readByte() != 0;
        this.isEnableSlowWholeDocumentDraw = parcel.readByte() != 0;
        this.isStatusBarTextBlack = parcel.readByte() != 0;
        this.forceDarkMode = parcel.readInt();
        this.backgroundColor = parcel.readInt();
        this.startFlag = parcel.readInt();
        this.startMode = parcel.readInt();
        this.screenOrientation = parcel.readInt();
        this.isNeedSoftInputAdapter = parcel.readByte() != 0;
        this.anim = parcel.readString();
        this.isEnableOnResumeCallback = parcel.readByte() != 0;
        this.isEnableOnDestroyCallback = parcel.readByte() != 0;
        this.isEnableOnPauseCallback = parcel.readByte() != 0;
        this.isStartAtBottomOfStatusBar = parcel.readByte() != 0;
        this.isHideBottomVirtualKeys = parcel.readByte() != 0;
        this.isEnableSelfProtection = parcel.readByte() != 0;
        this.isForResult = parcel.readByte() != 0;
        this.requestCode = parcel.readInt();
        this.isEnableImageCache = parcel.readByte() != 0;
        this.isDisableImageProxy = parcel.readByte() != 0;
        parcel.readMap(this.customizeArgMap, H5ProLaunchOption.class.getClassLoader());
        this.isDisableAllJsExtModules = parcel.readByte() != 0;
        Bundle readBundle = parcel.readBundle(H5ProLaunchOption.class.getClassLoader());
        Serializable serializable = readBundle.getSerializable(KEY_JS_MODULE_CLASS);
        if (serializable != null) {
            this.jsModuleClassMap = (HashMap) serializable;
        }
        Serializable serializable2 = readBundle.getSerializable(KEY_H5PRO_BUNDLES);
        if (serializable2 != null) {
            this.h5ProBundles = (HashMap) serializable2;
        }
        ArrayList<H5ProJsExtModule> parcelableArrayList = readBundle.getParcelableArrayList(KEY_H5PRO_JS_EXT_MODULES);
        if (parcelableArrayList != null) {
            this.jsExtModules = parcelableArrayList;
        }
        Serializable serializable3 = readBundle.getSerializable(KEY_H5PRO_GLOBAL_BI_PARAMS);
        if (serializable3 instanceof HashMap) {
            this.globalBiParams = (HashMap) serializable3;
        }
    }

    public H5ProLaunchOption() {
        this.customizeArgMap = new ArrayMap<>();
        this.isDisableAllJsExtModules = false;
        this.isImmerse = false;
        this.isShowStatusBar = false;
        this.isEnableSlowWholeDocumentDraw = false;
        this.isStatusBarTextBlack = false;
        this.forceDarkMode = -1;
        this.backgroundColor = 0;
        this.startFlag = 268435456;
        this.screenOrientation = -1;
        this.isNeedSoftInputAdapter = false;
        this.anim = "";
        this.isEnableOnResumeCallback = false;
        this.isEnableOnDestroyCallback = false;
        this.isEnableOnPauseCallback = false;
        this.isStartAtBottomOfStatusBar = false;
        this.isHideBottomVirtualKeys = false;
        this.isEnableSelfProtection = false;
        this.isForResult = false;
        this.requestCode = -1;
        this.isEnableImageCache = false;
        this.isDisableImageProxy = false;
        this.startMode = 0;
    }
}
