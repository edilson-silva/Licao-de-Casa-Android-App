package com.era.extras;

public enum AppConstants {

    // Key para Logs.
    LOG_KEY("LDC"),
    LOG_KEY_METHOD(" METHOD: "),
    LOG_KEY_SERVER_RETURN(" SERVER: "),
    LOG_KEY_ERROR(" ERROR: "),
    LOG_KEY_OK("OK: "),

	// Link do Servidor (inicial), deve ser adicionado o nome do metodo.
	SERVER_URL("http://192.168.25.16/"),
	// Complementos de Links para o Servidor USERDAO

    // Complemento de Links para o Servidor TEACHER
	TEACHER_DAO_GET_MY_STUDENTS("teacher/getMyStudents"),

    // Tipos de Usuarios.
    USER_STUDENT("1"),
    USER_RESPONSIBLE("2"),
    USER_TECHAER("3"),
    USER_ADMIN("4"),

    // Status dos Usuarios.
    USER_BANNED("-1"),
    USER_INACTIVE("0"),
    USER_ACTIVE("1"),

	//Method Flags - Bandeiras indicadoras de qual app esta fazendo requisicoes ao servidor web.

	//UserDAO - User Querys Flags.
	USERDAO_EXTERNAL_REGISTER("UserDAOExternalRegister"),					// Registrar-se - Externo
	USERDAO_EXTERNAL_LOGIN_VALIDATE("UserDAOExternalLoginValidate"),		// Validar Login - Externo
	USERDAO_EXTERNAL_SELECT_USER_BY_ID("UserDAOExternalSelectUserById"),	// Pegar Usuario via ID - Externo
	USERDAO_EXTERNAL_SELECT_ALL_USERS("UserDAOExternalSelectAllUsers"),		// Pegar todos os Usuarios - Externo
	USERDAO_EXTERNAL_UPDATE_USER_BY_ID("UserDAOExternalUpdateUserById"),	// Atualizar Usuario via ID - Externo
	USERDAO_EXTERNAL_DROP_USER_BY_ID("UserDAOExternalDropUserById"),		// Deletar Usuario via ID - Externo
	USERDAO_EXTERNAL_ACCOUNT_RECOVERY("UserDAOExternalAccountRecovery"),	// Recuperar Conta - Externo

	// TeacherDAO - Teacher Requests Flags
    TEACHERDAO_EXTERNAL_GET_MY_STUDENTS("teacher/getMyStudents"),

	//StudentDAO - User Querys Flags.
	STUDENTDAO_EXTERNAL_GET_STUDENTS("StudentDAOExternalGetStdents"),		// Pegar todos os Alunos - Externo
	//Mensagens do Sistema.
	CONNECTION_ERROR("Nao ha conexao.\nConecte-se ha uma rede 3G, 4G ou WiFi!"),
	GET_DATA_ERROR("Erro ao obter dados.\nPorfavor verifique sua conexao!"),
	EMAIL_PASSWORD_INCORRECT("Email ou Senha incorretos.\nPorfavor verifique e tente novamente"),
	FILL_ALL_FIELDS("Porfavor, preencha todos os campos!"),
	INVALID_EMAIL("Email invalido.\nPorfavor preencha o campo com um email valido!"),
	INCORRECT_PASSWORDS("As senhas nao coincidem.\nPorfavor verificar se sao iguais!"),
	UNEXPECTED_ERROR("Erro inesperado."),
	EMAIL_REGISTERED("O email ja esta cadastrado no sistema.\nTente outro endereco"),
	REGISTERED_SUCCESS("Cadastro realizado com Sucesso! :D\nVerifique seu email para ativar sua conta."),
	WRONG_CAPTCHA("Codigo Incorreto.\nDigite novamente!"),

	//Outros - Ainda a revisar
	GET_DATA_SERVER("GetServerData"),

	//Get Data
	GET_ALL_STUDENTS("GetAllStudents"),
	SEND_NEW_TASK("SendNewTask"),
	GET_ALL_TASKS("GetAllTask");
	
    private final String MESSAGE;

    /**
     * @param MESSAGE
     */
    private AppConstants(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.MESSAGE;
    }
    
}
