package com.example.androidjavasuperhandlers.utils;


import android.os.AsyncTask;

import  com.example.androidjavasuperhandlers.config.Constants;
import  com.example.androidjavasuperhandlers.model.LogEntity;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.table.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class BlobLogger {
    private double verBlobLog = 2.1;
    private String usrAndroidID;
    private String appName;
    private String logProcess;
    private String fmkUsername;
    private String fmkWorkorder;
    private int logProcessNo;


    public void createLogProcess(){
        logProcess = usrAndroidID + String.valueOf(System.currentTimeMillis()) +appName+ (int)Math.floor(Math.random()*(1000000000-100+1)+100);
    }
    public void initializeLog(String applicationName, String AndroidID) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        usrAndroidID = AndroidID;
        appName = applicationName;
        createLogProcess();
        fmkUsername = "undefined";
        fmkWorkorder = "undefined";
        logProcessNo = 0;
        postInfo("Initialize BlobLogger, version", String.valueOf(verBlobLog));
    }

    //.................................................................................
    public void postWarning(String logAction, String logValue) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        try {
            logValue = URLEncoder.encode(logValue, StandardCharsets.UTF_8.toString());
            objd.execute(logAction, "WARNING", logValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void postTrace(String logAction, String logValue) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        try {
            logValue = URLEncoder.encode(logValue, StandardCharsets.UTF_8.toString());
            objd.execute(logAction, "TRACE", logValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void postError(String logAction, String logValue) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        try {
            logValue = URLEncoder.encode(logValue, StandardCharsets.UTF_8.toString());
            objd.execute(logAction, "ERROR", logValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void postInfo(String logAction, String logValue) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        try {
            logValue = URLEncoder.encode(logValue, StandardCharsets.UTF_8.toString());
            objd.execute(logAction, "INFO", logValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public void postFatal(String logAction, String logValue) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        try {
            logValue = URLEncoder.encode(logValue, StandardCharsets.UTF_8.toString());
            objd.execute(logAction, "FATAL", logValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    //.................................................................................
    public void postWarning(String logAction) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        objd.execute(logAction, "WARNING", "");
    }
    public void postTrace(String logAction) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        objd.execute(logAction, "TRACE", "");
    }
    public void postError(String logAction) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        objd.execute(logAction, "ERROR", "");
    }
    public void postInfo(String logAction) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        objd.execute(logAction, "INFO", "");
    }
    public void postFatal(String logAction) {
        BlobLogger.LongOperation objd = new BlobLogger.LongOperation();
        objd.execute(logAction, "FATAL", "");
    }

    //.................................................................................
    class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                CloudStorageAccount storage = new CloudStorageAccount(new StorageCredentialsAccountAndKey(Constants.superHandlerBlobLogger.BLOB_ACCOUNT_NAME, Constants.superHandlerBlobLogger.BLOB_ACCOUNT_KEY));
                CloudTableClient tableClient = storage.createCloudTableClient();
                CloudTable cloudTable = tableClient.getTableReference("BlobLoggerTsys");
                LogEntity newLog = new LogEntity(System.currentTimeMillis() + "BL", "BlobLogger");
                logProcessNo = logProcessNo + 1;
                newLog.setlogProcessNo(String.valueOf(logProcessNo));
                newLog.setidMobile(usrAndroidID);
                newLog.setappName(appName);
                newLog.setfmkUsername(fmkUsername);
                newLog.setfmkWorkorder(fmkWorkorder);
                newLog.setlogProcess(logProcess);

                newLog.setlogAction(params[0]);
                newLog.setlogType(params[1]);
                newLog.setlogValue(params[2]);

                TableOperation insertCustomer1 = TableOperation.insertOrReplace(newLog);
                cloudTable.execute(insertCustomer1);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}
