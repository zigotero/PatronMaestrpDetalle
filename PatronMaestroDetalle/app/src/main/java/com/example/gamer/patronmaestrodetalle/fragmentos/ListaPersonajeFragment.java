package com.example.gamer.patronmaestrodetalle.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gamer.patronmaestrodetalle.R;
import com.example.gamer.patronmaestrodetalle.adaptadores.AdaptadorPersonajes;
import com.example.gamer.patronmaestrodetalle.entidades.Personajes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListaPersonajeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<Personajes> listapersonajes;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListaPersonajeFragment() {
        // Required empty public constructor
    }


    public static ListaPersonajeFragment newInstance(String param1, String param2) {
        ListaPersonajeFragment fragment = new ListaPersonajeFragment();
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
        View v=inflater.inflate(R.layout.fragment_lista_personaje, container, false);
       listapersonajes = new ArrayList<>();
        ButterKnife.bind(this,v);

        reciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarlistapersonajes();
        AdaptadorPersonajes miadapter = new AdaptadorPersonajes(listapersonajes);

        miadapter.setonclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"seleccionado: "+listapersonajes.get(reciclador.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();

            }
        });
        reciclador.setAdapter(miadapter);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void llenarlistapersonajes(){
        listapersonajes.add(new Personajes("Goku","te pega o te le pegas , se convierte en super tallarin y te saca la recsm",R.drawable.goku_cara));
        listapersonajes.add(new Personajes("Vegueta","se cree macho  te le pegas ,y se pone a llorar como zuripanta",R.drawable.vegueta_cara));
        listapersonajes.add(new Personajes("Gohan","es gay y lo violo picoro",R.drawable.gohan_cara));
        listapersonajes.add(new Personajes("Picoro","violo a gohan",R.drawable.picoro_cara));
        listapersonajes.add(new Personajes("Krilin","el keni de DBZ",R.drawable.krilin_cara));

        listapersonajes.add(new Personajes("Trunks","violado por 17 y 18 del fituro alterno",R.drawable.trunks_cara));

    }
}
