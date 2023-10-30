package br.dev.marcosvirgilio.seg.ui.menu;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import br.dev.marcosvirgilio.seg.R;
import br.dev.marcosvirgilio.seg.databinding.FragmentMenuItemBinding;
import br.dev.marcosvirgilio.seg.modelo.menu.ItemMenu;

import java.lang.reflect.Field;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ItemMenu}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {

    private final List<ItemMenu> mValues;

    public MenuRecyclerViewAdapter(List<ItemMenu> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentMenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNome());
        //holder.mContentView.setText(mValues.get(position).getNavegacao());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public final TextView mIdView;
        public final TextView mContentView;
        public ItemMenu mItem;

        public ViewHolder(@NonNull FragmentMenuItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            //CLICK - pegar posicao que foi clicada
            int adapterposition = this.getLayoutPosition();
            //mostrar posição clicada
            Context context =  view.getContext();
            /*
            //chamar navegação - mandeira fixa no fonte
            switch (adapterposition){
                case 0 : Navigation.findNavController(view).navigate(R.id.navigation_dashboard); break;
                case 1 : Navigation.findNavController(view).navigate(R.id.navigation_notifications); break;
            }
            */

            //chamar navegação - maneira dinâmica
            int navId = 0;
            try {
                Class resource = R.id.class;
                //Field[] fields = res.getDeclaredFields();
                ItemMenu menuClicado = mValues.get(adapterposition);
                Field field = resource.getField(menuClicado.getNavegacao());
                navId = field.getInt(null);
                if (navId != 0) {
                    Navigation.findNavController(view).navigate(navId);
                } else {
                    Snackbar mensagem = Snackbar.make(view, "Ops! Houve um problema ao abrir a tela.", Snackbar.LENGTH_LONG);
                    mensagem.show();
                }
            }catch (Exception e) {
                Snackbar mensagem = Snackbar.make(view, "Ops! " + e.getMessage(), Snackbar.LENGTH_LONG);
                mensagem.show();
            }

        }
    }
}