package com.era.data.connection.webserver;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.era.extras.AppConstants;

import okhttp3.RequestBody;

public class GetSetDataWeb extends AsyncTask<RequestBody, String, String>{

    private GetSetDataWebInterface getDataWebContext;
    private String urlComplement;
    private String flagMethodIndicator;

    private ProgressDialog progressDialog;
    private boolean showProgressDialog;
    private Context dialogContext;
    private String dialogMessage;

    private String serverAnswer;

    public GetSetDataWeb(GetSetDataWebInterface getDataWebContext, String urlComplement, String flagMethodIndicator, boolean showProgressDialog, Context dialogContext, String dialogMessage){
        this.getDataWebContext = getDataWebContext;
        this.urlComplement = urlComplement;
        this.flagMethodIndicator = flagMethodIndicator;
        this.showProgressDialog = showProgressDialog;
        this.dialogContext = dialogContext;
        this.dialogMessage = dialogMessage;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(dialogContext);
        if(showProgressDialog){
            progressDialog.setMessage(dialogMessage);
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(RequestBody... formBody) {
        System.out.println("formBody"+formBody[0].toString());
        try {
            HttpConnection httpConnection = new HttpConnection();
            serverAnswer = httpConnection.getAndSetData(AppConstants.SERVER_URL.toString() + urlComplement, formBody[0]);
            System.out.println("GSDW -> ServerAnswer: "+serverAnswer);
        } catch (Exception e) { e.printStackTrace(); }

        return serverAnswer;
    }

    @Override
    protected void onPostExecute(String params) {
        getDataWebContext.getServerData(flagMethodIndicator, params);
        progressDialog.dismiss();
    }

}