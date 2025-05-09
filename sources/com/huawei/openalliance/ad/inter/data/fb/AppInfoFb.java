package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import com.huawei.openalliance.ad.inter.data.fb.PermissionEntityFb;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class AppInfoFb extends Table {
    public ByteBuffer versionNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public ByteBuffer versionNameAsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public String versionName() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer versionCodeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public ByteBuffer versionCodeAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public String versionCode() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer uniqueIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 42, 1);
    }

    public ByteBuffer uniqueIdAsByteBuffer() {
        return __vector_as_bytebuffer(42, 1);
    }

    public String uniqueId() {
        int __offset = __offset(42);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int trafficReminder() {
        int __offset = __offset(48);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer sha256InByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 20, 1);
    }

    public ByteBuffer sha256AsByteBuffer() {
        return __vector_as_bytebuffer(20, 1);
    }

    public String sha256() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer safeDownloadUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public ByteBuffer safeDownloadUrlAsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public String safeDownloadUrl() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer reservedPkgNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 102, 1);
    }

    public ByteBuffer reservedPkgNameAsByteBuffer() {
        return __vector_as_bytebuffer(102, 1);
    }

    public String reservedPkgName() {
        int __offset = __offset(102);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer pureModeTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 96, 1);
    }

    public ByteBuffer pureModeTextAsByteBuffer() {
        return __vector_as_bytebuffer(96, 1);
    }

    public String pureModeText() {
        int __offset = __offset(96);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer privacyUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 108, 1);
    }

    public ByteBuffer privacyUrlAsByteBuffer() {
        return __vector_as_bytebuffer(108, 1);
    }

    public String privacyUrl() {
        int __offset = __offset(108);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer priorInstallWayInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 28, 1);
    }

    public ByteBuffer priorInstallWayAsByteBuffer() {
        return __vector_as_bytebuffer(28, 1);
    }

    public String priorInstallWay() {
        int __offset = __offset(28);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int popUpStyle() {
        int __offset = __offset(92);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer popUpAfterInstallTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 50, 1);
    }

    public ByteBuffer popUpAfterInstallTextAsByteBuffer() {
        return __vector_as_bytebuffer(50, 1);
    }

    public String popUpAfterInstallText() {
        int __offset = __offset(50);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int popUpAfterInstallNew() {
        int __offset = __offset(36);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int popNotify() {
        int __offset = __offset(60);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public PermissionEntityFb.Vector permissionsVector(PermissionEntityFb.Vector vector) {
        int __offset = __offset(26);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public PermissionEntityFb.Vector permissionsVector() {
        return permissionsVector(new PermissionEntityFb.Vector());
    }

    public int permissionsLength() {
        int __offset = __offset(26);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public PermissionEntityFb permissions(PermissionEntityFb permissionEntityFb, int i) {
        int __offset = __offset(26);
        if (__offset != 0) {
            return permissionEntityFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public PermissionEntityFb permissions(int i) {
        return permissions(new PermissionEntityFb(), i);
    }

    public ByteBuffer permissionUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 104, 1);
    }

    public ByteBuffer permissionUrlAsByteBuffer() {
        return __vector_as_bytebuffer(104, 1);
    }

    public String permissionUrl() {
        int __offset = __offset(104);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean permPromptForLanding() {
        int __offset = __offset(34);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean permPromptForCard() {
        int __offset = __offset(32);
        return __offset == 0 || this.bb.get(__offset + this.bb_pos) != 0;
    }

    public ByteBuffer packageNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer packageNameAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String packageName() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int noAlertTime() {
        int __offset = __offset(46);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer nextInstallWaysInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 80, 1);
    }

    public ByteBuffer nextInstallWaysAsByteBuffer() {
        return __vector_as_bytebuffer(80, 1);
    }

    public String nextInstallWays() {
        int __offset = __offset(80);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer intentUriInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 24, 1);
    }

    public ByteBuffer intentUriAsByteBuffer() {
        return __vector_as_bytebuffer(24, 1);
    }

    public String intentUri() {
        int __offset = __offset(24);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer intentPackageInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 58, 1);
    }

    public ByteBuffer intentPackageAsByteBuffer() {
        return __vector_as_bytebuffer(58, 1);
    }

    public String intentPackage() {
        int __offset = __offset(58);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer intentInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 56, 1);
    }

    public ByteBuffer intentAsByteBuffer() {
        return __vector_as_bytebuffer(56, 1);
    }

    public String intent() {
        int __offset = __offset(56);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer installPureModeTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 98, 1);
    }

    public ByteBuffer installPureModeTextAsByteBuffer() {
        return __vector_as_bytebuffer(98, 1);
    }

    public String installPureModeText() {
        int __offset = __offset(98);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer installPermiTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 94, 1);
    }

    public ByteBuffer installPermiTextAsByteBuffer() {
        return __vector_as_bytebuffer(94, 1);
    }

    public String installPermiText() {
        int __offset = __offset(94);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public InstallConfigFb installConfig(InstallConfigFb installConfigFb) {
        int __offset = __offset(30);
        if (__offset != 0) {
            return installConfigFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public InstallConfigFb installConfig() {
        return installConfig(new InstallConfigFb());
    }

    public int insActvNotifyCfg() {
        int __offset = __offset(68);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public ByteBuffer insActvNotifyBtnTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 66, 1);
    }

    public ByteBuffer insActvNotifyBtnTextAsByteBuffer() {
        return __vector_as_bytebuffer(66, 1);
    }

    public String insActvNotifyBtnText() {
        int __offset = __offset(66);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer iconUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 12, 1);
    }

    public ByteBuffer iconUrlAsByteBuffer() {
        return __vector_as_bytebuffer(12, 1);
    }

    public String iconUrl() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int hasPermissions() {
        int __offset = __offset(72);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer fullScrnNotifyTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 64, 1);
    }

    public ByteBuffer fullScrnNotifyTextAsByteBuffer() {
        return __vector_as_bytebuffer(64, 1);
    }

    public String fullScrnNotifyText() {
        int __offset = __offset(64);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int fullScrnNotify() {
        int __offset = __offset(62);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public long fileSize() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public ByteBuffer downloadUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 14, 1);
    }

    public ByteBuffer downloadUrlAsByteBuffer() {
        return __vector_as_bytebuffer(14, 1);
    }

    public String downloadUrl() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer dlBtnTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 52, 1);
    }

    public ByteBuffer dlBtnTextAsByteBuffer() {
        return __vector_as_bytebuffer(52, 1);
    }

    public String dlBtnText() {
        int __offset = __offset(52);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer developerNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 78, 1);
    }

    public ByteBuffer developerNameAsByteBuffer() {
        return __vector_as_bytebuffer(78, 1);
    }

    public String developerName() {
        int __offset = __offset(78);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer curInstallWayInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 82, 1);
    }

    public ByteBuffer curInstallWayAsByteBuffer() {
        return __vector_as_bytebuffer(82, 1);
    }

    public String curInstallWay() {
        int __offset = __offset(82);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer contiBtnInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 100, 1);
    }

    public ByteBuffer contiBtnAsByteBuffer() {
        return __vector_as_bytebuffer(100, 1);
    }

    public String contiBtn() {
        int __offset = __offset(100);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer contentInstallBtnActionInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 110, 1);
    }

    public ByteBuffer contentInstallBtnActionAsByteBuffer() {
        return __vector_as_bytebuffer(110, 1);
    }

    public String contentInstallBtnAction() {
        int __offset = __offset(110);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean checkSha256() {
        int __offset = __offset(22);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public int channelInfoSaveLimit() {
        int __offset = __offset(40);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return -2;
    }

    public ByteBuffer channelInfoInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 38, 1);
    }

    public ByteBuffer channelInfoAsByteBuffer() {
        return __vector_as_bytebuffer(38, 1);
    }

    public String channelInfo() {
        int __offset = __offset(38);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int autoOpenAfterInstall() {
        int __offset = __offset(88);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int appType() {
        int __offset = __offset(86);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public ByteBuffer appNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer appNameAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String appName() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer appLanguageInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 74, 1);
    }

    public ByteBuffer appLanguageAsByteBuffer() {
        return __vector_as_bytebuffer(74, 1);
    }

    public String appLanguage() {
        int __offset = __offset(74);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer appDetailsUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 106, 1);
    }

    public ByteBuffer appDetailsUrlAsByteBuffer() {
        return __vector_as_bytebuffer(106, 1);
    }

    public String appDetailsUrl() {
        int __offset = __offset(106);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer appDescInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 44, 1);
    }

    public ByteBuffer appDescAsByteBuffer() {
        return __vector_as_bytebuffer(44, 1);
    }

    public String appDesc() {
        int __offset = __offset(44);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer appCountryInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 76, 1);
    }

    public ByteBuffer appCountryAsByteBuffer() {
        return __vector_as_bytebuffer(76, 1);
    }

    public String appCountry() {
        int __offset = __offset(76);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean allowedNonWifiNetwork() {
        int __offset = __offset(70);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public long allAreaPopDelay() {
        int __offset = __offset(90);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public ByteBuffer afDlBtnTextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 54, 1);
    }

    public ByteBuffer afDlBtnTextAsByteBuffer() {
        return __vector_as_bytebuffer(54, 1);
    }

    public String afDlBtnText() {
        int __offset = __offset(54);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer actNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 84, 1);
    }

    public ByteBuffer actNameAsByteBuffer() {
        return __vector_as_bytebuffer(84, 1);
    }

    public String actName() {
        int __offset = __offset(84);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AppInfoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startPermissionsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startAppInfoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(54);
    }

    public static AppInfoFb getRootAsAppInfoFb(ByteBuffer byteBuffer, AppInfoFb appInfoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return appInfoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static AppInfoFb getRootAsAppInfoFb(ByteBuffer byteBuffer) {
        return getRootAsAppInfoFb(byteBuffer, new AppInfoFb());
    }

    public static int endAppInfoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createPermissionsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createAppInfoFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, boolean z, int i9, int i10, int i11, int i12, boolean z2, boolean z3, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, boolean z4, int i30, int i31, int i32, int i33, int i34, int i35, int i36, int i37, int i38, long j2, int i39, int i40, int i41, int i42, int i43, int i44, int i45, int i46, int i47, int i48) {
        flatBufferBuilder.startTable(54);
        addAllAreaPopDelay(flatBufferBuilder, j2);
        addFileSize(flatBufferBuilder, j);
        addContentInstallBtnAction(flatBufferBuilder, i48);
        addPrivacyUrl(flatBufferBuilder, i47);
        addAppDetailsUrl(flatBufferBuilder, i46);
        addPermissionUrl(flatBufferBuilder, i45);
        addReservedPkgName(flatBufferBuilder, i44);
        addContiBtn(flatBufferBuilder, i43);
        addInstallPureModeText(flatBufferBuilder, i42);
        addPureModeText(flatBufferBuilder, i41);
        addInstallPermiText(flatBufferBuilder, i40);
        addPopUpStyle(flatBufferBuilder, i39);
        addAutoOpenAfterInstall(flatBufferBuilder, i38);
        addAppType(flatBufferBuilder, i37);
        addActName(flatBufferBuilder, i36);
        addCurInstallWay(flatBufferBuilder, i35);
        addNextInstallWays(flatBufferBuilder, i34);
        addDeveloperName(flatBufferBuilder, i33);
        addAppCountry(flatBufferBuilder, i32);
        addAppLanguage(flatBufferBuilder, i31);
        addHasPermissions(flatBufferBuilder, i30);
        addInsActvNotifyCfg(flatBufferBuilder, i29);
        addInsActvNotifyBtnText(flatBufferBuilder, i28);
        addFullScrnNotifyText(flatBufferBuilder, i27);
        addFullScrnNotify(flatBufferBuilder, i26);
        addPopNotify(flatBufferBuilder, i25);
        addIntentPackage(flatBufferBuilder, i24);
        addIntent(flatBufferBuilder, i23);
        addAfDlBtnText(flatBufferBuilder, i22);
        addDlBtnText(flatBufferBuilder, i21);
        addPopUpAfterInstallText(flatBufferBuilder, i20);
        addTrafficReminder(flatBufferBuilder, i19);
        addNoAlertTime(flatBufferBuilder, i18);
        addAppDesc(flatBufferBuilder, i17);
        addUniqueId(flatBufferBuilder, i16);
        addChannelInfoSaveLimit(flatBufferBuilder, i15);
        addChannelInfo(flatBufferBuilder, i14);
        addPopUpAfterInstallNew(flatBufferBuilder, i13);
        addInstallConfig(flatBufferBuilder, i12);
        addPriorInstallWay(flatBufferBuilder, i11);
        addPermissions(flatBufferBuilder, i10);
        addIntentUri(flatBufferBuilder, i9);
        addSha256(flatBufferBuilder, i8);
        addSafeDownloadUrl(flatBufferBuilder, i7);
        addDownloadUrl(flatBufferBuilder, i6);
        addIconUrl(flatBufferBuilder, i5);
        addVersionName(flatBufferBuilder, i4);
        addVersionCode(flatBufferBuilder, i3);
        addPackageName(flatBufferBuilder, i2);
        addAppName(flatBufferBuilder, i);
        addAllowedNonWifiNetwork(flatBufferBuilder, z4);
        addPermPromptForLanding(flatBufferBuilder, z3);
        addPermPromptForCard(flatBufferBuilder, z2);
        addCheckSha256(flatBufferBuilder, z);
        return endAppInfoFb(flatBufferBuilder);
    }

    public static void addVersionName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addVersionCode(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addUniqueId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(19, i, 0);
    }

    public static void addTrafficReminder(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(22, i, 0);
    }

    public static void addSha256(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static void addSafeDownloadUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addReservedPkgName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(49, i, 0);
    }

    public static void addPureModeText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(46, i, 0);
    }

    public static void addPrivacyUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(52, i, 0);
    }

    public static void addPriorInstallWay(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(12, i, 0);
    }

    public static void addPopUpStyle(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(44, i, 0);
    }

    public static void addPopUpAfterInstallText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(23, i, 0);
    }

    public static void addPopUpAfterInstallNew(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(16, i, 0);
    }

    public static void addPopNotify(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(28, i, 0);
    }

    public static void addPermissions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(11, i, 0);
    }

    public static void addPermissionUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(50, i, 0);
    }

    public static void addPermPromptForLanding(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(15, z, false);
    }

    public static void addPermPromptForCard(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(14, z, true);
    }

    public static void addPackageName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addNoAlertTime(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(21, i, 0);
    }

    public static void addNextInstallWays(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(38, i, 0);
    }

    public static void addIntentUri(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(10, i, 0);
    }

    public static void addIntentPackage(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(27, i, 0);
    }

    public static void addIntent(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(26, i, 0);
    }

    public static void addInstallPureModeText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(47, i, 0);
    }

    public static void addInstallPermiText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(45, i, 0);
    }

    public static void addInstallConfig(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(13, i, 0);
    }

    public static void addInsActvNotifyCfg(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(32, i, 1);
    }

    public static void addInsActvNotifyBtnText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(31, i, 0);
    }

    public static void addIconUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addHasPermissions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(34, i, 0);
    }

    public static void addFullScrnNotifyText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(30, i, 0);
    }

    public static void addFullScrnNotify(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(29, i, 0);
    }

    public static void addFileSize(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(7, j, 0L);
    }

    public static void addDownloadUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addDlBtnText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(24, i, 0);
    }

    public static void addDeveloperName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(37, i, 0);
    }

    public static void addCurInstallWay(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(39, i, 0);
    }

    public static void addContiBtn(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(48, i, 0);
    }

    public static void addContentInstallBtnAction(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(53, i, 0);
    }

    public static void addCheckSha256(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(9, z, false);
    }

    public static void addChannelInfoSaveLimit(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(18, i, -2);
    }

    public static void addChannelInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(17, i, 0);
    }

    public static void addAutoOpenAfterInstall(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(42, i, 0);
    }

    public static void addAppType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(41, i, 1);
    }

    public static void addAppName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addAppLanguage(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(35, i, 0);
    }

    public static void addAppDetailsUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(51, i, 0);
    }

    public static void addAppDesc(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(20, i, 0);
    }

    public static void addAppCountry(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(36, i, 0);
    }

    public static void addAllowedNonWifiNetwork(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(33, z, false);
    }

    public static void addAllAreaPopDelay(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(43, j, 0L);
    }

    public static final class Vector extends BaseVector {
        public AppInfoFb get(AppInfoFb appInfoFb, int i) {
            return appInfoFb.__assign(AppInfoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public AppInfoFb get(int i) {
            return get(new AppInfoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addAfDlBtnText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(25, i, 0);
    }

    public static void addActName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(40, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
