package android.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int noOfCups=0;
    double total=0;
    String print="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox)findViewById(R.id.checkBox1);
        boolean WC=whippedCream.isChecked();
        CheckBox ChoclateTopping = (CheckBox)findViewById(R.id.checkBox2);
        boolean CT=ChoclateTopping.isChecked();

        EditText text1 = (EditText)findViewById(R.id.editText1);
        String name=text1.getText().toString();
        EditText text2 = (EditText)findViewById(R.id.editText2);
        String address=text2.getText().toString();
        print+="Name: "+name+"\nAddress: "+address;

        TextView priceTextView = (TextView) findViewById(R.id.Order_Summary_textView);

        String message=createOrderSummary(noOfCups,WC,CT);
        String[] email={"raghav.harsh09@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc822");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    public void cream(View view){
        RelativeLayout r1=(RelativeLayout)findViewById(R.id.base);
        CheckBox whippedCream = (CheckBox)findViewById(R.id.checkBox1);
        boolean WC=whippedCream.isChecked();
        CheckBox ChoclateTopping = (CheckBox)findViewById(R.id.checkBox2);
        boolean CT=ChoclateTopping.isChecked();
        if(WC==true && CT==true){
            r1.setBackgroundResource(R.drawable.coffeeboth);
        }
        else if(WC==true){
            r1.setBackgroundResource(R.drawable.coffeecream);
        }
        else if(WC==false && CT==false){
            r1.setBackgroundResource(R.drawable.cutecoffee);
        }
        else{
            r1.setBackgroundResource(R.drawable.coffeechocolate);
        }
    }
    public void chocolate(View view){
        RelativeLayout r1=(RelativeLayout)findViewById(R.id.base);
        CheckBox whippedCream = (CheckBox)findViewById(R.id.checkBox1);
        boolean WC=whippedCream.isChecked();
        CheckBox ChoclateTopping = (CheckBox)findViewById(R.id.checkBox2);
        boolean CT=ChoclateTopping.isChecked();
        if(WC==true && CT==true){
            r1.setBackgroundResource(R.drawable.coffeeboth);
        }
        else if(WC==true){
            r1.setBackgroundResource(R.drawable.coffeecream);
        }
        else if(WC==false && CT==false){
            r1.setBackgroundResource(R.drawable.cutecoffee);
        }
        else{
            r1.setBackgroundResource(R.drawable.coffeechocolate);
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    public void inc(View view) {
        CheckBox whippedCream = (CheckBox)findViewById(R.id.checkBox1);
        boolean WC=whippedCream.isChecked();
        CheckBox ChoclateTopping = (CheckBox)findViewById(R.id.checkBox2);
        boolean CT=ChoclateTopping.isChecked();
        noOfCups++;
        display(noOfCups);
        displayPrice(noOfCups,WC,CT);
    }
    public void dec(View view) {
        CheckBox whippedCream = (CheckBox)findViewById(R.id.checkBox1);
        boolean WC=whippedCream.isChecked();
        CheckBox ChoclateTopping = (CheckBox)findViewById(R.id.checkBox2);
        boolean CT=ChoclateTopping.isChecked();
        if(noOfCups<=0){
            noOfCups=0;
        }
        else {
            noOfCups--;
            display(noOfCups);
            displayPrice(noOfCups,WC,CT);
        }
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.textView2);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number,boolean WC,boolean CT) {
        total=number*5;
        if(WC==true){
            total=total+.2*number;
        }
        if(CT==true){
            total=total+.2*number;
        }
        TextView priceTextView = (TextView) findViewById(R.id.Order_Summary_textView);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(total*1));
    }
    private String createOrderSummary(int no,boolean WC, boolean CT) {
        total=no*5;
        print+="\nQuantity: " + no ;
        if(WC==true){
            print=print+"\nadded Whipped Cream";
            total+=no*.20;
        }
        if(CT==true){
            print=print+"\nadded Choclolate Topping";
            total+=no*.20;
        }

        print=print+"\nTotal: $" + total+"\nThank you!";
        String output=print;
        print="";
        return output;


    }


}