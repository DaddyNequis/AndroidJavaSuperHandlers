package com.example.androidjavasuperhandlers.model;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class LogEntity extends TableServiceEntity {

    public LogEntity(String Message, String Value) {
        this.partitionKey = Message;
        this.rowKey = Value;

    }
    public LogEntity() { }
    private String appName;
    private String idMobile;
    private String logProcess;
    private String fmkUsername;
    private String fmkWorkorder;
    private String logType;
    private String logAction;
    private String logValue;
    private String logProcessNo;



    public void setappName(String value) {this.appName = value; }
    public void setidMobile(String value) {this.idMobile = value; }
    public void setlogProcess(String value) {this.logProcess = value; }
    public void setfmkUsername(String value) {this.fmkUsername = value; }
    public void setfmkWorkorder(String value) {this.fmkWorkorder = value; }
    public void setlogType(String value) {this.logType = value; }
    public void setlogAction(String value) {this.logAction = value; }
    public void setlogValue(String value) {this.logValue = value; }
    public void setlogProcessNo(String value) {this.logProcessNo = value; }



    public String getappName() { return appName;}
    public String getidMobile() { return idMobile;}
    public String getlogProcess() { return logProcess;}
    public String getfmkUsername() { return fmkUsername;}
    public String getfmkWorkorder() { return fmkWorkorder;}
    public String getlogType() { return logType;}
    public String getlogAction() { return logAction;}
    public String getlogValue() { return logValue;}
    public String getlogProcessNo() { return logProcessNo;}

}
