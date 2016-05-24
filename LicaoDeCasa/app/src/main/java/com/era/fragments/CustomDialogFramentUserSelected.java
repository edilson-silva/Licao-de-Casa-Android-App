package com.era.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.era.data.bean.User;
import com.era.extras.AppConstants;
import com.era.licaodecasa.view.AdministratorActivity;
import com.era.licaodecasa.R;

public class CustomDialogFramentUserSelected extends DialogFragment{

	private User user;
	
	public CustomDialogFramentUserSelected() {
		// Construtor vazio. (Padrao)
	}
	
	public CustomDialogFramentUserSelected(User user) {
		//Construtor passando um usuario, necessario para mostrar usuario pre-carregado.
		this.user = user;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i("CustomDialogFragmentUserSelected", "onCreate");
		/*
		 * Impedindo do usuario clicar fora do fragment para fechar o mesmo.
		 * Por padrao o setCancelable recebe true.
		 */
		setCancelable(false);
		setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Panel);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		Log.i("CustomDialogFragmentUserSelected", "onCreateView");
		
		View view = inflater.inflate(R.layout.single_user_fragment_editor, container);
		TextView tvUserId = (TextView) view.findViewById(R.id.SingleUserFragment_TVUserID);
		final EditText etUserName = (EditText) view.findViewById(R.id.SingleUserFragment_ETUserName);
		final EditText etUserEmail = (EditText) view.findViewById(R.id.SingleUserFragment_ETUserEmail);

		final RadioGroup vgUserType = (RadioGroup) view.findViewById(R.id.SinleUserFragment_RGUserType);
		final RadioGroup vgUserState = (RadioGroup) view.findViewById(R.id.SinleUserFragment_RGUserState);
		
		Button btnUserUpdate = (Button) view.findViewById(R.id.SingleUserFragment_BTNUpdate);
		Button btnUserDelete = (Button) view.findViewById(R.id.SingleUserFragment_BTNDelete);
		Button btnCalcelExit = (Button) view.findViewById(R.id.SingleUserFragment_BTNCancelExit);
		
		//Capturando o tipo e o estado do usuario. 
		String userType = ""+ user.getuType();
		String userState = ""+ user.getuState();

		//Setando os valos nos componentes.
		// Id
		tvUserId.setText(""+ user.getuId());
		// Nome
		etUserName.setText(user.getuName());
		//Email
		etUserEmail.setText(user.getuEmail());
		//Tipo
		RadioButton rbUserType = null;
		if(AppConstants.USER_ADMIN.toString().equals(userType)){
			rbUserType = (RadioButton) view.findViewById(R.id.radioGroupTypeItem0);
		}else if(AppConstants.USER_TECHAER.toString().equals(userType)){
			rbUserType = (RadioButton) view.findViewById(R.id.radioGroupTypeItem1);
		}else if(AppConstants.USER_STUDENT.toString().equals(userType)){
			rbUserType = (RadioButton) view.findViewById(R.id.radioGroupTypeItem2);
		}else if(AppConstants.USER_RESPONSIBLE.toString().equals(userType)){
			rbUserType = (RadioButton) view.findViewById(R.id.radioGroupTypeItem3);
		}
		rbUserType.setChecked(true);
		//Estado
		RadioButton rbUserState = null;
		if(AppConstants.USER_ACTIVE.toString().equals(userState)){
			rbUserState = (RadioButton) view.findViewById(R.id.radioGroupStateItem0);
		}else if(AppConstants.USER_INACTIVE.toString().equals(userState)){
			rbUserState = (RadioButton) view.findViewById(R.id.radioGroupStateItem1);
		}else if(AppConstants.USER_BANNED.toString().equals(userState)){
			rbUserState = (RadioButton) view.findViewById(R.id.radioGroupStateItem2);
		}
		rbUserState.setChecked(true);		
		
