package Web;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.*;


public class GUI implements ActionListener {
    JTextArea area;
    WebScraping ob;
    JScrollPane pane;

    GUI() {
        ob = new WebScraping();
        JFrame frame = new JFrame("Web Scrapper");
        frame.setBounds(0, 0, 1000, 1000);
        JButton fr = new JButton("Frequent words");

        fr.setBounds(0, 0, 200, 50);        // fr buttton for frequent words
        fr.addActionListener(this);
        JButton uni = new JButton("Unique words");         // uni button for unique words

        uni.setBounds(200, 0, 200, 50);
        uni.addActionListener(this);
        JButton st = new JButton("Stories retrived");         // st for stries retrived for each category
        st.setBounds(400, 0, 200, 50);
        st.addActionListener(this);
        JButton min = new JButton("Max length story");
        min.setBounds(600, 0, 200, 50);
        min.addActionListener(this);
        JButton max = new JButton("Min length story");
        max.setBounds(800, 0, 200, 50);
        max.addActionListener(this);
        JLabel linkLabel = new JLabel("Link for Scrapping");
        JTextField linkField = new JTextField("https://www.bbc.com");
        linkField.setEditable(false);
        linkLabel.setBounds(435, 100, 150, 70);
        linkField.setBounds(435, 150, 130, 35);
        area = new JTextArea();
        area.setBounds(0, 200, 1000, 1000);
        area.setFont(new Font ("SAN SERIF",Font.PLAIN,20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.getScrollableTracksViewportWidth();
        pane = new JScrollPane(area);
        //area.add(pane,BorderLayout.CENTER);
        frame.add(pane);

        //area.getFont(Font callabari);

        frame.add(fr);
        frame.add(uni);
        frame.add(st);
        frame.add(min);
        frame.add(max);
        frame.add(linkLabel);
        frame.add(linkField);
        frame.add(area);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if(action.equals("Max length story"))
        {
            String value=ob.Max();
            area.setText(value);
        }

        if(action.equals("Min length story"))
        {
            String value=ob.Min();
            area.setText(value);

        }
        if(action.equals("Frequent words"))
        {
            area.setText("Wait Data is being fetched");
            String value=ob.Frequent();
            area.setText("Frequent Words are\n"+value);

        }
        if (action.equals("Stories retrived"))
        {
            //   area.setText("Wait Data is being fetched");
            String value=ob.NumberOfStories();
            area.setText(value);
        }
        if(action.equals("Unique words"))
        {
            area.setText("Wait Data is being fetched");
            String value= null;
            try {
                value = ob.unique();
                area.setText("Unique Words are\n"+value);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }


        }


    }

    public static void main(String[] args)
    {
        new GUI();
    }


}