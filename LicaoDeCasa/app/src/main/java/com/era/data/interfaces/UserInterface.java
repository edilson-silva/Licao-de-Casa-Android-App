package com.era.data.interfaces;

import java.util.ArrayList;

import android.content.Context;

import com.era.data.bean.User;

//import com.era.tablesdb.AutomaticLoginUser;

public interface UserInterface {

	//OPERACOES INTERNAS(DBLOCAL).
	
	/**
	 * @method insetLocalUser - Inseri usuario no Banco de Dados Local.
	 * @param user
	 * @return
	 */
	public void insetLocalUser(User user);
	
	/**
	 * @method validateLocalUserLogin - Inseri usuario no Banco de Dados Local.
	 * @param
	 * @return isAutoLogin
	 */
	public boolean validateLocalUserLogin();
	
	/**
	 * @method selectLocalUser - Deleta usuario no Banco de Dados Local, de acordo com seu id.
	 * @param
	 * @return user
	 */
	public User selectLocalUser();
	
	/**
	 * @method dropLocalUser - Deleta usuario no Banco de Dados Local, de acordo com seu id.
	 * @param user
	 * @return
	 */
	public void dropLocalUser(User user);
	
	/**
	 * @method updateLocalUser - Atualiza(Deleta Usuario e o Re-inseri) usuario no Banco de Dados Local.
	 * @param user
	 * @return
	 */
	public void updateLocalUser(User user);

	// OPERACOES EXTERNAS(SERVIDOR WEB);
	/**
	 * @method makeExternalRegisterUser - Faz o registro do usuario no Sistema.
	 * @param user
	 * @return
	 */
	public void makeExternalRegisterUser(String email, String password);	
	
	/**
	 * @method validateExternalUserLogin - Valida o login do usuario no Sistema.
	 * @param email, password
	 * @return
	 */
	public void validateExternalUserLogin(String urlComplement, String email, String password, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage, boolean cbAutoLoginIsChecked);
	
	/**
	 * @method selectExternalAllUsers - Seleciona todos os usuarios do Sistema.
	 * @param
	 * @return users
	 */
	public ArrayList<User> selectExternalAllUsers();
	
	/**
	 * @method selectExternalUserById - Seleciona usuario do Sistema, dependendo do seu Id.
	 * @param userId
	 * @return user
	 */
	public User selectExternalUserById(int userId);
	
	/**
	 * @method updateExternalUser - Atualiza usuario do Sistema, de acordo com seu Id.
	 * @param userId
	 * @return
	 */
	public void updateExternalUserById(int userId);

	/**
	 * @method updateExternalUser - Atualiza usuario do Sistema.
	 * @param user
	 * @return
	 */
	public void updateExternalUser(User user);

	
	/**
	 * @method dropExternalUserById - Deleta usuario do Sistema.
	 * @param userId
	 * @return
	 */
	public void dropExternalUserById(int userId);
	
	/**
	 * @method passwordRecoveryExternalUserById - Envia email para recuperacao de senha.
	 * @param userEmail
	 * @return
	 */
	public void passwordRecoveryExternalUserByEmail(String urlComplement, String email, Context activityContext, String flagMethodIndicator, boolean showProgressDialog, String dialogMessage);
	
}