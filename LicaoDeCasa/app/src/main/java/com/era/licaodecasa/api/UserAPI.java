package com.era.licaodecasa.api;

import com.era.licaodecasa.model.pojo.Tarefa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Edilson on 02/04/2016.
 */
public interface UserAPI {
    String USER_COMPLEMENT_URL = "lc/";

    // SOLICITAR UMA LISTA DE TAREFAS, BASEADA NO ID DO(A) PROFESSOR(A).
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST(USER_COMPLEMENT_URL+"")       // Tipo de metodo utilizado para envio/requisicao.
    Call<List<Tarefa>> loginValidate(@Field("email") String email, @Field("senha") String senha); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.


}
