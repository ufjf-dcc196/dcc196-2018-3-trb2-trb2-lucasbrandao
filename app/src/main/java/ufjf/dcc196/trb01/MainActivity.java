package ufjf.dcc196.trb01;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CADASTROALUNO = 1;
    public final static int REQUEST_CADASTRAREVENTO = 2;
    public final static int REQUEST_DETALHESPARTICIPANTE = 3;
    public final static int REQUEST_DETALHESEVENTO = 4;
    public final static int REQUEST_ALTERARDADOSALUNO = 5;
    public final static int REQUEST_INSCREVERNOEVENTO = 6;

    // variavel para poder excluir inscricao com toque longo
    public static int alunoPosition = 0;

    private Button cadastroAluno;
    private Button cadastroEvento;

    private static RecyclerView lstAluno;
    public static RecyclerView lstEvento;
    private static ParticipanteAdapter alunoAdapter;
    public static EventoAdapter eventoAdapter;

    public SQLiteDatabase conection;
    public participanteBD participante_bd;
    public bdController bdcontroller;

    public static ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
    public static ArrayList<Evento> listaEventos = new ArrayList<Evento>();


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
                alunoPage.putExtra("REQUEST_ALTERARDADOS", 0);
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
        eventoAdapter = new EventoAdapter(listaEventos, true);
        lstEvento.setAdapter(eventoAdapter);

        createConection();
        criarListaParticipantes();
        carregarListaAlunos();
        this.carregaListaEventos();


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
                    Integer matricula = resultado.getInt("matricula");

                    //verificar se matricula já está cadastrada
                    for (int i=0; i<listaAlunos.size(); i++){
                        if (matricula.equals(listaAlunos.get(i).getMatricula())){
                            Toast.makeText(getApplicationContext(), "Este número de matrícula já foi cadastrado", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    Aluno novoAluno = new Aluno(nome, email, matricula);
                    bdcontroller.insertParticipante(novoAluno);
                    alunoAdapter.notifyDataSetChanged();

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

    private void createConection() {
        try {
            participante_bd = new participanteBD(this);

            conection = participante_bd.getWritableDatabase();

            Log.d("participanteBD", "createConection: sucessfull");
            bdcontroller = new bdController(conection);

        } catch (SQLException e) {
            Log.d("participanteBD", "createConection: fail");
        }
    }

    public void criarListaParticipantes() {
        bdcontroller.insertParticipante(new Aluno("Amélia Godoy","amelia@gmail.com",45454545));
        bdcontroller.insertParticipante(new Aluno("Bartolomeu","bartolomeu@gmail.com",201551));
        bdcontroller.insertParticipante(new Aluno("Catarino Grilo","catarino@gmail.com",201552));
        bdcontroller.insertParticipante(new Aluno("Ermelinda Méndez","ermelinda@gmail.com",201553));
        bdcontroller.insertParticipante(new Aluno("Hermígio Capanema","capanema@gmail.com",201554));
        bdcontroller.insertParticipante(new Aluno("Noé Hernández","noeh@gmail.com",201555));
        bdcontroller.insertParticipante(new Aluno("Nídia Simão","nidias@gmail.com",201556));
        bdcontroller.insertParticipante(new Aluno("Poliana Rebello","polianarebello@gmail.com",201557));
        bdcontroller.insertParticipante(new Aluno("Silvana Girão","giraosilvana@gmail.com",201558));
    }

    public void carregarListaAlunos() {
        bdcontroller.loadList();
    }

    public void carregaListaEventos() {
        this.listaEventos.add(new Evento("Maratona de Programação","24/10/2018","17:00","Professor","Maratona..."));
        this.listaEventos.add(new Evento("Maratona de Programação2","25/10/2018","17:00","Professor","Maratona..."));
        this.listaEventos.add(new Evento("Palestra Segurança da Informação","25/10/2018","14:00","Professor","Palestra..."));
        this.listaEventos.add(new Evento("Vacathon","24/10/2018","17:00","Professor","Palestra..."));
        this.listaEventos.add(new Evento("Mesa Redonda","24/10/2018","17:00","Professor","Evento..."));
        this.listaEventos.add(new Evento("Palestra Banco de Dados NOSQL","24/10/2018","17:00","Professor","Palestra..."));
    }

    public static void alterarDadosParticipante(int position, String nome, String email) {
        listaAlunos.get(position).alterarDados(nome, email);

        // atualiza recyclerview
        alunoAdapter.notifyDataSetChanged();
    }

}
