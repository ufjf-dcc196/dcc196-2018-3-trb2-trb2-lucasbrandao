package ufjf.dcc196.trb01;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetalhesAluno extends AppCompatActivity {

    private TextView nome;
    private TextView email;
    private TextView matricula;
    private Button  alterarDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_aluno);

        nome = (TextView) findViewById(R.id.txt_dadonomealuno);
        email = (TextView) findViewById(R.id.txt_dadoemail);
        matricula = (TextView) findViewById(R.id.txt_dadomatricula);
        alterarDados = (Button) findViewById(R.id.btn_alterardados);

        carregarDadosAluno();

        alterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alterardadosPage = new Intent(DetalhesAluno.this, CadastroParticipante.class);
                alterardadosPage.putExtra("REQUEST_ALTERARDADOS", MainActivity.REQUEST_ALTERARDADOSALUNO);
                alterardadosPage.putExtra("NOMEATUAL", nome.getText().toString());
                alterardadosPage.putExtra("EMAILATUAL", email.getText().toString());
                startActivityForResult(alterardadosPage, MainActivity.REQUEST_ALTERARDADOSALUNO);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == CadastroParticipante.RESULT_CANCELED) {
            return;
        }

        Bundle resultado = data.getExtras();

        switch (requestCode) {
            case MainActivity.REQUEST_ALTERARDADOSALUNO:
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");


                // alterando na lista de alunos.
                int position = getIntent().getExtras().getInt("ALUNOPOSITION");
                MainActivity.alterarDadosParticipante(position, nome, email);

                // alterando nome na activity detalhesaluno
                this.nome.setText(nome);
                this.email.setText(email);
                Toast.makeText(getApplicationContext(),"Dados Alterados.", Toast.LENGTH_LONG).show();

                break;
        }
    }
}
