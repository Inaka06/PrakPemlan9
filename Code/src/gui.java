import java.awt.event.*;
import javax.swing.*;

public class gui extends JFrame {
    JMenuBar mb = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem close = new JMenuItem("Close");
    JMenuItem quit = new JMenuItem("Quit");
    JMenuItem about = new JMenuItem("About");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem cut = new JMenuItem("Cut");
    JLabel lblnama = new JLabel("Nama");
    JTextField txnama = new JTextField(30);
    JLabel lblnim = new JLabel("NIM");
    JTextField txnim = new JTextField(7);
    JLabel lblkelamin = new JLabel("Jenis Kelamin");
    String[] genderList = {"Pilih", "Pria", "Wanita"};
    JComboBox<String> cbGender = new JComboBox<>(genderList);
    JLabel lblhobi = new JLabel("Hobi");
    JRadioButton hobi1 = new JRadioButton("Shopping");
    JRadioButton hobi2 = new JRadioButton("Futsal");
    JRadioButton hobi3 = new JRadioButton("Musik");
    ButtonGroup groupHobi = new ButtonGroup();
    JLabel label1 = new JLabel("Nilai UTS");
    JTextField tx1 = new JTextField(20);
    JLabel label2 = new JLabel("Nilai UAS");
    JTextField tx2 = new JTextField(20);
    JButton cetak = new JButton("Cetak");
    JTextArea hasil = new JTextArea();

    gui() {
        setTitle("DATA MAHASISWA");
        setLocation(300, 100);
        setSize(300, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void KomponenVisual() {
        setJMenuBar(mb);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        file.add(open);
        file.add(close);
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        close.setEnabled(false);
        file.add(quit);
        help.add(about);
        setVisible(true);
        getContentPane().setLayout(null);
        getContentPane().add(lblnama);
        lblnama.setBounds(10, 10, 80, 20);
        getContentPane().add(txnama);
        txnama.setBounds(105, 10, 175, 20);
        getContentPane().add(lblnim);
        lblnim.setBounds(10, 33, 80, 20);
        getContentPane().add(txnim);
        txnim.setBounds(105, 33, 70, 20);
        getContentPane().add(lblkelamin);
        lblkelamin.setBounds(10, 56, 80, 20);
        getContentPane().add(cbGender);
        cbGender.setBounds(105, 56, 120, 20);
        getContentPane().add(lblhobi);
        lblhobi.setBounds(10, 80, 70, 20);
        groupHobi.add(hobi1);
        groupHobi.add(hobi2);
        groupHobi.add(hobi3);
        getContentPane().add(hobi1);
        hobi1.setBounds(105, 80, 100, 20);
        getContentPane().add(hobi2);
        hobi2.setBounds(105, 103, 100, 20);
        getContentPane().add(hobi3);
        hobi3.setBounds(105, 126, 100, 20);
        getContentPane().add(label1);
        label1.setBounds(10, 146, 80, 20);
        getContentPane().add(tx1);
        tx1.setBounds(105, 146, 175, 20);
        getContentPane().add(label2);
        label2.setBounds(10, 169, 80, 20);
        getContentPane().add(tx2);
        tx2.setBounds(105, 169, 175, 20);
        getContentPane().add(cetak);
        cetak.setBounds(10, 191, 270, 20);
        getContentPane().add(hasil);
        hasil.setBounds(10, 214, 270, 140);
        setVisible(true);

    }

    void AksiReaksi() {
        cetak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                hasil.append(lblnama.getText() + " : " + txnama.getText() + "\n");
                hasil.append(lblnim.getText() + " : " + txnim.getText() + "\n");
                String gender = cbGender.getSelectedItem().toString();

                if (gender.equals("Pilih")) {
                    hasil.append("Jenis Kelamin : belum dipilih\n");
                } else {
                    hasil.append("Jenis Kelamin : " + gender + "\n");
                }
                String hobi = "";

                if (hobi1.isSelected()) {
                    hobi = hobi1.getText();
                } else if (hobi2.isSelected()) {
                    hobi = hobi2.getText();
                } else if (hobi3.isSelected()) {
                    hobi = hobi3.getText();
                } else {
                    hobi = "Belum dipilih";
                }

                hasil.append("Hobi : " + hobi + "\n");
                hasil.append(label1.getText() + " : " + tx1.getText() + "\n");
                hasil.append(label2.getText() + " : " + tx2.getText() + "\n");
                double a = 0.0, b = 0.0, c = 0.0;
                try {
                    a = Double.parseDouble(tx1.getText());
                    b = Double.parseDouble(tx2.getText());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                JButton btn = (JButton) event.getSource();
                if (btn == cetak) {
                    c = (a + b) / 2;
                }
                hasil.append("Kalkulasi: ( "+ a + " + " + b + " ) / 2 = " + c + "\n");
                hasil.append("Nilai Akhir : " + c + "\n");
            }
        });
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Aplikasi Data Mahasiswa\n\n" +
                                "Fitur:\n" +
                                "- Input data mahasiswa\n" +
                                "- Menghitung nilai rata-rata\n" +
                                "- Menampilkan hasil data\n\n" +
                                "Dibuat oleh: [Inaka]\n" +
                                "Versi: 67.0" +
                                "Hehe",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }

    public static void main(String args[]) {
        gui e5 = new gui();
        e5.KomponenVisual();
        e5.AksiReaksi();
    }
}
