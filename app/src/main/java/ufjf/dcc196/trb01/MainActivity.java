package ufjf.dcc196.trb01;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CADASTROALUNO = 1;
    public final static int REQUEST_CADASTRAREVENTO = 2;

    private Button cadastroAluno;
    private Button cadastroEvento;

    private RecyclerView lstAluno;
    private RecyclerView lstEvento;
    private ParticipanteAdapter alunoAdapter;
    private EventoAdapter eventoAdapter;

    private ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
    private ArrayList<Evento> listaEventos = new ArrayList<Evento>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cadastroAluno = (Button) findViewById(R.id.btn_cadastropessoa);
        cadastroEvento = (Button) findViewById(R.id.btn_cadastroevento);

        cadastroAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alunoPage = new Intent(MainActivity.this, CadastroParticipante.class);
                startActivityForResult(alunoPage, REQUEST_CADASTROALUNO);
            }
        });

        cadastroEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventoPage = new Intent(MainActivity.this, CadastroEvento.class);
                startActivityForResult(eventoPage, REQUEST_CADASTRAREVENTO);
            }
        });


        lstAluno = (RecyclerView) findViewById(R.id.lstAlunos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstAluno.setLayoutManager(linearLayoutManager);
        alunoAdapter = new ParticipanteAdapter(listaAlunos);
        lstAluno.setAdapter(alunoAdapter);


        lstEvento = (RecyclerView) findViewById(R.id.lstEventos);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        lstEvento.setLayoutManager(linearLayoutManager2);
        eventoAdapter = new EventoAdapter(listaEventos);
        lstEvento.setAdapter(eventoAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == MainActivity.RESULT_OK && data != null) {
            Bundle resultado = data.getExtras();
            if (resultado.isEmpty()) {
                return;
            }

            switch (requestCode) {
                case MainActivity.REQUEST_CADASTROALUNO:
                    String nome = resultado.getString("nome");
                    String email = resultado.getString("email");
                    String matricula = resultado.getString("matricula");

                    Aluno novoAluno = new Aluno(nome, email, matricula);
                    this.listaAlunos.add(novoAluno);

                    Toast.makeText(getApplicationContext(), "Aluno Registrado: "+ nome, Toast.LENGTH_LONG).show();
                    break;

                case MainActivity.REQUEST_CADASTRAREVENTO:
                    String titulo = resultado.getString("titulo");
                    String dia = resultado.getString("dia");
                    String hora = resultado.getString("hora");
                    String facilitador = resultado.getString("facilitador");
                    String descricao = resultado.getString("descricao");

                    Evento novoEvento = new Evento(titulo, dia, hora, facilitador, descricao);
                    this.listaEventos.add(novoEvento);

                    Toast.makeText(getApplicationContext(), "Evento Registrado: "+ titulo, Toast.LENGTH_LONG).show();
                    break;

            }
        }
    }
}
