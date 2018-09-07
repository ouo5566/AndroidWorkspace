package app.rstone.com.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static app.rstone.com.contactsapp.Main.MEMADDR;
import static app.rstone.com.contactsapp.Main.MEMEMAIL;
import static app.rstone.com.contactsapp.Main.MEMNAME;
import static app.rstone.com.contactsapp.Main.MEMPHONE;
import static app.rstone.com.contactsapp.Main.MEMPHOTO;
import static app.rstone.com.contactsapp.Main.MEMSEQ;

public class MemberUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_update);
        final Context this__ = MemberUpdate.this;
        Intent intent = getIntent();
        String seq = intent.getExtras().getString(MEMSEQ);
        MemberDetail.ItemDetail detailQuery = new MemberDetail.ItemDetail(this__);
        detailQuery.seq = seq;
        Main.Member m = (Main.Member) new Main.DetailService() {
            @Override
            public Object perform() {
                return detailQuery.execute();
            }
        }.perform();

        ImageView profile = findViewById(R.id.profile);
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.changeEmail);
        EditText addr = findViewById(R.id.changeAddress);
        EditText phone = findViewById(R.id.changePhone);

        profile.setImageDrawable(getResources().getDrawable(
                                                        getResources().getIdentifier(
                                                        this.getPackageName()+":drawable/"+m.photo
                                                        ,null,null)
                                                , this__.getTheme()));
        name.setText(m.name);
        email.setText(m.email);
        addr.setText(m.addr);
        phone.setText(m.phone);

        findViewById(R.id.imgBtn).setOnClickListener(
                (View v)->{
                    Toast.makeText(this__,"구현 전",Toast.LENGTH_SHORT).show();
                }
        );
        findViewById(R.id.confirmBtn).setOnClickListener(
                (View v)->{
                    ItemUpdate query = new ItemUpdate(this__);
                    query.m.phone = ((phone.getText()+"").equals(""))? m.phone : phone.getText()+"";
                    query.m.email = ((email.getText()+"").equals(""))? m.email : email.getText()+"";
                    query.m.addr = ((addr.getText()+"").equals(""))? m.addr : addr.getText()+"";
                    query.m.name = ((name.getText()+"").equals(""))? m.name : name.getText()+"";
                    query.m.photo = m.photo;
                    query.m.seq = Integer.parseInt(seq);
                    new Main.StatusService() {
                        @Override
                        public void perform() {
                            query.execute();

                        }
                    }.perform();
                    Intent i = new Intent(this__, MemberDetail.class);
                    i.putExtra(MEMSEQ, seq+"");
                    startActivity(i);
                }
        );
        findViewById(R.id.cancelBtn).setOnClickListener(
                (View v)->{
                    Intent i = new Intent(this__, MemberDetail.class);
                    i.putExtra(MEMSEQ, seq+"");
                    startActivity(i);
                }
        );
    }
    private class MemberUpdateQuery extends Main.QueryFactory{
        SQLiteOpenHelper helper;
        public MemberUpdateQuery(Context _this) {
            super(_this);
            helper = new Main.SQLiteHelper(_this);
        }

        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getWritableDatabase();
        }
    }
    private class ItemUpdate extends MemberUpdateQuery{
        Main.Member m;
        public ItemUpdate(Context _this) {
            super(_this);
            m = new Main.Member();
        }
        public void execute(){
            String sql = String.format(
                    " UPDATE MEMBER SET " +
                            " %s = '%s', " +
                            " %s = '%s', " +
                            " %s = '%s', " +
                            " %s = '%s', " +
                            " %s = '%s' " +
                            " WHERE SEQ LIKE '%s' "
                    , MEMEMAIL, m.email, MEMADDR, m.addr
                    , MEMPHONE, m.phone, MEMPHOTO, m.photo
                    , MEMNAME,  m.name, m.seq);
            Log.d("SQL문 :: ",sql);
            this.getDatabase().execSQL(sql);
        }
    }

}
