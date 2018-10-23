package ufjf.dcc196.trb01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EventosInscritosAdapter extends RecyclerView.Adapter<EventosInscritosAdapter.ViewHolderEventosInscritos> implements View.OnClickListener {

    private List<Evento> eventosInscritos;

    public EventosInscritosAdapter(List<Evento> inscritos) {
        this.eventosInscritos = inscritos;
    }

    @NonNull
    @Override
    public EventosInscritosAdapter.ViewHolderEventosInscritos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.evento_layout, viewGroup, false);
        ViewHolderEventosInscritos holderEvento = new ViewHolderEventosInscritos(view, viewGroup.getContext());

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventosInscritosAdapter.ViewHolderEventosInscritos viewHolder, int i) {

        if ((eventosInscritos != null) && (eventosInscritos.size() > 0)) {
            Evento evento = eventosInscritos.get(i);
            viewHolder.txtTitulo.setText(evento.getTitulo());
        }
    }

    @Override
    public int getItemCount() {
        return eventosInscritos.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolderEventosInscritos extends RecyclerView.ViewHolder {

        public TextView txtTitulo;


        public ViewHolderEventosInscritos(@NonNull final View itemView, final Context context) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloEvento);


            // clique longo para cancelar inscricao
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if (eventosInscritos.size() > 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Cancelar a inscrição neste evento?")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        String nomeAluno = MainActivity.listaAlunos.get(MainActivity.alunoPosition).getNome();
                                        String nomeEvento = MainActivity.listaAlunos.get(MainActivity.alunoPosition).getEvento(getLayoutPosition()).getTitulo();

                                        // cancela inscricao na lista Alunos
                                        MainActivity.listaAlunos.get(MainActivity.alunoPosition).cancelarInscricao(getLayoutPosition());
                                        Toast.makeText(context, "Inscrição cancelada",Toast.LENGTH_LONG).show();
                                        notifyDataSetChanged();

                                        // cancela inscricao na lista Eventos
                                        for (i=0; i<MainActivity.listaEventos.size(); i++) {
                                            if (nomeEvento.equals(MainActivity.listaEventos.get(i).getTitulo())) {
                                                for (int j=0; j<MainActivity.listaEventos.get(i).getParticipantesInscritos().size(); j++) {
                                                    if (nomeAluno.equals(MainActivity.listaEventos.get(i).getParticipantesInscritos().get(j).getNome())) {
                                                        MainActivity.listaEventos.get(i).cancelaInscricaoParticipante(j);
                                                    }
                                                }
                                            }
                                        }


                                        dialogInterface.dismiss();
                                    }
                                })
                                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog alerta = builder.create();
                        alerta.setTitle("CANCELAR INSCRIÇÃO");
                        alerta.show();
                    }

                    return false;
                }
            });

        }
    }


}
