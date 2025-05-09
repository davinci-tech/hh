package com.huawei.watchface.mvp.model.datatype;

import com.huawei.watchface.utils.callback.IBaseCommonCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IFileTransferStateCallback;
import com.huawei.watchface.utils.callback.IPhotoFileCallback;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public class CommonFileInfo {
    private ByteBuffer mByteAll;
    private ByteBuffer mByteUnit;
    private int mFileId;
    private int mFileLength;
    private int mFileOffset;
    private String mFilePath;
    private int mFilePsnMax;
    private int mFilePsnTemp;
    private int mFileSize;
    private int mFileType;
    private IBaseCommonCallback mKitCallback;
    private String mFileName = "";
    private boolean mIsNeddVerify = true;
    private String mHashValue = "";
    private long mUriId = -1;
    private int mTotalIndex = 0;
    private int mStartTime = 0;
    private int mEndTime = 0;
    private IBaseResponseCallback mCallback = null;
    private IFileTransferStateCallback mFileCallback = null;
    private IPhotoFileCallback mFileRequestCallBack = null;

    public IBaseResponseCallback getCallback() {
        return this.mCallback;
    }

    public void setCallback(IBaseResponseCallback iBaseResponseCallback) {
        this.mCallback = iBaseResponseCallback;
    }

    public int getFileSize() {
        return this.mFileSize;
    }

    public void setFileSize(int i) {
        this.mFileSize = i;
    }

    public long getUriId() {
        return this.mUriId;
    }

    public void setUriId(long j) {
        this.mUriId = j;
    }

    public IFileTransferStateCallback getFileCallBack() {
        return this.mFileCallback;
    }

    public void setFileCallBack(IFileTransferStateCallback iFileTransferStateCallback) {
        this.mFileCallback = iFileTransferStateCallback;
    }

    public IPhotoFileCallback getFileRequestCallBack() {
        return this.mFileRequestCallBack;
    }

    public void setFileRequestCallBack(IPhotoFileCallback iPhotoFileCallback) {
        this.mFileRequestCallBack = iPhotoFileCallback;
    }

    public int getFileId() {
        return this.mFileId;
    }

    public void setFileId(int i) {
        this.mFileId = i;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public int getFileType() {
        return this.mFileType;
    }

    public void setFileType(int i) {
        this.mFileType = i;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public boolean isNeedVerify() {
        return this.mIsNeddVerify;
    }

    public void setNeedVerify(boolean z) {
        this.mIsNeddVerify = z;
    }

    public String getHashValue() {
        return this.mHashValue;
    }

    public void setHashValue(String str) {
        this.mHashValue = str;
    }

    public int getFileOffset() {
        return this.mFileOffset;
    }

    public void setFileOffset(int i) {
        this.mFileOffset = i;
    }

    public int getFileLength() {
        return this.mFileLength;
    }

    public void setFileLength(int i) {
        this.mFileLength = i;
    }

    public int getFilePsn() {
        return this.mFilePsnTemp;
    }

    public void setFilePsn(int i) {
        this.mFilePsnTemp = i;
    }

    public int getFilePsnMax() {
        return this.mFilePsnMax;
    }

    public void setFilePsnMax(int i) {
        this.mFilePsnMax = i;
    }

    public ByteBuffer getByteAll() {
        return this.mByteAll;
    }

    public void setByteAll(ByteBuffer byteBuffer) {
        this.mByteAll = byteBuffer;
    }

    public ByteBuffer getByteUnit() {
        return this.mByteUnit;
    }

    public void setByteUnit(ByteBuffer byteBuffer) {
        this.mByteUnit = byteBuffer;
    }

    public int getTotalIndex() {
        return this.mTotalIndex;
    }

    public void setTotalIndex(int i) {
        this.mTotalIndex = i;
    }

    public int getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(int i) {
        this.mStartTime = i;
    }

    public int getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(int i) {
        this.mEndTime = i;
    }

    public IBaseCommonCallback getKitCallback() {
        return this.mKitCallback;
    }

    public void setKitCallback(IBaseCommonCallback iBaseCommonCallback) {
        this.mKitCallback = iBaseCommonCallback;
    }
}
