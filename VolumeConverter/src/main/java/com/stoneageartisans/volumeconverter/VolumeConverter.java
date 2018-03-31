package com.stoneageartisans.volumeconverter;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

public class VolumeConverter extends Activity {

	// Constants
	private static final int milliliters = 0;
	private static final int teaspoons = 1;
	private static final int centiliters = 2;
	private static final int tablespoons = 3;
	private static final int fluid_ounces = 4;
	private static final int deciliters = 5;
	private static final int cups = 6;
	private static final int pints = 7;
	private static final int quarts = 8;
	private static final int liters = 9;
	private static final int gallons = 10;
	private static final int dekaliters = 11;
	private static final int hectoliters = 12;
	private static final int kiloliters = 13;
	
	// Variables
	private EditText edittext_input;
	private TextView textview_output;
	private int font_size;
	private int units_from;
	private int units_to;
	private Spinner spinner_units_from;
	private Spinner spinner_units_to;
	private String[] units;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.layout_main);
		initialize();

	}
	
	@Override
	public void onResume() {
		
	    super.onResume();
	    
	}
	
	@Override
	public void onPause() {
		
	    super.onPause();
	    
	}

	private void initialize() {
		
		units = this.getResources().getStringArray(R.array.units);
    	units_from = 0;
    	units_to = 0;

        // Calculate font size based on screen dimensions
        font_size = 20;

        // Set title font size
        ( (TextView) this.findViewById(R.id.title) ).setTextSize(font_size);

        edittext_input = (EditText) this.findViewById(R.id.input);
        edittext_input.setTextSize(font_size);

        ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, units) {
    		public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ( (TextView) view ).setTextSize(font_size);
                return view;
    		}
    		public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
               ( (TextView) view ).setTextSize(font_size);
               return view;
    		}
    	};
    	array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    	spinner_units_from = (Spinner) this.findViewById(R.id.units_from);
    	spinner_units_from.setAdapter(array_adapter);

    	textview_output = (TextView) this.findViewById(R.id.output);
    	textview_output.setTextSize(font_size);

    	spinner_units_to = (Spinner) this.findViewById(R.id.units_to);
    	spinner_units_to.setAdapter(array_adapter);

    	edittext_input.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,	KeyEvent event) {
				convert_units();
				return false;
			}
        });

    	spinner_units_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				units_from = position;
				convert_units();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

    	spinner_units_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				units_to = position;
				convert_units();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
    	
    	Button convert = ( (Button) this.findViewById(R.id.convert) );
    	convert.setTextSize(font_size);
    	convert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				convert_units();				
			}    		
    	});

    }

    private void convert_units() {

    	String output = "";
    	try {
    		double input = Double.parseDouble( edittext_input.getText().toString() );
	    	switch(units_from) {
	    		case teaspoons:  // from teaspoons
	    			switch(units_to) {
	    				case teaspoons:  // to teaspoons
	    					output = String.valueOf(input);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input / 3);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input / 6);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 48);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 96);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 192);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 768);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input / 0.20288413621);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input / 2.0288413621);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input / 20.288413621);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input / 202.88413621);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input / 2028.8413621);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 20288.413621);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 202884.13621);
	    					break;
	    			}
	    			break;
	    		case tablespoons:  // from tablespoons
	    			switch(units_to) {
						case teaspoons:  // to teaspoons
							output = String.valueOf(input * 3);
							break;
						case tablespoons:  // to tablespoons
							output = String.valueOf(input);
							break;
						case fluid_ounces:  // to fluid ounces
							output = String.valueOf(input / 2);
							break;
						case cups:  // to cups
	    					output = String.valueOf(input / 16);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 32);
	    					break;
						case quarts:  // quarts
							output = String.valueOf(input / 64);
							break;
						case gallons:  // to gallons
	    					output = String.valueOf(input / 256);
	    					break;
						case milliliters:  // to milliliters
	    					output = String.valueOf(input * 14.786764781);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 1.4786764781);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 0.14786764781);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input * 0.014786764781);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input * 0.0014786764781);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input * 0.00014786764781);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input * 0.000014786764781);
	    					break;
	    			}
	    			break;
	    		case fluid_ounces:  // from fluid ounces
	    			switch(units_to) {
						case teaspoons:  // to teaspoons
							output = String.valueOf(input * 6);
							break;
						case tablespoons:  // to tablespoons
							output = String.valueOf(input * 2);
							break;
						case fluid_ounces:  // to fluid ounces
							output = String.valueOf(input);
							break;
						case cups:  // to cups
	    					output = String.valueOf(input / 8);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 16);
	    					break;
						case quarts:  // quarts
							output = String.valueOf(input / 32);
							break;
						case gallons:  // to gallons
	    					output = String.valueOf(input / 128);
	    					break;
						case milliliters:  // to milliliters
	    					output = String.valueOf(input / 0.033814022701);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input / 0.33814022701);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input / 3.3814022701);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input / 33.814022701);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input / 338.14022701);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 3381.4022701);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 33814.022701);
	    					break;
	    			}
	    			break;
	    		case cups:  // from cups
	    			switch(units_to) {
						case teaspoons:  // to teaspoons
							output = String.valueOf(input * 48);
							break;
						case tablespoons:  // to tablespoons
							output = String.valueOf(input * 16);
							break;
						case fluid_ounces:  // to fluid ounces
							output = String.valueOf(input * 8);
							break;
						case cups:  // to cups
	    					output = String.valueOf(input);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 2);
	    					break;
						case quarts:  // quarts
							output = String.valueOf(input / 4);
							break;
						case gallons:  // to gallons
	    					output = String.valueOf(input / 16);
	    					break;
						case milliliters:  // to milliliters
	    					output = String.valueOf(input / 0.0042267528377);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input / 0.042267528377);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input / 0.42267528377);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input / 4.2267528377);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input / 42.267528377);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 422.67528377);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 4226.7528377);
	    					break;
	    			}
	    			break;
	    		case pints:  // from pints
	    			switch(units_to) {
						case teaspoons:  // to teaspoons
							output = String.valueOf(input * 96);
							break;
						case tablespoons:  // to tablespoons
							output = String.valueOf(input * 32);
							break;
						case fluid_ounces:  // to fluid ounces
							output = String.valueOf(input * 16);
							break;
						case cups:  // to cups
	    					output = String.valueOf(input * 2);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input);
	    					break;
						case quarts:  // quarts
							output = String.valueOf(input / 2);
							break;
						case gallons:  // to gallons
	    					output = String.valueOf(input / 8);
	    					break;
						case milliliters:  // to milliliters
	    					output = String.valueOf(input * 473.176473);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 47.3176473);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 4.73176473);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input * 0.473176473);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input * 0.0473176473);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input * 0.00473176473);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input * 0.000473176473);
	    					break;
	    			}
	    			break;
	    		case quarts:  // from quarts
	    			switch(units_to) {
						case teaspoons:  // to teaspoons
							output = String.valueOf(input * 192);
							break;
						case tablespoons:  // to tablespoons
							output = String.valueOf(input * 64);
							break;
						case fluid_ounces:  // to fluid ounces
							output = String.valueOf(input * 32);
							break;
						case cups:  // to cups
	    					output = String.valueOf(input * 4);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input * 2);
	    					break;
						case quarts:  // quarts
							output = String.valueOf(input);
							break;
						case gallons:  // to gallons
	    					output = String.valueOf(input / 4);
	    					break;
						case milliliters:  // to milliliters
	    					output = String.valueOf(input * 946.352946);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 94.6352946);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 9.46352946);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input * 0.946352946);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input * 0.0946352946);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input * 0.00946352946);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input * 0.000946352946);
	    					break;
	    			}
	    			break;
	    		case gallons:  // from gallons
	    			switch(units_to) {
						case teaspoons:  // to teaspoons
							output = String.valueOf(input * 768);
							break;
						case tablespoons:  // to tablespoons
							output = String.valueOf(input * 256);
							break;
						case fluid_ounces:  // to fluid ounces
							output = String.valueOf(input * 128);
							break;
						case cups:  // to cups
	    					output = String.valueOf(input * 16);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input * 8);
	    					break;
						case quarts:  // quarts
							output = String.valueOf(input * 4);
							break;
						case gallons:  // to gallons
	    					output = String.valueOf(input);
	    					break;
						case milliliters:  // to milliliters
	    					output = String.valueOf(input * 3785.411784);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 378.5411784);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 37.85411784);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input * 3.785411784);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input * 0.3785411784);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input * 0.03785411784);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input * 0.003785411784);
	    					break;
	    			}
	    			break;
	    		case milliliters:  // from milliliters
	    			switch(units_to) {
	    				case teaspoons:  // to teaspoons
	    					output = String.valueOf(input * 0.20288413621);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input * 0.067628045405);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input * 0.033814022701);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 236.5882365);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 473.176473);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 946.352946);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 3785.411784);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input / 10);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input / 100);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input / 1000);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input / 10000);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 100000);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 1000000);
	    					break;
	    			}
	    			break;
	    		case centiliters:  // from centiliters
	    			switch(units_to) {
		    			case teaspoons:  // to teaspoons
	    					output = String.valueOf(input * 2.0288413621);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input * 0.67628045405);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input * 0.33814022701);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 23.65882365);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 47.3176473);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 94.6352946);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 378.5411784);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input * 10);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input / 10);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input / 100);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input / 1000);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 10000);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 100000);
	    					break;
	    			}
	    			break;
	    		case deciliters:  // from deciliters
	    			switch(units_to) {
		    			case teaspoons:  // to teaspoons
	    					output = String.valueOf(input * 20.288413621);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input * 6.7628045405);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input * 3.3814022701);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 2.365882365);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 4.73176473);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 9.46352946);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 37.85411784);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input * 100);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 10);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input / 10);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input / 100);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 1000);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 10000);
	    					break;
	    			}
	    			break;
	    		case liters:  // from liters
	    			switch(units_to) {
		    			case teaspoons:  // to teaspoons
	    					output = String.valueOf(input * 202.88413621);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input * 67.628045405);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input * 33.814022701);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 0.2365882365);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 0.473176473);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 0.946352946);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 3.785411784);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input * 1000);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 100);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 10);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input / 10);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 100);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 1000);
	    					break;
	    			}
	    			break;
	    		case dekaliters:  // from dekaliters
	    			switch(units_to) {
		    			case teaspoons:  // to teaspoons
	    					output = String.valueOf(input * 2028.8413621);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input * 676.28045405);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input * 338.14022701);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 0.02365882365);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 0.0473176473);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 0.0946352946);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 0.3785411784);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input * 10000);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 1000);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 100);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input * 10);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input / 10);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 100);
	    					break;
	    			}
	    			break;
	    		case hectoliters:  // from hectoliters
	    			switch(units_to) {
		    			case teaspoons:  // to teaspoons
	    					output = String.valueOf(input * 20288.413621);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input * 6762.8045405);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input * 3381.4022701);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 0.002365882365);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 0.00473176473);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 0.00946352946);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 0.03785411784);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input * 100000);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 10000);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 1000);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input * 100);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input * 10);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input / 10);
	    					break;
	    			}
	    			break;
	    		case kiloliters:  // from kiloliters
	    			switch(units_to) {
		    			case teaspoons:  // to teaspoons
	    					output = String.valueOf(input * 202884.13621);
	    					break;
	    				case tablespoons:  // to tablespoons
	    					output = String.valueOf(input * 67628.045405);
	    					break;
	    				case fluid_ounces:  // to fluid ounces
	    					output = String.valueOf(input * 33814.022701);
	    					break;
	    				case cups:  // to cups
	    					output = String.valueOf(input / 0.0002365882365);
	    					break;
	    				case pints:  // to pints
	    					output = String.valueOf(input / 0.000473176473);
	    					break;
	    				case quarts:  // to quarts
	    					output = String.valueOf(input / 0.000946352946);
	    					break;
	    				case gallons:  // to gallons
	    					output = String.valueOf(input / 0.003785411784);
	    					break;
	    				case milliliters:  // to milliliters
	    					output = String.valueOf(input * 1000000);
	    					break;
	    				case centiliters:  // to centiliters
	    					output = String.valueOf(input * 100000);
	    					break;
	    				case deciliters:  // to deciliters
	    					output = String.valueOf(input * 10000);
	    					break;
	    				case liters:  // to liters
	    					output = String.valueOf(input * 1000);
	    					break;
	    				case dekaliters:  // to dekaliters
	    					output = String.valueOf(input * 100);
	    					break;
	    				case hectoliters:  // to hectoliters
	    					output = String.valueOf(input * 10);
	    					break;
	    				case kiloliters:  // kiloliters
	    					output = String.valueOf(input);
	    					break;
	    			}
	    			break;
	    	}
    	} catch(NumberFormatException ex) {
    		output = "";
    	}
    	textview_output.setText(output);

    }

}
