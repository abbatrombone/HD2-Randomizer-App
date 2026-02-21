package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.CustomButton;
import me.abbatrombone.traz.CustomComponents.JScrollbarUIComp.CustomScrollbar;
import me.abbatrombone.traz.JSONTools.JSONStats;
import me.abbatrombone.traz.JSONTools.WinLose;
import me.abbatrombone.traz.Managers.SettingsManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.List;

public class StatsPanel extends JPanel {
    private static final JPanel panel = new JPanel();
    private final JScrollPane jScrollPane = new JScrollPane(panel);
    private final JLabel topStatslabel = new JLabel("Best For Democracy");

    private BufferedImage backgroundImage;
    private float imageOpacity = 0.3f;

    private static final Logger logger = Logger.getLogger(me.abbatrombone.traz.Panels.StatsPanel.class.getName());
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color fgColor = settingsManager.getColor("Text_Color","#ffffff");
    private final Color bgColor = new Color(51,51,51);

    private JSONStats jsonRandom = new JSONStats(JSONStats.filePaths.randomStats);
    private JSONStats jsonSemiRandom = new JSONStats(JSONStats.filePaths.semiRandomStat);
    private JTable topStats = makeHighestWinTable();
    private JTable randomStats = makeRandomStatsTable();
    private JTable semirandomStats = makeSemiRandomStatsTable();

    private final CustomButton leftCycleButton = new CustomButton("◀",fgColor);
    private final CustomButton rightCycleButton = new CustomButton("▶",fgColor);
    private final CustomButton refreashButton = new CustomButton("Refresh",fgColor);

    private boolean randomIsactive = true;

    public StatsPanel(){

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Stats.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //still need to add buttons, and have tables hit a max size
        makeRanomStatsTables();
        leftCycleButton.addActionListener(e->{
            if (randomIsactive) {
                randomIsactive = !randomIsactive;
                makeSemiRanomStatsTables();
                removeAll();
                repaint();}
            else {
                randomIsactive = !randomIsactive;
                makeRanomStatsTables();
                removeAll();
                repaint();
            }
        });
        rightCycleButton.addActionListener(e->{
            if (randomIsactive) {
                randomIsactive = !randomIsactive;
                makeRanomStatsTables();
                removeAll();
                repaint();}
            else {
                randomIsactive = !randomIsactive;
                makeSemiRanomStatsTables();
                removeAll();
                repaint();
            }
        });
        refreashButton.addActionListener(e->{
            if (randomIsactive) {
                jsonRandom = new JSONStats(JSONStats.filePaths.randomStats);
                makeRanomStatsTables();
                removeAll();
                repaint();}
            else {
                jsonSemiRandom = new JSONStats(JSONStats.filePaths.semiRandomStat);
                makeSemiRanomStatsTables();
                removeAll();
                repaint();
            }
        });
    }
    private JTable makeHighestWinTable(){
        String[] cols = {"Category","Item","Wins","Losses"};
        List<Object[]> rows = new ArrayList<>();
        FontMetrics fm = panel.getFontMetrics(panel.getFont());
        int largstCat = 0;
        int largestKey = 0;

        for (String cat : List.of("Primary","Secondary","Throwable","Armor_Passive","Armor_Level","Booster","Enemy","Stratagem")) {
            Map.Entry<String, WinLose> e = jsonRandom.getHighestWin(cat);
            if (e != null) {
                if(fm.stringWidth(cat) > largstCat){ largstCat = fm.stringWidth(cat);}
                if(fm.stringWidth(e.getKey()) > largestKey){ largestKey = fm.stringWidth(e.getKey());}

                rows.add(new Object[]{cat, e.getKey(), e.getValue().getWin(), e.getValue().getLose()});
            }
        }

        Object[][] data = rows.toArray(new Object[0][]);

        JTable table = new JTable(data, cols);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(largstCat);
        table.getColumnModel().getColumn(1).setPreferredWidth(largestKey);//Font Render for spacing?
        table.getColumnModel().getColumn(2).setPreferredWidth(25);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);

