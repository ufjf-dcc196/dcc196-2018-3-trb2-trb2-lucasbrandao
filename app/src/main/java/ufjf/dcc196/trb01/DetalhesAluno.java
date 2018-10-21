package ufjf.dcc196.trb01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesAluno extends AppCompatActivity {

    private TextView nome;
    private TextView email;
    private TextView matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_aluno);

        nome = (TextView) findViewById(R.id.txt_dadonomealuno);
        email = (TextView) findViewById(R.id.txt_dadoemail);
        matricula = (TextView) findViewById(R.id.txt_dadomatricula);


        carregarDadosAluno();
    }

    private void carregarDadosAluno() {
        Bundle dados = getIntent().getExtras();
        if ((dados != null) && (dados.containsKey("DADOS_PARTICIPANTE"))) {
            Aluno participante = (Aluno) dados.getSerializable("DADOS_PARTICIPANTE");

            nome.setText(participante.getNome());
            email.setText(participante.getEmail());
            matricula.setText(participante.getMatricula());
        }
    }

    private void carregarDadosAluno2() {
        nome.setText("lucas");
        email.setText("lucazbrandao@gmail.com");
        matricula.setText("3094828484AC");
    }

}
