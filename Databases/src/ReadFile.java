/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author irum
 */
public class ReadFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String line;
        String splitter = ",";
        String[] token;

        List<String> list = new ArrayList<String>();
        try {
     /*
           to read data from file
           */
            BufferedReader br = new BufferedReader(new FileReader("artists-songs-albums-tags.csv"));

            while ((line = br.readLine()) != null) {
                token = line.split(splitter);  //to separate each word afer looking " , " in the file and make it as token
                for (int i = 0; i < token.length; i++) {
                    list.add(token[i]);
                }
            }

            List<String> newList = list.subList(5, list.size());


            List<String> artists = new ArrayList<>();
            List<String> songs = new ArrayList<>();
            List<String> albums = new ArrayList<>();
            List<ArrayList<String>> tags = new ArrayList<>();
            ArrayList<String> toAdd = new ArrayList<>();

            int counter = 0;

            for (String string : newList) {
                if (string.substring(0, 1).matches("[A-Z\\d\\W\"]") && counter == 1) {
                    artists.add(string);
                    counter++;
                } else if (string.substring(0, 1).matches("[A-Z\\d\\W\"]") && counter == 0) {
                    songs.add(string);
                    tags.add(toAdd);
                    toAdd = new ArrayList<>();
                    counter++;
                } else if (string.substring(0, 1).matches("[A-Z\\d\\W\"]") && counter == 2) {
                    albums.add(string);
                    counter = 0;
                } else if (string.substring(0, 1).matches("[a-z\\d\\W]")) {
                    toAdd.add(string);
                }
            }
            tags.add(toAdd);
            tags.remove(0);
            System.out.println(tags);
            br.close();

            List<String> filteredAlbums = albums.stream().distinct().collect(Collectors.toList());

            String url = "jdbc:postgresql://mod-fund-databases.cs.bham.ac.uk:5432/clw762";
            String user = "clw762";
            String password = "Prk379xq";

            String SQL = "INSERT INTO tags(songid, tag) VALUES(?,?)";

            try (Connection a = DriverManager.getConnection(url, user, password);
                 PreparedStatement psmt = a.prepareStatement(SQL)) {
                int SQLcounter = 0;
                for (List<String> songTagList : tags) {
                    for (String string : songTagList) {
                        psmt.setInt(1, SQLcounter);
                        psmt.setString(2, string);
                        psmt.executeUpdate();
                    }
                    SQLcounter++;
                }
                a.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String SQL2 = "INSERT INTO songs(id, title, artist, album) VALUES(?,?,?,?)";
            String artistSQL = "SELECT id FROM artists WHERE name LIKE ?";
            String albumSQL = "SELECT id FROM albums WHERE name LIKE ?";
            int artistID = 0;
            int albumID = 0;

            try (Connection a = DriverManager.getConnection(url, user, password);
                 PreparedStatement psmt = a.prepareStatement(SQL2);
                 PreparedStatement artistStatement = a.prepareStatement(artistSQL);
                 PreparedStatement albumStatement = a.prepareStatement(albumSQL)) {
                int SQLcounter = 0;
                for (String string : songs) {
                    psmt.setInt(1, SQLcounter);
                    psmt.setString(2, string);

                    artistStatement.setString(1, artists.get(SQLcounter));
                    albumStatement.setString(1, albums.get(SQLcounter));

                    ResultSet artistResult = artistStatement.executeQuery();
                    while (artistResult.next()) {
                        artistID = artistResult.getInt(1);
                    }

                    ResultSet albumResult = albumStatement.executeQuery();
                    while (albumResult.next()) {
                        albumID = albumResult.getInt(1);
                    }

                    psmt.setInt(3, artistID);
                    psmt.setInt(4, albumID);

                    psmt.executeUpdate();
                    SQLcounter++;
                }
                a.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "Error reading file");
        }
    }

}
