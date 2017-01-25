package com.example.test.roverapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import android.os.AsyncTask;


public class MainActivity extends AppCompatActivity {


    private UDPsend UDPSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //Init UDP socket connection to Droid (raspi):



        UDPSender = new UDPsend();


        Switch leftLED = (Switch) findViewById(R.id.leftLED);
        leftLED.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try {
                        send_udp("set_led2 1");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        send_udp("set_led2 0");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Switch rightLED = (Switch) findViewById(R.id.rightLED);
        rightLED.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                        new UDPsend().execute("hello world");


                }else{

                        new UDPsend().execute("set_led1 0");


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class UDPsend extends AsyncTask<String, Void, String> {
        private InetAddress Rover_ADDR;
        private DatagramSocket UDPSocket;
        public UDPsend(){
            try {
                Rover_ADDR = InetAddress.getByName(getResources().getString(R.string.ip));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                UDPSocket = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onPreExecute() {
            // Do stuff before the operation
        }

        @Override
        protected String doInBackground(String... params){
            try {
                String sendmsg = (String) params[0];
                int msg_length=sendmsg.length();
                byte[] message = sendmsg.getBytes();
                DatagramPacket p = new DatagramPacket(message, msg_length,Rover_ADDR,getResources().getInteger(R.integer.port_udp));
                this.UDPSocket.send(p);
            } catch (SocketException e) {
                Log.e("Udp:", "Socket Error:", e);
            } catch (IOException e) {
                Log.e("Udp Send:", "IO Error:", e);
            }
            return "a string";
        }

        @Override
        protected void onPostExecute(String result) {
            // Do stuff after the operation
        }
    }

    public void send_tcp(String msg) throws IOException {
        //Doesn't actually work yet...
        Socket socket = new Socket("10.0.0.162", 5005);

        OutputStream out = socket.getOutputStream();
        PrintWriter output = new PrintWriter(out);


        output.println(msg);

        socket.close();
    }
    public void send_udp(String msg) throws IOException {



    }
}
