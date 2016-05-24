package com.era.licaodecasa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.era.data.bean.User;
import com.era.extras.AppConstants;
import com.era.extras.GenericMethods;
import com.era.data.dao.UserDAO;
import com.era.licaodecasa.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountRecoveryActivity extends AppCompatActivity {

    private EditText etUserEmail;
    private User user;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_recovery);

        Log.i("AccountRecovery", "UserDAO inicializado");
        //Instanciando o objeto userDAO que retornara os valores das Query's
        userDAO = new UserDAO(this);
    }

    public void sendEmailToRecovery(View view){
        if(GenericMethods.isOnline(this)){
            //Iniciando os componentes e ligando eles as suas respectivas variaveis.
            etUserEmail = (EditText) findViewById(R.id.AccountRecovery_ETUserEmail);

            if(etUserEmail.getText().toString().isEmpty()){
                GenericMethods.showMessage(getApplicationContext(), AppConstants.FILL_ALL_FIELDS.toString());
            }else{
                userDAO.passwordRecoveryExternalUserByEmail("app/repassword", etUserEmail.getText().toString(), this, AppConstants.USERDAO_EXTERNAL_ACCOUNT_RECOVERY.toString(), true, "Enviando email!");
            }
        }else{
            GenericMethods.showMessage(this, AppConstants.CONNECTION_ERROR.toString());
        }
    }

    public void makeLogin(View view){
        //Chamando a Activity de Login.
        startActivity(new Intent(AccountRecoveryActivity.this, LoginActivity.class));
    }

    public void confirmEmailSender(String serverAnswer) {
        try {
            JSONObject jo = new JSONObject(serverAnswer);
            //Exibindo a mensagem vinda do servidor.
           GenericMethods.showMessage(getApplicationContext(), jo.getJSONObject("data").getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
