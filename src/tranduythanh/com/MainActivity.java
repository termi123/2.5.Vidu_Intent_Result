package tranduythanh.com;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	public static final int REQUEST_CODE_INPUT=113;
	public static final int RESULT_CODE_SAVE1=115;
	public static final int RESULT_CODE_SAVE2=116;
	Button btnInputData;
	ListView lvData;
	ArrayList<Integer>arrData=new ArrayList<Integer>();
	ArrayAdapter<Integer>adapter=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnInputData =(Button) findViewById(R.id.btnopenactivity);
		btnInputData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Mở Activity với REQUEST_CODE_INPUT
				Intent intent=new Intent(MainActivity.this, InputDataActivity.class);
				//gọi startActivityForResult
				startActivityForResult(intent, REQUEST_CODE_INPUT);
			}
		});
		//đoạn code dưới này học nhiều rồi, ko nói lại
		lvData=(ListView) findViewById(R.id.lvdata);
		adapter=new ArrayAdapter<Integer>
			(this, 
			android.R.layout.simple_list_item_1, 
			arrData);
		lvData.setAdapter(adapter);
	}
	/**
	 * Xử lý kết quả trả về ở đây
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//Kiểm tra có đúng requestCode =REQUEST_CODE_INPUT hay không
		//Vì ta có thể mở Activity với những RequestCode khác nhau
		if(requestCode==REQUEST_CODE_INPUT)
		{
			//Kiểm trả ResultCode trả về, cái này ở bên InputDataActivity truyền về
			//có nó rồi thì xử lý trở lên thông thường
			switch(resultCode)
			{
			case RESULT_CODE_SAVE1:
				//giá trị từ InputDataActivity
				int v1= data.getIntExtra("data", 0);
				arrData.add(v1*v1);
				adapter.notifyDataSetChanged();
				break;
			case RESULT_CODE_SAVE2:
				//giá trị từ InputDataActivity
				int v2= data.getIntExtra("data", 0);
				arrData.add(v2);
				adapter.notifyDataSetChanged();
				break;
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
