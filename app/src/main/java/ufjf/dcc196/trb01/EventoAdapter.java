package ufjf.dcc196.trb01;

import android.content.Context;
import android.content.Intent;
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


public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolderEvento> implements View.OnClickListener {

    private List<Evento> dados;
    private Boolean isMainActivity;

    public EventoAdapter(List<Evento> dados, Boolean isMainActivity) {
        this.dados = dados;
        this.isMainActivity = isMainActivity;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderEvento onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.evento_layout, viewGroup, false);
        ViewHolderEvento holderEvento = new ViewHolderEvento(view, viewGroup.getContext(), isMainActivity);

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderEvento viewHolder, int i) {
        if ((dados != null) && (dados.size() > 0)) {

            Evento evento = dados.get(i);

            viewHolder.txtTitulo.setText(evento.getTitulo());
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolderEvento extends RecyclerView.ViewHolder {

        public TextView txtTitulo;

        public ViewHolderEvento(@NonNull final View itemView, final Context context, Boolean mainAct) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloEvento);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {
                        Evento ev = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, DetalhesEvento.class);
                        intent.putExtra("DADOS_EVENTO", ev);
                        intent.putExtra("EVENTOPOSITION", getLayoutPosition());
                        if (isMainActivity) {
                            intent.putExtra("ISMAINACTIVITY", true);
                            ((AppCompatActivity) context).startActivityForResult(intent, MainActivity.REQUEST_DETALHESEVENTO);
                        } else {
                            intent.putExtra("ISMAINACTIVITY", false);
                            ((AppCompatActivity) context).startActivityForResult(intent, MainActivity.REQUEST_INSCREVERNOEVENTO);
                        }

                    }

                }
            });

        }
    }

}
