package com.era.data.bean;

//Tabela Notificacao
public class Notification {
	
	private long nId;	            //id
	private User nUserSender;		//id do usuario emissor(DE)
	private User nUserReceiver;		//id do usuario repector(PARA)
	private String nTitle;			//titulo
	private String nMessage;		//mensagem
	private int nState;				//estado

    public Notification() {
        //Construtor vazio
    }

    public Notification(long nId, User nUserSender, User nUserReceiver, String nTitle, String nMessage, int nState) {
        this.nId = nId;
        this.nUserSender = nUserSender;
        this.nUserReceiver = nUserReceiver;
        this.nTitle = nTitle;
        this.nMessage = nMessage;
        this.nState = nState;
    }

    public long getnId() {
		return nId;
	}

	public void setnId(long nId) {
		this.nId = nId;
	}

	public User getnUserSender() {
		return nUserSender;
	}

	public void setnUserSender(User nUserSender) {
		this.nUserSender = nUserSender;
	}

	public User getnUserReceiver() {
		return nUserReceiver;
	}

	public void setnUserReceiver(User nUserReceiver) {
		this.nUserReceiver = nUserReceiver;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnMessage() {
		return nMessage;
	}

	public void setnMessage(String nMessage) {
		this.nMessage = nMessage;
	}

	public int getnState() {
		return nState;
	}

	public void setnState(int nState) {
		this.nState = nState;
	}
}
