package com.huawei.health.health.utils.functionsetcard;

import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import defpackage.jdr;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class HandleCacheDataRunnable {
    protected final String mCacheTag;
    protected final String mVersion;

    public abstract void handleCacheData(HiHealthData hiHealthData, boolean z);

    public HandleCacheDataRunnable(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("cacheTag is empty");
        }
        this.mCacheTag = str;
        this.mVersion = str2;
    }

    private HiHealthData readCacheData() {
        long currentTimeMillis = System.currentTimeMillis();
        String readHealthData = SharedPreferenceUtil.getInstance(BaseApplication.getContext()).readHealthData(this.mCacheTag);
        if (TextUtils.isEmpty(readHealthData)) {
            LogUtil.a(this.mCacheTag, "readCacheData, return null");
            return null;
        }
        HiHealthData hiHealthData = (HiHealthData) jdr.bFW_(readHealthData, HiHealthData.CREATOR, this.mVersion);
        LogUtil.a(this.mCacheTag, "readCacheData success. times=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", length=", Integer.valueOf(readHealthData.length()), ", ver=", this.mVersion);
        return hiHealthData;
    }

    public void writeCacheData(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            SharedPreferenceUtil.getInstance(BaseApplication.getContext()).writeHealthData(this.mCacheTag, null);
            LogUtil.a(this.mCacheTag, "writeCacheData delete");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String bFT_ = jdr.bFT_(hiHealthData, this.mVersion);
        if (bFT_ != null) {
            SharedPreferenceUtil.getInstance(BaseApplication.getContext()).writeHealthData(this.mCacheTag, bFT_);
            LogUtil.a(this.mCacheTag, "writeCacheData save times=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", length=", Integer.valueOf(bFT_.length()), ", ver=", this.mVersion);
        }
    }

    protected String marshallToString(Parcelable parcelable) {
        String bFT_ = jdr.bFT_(parcelable, null);
        LogUtil.a(this.mCacheTag, "marshallToString, length=", Integer.valueOf(bFT_.length()));
        return bFT_;
    }

    protected <T> T unmarshallFromString(String str, Parcelable.Creator<T> creator) {
        T t = (T) jdr.bFW_(str, creator, null);
        String str2 = this.mCacheTag;
        Object[] objArr = new Object[2];
        objArr[0] = "unmarshallFromString, length=";
        objArr[1] = Integer.valueOf(str != null ? str.length() : 0);
        LogUtil.a(str2, objArr);
        return t;
    }

    protected String marshallListToString(List list) {
        String b = jdr.b(list, null);
        String str = this.mCacheTag;
        Object[] objArr = new Object[4];
        objArr[0] = "marshallListToString, list size=";
        objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
        objArr[2] = ", dataLen=";
        objArr[3] = Integer.valueOf(b.length());
        LogUtil.a(str, objArr);
        return b;
    }

    protected void unmarshallListFromString(String str, List list) {
        jdr.d(str, getClass().getClassLoader(), null, list);
        String str2 = this.mCacheTag;
        Object[] objArr = new Object[4];
        objArr[0] = "unmarshallListFromString, list size=";
        objArr[1] = Integer.valueOf(list.size());
        objArr[2] = ", dataLen=";
        objArr[3] = Integer.valueOf(str != null ? str.length() : 0);
        LogUtil.a(str2, objArr);
    }
}
