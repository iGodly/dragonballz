package com.example.dragonballz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characterList;
    private List<Character> filteredList;
    private Context context;
    private OnCharacterClickListener listener;
    public CharacterAdapter(List<Character> characterList, Context context, OnCharacterClickListener listener) {
        this.characterList = characterList;
        this.filteredList = new ArrayList<>(characterList);
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = filteredList.get(position);
        holder.nameTextView.setText(character.getName());
        holder.descriptionTextView.setText(character.getDescription());
        holder.imageView.setImageResource(character.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(context);
            View toastView = inflater.inflate(R.layout.custom_toast,null);
            ImageView toastImageView = toastView.findViewById(R.id.toastImageView);
            TextView toastTextView = toastView.findViewById(R.id.toastTextView);
            toastImageView.setImageResource(character.getImageResId());

            //We made the characters name BOLD (That's an extra on our opinion)
            String message = "You selected <b>" + character.getName() + "!</b>";
            toastTextView.setText(android.text.Html.fromHtml(message, android.text.Html.FROM_HTML_MODE_LEGACY));

            Toast toast = new Toast(context);
            toast.setView(toastView);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(characterList);
        } else {
            for (Character character : characterList) {
                if (character.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(character);
                }
            }
        }
        notifyDataSetChanged();
    }
    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        ImageView imageView;
        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
    public interface OnCharacterClickListener {
        void onCharacterClick(Character character);
    }
}