		/**
		 * @method btnUserUpdate - Chama o metodo fragmentUserUpdate da MainActivity,
		 * 						   Passando para o mesmo o usuario a ser atualizado.
		 * @param 
		 * @return
		 */
		btnUserUpdate.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				user.setuName(etUserName.getText().toString());
				user.setuEmail(etUserEmail.getText().toString());
				
				int userTypeIndex = vgUserType.getCheckedRadioButtonId();
				Log.i("userTypeIndex", "Value: "+userTypeIndex);
				if(userTypeIndex == R.id.radioGroupTypeItem0){
					//Administrador
					user.setuType(4);
				}else if(userTypeIndex == R.id.radioGroupTypeItem1){
					//Professor
					user.setuType(3);
				}else if(userTypeIndex == R.id.radioGroupTypeItem2){
					//Estudante
					user.setuType(1);
				}else if(userTypeIndex == R.id.radioGroupTypeItem3){
					//Responsavel
					user.setuType(2);
				}
				
				int userStateIndex = vgUserState.getCheckedRadioButtonId();
				Log.i("userStateIndex", "Value: "+userTypeIndex);
				if(userStateIndex == R.id.radioGroupStateItem0){
					//Ativo
					user.setuState(1);
				}else if(userStateIndex == R.id.radioGroupStateItem1){
					//Inativo
					user.setuState(0);
				}else if(userStateIndex == R.id.radioGroupStateItem2){
					//Banido
					user.setuState(-1);
				}
				
				((AdministratorActivity) getActivity()).fragmentUserUpdate(user);
			}
		
		});
		
		/**
		 * @method btnUserUpdate - Chama o metodo fragmentUserDelete da MainActivity,
		 * 						   Passando para o mesmo o usuario a ser deletado.
		 * @param 
		 * @return
		 */
		btnUserDelete.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				((AdministratorActivity) getActivity()).fragmentUserUpdate(user);
				
			}
		
		});
		
		/**
		 * @method btnUserUpdate - Chama o metodo fragmentUserCancelExit da MainActivity,
		 * @param 
		 * @return
		 */
		btnCalcelExit.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//Chamando o metodo dismiss() para fechar o dialog.
				//dismiss();
				/*
				 * Chamando o metodo cancelExit da MainActiivty.
				 * Passando para ele a responsabiliade de fechar o dislog.
				 */
				((AdministratorActivity) getActivity()).fragmentUserCancelExit();
			}
			
		});
		
		return(view);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		//Prevenindo erro de NullPointerException -> Dica do StackOverflow
		if(getDialog() == null){
			setShowsDialog(false);	
		}
		super.onActivityCreated(savedInstanceState);
		Log.i("CustomDialogFragmentUserSelected", "onActivityCreated()");
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		Log.i("CustomDialogFragmentUserSelected", "onAttach()");
	}
	
	@Override
	public void onCancel(DialogInterface dialog){
		super.onCancel(dialog);
		Log.i("CustomDialogFragmentUserSelected", "onCancel()");
	}
	
	/*
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		super.onCreateDialog(savedInstanceState);
		Log.i("CustomDialogFragmentUserSelected", "onCreateDialog()");
		return(null);
	}
	*/

	@Override
	public void onDestroyView(){
		super.onDestroyView();
		Log.i("CustomDialogFragmentUserSelected", "onDestroyView()");
	}
	
	@Override
	public void onDetach(){
		super.onDetach();
		Log.i("CustomDialogFragmentUserSelected", "onDetach()");
	}
	
	@Override
	public void onDismiss(DialogInterface dialog){
		super.onDismiss(dialog);
		Log.i("CustomDialogFragmentUserSelected", "onDismissDialog()");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		Log.i("CustomDialogFragmentUserSelected", "onSaveInstanceState()");
	}
	
	@Override
	public void onStart(){
		super.onStart();
		Log.i("CustomDialogFragmentUserSelected", "onStart()");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.i("CustomDialogFragmentUserSelected", "onStop()");
	}
}
