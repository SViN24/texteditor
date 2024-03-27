package com.john.text;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.awt.event.*;

class editor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea text_area;
    JMenuBar menu_bar;

    // Constructor
    editor() {
        frame = new JFrame("editor");
        text_area = new JTextArea();
        menu_bar = new JMenuBar();

        // JMenus
        JMenu file_menu = new JMenu("File");

        // Menu Items
        JMenuItem new_item = new JMenuItem("New");
        JMenuItem open_item = new JMenuItem("Open");
        JMenuItem save_item = new JMenuItem("Save");

        // Action Listeners and adds
        new_item.addActionListener(this);
        open_item.addActionListener(this);
        save_item.addActionListener(this);

        // construct menus
        file_menu.add(new_item);
        file_menu.add(open_item);
        file_menu.add(save_item);

        // bar construction
        menu_bar.add(file_menu);

        frame.setJMenuBar(menu_bar);
        frame.add(text_area);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent edit) {
        String menu_string = edit.getActionCommand();

        switch (menu_string) {
            case "New":
                text_area.setText("");
                break;

            case "Open":
                JFileChooser file_chooser = new JFileChooser("Open: ");
                int result = file_chooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selected_file = file_chooser.getSelectedFile();
                    try {
                        String entire = "", line = "";
                        FileReader fr = new FileReader(selected_file);
                        BufferedReader br = new BufferedReader(fr);

                        while ((line = br.readLine()) != null) {
                            entire = line + "\n" + entire;
                        }
                        text_area.setText(entire);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
                    }
                }
                break;

            case "Save":
                JFileChooser file_chooser_save = new JFileChooser("Save: ");
                int result_save = file_chooser_save.showSaveDialog(null);
                if (result_save == JFileChooser.APPROVE_OPTION) {
                    File selected_file = file_chooser_save.getSelectedFile();
                    try {
                        FileWriter fwr = new FileWriter(selected_file, false);
                        BufferedWriter bwr = new BufferedWriter(fwr);

                        bwr.write(text_area.getText());
                        bwr.flush();
                        bwr.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error writing file: " + e.getMessage());
                    }
                }
                break;

            default:
                JOptionPane.showMessageDialog(frame, "Not implemented");
                break;
        }

    }

    // Main class
    public static void main(String args[]) {
        editor e = new editor();
    }
}
