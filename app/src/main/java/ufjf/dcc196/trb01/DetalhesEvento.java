package ufjf.dcc196.trb01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalhesEvento extends AppCompatActivity {

    private TextView titulo;
    private TextView dia;
    private TextView hora;
    private TextView facilitador;
    private TextView descricao;
    private Button btnInscreverEvento;

    private RecyclerView lstParticipantesInscritos;
    private InscritosEventoAdapter participantesInscritosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        titulo = (TextView) findViewById(R.id.txt_dadotitulo);
        dia = (TextView) findViewById(R.id.txt_dadodia);
        hora = (TextView) findViewById(R.id.txt_dadohora);
        facilitador = (TextView) findViewById(R.id.txt_dadofacilitador);
        descricao = (TextView) findViewById(R.id.txt_dadodescricao);
        btnInscreverEvento = (Button) findViewById(R.id.btn_inscreverevento);

        carregarDadosEvento();
        carregarDetalhes();

        // inscreve em evento selecionado;
        btnInscreverEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Evento ev = (Evento) getIntent().getExtras().getSerializable("DADOS_EVENTO");
                Intent data = new Intent();
                data.putExtra("EVENTOSELECIONADO", ev);
                int ePosition = getIntent().getExtras().getInt("EVENTOPOSITION");

                data.putExtra("EVENTOPOSITION", ePosition);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        //exibe lista de inscritos neste evento
        lstParticipantesInscritos = (RecyclerView) findViewById(R.id.lstParticipantesEvento);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        lstParticipantesInscritos.setLayoutManager(linearLayout);
        int position = getIntent().getExtras().getInt("EVENTOPOSITION");
        participantesInscritosAdapter = new InscritosEventoAdapter(MainActivity.listaEventos.get(position).getParticipantesInscritos());
        lstParticipantesInscritos.setAdapter(participantesInscritosAdapter);
        participantesInscritosAdapter.notifyDataSetChanged();

    }

    private void carregarDadosEvento() {
        Bundle dados = getIntent().getExtras();
        if ((dados != null) && (dados.containsKey("DADOS_EVENTO"))) {
            Evento ev = (Evento) dados.getSerializable("DADOS_EVENTO");

            titulo.setText(ev.getTitulo());
            dia.setText(ev.getDia());
            hora.setText(ev.getHora());
            facilitador.setText(ev.getFacilitador());
            descricao.setText(ev.getDescricao());
        }
    }

    // oculta botao de registrar
    public void carregarDetalhes() {
        if (getIntent().getExtras().getBoolean("ISMAINACTIVITY")) {
            btnInscreverEvento.setVisibility(View.GONE);
        }

    }

}
