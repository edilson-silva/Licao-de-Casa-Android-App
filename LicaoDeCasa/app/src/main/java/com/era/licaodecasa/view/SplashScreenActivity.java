package com.era.licaodecasa.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.era.data.bean.User;
import com.era.extras.AppConstants;
import com.era.data.dao.UserDAO;
import com.era.licaodecasa.R;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity{

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Definindo a imagem do SplashScreent ou Texto, cado seja alterado.
        ImageView imgSplash = (ImageView) findViewById(R.id.IVSplashImage);

        //Criando objeto de ANIMATION(Animacao);
        Log.i("SplashActivity", "Criando Animacao");
        Animation animation = new AnimationUtils().loadAnimation(getApplicationContext(), R.anim.welcome_splash_rotate);
        //Setando a animacao no componente.
        imgSplash.setAnimation(animation);

        Log.i("SplashActivity","Chamando Listener");
        animation.setAnimationListener(new Animation.AnimationListener(){

            //Metodo responsavel por criar o banco de dados e extrair os dados inicialmente, caso LoginAutomatico == true(1);
            @Override
            public void onAnimationStart(Animation animation) {
                //Instanciando o objeto userDAO que retornara os valores das Query's
                userDAO = new UserDAO(getApplicationContext());
                Log.i("SplashActivity", "UserDAO inicializado");
            }

            //Metodo responsavel de chamar a Activity de Login ou MainActivity de cada tipo de usuario caso LoginAutomatico == true(1);
            @Override
            public void onAnimationEnd(Animation animation) {
                //Verificando qual activity chamar.
                //Chamando o metodo que testa se o login esta marcado como automatico.
                if(userDAO.validateLocalUserLogin()){
                    //Ainda a ser implementada a Tela(Activity) de SplashScreen.
                    setContentView(R.layout.activity_splash_screen);
                    User autoLoginUser = userDAO.selectLocalUser();
                    openSpecificActivity(SplashScreenActivity.this, autoLoginUser);
                    Log.i("SplashActivity", "AutoLogin Sim");
                }else{
                    Log.i("SplashActivity", "AutoLogin Nao");
                    //Finalizando a animacao.
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

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
        Log.i("SplashActivity", "Criei meu intent");

        //Criando a lista que recebera o usuario e sera passadda via Intent com Parcelable para a Activity Especifica
        //Parcelable so recebe Listas
        ArrayList<User> localUserArray = new ArrayList<>();
        localUserArray.add(localUser);

        intent.putParcelableArrayListExtra("user", localUserArray);
        activityContext.startActivity(intent);
        Log.i("SplashActivity", "startActivity ai vamos nos");
    }
}
