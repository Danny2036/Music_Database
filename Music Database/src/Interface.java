import java.awt.EventQueue;

import javax.swing.BoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JScrollPane;


public class Interface {

	private JFrame frame;
	
	private JTextField artistTxt;
	private JTextField albumTxt;
	private JTextField songTxt;
	private JTextField yearTxt;
	
	private JTable albumTable;
	
	private JTable songTable;
	private JTable artistTable;
	
	JLabel artistLbl;
	JLabel albumLbl;
	JLabel songLbl;
	JLabel yearLbl;
	
	JRadioButton artistBtn;
	JRadioButton albumBtn; 
	JRadioButton songBtn;
	JRadioButton yearBtn; 
	
	JLabel artistTableLbl;
	JLabel albumTableLbl;
	JLabel songTableLbl;
	JLabel yearTableLbl;
	
	JButton searchBtn;
	private JScrollPane albumScrollPane;
	private JScrollPane songScrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		artistTxt = new JTextField();
		artistTxt.setBounds(32, 57, 140, 20);
		frame.getContentPane().add(artistTxt);
		artistTxt.setColumns(10);
		
		artistLbl = new JLabel("Artist Name");
		artistLbl.setBounds(32, 32, 86, 14);
		frame.getContentPane().add(artistLbl);
		
