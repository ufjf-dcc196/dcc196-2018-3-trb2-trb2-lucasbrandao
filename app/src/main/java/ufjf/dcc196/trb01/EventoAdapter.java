package ufjf.dcc196.trb01;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static ufjf.dcc196.trb01.MainActivity.REQUEST_EDITAR_EVENTO;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolderEvento> implements View.OnClickListener {

    private List<Evento> dados;

    public EventoAdapter(List<Evento> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderEvento onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.evento_layout, viewGroup, false);
        ViewHolderEvento holderEvento = new ViewHolderEvento(view, viewGroup.getContext());

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderEvento viewHolder, int i) {
        if ((dados != null) && (dados.size() > 0)) {

            Evento evento = dados.get(i);

            viewHolder.txtTitulo.setText(evento.titulo);
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

        public ViewHolderEvento(@NonNull View itemView, final Context context) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloEvento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Dados_Evento.class);
                    ((AppCompatActivity)context).startActivityForResult(intent,REQUEST_EDITAR_EVENTO);
                }
            });
        }
    }

}
