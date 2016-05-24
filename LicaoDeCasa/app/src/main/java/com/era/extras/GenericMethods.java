package com.era.extras;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class GenericMethods {

	JSONObject jsonObject = new JSONObject();
	JSONArray jsonArray = new JSONArray();

    /**
     * @method - Verifica se há conexão de internet no dispositivo.
     *
     * @param context  - Context utilizado para criar o objeto Toast e inicializar o mesmo.
     * @param message - Mensagem que será exibida no Toast(Alet.
     */
	public static void showMessage(Context context, String message){
    	Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		//Centralizando a mensagem do Toast
    	//t.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

    /**
     * @method - Verifica se há conexão de internet no dispositivo.
     *
     * @param context  - Context utilizado para verificar o estado da interet.
     */
	public static boolean isOnline(Context context) {
	    ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = manager.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected()){
	    	return true;
	    }else{
	    	return false;
	    }
	}

	/**
     * @method - Cria e inicializa os ProgressDialogs de espera.
     *
	 * @param context  - Context que será utilizado para criar o ProgressDialog
     * @param title    - Titulo do ProgressDialog
     * @param message  - Mensagem do ProgressDialog
	 */
	public static void showProgressDialog(Context context, String title, String message){
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.show();
	}

    /**
     * @method - Finaliza(dismiss) o ProgressDialogs passado.
     *
     * @param progressDialog  - ProgressDialog que será finalizado, através da chamada do método dismiss().
     */
	public static void dismissProgressDialog(ProgressDialog progressDialog){
		progressDialog.dismiss();
	}


	// A PARTIR DAQUI ESTAO METODOS QUE NAO SERAO UTILIZADOS, POR HORA.
	public String generateJSON(Object obj){
		return (jsonObject.toString());
	}
	
	public void degenreteJSON(String strJSON){
		
	}
	
}