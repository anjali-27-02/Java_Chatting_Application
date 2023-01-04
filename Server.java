package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client implements ActionListener {
    JFrame jf;
    JTextField jtf;
    JTextArea jta;
    JScrollPane jsp;
    String m;
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;

    Thread t=new Thread(){
        public void run(){
            while(true){
                readMessage();

            }
        }
    };
    Client(){
        m=JOptionPane.showInputDialog("Enter the Ip_address");
        if(m!=null){
            if(!m.equals("")){
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                jf=new JFrame("CLIENT");
                jf.setSize(500,500);
                //jf.setLayout(null);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Image icon = Toolkit.getDefaultToolkit().getImage("D:\\panda.png");
                jf.setIconImage(icon);


                jtf=new JTextField();
                jtf.addActionListener(this);
                //jta.setEditable(false);
                //jtf.setBounds(0,400,500,30);
                jf.add(jtf,BorderLayout.SOUTH);
                Font f=new Font("Arial",1,16);
                jta=new JTextArea();
                jta.setFont(f);
                jta.setBackground(Color.gray);
                jta.setForeground(Color.white);
                jta.setEditable(false);
                jsp=new JScrollPane(jta);
                jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                jf.add(jsp);

                jf.setVisible(true);




            }




        }



    }
    public void ConnectToServer(){
        try {
             socket = new Socket(m, 1111);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jtf){
            String s=jtf.getText();
            jta.append(s+"\n");
            jtf.setText("");
            sendMessage(s);
        }

    }
    public void setIOStream() {

        try{

        dis=new DataInputStream(socket.getInputStream());
        dos=new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        t.start();

    }
    public void sendMessage(String msg){
        try {
            dos.writeUTF(msg);
            dos.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readMessage(){
        try{
            String message=dis.readUTF();
            showMessage("Server: "+message);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showMessage(String message){
        jta.append(message+"\n");
    }

}
