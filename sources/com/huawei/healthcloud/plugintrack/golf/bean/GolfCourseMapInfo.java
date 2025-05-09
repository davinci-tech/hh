package com.huawei.healthcloud.plugintrack.golf.bean;

import com.huawei.healthcloud.plugintrack.golf.Utils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.io.File;
import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
public class GolfCourseMapInfo implements GolfBeanToBytes {
    private static final int DEFAULT_INT_LENGTH = 4;
    private static final int DEFAULT_STRING_LENGTH = 80;
    private int aesHeaderLength;
    private int ivLength;
    private String mAlgorithm;
    private int mCourseId;
    private String mKey;
    private File mMapFile;
    private int mVersion;

    public int getCourseId() {
        return this.mCourseId;
    }

    public void setCourseId(int i) {
        this.mCourseId = i;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public void setVersion(int i) {
        this.mVersion = i;
    }

    public File getMapFile() {
        return this.mMapFile;
    }

    public void setMapFile(File file) {
        this.mMapFile = file;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public String getmKey() {
        return this.mKey;
    }

    public void setAlgorithm(String str) {
        this.mAlgorithm = str;
    }

    public String getmAlgorithm() {
        return this.mAlgorithm;
    }

    public int getIvLength() {
        return this.ivLength;
    }

    public void setIvLength(int i) {
        this.ivLength = i;
    }

    public int getAesHeaderLength() {
        return this.aesHeaderLength;
    }

    public void setAesHeaderLength(int i) {
        this.aesHeaderLength = i;
    }

    public String toString() {
        return "GolfCourseMapInfo{courseId=" + this.mCourseId + ", version=" + this.mVersion + ", mapFile=" + this.mMapFile.getName() + ", key =" + this.mKey + ", algorithm = " + this.mAlgorithm + ", ivLength = " + getIvLength() + ", aesHeaderLen = " + getAesHeaderLength() + ", mapFile size=" + this.mMapFile.length() + '}';
    }

    @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfBeanToBytes
    public byte[] toBytes() {
        byte[] bArr = new byte[8];
        System.arraycopy(Utils.int2Bytes(this.mCourseId), 0, bArr, 0, 4);
        System.arraycopy(Utils.int2Bytes(this.mVersion), 0, bArr, 4, 4);
        return bArr;
    }

    public byte[] keyInfoToBytes() {
        byte[] bArr = new byte[168];
        System.arraycopy(Utils.int2Bytes(this.ivLength), 0, bArr, 0, 4);
        System.arraycopy(Utils.int2Bytes(this.aesHeaderLength), 0, bArr, 4, 4);
        String str = this.mKey;
        if (str == null) {
            str = "";
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(bytes, 0, bArr, 8, Math.min(bytes.length, 80));
        String str2 = this.mAlgorithm;
        byte[] bytes2 = (str2 != null ? str2 : "").getBytes(StandardCharsets.UTF_8);
        System.arraycopy(bytes2, 0, bArr, 88, Math.min(bytes2.length, 80));
        return bArr;
    }

    public void deleteMapFile(String str) {
        if (getMapFile() == null || !getMapFile().exists()) {
            return;
        }
        boolean delete = getMapFile().delete();
        Object[] objArr = new Object[2];
        objArr[0] = "mapFile delete ";
        objArr[1] = delete ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
        LogUtil.a(str, objArr);
    }
}
