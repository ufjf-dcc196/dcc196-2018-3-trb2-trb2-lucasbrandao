package ufjf.dcc196.trb01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CadastroEvento extends AppCompatActivity {

    private Button registrarEvento;
    private TextView tituloEvento;
    private TextView diaEvento;
    private TextView horaEvento;
    private TextView facilitadorEvento;
    private TextView descricaoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        registrarEvento = findViewById(R.id.btn_confirmaevento);
        tituloEvento = findViewById(R.id.txt_titulo);
        diaEvento = findViewById(R.id.txt_dia);
        horaEvento = findViewById(R.id.txt_hora);
        facilitadorEvento = findViewById(R.id.txt_facilitador);
        descricaoEvento = findViewById(R.id.txt_descricao);

        registrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resposta = new Intent();

                String titulo = tituloEvento.getText().toString();
                String dia = diaEvento.getText().toString();
                String hora = horaEvento.getText().toString();
                String facilitador = facilitadorEvento.getText().toString();
                String descricao = descricaoEvento.getText().toString();

                resposta.putExtra("titulo", titulo);
                resposta.putExtra("dia", dia);
                resposta.putExtra("hora", hora);
                resposta.putExtra("facilitador", facilitador);
                resposta.putExtra("descricao", descricao);

                if (!titulo.isEmpty() && !dia.isEmpty() && !hora.isEmpty() && !facilitador.isEmpty() && !descricao.isEmpty()) {
                    setResult(RESULT_OK, resposta);
                    finish();
                }

                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
