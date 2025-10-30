package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView chat_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chat_list_view = findViewById(R.id.chat_list);

        List<Map<String, String>> chat_data = new ArrayList<>();

        String[] names = {"Мама", "Папа", "Диана", "Алина"};
        String[] times = {"10:42", "09:15", "Вчера", "Пн"};
        String[] messages = {
                "Ты поела? Не забудь зонт.",
                "Не забудь позвонить бабушке.",
                "Завтра увидимся в университете?",
                "Как ты себя чувствуешь сегодня?"
        };
        String[] unread = {"2", "0", "3", "1"};

        for (int i = 0; i < names.length; i++) {
            Map<String, String> chat = new HashMap<>();
            chat.put("name", names[i]);
            chat.put("time", times[i]);
            chat.put("message", messages[i]);
            chat.put("unread", unread[i]);
            chat_data.add(chat);
        }

        String[] from = {"name", "time", "message", "unread"};
        int[] to = {R.id.name, R.id.time, R.id.message, R.id.unread_badge};

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                chat_data,
                R.layout.chat_item,
                from,
                to
        );

        chat_list_view.setAdapter(adapter);

        chat_list_view.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            String chat_name = names[position];
            Toast.makeText(this, "Открыт чат: " + chat_name, Toast.LENGTH_SHORT).show();
        });
    }
}
