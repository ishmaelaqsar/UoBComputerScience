import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseConnect {

    // database URL
    private static final String DB_URL = "jdbc:postgresql://mod-fund-databases.cs.bham.ac.uk:5432/ixa444";
    // Database credentials
    private static final String USER = "ixa444";
    private static final String PASS = "XPBAFKGJ1";

    private Connection connection = null;

    private ArrayList<String> songs = new ArrayList<>();
    private ArrayList<String> artists = new ArrayList<>();
    private ArrayList<String> albums = new ArrayList<>();
    private ArrayList<ArrayList<String>> tags = new ArrayList<>();

    public static void main(String[] args) {
        DatabaseConnect db = new DatabaseConnect();
        db.openConnection();
        db.readFile();
        db.populateDatabase();
        db.closeConnection();
    }

    public void openConnection() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("Database accessed!");
        } else {
            System.out.println("Failed to make connection.");
        }

    }

    public void closeConnection() {
        try {
            connection.close();

            if (connection.isClosed()) {
                System.out.println("Connection is closed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        String line;
        String splitter = ",";
        String[] token;
        List<String> list = new ArrayList<>();

        try {
            /*
             * to read data from file
             */
            BufferedReader br = new BufferedReader(new FileReader("src/artists-songs-albums-tags.csv"));

            while ((line = br.readLine()) != null) {
                token = line.split(splitter); // to separate each word afer
                // looking " , " in the file and
                // make it as token
                list.addAll(Arrays.asList(token));

            }

            // convert the list into array
            String[] arrString = list.toArray(new String[list.size()]);

            br.close();

            ArrayList<String> ntags = new ArrayList<>();
            int count = 0;

            for (int i = 5; i < arrString.length; i++) {
                if (count == 0) {
                    songs.add(arrString[i]);
                } else if (count == 1) {
                    artists.add(arrString[i]);
                } else if (count == 2) {
                    albums.add(arrString[i]);
                } else if (count == 3) {
                    ntags.add(arrString[i]);
                } else {
                    if (Character.isLowerCase(arrString[i].charAt(0))) {
                        ntags.add(arrString[i]);
                    } else {
                        count = 0;
                        tags.add(ntags);
                        songs.add(arrString[i]);
                        ntags = new ArrayList<>();
                    }
                }
                count++;
            }
//            tags.add(ntags);
//            tags.remove(0);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "Error reading file");
        }
    }

    public void populateDatabase() {
        String SQLartist = "INSERT INTO artists(id, name) VALUES(?,?)";
        try (PreparedStatement psmt = connection.prepareStatement(SQLartist)) {
            int counter = 0;
            for (String artist : this.artists) {
                psmt.setInt(1, counter);
                psmt.setString(2, artist);
                psmt.executeUpdate();
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String SQLalbum = "INSERT INTO albums(id, name) VALUES(?,?)";
        try (PreparedStatement psmt = connection.prepareStatement(SQLalbum)) {
            int counter = 0;
            for (String albums : this.albums) {
                psmt.setInt(1, counter);
                psmt.setString(2, albums);
                psmt.executeUpdate();
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String SQL = "INSERT INTO songs(id, title, artist, album) VALUES(?,?,?,?)";
        String artistSQL = "SELECT id FROM artists WHERE name LIKE ?";
        String albumSQL = "SELECT id FROM albums WHERE name LIKE ?";
        int artistID = 0;
        int albumID = 0;

        try (PreparedStatement psmt = connection.prepareStatement(SQL);
             PreparedStatement artistStatement = connection.prepareStatement(artistSQL);
             PreparedStatement albumStatement = connection.prepareStatement(albumSQL)) {
            int counter = 0;
            for (String string : songs) {
                psmt.setInt(1, counter);
                psmt.setString(2, string);

                artistStatement.setString(1, this.artists.get(counter));
                albumStatement.setString(1, this.albums.get(counter));

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
                counter++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        String SQL1 = "INSERT INTO tags(song, tag) VALUES(?,?)";

        try (PreparedStatement psmt = connection.prepareStatement(SQL1)) {
            int SQLcounter = 0;
            for (List<String> songTagList : tags) {
                for (String string : songTagList) {
                    psmt.setInt(1, SQLcounter);
                    psmt.setString(2, string);
                    psmt.executeUpdate();
                }
                SQLcounter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
