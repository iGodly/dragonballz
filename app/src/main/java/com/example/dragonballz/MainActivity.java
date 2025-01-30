package com.example.dragonballz;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        List<Character> characterList = getCharacterList();
        adapter = new CharacterAdapter(characterList, this, character ->
                Toast.makeText(MainActivity.this, "Clicked: " + character.getName(), Toast.LENGTH_SHORT).show()
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }
    private List<Character> getCharacterList() {
        List<Character> list = new ArrayList<>();
        list.add(new Character("Goku", "The main protagonist of the series. A Saiyan raised on Earth who protects the planet.", R.drawable.goku));
        list.add(new Character("Vegeta", "The proud Saiyan prince who strives to surpass Goku.", R.drawable.vegeta));
        list.add(new Character("Gohan", "Goku's eldest son who possesses immense potential.", R.drawable.gohan));
        list.add(new Character("Piccolo", "A wise and powerful Namekian warrior and Gohan's mentor.", R.drawable.piccolo));
        list.add(new Character("Krillin", "Goku's best friend and a skilled martial artist.", R.drawable.krillin));
        list.add(new Character("Bulma", "A brilliant scientist and Goku's close friend.", R.drawable.bulma));
        list.add(new Character("Trunks", "Vegeta's son from the future, who helps save the world.", R.drawable.trunks));
        list.add(new Character("Frieza", "A ruthless galactic emperor and one of Goku's greatest foes.", R.drawable.frieza));
        list.add(new Character("Cell", "A bio-engineered android created from the DNA of powerful fighters.", R.drawable.cell));
        list.add(new Character("Majin Buu", "A magical, shape-shifting creature with incredible power.", R.drawable.majin_buu));
        return list;
    }
}