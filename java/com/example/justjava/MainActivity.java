/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int basePrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name);
        String name = nameEditText.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String summaryMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        intent.putExtra(Intent.EXTRA_TEXT, summaryMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String Message = getString(R.string.name) + " " + name;
        Message += "\n" + getString(R.string.add_whipped_cream) + " " + hasWhippedCream;
        Message += "\n" + getString(R.string.add_chocolate) + " " + hasChocolate;
        Message += "\n" + getString(R.string.quantity1) + " " + quantity;
        Message += "\n" + getString(R.string.total) + price;
        Message += "\n" + getString(R.string.thank_you);
        return(Message);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocoalte) {
        if(hasWhippedCream)
        {
            basePrice = 5 + 1;
        }
        if(hasChocoalte)
        {
            basePrice = 5 + 2;
        }
        if(hasWhippedCream && hasChocoalte)
        {
            basePrice = 5 + 1 + 2;
        }

        int totalPrice = basePrice * quantity;
        return(totalPrice);
    }

    public void increment(View view) {
        if(quantity == 10)
        {
            Toast.makeText(this, "Number of coffees ordered can not be more than 10", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            quantity = quantity + 1;
        }
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1)
        {
            Toast.makeText(this, "Number of coffees ordered can not be less than 1", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            quantity = quantity - 1;
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */



}