		albumTable = new JTable(5,5);
		albumTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Artist", "Year", "# of Tracks"
			}
		));
		albumTable.setBounds(194, 172, 400, 64);
		//frame.getContentPane().add(albumTable);
		
		albumTxt = new JTextField();
		albumTxt.setColumns(10);
		albumTxt.setBounds(32, 108, 140, 20);
		frame.getContentPane().add(albumTxt);
		
		albumLbl = new JLabel("Album Name");
		albumLbl.setBounds(32, 83, 86, 14);
		frame.getContentPane().add(albumLbl);
		
		songTxt = new JTextField();
		songTxt.setColumns(10);
		songTxt.setBounds(32, 169, 140, 20);
		frame.getContentPane().add(songTxt);
		
		songLbl = new JLabel("Song Name");
		songLbl.setBounds(32, 149, 86, 14);
		frame.getContentPane().add(songLbl);
		
		yearTxt = new JTextField();
		yearTxt.setColumns(10);
		yearTxt.setBounds(32, 222, 140, 20);
		frame.getContentPane().add(yearTxt);
		
		yearLbl = new JLabel("Year");
		yearLbl.setBounds(32, 202, 86, 14);
		frame.getContentPane().add(yearLbl);
		
		songTable = new JTable();
		songTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Artist", "Album Id"
			}
		));
		songTable.setBounds(194, 260, 400, 65);
		//frame.getContentPane().add(songTable);
		
		albumTableLbl = new JLabel("Albums");
		albumTableLbl.setBounds(327, 126, 193, 14);
		frame.getContentPane().add(albumTableLbl);
		
		JLabel songTableLbl = new JLabel("Song");
		songTableLbl.setBounds(327, 235, 193, 14);
		frame.getContentPane().add(songTableLbl);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] artistTableData = Interface.createArtistTable();
				String[][] albumTableData = Interface.createAlbumTable();
				String[][] songTableData = Interface.createSongTable();
				
				for(int i = 0; i < artistTableData.length; i++){
					((DefaultTableModel)artistTable.getModel()).addRow(artistTableData[i]);
				}
				
				for(int i = 0; i < albumTableData.length; i++){
					((DefaultTableModel)albumTable.getModel()).addRow(albumTableData[i]);
				}
				
				for(int i = 0; i < songTableData.length; i++){
					((DefaultTableModel)songTable.getModel()).addRow(songTableData[i]);
				}
			}
		});
		searchBtn.setBounds(63, 289, 89, 23);
		frame.getContentPane().add(searchBtn);
		
		artistBtn = new JRadioButton("");
		artistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(artistBtn.isSelected()){
					artistTxt.setEnabled(true);
				} else {
					artistTxt.setEnabled(false);
				}
			}
		});
		artistBtn.setBounds(9, 56, 109, 23);
		frame.getContentPane().add(artistBtn);
		
		albumBtn = new JRadioButton("");
		albumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(albumBtn.isSelected()){
					albumTxt.setEnabled(true);
				} else {
					albumTxt.setEnabled(false);
				}
			}
		});
		albumBtn.setBounds(9, 108, 109, 23);
		frame.getContentPane().add(albumBtn);
		
		songBtn = new JRadioButton("");
		songBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(songBtn.isSelected()){
					songTxt.setEnabled(true);
				} else {
					songTxt.setEnabled(false);
				}
			}
		});
		songBtn.setBounds(9, 168, 109, 23);
		frame.getContentPane().add(songBtn);
		
		yearBtn = new JRadioButton("");
		yearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(yearBtn.isSelected()){
					yearTxt.setEnabled(true);
				} else {
					yearTxt.setEnabled(false);
				}
			}
		});
		yearBtn.setBounds(9, 221, 109, 23);
		frame.getContentPane().add(yearBtn);
		
		artistTable = new JTable(5, 5);
		artistTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "# of Albums", "# of Tracks"
			}
		));
		artistTable.setBounds(194, 11, 400, 64);
		
		artistTableLbl = new JLabel("Artist");
		artistTableLbl.setBounds(327, 11, 193, 14);
		frame.getContentPane().add(artistTableLbl);
		
		JScrollPane artistScrollPane = new JScrollPane();
		artistScrollPane.setBounds(194, 32, 400, 83);
		frame.getContentPane().add(artistScrollPane);
		artistScrollPane.setViewportView(artistTable);
		
		albumScrollPane = new JScrollPane();
		albumScrollPane.setBounds(194, 149, 400, 75);
		frame.getContentPane().add(albumScrollPane);
		albumScrollPane.setViewportView(albumTable);
		
		songScrollPane = new JScrollPane();
		songScrollPane.setBounds(194, 260, 400, 75);
		frame.getContentPane().add(songScrollPane);
		songScrollPane.setViewportView(songTable);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

    public static String[][] createArtistTable(Connection con, String dbname, String name) throws SQLException{
        Statement stmt = null;
        String query = "select * from "+dbname+".ARTIST A where ";
        if(name!=null && name.length()>0){
            query+="A.Artist_Name like '%"+name+"%'";
        }
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int rowCount = 0;
            if(rs.last()){
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
            String[][] strings = new String[rowCount][4];
            int count = 0;
            while(rs.next()){
                String artistName = rs.getString("Artist_Name");
                String artistId =""+ rs.getInt("Artist_ID");
                String numAlbums =""+ rs.getInt("Num_Albums");
                String numTracks =""+ rs.getInt("numArtistTracks");

                strings[count][0] = artistId;
                strings[count][1] = artistName;
                strings[count][2] = numAlbums;
                strings[count][3] = numTracks;
                count++;
            }
            return strings;
        }
        catch(SQLException e){
            
        }
        finally{
            if(stmt != null){
                stmt.close();
            }
            return null;
        }
    }
    
    public static String[][] createAlbumTable(Connection con, String dbname, String name, Integer year, String artistname) throws SQLException{
        Statement stmt = null;
        String query = "select * from "+dbname+".ALBUM A where ";
        if(name!=null && name.length()>0){
            query += "A.Album_Name like '%"+name+"%'";
            if( year != null || (artistname!= null && artistname.length()>0)){
                query+= " and ";
            }
        }
        if(year != null){
            query += "A.Year='"+year+"'";
            if((artistname!= null && artistname.length()>0)){
                query+= " and ";
            }
        }
        if((artistname!=null && artistname.length()>0)){
            query+="A.Artist_Name like '%"+artistname+"%'";
        }
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int rowCount = 0;
            if(rs.last()){
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
            int count = 0;
            String[][] strings = new String[rowCount][5];
            while(rs.next()){
                String albumname = rs.getString("Album_Name");
                String albumid =""+ rs.getInt("Album_ID");
                String numtracks =""+ rs.getInt("numAlbumTracks");
                String albumyear =""+ rs.getInt("Year");
                String artist = ""+ rs.getString("Artist_Name");
                
                strings[count][0] = albumid;
                strings[count][1] = albumname;
                strings[count][2] = artist;
                strings[count][3] = albumyear;
                strings[count][4] = numtracks;
                count++;
            }
            return strings;
        }
        catch (SQLException e){
            
        }
        finally{
            if(stmt != null){
                stmt.close();
            }
            return null;
        }
    }
    
    public static String[][] createTrackTable(Connection con, String dbname, String name, String albumName, String artistName, Integer year) throws SQLException {
        Statement stmt = null;
        String query = "select T from "+dbname+".ALBUM A, "+dbname+".TRACK T where ";
        if(name!= null && name.length()>0){
            query+="T.Track_Name like '%"+name+"%'";
            if((albumName!=null && albumName.length() > 0) || (artistName != null && artistName.length() > 0) || year!=null){
                query+=" and ";
            }
        }
        if(albumName!=null && albumName.length() > 0){
            query+="T.Album_ID=A.Album_ID and A.Album_Name like '%"+albumName+"%'";
            if((artistName != null && artistName.length() > 0) || year!=null){
                query+=" and ";
            }
        }
        if(artistName!=null && artistName.length() > 0){
            query+="T.Artist_Name like '%"+artistName+"%'";
            if(year!= null){
                query+=" and ";
            }
        }
        if(year != null){
            query+="A.Album_ID=T.Album_ID and A.Year='"+year+"'";
        }
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int rowCount = 0;
            if(rs.last()){
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
            int count = 0;
            String[][] strings = new String[rowCount][4];
            while(rs.next()){
                String trackname = rs.getString("Track_Name");
                String trackid =""+ rs.getInt("Track_ID");
                String artistname = rs.getString("Artist_Name");
                String albumid = "" +rs.getInt("Album_ID");
                
                strings[count][0] = trackid;
                strings[count][1] = trackname;
                strings[count][2] = artistname;
                strings[count][3] = albumid;
                count++;
            }
        }
        catch (SQLException e){
            
        }
        finally{
            if(stmt != null){
                stmt.close();
            }
                return null;
        }
    }
}