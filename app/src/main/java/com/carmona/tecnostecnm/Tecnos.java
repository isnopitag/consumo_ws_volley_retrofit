package com.carmona.tecnostecnm;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tecnos extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager adminLayout;

    private RequestQueue colaSolicitudes;
    private List<Tecs> listaTecnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnos);

        listaTecnos = new ArrayList<>();
        colaSolicitudes = Volley.newRequestQueue(this);
        recyclerView = (RecyclerView) findViewById(R.id.reciclerview);
        recyclerView.setHasFixedSize(true); //Ajusta Los elementos que contiene el reciclador

        getTecnologicos();
        adminLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(adminLayout);
    }

    private void getTecnologicos(){
        String URL = "http://ws.itcelaya.edu.mx:8080/intertecs/apirest/institucion/listado";
        final StringRequest solicitudListaTecnos = new StringRequest(Request.Method.GET,URL,this,this){
            @Override
            public Map<String,String> getHeaders(){
              Map<String, String> params = new HashMap<String,String>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(String.format("%s:%s", "intertecs","1nt3rt3c5").getBytes(),Base64.DEFAULT)));
                return params;
            }
        };
        colaSolicitudes.add(solicitudListaTecnos);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Tecs tecno;
        try {
            JSONObject objJSON = new JSONObject(response);
            JSONArray arrayJSON = objJSON.getJSONArray("institucion");
            for (int i=0; i<arrayJSON.length();i++){
                JSONObject objJSONTecno = arrayJSON.getJSONObject(i);
                tecno= new Tecs();
                tecno.setIdTecno(objJSONTecno.getInt("id_institucion"));
                tecno.setNombreTecno(objJSONTecno.getString("institucion"));
                tecno.setNombreCortoTecno(objJSONTecno.getString("nombre_corto"));
                tecno.setLogoTecno(objJSONTecno.getString("logotipo"));
                listaTecnos.add(tecno);
            }
            adaptador = new TecnosAdapter(listaTecnos,this);
            recyclerView.setAdapter(adaptador);
        }catch (JSONException e){
            Log.e("Error",e.toString());
        }
    }
}
