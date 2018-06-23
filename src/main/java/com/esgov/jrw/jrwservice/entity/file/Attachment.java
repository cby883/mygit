package com.esgov.jrw.jrwservice.entity.file;

import com.baomidou.mybatisplus.annotations.TableName;
import com.esgov.jrw.jrwservice.entity.BaseEntity;

@TableName(value = "sys_attachment")
public class Attachment extends BaseEntity {

    private String targetType;
    private String targetId;
    private String targetFlag;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileType;
    private String remark;

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetFlag() {
        return targetFlag;
    }

    public void setTargetFlag(String targetFlag) {
        this.targetFlag = targetFlag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
