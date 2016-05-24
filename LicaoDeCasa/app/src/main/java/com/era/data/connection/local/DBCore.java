package com.era.data.connection.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBCore extends SQLiteOpenHelper{

	private static final String DB_NAME = "dblicaodecasa";
	private static final int DB_VERSION = 1;
	private static final String USER_TABLE_NAME = "user";
	
	//Criando construtor para ativar o SQLite
	public DBCore(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Executando SQL para criacao da tabela usuario.
		db.execSQL("CREATE TABLE "+USER_TABLE_NAME+"(" +
				"_id INTEGER PRIMARY KEY NOT NULL," +
				"name TEXT NOT NULL," +
				"email TEXT NOT NULL," +
				"type INTEGER NOT NULL," +
				"state INTEGER NOT NULL," +
				"auto_login INETEGER NOT NULL)");
		Log.i("SQL-EXEC", "Tabela usuario criada, OK!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * Ao sair do app e logar com outro usuario, a tabela eh destruida.
		 * Depois sera recriada e passara a adicionar os dados do novo usuario logado.
		 */
		db.execSQL("DROP TABLE "+USER_TABLE_NAME);
		Log.i("SQL-EXEC", "Tabela usuario deletada, OK!");
		onCreate(db);
		Log.i("SQL-EXEC", "Tabela usuario recriada, OK!");
	}

}
