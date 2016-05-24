package com.era.data.connection.webserver;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.era.extras.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Edilson on 08/03/2016.
 */
public class CustomJsonArrayRequest extends Request<JSONArray> {

    private Response.Listener<JSONArray> response;
    private Map<String, String> params;

    // Construtor 1 - Construtor com Method sempre GET
    public CustomJsonArrayRequest(String url, Map<String, String> params, Response.Listener<JSONArray> response, Response.ErrorListener listener) {
        super(Method.GET, url, listener);
        this.params = params;
        this.response = response;
    }

    // Construtor 2 - Pode ser passado o method(metodo) via parametro
    public CustomJsonArrayRequest(int method, String url, Map<String, String> params, Response.Listener<JSONArray> response, Response.ErrorListener listener) {
        super(method, url, listener);
        this.params = params;
        this.response = response;
    }

    public Map<String, String> getParams() throws AuthFailureError {
        return this.params;
    }

    /**
     * @param response
     * @return Response<JSONArray>
     * @see - Metodo utilizado para obter resposta do WebServer e enviar dados pelo JSON via JSONRequest, ArrayRequest ou StringRequest,
     * os dados sao passados para o metodo deliverResponse,
     * sendo deliverResponse responsavel por entregar ao metodo(Response.Listener<JSONArray> response) de sucesso.
     */
    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        String serverResponse = null;
        try {
            // Capturando resposta e guardando em String temporaria para criacao de JSONArray.
            serverResponse = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            // Criando JSONArray utilizando a String temporaria.
            Log.i("Test", "Res: " + new String(response.data, HttpHeaderParser.parseCharset(response.headers)));
            // Criando JSONArray, que recebe a String vinda do Servidor
            JSONArray ja = new JSONArray(serverResponse);
            // Retornando Response<JSONObject> criado a partir do JSONObject
            return Response.success(ja, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.i(AppConstants.LOG_KEY.toString(), "UnsupportedEncodingException" + AppConstants.LOG_KEY_ERROR.toString() + e.getMessage());
        } catch (JSONException e) {
            Log.i(AppConstants.LOG_KEY.toString(), "JSONException" + AppConstants.LOG_KEY_ERROR.toString() + e.getMessage());
        }
        return null;
    }

    /**
     * Metodo recebe o valor vindo do parseNetworkResponse, e eh repsonsavel por entregar os dados ao metodo de sucesso(Listener<JSONArray>)
     *
     * @param response
     */
    @Override
    protected void deliverResponse(JSONArray response) {
        this.response.onResponse(response);
    }
}
