package edu.illinois.geoalarm;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;


public class GeoAlarm extends Activity
{
	
	GeoAlarmDB database;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Instantiate the database
		database = new GeoAlarmDB(this.getApplicationContext());

		// Check the custom SQLite helper functions that load existing DB
		try
		{
			database.createDataBase();
		}
		catch (IOException e)
		{
			throw new Error("Unable to create/find database");
		}

		// Open the SQLite database
		try
		{
			database.openDataBase();
		}
		catch (SQLException sql)
		{
			throw new Error("Unable to execute sql in: " + sql.toString());
		}

		Spinner type = (Spinner) findViewById(R.id.spinner1);
		type.OnItemSelectedListener(new View.OnItemSelectedListener()
		{
			public void onSelect(View v)
			{
				AdapterView<?> parent, View view, int pos, long id);

				Toast.makeText(parent.getContext(), "The planet is " +
		          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
		    }
		});
		

	
		
    }
		// Execute SQLite to retrieve lines
		Cursor theCursor = database.geoAlarmDB.query("lines", null, "type = " + type, null, null, null, null);

		if(theCursor != null)
		{
			theCursor.moveToFirst();
			
			String[] lineNames;
			
			for(int i = 0; theCursor.isAfterLast() != false; i++)
			{
				int nameColumn = theCursor.getColumnIndex("name");
				lineNames[i] = theCursor.getString(nameColumn);

				theCursor.moveToNext();
			}
		}

		theCursor.close();
		database.geoAlarmDB.close();
    }
   
}