package apertx.pic10f200;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.graphics.*;
import android.content.*;
import java.util.*;
import android.view.View.*;
import java.io.*;
import android.widget.TextView.*;

public class MainActivity extends Activity {
	int [] dict = {62122768,62420678,2071645,2071662,2074373,2094244,2012571147,2251848,-2130938705,69852091,2372562,73550019,77487,81228,81414,79250671,79309075,83704906,65573,66069,63524740,63524756,62420354,2060894,1990869374,2193763,69851767,73549695,-1956807563,77867468,78984887,2583592,83704582,0};
	int[] opcode = {7,5,1,64,9,3,11,10,15,4,8,1,0,13,12,2,14,6,4,5,6,7,14,9,8,5,13,12,2,8,3,0,15};
	List<Integer> bytecode = new ArrayList<Integer>();
	List<String> label = new ArrayList<String>();
	List<Byte> laddr = new ArrayList<Byte>();
	EditText text;

	class _editor {
		float textSize;
		int textColor;
		long backPress;
		int backup;
	}
	_editor editor = new _editor();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences settings = getSharedPreferences("settings", this.MODE_PRIVATE);
		editor.textSize = settings.getFloat("textSize", 18);
		editor.textColor = settings.getInt("textColor", Color.CYAN);
		setTheme(settings.getInt("theme", android.R.style.Theme_Material));
		super.onCreate(savedInstanceState);
		text = new EditText(this);
		text.setGravity(Gravity.TOP);
		text.setTypeface(Typeface.MONOSPACE);
		text.setTextSize(editor.textSize);
		text.setTextColor(editor.textColor);
		text.setBackgroundColor(Color.TRANSPARENT);
		text.setHorizontallyScrolling(true);
		setContentView(text);
		editor.backup = text.getText().hashCode();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 10, Menu.NONE, "Compile");
		menu.add(Menu.NONE, 11, Menu.NONE, "Open");
		menu.add(Menu.NONE, 12, Menu.NONE, "Save");
		menu.add(Menu.NONE, 13, Menu.NONE, "Export");
		menu.add(Menu.NONE, 14, Menu.NONE, "Disassemble");
		menu.add(Menu.NONE, 15, Menu.NONE, "Find");
		menu.add(Menu.NONE, 16, Menu.NONE, "Settings");
		menu.add(Menu.NONE, 17, Menu.NONE, "Instruction set");
		menu.add(Menu.NONE, 18, Menu.NONE, "Help");
		menu.add(Menu.NONE, 19, Menu.NONE, "Exit");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case 10:
				build(text.getText().toString().trim());
				break;
			case 3:
				save();
				break;
			case 17:
				startActivity(new Intent(MainActivity.this, InstructionActivity.class));
				break;
			case 19:
				if (editor.backup != text.getText().hashCode())
					new AlertDialog.Builder(MainActivity.this).
						setTitle("Save file?").
						setMessage("Save modified open file?").
						setPositiveButton("Yes", new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface p1, int p2) {
								save();
								finish();
							}
						}).
						setNegativeButton("No", new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface p1, int p2) {
								finish();
							}
						}).show();
				else
					finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	void build(String p0) {
		if (p0.contains(" ")) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; (i < p0.length() - 1) && (p0.charAt(i) != ' '); i++)
				sb.append(p0.charAt(i));
			String p1 = sb.toString();
		} else
			bytecode.add(assemble(p0, "", ""));
	}

	int assemble(String p0, String p1, String p2) {
		int ret = -1;
		byte code;
		for (code = 0; p0.hashCode() != dict[code] && code < 33; code++);
		if (code == 3 || code == 12 || code == 24 || code == 30 || code == 31)
			ret = opcode[code];
		else if (code == 2 || code == 11)
			ret = (opcode[code] << 5) + Integer.parseInt(p1);
		else if (code == 25) {
			if (p1.charAt(0) < '0' && p1.charAt(0) > '9') {
				p1 = laddr.get(label.indexOf(p1)).toString();
			}
			ret = (opcode[code] << 9) + Integer.parseInt(p1);
		} else if (code == 31)
			ret = (opcode[code] << 3) + Integer.parseInt(p1);
		else if (code <= 17) {
			byte dist = 0;
			if (p2 == "F")
				dist = 32;
			ret = (opcode[code] << 6) + Integer.parseInt(p1) + dist;
		} else if (code <= 21)
			ret = (opcode[code] << 8) + Integer.parseInt(p1) + (Integer.parseInt(p2) << 5);
		else if (code <= 32)
			ret = (opcode[code] << 8) + Integer.parseInt(p1);
		return ret;
	}

	void save() {
		try {
			editor.backup = text.getText().hashCode();
		} catch (Exception e) {}
	}

	@Override
	public void onBackPressed() {
		if (editor.backPress + 2000 > System.currentTimeMillis())
			if (editor.backup != text.getText().hashCode())
				new AlertDialog.Builder(MainActivity.this).
					setTitle("Save file?").
					setMessage("Save modified open file?").
					setPositiveButton("Yes", new AlertDialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface p1, int p2) {
							save();
							finish();
						}
					}).
					setNegativeButton("No", new AlertDialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface p1, int p2) {
							finish();
						}
					}).show();
			else
				finish();
		else {
			Toast.makeText(this, "Press back once more", Toast.LENGTH_SHORT).show();
			editor.backPress = System.currentTimeMillis();
		}
	}
}
