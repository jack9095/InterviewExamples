package custom.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.annotation.R;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import custom.eventbus.annotation.Subscribe;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MethodManager methodManager = new MethodManager();
        WeakReference<MethodManager> str = new WeakReference<MethodManager>(methodManager);
        setContentView(R.layout.activity_two);
        EventBus.getInstance().register(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getInstance().post("王菲");

                finish();
            }
        });
    }

    @Subscribe(value = ThreadMode.MAIN,stick = true)
    public void eventMainThread(Object obj){
        Log.e("TwoActivity",obj + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }
}
