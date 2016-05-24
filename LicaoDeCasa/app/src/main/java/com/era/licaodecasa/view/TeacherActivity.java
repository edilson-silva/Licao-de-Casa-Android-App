package com.era.licaodecasa.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.era.licaodecasa.model.adapters.TabsAdapter;
import com.era.data.bean.User;
import com.era.extras.GenericMethods;
import com.era.licaodecasa.R;
import com.era.licaodecasa.model.utilities.tabs.SlidingTabLayout;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class TeacherActivity extends AppCompatActivity {

    private Toolbar mToolbar = null;
    private User mUser, localUser;
    Bundle savedState = null;
    private FragmentManager fManager = getSupportFragmentManager();
    private Toast toast;
    private long lastBackPressTime = 0;
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;

    // REFERENTES AS TABS - Implementar as Tabs sempre acima do NavigationDrawer para nao gerar NullPointerException
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        savedState = savedInstanceState;

        // Pegando usuario passado atraves do parcelable
        mUser = (User) getIntent().getParcelableArrayListExtra("user").get(0);

        //Metodo de configuracao da Toolbar
        toolbarSetup();


        //Criando e inserindo o NavigationDrawer.
        // Aqui vira a chamada de um metodo que nos fornecera o Navigation Drawer Default(Generico).
        // Atualmente a implementacao do mesmo esta abaixo dos spinners, que sumirao e darao lugar as tabs.

        // SLIDING TABS
        mViewPager = (ViewPager) findViewById(R.id.vp_my_tabs);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_my_tabs);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.color_white));
        mSlidingTabLayout.setViewPager(mViewPager);

        // ACCOUNT HEADER PARA NAVIGATION DRAWER
        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_material_red)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(getResources().getString(R.string.NavigationDrawer_TVProfileName))
                                .withEmail(getResources().getString(R.string.NavigationDrawer_TVProfileEmail))
                )
                .withOnAccountHeaderListener(
                        new AccountHeader.OnAccountHeaderListener() {
                            @Override
                            public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                                // SETING NEW NAME AND EMAIL OF USER
                                return true;
                            }
                        })
                .build();
        // NAVIGATION DRAWER
        navigationDrawerLeft = new DrawerBuilder()
                // Vinculando a Activity
                .withActivity(this)
                        // Vinculando a Toolbar
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                        // Animando botao - Menu Amburger
                .withActionBarDrawerToggleAnimated(true)
                        //.withSliderBackgroundColorRes(R.color.colorPrimary)
                .withDrawerGravity(Gravity.LEFT)
                        // Passando a instancia e salvando o estado atual do App
                .withSavedInstance(savedInstanceState)
                        // Indicando que o item 0 sera o item selecionado por padrao
                .withSelectedItem(0)
                        // Instanciando um Listener, para pegar o click dos itens do Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                        // Adicionando o AccountHeader ao DrawerLayout
                .withAccountHeader(accountHeader)
                .build();
        // NAVIGATION DRAWER ITENS E ICONES
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName(R.string.NavigationDrawer_MIAppHome).withIcon(R.drawable.ic_home_black_48dp));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName(R.string.NavigationDrawer_TVProfileName).withIcon(R.drawable.ic_person_black_48dp));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName(R.string.NavigationDrawer_MINotifications).withIcon(R.drawable.ic_notifications_black_48dp));
        navigationDrawerLeft.addItem(new DividerDrawerItem());
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName(R.string.NavigationDrawer_MIExit).withIcon(R.drawable.ic_exit_to_app_black_48dp));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*
     *  Metodo responsavel por pegar os alunos de determinado filtro de pesquisa.
     */
    public void getAllStudents(View view) {
        GenericMethods.showMessage(getApplicationContext(), "BTN Press");
    }

    /*
     *  Metodo implementado pela Interface de NavigationDrawer.OnNavigationItemSelectedListener
     *  Metodo respoonsavel por receber o click nos itens do menu do NavigationDrawer
     */

    //Sobrescrevendo o metodo do botao voltar, caso o mesmo seja pressionado no comeco da pilha da activity, pergunta se usuario quer mesmo fechar o app.
    //Se o botao for pressionado novamente antes da mensagem sumir a aplicao eh encerrada.
    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_SHORT);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
                super.onBackPressed();
            }
        }
    }

    /*
     *  Metodo que inicializa a Toolbar e inseri nela as informacoes padroes.
     *  @return: void
     */
    private void toolbarSetup() {
        //Inserindo Titulo na actionBar
        mToolbar = (Toolbar) findViewById(R.id.tb_my_toolbar);
        mToolbar.setTitle("Bem vindo(a) " + getResources().getString(R.string.Title_Activity_Teacher));
        mToolbar.setSubtitle(mUser.getuName());
        setSupportActionBar(mToolbar);
    }

    // GET PARA FRAGMENTS PEGAREM OS DADOS DO PROFESSOR
    public User getTeacher(){
        return mUser;
    }


}
