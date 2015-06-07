package jp.personal.pakuqi.chatworktrial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.personal.pakuqi.chatworktrial.javabeans.RoomBean;

public class LoginActivity extends AppCompatActivity {

    //ベースのURL
    static final String BASEURL = "https://api.chatwork.com/v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //レイアウトフィルの設定
        setContentView(R.layout.activity_login_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //メニューの生成
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //メニューを押した時の処理
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //ログインボタン押下時のコールバックルーチン
    public void onOkClicked(String apiToken) {

        //Fragmentでログインボタンが押された
        //自分のチャットルーム一覧を取得する

        //チャットルーム一覧データを格納するArrayList
        final ArrayList<RoomBean> countries = new ArrayList<RoomBean>();

        //URLを生成
        String roomListUrl = BASEURL + "/rooms";

        //Volleyのオブジェクト生成
        RequestQueue mQueue = Volley.newRequestQueue(this);

        //VolleyオブジェクトにJsonArrayRequestを追加してデータを取得する
        mQueue.add(new JsonArrayRequest(roomListUrl,
                           new Response.Listener<JSONArray>() {
                               @Override
                               public void onResponse(JSONArray response) {
                                    //正常終了時のコールバック

                                   //取得したJSONオブジェクトの取り出し
                                   for (int i = 0; i < response.length(); i++) {
                                       try {
                                           JSONObject jo = response.getJSONObject(i);
                                           int roomId = jo.getInt("room_id");           //部屋ID
                                           String name = jo.getString("name");          //部屋の名前
                                           String iConPath = jo.getString("icon_path"); //アイコンのパス

                                           //取り出したデータを保存
                                           countries.add(new RoomBean(roomId, name, iConPath));

                                           //取り出したデータを内部にキャッシュする

                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }

                                   //チャットルーム一覧を表示するActivityを起動する
                                   //取得したチャットルームの一覧データ配列を渡す
                                   Intent intent = new Intent(getApplicationContext(), ChatRoomList.class);
                                   intent.putExtra("jp.personal.pakuqi.chatworktrial.roomObject", countries);
                                   startActivity(intent);
                               }
                           },

                           new Response.ErrorListener() {
                               @Override public void onErrorResponse(VolleyError error) {
                                   //エラーが発生した場合のコールバック

                                   //キャッシュしたデータがあれば取り出してチャットルーム一覧を表示する

                               }
                           }){
                       @Override
                       public Map<String, String> getHeaders() throws AuthFailureError {
                           //HTTPヘッダにAPI Tokenを設定する
                           String aPItOKEN = "8ffe5c96620d3cc431cfc1dd271736b9"; //仮
                           Map<String, String> headers = super.getHeaders();
                           Map<String, String> newHeaders = new HashMap<String, String>();
                           newHeaders.put("X-ChatWorkToken", aPItOKEN);
                           return newHeaders;
                       }
                   }
        );

    }
}
