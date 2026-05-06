import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// objects
abstract class Matkul {
    double tugas, kuis, uts, uas;

    Matkul(double t, double k, double u1, double u2) {
        tugas = t;
        kuis = k;
        uts = u1;
        uas = u2;
    }

    abstract double hitung();
}

class Pemlan extends Matkul {
    Pemlan(double t, double k, double u1, double u2) {
        super(t, k, u1, u2);
    }
    double hitung() {
        return tugas*0.2 + kuis*0.2 + uts*0.3 + uas*0.3;
    }
}

class ASD extends Matkul {
    ASD(double t, double k, double u1, double u2) {
        super(t, k, u1, u2);
    }
    double hitung() {
        return tugas*0.1 + kuis*0.2 + uts*0.3 + uas*0.4;
    }
}

class Matkomlan extends Matkul {
    Matkomlan(double t, double k, double u1, double u2) {
        super(t, k, u1, u2);
    }
    double hitung() {
        return (tugas + kuis + uts + uas) / 4;
    }
}

class Probstat extends Matkul {
    Probstat(double t, double k, double u1, double u2) {
        super(t, k, u1, u2);
    }
    double hitung() {
        return tugas*0.3 + kuis*0.2 + uts*0.2 + uas*0.3;
    }
}

// gui
public class RataNilai extends JFrame {

    // radio
    JRadioButton rbPemlan = new JRadioButton("Pemlan");
    JRadioButton rbASD = new JRadioButton("ASD");
    JRadioButton rbMatkom = new JRadioButton("Matkomlan");
    JRadioButton rbProb = new JRadioButton("Probstat");
    ButtonGroup group = new ButtonGroup();

    // input
    JTextField tTugas = new JTextField(5);
    JTextField tKuis = new JTextField(5);
    JTextField tUTS = new JTextField(5);
    JTextField tUAS = new JTextField(5);
    JTextField tHasil = new JTextField(5);

    JButton btnHitung = new JButton("Hitung");
    JButton btnTampil = new JButton("Tampilkan nilai semua matkul");

    JTextArea area = new JTextArea();
    JScrollPane scroll = new JScrollPane(area);

    ArrayList<String> data = new ArrayList<>();

    public RataNilai() {
        setTitle("Hitung Nilai Akhir dengan GUI");
        setSize(400, 520);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // radyo
        group.add(rbASD);
        group.add(rbPemlan);
        group.add(rbMatkom);
        group.add(rbProb);

        add(rbASD); rbASD.setBounds(20, 20, 70, 20);
        add(rbPemlan); rbPemlan.setBounds(100, 20, 80, 20);
        add(rbMatkom); rbMatkom.setBounds(190, 20, 100, 20);
        add(rbProb); rbProb.setBounds(300, 20, 90, 20);

        // label
        JLabel lTugas = new JLabel("Tugas :");
        JLabel lKuis = new JLabel("Kuis :");
        JLabel lUTS = new JLabel("UTS :");
        JLabel lUAS = new JLabel("UAS :");
        JLabel lHasil = new JLabel("Hasil :");

        add(lTugas); lTugas.setBounds(80, 60, 60, 20);
        add(tTugas); tTugas.setBounds(150, 60, 80, 20);

        add(lKuis); lKuis.setBounds(80, 90, 60, 20);
        add(tKuis); tKuis.setBounds(150, 90, 80, 20);

        add(lUTS); lUTS.setBounds(80, 120, 60, 20);
        add(tUTS); tUTS.setBounds(150, 120, 80, 20);

        add(lUAS); lUAS.setBounds(80, 150, 60, 20);
        add(tUAS); tUAS.setBounds(150, 150, 80, 20);

        add(lHasil); lHasil.setBounds(80, 180, 60, 20);
        add(tHasil); tHasil.setBounds(150, 180, 80, 20);
        tHasil.setEditable(false);

        // center text
        tTugas.setHorizontalAlignment(JTextField.CENTER);
        tKuis.setHorizontalAlignment(JTextField.CENTER);
        tUTS.setHorizontalAlignment(JTextField.CENTER);
        tUAS.setHorizontalAlignment(JTextField.CENTER);
        tHasil.setHorizontalAlignment(JTextField.CENTER);

        // tombol
        add(btnHitung); btnHitung.setBounds(120, 210, 120, 25);

        add(scroll); scroll.setBounds(50, 250, 300, 150);

        add(btnTampil); btnTampil.setBounds(90, 420, 220, 25);

        // reset
        ActionListener reset = e -> {
            tTugas.setText("");
            tKuis.setText("");
            tUTS.setText("");
            tUAS.setText("");
            tHasil.setText("");
        };

        rbASD.addActionListener(reset);
        rbPemlan.addActionListener(reset);
        rbMatkom.addActionListener(reset);
        rbProb.addActionListener(reset);

        // kalkulasi
        btnHitung.addActionListener(e -> {
            try {
                double t = Double.parseDouble(tTugas.getText());
                double k = Double.parseDouble(tKuis.getText());
                double u1 = Double.parseDouble(tUTS.getText());
                double u2 = Double.parseDouble(tUAS.getText());

                Matkul m = null;
                String nama = "";

                if (rbPemlan.isSelected()) {
                    m = new Pemlan(t,k,u1,u2);
                    nama = "Pemlan";
                } else if (rbASD.isSelected()) {
                    m = new ASD(t,k,u1,u2);
                    nama = "ASD";
                } else if (rbMatkom.isSelected()) {
                    m = new Matkomlan(t,k,u1,u2);
                    nama = "Matkomlan";
                } else if (rbProb.isSelected()) {
                    m = new Probstat(t,k,u1,u2);
                    nama = "Probstat";
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih mata kuliah dulu!");
                    return;
                }

                double hasil = m.hitung();
                tHasil.setText(String.format("%.2f", hasil));

                boolean found = false;
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).startsWith(nama)) {
                        data.set(i, nama + " : " + String.format("%.2f", hasil));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    data.add(nama + " : " + String.format("%.2f", hasil));
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Input harus angka!");
            }
        });

        // tampil
        btnTampil.addActionListener(e -> {
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Belum ada data!");
                return;
            }

            area.setText("HASIL NILAI SEMUA MATA KULIAH\n\n");
            for (String s : data) {
                area.append(s + "\n");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new RataNilai();
    }
}