        table.setOpaque(false);
        table.setBackground(new Color(0,0,0,0));
        table.setShowGrid(true);
        table.setGridColor(new Color(255, 255, 255, 30));
        table.setIntercellSpacing(new Dimension(0,0));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    ((JComponent)c).setOpaque(false);
                }
                return c;
            }
        };

        table.getTableHeader().setDefaultRenderer(renderer);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        return table;
    }
    private JTable makeRandomStatsTable(){
        String[] cols = {"Category","Item","Wins","Losses"};
        List<Object[]> rows = new ArrayList<>();
        FontMetrics fm = panel.getFontMetrics(panel.getFont());
        int largstCat = 0;
        int largestKey = 0;

        for (String cat : List.of("Primary","Secondary","Throwable","Armor_Passive","Armor_Level","Booster","Enemy","Stratagem")) {
            List<Map.Entry<String, WinLose>> e = jsonRandom.getTopWins(cat);
            if (e != null) {
                for (Map.Entry<String, WinLose> stringWinLoseEntry : e) {
                    if (fm.stringWidth(cat) > largstCat) {
                        largstCat = fm.stringWidth(cat);
                    }
                    if (fm.stringWidth(stringWinLoseEntry.getKey()) > largestKey) {
                        largestKey = fm.stringWidth(stringWinLoseEntry.getKey());
                    }
                    rows.add(new Object[]{cat, stringWinLoseEntry.getKey(), stringWinLoseEntry.getValue().getWin(), stringWinLoseEntry.getValue().getLose()});
                }
            }
        }

        Object[][] data = rows.toArray(new Object[0][]);

        JTable table = new JTable(data, cols);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(largstCat);
        table.getColumnModel().getColumn(1).setPreferredWidth(largestKey);//Font Render for spacing?
        table.getColumnModel().getColumn(2).setPreferredWidth(25);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);

        table.setOpaque(false);
        table.setBackground(new Color(0,0,0,0));
        table.setShowGrid(true);
        table.setGridColor(new Color(255, 255, 255, 30));
        table.setIntercellSpacing(new Dimension(0,0));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    ((JComponent)c).setOpaque(false);
                }
                return c;
            }
        };

        table.getTableHeader().setDefaultRenderer(renderer);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        return table;
    }
    private JTable makeRandomStatsTablePanel(String cat){

        String[] cols = {cat,"Wins","Losses"};
        List<Object[]> rows = new ArrayList<>();
        FontMetrics fm = panel.getFontMetrics(panel.getFont());

        int largestKey = 0;

        List<Map.Entry<String, WinLose>> e = jsonRandom.getTopWins(cat);

        if (e != null) {
            for (Map.Entry<String, WinLose> stringWinLoseEntry : e) {
                if (fm.stringWidth(stringWinLoseEntry.getKey()) > largestKey) {
                    largestKey = fm.stringWidth(stringWinLoseEntry.getKey());
                }
                rows.add(new Object[]{stringWinLoseEntry.getKey(), stringWinLoseEntry.getValue().getWin(), stringWinLoseEntry.getValue().getLose()});
            }
        }

        Object[][] data = rows.toArray(new Object[0][]);

        JTable table = new JTable(data, cols);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(largestKey);//Font Render for spacing?
        table.getColumnModel().getColumn(1).setPreferredWidth(25);
        table.getColumnModel().getColumn(2).setPreferredWidth(25);
        table.setOpaque(false);
        table.setBackground(new Color(0,0,0,0));
        table.setShowGrid(true);
        table.setGridColor(new Color(255, 255, 255, 30));
        table.setIntercellSpacing(new Dimension(0,0));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    ((JComponent)c).setOpaque(false);
                }
                return c;
            }
        };


        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.getTableHeader().setDefaultRenderer(renderer);

        return table;
    }
    private JTable makeSemiRandomStatsTable(){
        String[] cols = {"Category","Item","Wins","Losses"};
        List<Object[]> rows = new ArrayList<>();
        FontMetrics fm = panel.getFontMetrics(panel.getFont());
        int largstCat = 0;
        int largestKey = 0;

        for (String cat : List.of("Primary","Secondary","Throwable","Armor_Passive","Armor_Level","Booster","Enemy","Stratagem")) {
            List<Map.Entry<String, WinLose>> e = jsonSemiRandom.getTopWins(cat);
            if (e != null) {
                for (Map.Entry<String, WinLose> stringWinLoseEntry : e) {
                    if (fm.stringWidth(cat) > largstCat) {
                        largstCat = fm.stringWidth(cat);
                    }
                    if (fm.stringWidth(stringWinLoseEntry.getKey()) > largestKey) {
                        largestKey = fm.stringWidth(stringWinLoseEntry.getKey());
                    }
                    rows.add(new Object[]{cat, stringWinLoseEntry.getKey(), stringWinLoseEntry.getValue().getWin(), stringWinLoseEntry.getValue().getLose()});
                }
            }
        }

        Object[][] data = rows.toArray(new Object[0][]);

        JTable table = new JTable(data, cols);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(largstCat);
        table.getColumnModel().getColumn(1).setPreferredWidth(largestKey);
        table.getColumnModel().getColumn(2).setPreferredWidth(25);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);

        return table;
    }
    private JScrollPane wrapTable(JTable table){
        JScrollPane scroll = new JScrollPane(table);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new CustomScrollbar());
        return scroll;
    }
    public static JPanel getPanel() {
        return panel;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // MUST be first

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imageOpacity));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
    private void makeRanomStatsTables(){
        JScrollPane topStatsScroll = new JScrollPane(topStats);
        topStatsScroll.setVerticalScrollBar(new CustomScrollbar());
        topStatsScroll.setPreferredSize(new Dimension(topStats.getPreferredSize().width +125,150));

        topStatsScroll.setOpaque(false);
        topStatsScroll.getViewport().setOpaque(false);
        topStatsScroll.setBorder(null);

        JScrollPane randomStatsScroll = new JScrollPane(randomStats); //probably should split categories into their own tables
        randomStatsScroll.setVerticalScrollBar(new CustomScrollbar());
        randomStatsScroll.setPreferredSize(new Dimension(randomStats.getPreferredSize().width +125,200));
        randomStatsScroll.setOpaque(false);
        randomStatsScroll.getViewport().setOpaque(false);
        randomStatsScroll.setBorder(null);

        setOpaque(true);
        topStatslabel.setBackground(bgColor);
        topStatslabel.setForeground(fgColor);

        JTable prim = makeRandomStatsTablePanel("Primary");
        prim.setAutoCreateRowSorter(true);
        JLabel primLabel = new JLabel("Primaries");
        JScrollPane primScroll = wrapTable(prim);

        JTable sec = makeRandomStatsTablePanel("Secondary");
        sec.setAutoCreateRowSorter(true);
        JLabel secLabel = new JLabel("Secondaries");
        JScrollPane secScroll = wrapTable(sec);

        JTable thr = makeRandomStatsTablePanel("Throwable");
        thr.setAutoCreateRowSorter(true);
        JLabel thrLabel = new JLabel("Throwables");
        JScrollPane thrScroll = wrapTable(thr);

        JTable ap = makeRandomStatsTablePanel("Armor_Passive");
        ap.setAutoCreateRowSorter(true);
        JLabel apLabel = new JLabel("Armor Passives");
        JScrollPane apScroll = wrapTable(ap);

        JTable al = makeRandomStatsTablePanel("Armor_Level");
        al.setAutoCreateRowSorter(true);
        JLabel alLabel = new JLabel("Armor Level");
        JScrollPane alScroll = wrapTable(al);

        JTable b = makeRandomStatsTablePanel("Booster");
        b.setAutoCreateRowSorter(true);
        JLabel bLabel = new JLabel("Boosters");
        JScrollPane bScroll = wrapTable(b);

        JTable en = makeRandomStatsTablePanel("Enemy");
        en.setAutoCreateRowSorter(true);
        JLabel enLabel = new JLabel("Enemies");
        JScrollPane enScroll = wrapTable(en);

        JTable s = makeRandomStatsTablePanel("Stratagem");
        s.setAutoCreateRowSorter(true);
        JLabel sLabel = new JLabel("Stratagems");
        JScrollPane sScroll = wrapTable(s);

        GroupLayout statsPanelLayout = new GroupLayout(this);

        statsPanelLayout.setHorizontalGroup(
                statsPanelLayout.createParallelGroup()
                        .addGroup(statsPanelLayout.createParallelGroup()
                                .addComponent(topStatslabel)
                                .addComponent(topStatsScroll)
                        )
                        .addGroup(statsPanelLayout.createSequentialGroup()
                                .addGroup(statsPanelLayout.createParallelGroup()
                                        .addComponent(primLabel)
                                        .addComponent(primScroll,prim.getPreferredSize().width,prim.getPreferredSize().width +25,prim.getPreferredSize().width+100)
                                        .addComponent(secLabel)
                                        .addComponent(secScroll,sec.getPreferredSize().width,sec.getPreferredSize().width +25,sec.getPreferredSize().width+100)
                                        .addComponent(thrLabel)
                                        .addComponent(thrScroll,thr.getPreferredSize().width,thr.getPreferredSize().width +25,thr.getPreferredSize().width+100)
                                        .addComponent(leftCycleButton,25,25,25)
                                )
                                .addGap(10)
                                .addGroup(statsPanelLayout.createParallelGroup()
                                        .addComponent(alLabel)
                                        .addComponent(alScroll,al.getPreferredSize().width,al.getPreferredSize().width +25,al.getPreferredSize().width+100)
                                        .addComponent(apLabel)
                                        .addComponent(apScroll,ap.getPreferredSize().width,ap.getPreferredSize().width +25,ap.getPreferredSize().width+100)
                                        .addComponent(bLabel)
                                        .addComponent(bScroll,b.getPreferredSize().width,b.getPreferredSize().width +25,b.getPreferredSize().width+100)
                                        .addGroup(statsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGap(250)
                                                .addComponent(refreashButton)
                                        )
                                )
                                .addGap(10)
                                .addGroup(statsPanelLayout.createParallelGroup()
                                        .addComponent(enLabel)
                                        .addComponent(enScroll,en.getPreferredSize().width,en.getPreferredSize().width +25,en.getPreferredSize().width+100)
                                        .addComponent(sLabel)
                                        .addComponent(sScroll,s.getPreferredSize().width,s.getPreferredSize().width +25,s.getPreferredSize().width+100)
                                        .addGroup(statsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGap(365)
                                                .addComponent(rightCycleButton,25,25,25)
                                        )
                                )
                        )
        );

        statsPanelLayout.setVerticalGroup(
                statsPanelLayout.createSequentialGroup()
                        .addComponent(topStatslabel)
                        .addComponent(topStatsScroll,150,150,150)
                        .addGap(10)
                        .addGroup(statsPanelLayout.createParallelGroup()
                                .addGroup(statsPanelLayout.createSequentialGroup()
                                        .addComponent(primLabel)
                                        .addComponent(primScroll,75,75,75)
                                        .addComponent(secLabel)
                                        .addComponent(secScroll,75,75,75)
                                        .addComponent(thrLabel)
                                        .addComponent(thrScroll,75,75,75)
                                        .addComponent(leftCycleButton,25,25,25)
                                )
                                .addGroup(statsPanelLayout.createSequentialGroup()
                                        .addComponent(alLabel)
                                        .addComponent(alScroll,68,68,68)
                                        .addComponent(apLabel)
                                        .addComponent(apScroll,75,75,75)
                                        .addComponent(bLabel)
                                        .addComponent(bScroll,82,82,82)
                                        .addComponent(refreashButton)
                                )
                                .addGroup(statsPanelLayout.createSequentialGroup()
                                        .addComponent(enLabel)
                                        .addComponent(enScroll,68,68,68)
                                        .addComponent(sLabel)
                                        .addComponent(sScroll,172,172,172)
                                        .addComponent(rightCycleButton,25,25,25)
                                )
                        )
        );
        setLayout(statsPanelLayout);
    }
    private void makeSemiRanomStatsTables(){
        JScrollPane topStatsScroll = new JScrollPane(topStats);
        topStatsScroll.setVerticalScrollBar(new CustomScrollbar());
        topStatsScroll.setPreferredSize(new Dimension(topStats.getPreferredSize().width +125,150));

        topStatsScroll.setOpaque(false);
        topStatsScroll.getViewport().setOpaque(false);
        topStatsScroll.setBorder(null);

        JScrollPane semirandomStatsScroll = new JScrollPane(semirandomStats);
        semirandomStatsScroll.setVerticalScrollBar(new CustomScrollbar());
        semirandomStatsScroll.setPreferredSize(new Dimension(semirandomStats.getPreferredSize().width +125,200));
        semirandomStatsScroll.setOpaque(false);
        semirandomStatsScroll.getViewport().setOpaque(false);
        semirandomStatsScroll.setBorder(null);

        setOpaque(true);
        topStatslabel.setBackground(bgColor);
        topStatslabel.setForeground(fgColor);

        JTable sec = makeRandomStatsTablePanel("Secondary");
        sec.setAutoCreateRowSorter(true);
        JLabel secLabel = new JLabel("Secondaries");
        JScrollPane secScroll = wrapTable(sec);

        JTable thr = makeRandomStatsTablePanel("Throwable");
        thr.setAutoCreateRowSorter(true);
        JLabel thrLabel = new JLabel("Throwables");
        JScrollPane thrScroll = wrapTable(thr);

        JTable al = makeRandomStatsTablePanel("Armor_Level");
        al.setAutoCreateRowSorter(true);
        JLabel alLabel = new JLabel("Armor Level");
        JScrollPane alScroll = wrapTable(al);

        JTable b = makeRandomStatsTablePanel("Booster");
        b.setAutoCreateRowSorter(true);
        JLabel bLabel = new JLabel("Boosters");
        JScrollPane bScroll = wrapTable(b);

        JTable en = makeRandomStatsTablePanel("Enemy");
        en.setAutoCreateRowSorter(true);
        JLabel enLabel = new JLabel("Enemies");
        JScrollPane enScroll = wrapTable(en);

        JTable s = makeRandomStatsTablePanel("Stratagem");
        s.setAutoCreateRowSorter(true);
        JLabel sLabel = new JLabel("Stratagems");
        JScrollPane sScroll = wrapTable(s);

        GroupLayout statsPanelLayout = new GroupLayout(this);

        statsPanelLayout.setHorizontalGroup(
                statsPanelLayout.createParallelGroup()
                        .addGroup(statsPanelLayout.createParallelGroup()
                                .addComponent(topStatslabel)
                                .addComponent(topStatsScroll)
                        )
                        .addGroup(statsPanelLayout.createSequentialGroup()
                                .addGroup(statsPanelLayout.createParallelGroup()
                                        .addComponent(secLabel)
                                        .addComponent(secScroll,sec.getPreferredSize().width,sec.getPreferredSize().width +25,sec.getPreferredSize().width+100)
                                        .addComponent(thrLabel)
                                        .addComponent(thrScroll,thr.getPreferredSize().width,thr.getPreferredSize().width +25,thr.getPreferredSize().width+100)
                                        .addComponent(leftCycleButton,25,25,25)
                                )
                                .addGap(10)
                                .addGroup(statsPanelLayout.createParallelGroup()
                                        .addComponent(alLabel)
                                        .addComponent(alScroll,al.getPreferredSize().width,al.getPreferredSize().width +25,al.getPreferredSize().width+100)
                                        .addComponent(bLabel)
                                        .addComponent(bScroll,b.getPreferredSize().width,b.getPreferredSize().width +25,b.getPreferredSize().width+100)
                                        .addGroup(statsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGap(250)
                                                .addComponent(refreashButton)
                                        )
                                )
                                .addGap(10)
                                .addGroup(statsPanelLayout.createParallelGroup()
                                        .addComponent(enLabel)
                                        .addComponent(enScroll,en.getPreferredSize().width,en.getPreferredSize().width +25,en.getPreferredSize().width+100)
                                        .addComponent(sLabel)
                                        .addComponent(sScroll,s.getPreferredSize().width,s.getPreferredSize().width +25,s.getPreferredSize().width+100)
                                        .addGroup(statsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGap(365)
                                                .addComponent(rightCycleButton,25,25,25)
                                        )
                                )
                        )

        );

        statsPanelLayout.setVerticalGroup(
                statsPanelLayout.createSequentialGroup()
                        .addComponent(topStatslabel)
                        .addComponent(topStatsScroll,150,150,150)
                        .addGap(10)
                        .addGroup(statsPanelLayout.createParallelGroup()
                                .addGroup(statsPanelLayout.createSequentialGroup()
                                        .addComponent(secLabel)
                                        .addComponent(secScroll,75,75,75)
                                        .addComponent(thrLabel)
                                        .addComponent(thrScroll,75,75,75)
                                        .addComponent(leftCycleButton,25,25,25)
                                )
                                .addGroup(statsPanelLayout.createSequentialGroup()
                                        .addComponent(alLabel)
                                        .addComponent(alScroll,75,75,75)
                                        .addComponent(bLabel)
                                        .addComponent(bScroll,75,75,75)
                                        .addComponent(refreashButton,25,25,25)
                                )
                                .addGroup(statsPanelLayout.createSequentialGroup()
                                        .addComponent(enLabel)
                                        .addComponent(enScroll,75,75,75)
                                        .addComponent(sLabel)
                                        .addComponent(sScroll,75,75,75)
                                        .addComponent(rightCycleButton,25,25,25)
                                )
                        )
        );
        setLayout(statsPanelLayout);
    }
}

