package com.era.licaodecasa.model.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.era.data.bean.User;
import com.era.licaodecasa.R;

public class ListAllUsersAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<User> users;
	
	public ListAllUsersAdapter(Context context, ArrayList<User> users) {
		this.context = context;
		this.users = users;
	}
	
	@Override
	public int getCount() {
		return users.size();
	}

	@Override
	public Object getItem(int postition) {
		return users.get(postition);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		
		User user = users.get(position);
		View layout;
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.single_user_line, null);			
	
		// Criando objetos de TextView e inicializando os mesmos com valores vindos do ArrayList
		TextView tvId, tvName, tvEmail, tvType, tvState;
		// ID
		tvId = (TextView) layout.findViewById(R.id.SingleUser_TVUserID);
		tvId.setText(""+user.getuId());
		// NAME
		tvName = (TextView) layout.findViewById(R.id.SingleUser_TVUserName);
		tvName.setText(user.getuName());
		// EMAIL
		tvEmail = (TextView) layout.findViewById(R.id.SingleUser_TVUserEmail);
		tvEmail.setText(user.getuEmail());
		// TYPE
		tvType = (TextView) layout.findViewById(R.id.SingleUser_TVUserType);
		if(user.getuType() == 1){
			tvType.setText("Estudante");			
		}else if(user.getuType() == 2){
			tvType.setText("Responsavel");
		}else if(user.getuType() == 3){
			tvType.setText("Professor");
		}else if(user.getuType() == 4){
			tvType.setText("Administrador");
		}
		// STATE
		tvState = (TextView) layout.findViewById(R.id.SingleUser_TVUserState);
		//tvState.setText(tvState.getText()+" "+user.getuState());
		tvState = (TextView) layout.findViewById(R.id.SingleUser_TVUserState);
		if(user.getuState() == 1){
			tvState.setText("Ativo");			
		}else if(user.getuState() == 0){
			tvState.setText("Inativo");
		}else if(user.getuState() == -1){
			tvState.setText("Banido");
		}
		
		// Retornando um Item(Linha), ele eh chamado mais de uma vez, dependendo do tamanho do ArrayLisy(Quantidade de Linhas).
		return layout;
	}

}
