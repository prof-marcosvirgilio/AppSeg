package br.dev.marcosvirgilio.seg.ui.usuario;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import br.dev.marcosvirgilio.seg.R;
import br.dev.marcosvirgilio.seg.modelo.usuario.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadUsuarioFragment extends Fragment implements View.OnClickListener, Response.ErrorListener, Response.Listener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //volley
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;
    //passar a view como atributo da classe e não do método
    View view;

    // tela
    private Button btSalvar = null;
    private EditText txNome = null;
    private EditText txEmail = null;
    private EditText txSenha = null;

    public CadUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadUsuarioFragment newInstance(String param1, String param2) {
        CadUsuarioFragment fragment = new CadUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_usuario, container, false);

//instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
//inicializando a fila de requests do SO
        this.requestQueue.start();
        //para objeto da classe View com nome = view;
        this.btSalvar = (Button) view.findViewById(R.id.btSalvar);
        this.txNome = (EditText) view.findViewById(R.id.editTextTextPersonName);
        this.txEmail = (EditText) view.findViewById(R.id.editTextTextEmailAddress);
        this.txSenha = (EditText) view.findViewById(R.id.editTextTextPassword);
        //ativando o listener no botão
        this.btSalvar.setOnClickListener(this);

        return this.view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Houve um problema ao realizar o cadastro: " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();

    }

    @Override
    public void onResponse(Object response) {
        String resposta = response.toString();
        try {
            if(resposta.equals("500"))
            {
                Snackbar mensagem = Snackbar.make(view,
                        "Erro! = " + resposta,
                        Snackbar.LENGTH_LONG);
                mensagem.show();
            } else {
                //sucesso
                //limpar campos da tela
                this.txNome.setText("");
                this.txEmail.setText("");
                this.txSenha.setText("");
                //mensagem de sucesso
                Snackbar mensagem = Snackbar.make(view,
                        "Sucesso! = " + resposta,
                        Snackbar.LENGTH_LONG);
                mensagem.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //verificando se é o botão salvar
            case R.id.btSalvar:
                //instanciando objeto de negócio
                Usuario u = new Usuario();
                //populando objeto com dados da tela
                u.setNome(this.txNome.getText().toString());
                u.setEmail(this.txEmail.getText().toString());
                u.setSenha(this.txSenha.getText().toString());
                //enviar objeto para o REST Server
                jsonObjectReq = new JsonObjectRequest(Request.Method.POST,
                        "http://10.0.2.2:8080/segServer/rest/usuario",
                        u.toJsonObject(), this, this);
                requestQueue.add(jsonObjectReq);
                break;
        }
    }
}