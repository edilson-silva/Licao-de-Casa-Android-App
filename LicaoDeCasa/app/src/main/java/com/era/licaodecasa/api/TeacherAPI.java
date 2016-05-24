package com.era.licaodecasa.api;

import com.era.licaodecasa.model.pojo.Aluno;
import com.era.licaodecasa.model.pojo.Curso;
import com.era.licaodecasa.model.pojo.Disciplina;
import com.era.licaodecasa.model.pojo.Escola;
import com.era.licaodecasa.model.pojo.Tarefa;
import com.era.licaodecasa.model.pojo.Turma;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Edilson on 14/03/2016.
 */
public interface TeacherAPI {

    String TEACHER_COMPLEMENT_URL = "teacher/";

    // SOLICITAR UMA LISTA DE TAREFAS, BASEADA NO ID DO(A) PROFESSOR(A).
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST(TEACHER_COMPLEMENT_URL+"")    // Tipo de metodo utilizado para envio/requisicao.
    Call<List<Tarefa>> getMyTasks(@Field("id") int id); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

    // ENVIAR UMA NOVA TAREFA, BASEADA NO ID DO(A) PROFESSOR(A).
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST(TEACHER_COMPLEMENT_URL+"")    // Tipo de metodo utilizado para envio/requisicao.
    Call<String> sendNewTask(@Field("id") int id); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

    // SOLICITAR UMA LISTA DE ESTUDANTES, BASEADA NO ID DO(A) PROFESSOR(A).
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST(TEACHER_COMPLEMENT_URL+"getMyStudents") // Tipo de metodo utilizado para envio/requisicao.
    // LEMBRAR DE ADD OS PARAMETROS COM OS DADOS DE AUTENTICACAO DO PROFESSOR
    Call<List<Aluno>> getMyStudents(@Field("id") int id); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

    // ============================== METODOS DOS SPINNERS ==============================

    // SOLICITAR UMA LISTA DE ESCOLAS QUE O PROGRSSOR(A) LECIONA, BASEADA NO ID DO(A) PROFESSOR(A).
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST("http://192.168.25.16/lc/getSchools")    // Tipo de metodo utilizado para envio/requisicao.
    Call<List<Escola>> getSchools(@Field("id_teacher") int idTeacher); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

    // SOLICITAR UMA LISTA DE DISCIPLINAS QUE O PROGRSSOR(A) LECIONA, BASEADA NO ID DO(A) PROFESSOR(A) E ID DA ESCOLA SELECIONADA.
    @FormUrlEncoded                                     // Formatando URL para envio de dados UTF-8
    @POST(TEACHER_COMPLEMENT_URL+"getDisciplines")      // Tipo de metodo utilizado para envio/requisicao.
    Call<List<Disciplina>> getDisciplines(@Field("id_teacher") int idTeacher); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

    // SOLICITAR UMA LISTA DE CURSOS, BASEADOS NO ID DA ESCOLA SELECIONADA.
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST(TEACHER_COMPLEMENT_URL+"lc/getCurses")    // Tipo de metodo utilizado para envio/requisicao.
    Call<List<Curso>> getCourses(@Field("id_school") int idSchool); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

    // SOLICITAR UMA LISTA DE TURMAS, BASEADAS NO ID DO CURSO SELECIONADO.
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST(TEACHER_COMPLEMENT_URL+"lc/getTeams")    // Tipo de metodo utilizado para envio/requisicao.
    Call<List<Turma>> getTeams(@Field("id_curse") int idCourse, @Field("id_school") int idSchool); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

    // ENVIAR NOVA ESCOLA E CURSO PARA ENSINAR, BASEADA NO ID DO(A) PROFESSOR(A).
    @FormUrlEncoded                     // Formatando URL para envio de dados UTF-8
    @POST(TEACHER_COMPLEMENT_URL+"")    // Tipo de metodo utilizado para envio/requisicao.
    Call<String> sendNewTeach(@Field("idEscola") int idEscola, @Field("idDisiplina") int idDiscipline); // Nome do metodo a ser implementado em determinada classe, passsagem de parametros via URL.

}
