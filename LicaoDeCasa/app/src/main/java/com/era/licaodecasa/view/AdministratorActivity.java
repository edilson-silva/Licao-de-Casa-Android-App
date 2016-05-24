package com.era.licaodecasa.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.era.licaodecasa.model.adapters.ListAllUsersAdapter;
import com.era.data.bean.User;
import com.era.fragments.CustomDialogFramentUserSelected;
import com.era.licaodecasa.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdministratorActivity extends FragmentActivity {

    private User mUser, localUser;
    private ListView lvShowAllUsers;
    private SearchView svSearchData;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        //Inicializando objeto usuario, recebendo o mesmo de forma serializada.
        mUser = (User) getIntent().getParcelableArrayListExtra("user").get(0);

        //svSearchData = (SearchView) findViewById(R.id.AdministratorActivity_SVGetUsers);
        //svSearchData.setOnQueryTextListener(new SearchData());

        //verificar a duplicidade dos dados no listview.
        lvShowAllUsers = (ListView) findViewById(R.id.AdministratorActivity_LVShowAllUsers);
        lvShowAllUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Criando view e recebendo dados do item.
                ViewGroup vgItem = (ViewGroup) view;
                TextView userId = (TextView) vgItem.findViewById(R.id.SingleUser_TVUserID);
                TextView userName = (TextView) vgItem.findViewById(R.id.SingleUser_TVUserName);
                TextView userEmail = (TextView) vgItem.findViewById(R.id.SingleUser_TVUserEmail);
                TextView userType = (TextView) vgItem.findViewById(R.id.SingleUser_TVUserType);
                TextView userState = (TextView) vgItem.findViewById(R.id.SingleUser_TVUserState);

                localUser = new User();
                localUser.setuId(Integer.parseInt("" + userId.getText().toString()));
                localUser.setuName(userName.getText().toString());
                localUser.setuEmail(userEmail.getText().toString());
                // TYPE
                if (userType.getText().toString().equals("Estudante")){
                    localUser.setuType(1);
                }else if(userType.getText().toString().equals("Responsavel")){
                    localUser.setuType(2);
                }else if(userType.getText().toString().equals("Professor")){
                    localUser.setuType(3);
                }else if(userType.getText().toString().equals("Administrador")){
                    localUser.setuType(4);
                }
                // STATE
                if(userState.getText().toString().equals("Ativo")){
                    localUser.setuState(1);
                }else if(userState.getText().toString().equals("Intivo")){
                    localUser.setuState(0);
                }else if(userState.getText().toString().equals("Banido")){
                    localUser.setuState(-1);
                }

                //Chamando o metodo e exibindo os dados no Dialog.
                openEditUserFragment(localUser);
            }
        });

    }

    //Metodos relacionados aos Botoes do CustomDialog.
    /**
     * @method fragmentUserUpdate - Atualizar Usuario, recebendo dados do CustomDialog.
     * @param user
     * @return
     */
    public void fragmentUserUpdate(User user){
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        CustomDialogFramentUserSelected cdfus = (CustomDialogFramentUserSelected) getSupportFragmentManager().findFragmentByTag("dialog");

        if(cdfus != null){
            cdfus.dismiss();
            fragTransaction.remove(cdfus);

            JSONObject jo = new JSONObject();
            try {
                jo.put("id", localUser.getuId());
                jo.put("name", localUser.getuName());
                jo.put("email", localUser.getuEmail());
                jo.put("type", localUser.getuType());
                jo.put("state", localUser.getuState());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i("JSONUser", "-> " + jo.toString());

        }
    }

    /**
     * @method fragmentUserDelete - Deletar Usuario, recebendo dados do CustomDialog.
     * @param user
     * @return
     */
    public void fragmentUserDelete(User user){

    }

    /**
     * @method fragmentUserCalcelExit - Fechar o CustomDialog, ao clicar no botao Sair do mesmo.
     * @param
     * @return
     */
    public void fragmentUserCancelExit(){
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        CustomDialogFramentUserSelected cdfus = (CustomDialogFramentUserSelected) getSupportFragmentManager().findFragmentByTag("dialog");

        if(cdfus != null){
            cdfus.dismiss();
            fragTransaction.remove(cdfus);
        }

    }

    /**
     * @method openEditUserFragment - Faz a chamada e Exibe um CustomDialog com o usuario selecionado.
     * @param userMethod
     * @return
     */
    public void openEditUserFragment(User userMethod){
        //Criando objeto FragmentTransaction para dar inicio a instanciacao e lancamento dele na tela.
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        CustomDialogFramentUserSelected cdfus = new CustomDialogFramentUserSelected(userMethod);
        cdfus.show(fragTransaction, "dialog");
    }

    /*
    private class SearchData implements SearchView.OnQueryTextListener, GetSetDataWebInterface {

        @Override
        public boolean onQueryTextChange(String parcialText) {
            Log.i("SearchView", "RES: "+parcialText);
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String completeText) {
            Log.i("SearchView", "RES: "+completeText);
            //GetSetDataWeb getDataWebJSONM = new GetSetDataWeb(this, "administrator/getusersstudents", AppConstants.USERDAO_EXTERNAL_SELECT_ALL_USERS.toString(), false, getApplicationContext(), "Verificando os dados!");
            //getDataWebJSONM.execute(""+mUser.getuType());
            return false;
        }

        @Override
        public void getServerData(Context activityContext, String flagMethodIndicator, String serverAnswer) {
            System.out.println(serverAnswer);
            ArrayList<User> users = null;

            try {
                users = new ArrayList<User>();
                JSONObject jo = new JSONObject(serverAnswer);
                JSONArray ja = jo.getJSONArray("user");
                User auxUser;
                for (int i = 0; i < ja.length(); i++) {
                    auxUser = new User();
                    auxUser.setuId(ja.getJSONObject(i).getInt("id"));
                    auxUser.setuName(ja.getJSONObject(i).getString("nome"));
                    auxUser.setuEmail(ja.getJSONObject(i).getString("email"));
                    auxUser.setuType(ja.getJSONObject(i).getInt("tipo"));
                    auxUser.setuState(ja.getJSONObject(i).getInt("estado"));
                    users.add(auxUser);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            populateList(users);
        }
    }
    */

    /**
     * @method populateList - Faz o povoamento do ListView com os dados obtidos a partir da pesquisa.
     * @param users
     * @return
     */
    public void populateList(ArrayList<User> users) {
        lvShowAllUsers = (ListView) findViewById(R.id.AdministratorActivity_LVShowAllUsers);
        lvShowAllUsers.setAdapter(new ListAllUsersAdapter(this, users));
    }
}
