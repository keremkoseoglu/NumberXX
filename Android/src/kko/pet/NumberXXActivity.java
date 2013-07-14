package kko.pet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class NumberXXActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Add event to button
        Button b = (Button) findViewById(R.id.btnInv);
        b.setOnClickListener(ocl);
    }
    
    View.OnClickListener ocl = new View.OnClickListener() {
		
		public void onClick(View v) {
			
			// Get phrase
			EditText et = (EditText) findViewById(R.id.txtPhrase);
			String phrase = et.getText().toString().toUpperCase();
			
			// Get number
			et = (EditText) findViewById(R.id.txtNumber);
			String ns = et.getText().toString();
			int n = Integer.valueOf(ns);
			
			// Investigator object
			Investigator i = new Investigator();
			Solution s = i.analyse(phrase, n);
			
			// Set text
			et = (EditText) findViewById(R.id.txtLog);
			et.setText("");
			
			String[] st = s.getSolution();
			for (n = 0; n < st.length; n++) et.append(st[n] + "\r\n");
			
		}
	};
    
}