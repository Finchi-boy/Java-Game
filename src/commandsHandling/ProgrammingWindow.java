package commandsHandling;

import levels.LevelManager;
import utilz.Constants;
import utilz.RunListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ProgrammingWindow extends JPanel{
    private JTextArea outputArea;
    private JTextArea codeArea;
    private JScrollPane scrollPane;
    private JButton runButton;
    private RunListener runListener;



    public ProgrammingWindow() {
        setBounds(Constants.TILES_IN_BOARD * Constants.TILE_SIZE, 0, Constants.SIZE_OF_EDITOR, Constants.WINDOW_HEIGHT);
        setLayout(null);
        initCodeArea();
        initOutputArea();
        initRunButton();

    }

    private void initRunButton() {
        runButton = new JButton("Run");
        runButton.setBounds(0, 0, Constants.SIZE_OF_EDITOR, 50);
        runButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        runButton.addActionListener(e -> {
    try {
        runListener.onRun(codeArea.getText());
    }
    catch (Exception ex) {
        outputArea.setText(ex.getMessage());
        return;
    }

        });
        add(runButton);
    }


    private void initCodeArea()
    {
         codeArea = new JTextArea();
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        codeArea.setLineWrap(false); // brak zawijania linii
        codeArea.setWrapStyleWord(false);

        // Numeracja linii
        JTextArea lineNumbers = new JTextArea("1");

        int width = (int)(2.5 * codeArea.getFontMetrics(codeArea.getFont()).stringWidth("0"));
        lineNumbers.setPreferredSize(new Dimension(width, Integer.MAX_VALUE));
        lineNumbers.setFont(codeArea.getFont());
        lineNumbers.setBackground(Color.LIGHT_GRAY);
        lineNumbers.setEditable(false);
        codeArea.getDocument().addDocumentListener(new DocumentListener() {
            public String getLineNumbers() {
                int lines = codeArea.getLineCount();
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= lines; i++) {
                    sb.append(i).append(System.lineSeparator());
                }
                return sb.toString();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                lineNumbers.setText(getLineNumbers());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lineNumbers.setText(getLineNumbers());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lineNumbers.setText(getLineNumbers());
            }
        });

        JScrollPane scrollPane = new JScrollPane(codeArea);
        scrollPane.setRowHeaderView(lineNumbers);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(Constants.SIZE_OF_EDITOR, Constants.WINDOW_HEIGHT - 100);
        scrollPane.setLocation(0, 50);


        add(scrollPane);
        setVisible(true);
        codeArea.setText("A");
        if(codeArea.getText().isEmpty()) {
            System.out.println("Code area is empty");
        }


    }

    private void initOutputArea() {
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(0, Constants.WINDOW_HEIGHT - 50, Constants.SIZE_OF_EDITOR, 50);
        add(scrollPane);
    }


    private void readCommands(String code) throws Exception {
        code = code.replaceAll("\\s+", ""); // Usunięcie białych znaków
    }

    public void setRunListener(RunListener runListener) {
        this.runListener = runListener;
    }



}
