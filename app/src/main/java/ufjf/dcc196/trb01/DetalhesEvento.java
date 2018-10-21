package ufjf.dcc196.trb01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesEvento extends AppCompatActivity {

    private TextView titulo;
    private TextView dia;
    private TextView hora;
    private TextView facilitador;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        titulo = (TextView) findViewById(R.id.txt_dadotitulo);
        dia = (TextView) findViewById(R.id.txt_dadodia);
        hora = (TextView) findViewById(R.id.txt_dadohora);
        facilitador = (TextView) findViewById(R.id.txt_dadofacilitador);
        descricao = (TextView) findViewById(R.id.txt_dadodescricao);

        carregarDadosEvento();

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



}
