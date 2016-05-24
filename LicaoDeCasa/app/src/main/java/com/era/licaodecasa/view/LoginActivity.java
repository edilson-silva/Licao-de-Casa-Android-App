package com.era.licaodecasa.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.era.data.bean.User;
import com.era.extras.AppConstants;
import com.era.extras.GenericMethods;
import com.era.data.dao.UserDAO;
import com.era.licaodecasa.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText etUserEmail, etUserPassword;
    private CheckBox cbAutoLogin;
    private UserDAO userDAO;
    private User localUser;
    private static String jsonResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = (Toolbar) findViewById(R.id.LoginActivity_TBLogin);
        mToolbar.setTitle(""+getResources().getString(R.string.Title_Activity_Login));
        setSupportActionBar(mToolbar);
        userDAO = new UserDAO(this);
    }

    /**
     * @method loginValidate - Metodo para validacao de login, vinculado ao botao Login_BTNLogin.
     * @param - serverAnswer
     * @return
     */
    public void loginValidate(View view){
        if(GenericMethods.isOnline(this)){
            //Iniciando os componentes, somente se o metodo for chamado.
            etUserEmail = (EditText) findViewById(R.id.Login_ETUserEmail);
            etUserPassword = (EditText) findViewById(R.id.Login_ETUserPassword);
            cbAutoLogin = (CheckBox) findViewById(R.id.Login_CBAutomaticLogin);

            if(TextUtils.isEmpty(etUserEmail.getText().toString()) || TextUtils.isEmpty(etUserPassword.getText().toString())){
                GenericMethods.showMessage(this, AppConstants.FILL_ALL_FIELDS.toString());
            }else{
                //Se checkBox cbAutoLogin for selecionado, indica que usuario quer login automatico no app.
                //Variavel boolean indica controle por padrão recebe false.
                //boolean cbAutoLoginIsChecked = cbAutoLogin.isChecked();
                //userDAO.validateExternalUserLogin("user/login", etUserEmail.getText().toString(), etUserPassword.getText().toString(), this, AppConstants.USERDAO_EXTERNAL_LOGIN_VALIDATE.toString(), true, "Validando Dados!", cbAutoLoginIsChecked);

            }
            //Chamando o metodo que limpa os campos.
            clearAll();
        }else{
            //Exibe a mensagem de nao ha conexao.
            GenericMethods.showMessage(this, AppConstants.CONNECTION_ERROR.toString());
        }
    }

    /**
     * @method makeRegister - Chama a activity para fazer Registo.
     * @param
     * @return
     */
    public void makeRegister(View view){
        //Chamando a Activity de Cadastro.
        startActivity(new Intent(this, SignUpActivity.class));
    }

    /**
     * @method forgotPassword - Chama a activity para fazer Recuperacao de Senha.
     * @param
     * @return
     */
    public void forgotPassword(View view){
        //Chamando a Activity de Recuperacao de conta/senha.
        startActivity(new Intent(this, AccountRecoveryActivity.class));
    }

    /**
     * @method clearAllEditTextAndVariables - Limpa o campo de senha do EditText etUserPassword.
     * @param
     * @return
     */
    private void clearAll(){
        etUserPassword.setText("");
    }

    /**
     * @method openSpecifyActivity - Chama a Activity dependendo do tipo de usuario logado na sessao atual.
     * @param localUser
     * @return
     */
    public void openSpecificActivity(Context activityContext, User localUser){
        Log.i("LoginActivity", "Entrei para chamar a activity");
        Intent intent = null;
        if(localUser.getuType() == Integer.parseInt(AppConstants.USER_ADMIN.toString())){
            intent = new Intent(activityContext, AdministratorActivity.class);
        } else if(localUser.getuType() == Integer.parseInt(AppConstants.USER_STUDENT.toString())){
            intent = new Intent(activityContext, StudentActivity.class);
        }else if(localUser.getuType() == Integer.parseInt(AppConstants.USER_TECHAER.toString())){
            intent = new Intent(activityContext, TeacherActivity.class);
        }else if(localUser.getuType() == Integer.parseInt(AppConstants.USER_RESPONSIBLE.toString())){
            intent = new Intent(activityContext, ResponsibleActivity.class);
        }
        Log.i("LoginActivity", "Criei meu intent");
        //Criando a lista para passar o usuario via intent com parcelable
        ArrayList<User> localUserArray = new ArrayList<>();
        localUserArray.add(localUser);
        //Parcelable so recebe Listas
        intent.putParcelableArrayListExtra("user", localUserArray);
        activityContext.startActivity(intent);
        Log.i("LoginActivity", "startActivity ai vamos nos");
    }

}