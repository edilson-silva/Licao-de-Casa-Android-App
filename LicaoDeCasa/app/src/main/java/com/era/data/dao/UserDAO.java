package com.era.data.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.era.data.bean.User;
import com.era.extras.AppConstants;
import com.era.extras.GenericMethods;
import com.era.data.connection.local.DBCore;
import com.era.data.connection.webserver.GetSetDataWeb;
import com.era.data.connection.webserver.GetSetDataWebInterface;
import com.era.data.interfaces.UserInterface;
import com.era.licaodecasa.view.AccountRecoveryActivity;
import com.era.licaodecasa.view.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class UserDAO implements UserInterface, GetSetDataWebInterface {

	//Constantes para banco de dados e clausulas Where
	static final String USER_TABLE_NAME = "user";
	
	//Variavel para conexao com a Base de Dados do SQLite(Interna).
	private SQLiteDatabase db;
	private Context context;
	private User user;
	private RequestBody formBody;

    //CheckBox selecionado - somente para metodo de login
    private boolean cbAutoLoginIsSelected;

    //Objeto relativo a conexao com a internet, recebe os dados ainda na thread de UI, sendo ele uma extensao de AsyncTask.
    GetSetDataWeb getSetDataWeb;

	public UserDAO() {
		//Construtor vazio somente para instancias padroes do usuario;
	}
	
	//Construtor chama o construtor de DBCore e inicia as transacoes com o SQLite
	public UserDAO(Context context) {
		//Aqui passamos para o nosso db um SQLiteDatabase.
		db = new DBCore(context).getWritableDatabase();
		this.context = context;
	}
	
	@Override
	public void insetLocalUser(User userToInsert) {
		//SQLite trabalha com chave-valor e a entidade.
		ContentValues values = new ContentValues();
		values.put("_id", userToInsert.getuId());
		values.put("name", userToInsert.getuName());
		values.put("email", userToInsert.getuEmail());
		values.put("type", userToInsert.getuType());
		values.put("state", userToInsert.getuState());
		values.put("auto_login", userToInsert.getuAutoLoginCode());
		
		/*
		 * Inserindo, passnado o nome da tabela
		 * 1 - Nome da tabela.
		 * 2 - Columa adicional, para nao deixar nulo, caso todos os registros sejam nulos.
		 * 3 - Valores a serem inseridos.
		 */
		db.insert(USER_TABLE_NAME, null, values);
		Log.i("DBQuery","Insert OK!");
	}

	@Override
	public boolean validateLocalUserLogin() {
		String[] columns = new String[]{"auto_login"};

		// Aqui o select retorna um cursor que sera armazenado e passara o dado requerido.
		Cursor cursor = db.query(USER_TABLE_NAME, columns, null, null, null, null, null);

		boolean isAutoLogin = false;
		System.out.println("user auto_login getCount: " + cursor.getCount());
		
		if(cursor.getCount() > 0){
			// Movendo cursor para o inicio, para pegar o primeiro registro. 
			cursor.moveToFirst();

			do {
				//verificando se o campo auto_login tem o valor 1.
				if(cursor.getInt(0) == 1){
					isAutoLogin = true;
					Log.i("validateLocalUserLogin","Select -> IsAutoLogin OK!");
				}
			} while (cursor.moveToNext());
		}else{
			Log.i("validateLocalUserLogin","Select -> IsAutoLogin ERR == Null!");
		}
        cursor.close();
		return isAutoLogin;
	}

	@Override
	public User selectLocalUser() {
		String[] columns = new String[]{"_id", "name", "email", "type", "state", "auto_login"};

		// Aqui o select retorna um cursor que sera armazenado e passado para o novo objeto usuario.
		Cursor cursor = db.query(USER_TABLE_NAME, columns, null, null, null, null, null);
		User userToSelect = new User();
		
		if(cursor.getCount() > 0){
			// Movendo cursor para o inicio, para pegar o primeiro registro. 
			cursor.moveToFirst();			
			do {
				userToSelect.setuId(cursor.getInt(0));
				userToSelect.setuName(cursor.getString(1));
				userToSelect.setuEmail(cursor.getString(2));
				userToSelect.setuType(cursor.getInt(3));
				userToSelect.setuState(cursor.getInt(4));
				userToSelect.setuAutoLoginCode(cursor.getInt(5));
			} while (cursor.moveToNext());
			Log.i("selectLocalUser","Select -> User OK!");
		}else{
			userToSelect = null;
			Log.i("selectLocalUser","Entrou aqui no erro -> User = null!");
		}
        cursor.close();
		return userToSelect;
	}

	@Override
	public void dropLocalUser(User userToDrop) {
        //Metodo 1 de se fazer.
        //String[] columns = new String[]{"_id"};
        //db.delete(USER_TABLE_NAME, "_id = ", columns);
		Log.i("dropLocalUser", "Delete -> User Id: " + userToDrop.getuId());
		/*
		 * Deletando, passnado usuario e deletando pelo _id.
		 * 1 - Nome da tabela.
		 * 2 - Clausula Where, inteira ou parcial.
		 * 3 - Array de String para clausulas wheres parciais.
		 * Lembrando que eh possivel colocar a clausula where completa e deixar o WhereArgs null.
		 */
		db.delete(USER_TABLE_NAME, "_id = " + userToDrop.getuId(), null);
		Log.i("dropLocalUser","Delete -> OK!");
	}

	@Override
	public void updateLocalUser(User userToUpdate) {
		String[] columns = new String[]{"_id, name, email, type, state, auto_login"};
		Cursor cursor = db.query(USER_TABLE_NAME, columns, null, null, null, null, null);
		cursor.moveToFirst();
		
		if(cursor.getCount() > 0){
			//SQLite trabalha com chave-valor e a entidade.
			ContentValues values = new ContentValues();
			values.put("name", userToUpdate.getuName());
			values.put("email", userToUpdate.getuEmail());
			values.put("type", userToUpdate.getuType());
			values.put("state", userToUpdate.getuState());
			values.put("auto_login", userToUpdate.getuAutoLoginCode());
			/*
			 * Atualizndo, passnado um usuario e o o codigo se login automatico(1).
			 * 1 - Nome da tabela.
			 * 2 - Valores a ser passados para o DB.
			 * 3 - Clausula Where, inteira ou parcial.
			 * 4 - Array de String para clausulas wheres parciais.
			 * Lembrando que eh possivel colocar a clausula where completa e deixar o WhereArgs null.
			 */
			String[] whereClause = new String[]{""+ userToUpdate.getuId()};
			
			db.update(USER_TABLE_NAME, values, "_id = ?", whereClause);
			Log.i("updateLocalUser","Update -> OK!");
		}else{
			insetLocalUser(userToUpdate);
			Log.i("updateLocalUser","Update, Insert -> OK!");
		}
        cursor.close();
		
	}

	@Override
	public void makeExternalRegisterUser(String email, String password) {
		// TODO Auto-generated method stub
	}

	@SuppressLint("LongLogTag")
    @Override
	public void validateExternalUserLogin(String urlComplement, String email, String password, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage, boolean cbAutoLoginIsChecked) {
		this.cbAutoLoginIsSelected = cbAutoLoginIsChecked;
		formBody = new FormBody.Builder().add("email", email).add("senha", password).build();

        GetSetDataWeb getSetDataWeb = new GetSetDataWeb(this, urlComplement, flagMethodIndicator, showProgressDialog, activityContext, dialogMessage);
		Log.i("UserDao-ValidateExternalUser", "Passei os dados para o metodo de chamada a web");
		getSetDataWeb.execute(formBody);
	}

	@Override
	public ArrayList<User> selectExternalAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectExternalUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateExternalUserById(int userId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateExternalUser(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dropExternalUserById(int userId) {
		// TODO Auto-generated method stub
	}

    @SuppressLint("LongLogTag")
	@Override
	public void passwordRecoveryExternalUserByEmail(String urlComplement, String email, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage) {
		getSetDataWeb = new GetSetDataWeb(this, urlComplement, flagMethodIndicator, showProgressDialog, activityContext, dialogMessage);
		Log.i("UserDao-passwordRecoveryExternalUserByEmail", "Passei os dados para o metodo de chamada a web");
		getSetDataWeb.execute(formBody);
		
	}	

	@Override
	public void getServerData(String flagMethodIndicator, String serverAnswer) {
        if(serverAnswer != null) {
            if (flagMethodIndicator.equals(AppConstants.USERDAO_EXTERNAL_LOGIN_VALIDATE.toString())) {
                try {
                    Log.i("Server", "Answer: " + serverAnswer);
                    JSONObject jo = new JSONObject(serverAnswer);
                    boolean isAuthorizedLogin = jo.getBoolean("return");

                    if (isAuthorizedLogin) {
                        JSONObject joUser = new JSONObject(serverAnswer).getJSONObject("user");

                        //se login ok cria o objeto e o passa para o metodo que decidira qual Activity chamara.
                        user = new User();
                        user.setuId(joUser.getLong("id"));
                        user.setuName(joUser.getString("name"));
                        user.setuEmail(joUser.getString("email"));
                        user.setuType(joUser.getInt("type"));
                        user.setuState(joUser.getInt("status"));

                        Log.i("LoginActivity", "Criei o user ");

                        if (cbAutoLoginIsSelected) {
                            Log.i("LoginActivity", "cbAutoLogin.isChecked() ");
                            user.setuAutoLoginCode(1);
                            insetLocalUser(user);
                        } else {
                            Log.i("LoginActivity", "cbAutoLogin.isUnChecked() ");
                            user.setuAutoLoginCode(0);
                        }
                        new LoginActivity().openSpecificActivity(context, user);
                    } else {
                        GenericMethods.showMessage(context, jo.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (flagMethodIndicator.equals(AppConstants.USERDAO_EXTERNAL_ACCOUNT_RECOVERY.toString())) {
                new AccountRecoveryActivity().confirmEmailSender(serverAnswer);
            }
        }
		
	}

}